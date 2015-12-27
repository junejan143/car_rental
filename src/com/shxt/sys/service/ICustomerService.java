package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.Customer;
import com.shxt.sys.model.CustomerQuery;
import com.shxt.sys.model.MemberType;

public interface ICustomerService {
	/**
	 * 
	 * @描述: 带查询条件的分页查询
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:32:09
	 * @参数:@param pageBean
	 * @参数:@param query
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean find(PageBean pageBean ,CustomerQuery query);
	/**
	 * 
	 * @描述: 查询会员类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:32:37
	 * @参数:@return 
	 * @返回值：List<MemberType>
	 */
	public List<MemberType> memberTypeList();
	/**
	 * 
	 * @描述: 验证身份证号是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:33:11
	 * @参数:@param cusIdCard
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkCard(String cusIdCard);
	/**
	 * 
	 * @描述: 添加客户信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:33:55
	 * @参数:@param customer 
	 * @返回值：void
	 */
	public void add(Customer customer);
	/**
	 * 
	 * @描述: 获取会员类型
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:34:13
	 * @参数:@param memberType
	 * @参数:@return 
	 * @返回值：MemberType
	 */
	public MemberType memberType(String memberType);
	/**
	 * 
	 * @描述: 通过id查找顾客信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:34:40
	 * @参数:@param cusId
	 * @参数:@return 
	 * @返回值：Customer
	 */
	public Customer getCustomerById(Integer cusId);
	/**
	 * 
	 * @描述: 修改客户信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:35:04
	 * @参数:@param customer 
	 * @返回值：void
	 */
	public void update(Customer customer);
	/**
	 * 
	 * @描述: 通过id修改客户状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:35:22
	 * @参数:@param cus_id 
	 * @返回值：void
	 */
	public void updateStatus(Integer cus_id);
	
}
