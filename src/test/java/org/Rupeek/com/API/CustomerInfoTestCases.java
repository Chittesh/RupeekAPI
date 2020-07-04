package org.Rupeek.com.API;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CustomerInfoTestCases extends BasePage {

	@BeforeTest(dependsOnMethods = "verifyAuthenticationAndGetAuthToken")
	public void GetCustomerDeatils() {
		getAllCustomerDeatils();
	}

	@Test()
	public void performGetRequestToFetchCustomerDeatilsWithInvalidToken() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given()
				.header(StaticData.authorizationHeader,
						StaticData.authorizationTypeBearer + StaticData.invalidAuthorizationTypeBearer)
				.header(StaticData.contentHeader, StaticData.contentTypeJson).when()
				.get(EnvironmentURLS.getUserInfoUrl()).then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_401,
				"Verify Status : " + response.getStatusCode());
	}
	
	@Test()
	public void performGetRequestToFetchCustomerDeatilsWithEmptyToken() {
		RestAssured.baseURI = EnvironmentURLS.getBaseUrl();
		Response response = given()
				.header(StaticData.authorizationHeader,
						StaticData.authorizationTypeBearer + "")
				.header(StaticData.contentHeader, StaticData.contentTypeJson).when()
				.get(EnvironmentURLS.getUserInfoUrl()).then().extract().response();
		Assert.assertEquals(response.getStatusCode(), StaticData.status_401,
				"Verify Status : " + response.getStatusCode());
		Assert.assertTrue(response.asString().contains("error"), "Verify response contains error");
		JsonPath jsonPath = new JsonPath(response.asString());
		String error = jsonPath.get("error");
		Assert.assertEquals(error, StaticData.unAuthorizedError, "Verify Unauthorized Error is present on Response");
	}


	@Test()
	public void verifyFirstNameLastNameAndCareerContainsOnlyAlphabets() {
		checkForOnlyAlphabets(firstNameOfCustomers);
		checkForOnlyAlphabets(lastNameOfCustomers);
		checkForOnlyAlphabets(careerOfCustomers);
	}

	@Test()
	public void verifyPhoneNumberConatinsOnlyDigits() {
		HashMap<String, Boolean> hm = new HashMap<String, Boolean>();
		for (int i = 0; i < phoneNumberOfCustomers.size(); i++) {
			hm.put(phoneNumberOfCustomers.get(i), isDigit(phoneNumberOfCustomers.get(i)));
		}
		for (Entry<String, Boolean> entry : hm.entrySet()) {
			Assert.assertTrue(entry.getValue(), " Verify value " + entry.getKey() + " contains only digits");
		}
	}

	@Test(dependsOnMethods = { "checkFirstNameLastNameAndCareerContainsOnlyAlphabets",
			"checkPhoneNumberConatinsOnlyDigits" })
	public void verifyValidCustomerDetails() {
		String actualCustomerFirstName = "Aliko";
		String actualCustomerLastName = "Dangote";
		String actualCustomerName = actualCustomerFirstName + " " + actualCustomerLastName;
		String actualCustomerCareer = "Billionaire Industrialist";
		String actualCustomerPhone = "8037602400";
		Assert.assertTrue(fullNameOfCustomers.contains(actualCustomerName),
				" Verify " + actualCustomerName + " presence on the database");
		Assert.assertTrue(firstNameOfCustomers.contains(actualCustomerFirstName),
				" Verify " + actualCustomerFirstName + " presence on the database");
		Assert.assertTrue(lastNameOfCustomers.contains(actualCustomerLastName),
				" Verify " + actualCustomerLastName + " presence on the database");
		Assert.assertTrue(careerOfCustomers.contains(actualCustomerCareer),
				" Verify " + actualCustomerCareer + " presence on the database");
		Assert.assertTrue(phoneNumberOfCustomers.contains(actualCustomerPhone),
				" Verify " + actualCustomerPhone + " presence on the database");
	}

	@Test()
	public void checkInValidCustomerDetails() {
		String actualCustomerFirstName = "Chittesh";
		String actualCustomerLastName = "Charles";
		String actualCustomerName = actualCustomerFirstName + " " + actualCustomerLastName;
		String actualCustomerCareer = "SDET";
		String actualCustomerPhone = "9911223344";
		Assert.assertFalse(fullNameOfCustomers.contains(actualCustomerName),
				" Verify " + actualCustomerName + " presence on the database");
		Assert.assertFalse(firstNameOfCustomers.contains(actualCustomerFirstName),
				" Verify " + actualCustomerFirstName + " presence on the database");
		Assert.assertFalse(lastNameOfCustomers.contains(actualCustomerLastName),
				" Verify " + actualCustomerLastName + " presence on the database");
		Assert.assertFalse(careerOfCustomers.contains(actualCustomerCareer),
				" Verify " + actualCustomerCareer + " presence on the database");
		Assert.assertFalse(phoneNumberOfCustomers.contains(actualCustomerPhone),
				" Verify " + actualCustomerPhone + " presence on the database");
	}

}
