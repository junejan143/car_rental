package com.shxt.sys.service;

import java.util.List;
import com.shxt.base.service.BaseService;
import com.shxt.sys.model.Menu;
import com.shxt.sys.model.Role;


public class RoleServiceImpl extends BaseService implements IRoleService {

	private static final boolean String = false;

	private String[] menu_id;
	
	public List<Role> getRoleAvailableList() {
		String hql = "from Role r where r.role_status=? order by r.role_id desc";
		return (List<Role>) this.baseDao.list(hql, "1");
	}

	public Role getRoleById(Integer roleId) {
		return (Role) this.baseDao.load(Role.class, roleId);
	}

	public List<Role> getAllList() {
		String hql = "from Role r order by r.role_id desc";
		return (List<Role>) this.baseDao.list(hql);
	}

	public void update(Role role) {
		Role old_role = (Role) this.baseDao.load(Role.class, role.getRole_id());
		old_role.setRole_photo(role.getRole_photo());
		old_role.setRole_name(role.getRole_name());
		old_role.setRole_desc(role.getRole_desc());
		
		this.baseDao.update(old_role);
		
	}

	public Long checkName(String name) {
		String hql = "select count(*) from Role r where r.role_name=?";
		return (Long) this.baseDao.query(hql, name.trim());
	}

	public void updateAuthorize(Integer roleId, String[] selectedMenuIds) {
		//获取该角色信息--因为我们是即时加载，那么获取角色的同时获取了改角色下的菜单信息
		Role role = (Role) this.baseDao.load(Role.class, roleId);
		
		//清空原有的菜单信息
		role.getMenuSet().clear();
		
		if(selectedMenuIds!=null&&selectedMenuIds.length>0){
			for(String menu_id : selectedMenuIds){
				Menu menu = (Menu) this.baseDao.load(Menu.class, Integer.parseInt(menu_id));
				role.getMenuSet().add(menu);
			}
		}
		
		this.baseDao.update(role);
		
	}

	public void delete(Integer roleId) {
		
		String hql="update User u set u.role=null where u.role.role_id=?";
		this.baseDao.updateByHql(hql, roleId);
		this.baseDao.delete(Role.class, roleId);
		
	}

	public void add(Role role) {
		this.baseDao.save(role);		
	}

	public void updateStatus(java.lang.String status, Integer roleId) {
		
		String hql="update Role r set r.role_status=? where r.role_id=?";
		this.baseDao.updateByHql(hql, new Object[]{status,roleId});
		
	}

}
