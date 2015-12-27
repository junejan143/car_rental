package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.base.service.BaseService;
import com.shxt.sys.chart.ChartDatas;
import com.shxt.sys.model.User;

public class AnalysisServiceImpl extends BaseService implements IAnalysisService {

	public List<ChartDatas> roleAnalysis() {
	    String sql ="select IFNULL(r.role_name,'无角色') label,count(u.user_id) value, CONCAT('JavaScript:showData(\"',IFNULL(r.role_id,''),'\")') link from web_sys_role r right join web_sys_user u on r.role_id=u.fk_role_id GROUP BY r.role_name";
	    return (List<ChartDatas>) this.baseDao.listSQL(sql, ChartDatas.class, false);
    }

	public PageBean showRole(Integer roleId ,PageBean pageBean){
		String hql;
		if(roleId==null){
			hql = "from User u where u.role is null";
		}else{
			hql = "from User u where u.role.role_id ="+roleId;
		}
		
		return  this.baseDao.find(hql, pageBean);
	}
	
}
