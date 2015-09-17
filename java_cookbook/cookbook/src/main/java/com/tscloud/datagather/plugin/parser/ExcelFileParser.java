package com.tscloud.datagather.plugin.parser;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *主要用来导入Excel文件，该文件需要满足一下条件
 * 1：该文件的第一行为表头，从第二行开始都是数据
 */
public class ExcelFileParser implements IFileParser {


    public ArrayList<ArrayList<String>> readExcel(String fileName, String path) {
        ArrayList<ArrayList<String>> Row = new ArrayList<ArrayList<String>>();

        try {
            Workbook workBook = null;
            try {
                workBook = new XSSFWorkbook(path + fileName);
            } catch (Exception ex) {
                workBook = new HSSFWorkbook(new FileInputStream(path + fileName));
            }

            for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
                Sheet sheet = workBook.getSheetAt(numSheet);
                if (sheet == null) {
                    continue;
                }
                //第一行为标题行，每一行遍历列的次数，要与第一行相同
                int totalColNumber=sheet.getRow(0).getLastCellNum();
                // 循环行Row，第一行为标题行，从第二行开始读取数据
                for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    // 循环列Cell
                    ArrayList<String> arrCell = new ArrayList<String>();
                    for (int cellNum = 0; cellNum < totalColNumber; cellNum++) {
                        Cell cell = row.getCell(cellNum);

                        arrCell.add(getValue(cell));
                    }
                    Row.add(arrCell);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Row;
    }

    private String getValue(Cell cell) {
        if (cell != null) {

            if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(cell.getBooleanCellValue());
            } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            } else {
                return String.valueOf(cell.getStringCellValue());
            }
        }else{
            return "";
        }
    }


}