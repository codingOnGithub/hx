package com.hotent.org.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.org.api.model.Attribute;
import com.hotent.org.api.service.AttributeService;
import com.hotent.org.persistence.manager.AttributeManager;

@Service
public class DefaultAttributeService implements AttributeService{
	@Resource
	AttributeManager attributeManager;
	@Override
	public Attribute getByAttrKey(String attrKey) {
		return attributeManager.getByAttrKey(attrKey);
	}

	@Override
	public List<Attribute> queryAll() {
		List<?> list = attributeManager.getAll();
		return (List<Attribute>) list;
	}

	@Override
	public List<Attribute> queryByBelongType(String belongType) {
		List<?> list = attributeManager.queryByBelongType(belongType);
		return (List<Attribute>) list;
	}

}
