package com.shxt.sys.service;

import java.util.List;

import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.CarInfo;
import com.shxt.sys.model.CarInfoQuery;
import com.shxt.sys.model.CarType;
import com.shxt.sys.model.Customer;
import com.shxt.sys.model.CustomerQuery;
import com.shxt.sys.model.RentCar;

public interface IRentService {
	/**
	 * 
	 * @描述: 查询汽车类型信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:46:19
	 * @参数:@return 
	 * @返回值：List<CarType>
	 */
	public List<CarType> carTypeList();
	/**
	 * 
	 * @描述: 带查询条件的分页查询
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:47:27
	 * @参数:@param pageBean
	 * @参数:@param query
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean find(PageBean pageBean,CarInfoQuery query);
	/**
	 * 
	 * @描述: 通过汽车id获取汽车信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:47:54
	 * @参数:@param carId
	 * @参数:@return 
	 * @返回值：CarInfo
	 */
	public CarInfo getCarInfoById(Integer carId);
	/**
	 * 
	 * @描述: 通过id获取顾客信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:48:22
	 * @参数:@param idCard
	 * @参数:@return 
	 * @返回值：Customer
	 */
	public Customer getCustomerById(String idCard);
	/**
	 * 
	 * @描述: 租赁方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:48:59
	 * @参数:@param rentCar 
	 * @返回值：void
	 */
	public void add(RentCar rentCar);
	/**
	 * 
	 * @描述: 通过id获取顾客信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:49:29
	 * @参数:@param cusId
	 * @参数:@return 
	 * @返回值：Customer
	 */
	public Customer getCustomerById(Integer cusId);
	/**
	 * 
	 * @描述: 通过id修改汽车状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:49:50
	 * @参数:@param car_id 
	 * @返回值：void
	 */
	public void updateCarInfo(Integer car_id);
	/**
	 * 
	 * @描述: 修改汽车信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:50:15
	 * @参数:@param carInfo 
	 * @返回值：void
	 */
	public void update(CarInfo carInfo);
	/**
	 * 
	 * @描述: 带查询条件的分页查询  预定汽车的信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:50:39
	 * @参数:@param pageBean
	 * @参数:@param query
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean findreturn(PageBean pageBean,CarInfoQuery query);
	/**
	 * 
	 * @描述: 修改汽车状态信息 取消预订
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:51:30
	 * @参数:@param carInfo 
	 * @返回值：void
	 */
	public void updateReturn(CarInfo carInfo);
	/**
	 * 
	 * @描述: 分页查询为归还的汽车信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:53:16
	 * @参数:@param pageBean
	 * @参数:@return 
	 * @返回值：PageBean
	 */
	public PageBean findBack(PageBean pageBean);
	/**
	 * 
	 * @描述: 查询租赁管理信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:53:52
	 * @参数:@param rentId
	 * @参数:@return 
	 * @返回值：RentCar
	 */
	public RentCar getRentCar(Integer rentId);
	/**
	 *  
	 * @描述: 归还汽车  修改租赁信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:57:11
	 * @参数:@param rentCar
	 * @参数:@return 
	 * @返回值：RentCar
	 */
	public RentCar updateBack(RentCar rentCar);
	/**
	 * 
	 * @描述: 通过id修改汽车状态信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午05:57:29
	 * @参数:@param carId 
	 * @返回值：void
	 */
	public void updateCarInfoStatus(Integer carId);
}