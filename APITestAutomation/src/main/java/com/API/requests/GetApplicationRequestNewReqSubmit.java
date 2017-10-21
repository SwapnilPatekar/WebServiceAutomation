package com.API.requests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.API.ServiceEnum.ServiceEndpoint;
import com.API.Utils.ExcelOperation;
import com.API.Utils.FrameworkServices;
import com.API.Utils.GetHeaders;
import com.API.Utils.GetParameters;
import com.API.Utils.SchemaValidator;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;

public class GetApplicationRequestNewReqSubmit {

	public void getApplicationRequestNewReqSubmit(XSSFWorkbook workbook,String scenarioID, ExcelOperation excelOperation) throws Exception {
		HashMap<String, String> headers = GetHeaders.getHeaders(workbook, scenarioID,excelOperation);
		HashMap<String, String> parameters = excelOperation.getScenarioData(workbook, "DT_Parameters", scenarioID).get(1);
		for (HashMap<String, String>arn: excelOperation.getScenarioData(workbook, "DT_ResponeGetAllApplication", scenarioID)  )
		{
			String uri=FrameworkServices.getConfigProperties().getProperty("ApplicationURI")+ServiceEndpoint.Registration_GETAPPREQ_NEW_REG_SUBMIT.getUrl();
			//GetAuthToken authToken= new GetAuthToken();
			Response res = given().
					header("clientid",headers.get("clientid")).
					header("client-secret", headers.get("client-secret")).
					header("state-cd", headers.get("state-cd")).
					header("auth-token", headers.get("auth-token")).
					header("username",headers.get("username")).
					parameters("action",parameters.get("action"),
							"state_cd",parameters.get("state_cd"),
							"arn",arn.get("arn")).
					when().
					contentType(ContentType.JSON).get(uri);
			Reporter.log(res.asString());
			/*
			 * Status code assertion 
			 * 
			 */
			String actualStatus_cd = JsonPath.read(res.asString(),"status_cd").toString();
			//boolean assertstatuscode=SchemaValidator.isJsonValid("1", Actualstatus_cd);
			Assert.assertEquals(actualStatus_cd, "1");
			/*
			 * Decrypt Response
			 * 
			 */
			String ActualData = JsonPath.read(res.asString(),"data").toString();
			GstUtil gstUtil = new GstUtil();
			byte[] byteDecodedData = gstUtil.decodeBase64StringTOByte(ActualData);
			String strDecodedData = new String(byteDecodedData);
			System.out.println(strDecodedData);
			/*
			 * Schema Validation
			 * 
			 */
			Reporter.log(strDecodedData);
			String expectedSchema=SchemaValidator.getJsonExpected(FrameworkServices.getConfigProperties().getProperty("SchemaLocation")+"\\"+"Get Application Requests - New Reg Submit response_schema.json");
			boolean assertT=SchemaValidator.isJsonValid(expectedSchema, strDecodedData);
			Assert.assertEquals(assertT, true,"Failed due to schema is not matching");
		}
	}
}

