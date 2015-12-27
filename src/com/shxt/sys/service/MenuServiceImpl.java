package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.service.BaseService;
import com.shxt.sys.dto.MenuDTO;
import com.shxt.sys.model.Menu;

public class MenuServiceImpl extends BaseService implements IMenuService {

	public List<Menu> getParentList() {
		String hql="from Menu m where m.parent_id is null";
		return (List<Menu>) this.baseDao.list(hql);
	}

	public void addParent(Menu menu) {
		
		this.baseDao.save(menu);
		
	}
	
	public List<MenuDTO> getParentListSQL(){
		String sql = "select * from web_sys_menu m where m.parent_id is null order by m.menu_id desc";
		return this.getNodeList(sql);
	}
	
	private List<MenuDTO> getNodeList(String sql){
		List<MenuDTO> menuDTOList = (List<MenuDTO>) this.baseDao.listSQL(sql, MenuDTO.class, false);
		
		if(menuDTOList!=null&&menuDTOList.size()>0){
			
			for (MenuDTO menuDTO : menuDTOList) {
				String child_sql = "select * from web_sys_menu m where m.parent_id="+menuDTO.getMenu_id()+" order by m.menu_id desc";
				
				menuDTO.setChildList(this.getNodeList(child_sql));
			}
		}
		return menuDTOList;
	}

	public Long checkName(String name) {
		String hql = "select count(*) from Menu m where m.menu_name=?";
		return (Long) this.baseDao.query(hql, name.trim());
	}

	public List<Menu> getSelectedMenuById(Integer roleId) {
		String sql = "select * from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getUnSelectedMenuById(Integer roleId) {
		String sql ="select mm.* from web_sys_menu mm where mm.parent_id is not null and mm.menu_id not in (select m.menu_id from web_sys_menu m,role_link_menu rlm where rlm.fk_menu_id=m.menu_id and rlm.fk_role_id=?)";
		return (List<Menu>) this.baseDao.listSQL(sql, roleId, Menu.class, true);
	}

	public List<Menu> getChildLeftMenuById(Integer userId) {
		String sql = "SELECT m.* FROM web_sys_user u,role_link_menu rlm,web_sys_menu m WHERE u.fk_role_id = rlm.fk_role_id AND rlm.fk_menu_id = m.menu_id AND u.user_id = ? AND m.postion = 'LEFT'"; 
		return (List<Menu>) this.baseDao.listSQL(sql, userId, Menu.class, true);
	}

	public List<Menu> getParentLeftMenuById(Integer userId) {
		String sql = "select mm.* from web_sys_menu mm where mm.menu_id in (SELECT DISTINCT m.parent_id FROM web_sys_user u,role_link_menu rlm,web_sys_menu m WHERE u.fk_role_id = rlm.fk_role_id AND rlm.fk_menu_id = m.menu_id AND u.user_id = ? AND m.postion = 'LEFT')"; 
		return (List<Menu>) this.baseDao.listSQL(sql, userId, Menu.class, true);
	}

	public void deleteChild(Integer menuId) {
		
		this.baseDao.delete(Menu.class, menuId);
		
	}

	public void deleteParent(Integer menuId) {
		
		this.baseDao.delete(Menu.class, menuId);
		
	}

	public Long checkChild(Integer menuId) {
		String hql = "select count(*) from Menu m where m.parent_id=?";
		return (Long) this.baseDao.query(hql, String.valueOf(menuId));
	}

	public void addChild(Menu menu) {
		
		this.baseDao.save(menu);
		
	}

	public Menu menu(Integer menuId) {
	
		return (Menu) this.baseDao.load(Menu.class, menuId);
	}

	public void updateParent(Menu menu) {
		
		Menu oldMenu = (Menu) this.baseDao.load(Menu.class, menu.getMenu_id());
		
		oldMenu.setMenu_name(menu.getMenu_name());
		
		oldMenu.setPostion(menu.getPostion());
		
		this.baseDao.update(oldMenu);
	}

	public void updateChild(Menu menu,String menu_id) {
		
		Menu oldMenu = (Menu) this.baseDao.load(Menu.class, menu.getMenu_id());
		
		oldMenu.setMenu_name(menu.getMenu_name());
		
		oldMenu.setPostion(menu.getPostion());
		
		oldMenu.setTarget(menu.getTarget());
		
		oldMenu.setUrl(menu.getUrl());
		
		oldMenu.setParent_id(menu_id);
		
		this.baseDao.update(oldMenu);
		
	}


	
}
