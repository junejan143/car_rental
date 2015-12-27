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

import com.opensymphony.xwork2.ActionContext;
import com.shxt.action.BaseAction;
import com.shxt.sys.model.Role;
import com.shxt.sys.service.IMenuService;
import com.shxt.sys.service.IRoleService;
import com.shxt.sys.service.MenuServiceImpl;
import com.shxt.sys.service.RoleServiceImpl;

public class RoleAction extends BaseAction {
	/**定义list保存role*/
	private List<Role> roleList;
	/**定义role类*/
	private Role role;
	/**定义角色id*/
	private Integer role_id;
	/**定义角色的状态*/
	private String role_status;
	/**定义角色名称*/
	private String role_name;
	/**定义角色图片*/
	private File photo; 
	/**定义角色图片名称*/
	private String photoFileName;
	/**定义角色图片类型*/
	private String photoContentType;
	/**定义菜单的数组*/
	private String[] selectedMenuIds;
	/**定义roleService接口*/
	private IRoleService roleService ;
	/**用于接口注入*/
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	/**定义menuService接口*/
	private IMenuService menuService ;
	/**用于接口注入*/
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	/**
	 * 
	 * @描述: 查询角色链表
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:47:50
	 * @参数:@return 
	 * @返回值：String
	 */
	public String list(){
		//查询角色信息
		roleList = this.roleService.getAllList();
		//跳转jsp路径
		this.toJsp="jsp/role/list";
		return DISPATCHER;
	}
	/**
	 *  
	 * @描述: 跳转修改界面
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:48:35
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//通过主键查询角色信息
		role = this.roleService.getRoleById(role_id);
		//跳转jsp路径
		this.toJsp="jsp/role/update";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 修改角色方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:49:25
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/role");
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
				role.setRole_photo(saveName);
			}
			//调用方法修改角色
			this.roleService.update(role);
			this.flag = "success";
			this.message = "更新品牌成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新品牌失败，异常信息为"+e.getMessage();
			
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 验证角色是否存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:50:12
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckName(){
		//用于传递数据
		Map<String, Object> map = new HashMap<String, Object>();
 		//查询角色
		Long count = this.roleService.checkName(role_name);
		//判断角色    >0角色已存在   <0角色不存在
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该角色已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 
	 * @描述: 显示角色授权功能
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:52:08
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toAuthorize(){
		
		try {
			//通过id查询角色信息
			role = this.roleService.getRoleById(role_id);
			//获取该角色下的已拥有的菜单信息
			ActionContext.getContext().put("selectedMenuList", this.menuService.getSelectedMenuById(role_id));
			//获取该角色下的未拥有的菜单信息
			ActionContext.getContext().put("unSelectedMenuList", this.menuService.getUnSelectedMenuById(role_id));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//jsp跳转路径
		this.toJsp="jsp/role/authorize";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 角色授权
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:53:06
	 * @参数:@return 
	 * @返回值：String
	 */
	public String updateAuthorize(){
		
		try {
			//通过id修改角色权限
			this.roleService.updateAuthorize(role_id, selectedMenuIds);
			
			this.flag = "success";
			this.message = "权限更新成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "权限更新失败，异常信息为"+e.getMessage();
			
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
		
	}
	/**
	 * 
	 * @描述: 删除角色
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:54:14
	 * @参数:@return 
	 * @返回值：String
	 */
	public String delete(){
		try {
			//查询角色信息
			this.roleService.delete(role_id);
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//提示 并刷新界面
			out.write("<script type='text/javascript'>alert('您已经彻底删除该角色，谢谢合作！');window.location.href='listRoleAction.action'</script> ");
				
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		return NONE;
		
	}
	
	
	/**跳到添加jsp界面*/
	public String toAdd(){
		this.toJsp = "jsp/role/add";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 添加角色
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:55:28
	 * @参数:@return 
	 * @返回值：String
	 */
	public String add(){
		try {
			
			if(photo!=null){
				//2获取文件上传目录
				String realpath = this.servletContext.getRealPath("/upload/role");
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
				role.setRole_photo(saveName);
			}
			//调用方法添加角色
			this.roleService.add(role);
			this.flag = "success";
			this.message = "添加角色成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加角色失败，异常信息为"+e.getMessage();
			
		}
		//提示jsp路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 改变角色状态
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午06:56:29
	 * @参数:@return 
	 * @返回值：String
	 */
	public String changStatus(){
		//用于传递数据
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//通过id改变角色状态
			this.roleService.updateStatus(role_status, role_id);
			
			map.put("flag", "success");
			map.put("message", "更新状态成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "error");
			map.put("message", "更新状态失败,异常信息为:"+e.getMessage());
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
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

	public String[] getSelectedMenuIds() {
		return selectedMenuIds;
	}

	public void setSelectedMenuIds(String[] selectedMenuIds) {
		this.selectedMenuIds = selectedMenuIds;
	}

	public String getRole_status() {
		return role_status;
	}

	public void setRole_status(String roleStatus) {
		role_status = roleStatus;
	}
	
	
	
	
}
