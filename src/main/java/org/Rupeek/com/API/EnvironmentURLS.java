package org.Rupeek.com.API;

import java.util.ResourceBundle;

/**
 * @author chicharles
 * @Description : This class contains common method to fetch the API urls
 *
 */
public class EnvironmentURLS {
	static ResourceBundle userCreditinals = ResourceBundle.getBundle("urls");

	/**
	 * @Description : Method to get API Base URL
	 * @param : NA
	 * @return : String
	 * @Date : 7/4/2020
	 */
	public static String getBaseUrl() {
		return userCreditinals.getString("baseUrl");
	}

	/**
	 * @Description : Method to get API Auth URL
	 * @param : NA
	 * @return : String
	 * @Date : 7/4/2020
	 */
	public static String getAuthenticateUrl() {
		return userCreditinals.getString("authenticate");
	}

	/**
	 * @Description : Method to get API User Info URL
	 * @param : NA
	 * @return : String
	 * @Date : 7/4/2020
	 */
	public static String getUserInfoUrl() {
		return userCreditinals.getString("userInfo");
	}

}
