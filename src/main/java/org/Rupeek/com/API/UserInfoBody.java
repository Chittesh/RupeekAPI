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
		String userName = userCreditinals.getString("userName");
		byte[] decodedBytes = Base64.getDecoder().decode(userCreditinals.getString("password"));
		String password1 = new String(decodedBytes);
		UserInfo obj = new UserInfo();
		obj.setusername(userName);
		obj.setpassword(password1);
		return obj;
	}

}
