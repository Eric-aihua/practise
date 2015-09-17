import com.tscloud.datagather.DataGatherExcelFilePlugin;

/**
 * Created by Eric on 2015/9/10.
 */
public class ParserExcelMain {


    public static void main(String args[]){
        String fileDir="D:\\work\\svn\\项目管理\\硚口政务云平台\\样例数据\\";
        String fileName="法人-企业信息.xlsx";
        String dbConnection="qkesb,qkmapgis,qkdi";

        DataGatherExcelFilePlugin plugin=new DataGatherExcelFilePlugin(fileDir,fileName,dbConnection);
        plugin.importFile();

    }

//    public static void readExcelTest(){
//        String fileDir="D:\\work\\svn\\项目管理\\硚口政务云平台\\样例数据\\";
//        String fileName="法人-企业信息.xlsx";
//        ExcelFileParser excelFileParser=new ExcelFileParser();
//       System.out.println(excelFileParser.readExcel(fileName,fileDir));
//    }

}
