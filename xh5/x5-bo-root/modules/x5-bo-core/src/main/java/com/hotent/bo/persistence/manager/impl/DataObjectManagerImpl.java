package com.hotent.bo.persistence.manager.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.api.Dao;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bo.exception.BOBaseException;
import com.hotent.bo.instance.BOInstanceFactory;
import com.hotent.bo.persistence.dao.DataObjectDao;
import com.hotent.bo.persistence.manager.BODefManager;
import com.hotent.bo.persistence.manager.DataObjectManager;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;

@Service("dataObjectManager")
public class DataObjectManagerImpl extends AbstractManagerImpl<String, DataObject> implements DataObjectManager{
	@Resource
	DataObjectDao dataObjectDao;
	@Resource
	IdGenerator idGenerator;
	@Resource
	BODefManager boDefManager;
	@Resource
	BOInstanceFactory boInstanceFactory;
	
	@Override
	protected Dao<String, DataObject> getDao() {
		return dataObjectDao;
	}
	
	@Override
	public DataObject get(String arg0) {
		DataObject dataObject = super.get(arg0);
		if(BeanUtils.isNotEmpty(dataObject)){
			BODef boDef = boDefManager.loadBODef(dataObject.getDefId());
			//根据BODef的类型获取对应类型的DataObject
			DataObject newDataObject = boInstanceFactory.getNewDataObject(boDef);
			newDataObject.setId(dataObject.getId());
			newDataObject.setBoDef(boDef);
			newDataObject.setInstData(dataObject.getInstData());
			newDataObject.setCreateTime(dataObject.getCreateTime());
			newDataObject.setDefId(dataObject.getDefId());
			return newDataObject;
		}
		return dataObject;
	}
	
	public DataObject saveDataObject(DataObject dataObject) {
		String defId = dataObject.getDefId();
		if(BeanUtils.isEmpty(defId)){
			defId = dataObject.getBoDef().getId();
			if(BeanUtils.isEmpty(defId)){
				throw new BOBaseException("保存DataObject到数据库之前需要发布对应的BoDef");
			}
			else{
				dataObject.setDefId(defId);
			}
		}
		
		String instanceId = dataObject.getId();
		if(BeanUtils.isEmpty(instanceId)){
			String id = idGenerator.getSuid();
			dataObject.setId(id);
			dataObject.setCreateTime(new Date());
			dataObjectDao.create(dataObject);
		}
		else{
			dataObjectDao.update(dataObject);
		}
		return dataObject;
	}
}
