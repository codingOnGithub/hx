package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmFormLanguage;
import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platform.service.bpm.BpmFormLanguageService;
import com.hotent.platform.dao.bpm.BpmFormLanguageDao;
import com.hotent.platform.dao.system.SysTypeKeyDao;

/**
 * 对象功能:系统分类键定义 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-10 10:18:36
 */
@Service
public class SysTypeKeyService extends BaseService<SysTypeKey>
{
	@Resource
	private SysTypeKeyDao dao;
	@Resource
	private BpmFormLanguageDao bpmFormLanguageDao;
	
	public SysTypeKeyService()
	{
	}
	
	@Override
	protected IEntityDao<SysTypeKey, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据key取得TypeKey对象。
	 * @param key
	 * @return
	 */
	public SysTypeKey getByKey(String key){
		
		return dao.getByKey(key);
	}
	
	/**
	 * 判断键值是否存在。
	 * @param typeKey
	 * @return
	 */
	public boolean isExistKey(String typeKey){
		typeKey=typeKey.toLowerCase();
		return dao.isExistKey(typeKey);
	}
	
	/**
	 * 检查某个Key是否存在
	 * @param typeKey
	 * @param typeId 除本Key外
	 * @return
	 */
	public boolean isKeyExistForUpdate(String typeKey,Long typeId){
		typeKey=typeKey.toLowerCase();
		return dao.isKeyExistForUpdate(typeKey, typeId);
	}
	
	/**
	 * 排序操作。
	 * @param aryTypeId
	 */
	public void saveSequence(Long[] aryTypeId){
		for(int i=0;i<aryTypeId.length;i++){
			dao.updateSequence(aryTypeId[i],i);
		}
	}
	
	public void delByTypeIds(Long[] aryTypeId){
		for(Long typeId : aryTypeId){
			dao.delById(typeId);
			// 删除和分类相关的国际化资源记录
			bpmFormLanguageDao.delByFormId(typeId.toString());
		}
	}
	
	/**
	 * 回填国际化资源
	 * @param list
	 */
	public void handleLocale(List<SysTypeKey> list){
		if(BeanUtils.isEmpty(list)) return;
		String lanType = ContextUtil.getLocale().toString();
		List<BpmFormLanguage> bpmFormLanguageList = bpmFormLanguageDao.getByTypeAndLanType(lanType, BpmFormLanguage.SYS_TYPE_KEY);
		if(bpmFormLanguageList.size()==0) return;
		
		Map<String, String> map = new HashMap<String, String>();
		for(BpmFormLanguage bpmFormLanguage : bpmFormLanguageList){
			map.put(bpmFormLanguage.getReskey(), bpmFormLanguage.getResvalue());
		}
		for(SysTypeKey sysTypeKey : list){
			String resValue = map.get(sysTypeKey.getTypeKey());
			if(StringUtil.isEmpty(resValue)) continue;
			sysTypeKey.setTypeName(resValue);
		}
	}
	
	/**
	 * 回填国际化资源
	 * @param sysTypeKey
	 */
	public void handleLocale(SysTypeKey sysTypeKey){
		if(BeanUtils.isEmpty(sysTypeKey)) return;
		String lanType = ContextUtil.getLocale().toString();
		BpmFormLanguage bpmFormLanguage = bpmFormLanguageDao.getSysTypeKeyByFormIdAndLanType(sysTypeKey.getTypeId().toString(), lanType, 
				BpmFormLanguage.SYS_TYPE_KEY);
		if(BeanUtils.isEmpty(bpmFormLanguage)) return;
		sysTypeKey.setTypeName(bpmFormLanguage.getResvalue());
	}

}
