package com.shxt.sys.service;

import java.util.List;

import com.shxt.sys.model.MemberType;


public interface IMemberTypeService {
	/**
	 * 
	 * @描述: 查询会员类型
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:35:52
	 * @参数:@return 
	 * @返回值：List<MemberType>
	 */
	public List<MemberType> getList();
	/***
	 * 
	 * @描述: 添加会员类型
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:36:15
	 * @参数:@param memberType 
	 * @返回值：void
	 */
	public void add(MemberType memberType);
	/**
	 * 
	 * @描述: 通过id获取会员类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:36:38
	 * @参数:@param typeId
	 * @参数:@return 
	 * @返回值：MemberType
	 */
	public MemberType getMemberTypeById(Integer typeId);
	/**
	 * 
	 * @描述: 修改会员类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:37:01
	 * @参数:@param memberType 
	 * @返回值：void
	 */
	public void update(MemberType memberType);
	/**
	 * 
	 * @描述: 验证会员名称是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:37:24
	 * @参数:@param name
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkName(String name);
	/**
	 * 
	 * @描述: 通过id修改会员状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:38:01
	 * @参数:@param typeId
	 * @参数:@param status 
	 * @返回值：void
	 */
	public void updateStatus(Integer typeId,String status);
	/**
	 * 
	 * @描述: 删除会员类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:38:21
	 * @参数:@param typeId 
	 * @返回值：void
	 */
	public void delete(Integer typeId); 
}
