package com.shxt.sys.model;

import java.io.Serializable;

public class MemberType implements Serializable {
	/**主键id*/
	private Integer type_id;
	/**会员名称*/
	private String type_name;
	/**折扣*/
	private String discount;
	/**状态  为1时可用 为2时不可用*/
	private String type_status = "1";
	/**会员类型图片*/
	private String photo ="type01.png";
	
	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer typeId) {
		type_id = typeId;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String typeName) {
		type_name = typeName;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getType_status() {
		return type_status;
	}

	public void setType_status(String typeStatus) {
		type_status = typeStatus;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
