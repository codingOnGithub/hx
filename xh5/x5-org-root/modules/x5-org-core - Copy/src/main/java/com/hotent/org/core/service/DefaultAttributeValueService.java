package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.org.api.model.AttributeValue;
import com.hotent.org.api.service.AttributeValueService;
import com.hotent.org.persistence.manager.AttributeValueManager;
import com.hotent.org.persistence.query.AttributeValueQuery;

@Service
public class DefaultAttributeValueService implements AttributeValueService{
	@Resource
	AttributeValueManager attributeValueManager;
	
	@Override
	public List<AttributeValue> queryByUserKey(String userKey) {
		AttributeValueQuery query = new AttributeValueQuery();
		query.createCriteria().andUserIdEqualTo(userKey);
		List<?> list =  attributeValueManager.queryByCriteria(query);
		return (List<AttributeValue>) list;
	}

	@Override
	public List<AttributeValue> queryByGroupKey(String groupKey) {
		AttributeValueQuery query = new AttributeValueQuery();
		query.createCriteria().andGroupIdEqualTo(groupKey);
		return (List)attributeValueManager.queryByCriteria(query);
	}

}
