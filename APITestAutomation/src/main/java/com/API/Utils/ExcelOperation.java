package com.API.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOperation {
	static Properties configProp;
	static FileInputStream configInput;
	public static Properties getConfigProperties() throws FileNotFoundException, IOException {
		if (configProp==null) {
			configProp = new Properties();
			configInput = new FileInputStream("config.properties");
			configProp.load(configInput);
		}
		return configProp;
	}
	public  void setDataInExcelFile(XSSFWorkbook workbook,String sheetName,int rowNum,int colNum,String data ) throws Exception {
		try {
			String excelFilePath = FrameworkServices.getConfigProperties().getProperty("TestDataFileLocation")+"\\"+FrameworkServices.getConfigProperties().getProperty("TestDataWorkBook");
			XSSFSheet sheet=workbook.getSheet(sheetName);
			XSSFCell cell = sheet.getRow(rowNum).getCell(colNum,Row.CREATE_NULL_AS_BLANK);
			cell.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(excelFilePath);
			workbook.write(fos);
			fos.close();
		} catch (Exception e){
			throw (e);
		}
	}
	public List<LinkedHashMap<String, String>> getScenarioData(XSSFWorkbook workbook,String sheetName,String scenario){
		List<LinkedHashMap<String, String>> testDataList=new ArrayList<LinkedHashMap<String, String>>();
		XSSFSheet sheet=workbook.getSheet(sheetName);
		for(int i=1;i<=sheet.getPhysicalNumberOfRows()-1;i++) {
			Row headerRow=sheet.getRow(0);
			Row row=sheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(scenario)) {
				LinkedHashMap<String, String> testData=new LinkedHashMap<String, String>();
				for(int j=1;j<row.getPhysicalNumberOfCells();j++) {
					if(row.getCell(j)!=null) {
						testData.put(headerRow.getCell(j).getStringCellValue(), row.getCell(j).getStringCellValue());
					}
					else {
						testData.put(headerRow.getCell(j).getStringCellValue(), "");
					}
				}
				System.out.println(testData);
				testDataList.add(testData);
			}
		}
		return testDataList;
	}
	/*
	 * PSS for jgetting Scenario Rows in List
	 * 
	 */
	public List<Integer> getScenarioRowforSettingData(XSSFWorkbook workbook,String sheetName, String scenarioID){
		List<Integer> rowPositionList=new ArrayList<Integer>();
		XSSFSheet sheet=workbook.getSheet(sheetName);
		for(int i=1;i<=sheet.getPhysicalNumberOfRows()-1;i++) {
			int j=0;
			Row headerRow=sheet.getRow(0);
			Row row=sheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(scenarioID)) {
				j=row.getRowNum();
				rowPositionList.add(j);
			}
		}
		return rowPositionList;
	}
	public void setDataIntoExcel(XSSFWorkbook workbook,String sheetName,int rowNumber ,String columnName, String testData) {
		XSSFSheet sheet=workbook.getSheet(sheetName);
		Row headerRow=sheet.getRow(0);
		LinkedHashMap<String, Integer> headerPosition=new LinkedHashMap<String, Integer>();
		for(int columnNo=1;columnNo<headerRow.getPhysicalNumberOfCells();columnNo++) {
			headerPosition.put(headerRow.getCell(columnNo).getStringCellValue(), columnNo);
		}
		Row dataRow=sheet.getRow(rowNumber);
		XSSFCell cell=(XSSFCell) dataRow.getCell(headerPosition.get(columnName),Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(testData);
	}
	public void closeWorkbook(XSSFWorkbook workbook) throws IOException {
		String excelFilePath = FrameworkServices.getConfigProperties().getProperty("TestDataFileLocation")+"\\"+FrameworkServices.getConfigProperties().getProperty("TestDataWorkBook");
		FileOutputStream fos = new FileOutputStream(excelFilePath);
		workbook.write(fos);
		fos.close();
	}
	public void createScenarioDataFromResponse(XSSFWorkbook workbook, String sheetName,String scenarioId,int rowsToBeAdded) throws IOException {

		XSSFSheet sheet=workbook.getSheet(sheetName);
		for(int i=1;i<=rowsToBeAdded;i++) {
			int currentRow=sheet.getPhysicalNumberOfRows();
			sheet.createRow(currentRow);
			XSSFCell cell=sheet.getRow(currentRow).getCell(0,Row.CREATE_NULL_AS_BLANK);
			cell.setCellValue(scenarioId);

		}
		closeWorkbook(workbook);
	}
	public void updateStatusInExcel(XSSFWorkbook workbook, String sheetName,String scenarioId, String data) throws FileNotFoundException, IOException {
		String excelFilePath = FrameworkServices.getConfigProperties().getProperty("TestDataFolder")+FrameworkServices.getConfigProperties().getProperty("TestScenarioFileLocation");
		XSSFSheet sheet=workbook.getSheet(sheetName);
		for (int i=1;i<=sheet.getPhysicalNumberOfRows();i++)
		{
			XSSFCell cell1=sheet.getRow(i).getCell(0,Row.CREATE_NULL_AS_BLANK);
			if(cell1.getStringCellValue().equalsIgnoreCase(scenarioId)) {
				XSSFCell cell = sheet.getRow(i).getCell(5);
				cell.setCellValue(data);
				FileOutputStream fos = new FileOutputStream(excelFilePath);
				workbook.write(fos);
				fos.close();
				break;
			}
		}
	}
}
