package com.shxt.base.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 * 文件上传工具类
 * @author 韩老师
 * @ClassName: FileUpload
 * @Version 1.0
 * @ModifiedBy
 * @Copyright 四海兴唐
 * @date 2014-6-11 下午06:33:13
 * @description
 */
public class FileUpload {
	
	/**获取上传文件的名称
	 * @author 韩老师
	 * @title: getFileName
	 * @date 2013-1-13 上午09:59:28
	 * @param fileName
	 * @return String
	 */
	private static String getFileName(String fileName){
//		System.out.println("上传文件名称:" + fileName);
		
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = df.format(new Date());
		int random = new Random().nextInt(10000);//作业：随机数范围 1000-9999
//		int index = fileName.lastIndexOf(".");
//		System.out.println(index);
		String type = fileName.substring(fileName.lastIndexOf("."));
//		System.out.println(type);
//		System.out.println("保存的文件名称:" + dateStr + random);
		return dateStr + random + type;
	}
	
	/**
	 * 上传文件
	 * @author 韩老师
	 * @title: upload
	 * @date 2013-2-26 下午03:44:35
	 * @param file 上传的文件
	 * @param fileName 上传的文件名称
	 * @return 上传到服务器的文件的名称
	 * @throws IOException String
	 */
	public static String upload(File file,String fileName) throws IOException {
		//获取此项目在服务器上的路径
		String path = ServletActionContext.getServletContext().getRealPath("/photo");
		
		//获取最终传到服务器上的文件的名称
		String newName = FileUpload.getFileName(fileName);
		
		File newFile = new File(path, newName);
		
		FileUtils.copyFile(file, newFile);
		
		return newName;
	}
}
