package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.service.AttributeService;
import com.hotent.org.persistence.manager.AttributeManager;
import com.hotent.org.persistence.query.AttributeQuery;

@Service
public class DefaultAttributeService implements AttributeService {
	@Resource
	AttributeManager attributeManager;

	@Override
	public Attribute getByAttrKey(String attrKey) {
		AttributeQuery.FindQuery findQuery = new AttributeQuery.FindQuery();
		findQuery.createCriteria().andAttrKeyEqualTo(attrKey).andStatusIn(Attribute.Status.getValidStatuses());
		List<Attribute> attrs = (List) attributeManager.queryByCriteria(findQuery);
		if (attrs.isEmpty()) {
			return null;
		} else {
			return attrs.get(0);
		}
	}

	@Override
	public List<Attribute> queryAll() {
		AttributeQuery.FindQuery findQuery = new AttributeQuery.FindQuery();
		findQuery.createCriteria().andStatusIn(Attribute.Status.getValidStatuses());
		return (List) attributeManager.queryByCriteria(findQuery);
	}

	@Override
	public List<Attribute> queryByBelongType(String belongType) {
		AttributeQuery.FindQuery findQuery = new AttributeQuery.FindQuery();
		findQuery.createCriteria().andBelongTypeEqualTo(belongType).andStatusIn(Attribute.Status.getValidStatuses());
		return (List) attributeManager.queryByCriteria(findQuery);
	}

	@Override
	public Attribute getByAttrId(String attrId) {
		AttributeQuery.FindQuery findQuery = new AttributeQuery.FindQuery();
		findQuery.createCriteria().andAttrIdEqualTo(attrId).andStatusIn(Attribute.Status.getValidStatuses());
		List<Attribute> attrs = (List) attributeManager.queryByCriteria(findQuery);
		if (attrs.isEmpty()) {
			return null;
		} else {
			return attrs.get(0);
		}
	}

	@Override
	public List<Attribute> queryAll(Page page) {
		AttributeQuery.FindQuery findQuery = new AttributeQuery.FindQuery();
		findQuery.createCriteria().andStatusIn(Attribute.Status.getValidStatuses());
		return (List) attributeManager.queryByCriteria(findQuery,page);
	}

}
