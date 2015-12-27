package com.shxt.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware,
		ServletContextAware, SessionAware {
	
	/**获取session*/
	protected Map<String, Object> session;
	/**获取request*/
	protected HttpServletRequest request;
	/**获取response*/
	protected HttpServletResponse response;
	/**获取application*/
	protected ServletContext servletContext;
 	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext=servletContext;
	}

	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	/**错误的类型*/
	protected String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**标识状态*/
	protected String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	/**跳转到jsp*/
	protected String toJsp;

	public String getToJsp() {
		return toJsp;
	}

	public void setToJsp(String toJsp) {
		this.toJsp = toJsp;
	}
	/**跳转到action*/
	protected String toAction;

	public String getToAction() {
		return toAction;
	}

	public void setToAction(String toAction) {
		this.toAction = toAction;
	}
	
	/**重定向到JSP页面*/
	protected String DISPATCHER="dispatcher";
	/**请求转发到JSP页面*/
	protected String REDIRECT="redirect";
	/**请求转发到Action*/
	protected String CHAIN="chain";
	/**重定向到Action*/
	protected String REDIRECTACTION="redirectAction";
	/**josn请求使用*/
	protected String JSON="json";
	/**针对于josn传递数据使用*/
	protected Object jsonResult;
	
	public Object getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Object jsonResult) {
		this.jsonResult = jsonResult;
	}
	
}
