package com.shxt.sys.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable {
	/**系统角色表的主键标识*/
	private Integer role_id;
	/**角色名称，唯一标识*/
	private String role_name;
	/**角色的简单描述，备用*/
	private String role_desc;
	/**角色的状态: 1为可用状态  2为禁用状态*/
	private String role_status = "1";
	/**角色的头像: 默认情况下提供三个头像  role1.png role2.png role3.png*/
	private String role_photo = "role1.png";
	/**用户集合 设置关系多对多*/
	private Set<User> userSet ;
	/**菜单集合 设置关系多对多*/
	private Set<Menu> menuSet;
	
	public Role() {
		userSet = new HashSet<User>();
		menuSet = new HashSet<Menu>();
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
	}

	public String getRole_desc() {
		return role_desc;
	}

	public void setRole_desc(String roleDesc) {
		role_desc = roleDesc;
	}

	public String getRole_status() {
		return role_status;
	}

	public void setRole_status(String roleStatus) {
		role_status = roleStatus;
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	public Set<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	public String getRole_photo() {
		return role_photo;
	}

	public void setRole_photo(String rolePhoto) {
		role_photo = rolePhoto;
	}
	
	

	
	
}
