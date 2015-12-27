package com.shxt.sys.action;

import java.util.List;
import com.shxt.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.base.utils.DateFormatHelper;
import com.shxt.sys.model.CarInfo;
import com.shxt.sys.model.CarInfoQuery;
import com.shxt.sys.model.CarType;
import com.shxt.sys.model.Customer;
import com.shxt.sys.model.RentCar;
import com.shxt.sys.service.IRentService;

public class RentAction extends BaseAction {
	//分页
	private PageBean pageBean;
	//查询
	private CarInfoQuery query;
	//车辆id
	private Integer car_id;
	//顾客id
	private Integer cus_id;
	//身份证号
	private String id_card;
	//租赁id
	private Integer rent_id;
	//定义rentCar类
	private RentCar rentCar;
	//定义list存CarType类
	private List<CarType> carTypeList;
	//定义carInfo类
	private CarInfo carInfo;
	//定义rentService接口
	public IRentService rentService;
	//用于接口注入
	public void setRentService(IRentService rentService) {
		this.rentService = rentService;
	}
	/**
	 * 
	 * @描述: 分页查询租赁信息
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:57:46
	 * @参数:@return 
	 * @返回值：String
	 */
	public String find(){
		//判断是否有pageBean
		if(pageBean==null){
			pageBean = new PageBean();
		}
		//设置每页显示的条数
		pageBean.setPageSize(5);
		//查询汽车类型信息
		carTypeList = this.rentService.carTypeList();
		//分页查询租赁情况
		this.rentService.find(pageBean, query);
		//要跳转的jsp路径
		this.toJsp = "jsp/rent/list";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到租赁界面方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:00:01
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toAdd(){
		//查询汽车信息
		carInfo = this.rentService.getCarInfoById(car_id);
		//跳转到jsp界面路径
		this.toJsp = "jsp/rent/add";
		return DISPATCHER;
	
	}
	/**
	 * 
	 * @描述: 租赁方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:02:56
	 * @参数:@return 
	 * @返回值：String
	 */
	public String add(){

		try {	
			//判断租赁的汽车是否存在
			if (car_id!=null&&car_id>0) {
				//通过主键查询汽车信息
				carInfo = this.rentService.getCarInfoById(car_id);
				//存入租赁表
				rentCar.setCarInfo(carInfo);
			}
			//判断顾客是否存在
			if (cus_id!=null&&cus_id>0) {
				//用过主键插叙顾客信息
				Customer customer= this.rentService.getCustomerById(cus_id);
				//存入租赁表
				rentCar.setCustomer(customer);
			}
			//存入租赁价格
			rentCar.setRent_price(carInfo.getRent_price());
			//存入汽车车牌号
			rentCar.setCar_code(carInfo.getCar_code());
			//存入汽车保证金
			rentCar.setDeposit(carInfo.getDeposit());
			//存入应收金额
			rentCar.setReceivable_price(String.valueOf(Float.parseFloat(carInfo.getRent_price())*rentCar.getDays_number()));
			//存入应该归还日期
			rentCar.setEnd_date(DateFormatHelper.parseDate(DateFormatHelper.getAddDay(DateFormatHelper.toString(rentCar.getStart_date()),DateFormatHelper.DEFAULT_DATE,rentCar.getDays_number(),"yyyy-MM-dd"),"yyyy-MM-dd"));
			//修改该汽车状态为租赁
			this.rentService.updateCarInfo(car_id);
			//添加租赁表
			this.rentService.add(rentCar);
			//提示信息
			this.flag = "success";
			this.message = "租赁成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "租赁失败，异常信息为"+e.getMessage();
			
		}
		//跳转jsp界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 获取顾客信息方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:14:04
	 * @参数:@return 
	 * @返回值：String
	 */
	public String getCustomer(){
		try {
			//通过主键id获取顾客信息
			this.jsonResult = this.rentService.getCustomerById(id_card);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON;
	}
	/**
	 * 
	 * @描述: 预定汽车方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:14:56
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//通过id查询汽车信息
		carInfo = this.rentService.getCarInfoById(car_id);
		//跳转到预定界面
		this.toJsp = "jsp/rent/update";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 汽车预定方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:29:43
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){
		
		try {	
			//设置汽车状态
			carInfo.setCar_status("2");
			//修改汽车状态
			this.rentService.update(carInfo);
			
			this.flag = "success";
			this.message = "预定成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "预定失败，异常信息为"+e.getMessage();
			
		}
		//提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 租赁管理方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:31:40
	 * @参数:@return 
	 * @返回值：String
	 */
	public String findReturn(){
		//判断是否有pageBean
		if(pageBean==null){
			pageBean = new PageBean();
		}
		//设置每页显示的条数
		pageBean.setPageSize(5);
		//查询汽车类型信息
		carTypeList = this.rentService.carTypeList();
		//查询汽车状态为预定的
		this.rentService.findreturn(pageBean, query);
		//跳转到预定管理界面
		this.toJsp = "jsp/rent/listreturn";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 取消预订方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:39:52
	 * @参数:@return 
	 * @返回值：String
	 */
	public String updateReturn(){
		try {	
			//通过主键id查询汽车信息
			carInfo = this.rentService.getCarInfoById(car_id);
			//设置汽车状态为可租
			carInfo.setCar_status("1");
			//修改汽车信息
			this.rentService.updateReturn(carInfo);
			//提示信息
			this.flag = "success";
			this.message = "取消预订成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "取消预订失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 分页显示租赁汽车信息方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:41:55
	 * @参数:@return 
	 * @返回值：String
	 */
	public String findBack(){
		//判断是否存在pageBean
		if(pageBean==null){
			pageBean = new PageBean();
		}
		//设置每页显示条数
		pageBean.setPageSize(5);
		//查询租赁信息
		this.rentService.findBack(pageBean);
		//跳转到jsp界面路径
		this.toJsp = "jsp/rent/listback";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到归还界面方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:44:30
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toBack(){
		//通过主键查询归还信息
		rentCar = this.rentService.getRentCar(rent_id);
		//归还界面路径
		this.toJsp = "jsp/rent/back";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 汽车归还方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午04:45:35
	 * @参数:@return 
	 * @返回值：String
	 */
	public String updateBack(){
		
		try {	
			//通过主键修改汽车状态
			this.rentService.updateCarInfoStatus(car_id);
			//修改租赁表信息
			this.rentService.updateBack(rentCar);
			//提示信息
			this.flag = "success";
			this.message = "归还成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "归还失败，异常信息为"+e.getMessage();
			
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	
	public PageBean getPageBean() {
		return pageBean;
	}
	
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<CarType> getCarTypeList() {
		return carTypeList;
	}

	public void setCarTypeList(List<CarType> carTypeList) {
		this.carTypeList = carTypeList;
	}

	public CarInfoQuery getQuery() {
		return query;
	}

	public void setQuery(CarInfoQuery query) {
		this.query = query;
	}


	public Integer getCar_id() {
		return car_id;
	}


	public void setCar_id(Integer carId) {
		car_id = carId;
	}


	public CarInfo getCarInfo() {
		return carInfo;
	}


	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}


	public String getId_card() {
		return id_card;
	}


	public void setId_card(String idCard) {
		id_card = idCard;
	}

	public Integer getCus_id() {
		return cus_id;
	}


	public void setCus_id(Integer cusId) {
		cus_id = cusId;
	}


	public RentCar getRentCar() {
		return rentCar;
	}


	public void setRentCar(RentCar rentCar) {
		this.rentCar = rentCar;
	}


	public Integer getRent_id() {
		return rent_id;
	}


	public void setRent_id(Integer rentId) {
		rent_id = rentId;
	}
	
}
