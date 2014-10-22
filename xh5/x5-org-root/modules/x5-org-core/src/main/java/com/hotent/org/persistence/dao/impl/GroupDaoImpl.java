package com.hotent.org.persistence.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.Page;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.org.persistence.dao.GroupDao;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.GroupQuery.FindQuery;

@SuppressWarnings("unchecked")
@Repository
public class GroupDaoImpl<PK extends Serializable,T extends DefaultGroup> extends MyBatisDaoImpl<PK,T> implements GroupDao<PK,T>{

	public DefaultGroup getByKey(String groupKey) {
		return getUnique("getByGroupKey", buildMap("groupKey", groupKey));
	}

	@Override
	public String getNamespace() {
		return DefaultGroup.class.getName();
	}

	@Override
	public void deleteByGroupKey(String groupKey) {
		deleteByKey("removeByGroupKey", buildMap("groupKey", groupKey));
	}

//	@Override
//	public List<DefaultGroup> queryByDimId(String dimId) {
//		return (List<DefaultGroup>)getByKey("queryByDimId", buildMap("dimId", dimId));
//	}
//
//	@Override
//	public List<DefaultGroup> queryByUserId(String userId, boolean isEnabled) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", userId);
//		map.put("isEnabled", isEnabled ? 'Y' : 'N');
//		return (List<DefaultGroup>) getByKey("queryByUserId", map);
//	}
//
//	@Override
//	public List<DefaultGroup> queryByUserId(String userId, boolean isEnabled, Page page) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userId", userId);
//		map.put("isEnabled", isEnabled ? 'Y' : 'N');
//		return (List<DefaultGroup>)getByKey("queryByUserId", map, page);
//	}
//
//	@Override
//	public List queryByGroupRelation(String groupKey, String relType) {
//		Map<String,Object> params=new HashMap<String, Object>();
//		params.put("groupId", groupKey);
//		params.put("relType", relType);
//		return this.getByKey("queryByGroupRelation", params);
//	}
//
//	@Override
//	public List queryByUserRelation(String userKey, String relType) {
//		Map<String,Object> params=new HashMap<String, Object>();
//		params.put("userId", userKey);
//		params.put("relType", relType);
//		return this.getByKey("queryByUserRelation", params);
//	}
//
//	@Override
//	public List queryGroupByAttributeValue(String key, Object value) {
//		return null;
//	}
//	
//	

	@Override
	public List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query) {
		return (List<DefaultGroup>) this.getByKey("queryByCriteria", query);
	}

	@Override
	public List<DefaultGroup> queryByCriteria(GroupQuery.FindQuery query, Page page) {
		return (List<DefaultGroup>) this.getByKey("queryByCriteria", query,page);
	}
	

	@Override
	public long queryCountByCriteria(GroupQuery.FindQuery query) {
		return (Long) this.getOne("queryCountByCriteria", query);
	}
	
	@Override
	public void updateByUpdateQuery(GroupQuery.UpdateQuery uQuery){
		this.updateByKey("updateByUpdateQuery",uQuery);
	}

	
	@Override
	public void deleteByDeleteQuery(GroupQuery.DeleteQuery dQuery) {
		this.deleteByKey("deleteByDeleteQuery",dQuery);
	}
}
