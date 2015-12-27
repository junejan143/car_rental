package com.shxt.sys.model;

import java.io.Serializable;

public class CustomerQuery implements Serializable {
	/**顾客姓名*/
	private String cus_name;
	/**顾客性别*/
	private String cus_sex;
	/**担保人姓名*/
	private String gua_name;

	public String getCus_name() {
		return cus_name;
	}

	public void setCus_name(String cusName) {
		cus_name = cusName;
	}

	public String getCus_sex() {
		return cus_sex;
	}

	public void setCus_sex(String cusSex) {
		cus_sex = cusSex;
	}

	public String getGua_name() {
		return gua_name;
	}

	public void setGua_name(String guaName) {
		gua_name = guaName;
	}
	
	
}
