package org.Rupeek.com.API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases extends BasePage {

	@BeforeTest(dependsOnMethods = "verifyAuthenticationAndGetAuthToken")
	public void GetCustomerDeatils() {
		getAllCustomerDeatils();
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

	@Test()
	public void checkFirstNameLastNameAndCareerContainsOnlyAlphabets() {
		checkForOnlyAlphabets(firstNameOfCustomers);
		checkForOnlyAlphabets(lastNameOfCustomers);
		checkForOnlyAlphabets(careerOfCustomers);
	}

	@Test(dependsOnMethods = "checkFirstNameLastNameAndCareerContainsOnlyAlphabets")
	public void checkValidCustomerDetails() {
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
