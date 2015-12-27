package com.shxt.sys.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**系统用户主键标识*/
	private Integer user_id;
	/**账号信息*/
	private String account;
	/**密码：可以增加MD5加密*/
	private String password;
	/**姓名*/
	private String user_name;
	/**性别有三种: "男" "女" "未知"*/
	private String sex;
	/**电子邮件*/
	private String email;
	/**联系方式*/
	private String telphone;
	/**身份证号码*/
	private String id_card;
	/**设置右侧显示的页面*/
	private String home_page = "content.jsp";
	/**用户状态：1为可用 2为禁用 3为删除*/
	private String account_status = "1";
	/**禁用的截至日期*/
	private Date stop_date ;
	/**用户创建的日期*/
	private Date create_date = new Date();
	/**用户是谁创建的*/
	private String create_name ;
	/**是否允许删除的标识，1为都运行，2为不允许进行删除*/
	private String del_flag = "1";
	/**用户和角色是多对一关系*/
	private Role role;
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer userId) {
		user_id = userId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getId_card() {
		return id_card;
	}
	public void setId_card(String idCard) {
		id_card = idCard;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String homePage) {
		home_page = homePage;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String accountStatus) {
		account_status = accountStatus;
	}
	public Date getStop_date() {
		return stop_date;
	}
	public void setStop_date(Date stopDate) {
		stop_date = stopDate;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date createDate) {
		create_date = createDate;
	}
	public String getCreate_name() {
		return create_name;
	}
	public void setCreate_name(String createName) {
		create_name = createName;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String delFlag) {
		del_flag = delFlag;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
