package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.CarInfo;
import com.shxt.sys.model.CarInfoQuery;
import com.shxt.sys.model.CarType;

public interface ICarInfoService {
	/**
	 * 
	 * @描述: 带查询条件的分页
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:24:15
	 * @参数:@param pageBean
	 * @参数:@param carInfoQuery
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean find(PageBean pageBean ,CarInfoQuery carInfoQuery);
	/**
	 * 
	 * @描述: 获取汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:24:56
	 * @参数:@return 
	 * @返回值：List<CarType>
	 */
	public List<CarType> carTypeList();
	/**
	 * 
	 * @描述: 添加汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:25:17
	 * @参数:@param carInfo 
	 * @返回值：void
	 */
	public void add(CarInfo carInfo);
	/**
	 * 
	 * @描述: 查询汽车车牌号码是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:25:40
	 * @参数:@param carCode
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long checkCode(String carCode);
	/**
	 * 
	 * @描述: 通过类型主键查询类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:26:15
	 * @参数:@param typeId
	 * @参数:@return 
	 * @返回值：CarType
	 */
	public CarType carType(String typeId);
	/**
	 * 
	 * @描述: 通过id查询汽车信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:26:47
	 * @参数:@param carId
	 * @参数:@return 
	 * @返回值：CarInfo
	 */
	public CarInfo carInfo(String carId); 
	/**
	 * 
	 * @描述: 修改汽车信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:28:13
	 * @参数:@param carInfo 
	 * @返回值：void
	 */
	public void update(CarInfo carInfo);
}
