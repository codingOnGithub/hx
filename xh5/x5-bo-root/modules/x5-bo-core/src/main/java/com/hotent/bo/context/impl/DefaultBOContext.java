package com.hotent.bo.context.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.bo.context.BOContext;
import com.hotent.bo.def.parse.BODefParseFactory;
import com.hotent.bo.instance.BOInstanceFactory;
import com.hotent.bo.persistence.manager.BODefManager;
import com.hotent.bo.persistence.manager.DataObjectManager;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;

@Service
public class DefaultBOContext implements BOContext {
	@Resource
	BODefManager boDefManager;
	
	@Resource
	BOInstanceFactory boinstanceFactory;
	
	@Resource
	DataObjectManager dataObjectManager;
	
	@Resource
	BODefManager bODefManager;
	
	@Resource
	BODefParseFactory bODefParseFactory;
	
	@Override
	public BODef createBODef(String defId) {
		return boDefManager.get(defId);
	}

	@Override
	public DataObject createDataObject(BODef boDef) {
		return boinstanceFactory.createDataObject(boDef);
	}

	@Override
	public DataObject createDataObject(BODef boDef, Object data) {
		return boinstanceFactory.createDataObject(boDef,data);
	}

	@Override
	public DataObject save(DataObject dataObject) {
		return dataObjectManager.saveDataObject(dataObject);
	}

	@Override
	public DataObject getDataObject(String instanceId) {
		return dataObjectManager.get(instanceId);
	}
	
	@Override
	public void deploy(BODef boDef) {
		bODefManager.deploy(boDef);
	}

	@Override
	public void modify(BODef boDef) {
		bODefManager.modify(boDef);
	}
	
	@Override
	public BODef parse(String defxml) {
		return bODefParseFactory.parse(defxml);
	}

	@Override
	public String getBODefXML(BODef boDef) {
		return bODefParseFactory.getBODefXML(boDef);
	}
}
