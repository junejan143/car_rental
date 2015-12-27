package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.Role;
import com.shxt.sys.model.User;
import com.shxt.sys.model.UserQuery;


public interface IUserService {
	/**
	 * 
	 * @描述: 用户登陆操作
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:00:55
	 * @参数:@param user
	 * @参数:@return 
	 * @返回值：User
	 */
	public User login(User user);
	/**
	 * 
	 * @描述: 用户添加操作
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:01:23
	 * @参数:@param user 
	 * @返回值：void
	 */
	public void add(User user);
	/**
	 * 
	 * @描述: 带查找条件的分页查询
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:01:45
	 * @参数:@param pageBean
	 * @参数:@param query
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean find(PageBean pageBean , UserQuery query);
	/**
	 * 
	 * @描述: 验证账号是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:02:27
	 * @参数:@param account
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long getCountByAccount(String account);
	/**
	 * 
	 * @描述: 通过id查找用户信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:03:02
	 * @参数:@param userId
	 * @参数:@return 
	 * @返回值：User
	 */
	public User getUserById(Integer userId);
	/**
	 * 
	 * @描述: 修改用户信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:03:28
	 * @参数:@param user 
	 * @返回值：void
	 */
	public void update(User user);
	/**
	 * 
	 * @描述: 通过id修改用户状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:03:42
	 * @参数:@param user_id 
	 * @返回值：void
	 */
	public void updateStatus(Integer user_id);
	/**
	 * 
	 * @描述: 查询角色链表
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:04:02
	 * @参数:@return 
	 * @返回值：List<Role>
	 */
	public List<Role> roleList();
	/**
	 * 
	 * @描述: 通过id查询角色信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:04:20
	 * @参数:@param roleId
	 * @参数:@return 
	 * @返回值：Role
	 */
	public Role role(String roleId);
	
}
