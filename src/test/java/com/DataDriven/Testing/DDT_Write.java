package com.DataDriven.Testing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DDT_Write {

	public static void main(String[] args) throws Throwable {
		
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("TestSheet1.xlsx");
		XSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("Name");
		row.createCell(1).setCellValue("ID");
		XSSFRow row1=sheet.createRow(1);
		row1.createCell(0).setCellValue("King");
		row1.createCell(1).setCellValue("54");
		FileOutputStream fos=new FileOutputStream("C:\\Users\\debap\\eclipse-workspace\\Automation_Program\\src\\test\\java\\com\\DataDriven\\Testing\\DDT_Write.xlsx");
		wb.write(fos);
		wb.close();
		fos.close();
		
		
		
	}
}
