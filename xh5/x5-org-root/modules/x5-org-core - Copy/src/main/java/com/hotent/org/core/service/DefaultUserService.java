package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.org.api.model.User;
import com.hotent.org.api.service.UserService;
import com.hotent.org.persistence.manager.UserManager;

@Service
@SuppressWarnings("unchecked")
public class DefaultUserService implements UserService{
	@Resource
	UserManager userManager;
	@Override
	public User getByKey(String userKey) {
		return userManager.getByAccount(userKey);
	}

	
	@Override
	public List<User> queryByUserRelation(String userKey, String relType) {
		return (List) userManager.queryByUserRelation(userKey, relType);
	}

	@Override
	public List<User> queryByGroupRelation(String groupKey, String relType) {
		return (List) userManager.queryByUserGroupRelation(groupKey, relType);
	}

	@Override
	public List<User> queryUserByAttributeValue(String key, Object value) {
		return (List) userManager.queryUserByAttributeValue(key, value);
	}

}
