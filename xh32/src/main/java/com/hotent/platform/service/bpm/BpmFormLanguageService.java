package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmFormLanguageDao;
import com.hotent.platform.dao.form.BpmFormFieldDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormLanguage;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;

/**
 *<pre>
 * 对象功能:表单国际化资源 Service类
 * 开发公司:hotent
 * 开发人员:xxx
 * 创建时间:2013-12-26 09:54:54
 *</pre>
 */
@Service
public class BpmFormLanguageService extends BaseService<BpmFormLanguage>
{
	@Resource
	private BpmFormLanguageDao dao;
	@Resource
	private BpmFormTableDao  bpmFormTableDao;
	@Resource
	private BpmFormFieldDao bpmFormFieldDao;
	
	@Resource
	private BpmDefinitionDao bpmDefinitionDao;
	
	
	
	public BpmFormLanguageService()
	{
	}
	
	@Override
	protected IEntityDao<BpmFormLanguage, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 通过表单或者表格ID 以及字段key获取记录
	 * @param formId
	 * @param resKey
	 * @return
	 */
	public List<BpmFormLanguage> getByFormIdAndResKey(String formId,String resKey,Integer typeid){
		return dao.getByFormIdAndResKey(formId, resKey,typeid);
	}
	
	/**
	 * 通过表单ID或者表ID删除记录
	 * @param formId
	 */
	public void delByFormIdAndType(String formId,Integer typeId){
		dao.delByFormIdAndType(formId,typeId);
		
	}
	
	/**
	 * 通过表单、表ID和字段key批量删除记录
	 * @param formId
	 * @param resKeys
	 */
	public void delByFormIdAndResKeys(String formId,String[] resKeys){
		for(String reskey : resKeys){
			dao.delByFormIdAndResKey(formId, reskey);
		}
	}
	
	/**
	 * 通过分类Key和类型批量删除记录
	 * @param formId
	 * @param resKeys
	 */
	public void delByKeysAndTypeId(String[] resKeys, Integer typeId){
		for(String resKey : resKeys){
			dao.delByResKeyAndType(resKey, typeId);
		}
	}
	
	/**
	 * 删除操作按钮的国际化资源
	 * @param formIds
	 * @param reskey
	 */
	public void delbutByFormIdAndResKeys(String[] formIds, String reskey) {
		for(String formId : formIds){
			dao.delByFormIdAndResKey(formId, reskey);
		}
	}
	
