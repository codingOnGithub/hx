package com.hotent.bo.instance.adapter.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.dom4j.Element;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.bo.BOConfiguration;
import com.hotent.bo.exception.BOBaseException;
import com.hotent.bo.instance.adapter.BOAdapter;
import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_REF_TYPE;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_TYPE;
import com.hotent.bo.valid.BOValid;

public class XmlBOAdapter implements BOAdapter {
	@Resource
	BOValid boValid;
	
	@Override
	public String getDataType() {
		return "xml";
	}
	
	//构建初始化的数据
	private DataObject setInstData(DataObject dataObject){
		BODef boDef = dataObject.getBoDef();
		List<BOAttribute> attributes = boDef.getBOAttributeList();
		for(BOAttribute attribute:attributes){
			String attributeName = attribute.getName();
			String attrPath = dataObject.getBoDef().getName() + "." + attributeName;
			Object val = "";
			//基本数据类型
			if(BOATTRIBUTE_TYPE.BASE.equals(attribute.getType())){
				if(BeanUtils.isNotEmpty(attribute.getDefaultValue())){
					val = attribute.getDefaultValue();
				}
				dataObject.set(attrPath, val);
			}
			//复合数据类型
			else{
				BODef relBodef = attribute.getRefBODef();
				DataObject subDataObject = getNewDataObject(dataObject);
				subDataObject.setBoDef(relBodef);
				subDataObject.setInstData("<" + attributeName + "></" +attributeName + ">");
				setInstData(subDataObject);
				val = subDataObject.getInstData();
			}
			dataObject.set(attrPath, val);
		}
		return dataObject;
	}
	
	/**
	 * 将传入的数据与已有数据进行合并
	 * @param oldObject
	 * @param newObject
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	private DataObject setInstData(DataObject oldObject,DataObject newObject){
		Boolean validAttribute = BOConfiguration.getValidAttribute();
		List<BOAttribute> attributes = oldObject.getBoDef().getBOAttributeList();
		for(BOAttribute attribute:attributes){
			String attributeName = attribute.getName();
			String attrPath = oldObject.getBoDef().getName() + "." + attributeName;
			//基本数据类型
			if(BOATTRIBUTE_TYPE.BASE.equals(attribute.getType())){
				Object val = newObject.get(attrPath);
				if(BeanUtils.isNotEmpty(val)){
					//若配置为不验证输入的数据，或者BOAttribute的规则通过了验证，则设置该属性的值
					if(!validAttribute||boValid.valid(attribute, val)){
						oldObject.set(attrPath, val);
					}
				}
			}
			//复合数据类型
			else{
				//引用类型为  HAS_MANY
				if(BOATTRIBUTE_REF_TYPE.HAS_MANY.equals(attribute.getRefType())){
					List<DataObject> newSubs = newObject.getDataObjects(attrPath);
					if(BeanUtils.isEmpty(newSubs)){
						continue;
					}
					List<DataObject> oldSubs = oldObject.getDataObjects(attrPath);
					List<DataObject> returnSubs = new ArrayList<DataObject>();
					int oldSize = oldSubs.size();
					int newSize = newSubs.size();
					for(int i = 0;i<newSize;i++){
						DataObject newSub = newSubs.get(i);
						DataObject oldSub;
						if(i<oldSize){
							oldSub = oldSubs.get(i);
						}
						else{
							DataObject obj = getNewDataObject(oldObject);
							oldSub = oldSubs.get(0);
							obj.setBoDef(oldSub.getBoDef());
							obj.setInstData(oldSub.getInstData());
							oldSub = obj;
						}
						returnSubs.add(setInstData(oldSub,newSub));
					}
					oldObject.setDataObjects(attrPath, returnSubs);
				}
				else{
					DataObject oldSub = oldObject.getDataObject(attrPath);
					DataObject newSub = newObject.getDataObject(attrPath);
					setInstData(oldSub,newSub);
					oldObject.setDataObject(attrPath, oldSub);
				}
			}
		}
		return oldObject;
	}
	
	/**
	 * 通过已有DataObject创建一个新的实例
	 * @param dataObject
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	private DataObject getNewDataObject(DataObject dataObject){
		try {
			return dataObject.getClass().newInstance();
		}
		catch (Exception e) {
			throw new BOBaseException("创建dataType为:"+dataObject.getDataType()+"的BODef所对应的DataObject时出错");
		}
	}
   
	public void loadData(Object data, DataObject dataObject) {
		if(BeanUtils.isEmpty(data)){
			setInstData(dataObject);
		}
		else{
			DataObject newDataObject = getNewDataObject(dataObject);
			newDataObject.setAttrName(dataObject.getAttrName());
			newDataObject.setBoDef(dataObject.getBoDef());
			if(data instanceof Element){
				Element ele = (Element)data;
				newDataObject.setInstData(ele.asXML());
			}
			else{
				newDataObject.setInstData(data.toString());
			}
			setInstData(dataObject, newDataObject);
		}
	}

	@Override
	public String parseData(DataObject dataObject) {
		return dataObject.getInstData();
	}
}
