package com.hotent.core.page;

import java.util.Locale;

import com.hotent.core.util.ContextUtil;

/**
 * 分页语言
 * 
 * @author zxh
 * 
 */
public class PageLanguage {
	/**
	 * 每页记录
	 */
	private String perPageSize = "每页记录";
	/**
	 * 首页
	 */
	private String firstPage = "首页";
	/**
	 * 上一页
	 */
	private String prevPage = "上一页";
	/**
	 * 下一页
	 */
	private String nextPage = "下一页";
	/**
	 * 尾页
	 */
	private String lastPage = "尾页";
	/**
	 * 跳转
	 */
	private String go = "跳转";
	/**
	 * 刷新
	 */
	private String refresh = "刷新";
	/**
	 * 显示记录从{0}到{1}
	 */
	private String displayPage = "显示记录从{0}到{1}";
	/**
	 * 总数 {0}条
	 */
	private String totalCount = "总数 {0}条";
	
	private String showPage = "当前没有记录";

	/**
	 * 获取分页语言
	 * 
	 * @param p
	 * @return
	 */
	public static PageLanguage getPageLanguage(PageBean pb) {
		Locale locale = ContextUtil.getLocale();
		PageLanguage pageLanguage = new PageLanguage();
		// 每页记录
		pageLanguage.setPerPageSize(ContextUtil.getMessagesL("paging.perPageSize",locale));
		// 首页
		pageLanguage.setFirstPage(ContextUtil.getMessagesL("paging.firstPage",locale));
		// 上一页
		pageLanguage.setPrevPage(ContextUtil.getMessagesL("paging.prevPage",locale));
		// 下一页
		pageLanguage.setNextPage(ContextUtil.getMessagesL("paging.nextPage",locale));
		// 尾页
		pageLanguage.setLastPage(ContextUtil.getMessagesL("paging.lastPage",locale));
		// 跳转
		pageLanguage.setGo(ContextUtil.getMessagesL("paging.go",locale));
		// 刷新
		pageLanguage.setRefresh(ContextUtil.getMessagesL("paging.refresh",locale));
		
		int count = pb.getTotalCount();
		// 显示记录从{0}到{1}
		int first = count > 0 ? pb.getFirst() + 1 : 0;
		Object[] args = { first, pb.getLast() };
		String displayPage = ContextUtil.getMessages("paging.displayPage",args,locale);
		pageLanguage.setDisplayPage(displayPage);
		// 总数 {0}条
		Object[] args1 = { count };
		String totalCount =  ContextUtil.getMessages("paging.totalCount",args1,locale);
		
		pageLanguage.setTotalCount(totalCount);
		if( count > 0 ){
			pageLanguage.setShowPage(displayPage+","+totalCount+".");
		}else{
			pageLanguage.setShowPage(ContextUtil.getMessagesL("paging.noRecords",locale));
		}

		return pageLanguage;
	}

	/**
	 * @return the perPageSize
	 */
	public String getPerPageSize() {
		return perPageSize;
	}

	/**
	 * @param perPageSize
	 *            the perPageSize to set
	 */
	public void setPerPageSize(String perPageSize) {
		this.perPageSize = perPageSize;
	}

	/**
	 * @return the firstPage
	 */
	public String getFirstPage() {
		return firstPage;
	}

	/**
	 * @param firstPage
	 *            the firstPage to set
	 */
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}

	/**
	 * @return the prevPage
	 */
	public String getPrevPage() {
		return prevPage;
	}

	/**
	 * @param prevPage
	 *            the prevPage to set
	 */
	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
	}

	/**
	 * @return the nextPage
	 */
	public String getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the lastPage
	 */
	public String getLastPage() {
		return lastPage;
	}

	/**
	 * @param lastPage
	 *            the lastPage to set
	 */
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the go
	 */
	public String getGo() {
		return go;
	}

	/**
	 * @param go
	 *            the go to set
	 */
	public void setGo(String go) {
		this.go = go;
	}

	/**
	 * @return the refresh
	 */
	public String getRefresh() {
		return refresh;
	}

	/**
	 * @param refresh
	 *            the refresh to set
	 */
	public void setRefresh(String refresh) {
		this.refresh = refresh;
	}

	/**
	 * @return the displayPage
	 */
	public String getDisplayPage() {
		return displayPage;
	}

	/**
	 * @param displayPage
	 *            the displayPage to set
	 */
	public void setDisplayPage(String displayPage) {
		this.displayPage = displayPage;
	}

	/**
	 * @return the totalCount
	 */
	public String getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the showPage
	 */
	public String getShowPage() {
		return showPage;
	}

	/**
	 * @param showPage the showPage to set
	 */
	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}

}
