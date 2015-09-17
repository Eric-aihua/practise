package com.tscloud.datagather;

import com.tscloud.datagather.model.DBMetaFileMapping;
import com.tscloud.datagather.model.FileColumn;
import com.tscloud.datagather.model.TableMetaData;
import com.tscloud.datagather.persistence.FileRecordPersistenceCloudDBImpl;
import com.tscloud.datagather.persistence.IFileRecordPersistence;
import com.tscloud.datagather.plugin.parser.ExcelFileParser;
import com.tscloud.datagather.plugin.parser.IFileParser;
import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.*;

/**
 * Created by Eric on 2015/9/10.
 */
public class DataGatherExcelFilePlugin {
    private IFileParser parser;
    private IFileRecordPersistence persistence;

    private TableMetaData metaData;
    private String tableName;
    private String filePath;
    private String fileName;
    private String dbConn;

    public DataGatherExcelFilePlugin(String filePath,String fileName ,String dbConn){
        parser=new ExcelFileParser();
        persistence=new FileRecordPersistenceCloudDBImpl(dbConn);
        this.fileName=fileName;
        this.filePath=filePath;
        this.dbConn=dbConn;

    }

    public void importFile(){
        String tableMetaDataJsonFile=getTableMetataFile();
        generateFileDBFieldMapping(tableMetaDataJsonFile);
        ArrayList<ArrayList<String>> row= parser.readExcel(fileName, filePath);
        int insertCount=0;
        int faildCount=0;
        for (ArrayList<String> cell : row) {
            StringBuilder sqlBuilder=new StringBuilder("upsert into "+tableName+" (");
            buildFields(sqlBuilder);
            int valuesCount=0;

            for (String str : cell) {
                valuesCount++;
                sqlBuilder.append("'");
                sqlBuilder.append(str);
                if(valuesCount<metaData.getColumnMapping().size()){
                    sqlBuilder.append("',");
                }else{
                    sqlBuilder.append("'");
                }
            }
            sqlBuilder.append(")");
            System.out.println(sqlBuilder.toString());
            boolean insertResult=persistence.persist(sqlBuilder.toString());
            if(insertResult){
                insertCount++;
            }else{
                faildCount++;
            }

        }
        System.out.println("成功导入："+insertCount+" 条 失败导入："+faildCount+" 条");
    }

    private int buildFields(StringBuilder sqlBuilder) {
        int fieldCount=0;
        for(FileColumn fileColumn:metaData.getColumnMapping()){
            fieldCount++;
            sqlBuilder.append("");
            sqlBuilder.append(fileColumn.getDbFieldName());
            if(fieldCount<metaData.getColumnMapping().size()){
            sqlBuilder.append(",");
            }else{
                sqlBuilder.append("");
            }
        }
        sqlBuilder.append(") values (");
        return fieldCount;
    }

    /**
     * 根据JSON文件生成DB Table的元数据对象,在Json文件中定义的字段顺序，需要和Excel文件中的字段顺序一致
     * @param metaDataJsonFile
     */
    private void generateFileDBFieldMapping(String metaDataJsonFile) {
        String jsonStr=getJsonStr(metaDataJsonFile);
        metaData=JSON.parseObject(jsonStr, TableMetaData.class);
        tableName=metaData.getTableName();

    }

    private String getJsonStr(String jsonFile) {

        BufferedReader reader = null;
        String jsonStr = "";
        try {
            InputStream input = new FileInputStream(jsonFile);
            InputStreamReader inputStreamReader = new InputStreamReader(input, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = "";
            while ((tempString = reader.readLine()) != null) {
                jsonStr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonStr;
    }

    /**
     * 根据文件的位置，获取对应的JSON文件
     * @return
     */
    private String getTableMetataFile() {
        return DBMetaFileMapping.metaFileMapping.get(filePath+fileName);
    }



}
