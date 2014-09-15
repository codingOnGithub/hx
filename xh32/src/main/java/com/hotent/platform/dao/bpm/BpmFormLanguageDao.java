package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmFormLanguage;
/**
 *<pre>
 * 对象功能:表单国际化资源 Dao类
 * 开发公司:宏天
 * 开发人员:xxx
 * 创建时间:2013-12-26 09:54:54
 *</pre>
 */
@Repository
public class BpmFormLanguageDao extends BaseDao<BpmFormLanguage>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmFormLanguage.class;
	}

	public List<BpmFormLanguage> getByFormIdAndResKey(String formId,String resKey,Integer typeid){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("formId", formId);
		map.put("resKey", resKey);
		map.put("typeId", typeid);
		return this.getBySqlKey("getByMapKey",map);
	}
	
	public List<BpmFormLanguage> getByFormIdAndLanType(String formId,String lanType,Integer typeid){
		if(StringUtil.isEmpty(formId)||StringUtil.isEmpty(lanType))return null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("formId", formId);
		map.put("lanType", lanType);
		map.put("typeId", typeid);
		return this.getBySqlKey("getByMapKey",map);
	}
	
	public BpmFormLanguage getByFormIdResKeyAndLanguage(String formId,String resKey,String language,Integer typeid){
		if(StringUtil.isEmpty(resKey)||StringUtil.isEmpty(formId))return null;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("formId", formId);
		map.put("resKey", resKey);
		map.put("lanType", language);
		map.put("typeId", typeid);
		return (BpmFormLanguage)this.getOne("getByMapKey", map);
	}
	
	public void delByFormId(String formId){
		this.delBySqlKey("delByFormId", formId);
	}
	
	public void delByFormIdAndType(String formId,Integer typeId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("formId", formId);
		map.put("typeId", typeId);
		this.delBySqlKey("delByFormIdAndType",map);
	}
	
	public void delByFormIdAndResKey(String formId,String resKey){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("formId", formId);
		map.put("resKey", resKey);
		this.delBySqlKey("delByMapKey", map);
	}
	
	public void delByResKeyAndType(String resKey,Integer typeid){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeId", typeid);
		map.put("resKey", resKey);
		this.delBySqlKey("delByMapKey", map);
	}

	public List<BpmFormLanguage> getByFormIdAndType(String formId, Integer typeId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("formId", formId);
		map.put("typeId", typeId);
		return this.getBySqlKey("getByMapKey",map);
	}
	
	public List<BpmFormLanguage> getByResKeyAndType(String resKey, Integer typeId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("resKey", resKey);
		map.put("typeId", typeId); 
		return this.getBySqlKey("getByMapKey",map);
	}

	public List<BpmFormLanguage> getByTypeAndLanType(String lanType, Integer typeId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("lanType", lanType);
		map.put("typeId", typeId);
		return this.getBySqlKey("getByMapKey",map);
	}
	
	public BpmFormLanguage getSysTypeKeyByFormIdAndLanType(String formId, String lanType, Integer typeId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("lanType", lanType);
		map.put("formId", formId);
		map.put("typeId", typeId);
		return (BpmFormLanguage)this.getOne("getByMapKey", map);
	}
	
	public List<BpmFormLanguage> getByResKeyTypeAndLanType(String resKey, Integer typeId, String lanType){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("lanType", lanType);
		map.put("resKey", resKey);
		map.put("typeId", typeId);
		return this.getBySqlKey("getByMapKey",map);
	}
	
	public BpmFormLanguage getGlobalTypeByFormIdAndLanType(String formId, String lanType, Integer typeId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("lanType", lanType);
		map.put("formId", formId);
		map.put("typeId", typeId);
		return (BpmFormLanguage)this.getOne("getByMapKey", map);
	}
	
}