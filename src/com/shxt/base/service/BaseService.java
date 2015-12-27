package com.shxt.base.service;

import com.shxt.base.dao.IBaseDao;

public class BaseService {
	//定义baseDao接口
	protected IBaseDao baseDao;
	//用于接口注入
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
