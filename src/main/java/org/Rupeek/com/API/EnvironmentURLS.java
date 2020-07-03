package org.Rupeek.com.API;

import java.util.ResourceBundle;

/**
 * @author chicharles
 *
 */
public class EnvironmentURLS {
	static ResourceBundle userCreditinals = ResourceBundle.getBundle("urls");

	public static String getBaseUrl() {
		return userCreditinals.getString("baseUrl");
	}

	public static String getAuthenticateUrl() {
		return userCreditinals.getString("authenticate");
	}
	
	public static String getUserInfoUrl() {
		return userCreditinals.getString("userInfo");
	}

}
