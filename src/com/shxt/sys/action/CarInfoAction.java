package com.shxt.sys.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.shxt.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.CarInfo;
import com.shxt.sys.model.CarInfoQuery;
import com.shxt.sys.model.CarType;
import com.shxt.sys.model.Role;
import com.shxt.sys.service.ICarInfoService;

public class CarInfoAction extends BaseAction {
	//定义分页
	private PageBean pageBean;
	//定义查询条件
	private CarInfoQuery query;
	//定义汽车类
	private CarInfo carInfo;
	//定义汽车类型ID
	private String type_id;
	//定义汽车ID
	private String car_id;
	//定义汽车车牌号
	private String carCode;
	//定义汽车图片
	private File photo; 
	//定义汽车图片的名称
	private String photoFileName;
	//定义汽车图片的后缀
	private String photoContentType;
	//定义一个list存汽车品牌类型
	private List<CarType> carTypeList;
	//定义carInfoService接口
	private ICarInfoService carInfoService;
	//用于注入接口
	public void setCarInfoService(ICarInfoService carInfoService) {
		this.carInfoService = carInfoService;
	}
	/**
	 * 查询汽车的所有信息分页显示
	 */
	public String find(){
		//判断是否有pageBean
		if(pageBean==null){
			//新建pageBean
			pageBean = new PageBean();
		}
		//设置pageBean的每页显示条数
		pageBean.setPageSize(4);
		//查询汽车的类型信息
		carTypeList = this.carInfoService.carTypeList();
		//查询汽车的信息
		this.carInfoService.find(pageBean, query);
		//跳转到jsp/carinfo/list路径
		this.toJsp="jsp/carinfo/list";
		return DISPATCHER;
	}
	/**
	 * 	
	 * @描述: 用于跳转到添加界面
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午05:49:24
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toAdd(){
		//传递汽车的类型到添加界面
		carTypeList = this.carInfoService.carTypeList();
		//跳转到jsp/carinfo/add路径
		this.toJsp = "jsp/carinfo/add";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 添加汽车方法
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午05:50:42
	 * @参数:@return 
	 * @返回值：String
	 */
	public String add(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/carinfo");
				//3判断文件夹是否存在
				File folder = new File(realpath);
				if(!folder.isDirectory()){
					folder.mkdir();
				}
				String ext = FilenameUtils.getExtension(photoFileName);
				
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(1000)+"."+ext;
				//String saveName = UUID.randomUUID().toString()+"."+ext;
				//4在tomcat项目的根目录upload的文件夹下创建
				File saveFile = new File(realpath, saveName);
				//5上传
				FileUtils.copyFile(photo, saveFile);
				//保存
				carInfo.setPhoto(saveName);
			}
			
			if(type_id!=null&&type_id.length()>0){
				//查询role对象
				CarType carType = this.carInfoService.carType(type_id);
				carInfo.setCarType(carType);
			}
			//调用add方法添加数据
			this.carInfoService.add(carInfo);
			this.flag = "success";
			this.message = "添加车辆成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加车辆失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 检测车牌号是否可用
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午05:52:42
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckCode(){
		
		Map<String, Object> map = new HashMap<String, Object>();
 		//调用checkCode方法
		Long count = this.carInfoService.checkCode(carCode);
		//判断车牌号是否已用，大于0不可用，小于0可用
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该车牌号已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		this.jsonResult = map;
		return JSON;

	}
	/**
	 * 
	 * @描述: 跳转到汽车修改界面
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午05:55:43
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//通过ID查询汽车的所有信息
		carInfo = this.carInfoService.carInfo(car_id);
		//查询汽车的类型
		carTypeList = this.carInfoService.carTypeList();
		//跳转路径为jsp/carinfo/update
		this.toJsp = "jsp/carinfo/update";
		
		return DISPATCHER;
		
	}
	/**
	 * 
	 * @描述: 汽车修改方法
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午05:57:25
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/carinfo");
				//3判断文件夹是否存在
				File folder = new File(realpath);
				if(!folder.isDirectory()){
					folder.mkdir();
				}
				String ext = FilenameUtils.getExtension(photoFileName);
				
				String saveName = (new Date()).getTime()+"_"+(new Random()).nextInt(1000)+"."+ext;
				//String saveName = UUID.randomUUID().toString()+"."+ext;
				//4在tomcat项目的根目录upload的文件夹下创建
				File saveFile = new File(realpath, saveName);
				//5上传
				FileUtils.copyFile(photo, saveFile);
				//保存
				carInfo.setPhoto(saveName);
			}
			
			if(type_id!=null&&type_id.length()>0){
				//查询role对象
				CarType carType = this.carInfoService.carType(type_id);
				carInfo.setCarType(carType);
			}
			//调用update方法修改汽车的信息
			this.carInfoService.update(carInfo);
			
			this.flag = "success";
			this.message = "更新车辆成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新车辆失败，异常信息为"+e.getMessage();
			
		}
		
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: Ajax 实现加载更多的功能
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午05:59:03
	 * @参数:@return 
	 * @返回值：String
	 */
	public String findList(){
		
		if(pageBean==null){
			pageBean = new PageBean();
		}
		//查询pageBean
		pageBean = this.carInfoService.find(pageBean, query);
		//传递数据
		this.jsonResult=pageBean;
		
		return JSON;
	}
	
	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public CarInfoQuery getQuery() {
		return query;
	}

	public void setQuery(CarInfoQuery query) {
		this.query = query;
	}

	public List<CarType> getCarTypeList() {
		return carTypeList;
	}

	public void setCarTypeList(List<CarType> carTypeList) {
		this.carTypeList = carTypeList;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContentType() {
		return photoContentType;
	}

	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}

	public CarInfo getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(CarInfo carInfo) {
		this.carInfo = carInfo;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCar_id() {
		return car_id;
	}

	public void setCar_id(String carId) {
		car_id = carId;
	}

	

	
	
}
