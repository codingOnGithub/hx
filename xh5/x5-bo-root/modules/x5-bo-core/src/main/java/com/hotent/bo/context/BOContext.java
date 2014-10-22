package com.hotent.bo.context;

import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;

/**
 * BO模块上下文
 * <pre> 
 * 构建组：x5-bo-core
 * 作者：heyifan
 * 邮箱:heyf@jee-soft.cn
 * 日期:2014-1-26-上午10:45:00
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BOContext {
	/**
	 * 根据defID获取BODef
	 * @param defId
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	BODef createBODef(String defId);
	
	/**
	 * 通过BODef初始化DataObject
	 * @param boDef
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject createDataObject(BODef boDef);
	
	/**
	 * 通过BODef初始化DataObject并装载数据
	 * @param boDef
	 * @param data
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject createDataObject(BODef boDef, Object data);
	
	/**
	 * 发布BODef
	 * @param boDef 
	 * @param defId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void deploy(BODef boDef);
	
	/**
	 * 修改BODef
	 * @param boDef 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	void modify(BODef boDef);
	
	
	/**
	 * 保存DataObject到数据库
	 * @param dataObject 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject save(DataObject dataObject);
	
	/**
	 * 通过实例ID获取DataObject
	 * @param instanceId
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	DataObject getDataObject(String instanceId);
	
	/**
	 * 解析xml文件为BODef
	 * @param defxml
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	BODef parse(String defxml);
	
	/**
	 * 将BODef反解析为xml
	 * @param boDef
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	String getBODefXML(BODef boDef);
}
