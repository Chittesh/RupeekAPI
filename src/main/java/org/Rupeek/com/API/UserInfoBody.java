package org.Rupeek.com.API;

import java.util.Base64;
import java.util.ResourceBundle;

/**
 * @author chicharles
 * @Description : class to get user credentials
 *
 */
public class UserInfoBody {

	/**
	 * @Description : Method to create valid Json Body with valid credentials
	 * @param : NA
	 * @return : UserInfo
	 * @Date : 7/4/2020
	 */
	public static UserInfo getUserInfoBody() {
		ResourceBundle userCreditinals = ResourceBundle.getBundle("config");
		String userName = userCreditinals.getString("UserName");
		byte[] decodedBytes = Base64.getDecoder().decode(userCreditinals.getString("Password"));
		String password = new String(decodedBytes);
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password);
		return obj;
	}

	/**
	 * @Description : Method to create valid Json Body with valid password and
	 *              Invalid user name
	 * @param : NA
	 * @return : UserInfo
	 * @Date : 7/4/2020
	 */
	public static UserInfo getInvalidUserNameInfoBody() {
		ResourceBundle userCreditinals = ResourceBundle.getBundle("config");
		String userName = userCreditinals.getString("InvaildUserName");
		byte[] decodedBytes = Base64.getDecoder().decode(userCreditinals.getString("Password"));
		String password = new String(decodedBytes);
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password);
		return obj;
	}

	/**
	 * @Description : Method to create valid Json Body with invalid password and
	 *              valid user name
	 * @param : NA
	 * @return : UserInfo
	 * @Date : 7/4/2020
	 */
	public static UserInfo getInvalidPasswordInfoBody() {
		ResourceBundle userCreditinals = ResourceBundle.getBundle("config");
		String userName = userCreditinals.getString("UserName");
		String password = userCreditinals.getString("InvalidPassword");
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password);
		return obj;
	}

	/**
	 * @Description : Method to create valid Json Body with invalid password and
	 *              invalid user name
	 * @param : NA
	 * @return : UserInfo
	 * @Date : 7/4/2020
	 */
	public static UserInfo getInvalidUserNameAndPasswordInfoBody() {
		ResourceBundle userCreditinals = ResourceBundle.getBundle("config");
		String userName = userCreditinals.getString("InvaildUserName");
		String password = userCreditinals.getString("InvalidPassword");
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password);
		return obj;
	}

	/**
	 * @Description : Method to create valid Json Body with empty password and empty
	 *              user name
	 * @param : NA
	 * @return : UserInfo
	 * @Date : 7/4/2020
	 */
	public static UserInfo getemptyUserNameAndPasswordInfoBody() {
		UserInfo obj = new UserInfo();
		obj.setusername("");
		obj.setpassword("");
		return obj;
	}

}
