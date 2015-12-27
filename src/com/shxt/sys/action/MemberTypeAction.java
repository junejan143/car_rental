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
import com.shxt.sys.model.MemberType;
import com.shxt.sys.service.IMemberTypeService;
import com.shxt.sys.service.MemberTypeServiceImpl;


public class MemberTypeAction extends BaseAction {
	//定义一个list用于存入membertype类
	private List<MemberType> list;
	//定义一个membertype类
	private MemberType memberType;
	//定义会员类型的主键
	private Integer type_id;
	//定义会员状态
	private String status;
	//定义会员名称
	private String type_name;
	//定义会员头像
	private File photo; 
	//定义头像的文件名称
	private String photoFileName;
	//定义会员头像上的文件类型
	private String photoContentType;
	//定义一个memberTypeService接口
	private IMemberTypeService memberTypeService ;
	//用于接口注入
	public void setMemberTypeService(IMemberTypeService memberTypeService) {
		this.memberTypeService = memberTypeService;
	}

	/**查询所有的会员类型*/
	public String list(){
		//调用getList方法查询会员类型
		list = this.memberTypeService.getList();
		//跳转的jsp路径
		this.toJsp = "jsp/membertype/list";
		return DISPATCHER;
	}
	
	/**跳到添加的jsp界面*/
	public String toAdd(){
		//跳转到添加方法的路径
		this.toJsp = "jsp/membertype/add";
		return DISPATCHER;
	}
	
	/**添加功能*/
	public String add(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/membertype");
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
				memberType.setPhoto(saveName);
			}
			//调用add方法添加数据
			this.memberTypeService.add(memberType);
			//提示信息
			this.flag = "success";
			this.message = "添加品牌成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加品牌失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面的路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到修改界面
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:07:26
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//通过ID查询要修改的会员类型
		memberType = this.memberTypeService.getMemberTypeById(type_id);
		//要跳转的jsp路径
		this.toJsp="jsp/membertype/update";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 修改方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:08:41
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/membertype");
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
				memberType.setPhoto(saveName);
			}
			//调用update方法修改数据
			memberTypeService.update(memberType);
			//提示信息
			this.flag = "success";
			this.message = "更新品牌成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新品牌失败，异常信息为"+e.getMessage();
			
		}
		//跳转的提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 验证会员类型是否已存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:09:44
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckName(){
		//保存提示信息
		Map<String, Object> map = new HashMap<String, Object>();
 		//调用checkName方法 通过返回值判断会员类型是否存在   >0存在   <0不存在
		Long count = this.memberTypeService.checkName(type_name);
		//判断是否存在
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该会员类型已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		//传递信息
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 
	 * @描述: 更改会员的状态方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:14:14
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toChangeStatus(){
		
		try {
			//调用updateStatus方法更改状态
			this.memberTypeService.updateStatus(type_id, status);
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//跳转到listMemberTypeAction 用于实现页面刷新
			out.write("<script type='text/javascript'>window.location.href='listMemberTypeAction.action'</script> ");
				
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
	 * @描述: 彻底删除会员类型
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:16:24
	 * @参数:@return 
	 * @返回值：String
	 */
	public String delete(){
		try {
			//调用delete方法 通过主键ID删除会员类型
			this.memberTypeService.delete(type_id);
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//跳转到listMemberTypeAction 用于实现页面刷新
			out.write("<script type='text/javascript'>window.location.href='listMemberTypeAction.action'</script> ");
				
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return NONE;
	}
	
	
	public List<MemberType> getList() {
		return list;
	}



	public void setList(List<MemberType> list) {
		this.list = list;
	}



	public MemberType getMemberType() {
		return memberType;
	}



	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
