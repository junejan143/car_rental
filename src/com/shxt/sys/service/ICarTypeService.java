package com.shxt.sys.service;

import java.util.List;

import com.shxt.sys.model.CarType;

public interface ICarTypeService {
	/**
	 * 
	 * @描述: 获取汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:28:33
	 * @参数:@return 
	 * @返回值：List<CarType>
	 */
	public List<CarType> getParentList();
	/**
	 * 
	 * @描述: 添加汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:29:41
	 * @参数:@param carType 
	 * @返回值：void
	 */
	public void add(CarType carType);
	/**
	 * 
	 * @描述: 通过id查询汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:30:03
	 * @参数:@param typeId
	 * @参数:@return 
	 * @返回值：CarType
	 */
	public CarType getCarTypeById(Integer typeId);
	/**
	 * 
	 * @描述: 修改汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:30:33
	 * @参数:@param carType 
	 * @返回值：void
	 */
	public void update(CarType carType);
	/**
	 * 
	 * @描述: 验证类型名是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:30:51
	 * @参数:@param name
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkName(String name);
	/**
	 * 
	 * @描述: 通过id更改状态信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:31:14
	 * @参数:@param status
	 * @参数:@param typeId 
	 * @返回值：void
	 */
	public void updateStatus(String status,Integer typeId);
	/**
	 * 
	 * @描述: 通过id删除类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:31:41
	 * @参数:@param typeId 
	 * @返回值：void
	 */
	public void delete(Integer typeId);
}
