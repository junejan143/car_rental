package com.shxt.base.dao;

import java.util.List;

public class PageBean {
	/**当前页*/
	private Integer pageNow = 1;
	/**每页显示条数*/
	private Integer pageSize = 10;
	/**总记录数*/
	private Long totalCount;
	/**总页数*/
	private Long totalPage;
	/**结果集*/
	private List<?> datas;
	/**
	 * @描述: 获取当前页
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:09:19
	 * @参数:@return 
	 * @返回值：Integer
	 */
	public Integer getPageNow() {
		return pageNow;
	}
	/**
	 * @描述: 设置当前页
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:09:19
	 * @参数:@return 
	 * @返回值：Integer
	 */
	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}
	/**
	 * @描述: 获取每页显示条数
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:10:26
	 * @参数:@return 
	 * @返回值：Integer
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @描述: 设置每页显示条数
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:10:26
	 * @参数:@return 
	 * @返回值：Integer
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @描述: 获取总记录数
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:11:23
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long getTotalCount() {
		return totalCount;
	}
	/**
	 * @描述: 设置总记录数
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:11:23
	 * @参数:@return 
	 * @返回值：Long
	 */
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * @描述: 获取总页数
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:12:23
	 * @参数:@return 
	 * @返回值：Long
	 */
	public Long getTotalPage() {
		return totalPage;
	}
	/**
	 * @描述: 设置总页数
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:12:23
	 * @参数:@return 
	 * @返回值：Long
	 */
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	/**
	 * @描述: 获取返回结果集
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:12:52
	 * @参数:@return 
	 * @返回值：List<?>
	 */
	public List<?> getDatas() {
		return datas;
	}
	/**
	 * @描述: 设置返回结果集
	 * @作者: 徐新伟
	 * @时间:2015-2-24 下午01:12:52
	 * @参数:@return 
	 * @返回值：List<?>
	 */
	public void setDatas(List<?> datas) {
		this.datas = datas;
	}

}
