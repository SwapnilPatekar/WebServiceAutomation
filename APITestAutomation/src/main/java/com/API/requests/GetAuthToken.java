package com.API.requests;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.API.Response.GetAuthTokenResponse;
import com.API.ServiceEnum.ServiceEndpoint;
import com.API.Utils.ExcelOperation;
import com.API.Utils.FrameworkServices;
import com.API.Utils.GetHeaders;
import com.API.Utils.SchemaValidator;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import net.minidev.json.JSONObject;
public class GetAuthToken  
{

	/*
	 * Prerequisite for request
	 * 
	 */

	/*J
	 * SON Creation
	 * 
	 */
	public String generateJson(XSSFWorkbook workbook, String ScenarioId) throws Exception  {
	String json="";
		ExcelOperation excelOperation=new  ExcelOperation();
		LinkedHashMap<String, String>jsonMap=excelOperation.getScenarioData(workbook, "DT_RequestGetAuthentication", ScenarioId).get(0);
		GstUtil gstUtil=new GstUtil();
		String encrypt_password = gstUtil.encryptwithPK_PEM(jsonMap.get("password").getBytes("UTF-8"));
		String encrypt_app_key = gstUtil.encryptwithPK_PEM(gstUtil.decodeBase64StringTOByte(jsonMap.get("app_key")));
		jsonMap.replace("password", encrypt_password);
		jsonMap.replace("app_key", encrypt_app_key);
		JSONObject jsonObject=new JSONObject();
		jsonObject.putAll(jsonMap);
		json=jsonObject.toString();
		System.out.println(json);
		return json;
	}
	public void getAuthenticationRequest(XSSFWorkbook workbook,String scenarioID,ExcelOperation excelOperation) throws Exception {
		try {
			HashMap<String, String> headers=GetHeaders.getHeaders(workbook, scenarioID,excelOperation);
			String uri=FrameworkServices.getConfigProperties().getProperty("ApplicationURI")+ServiceEndpoint.AUTHENTICATE.getUrl();
			//generateJson(workbook, scenarioID);
			//HashMap<String, String> parameters=GetParameters.getParameters(workbook, scenarioID)
			String json=generateJson(workbook, scenarioID);

			/*
			 * Request
			 * 
			 */
			Response res = given().
					header("clientid",headers.get("clientid")).
					header("client-secret", headers.get("client-secret")).
					header("state-cd", headers.get("state-cd")).
					body(json).
					when().
					contentType(ContentType.JSON).post(uri);
			Reporter.log("Response is-->"+res.asString());
			System.out.println(res.asString());

			/*
			 * Response is converted to pojo object
			 * 
			 */

			Gson gson=new Gson();
			GetAuthTokenResponse getAuthTokenResponse=gson.fromJson(res.asString(), GetAuthTokenResponse.class);
			String actualResponse=res.asString();
			String actual_authtoken=JsonPath.read(res.asString(),"auth_token").toString();
			excelOperation.setDataInExcelFile(workbook, "DT_Headers", 1, 8, actual_authtoken);
			System.out.println(this.getClass().getResource("/").getPath());

			/*
			 *  Schema Validation
			 * 
			 */

			String expectedSchema=SchemaValidator.getJsonExpected(FrameworkServices.getConfigProperties().getProperty("SchemaLocation")+"\\SchemaValidateAuth.json");
			//ValidatableResponse validationres = res.then().assertThat().body(matchesJsonSchemaInClasspath("SchemaValidateAuth.json"));
			boolean assertValue=SchemaValidator.isJsonValid(expectedSchema, actualResponse);
			Assert.assertEquals(assertValue, true);


			//Assert.assertEquals(actual_authtoken, "1");

		}
		catch(Exception e) {
			throw e;
		}
	}
}
