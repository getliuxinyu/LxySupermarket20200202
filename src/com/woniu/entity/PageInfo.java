package com.woniu.entity;

import java.util.List;

/**
 * 页面信息
 * 	属性：总条数
 * 		页面显示条数
 * 		当前页码
 * 		总页码
 * 		当前页面数据
 * @author Administrator
 *
 */
public class PageInfo<T> {
	/**总条数 */
	private int totalCount;
	/**当前页面显示条数 */
	private int pageSize;
	/**当前页码*/
	private int currentPage; 
	/**总页码,知道总条数和当前页面显示条数就可以算出。不需要提供set方法*/
	private int totalPages; 
	/**存储查询结果的集合*/
	private List<T> li;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getTotalPages() {
		
		this.totalPages = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
		return totalPages;
	}
	public List<T> getLi() {
		return li;
	}
	public void setLi(List<T> li) {
		this.li = li;
	}
	
	
}
