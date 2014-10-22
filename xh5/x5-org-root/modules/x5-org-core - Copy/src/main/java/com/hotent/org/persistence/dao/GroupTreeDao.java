package com.hotent.org.persistence.dao;

import java.io.Serializable;
import java.util.List;
import com.hotent.org.persistence.model.DefaultGroupTree;

public interface GroupTreeDao<PK extends Serializable,T extends DefaultGroupTree> extends GroupDao<PK,T>{
	/**
	 * 通过路径获取子节点
	 * @param path 路径
	 * @return
	 */
	public List<DefaultGroupTree> queryChildrenByPath(String path);
	
	/**
	 * 通过父节点ID获取用户组
	 * @param parentId 父节点ID
	 * @return
	 */
	public List<DefaultGroupTree> queryByParentId(String parentId);
}
