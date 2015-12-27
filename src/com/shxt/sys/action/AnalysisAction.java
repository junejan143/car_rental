package com.shxt.sys.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.shxt.action.BaseAction;
import com.shxt.base.dao.PageBean;
import com.shxt.sys.chart.Chart;
import com.shxt.sys.chart.ChartDatas;
import com.shxt.sys.model.User;
import com.shxt.sys.service.IAnalysisService;

public class AnalysisAction extends BaseAction {
	//定义角色ID
	private Integer role_id;
	//定义list保存user类
	private List<User> userList;
	//定义pageBase做分页操作
	private PageBean pageBean;
	//定义analysisService接口
	private IAnalysisService analysisService;
	//用于注入接口
	public void setAnalysisService(IAnalysisService analysisService) {
    	this.analysisService = analysisService;
    }

	/**
	 * 跳转到统计角色人员统计图界面
	 * @return
	 */
	public String toStatisticRole(){
		this.toJsp = "jsp/statistic/role";
		return DISPATCHER;
	}
	/**
	 * 
	 * @描述: 查询数据用于显示图表数据
	 * @作者: 徐新伟
	 * @时间:2015-3-10 上午10:27:16
	 * @参数:@return
	 * @参数:@throws IOException 
	 * @返回值：String
	 */
	public String showRoleChart() throws IOException{
		//设置图表的各各属性
		Chart chart = new Chart();
		//设置y轴的名称
		chart.setYaxisname("用户人数");
		//设置图表的名称
		chart.setCaption("统计角色拥有的人数");
		//设置x轴的名称
		chart.setxAxisName("角色名称");
		try {
			//从数据库获取数据
			List<ChartDatas> chartDatas =this.analysisService.roleAnalysis();
			
			Map<String, Object> map = new HashMap<String, Object>();
			//存入map中
			map.put("chart", chart);
			map.put("data", chartDatas);
			
			//this.jsonResult = "{\"chart\": {\"yaxisname\": \"四海兴唐\",\"caption\": \"Top 5 Sales Person\",\"numberprefix\": \"$\",\"useroundedges\": \"1\",\"bgcolor\": \"FFFFFF,FFFFFF\",\"showborder\": \"0\"},\"data\": [{\"label\": \"Alex\",\"value\": \"25000\"},{\"label\": \"Mark\",\"value\": \"35000\"},{\"label\": \"David\",\"value\": \"42300\"},{\"label\": \"Graham\",\"value\": \"35300\"},{\"label\": \"John\",\"value\": \"31300\"}]}";
			//gosn方式传递数据
			Gson gson = new Gson();
			
			this.response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = this.response.getWriter();
			out.write(gson.toJson(map));
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return NONE;
	}
	
	/**
	 * 
	 * @描述: 分页显示每类角色的信息
	 * @作者: 徐新伟
	 * @时间:2015-3-10 上午10:29:23
	 * @参数:@return 
	 * @返回值：String
	 */
	public String toShowRole(){
		//判断pagebean是否为空，为空创建pagebean
		if(pageBean==null){
			pageBean = new PageBean();
		}
		//查询角色信息存入pagebean
		pageBean = this.analysisService.showRole(role_id, pageBean);
		//跳转到showrole界面
		this.toJsp = "jsp/statistic/showrole";
		
		return DISPATCHER;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}
