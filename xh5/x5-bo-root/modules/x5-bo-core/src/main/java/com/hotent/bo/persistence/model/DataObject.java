package com.hotent.bo.persistence.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface DataObject extends Serializable{
	/**
	 * 获取实例ID 
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getId();
	
	/**
	 * 设置实例ID
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setId(String id);
	
	/**
	 * 获取BoDef的ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getDefId();
	
	/**
	 * 设置BoDef的ID
	 * @param defId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setDefId(String defId);
	
	/**
	 * 获取BODef
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	BODef getBoDef();
	
	/**
	 * 设置BODef
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setBoDef(BODef boDef);
	/**
	 * 设置数据
	 * @param instData 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setInstData(String instData);
	
	/**
	 * 获取数据
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getInstData();
	
	/**
	 * 设置创建时间
	 * @param createTime 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setCreateTime(Date createTime);
	
	/**
	 * 获取创建时间
	 * @return 
	 * Date
	 * @exception 
	 * @since  1.0.0
	 */
	Date getCreateTime();
	/**
	 * 通过key获取对应的值
	 * @param key 数据中某个值的path
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	Object get(String key);
	/**
	 * 通过key设置对应的值
	 * @param key 数据中某个值的path
	 * @param value 要设置的值
	 * @return
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void set(String key, Object value);
	/**
	 * 通过key获取对应字符串值
	 * @param key 数据中某个值的path
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getString(String key);
	/**
	 * 通过key获取对应整型值
	 * @param key 数据中某个值的path
	 * @return 
	 * Integer
	 * @exception 
	 * @since  1.0.0
	 */
	Integer getInt(String key);
	/**
	 * 通过key获取对应长整型值
	 * @param key 数据中某个值的path
	 * @return 
	 * Long
	 * @exception 
	 * @since  1.0.0
	 */
	Long getLong(String key);
	/**
	 * 通过key获取对应浮点型值
	 * @param key 数据中某个值的path
	 * @return 
	 * Float
	 * @exception 
	 * @since  1.0.0
	 */
	Float getFloat(String key);
	/**
	 * 通过key获取对应布尔型值 
	 * @param key 数据中某个值的path
	 * @return 
	 * Boolean
	 * @exception 
	 * @since  1.0.0
	 */
	Boolean getBoolean(String key);
	/**
	 * 通过key获取对应短整型值
	 * @param key 数据中某个值的path
	 * @return 
	 * Short
	 * @exception 
	 * @since  1.0.0
	 */
	Short getShort(String key);
	/**
	 * 通过key获取对应日期型值
	 * @param key 数据中某个值的path
	 * @return 
	 * Date
	 * @exception 
	 * @since  1.0.0
	 */
	Date getDate(String key);
	/**
	 * 通过key获取对应的业务对象实例类型的值
	 * @param key 数据中某个值的path
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject getDataObject(String key);
	/**
	 * 通过key设置对应业务对象实例的值
	 * @param key 数据中某个值的path
	 * @param dataObject 要设置的业务对象实例的值
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setDataObject(String key, DataObject dataObject);
	/**
	 * 获取集合类型的业务对象实例值
	 * @param key 数据中某个值的path
	 * @return 
	 * List<DataObject>
	 * @exception 
	 * @since  1.0.0
	 */
	List<DataObject> getDataObjects(String key);
	/**
	 * 通过key对集合类型的业务对象实例的值进行设置
	 * @param key 数据中某个值的path
	 * @param list 业务对象实例集合
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setDataObjects(String key, List<DataObject> list);
	/**
	 * 往key所对应的集合类型业务对象实例中添加一个实例
	 * @param key 数据中某个值的path
	 * @param dataObject 业务对象实例
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void put(String key, DataObject dataObject);
	
	/**
	 * 获取数据的类型
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getDataType();
	
	/**
	 * 获取对应的属性名称
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getAttrName();
	
	/**
	 * 设置对应的属性名称
	 * @param attrName 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void setAttrName(String attrName);
}
