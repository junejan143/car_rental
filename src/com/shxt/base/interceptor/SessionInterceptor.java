package com.shxt.base.interceptor;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取session为map类型
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		ActionProxy proxy = invocation.getProxy();
		//获取actiong的名称
		String actionName = proxy.getActionName();
		//获取命名空间
		String nameSpace = proxy.getNamespace();
		//判读action名称和前缀是否正确
		if("loginLoginAction".equals(actionName)&&"/sys".equals(nameSpace)){
			return invocation.invoke();
		}else{
			//判断session里是否有值
			if(session.containsKey("session_user")){
				return invocation.invoke();
			}else{
				//获取response
				HttpServletResponse response = ServletActionContext.getResponse();
				//获取request
				HttpServletRequest request = ServletActionContext.getRequest();
				//设置编码为utf-8
				response.setContentType("text/html;charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				//提示重新登录，跳回登录界面
				out.write("<script type='text/javascript'>alert('请重新登录');window.top.location.href='"+request.getContextPath()+"/index.jsp' </script>>");
				
				out.flush();
				out.close();
				
				return "login";
			}
		}
	}
}
