package com.hotent.bo.persistence.manager;

import com.hotent.base.manager.api.Manager;
import com.hotent.bo.persistence.model.DataObject;

public interface DataObjectManager extends Manager<String, DataObject>{
	DataObject saveDataObject(DataObject dataObject);
}
