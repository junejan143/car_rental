package com.shxt.sys.model;

import java.io.Serializable;

public class CarType implements Serializable {
	/**品牌的主键标识*/
	private Integer type_id;
	/**品牌名称*/
	private String type_name;
	/**品牌的父节点ID*/
	private String parent_id;
	/**品牌的图片，根据情况设置*/
	private String icon;
	/**品牌的状态
	 * 1、可用;2、不可用*/
	private String type_status="1";
	
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
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getType_status() {
		return type_status;
	}
	public void setType_status(String typeStatus) {
		type_status = typeStatus;
	}
	
}
