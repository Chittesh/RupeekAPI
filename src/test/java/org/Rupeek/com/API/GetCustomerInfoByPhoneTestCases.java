package org.Rupeek.com.API;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetCustomerInfoByPhoneTestCases extends BasePage {

	@Test()
	public void verifyCustomerDetailsBasedOnValidPhoneNumber() {
		Object obj = getAllCustomerDeatilsBasedOnPhoneNumber(StaticData.validPhoneNumber);
		JSONObject contactJsonObject = new JSONObject(obj.toString());
		Assert.assertTrue(contactJsonObject.has(StaticData.firstNameFiledName), "Verify first_name filed is present");
		Assert.assertEquals(contactJsonObject.getString(StaticData.firstNameFiledName), StaticData.validFirstName,
				"Verify first_name");
		Assert.assertTrue(contactJsonObject.has(StaticData.lastNameFiledName), "Verify last_name filed is present");
		Assert.assertEquals(contactJsonObject.getString(StaticData.lastNameFiledName), StaticData.validLastName,
				"Verify last_name");
		Assert.assertTrue(contactJsonObject.has(StaticData.careerFiledName), "Verify career filed is present");
		Assert.assertEquals(contactJsonObject.getString(StaticData.careerFiledName), StaticData.validCareer,
				"Verify career");
		Assert.assertTrue(contactJsonObject.has(StaticData.phoneNumberFiledName), "Verify Phone filed is present");
		Assert.assertEquals(contactJsonObject.getString(StaticData.phoneNumberFiledName), StaticData.validPhoneNumber,
				"Verify Phone");
	}

	@Test()
	public void verifyCustomerDetailsBasedOnInValidPhoneNumber() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given()
				.header(StaticData.authorizationHeader, StaticData.authorizationTypeBearer + access_token)
				.header(StaticData.contentHeader, StaticData.contentTypeJson).when()
				.get(EnvironmentURLS.getUserInfoUrl() + "/" + StaticData.inValidPhoneNumber).then().extract()
				.response();
		Assert.assertTrue(response.asString().length()==0, "Verify No response is present");
	}

}
