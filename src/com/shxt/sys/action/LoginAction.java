package com.shxt.sys.action;

import com.shxt.action.BaseAction;
import com.shxt.sys.model.User;
import com.shxt.sys.service.IUserService;
import com.shxt.sys.service.UserServiceImpl;


public class LoginAction extends BaseAction {
	//定义user类
	private User user;
	//定义userService接口
	private IUserService userService;
	//用于接口注入
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	/**
	 * 
	 * @描述: 用户登陆操作
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午02:52:13
	 * @参数:@return 
	 * @返回值：String
	 */
	public String login(){

		//判断user是否存在  
		if(user==null){
			//不存在user 提示重新登陆
			this.message="数据被带到了外星球，请重新输入";
			return LOGIN;
		}else{
			//判断用户名或密码是否为空
			if (user.getAccount().trim().length()==0||user.getPassword().trim().length()==0) {
				//有为空的提示重新登陆
				this.message="您输入的用户名或密码为空，请重新输入";
				return LOGIN;
			}else{
				//查询user类
				user = userService.login(user);
				//判断user是否为空  
				if(user==null){
					//user为空提示用户名或密码错误
					this.message="用户名或密码错误，请重新输入";
					return LOGIN;
				}else{
					//判断用户的状态是否可用
					if("2".equals(user.getAccount_status())){
						//提示用户被禁用联系管理员
						this.message="该用户已经被禁用，请联系管理员";
						return LOGIN;
					}else{
						//把user存入session
						this.session.put("session_user", user);
						//登陆成功跳转到下一个action
						this.toAction="getNavLeftMenuAction.action";
						//重定向到action
						return REDIRECTACTION;
//						this.toJsp="jsp/main/main";
//						return DISPATCHER;
					}
				}
			}
		}
	}

	/**
	 * 
	 * @描述: 用户注销  退出操作
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午02:59:07
	 * @参数:@return 
	 * @返回值：String
	 */
	public String loginOut(){
		//需要把所有的Session注销
		this.session.clear();//清空Struts2 
		//移除 session_user_id的session
		this.request.getSession().removeAttribute("session_user_id");
		this.request.getSession().invalidate();
		
		return LOGIN;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
