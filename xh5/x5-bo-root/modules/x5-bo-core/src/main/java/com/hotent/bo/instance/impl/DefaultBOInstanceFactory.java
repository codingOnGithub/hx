package com.hotent.bo.instance.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.bo.exception.BOBaseException;
import com.hotent.bo.exception.NoMatchAdapterFoundException;
import com.hotent.bo.instance.BOInstanceFactory;
import com.hotent.bo.instance.adapter.BOAdapter;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.DataObject;
import com.hotent.bo.persistence.model.impl.JSONDataObject;

@Repository
public class DefaultBOInstanceFactory implements BOInstanceFactory {
	//注入DataObject接口的实现类
	private List<DataObject> dataObjects;
	//注入BOAdapter接口的实现类
	private List<BOAdapter> boAdapters;
	
	public void setDataObjects(List<DataObject> dataObjects){
		this.dataObjects = dataObjects;
	}
	
	public void setBoAdapters(List<BOAdapter> boAdapters) {
		this.boAdapters = boAdapters;
	}

	/**
	 * 根据BODef中描述的类型(dataType)返回对应的DataObject 
	 * @param boDef
	 * @return 
	 * DataObject
	 * @exception 
	 * @since  1.0.0
	 */
	public DataObject getNewDataObject(BODef boDef){
		String dataType = boDef.getDataType();
		DataObject dataObject = new JSONDataObject();
		for(DataObject dObject : dataObjects){
			if(dataType.equals(dObject.getDataType())){
				try{
					//获取注入实现类的新实例
					dataObject = dObject.getClass().newInstance();
				}
				catch (Exception e) {
					throw new BOBaseException("创建dataType为:"+dataType+"的BODef所对应的DataObject时出错");
				}
				break;
			}
		}
		return dataObject;
	}
	
	/**
	 * 获取DataObject所对应的适配器
	 * @param dataType
	 * @return 
	 * BOAdapter
	 * @exception 
	 * @since  1.0.0
	 */
	private BOAdapter getBoAdapter(String dataType){
		BOAdapter boAdapter = null;
		for(BOAdapter adapter : boAdapters){
			if(dataType.equals(adapter.getDataType())){
				boAdapter = adapter;
				break;
			}
		}
		if(BeanUtils.isEmpty(boAdapter)){
			throw new NoMatchAdapterFoundException("未找到匹配数据类型为:" + dataType + "的适配器");
		}
		return boAdapter;
	}

	public DataObject createDataObject(BODef boDef){
		//实例化时 不设置 instanceId，保存到数据库中时 再设置instanceId
		DataObject dataObject = getNewDataObject(boDef);
		dataObject.setBoDef(boDef);
		BOAdapter boAdapter = getBoAdapter(dataObject.getDataType());
		//构建初始化数据
		boAdapter.loadData(null, dataObject);
		return dataObject;
	}

	public DataObject createDataObject(BODef boDef, Object data){
		DataObject dataObject = createDataObject(boDef);
		BOAdapter boAdapter = getBoAdapter(dataObject.getDataType());
		boAdapter.loadData(data, dataObject);
		return dataObject;
	}

	public DataObject createDataObject(BODef boDef, Object data, String attrName) {
		DataObject dataObject = getNewDataObject(boDef);
		dataObject.setBoDef(boDef);
		dataObject.setAttrName(attrName);
		BOAdapter boAdapter = getBoAdapter(dataObject.getDataType());
		boAdapter.loadData(data, dataObject);
		return dataObject;
	}
}