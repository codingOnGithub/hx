package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.org.api.model.User;
import com.hotent.org.api.model.User.Status;
import com.hotent.org.api.service.UserService;
import com.hotent.org.persistence.manager.UserManager;
import com.hotent.org.persistence.query.UserQuery;

@Service
@SuppressWarnings("unchecked")
public class DefaultUserService implements UserService{
	@Resource
	UserManager userManager;
	
	@Override
	public User getById(String id) {
		UserQuery.FindQuery query = new UserQuery.FindQuery();
		query.createCriteria().andUserIdEqualTo(id).andStatusIn(Status.getValidStatuses());
		return getOne(userManager.queryByCriteria(query));
	}
	
	@Override
	public User getByAccount(String account) {
		UserQuery.FindQuery query = new UserQuery.FindQuery();
		query.createCriteria().andAccountEqualTo(account).andStatusIn(Status.getValidStatuses());
		return getOne(userManager.queryByCriteria(query));
		
	}

	
	@Override
	public List<User> queryByUserRelation(String userKey, String relType) {
		return (List) userManager.queryByUserRelation(userKey, relType,Status.getValidStatuses());
	}

	@Override
	public List<User> queryByGroupRelation(String groupId, String relType) {
		return (List) userManager.queryByUserGroupRelation(groupId, relType,Status.getValidStatuses());
	}

	@Override
	public List<User> queryUserByAttributeValue(String key, Object value) {
		return (List) userManager.queryByAttributeValue(key, value,Status.getValidStatuses());
	}

	
	private <T> T getOne(List<T> list){
		if(list==null||list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
	
}
