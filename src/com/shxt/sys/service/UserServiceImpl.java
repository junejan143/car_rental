package com.shxt.sys.service;

import java.util.List;

import sun.net.www.content.text.plain;

import com.shxt.base.dao.PageBean;
import com.shxt.base.service.BaseService;
import com.shxt.base.utils.MD5Helper;
import com.shxt.sys.model.Role;
import com.shxt.sys.model.User;
import com.shxt.sys.model.UserQuery;


public class UserServiceImpl extends BaseService implements IUserService {

	public User login(User user) {
		String hql = "from User u where u.account=? and u.password=?";
		return (User) this.baseDao.query(hql, new Object[]{user.getAccount().trim(),MD5Helper.MD5(user.getPassword().trim())});
	}
	public PageBean find(PageBean pageBean , UserQuery query) {
		String hql = "from User u where 1=1";
		if (query!=null) {
			if (query.getUser_name()!=null&&query.getUser_name().trim().length()>0) {
				hql +=" and u.user_name like '"+query.getUser_name().trim()+"%'";
			}
			if (query.getSex()!=null&&query.getSex().length()>0) {
				hql +=" and u.sex='"+query.getSex()+"'";
			}
			
		}
		hql +=" order by u.user_id desc";
		return this.baseDao.find(hql, pageBean);
	}
	public void add(User user) {
		this.baseDao.save(user);
		
	}
	public Long getCountByAccount(String account) {
		String hql = "select count(*) from User u where u.account=?";
		return (Long) this.baseDao.query(hql, account.trim());
	}
	public User getUserById(Integer userId) {
		return (User) this.baseDao.load(User.class, userId);
	}
	public void update(User user) {
		
		// 通过该user获取数据中的oldUser
		User oldUser = (User)this.baseDao.load(User.class, user.getUser_id());
		
		oldUser.setUser_name(user.getUser_name());
		
		if(user.getRole()!=null){
			oldUser.setRole(user.getRole());
		}else{
			oldUser.setRole(null);
		}
		
		this.baseDao.update(oldUser);
		
	}
	public void updateStatus(Integer userId) {
		User user = (User)this.baseDao.load(User.class, userId);
		if(user.getAccount_status().equals("1")){
			user.setAccount_status("2");
		}else{
			user.setAccount_status("1");
		}
		
		this.baseDao.update(user);
		
	}
	public List<Role> roleList() {
		
		String hql = "from Role r ";
		
		return (List<Role>) this.baseDao.list(hql);
	
	}
	public Role role(String roleId) {
		
		return (Role) this.baseDao.load(Role.class, Integer.parseInt(roleId));
		
	}

}
