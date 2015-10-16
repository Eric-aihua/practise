package eric.cookbook.cvs.sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 主要用来演示如何解析CSV文件
 * 
 * @author Eric
 *
 */
public class CSVReadSample {
	
	private String fileEncoding="UTF-8";

	public static void main(String[] args) {
		importCSV("D:\\work\\svn\\项目管理\\硚口政务云平台\\样例数据\\201501名录库地税局.csv");
	}

	public static List<String[]> importCSV(String fileName) {
		List<String[]> resultList = new ArrayList<String[]>();
		CSVReader csvReader = null;
		Reader fileReader = null;
		try {

			String[] csvRow = null;
			//加上编码，解决中文乱码问题
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"fileEncoding"));
			csvReader = new CSVReader(fileReader, ',');
			csvReader.readNext();
			while ((csvRow = csvReader.readNext()) != null) {
				resultList.add(csvRow);
				for (String col : csvRow) {
					System.out.print(col+'#');
				}
				System.out.println();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				csvReader.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultList;

	}

}
