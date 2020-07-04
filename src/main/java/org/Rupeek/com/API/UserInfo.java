package org.Rupeek.com.API;

/**
 * @author chicharles
 * @Description : class to generate user info /authenticate API Json body
 *
 */
public class UserInfo {

	private String username;
	private String password;

	public String getusername() {
		return username;
	}

	public void setusername(String userName) {
		this.username = userName;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

}
