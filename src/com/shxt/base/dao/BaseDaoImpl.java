package com.shxt.base.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.shxt.base.utils.AliasToBeanResultTransformer;

/**
 * BaseDao的封装，用于处理数据库
 * 
 * @author 徐新伟
 * 
 */
public class BaseDaoImpl implements IBaseDao {
	
	//创建session工厂
	private SessionFactory sessionFactory;
	//注入session工厂
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	
	}
	//获取session工厂
	public Session getSession() {
		
		return this.sessionFactory.getCurrentSession();
	
	}
	/**
	 * 保存
	 */
	public void save(Object object) {
		
		getSession().save(object);
	
	}
	/**
	 * 修改
	 */
	public void update(Object object) {
	
		getSession().update(object);
	
	}
	/**
	 * 删除     
	 */
	public void delete(Object object) {
	
		getSession().delete(object);
	
	}
	/**
	 * 删除  通过主键ID
	 */
	public void delete(Class<?> clazz, Integer id) {
	
		getSession().delete(getSession().load(clazz, id));
	
	}
	/**
	 * 加载  通过主键ID
	 */
	public Object load(Class<?> clazz, Integer id) {

		return getSession().load(clazz, id);
	
	}
	/**
	 * 调用输入多个参数的方法，不参数
	 */
	public List<?> list(String hql) {
	
		return this.list(hql, null);
	
	}
	/**
	 * 调用输入多个参数的方法，传入一个参数
	 */
	public List<?> list(String hql, Object arg) {
	
		return this.list(hql, new Object[] { arg });
	
	}
	/**
	 * hql语句查询数据得到一个list链表
	 * 预编译传多个参数
	 */
	public List<?> list(String hql, Object[] args) {
		//创建一个query
		Query query = getSession().createQuery(hql);
		//判断是否有参数
		if (args != null && args.length > 0) {
			//遍历输入参数
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//执行方法，返回结果
		return query.list();

	}
	/**
	 * 调用输入多个参数的方法，不输入数
	 */
	public Object query(String hql) {
		return this.query(hql, null);
	}
	/**
	 * 调用输入多个参数的方法，传入一个参数
	 */
	public Object query(String hql, Object arg) {

		return this.query(hql, new Object[] { arg });
	}
	/**
	 * hql语句查询数据得到一个数据 
	 * 预编译传多个参数
	 */
	public Object query(String hql, Object[] args) {
		//创建一个query
		Query query = getSession().createQuery(hql);// hql预处理模式 ? from user ? ?
													// ? ?
		//判断是否有参数
		if (args != null && args.length > 0) {
			//遍历输入参数
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//执行方法，返回结果
		return query.uniqueResult();

	}
	/**
	 * hql语句修改数据 
	 * 预编译传多个参数
	 */
	public void updateByHql(String hql, Object[] args) {
		//创建一个query
		Query query = getSession().createQuery(hql);// hql预处理模式 ? from user ? ?
													// ? ?
		//判断是否有参数
		if (args != null && args.length > 0) {
			//遍历输入参数
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//执行方法
		query.executeUpdate();

	}
	/**
	 * 调用输入多个参数的方法，传入一个参数
	 */
	public void updateByHql(String hql, Object arg) {
	
		this.updateByHql(hql, new Object[] { arg });
	
	}
	/**
	 * 调用输入多个参数的方法，不输入数
	 */
	public void updateByHql(String hql) {
	
		this.updateByHql(hql, null);
	
	}
	/**
	 * hql语句分页查询数据得到类PageBean
	 * 预编译传多个参数
	 */
	public PageBean find(String hql, Object[] args, PageBean pageBean) {
		//创建一个query
		Query pager_query = getSession().createQuery(hql);
		//判断是否有参数
		if (args != null && args.length > 0) {
			//遍历输入参数
			for (int i = 0; i < args.length; i++) {
				pager_query.setParameter(i, args[i]);
			}
		}
		// 设置分页
		pager_query.setFirstResult(
				(pageBean.getPageNow() - 1) * pageBean.getPageSize())
				.setMaxResults(pageBean.getPageSize());

		// 需要把数据存储起来
		// List<?> tempList = pager_query.list();
		pageBean.setDatas(pager_query.list());
		//获取hql拼接语句
		String count_hql = this.getCountHql(hql);
		//创建一个query
		Query count_query = getSession().createQuery(count_hql);
		//判断是否有参数
		if (args != null && args.length > 0) {
			//遍历输入参数
			for (int i = 0; i < args.length; i++) {
				count_query.setParameter(i, args[i]);
			}
		}
		//总记录数
		Long totalCount = (Long) count_query.uniqueResult();

		// 总记录数保存到PageBean当中
		pageBean.setTotalCount(totalCount);
		// 总页数
		Long totalPage = totalCount % pageBean.getPageSize() == 0 ? totalCount
				/ pageBean.getPageSize() : totalCount / pageBean.getPageSize()
				+ 1;
		// 总页数保存到PageBean当中
		pageBean.setTotalPage(totalPage);

		return pageBean;
	}
	/**
	 * 调用输入多个参数的方法，传入一个参数
	 */
	public PageBean find(String hql, Object arg, PageBean pageBean) {

		return this.find(hql, new Object[] { arg }, pageBean);
	
	}
	/**
	 * 调用输入多个参数的方法，不输入数
	 */
	public PageBean find(String hql, PageBean pageBean) {
	
		return this.find(hql, null, pageBean);
	
	}
	/**
	 * 
	 * @描述: 变更hql语句为统一格式
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:15:56
	 * @参数:@param hql
	 * @参数:@return 
	 * @返回值：String
	 */
	private String getCountHql(String hql) {// select u from User u;
	
		String count_hql = hql.substring(0, hql.indexOf("from"));

		if (count_hql.trim().equals("")) {
			hql = "select count(*) " + hql;
		} else {
			hql.replace(count_hql, " select count(*) ");
		}
		return hql;
	}
	/**
	 * sql语句查询数据得到list
	 * 预编译传多个参数
	 */
	public List<?> listSQL(String sql, Object[] args, Class<?> clazz,
			boolean isHasHBM) {

		SQLQuery query = getSession().createSQLQuery(sql);

		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}

		if (isHasHBM) {
			query.addEntity(clazz);
		} else {
			query.setResultTransformer(new AliasToBeanResultTransformer(clazz));
		}

		return query.list();

	}
	/**
	 * sql语句查询数据得到一个list
	 * 调用输入多个参数的方法，传入一个参数
	 */
	public List<?> listSQL(String sql, Object arg, Class<?> clazz,
			boolean isHasHBM) {
		
		return this.listSQL(sql, new Object[] { arg }, clazz, isHasHBM);
	
	}
	/**
	 * sql语句查询数据得到一个list
	 * 调用输入多个参数的方法，不传入参数
	 */
	public List<?> listSQL(String sql, Class<?> clazz, boolean isHasHBM) {
		
		return this.listSQL(sql, null, clazz, isHasHBM);
	
	}

}