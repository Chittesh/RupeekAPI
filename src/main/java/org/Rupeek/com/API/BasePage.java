package org.Rupeek.com.API;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BasePage {
	static String access_token;
	static ArrayList<String> fullNameOfCustomers = new ArrayList<String>();;
	static ArrayList<String> firstNameOfCustomers = new ArrayList<String>();;
	static ArrayList<String> lastNameOfCustomers = new ArrayList<String>();;
	static ArrayList<String> careerOfCustomers = new ArrayList<String>();;
	static ArrayList<String> phoneNumberOfCustomers = new ArrayList<String>();;

	@BeforeTest()
	public void verifyAuthenticationAndGetAuthToken() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson)
				.body(UserInfoBody.getUserInfoBody()).log().all().when().post(EnvironmentURLS.getAuthenticateUrl())
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());
		JsonPath jsonPath = new JsonPath(response.asString());
		access_token = jsonPath.get("token");
	}

	public void getAllCustomerDeatils() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given()
				.header(StaticData.authorizationHeader, StaticData.authorizationTypeBearer + access_token)
				.header(StaticData.contentHeader, StaticData.contentTypeJson).when()
				.get(EnvironmentURLS.getUserInfoUrl()).then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());
		Assert.assertEquals(response.contentType(), StaticData.contentTypeJson, "Verify content Type");
		System.out.println(response.asString());

		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(response.asString());
		} catch (ParseException e) {
			System.out.println(e.getLocalizedMessage());
		}

		JSONArray contactsjsonArray = new JSONArray(obj.toString());
		for (int i = 0; i < contactsjsonArray.length(); i++) {
			JSONObject objects = contactsjsonArray.getJSONObject(i);
			fullNameOfCustomers.add(objects.getString("first_name") + " " + objects.getString("last_name"));
			firstNameOfCustomers.add(objects.getString("first_name"));
			lastNameOfCustomers.add(objects.getString("last_name"));
			careerOfCustomers.add(objects.getString("career"));
			phoneNumberOfCustomers.add(objects.getString("phone"));
		}

	}

	public void checkForOnlyAlphabets(ArrayList<String> listValues) {
		HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
		for (int i = 0; i < listValues.size(); i++) {
			hm.put(listValues.get(i), isAlpha(listValues.get(i)));
		}
		for (Entry<String, Boolean> entry : hm.entrySet()) {
			Assert.assertTrue(entry.getValue(), " Verify value " + entry.getKey() + " contains only Aplphabets");
		}
	}

	public void checkForUnauthorizedError(UserInfo userInfo, int StatusCode) {
		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson).body(userInfo).log()
				.all().when().post(EnvironmentURLS.getAuthenticateUrl()).then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StatusCode, "Verify Status : " + response);
		Assert.assertTrue(response.asString().contains("error"), "Verify response contains error");
		JsonPath jsonPath = new JsonPath(response.asString());
		String error = jsonPath.get("error");
		Assert.assertEquals(error, StaticData.unAuthorizedError, "Verify Unauthorized Error is present on Response");
		Assert.assertEquals(response.contentType(), StaticData.contentTypeJson, "Verify content Type");
	}

	public boolean isAlpha(String name) {
		String expression = "[a-zA-Z]+";
		CharSequence inputStr = name.replaceAll(" ", "").trim();
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		boolean status;
		if (matcher.matches()) {
			status = true;
		} else {
			status = false;
		}
		return ((!name.equals("")) && (name != null) && status);
	}

	public boolean isDigit(String name) {
		String expression = "[0-9]+";
		CharSequence inputStr = name.trim();
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		boolean status;
		if (matcher.matches()) {
			status = true;
		} else {
			status = false;
		}
		return ((!name.equals("")) && (name != null) && status);
	}

}
