package com.shxt.sys.model;

import java.io.Serializable;

public class CarInfoQuery implements Serializable {
	/**汽车名称*/
	private String car_name;
	/**汽车类型id*/
	private String type_id;
	/**汽车状态*/
	private String car_status;
	/**汽车车牌号*/
	private String car_code;
	/**预定人姓名*/
	private String reserve_user_name;
	
	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String carName) {
		car_name = carName;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public String getCar_status() {
		return car_status;
	}

	public void setCar_status(String carStatus) {
		car_status = carStatus;
	}

	public String getCar_code() {
		return car_code;
	}

	public void setCar_code(String carCode) {
		car_code = carCode;
	}

	public String getReserve_user_name() {
		return reserve_user_name;
	}

	public void setReserve_user_name(String reserveUserName) {
		reserve_user_name = reserveUserName;
	}

	
}