	/**
	 * 保存或更新数据
	 * @param actDefId
	 * @param nodeId
	 * @param map
	 */
	public void batchSaveUpdate(String formId, String resKey, Map<String,String> map, Integer typeid){
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = map.get(key);
			if(StringUtil.isEmpty(value)) continue;
			BpmFormLanguage bpmFormLanguage = dao.getByFormIdResKeyAndLanguage(formId, resKey, key,typeid);
			if(BeanUtils.isEmpty(bpmFormLanguage)){
				bpmFormLanguage = new BpmFormLanguage();
				bpmFormLanguage.setId(UniqueIdUtil.genId());
				bpmFormLanguage.setFormid(formId);
				bpmFormLanguage.setReskey(resKey);
				bpmFormLanguage.setResvalue(value);
				bpmFormLanguage.setLantype(key);
				bpmFormLanguage.setTypeid(typeid);
				dao.add(bpmFormLanguage);
			}
			else{
				bpmFormLanguage.setResvalue(value);
				dao.update(bpmFormLanguage);
			}
		}
	}
	
	/**
	 * 创建表时 添加默认的中文国际化资源
	 * @param model
	 */
	public void createTableInternational(BpmFormTable bpmFormTable){
		String tableId = bpmFormTable.getTableId().toString();
		if(BeanUtils.isNotEmpty(tableId))
			dao.delByFormId(tableId);
		String lanType = ContextUtil.getLocale().toString();
		List<BpmFormField> list = bpmFormTable.getFieldList(); 
		for (BpmFormField bpmFormField:list) {
			BpmFormLanguage bpmFormLanguage = new BpmFormLanguage();
			bpmFormLanguage.setId(UniqueIdUtil.genId());
			bpmFormLanguage.setFormid(tableId);
			bpmFormLanguage.setReskey(bpmFormField.getFieldName());
			bpmFormLanguage.setResvalue(bpmFormField.getFieldDesc());
			bpmFormLanguage.setLantype(lanType);
			bpmFormLanguage.setTypeid(BpmFormLanguage.BPM_FORM_TYPE);
			dao.add(bpmFormLanguage);
		}
	}

	/**
	 * 初始化表中文国际化资源
	 * @param formId 
	 */
	public void init(Long formId) {
		BpmFormTable bpmFormTable = bpmFormTableDao.getById(formId);
		if(BeanUtils.isNotEmpty(bpmFormTable)){
			List<BpmFormField> bpmFormFieldList =bpmFormFieldDao.getByTableId(bpmFormTable.getTableId());
			if(BeanUtils.isNotEmpty(bpmFormFieldList)){
				bpmFormTable.setFieldList(bpmFormFieldList);
				this.createTableInternational(bpmFormTable);
			}
		}
			
	}
	/**
	 * 获取国际化资源
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public BpmFormLanguage getByIdAndNodeIdAndLan(String actDefId,String nodeId,Integer typeid){
		Locale locale = ContextUtil.getLocale();
		return dao.getByFormIdResKeyAndLanguage(actDefId, nodeId, locale.toString(),typeid);
	}
	
	/**
	 * 获取流程某节点的国际化资源
	 * @param defId
	 * @param nodeId
	 * @return
	 */
	public BpmFormLanguage getByDefIdAndNodeId(Long defId,String nodeId,Integer typeid){
		BpmDefinition bpmDefinition = bpmDefinitionDao.getById(defId);
		if(BeanUtils.isEmpty(bpmDefinition))return null;
		String actDefId = bpmDefinition.getActDefId();
		BpmFormLanguage bpmFormLanguage=getByIdAndNodeIdAndLan(actDefId, nodeId,typeid);
		return bpmFormLanguage;
	}
	
	//
	public Map<String,String> getLanguageMapByActDefId(String actDefId,Integer typeid) {
		Locale locale = ContextUtil.getLocale();
		List<BpmFormLanguage> bpmFormLanguageList =dao.getByFormIdAndLanType(actDefId,locale.toString(),typeid);
		Map<String,String> map = new HashMap<String, String>();

		if(BeanUtils.isEmpty(bpmFormLanguageList))
			return map;
		for (BpmFormLanguage bpmFormLanguage : bpmFormLanguageList) {
			map.put(bpmFormLanguage.getReskey(), bpmFormLanguage.getResvalue());
		}
		return map;
	}

	/**
	 * 根据流程Id获取全部操作按钮的国际化资源
	 * @param actDefId
	 * @param bpmButtonType
	 * @return
	 */
	public Map<String, Map<String,String>> getButLangMapByActDefId(String actDefId, Integer typeid) {
		List<BpmFormLanguage> bpmFormLanguageList =dao.getByResKeyAndType(actDefId, typeid);
		Map<String,Map<String,String>> map = new HashMap<String, Map<String,String>>();

		if(BeanUtils.isEmpty(bpmFormLanguageList))
			return map;
		for (BpmFormLanguage bpmFormLanguage : bpmFormLanguageList) {
			String formId = bpmFormLanguage.getFormid();
			Map<String,String> keyValues = map.get("key_"+formId);
			if(BeanUtils.isEmpty(keyValues)){
				keyValues = new HashMap<String, String>();
				map.put("key_" +formId, keyValues);
			}
			keyValues.put(bpmFormLanguage.getLantype(),bpmFormLanguage.getResvalue());
		}
		return map;
	}

	/**
	 * 根据resKey和typeid删除国际化资源
	 * @param resKey
	 * @param typeid
	 */
	public void delByResKeyAndType(String resKey,Integer typeid) {
		dao.delByResKeyAndType(resKey,typeid);
		
	}

	public List<BpmFormLanguage> getByFormIdAndType(String formId,Integer typeId) {
		if(StringUtil.isEmpty(formId))return new ArrayList<BpmFormLanguage>();
		List<BpmFormLanguage> bpmFormLanguageList =dao.getByFormIdAndType(formId,typeId);
		return bpmFormLanguageList;
	}
	
	public List<BpmFormLanguage> getByResKeyAndType(String resKey,Integer typeId) {
		if(StringUtil.isEmpty(resKey))return new ArrayList<BpmFormLanguage>();
		List<BpmFormLanguage> bpmFormLanguageList =dao.getByResKeyAndType(resKey,typeId);
		return bpmFormLanguageList;
	}
	
	/**
	 * 获取分类标志当前语言的数据
	 * @return
	 */
	public List<BpmFormLanguage> getByTypeAndLanType(String lanType, Integer typeId){
		List<BpmFormLanguage> bpmFormLanguageList = dao.getByTypeAndLanType(lanType, typeId);
		return bpmFormLanguageList;
	}
	
	/**
	 * 获取分类标志当前语言的数据
	 * @return
	 */
	public BpmFormLanguage getSysTypeKeyByFormIdAndLanType(String formId, String lanType, Integer typeId){
		BpmFormLanguage bpmFormLanguage = dao.getSysTypeKeyByFormIdAndLanType(formId, lanType, typeId);
		return bpmFormLanguage;
	}
	
	/**
	 * 获取当前语言的数据
	 * @return
	 */
	public List<BpmFormLanguage> getByResKeyTypeAndLanType(String resKey, Integer typeId, String lanType){
		List<BpmFormLanguage> bpmFormLanguageList = dao.getByResKeyTypeAndLanType(resKey, typeId, lanType);
		return bpmFormLanguageList;
	}
	
	/**
	 * 获取系统分类当前语言的数据
	 * @return
	 */
	public BpmFormLanguage getGlobalTypeByFormIdAndLanType(String formId, String lanType, Integer typeId){
		BpmFormLanguage bpmFormLanguage = dao.getGlobalTypeByFormIdAndLanType(formId, lanType, typeId);
		return bpmFormLanguage;
	}
	
}

