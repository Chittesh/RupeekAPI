package org.Rupeek.com.API;

import java.util.Base64;
import java.util.ResourceBundle;

/**
 * @author chicharles
 *
 */
public class UserInfoBody {

	/**
	 * @Desciprion : method to get Json Object reference
	 * @return
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

	public static UserInfo getInvalidPasswordInfoBody() {
		ResourceBundle userCreditinals = ResourceBundle.getBundle("config");
		String userName = userCreditinals.getString("UserName");
		String password = userCreditinals.getString("InvalidPassword");
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password);
		return obj;
	}

	public static UserInfo getInvalidUserNameAndPasswordInfoBody() {
		ResourceBundle userCreditinals = ResourceBundle.getBundle("config");
		String userName = userCreditinals.getString("InvaildUserName");
		String password = userCreditinals.getString("InvalidPassword");
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password);
		return obj;
	}
	
	public static UserInfo getemptyUserNameAndPasswordInfoBody() {
		UserInfo obj = new UserInfo();
		obj.setusername("");
		obj.setpassword("");
		return obj;
	}

}
