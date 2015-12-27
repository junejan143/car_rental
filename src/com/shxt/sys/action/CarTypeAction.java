package com.shxt.sys.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.shxt.action.BaseAction;
import com.shxt.sys.model.CarType;
import com.shxt.sys.service.CarTypeServiceImpl;
import com.shxt.sys.service.ICarTypeService;

public class CarTypeAction extends BaseAction {
	//定义list保存CarType类
	private List<CarType> parentList;
	//定义CarType类
	private CarType carType;
	//定义汽车类型的ID
	private Integer type_id;
	//定义汽车类型的名称
	private String type_name;
	//定义汽车类型的状态
	private String status;
	//定义汽车类型的图片
	private File photo; 
	//定义汽车类型的图片的名称
	private String photoFileName;
	//定义汽车类型的图片的后缀
	private String photoContentType;
	//定义carTypeService接口
	private ICarTypeService carTypeService;
	//用于接口注入
	public void setCarTypeService(ICarTypeService carTypeService) {
		this.carTypeService = carTypeService;
	}
	/**
	 * 
	 * @描述: 查询汽车品牌数据
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午06:05:14
	 * @参数:@return 
	 * @返回值：String
	 */
	public String parentList(){
		//调用getParentList查询数据
		parentList = this.carTypeService.getParentList();
		//跳转路径为jsp/cartype/list
		this.toJsp = "jsp/cartype/list";
		return DISPATCHER;
	}
	/**跳到添加的jsp界面*/
	public String toAdd(){
		
		this.toJsp = "jsp/cartype/add";
		return DISPATCHER;
	}
	/**品牌添加功能*/
	public String add(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/cartype");
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
				carType.setIcon(saveName);
			}
			carTypeService.add(carType);
			this.flag = "success";
			this.message = "添加品牌成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加品牌失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到修改界面
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午06:08:03
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//调用getCarTypeById方法通过ID查询数据
		carType = this.carTypeService.getCarTypeById(type_id);
		//跳转路径为jsp/cartype/update
		this.toJsp="jsp/cartype/update";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 汽车品牌修改方法
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午06:18:27
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/cartype");
				System.out.println(realpath);
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
				carType.setIcon(saveName);
			}
			//调用update修改数据
			carTypeService.update(carType);
			this.flag = "success";
			this.message = "更新品牌成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新品牌失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 检测品牌是否重名
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午06:19:50
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckName(){
		
		Map<String, Object> map = new HashMap<String, Object>();
 		//调用checkName方法
		Long count = this.carTypeService.checkName(type_name);
		//判断品牌是否可用   大于0不可用   小于0可用
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该品牌已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		//用于传递提示信息
		this.jsonResult = map;
		return JSON;
	}
	/**
	 *  
	 * @描述: 修改品牌状态
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午06:24:01
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toChangeStatus(){
		try {
			//调用updateStatus方法修改状态
			this.carTypeService.updateStatus(status, type_id);
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//刷新界面
			out.write("<script type='text/javascript'>window.location.href='parentListCarTypeAction.action'</script> ");
				
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return NONE;
	}
	/**
	 * 
	 * @描述: 彻底删除此品牌
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午06:25:27
	 * @参数:@return 
	 * @返回值：String
	 */
	public String delete(){
		try {
			//通过ID删除此品牌
			this.carTypeService.delete(type_id);
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//刷新界面
			out.write("<script type='text/javascript'>window.location.href='parentListCarTypeAction.action'</script> ");
				
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return NONE;
	}
	
	public List<CarType> getParentList() {
		return parentList;
	}

	public void setParentList(List<CarType> parentList) {
		this.parentList = parentList;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
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
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer typeId) {
		type_id = typeId;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String typeName) {
		type_name = typeName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
