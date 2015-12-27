package com.shxt.base.utils;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class CreateTable {

	/**
	 * @param args
	 * 建表类
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration();
		
		cfg.configure();
		
		try {
			SchemaExport export = new SchemaExport(cfg);
			//script 是否在控制台显示SQL语句
			//export 是否在数据中执行SQL脚本语句
			export.create(true, true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
