package com.AQMTech.Keyword;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.API.TestManagementPOJO.MasterScriptPOJO;
import com.API.Utils.ExcelOperation;

public class FunctionalKeyword extends KeywordHelper {
	public void executeTestStep(XSSFWorkbook workbook,MasterScriptPOJO masterScriptPOJO,String scenarioID,ExcelOperation excelOperation) throws Exception {
		String executeStep=masterScriptPOJO.getStepkeyword();
		switch (executeStep) {
		case "Authentication":
			getAuthentication(workbook, scenarioID,excelOperation);
			break;
		case "GetAllApplicationRequest":
			getAllApplicationRequest(workbook, scenarioID,excelOperation);
			break;
		case "GetAllApplicationRequest_NewRegSubmit":
			getAllApplicationRequestNewReqSubmit(workbook, scenarioID, excelOperation);
			break;
		case "GetEntityRequests":
			getEntityRequests(workbook, scenarioID, excelOperation);
			break;
		default:
			break;
		}
	}
}
