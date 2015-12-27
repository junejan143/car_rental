package com.shxt.base.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * @描述:hibernate的工具类
 * @作者:徐新伟
 * @版本:1.0
 * @版权所有:凌云6
 * @项目名称:汽车租赁
 * @时间 2015-3-3 上午11:15:44
 */
public class HibernateUtils {
	//定义配置
	private static Configuration cfg = null;
	//定义session工厂
	private static SessionFactory factory = null;
	
	static{
		//获取配置
		cfg = new Configuration();
		//读取配置文件
		cfg.configure();
		//获取session工厂
		factory = cfg.buildSessionFactory();
	}
	
	public static  Session getSession(){
		//打开工厂
		return factory.openSession();
	}
	
	public static void closeSession(Session session){
		//关闭工厂
		if(session!=null)session.close();
	}

}
