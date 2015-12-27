package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.sys.chart.ChartDatas;
import com.shxt.sys.model.User;

public interface IAnalysisService {
	/**
	 * 
	 * @描述: 查询角色和用户的关系，做统计信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:22:22
	 * @参数:@return 
	 * @返回值：List<ChartDatas>
	 */
	public List<ChartDatas> roleAnalysis();
	/**
	 * 
	 * @描述: 按角色信息分类查询用户信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:23:03
	 * @参数:@param roleId
	 * @参数:@param pageBean
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean showRole(Integer roleId ,PageBean pageBean);
}
