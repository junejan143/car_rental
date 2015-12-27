package com.shxt.sys.service;

import java.util.List;

import com.shxt.sys.dto.MenuDTO;
import com.shxt.sys.model.Menu;

public interface IMenuService {
	/**
	 * 
	 * @描述: 获取父菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:38:50
	 * @参数:@return 
	 * @返回值：List<Menu>
	 */
	public List<Menu> getParentList();
	/**
	 * 
	 * @描述: 添加父菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:38:59
	 * @参数:@param menu 
	 * @返回值：void
	 */
	public void addParent(Menu menu);
	/**
	 * 
	 * @描述: 添加子菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:39:34
	 * @参数:@param menu 
	 * @返回值：void
	 */
	public void addChild(Menu menu);
	/**
	 * 
	 * @描述: 获取父菜单信息DTO类型
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:39:49
	 * @参数:@return 
	 * @返回值：List<MenuDTO>
	 */
	public List<MenuDTO> getParentListSQL();
	/**
	 * 
	 * @描述: 验证菜单名称是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:40:13
	 * @参数:@param name
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkName(String name);
	/**
	 * 
	 * @描述: 通过角色id获取以被授权的菜单
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:41:02
	 * @参数:@param role_id
	 * @参数:@return 
	 * @返回值：List<Menu>
	 */
	public List<Menu> getSelectedMenuById(Integer role_id);
	/**
	 * 
	 * @描述: 通过角色id获取未被授权的菜单
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:42:14
	 * @参数:@param role_id
	 * @参数:@return 
	 * @返回值：List<Menu>
	 */
	public List<Menu> getUnSelectedMenuById(Integer role_id);
	/**
	 * 
	 * @描述: 通过用户主键获取左侧子菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:42:36
	 * @参数:@param userId
	 * @参数:@return 
	 * @返回值：List<Menu>
	 */
	public List<Menu> getChildLeftMenuById(Integer userId);
	/**
	 * 
	 * @描述: 通过用户主键获取左侧父菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:43:04
	 * @参数:@param userId
	 * @参数:@return 
	 * @返回值：List<Menu>
	 */
	public List<Menu> getParentLeftMenuById(Integer userId);
	/**
	 * 
	 * @描述: 删除子菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:43:33
	 * @参数:@param menuId 
	 * @返回值：void
	 */
	public void deleteChild(Integer menuId);
	/**
	 * 
	 * @描述: 删除父菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:43:53
	 * @参数:@param menuId 
	 * @返回值：void
	 */
	public void deleteParent(Integer menuId);
	/**
	 * 
	 * @描述: 查询父菜单下是否有子菜单
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:44:07
	 * @参数:@param menuId
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkChild(Integer menuId);
	/**
	 * 
	 * @描述: 通过id获取菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:45:14
	 * @参数:@param menuId
	 * @参数:@return 
	 * @返回值：Menu
	 */
	public Menu menu(Integer menuId);
	/**
	 *  
	 * @描述: 修改父菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:45:39
	 * @参数:@param menu 
	 * @返回值：void
	 */
	public void updateParent(Menu menu);
	/**
	 * 
	 * @描述: 修改子菜单信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:45:59
	 * @参数:@param menu
	 * @参数:@param menu_id 
	 * @返回值：void
	 */
	public void updateChild(Menu menu,String menu_id);
	
}
