package com.hotent.org.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.org.persistence.dao.GroupTreeDao;
import com.hotent.org.persistence.model.DefaultGroupTree;

@Repository
public class GroupTreeDaoImpl<PK extends Serializable,T extends DefaultGroupTree> extends GroupDaoImpl<String,DefaultGroupTree> implements GroupTreeDao<String,DefaultGroupTree>{
    @Override
    public String getNamespace() {
        return DefaultGroupTree.class.getName();
    }
    
    @Override
	public List<DefaultGroupTree> queryChildrenByPath(String path) {
		return this.getByKey("queryChildrenByPath", this.buildMap("path", path + "%"));
	}

	@Override
	public List<DefaultGroupTree> queryByParentId(String parentId) {
		return this.getByKey("queryByParentId", buildMap("parentId", parentId));
	}
}
