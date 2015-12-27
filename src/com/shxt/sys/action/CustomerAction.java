package com.shxt.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shxt.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.sys.model.Customer;
import com.shxt.sys.model.CustomerQuery;
import com.shxt.sys.model.MemberType;
import com.shxt.sys.model.RentCar;
import com.shxt.sys.model.Role;
import com.shxt.sys.service.ICustomerService;

public class CustomerAction extends BaseAction {
	//定义分页
	private PageBean pageBean;
	//定义查询条件
	private CustomerQuery query;
	//定义身份证号
	private String cusIdCard;
	//定义Customer类
	private Customer customer;
	//定义会员类型
	private String type_id;
	//定义主键ID
	private Integer cus_id;
	//定义list保存MemberType类
	private List<MemberType> memberTypesList;
	//定义customerService接口
	private ICustomerService customerService;
	//用于接口注入
	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}
	/**
	 * 
	 * @描述: 查询分页数据
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:07:43
	 * @参数:@return 
	 * @返回值：String
	 */
	public String find(){
		//判断是否存在pageBean
		if (pageBean==null) {
			//创建pageBean
			pageBean = new PageBean();
		}
		//调用find方法查询数据
		this.customerService.find(pageBean,query);
		//跳转路径为jsp/customer/list
		this.toJsp="jsp/customer/list";
		return DISPATCHER;
		
	}
	/**
	 * 
	 * @描述: 跳转到添加界面
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:11:46
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toAdd(){
		//获取会员类型
		memberTypesList = this.customerService.memberTypeList();
		//跳转路径为jsp/customer/add
		this.toJsp="jsp/customer/add";
		
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 添加顾客方法
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:13:01
	 * @参数:@return 
	 * @返回值：String
	 */
	public String add(){
		
		try {	
			//判断是否为会员
			if(type_id!=null&&type_id.length()>0){
				//查询会员对象
				MemberType memberType = this.customerService.memberType(type_id);
				//设置会员
				customer.setMemberType(memberType);
			}
			//调用add方法
			this.customerService.add(customer);
			
			this.flag = "success";
			this.message = "添加用户成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "添加用户失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 跳转到修改客户信息界面
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:14:35
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toUpdate(){
		//通过ID查询客户的信息
		customer = this.customerService.getCustomerById(cus_id);
		//查询会员类型
		memberTypesList = this.customerService.memberTypeList();
		//跳转路径为jsp/customer/update
		this.toJsp="jsp/customer/update";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 修改客户信息方法
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:16:42
	 * @参数:@return 
	 * @返回值：String
	 */
	public String update(){
	
		try {
			//判断会员类型是否存在
			if(type_id!=null&type_id.length()>0){
				//通过ID获取会员类型
				MemberType memberType = this.customerService.memberType(type_id);
				//设置会员类型
				customer.setMemberType(memberType);
			}
			//调用update方法修改客户信息
			this.customerService.update(customer);
			
			this.flag = "success";
			this.message = "更新用户成功，谢谢合作！";
		} catch (Exception e) {
			e.printStackTrace();
			this.flag = "error";
			this.message = "更新用户失败，异常信息为"+e.getMessage();
			
		}
		//跳转到提示界面
		this.toJsp = "jsp/message";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 改变客户的状态
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:21:11
	 * @参数:@return 
	 * @返回值：String
	 */
	public String changStatus(){
		//定义map用于传递数据
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			//调用updateStatus方法修改客户状态
			this.customerService.updateStatus(cus_id);
			
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
	/**
	 * 
	 * @描述: 验证身份证号是否可用
	 * @作者: 徐新伟
	 * @时间:2015-3-19 下午07:22:13
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toCheckCard(){
		//定义map用于传递数据
		Map<String, Object> map = new HashMap<String, Object>();
 		//调用checkCard方法     通过返回值判断是否有身份证号是否存在   >0存在    <0不存在
		Long count = this.customerService.checkCard(cusIdCard);
		if(count>0){
			map.put("flag", "error");
			map.put("message", "该身份证号已存在，请重新输入！！！");
		}else{
			map.put("flag", "success");
		}
		//传递数据
		this.jsonResult = map;
		return JSON;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public CustomerQuery getQuery() {
		return query;
	}

	public void setQuery(CustomerQuery query) {
		this.query = query;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<MemberType> getMemberTypesList() {
		return memberTypesList;
	}

	public void setMemberTypesList(List<MemberType> memberTypesList) {
		this.memberTypesList = memberTypesList;
	}

	public String getCusIdCard() {
		return cusIdCard;
	}

	public void setCusIdCard(String cusIdCard) {
		this.cusIdCard = cusIdCard;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String typeId) {
		type_id = typeId;
	}

	public Integer getCus_id() {
		return cus_id;
	}

	public void setCus_id(Integer cusId) {
		cus_id = cusId;
	}

	
	
}
