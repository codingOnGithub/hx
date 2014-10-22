package com.hotent.org.persistence.model;

import java.util.List;

import com.hotent.org.api.model.GroupTree;

/**
 * 对象功能:树形用户组对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2013-12-11 16:55:42
 */
public class DefaultGroupTree extends DefaultGroup implements GroupTree{
	protected GroupTree parent;				/*父节点*/
	protected List<GroupTree> children;		/*子节点*/
	
	public void setParent(GroupTree parent){
		this.parent = parent;
	}
	
	public GroupTree getParent(){
		return parent;
	}
	
	public void setChildren(List<GroupTree> children){
		this.children = children;
	}
	
	public List<GroupTree> getChildren(){
		return children;
	}
}