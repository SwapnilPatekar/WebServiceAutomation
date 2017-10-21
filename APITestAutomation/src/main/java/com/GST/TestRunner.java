package com.GST;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.API.TestManagementPOJO.MasterScriptPOJO;
import com.API.Utils.ExcelOperation;
import com.API.Utils.FrameworkServices;
import com.AQMTech.Keyword.FunctionalKeyword;
import com.beust.jcommander.Parameter;

public class TestRunner  extends FunctionalKeyword{
	FileInputStream inputStream;
	XSSFWorkbook workbook; 
	@Parameters({ 
		"Description", "ScriptReference", "ScenarioReference", 
	})

	@Test
	public void test(String description,String scriptReference,String scenarioReference) throws Exception {
		ExcelOperation excelOperation=new ExcelOperation();
		boolean flag=false;
		try {
			FrameworkServices frameworkServices=new FrameworkServices();
			//ExcelOperation excelOperation=new ExcelOperation();
			List< MasterScriptPOJO> scriptList=frameworkServices.getMasterScript(scriptReference);
			FileInputStream fileInputStream=new FileInputStream(new File(frameworkServices.getConfigProperties().getProperty("TestDataFileLocation")+"//"+frameworkServices.getConfigProperties().getProperty("TestDataWorkBook")));
			XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
			for(MasterScriptPOJO masterScriptPOJO:scriptList) {
				executeTestStep(workbook, masterScriptPOJO, scenarioReference,excelOperation);
			}
			flag=true;
			//excelOperation.updateStatusInExcel(workbook, "TestScenarios", scenarioReference, "Passed");
		}
		catch(Exception |AssertionError e) {
			flag=false;
			Assert.assertEquals(true, false,"Failed due to exception");
			//excelOperation.updateStatusInExcel(workbook, "TestScenarios", scenarioReference, "Failed");
		}
		finally{
			FileInputStream fileInputStream=new FileInputStream(new File(FrameworkServices.getConfigProperties().getProperty("TestDataFolder")+FrameworkServices.getConfigProperties().getProperty("TestScenarioFileLocation")));
			XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
			if(flag) {
				excelOperation.updateStatusInExcel(workbook, "TestScenarios", scenarioReference, "Passed");
			}
			else
			{
				excelOperation.updateStatusInExcel(workbook, "TestScenarios", scenarioReference, "Failed");

			}
		}
	}
}
