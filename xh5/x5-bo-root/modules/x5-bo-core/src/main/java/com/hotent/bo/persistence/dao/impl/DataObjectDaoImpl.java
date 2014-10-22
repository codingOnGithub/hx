package com.hotent.bo.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bo.persistence.dao.DataObjectDao;
import com.hotent.bo.persistence.model.DataObject;
import com.hotent.bo.persistence.model.impl.JSONDataObject;

@Repository
public class DataObjectDaoImpl extends MyBatisDaoImpl<String, DataObject> implements DataObjectDao{

    @Override
    public String getNamespace() {
        return JSONDataObject.class.getName();
    }
	
}
