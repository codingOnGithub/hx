package com.hotent.org.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.org.api.model.Group;
import com.hotent.org.api.model.Group.Status;
import com.hotent.org.api.service.GroupService;
import com.hotent.org.persistence.manager.GroupManager;
import com.hotent.org.persistence.model.DefaultGroup;
import com.hotent.org.persistence.query.GroupQuery;
import com.hotent.org.persistence.query.GroupQuery.FindQuery;

@Service
public class DefaultGroupService implements GroupService {

	@Resource
	GroupManager<String, DefaultGroup> groupManager;
	

	@Override
	public Group getByGroupKey(String groupKey) {
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createCriteria().andKeyEqualTo(groupKey).andStatusIn(Group.Status.getValidStatuses());
		List<DefaultGroup> parents = groupManager.queryByCriteria(query);
		if (parents.isEmpty()) {
			return null;
		}
		return parents.get(0);
	}
	
	@Override
	public Group getByGroupId(String groupId) {
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createCriteria().andGroupIdEqualTo(groupId).andStatusIn(Group.Status.getValidStatuses());
		List<DefaultGroup> parents = groupManager.queryByCriteria(query);
		if (parents.isEmpty()) {
			return null;
		}
		return parents.get(0);
	}

	@Override
	public Group getSuperior(String groupId) {
		DefaultGroup group = (DefaultGroup) this.getByGroupId(groupId);
		if (group == null||StringUtils.isEmpty(group.getParentId())) {
			return null;
		}
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createCriteria().andGroupIdEqualTo(group.getParentId()).andStatusIn(Group.Status.getValidStatuses());
		List<DefaultGroup> parents = groupManager.queryByCriteria(query);
		if (parents.isEmpty()) {
			return null;
		}
		return parents.get(0);
	}

	@Override
	public List<Group> queryParents(String groupId) {
		DefaultGroup group = (DefaultGroup) this.getSuperior(groupId);
		if (group == null) {
			return new ArrayList<Group>();
		}
		String[] ids = StringUtils.split(group.getPath(), ".");
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createCriteria().andGroupIdIn(Arrays.asList(ids));
		return (List) groupManager.queryByCriteria(query);

	}

	@Override
	public List<Group> queryBrothers(String groupId) {
		DefaultGroup group = (DefaultGroup) this.getSuperior(groupId);
		if (group == null) {
			return new ArrayList<Group>();
		}
		GroupQuery.FindQuery query = new GroupQuery.FindQuery();
		query.createCriteria().andParentIdEqualTo(group.getGroupId()).andStatusIn(Group.Status.getValidStatuses());
		return (List) groupManager.queryByCriteria(query);
	}

	@Override
	public List<Group> queryChildren(String groupId, Boolean descendants) {
		if (descendants) {
			DefaultGroup group = (DefaultGroup) this.getByGroupId(groupId);
			if (group == null) {
				return new ArrayList<Group>();
			}
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			query.createCriteria().andPathLike(group.getPath()+"%").andGroupIdEqualTo(groupId).andStatusIn(Group.Status.getValidStatuses());
			return (List) groupManager.queryByCriteria(query);
		} else {
			GroupQuery.FindQuery query = new GroupQuery.FindQuery();
			query.createCriteria().andParentIdEqualTo(groupId).andStatusIn(Group.Status.getValidStatuses());
			return (List) groupManager.queryByCriteria(query);
		}
	}

	@Override
	public List<Group> queryByGroupRelation(String groupKey, String relType) {
		return (List) groupManager.queryByGroupRelation(groupKey, relType,Group.Status.getValidStatuses());
	}

	@Override
	public List<Group> queryByUserRelation(String userKey, String relType) {
		return (List) groupManager.queryByUserGroupRelation(userKey, relType,Group.Status.getValidStatuses());
	}

	@Override
	public List<Group> queryGroupByAttributeValue(String key, Object value) {
		return (List) groupManager.queryGroupByAttributeValue(key, value,Group.Status.getValidStatuses());
	}

}
