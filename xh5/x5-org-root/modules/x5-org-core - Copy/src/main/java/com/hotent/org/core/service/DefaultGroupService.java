package com.hotent.org.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.org.api.model.Group;
import com.hotent.org.api.service.GroupService;
import com.hotent.org.persistence.manager.GroupManager;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.GroupQuery;

@Service
public class DefaultGroupService implements GroupService{
	
	@Resource
	GroupManager<String, DefaultGroup> groupManager;
	
	@Override
	public Group getByGroupKey(String groupKey) {
		return groupManager.getByKey(groupKey);
	}

	@Override
	public Group getSuperior(String groupKey) {
		DefaultGroup group = groupManager.get(groupKey);
		if(group==null){
			return null;
		}
		GroupQuery query = new GroupQuery();
		query.createCriteria().andGroupIdEqualTo(group.getKey());
		List<DefaultGroup> parents = groupManager.queryByCriteria(query);
		if(parents.isEmpty()){
			return null;
		}
		return parents.get(0);
	}

	@Override
	public List<Group> queryParents(String groupKey) {
		DefaultGroup group = groupManager.get(groupKey);
		if(group==null){
			return new ArrayList<Group>();
		}
		String [] ids = StringUtils.split(group.getPath(),".");
		GroupQuery query = new GroupQuery();
		query.createCriteria().andGroupIdIn(Arrays.asList(ids));
		return  (List)groupManager.queryByCriteria(query);
		
	}

	@Override
	public List<Group> queryBrothers(String groupKey) {
		DefaultGroup group = groupManager.get(groupKey);
		if(group==null){
			return new ArrayList<Group>();
		}
		GroupQuery query = new GroupQuery();
		query.createCriteria().andParentIdEqualTo(group.getGroupId());
		return  (List)groupManager.queryByCriteria(query);
	}

	@Override
	public List<Group> queryChildren(String groupKey, Boolean descendants) {
		if(descendants){
			DefaultGroup group = groupManager.get(groupKey);
			if(group==null){
				return new ArrayList<Group>();
			}
			GroupQuery query = new GroupQuery();
			query.createCriteria().andPathLike(group.getPath());
			return  (List)groupManager.queryByCriteria(query);
		}else{
			GroupQuery query = new GroupQuery();
			query.createCriteria().andParentIdEqualTo(groupKey);
			return  (List)groupManager.queryByCriteria(query);
		}
	}

	@Override
	public List<Group> queryByGroupRelation(String groupKey, String relType) {
		return (List)groupManager.queryByGroupRelation(groupKey, relType);
	}

	
	@Override
	public List<Group> queryByUserRelation(String userKey, String relType) {
		return (List)groupManager.queryByUserGroupRelation(userKey, relType);
	}

	@Override
	public List<Group> queryGroupByAttributeValue(String key, Object value) {
		return (List)groupManager.queryGroupByAttributeValue(key, value);
	}
	
}
