package com.hotent.web.json;

import java.util.List;

import com.hotent.base.db.mybatis.domain.PageList;

/**
 * 
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-17-下午2:51:46
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class PageJson {
	//总记录数
	private Integer total=0;
	//行记录
	private List rows=null;
	
	public PageJson() {
		
	}
	
	public PageJson(List rows,Integer total){
		this.rows=rows;this.total=total;
		
	}
	
	public PageJson(PageList pageList){
		this.rows=pageList;
		this.total=pageList.getPageResult().getTotalCount();
	}
	
	public PageJson(List arrayList){
		
		this.rows=arrayList;
		this.total=arrayList.size();
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public List getRows() {
		return rows;
	}
	
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
