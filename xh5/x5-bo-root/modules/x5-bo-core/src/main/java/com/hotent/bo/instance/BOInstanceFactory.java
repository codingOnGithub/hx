package com.hotent.bo.instance;

import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;


public interface BOInstanceFactory {
	/**
	 * 根据BODef中描述的类型(dataType)返回对应的DataObject 
	 * @param boDef
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject getNewDataObject(BODef boDef);
	/**
	 * 通过BODef初始化DataObject对象
	 * 
	 * @param boDef 业务对象定义
	 * @return 
	 * DataObject 业务对象实例
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject createDataObject(BODef boDef);
	
	/**
	 * 通过BODef初始化DataObject对象，并将数据data装载到对象中 
	 * @param boDef 业务对象定义
	 * @param data 数据
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject createDataObject(BODef boDef, Object data);
	
	/**
	 * 通过BODef初始化DataObject对象，并将数据data装载到对象中 ,并在构建DataObject对象时传入该对象属性名称
	 * @param boDef 业务对象定义
	 * @param data 数据
	 * @param attrName 该Dataobject对应的属性名称
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject createDataObject(BODef boDef,Object data,String attrName);
}
