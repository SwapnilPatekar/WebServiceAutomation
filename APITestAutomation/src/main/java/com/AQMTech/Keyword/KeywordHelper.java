package com.AQMTech.Keyword;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.API.Utils.ExcelOperation;
import com.API.requests.GetAllApplicationRequest;
import com.API.requests.GetApplicationRequestNewReqSubmit;
import com.API.requests.GetAuthToken;
import com.API.requests.GetEntityRequests;

public class KeywordHelper {
	public void getAuthentication(XSSFWorkbook workbook,String scenarioID, ExcelOperation excelOperation) throws Exception {
		//TODO Create object of Request then call method which contains request
		GetAuthToken authtoken = new GetAuthToken();
		authtoken.getAuthenticationRequest(workbook, scenarioID,excelOperation);
	}
	public void getAllApplicationRequest(XSSFWorkbook workbook,String scenarioID, ExcelOperation excelOperation) throws Exception {
		//TODO Create object of Request then call method which contains request
		GetAllApplicationRequest apprequest =new GetAllApplicationRequest();
		apprequest.getAllApplicationRequest(workbook, scenarioID,excelOperation);
	}
	public void getAllApplicationRequestNewReqSubmit(XSSFWorkbook workbook,String scenarioID, ExcelOperation excelOperation) throws Exception {
		//TODO Create object of Request then call method which contains request
		GetApplicationRequestNewReqSubmit newReq = new GetApplicationRequestNewReqSubmit();
		newReq.getApplicationRequestNewReqSubmit(workbook, scenarioID, excelOperation);
	}
	public void getEntityRequests(XSSFWorkbook workbook,String scenarioID, ExcelOperation excelOperation) throws Exception {
		//TODO Create object of Request then call method which contains request
		GetEntityRequests entReq = new GetEntityRequests();
		entReq.getEntityRequests(workbook, scenarioID, excelOperation);
	}
	
}
