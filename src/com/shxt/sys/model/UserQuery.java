package com.shxt.sys.model;

import java.io.Serializable;

public class UserQuery implements Serializable {
	/**用户姓名*/
	private String user_name;
	/**用户性别*/
	private String sex;
 
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
