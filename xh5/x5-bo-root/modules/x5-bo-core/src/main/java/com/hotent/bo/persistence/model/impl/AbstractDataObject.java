package com.hotent.bo.persistence.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.core.util.time.DateFormatUtil;
import com.hotent.bo.BOConfiguration;
import com.hotent.bo.exception.BOBaseException;
import com.hotent.bo.instance.BOInstanceFactory;
import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;

public abstract class AbstractDataObject implements DataObject {
	public abstract String getDataType();
	
	public abstract Object get(String key);

	public abstract void  set(String key, Object value);
	
	public abstract String getInstData();
	
	public abstract void setInstData(String instData);
	
	protected String  id; /*业务实例ID*/
	protected Date  createTime; /*创建时间*/
	protected String defId; /*对象定义ID*/
	protected BODef boDef; /*对象定义*/
	protected String attrName; /*对应的属性名称*/
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 业务实例ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	
	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public void setBoDef(BODef boDef)
	{
		this.boDef = boDef;
	}
	/**
	 * 返回 对象定义
	 * @return
	 */
	public BODef getBoDef()
	{
		return this.boDef;
	}
	
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public Date getCreateTime() 
	{
		return this.createTime;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("createTime", this.createTime) 
		.toString();
	}

	@Override
	public String getString(String key) {
		return get(key).toString();
	}

	@Override
	public Integer getInt(String key) {
		return Integer.parseInt(getString(key));
	}

	@Override
	public Long getLong(String key) {
		return Long.parseLong(getString(key));
	}

	@Override
	public Float getFloat(String key) {
		return Float.parseFloat(getString(key));
	}

	@Override
	public Boolean getBoolean(String key) {
		return Boolean.parseBoolean(getString(key));
	}

	@Override
	public Short getShort(String key) {
		return Short.parseShort(getString(key));
	}
	
	private BOAttribute getAttribute(BOAttribute attribute,String path){
		List<BOAttribute> attributes = attribute.getRefBODef().getBOAttributeList();
		for(BOAttribute boAttribute : attributes){
			if(path.equals(boAttribute.getName())){
				return boAttribute;
			}
		}
		return null;
	}
	
	/**
	 * 通过路径获取对应属性
	 * @param path
	 * @return 
	 * BOAttribute
	 * @exception 
	 * @since  1.0.0
	 */
	private BOAttribute getAttributeByPath(String path){
		String newPath = path.replaceAll("\\[.*\\]", "");
		String[] pathAry = newPath.split("\\.");
		int count = pathAry.length;
		if(count < 2)return null;
		BOAttribute attr = null;
		List<BOAttribute> attributes = this.boDef.getBOAttributeList();
		for(BOAttribute boAttribute : attributes){
			if(pathAry[1].equals(boAttribute.getName())){
				attr = boAttribute;
				break;
			}
		}
		int index = 2;
		while(index < count){
			attr = getAttribute(attr, pathAry[index]);
			index++;
		}
		return attr;
	}

	@Override
	public Date getDate(String key){
		//获取路径所对应的属性
		BOAttribute attribute = getAttributeByPath(key);
		String dateString = getString(key);
		//从属性中获取 日期的格式
		String format = "";
		if(BeanUtils.isNotEmpty(attribute)){
			format = attribute.getFormat();
		}
		try{
			if(BeanUtils.isEmpty(format)){
				return DateFormatUtil.parse(dateString);
			}
			else{
				return DateFormatUtil.parse(dateString, format);
			}
		}
		catch (Exception e) {
			throw new BOBaseException("将获取的值转换为Date类型时出错了,日期的值:" + dateString +",日期的格式:" + format);
		}
	}

	@Override
	public DataObject getDataObject(String key) {
		//获取路径所对应的属性
		BOAttribute attribute = getAttributeByPath(key);
		return createSubDataObject(attribute,get(key));
	}
	
	private DataObject createSubDataObject(BOAttribute attribute,Object data){
		BOInstanceFactory boInstanceFactory = BOConfiguration.getBean(BOInstanceFactory.class);
		return boInstanceFactory.createDataObject(attribute.getRefBODef(), data,attribute.getName()); 
	}

	@Override
	public void setDataObject(String key, DataObject dataObject) {
		set(key, dataObject.getInstData());
	}

	@Override
	public List<DataObject> getDataObjects(String key) {
		//获取路径所对应的属性
		BOAttribute attribute = getAttributeByPath(key);
		List<DataObject> returnList = new ArrayList<DataObject>();
		Object returnObj = get(key);
		if(returnObj instanceof List){
			List<Object> list = (List<Object>)get(key);
			if(BeanUtils.isEmpty(list)){
				return returnList;
			}
			for(Object obj : list){
				returnList.add(createSubDataObject(attribute, obj));
			}
		}
		else{
			returnList.add(createSubDataObject(attribute, returnObj));
		}
		return returnList;
	}

	@Override
	public void setDataObjects(String key, List<DataObject> list) {
		List<Object> ary = new ArrayList<Object>();
		for(DataObject dataObject : list){
			ary.add(dataObject.getInstData());
		}
		set(key, ary);
	}

	@Override
	public void put(String key, DataObject dataObject) {
		List<DataObject> list = getDataObjects(key);
		list.add(dataObject);
		setDataObjects(key, list);
	}
	
	
	/**
	 * 获取对应的属性名称
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getAttrName(){
		return this.attrName;
	}
	
	/**
	 * 设置对应的属性名称
	 * @param attrName 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void setAttrName(String attrName){
		this.attrName = attrName;
	}
}
