package com.hotent.bo.instance.adapter;

import com.hotent.bo.persistence.model.DataObject;


public interface BOAdapter {
	/**
	 * 返回适配器处理的数据类型
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getDataType();
	/**
	 * 装载数据
	 * @param data 数据
	 * @param dataObject 业务对象实例 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void loadData(Object data,DataObject dataObject);
	/**
	 * 从业务对象中解析数据
	 * @param dataObject 业务对象实例
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String parseData(DataObject dataObject);
}
