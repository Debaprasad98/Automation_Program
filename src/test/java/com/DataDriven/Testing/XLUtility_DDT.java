package com.DataDriven.Testing;

import java.io.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

public class XLUtility_DDT {

	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	String path;
	XSSFCellStyle style;

	XLUtility_DDT(String path){
		this.path=path;
	}

	public int getRowCount(String sheetname) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
	}
	public int getCellCount(String sheetname,int rownum) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
	}

	public String getCellData(String sheetname,int rownum,int cellnum) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);

		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell);
		} catch (Exception e) {
			System.out.println("Error statment "+e.getMessage());
			data="";
		}
		workbook.close();
		fis.close();
		return data;
	}
	public void setCellData(String sheetname,int rownum,int cellnum,String data) throws IOException {
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet(sheetname);
		row=sheet.createRow(rownum);
		cell=row.createCell(cellnum);
		cell.setCellValue(data);
		fos= new FileOutputStream(path);
		workbook.write(fos);
		workbook.close();
		fos.close();

	}
	public void greenColor(String sheetname,int rownum,int cellnum) throws IOException {
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);

		style=workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		workbook.write(fos);
		workbook.close();
		fis.close();
		fos.close();
	}






}
