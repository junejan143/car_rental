package com.shxt.sys.service;

import java.util.List;
import com.shxt.sys.model.Role;


public interface IRoleService {
	/**
	 * 
	 * @描述: 获取状态为“1”的角色
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:41:15
	 * @参数:@return 
	 * @返回值：List<Role>
	 */
	public List<Role> getRoleAvailableList();
	/**
	 * 
	 * @描述: 通过id获取角色信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:57:58
	 * @参数:@param role_id
	 * @参数:@return 
	 * @返回值：Role
	 */
	public Role getRoleById(Integer role_id);
	/**
	 * 
	 * @描述: 获取所有的角色信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:58:23
	 * @参数:@return 
	 * @返回值：List<Role>
	 */
	public List<Role> getAllList();
	/**
	 * 
	 * @描述: 修改角色信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:58:41
	 * @参数:@param role 
	 * @返回值：void
	 */
	public void update(Role role);
	/**
	 * 
	 * @描述: 验证角色名是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:59:00
	 * @参数:@param name
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkName(String name);
	/**
	 * 
	 * @描述: 角色授权功能
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:59:34
	 * @参数:@param role_id
	 * @参数:@param selectedMenuIds 
	 * @返回值：void
	 */
	public void updateAuthorize(Integer role_id, String[] selectedMenuIds);
	/**
	 * 
	 * @描述: 通过id删除角色
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:59:56
	 * @参数:@param roleId 
	 * @返回值：void
	 */
	public void delete(Integer roleId);
	/**
	 * 
	 * @描述: 角色添加操作
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:00:16
	 * @参数:@param role 
	 * @返回值：void
	 */
	public void add(Role role);
	/**
	 * 
	 * @描述: 通过id修改角色状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午07:00:28
	 * @参数:@param status
	 * @参数:@param roleId 
	 * @返回值：void
	 */
	public void updateStatus(String status,Integer roleId);
	
}
