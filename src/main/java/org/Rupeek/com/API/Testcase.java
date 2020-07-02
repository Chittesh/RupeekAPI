package org.Rupeek.com.API;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Testcase {
	static String access_token;

	@BeforeTest()
	public void verifyAuthenticationAndGetAuthToken() {
		RestAssured.baseURI = UserInfoBody.getBaseUrl();
		Response response = given().header("Content-Type", "application/json").body(UserInfoBody.getUserInfoBody())
				.log().all().when().post("/authenticate").then().extract().response();
		Assert.assertEquals(response.getStatusCode(), 200, "Verify Status : " + response.getStatusCode());
		JsonPath jsonPath = new JsonPath(response.asString());
		access_token = jsonPath.get("token");
	}

	@Test()
	public void getUserDetails() {
		RestAssured.baseURI = UserInfoBody.getBaseUrl();
		Response response = given().header("Authorization", "Bearer " + access_token)
				.header("Content-Type", "application/json").when()
				.get("/api/v1/users").then().extract().response();
		System.out.println(response.asString());
	}

}
