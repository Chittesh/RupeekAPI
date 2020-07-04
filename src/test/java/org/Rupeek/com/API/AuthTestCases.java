package org.Rupeek.com.API;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AuthTestCases extends BasePage {

	@Test()
	public void verifyInvalidAuthentication() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		checkForUnauthorizedError(UserInfoBody.getInvalidUserNameInfoBody(), StaticData.status_401);
		checkForUnauthorizedError(UserInfoBody.getInvalidPasswordInfoBody(), StaticData.status_401);
		checkForUnauthorizedError(UserInfoBody.getInvalidUserNameAndPasswordInfoBody(), StaticData.status_401);

		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson).body("").log().all()
				.when().post(EnvironmentURLS.getAuthenticateUrl()).then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_400, "Verify Status : " + response);
		Assert.assertTrue(response.asString().contains("error"), "Verify response contains error");
		JsonPath jsonPath = new JsonPath(response.asString());
		String error = jsonPath.get("error");
		Assert.assertEquals(error, StaticData.badRequestError, "Verify Unauthorized Error is present on Response");
		Assert.assertEquals(response.contentType(), StaticData.contentTypeJson, "Verify content Type");

	}
	
	@Test()
	public void verifyValidAuthentication() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given().header(StaticData.contentHeader, StaticData.contentTypeJson)
				.body(UserInfoBody.getUserInfoBody()).log().all().when().post(EnvironmentURLS.getAuthenticateUrl())
				.then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_200,
				"Verify Status : " + response.getStatusCode());
		Assert.assertTrue(response.asString().contains("token"), "Verify response contains token");
		Assert.assertEquals(response.contentType(), StaticData.contentTypeJson, "Verify content Type");
	}

}
