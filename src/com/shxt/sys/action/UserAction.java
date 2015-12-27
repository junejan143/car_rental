package com.shxt.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shxt.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.Role;
import com.shxt.sys.model.User;
import com.shxt.sys.model.UserQuery;
import com.shxt.sys.service.IUserService;
import com.shxt.sys.service.UserServiceImpl;

public class UserAction extends BaseAction {
	//保存查询分页数据
	private PageBean pageBean;
	//保存用户类信息用于模型驱动
	private User  user;
	//用户的账号信息
	private String account;
	//用户的角色信息链表
	private List<Role> roleList;
	//用户的id
	private Integer user_id;
	//用户所拥有的角色的ID
	private String role_id;
	/**定义查询*/
	private UserQuery query;
	//定义userService接口
	private IUserService userService ;
	//注入userService
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * 
	 * @描述: 用户信息的分页
	 * @作者: 徐新伟
	 * @时间:2015-3-10 上午10:56:23
	 * @参数:@return 
	 * @返回值：String
	 */
	public String find(){
		//判断pagebean，无则创建pagebean类
		if(pageBean==null){
			pageBean = new PageBean();
		}
		//查询用户信息保存于pagebean中
		pageBean = userService.find(pageBean, query);
		//跳转到用户list界面
		this.toJsp="jsp/user/list";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到添加新用户界面
	 * @作者: 徐新伟
	 * @时间:2015-3-10 上午11:00:54
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toAdd(){
		//查询数据库中所以的角色类型
		roleList = this.userService.roleList();
		//跳转到用户添加界面
		this.toJsp="jsp/user/add";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 添加新用户
	 * @作者: 徐新伟
	 * @时间:2015-3-10 上午11:02:53
	 * @参数:@return 
	 * @返回值：String
	 */
	public String add(){
		
		try {	
			//判断是否存在role对象
			if(role_id!=null&&role_id.length()>0){
				//查询role对象
				Role role = this.userService.role(role_id);
				//设置role对象
				user.setRole(role);
			}
			//添加用户信息
			this.userService.add(user);
			
			this.flag = "success";
			this.message = "添加用户成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加用户失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	
	}
	/**
	 * 
	 * @描述: 验证账号是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:48:43
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckName(){
		//用于存放提示信息
		Map<String, Object> map = new HashMap<String, Object>();
 		//查询账号信息
		Long count = this.userService.getCountByAccount(account);
		//判断账号是否存在   >0存在    <0不存在
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该账号已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 
	 * @描述: 跳转到修改界面方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:50:46
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//通过主键查询用户信息
		user =this.userService.getUserById(user_id);
		//查询角色信息
		roleList = this.userService.roleList();
		//跳转jsp界面路径
		this.toJsp="jsp/user/update";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 修改用户信息方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:52:40
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){

		try {
			//判断是否有角色
			if(role_id!=null&&role_id.length()>0){
				//查询role对象
				Role role = this.userService.role(role_id);
				//设置角色
				user.setRole(role);
			}
			//修改用户信息
			this.userService.update(user);
			//提示信息
			this.flag = "success";
			this.message = "更新用户成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新用户失败，异常信息为"+e.getMessage();
			
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 更改用户状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:54:06
	 * @参数:@return 
	 * @返回值：String
	 */
	public String changStatus(){
		//用于存放提示信息
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//通过id修改状态
			this.userService.updateStatus(user_id);
			//提示信息
			map.put("flag", "success");
			map.put("message", "更新状态成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "error");
			map.put("message", "更新状态失败,异常信息为:"+e.getMessage());
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}
	
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public UserQuery getQuery() {
		return query;
	}

	public void setQuery(UserQuery query) {
		this.query = query;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}


	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer userId) {
		user_id = userId;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String roleId) {
		role_id = roleId;
	}



	
}
