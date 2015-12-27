package com.shxt.sys.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MenuDTO implements Serializable{
	/**系统菜单信息表的主键标识*/
	private Integer menu_id;
	/**菜单名称*/
	private String menu_name;
	/**菜单的父节点ID*/
	private String parent_id;
	/**菜单的访问路径*/
	private String url;
	/**菜单的图片，根据情况设置*/
	private String icon;
	/**菜单的显示位置 默认值有  LEFT和TOP*/
	private String postion = "LEFT";
	/**对应超级连接的target属性的值*/
	private String target = "rightFrame";
	/**菜单的子节点链表*/
	private List<MenuDTO> childList;
	
	public MenuDTO(){
		childList = new ArrayList<MenuDTO>();
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menuId) {
		menu_id = menuId;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menuName) {
		menu_name = menuName;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parentId) {
		parent_id = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<MenuDTO> getChildList() {
		return childList;
	}

	public void setChildList(List<MenuDTO> childList) {
		this.childList = childList;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}
}
