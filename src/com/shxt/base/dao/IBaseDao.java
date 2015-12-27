package com.shxt.base.dao;

import java.util.List;

public interface IBaseDao {

	/**
	 * 保存
	 */
	public abstract void save(Object object);
	/**
	 * 修改
	 */
	public abstract void update(Object object);
	/**
	 * 删除     
	 */
	public abstract void delete(Object object);
	/**
	 * 删除  通过主键ID
	 */
	public abstract void delete(Class<?> clazz, Integer id);
	/**
	 * 加载  通过主键ID
	 */
	public abstract Object load(Class<?> clazz, Integer id);
	/**
	 * hql语句查询数据得到一个list链表
	 * 预编译不传参数
	 */
	public abstract List<?> list(String hql);
	/**
	 * hql语句查询数据得到一个list链表
	 * 预编译传一个参数
	 */
	public abstract List<?> list(String hql, Object arg);
	/**
	 * hql语句查询数据得到一个list链表
	 * 预编译传多个参数
	 */
	public abstract List<?> list(String hql, Object[] args);
	/**
	 * hql语句查询数据得到一个数据
	 * 预编译不传参数
	 */
	public abstract Object query(String hql);
	/**
	 * hql语句查询数据得到一个数据
	 * 预编译传一个参数
	 */
	public abstract Object query(String hql, Object arg);
	/**
	 * hql语句查询数据得到一个数据 
	 * 预编译传多个参数
	 */
	public abstract Object query(String hql, Object[] args);
	/**
	 * hql语句修改数据 
	 * 预编译传多个参数
	 */
	public abstract void updateByHql(String hql, Object[] args);
	/**
	 * hql语句修改数据 
	 * 预编译传一个参数
	 */
	public abstract void updateByHql(String hql, Object arg);
	/**
	 * hql语句修改数据 
	 * 预编译不传参数
	 */
	public abstract void updateByHql(String hql);
	/**
	 * hql语句分页查询数据得到类PageBean
	 * 预编译传多个参数
	 */
	public abstract PageBean find(String hql, Object[] args, PageBean pageBean);
	/**
	 * hql语句分页查询数据得到类PageBean
	 * 预编译传一个参数
	 */
	public abstract PageBean find(String hql, Object arg, PageBean pageBean);
	/**
	 * hql语句分页查询数据得到类PageBean
	 * 预编译不传参数
	 */
	public abstract PageBean find(String hql, PageBean pageBean);
	/**
	 * sql语句查询改数据得到list链表 
	 * 预编译传多个参数
	 * isHasHBM 是否有映射文件 有为true 否则为false
	 */
	public abstract List<?> listSQL(String sql, Object[] args, Class<?> clazz,
			boolean isHasHBM);
	/**
	 * sql语句查询改数据得到list链表 
	 * 预编译传一个参数
	 * isHasHBM 是否有映射文件 有为true 否则为false
	 */
	public abstract List<?> listSQL(String sql, Object arg, Class<?> clazz,
			boolean isHasHBM);
	/**
	 * sql语句查询改数据得到list链表 
	 * 预编译不传参数
	 * isHasHBM 是否有映射文件 有为true 否则为false
	 */
	public abstract List<?> listSQL(String sql, Class<?> clazz, boolean isHasHBM);

}