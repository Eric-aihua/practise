package com.eric.excel.poi.readsample;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ClientTest2 {
	public static void main(String[] args) {
		try {
			String path = "D://work//昌吉//关注//调研数据//工商局//0810//工商局个体、企业注册信息//个体7276户.xls";
			System.out.println(Common.PROCESSING + path);
			InputStream is = new FileInputStream(path);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			// Read the Sheet
			for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
				HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
				if (hssfSheet == null) {
					continue;
				}
				// Read the Row
				for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
					HSSFRow hssfRow = hssfSheet.getRow(rowNum);
					if (hssfRow != null) {
						HSSFCell no = hssfRow.getCell(0);
						HSSFCell name = hssfRow.getCell(1);
						HSSFCell age = hssfRow.getCell(2);
						HSSFCell score = hssfRow.getCell(3);
						System.out.println(no+" "+name+" "+age+" "+score);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
