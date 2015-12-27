package com.shxt.sys.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.shxt.action.BaseAction;
import com.shxt.sys.dto.MenuDTO;
import com.shxt.sys.model.Menu;
import com.shxt.sys.model.Role;
import com.shxt.sys.model.User;
import com.shxt.sys.service.IMenuService;
import com.shxt.sys.service.MenuServiceImpl;

public class MenuAction extends BaseAction {
	//定义list存父节点菜单
	private List<Menu> parentList;
	//定义menu类
	private Menu menu;
	//定义菜单名称
	private String menu_name;
	//定义menuService接口
	private IMenuService menuService ;
	//定义菜单主键
	private Integer menu_id;
	//用于接口注入
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	/**
	 * 
	 * @描述: 
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:21:56
	 * @参数:@return 
	 * @返回值：String
	 */
	public String parentList() {
		//获取父节点信息
		parentList = menuService.getParentList();
		//获取父节点信息
		List<MenuDTO> tempList = menuService.getParentListSQL();
		//用于传值
		ActionContext.getContext().put("parentNoteList", tempList);
		//跳转的jsp路径
		this.toJsp = "jsp/menu/parent_list";
		return DISPATCHER;
	}
	/**跳到添加父节点的jsp界面*/
	public String toAddParent(){
		//跳转到添加父节点的界面路径
		this.toJsp = "jsp/menu/addparent";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 添加父节点的方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:26:10
	 * @参数:@return 
	 * @返回值：String
	 */
	public String addParent(){
		
		try {
			//调用add方法添加父节点菜单
			menuService.addParent(menu);
			//提示信息
			this.flag = "success";
			this.message = "添加菜单成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加菜单失败，异常信息为"+e.getMessage();
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**跳到添加子节点的jsp界面*/
	public String toAddChild(){
		//查询父节点信息  用于显示添加到哪一个父菜单下
		parentList = menuService.getParentList();
		//跳转到子菜单添加界面路径
		this.toJsp = "jsp/menu/addchild";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 添加子菜单方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:30:28
	 * @参数:@return 
	 * @返回值：String
	 */
	public String addChild(){
		
		try {
			//设置添加的父节点id
			menu.setParent_id(menu_id.toString());
			//调用addChild方法添加子菜单
			this.menuService.addChild(menu);
			//提示信息
			this.flag = "success";
			this.message = "添加菜单成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加菜单失败，异常信息为"+e.getMessage();
		}
		//跳转的提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 用于验证是否已存在
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:32:49
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckName(){
		
		Map<String, Object> map = new HashMap<String, Object>();
 		//查询是否存在
		Long count = this.menuService.checkName(menu_name);
		//判断是否存在  >0存在   <0不存在
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该菜单已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 
	 * @描述: 通过user_id获取左侧菜单栏
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:34:47
	 * @参数:@return 
	 * @返回值：String
	 */
	public String getNavLeft(){
		//通过session获取用户对象
		User user = (User) this.session.get("session_user");
		//取除该用户对于的菜单信息
		ActionContext.getContext().put("childLeftMenuList", this.menuService.getChildLeftMenuById(user.getUser_id()));
		//取除该用户对于的父菜单信息
		ActionContext.getContext().put("parentLeftMenuList", this.menuService.getParentLeftMenuById(user.getUser_id()));
		//跳转jsp路径
		this.toJsp="jsp/main/main";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 删除子菜单
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:35:53
	 * @参数:@return 
	 * @返回值：String
	 */
	public String deleteChild(){
		try {
			//通过id删除子菜单
			this.menuService.deleteChild(menu_id);
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//提示删除成功，并刷新界面
			out.write("<script type='text/javascript'>alert('您已经彻底删除该菜单，谢谢合作！');window.location.href='parentListMenuAction.action'</script> ");
				
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
	 * @描述: 删除父菜单
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:37:29
	 * @参数:@return 
	 * @返回值：String
	 */
	public String deleteParent(){
		try {
			//通过id删除父菜单
			this.menuService.deleteParent(menu_id);
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			//提示删除成功，并刷新界面
			out.write("<script type='text/javascript'>alert('您已经彻底删除该菜单，谢谢合作！');window.location.href='parentListMenuAction.action'</script> ");
				
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
	 * @描述: 验证父菜单下是否存在子菜单
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:38:33
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckChild(){
		//用于存放数据
		Map<String, Object> map = new HashMap<String, Object>();
 		try {
 			//查询数据
 			Long count = this.menuService.checkChild(menu_id);
 			//判断是否存在  >0存在  <0不存在
 			if(count>0){
 				map.put("flag", "error");
 				map.put("message", "该菜单存在子节点，不可以删除！！！");
 			}else{
 				map.put("flag", "success");
 			}
 			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}
	/**
	 * 
	 * @描述: 跳转到修改父菜单界面
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:41:04
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdateParent(){
		//查询并传递数据
		menu = this.menuService.menu(menu_id);
		//要跳转的jsp界面
		this.toJsp = "jsp/menu/updateparent";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 修改父菜单方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:42:53
	 * @参数:@return 
	 * @返回值：String
	 */
	public String updateParent(){
		try {
			//调用updateParent方法修改父菜单信息
			this.menuService.updateParent(menu);
			//提示信息
			this.flag = "success";
			this.message = "更新父节点成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新父节点失败，异常信息为"+e.getMessage();
			
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到修改子菜单界面
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:44:27
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdateChild(){
		//查询父菜单信息
		parentList = menuService.getParentList();
		//通过id查询子菜单的所有信息
		menu = this.menuService.menu(menu_id);
		//跳转的jsp路径
		this.toJsp = "jsp/menu/updatechild";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 修改子菜单方法
	 * @作者: 徐新伟
	 * @时间:2015-3-25 下午03:48:31
	 * @参数:@return 
	 * @返回值：String
	 */
	public String updateChild(){
		try {
			//通过id修改子菜单
			this.menuService.updateChild(menu,menu_id.toString());
			//提示信息
			this.flag = "success";
			this.message = "更新父节点成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新父节点失败，异常信息为"+e.getMessage();
			
		}
		//提示界面路径
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	
	public List<Menu> getParentList() {
		return parentList;
	}

	public void setParentList(List<Menu> parentList) {
		this.parentList = parentList;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menuName) {
		menu_name = menuName;
	}
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menuId) {
		menu_id = menuId;
	}
}
