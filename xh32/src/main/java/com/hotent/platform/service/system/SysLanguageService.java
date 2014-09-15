package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.model.system.SysLanguage;
import com.hotent.platform.dao.system.SysLanguageDao;

/**
 *<pre>
 * 对象功能:系统支持的语言 Service类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-12-26 18:29:05
 *</pre>
 */
@Service
public class SysLanguageService extends BaseService<SysLanguage>
{
	@Resource
	private SysLanguageDao dao;
	
	
	
	public SysLanguageService()
	{
	}
	
	@Override
	protected IEntityDao<SysLanguage, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	public void updNotDefault(){
		dao.updNotDefault();
	}
	
	public void setDefault(Long id){
		dao.setDefault(id);
	}
	
	public void enable(Long id){
		dao.enable(id);
	}
	
	public void disable(Long id){
		dao.disable(id);
	}
	
	/**
	 * 取得所有非禁用语言
	 * @return
	 */
	public List<SysLanguage> getUserableLangauge(){
		return dao.getUserableLangauge();
	}
	
	public SysLanguage getByLanguage(String language){
		return dao.getByLanguage(language);
	}
	
	/**
	 * 取得所有语言分类
	 */
	public List<SysLanguage> getAll(){
		return dao.getAll();
		
	}
	
	public String getAllLanguages(){
		List<SysLanguage> languages = dao.getAll();
		JSONArray jarry = JSONArray.fromObject(languages);
		return jarry.toString();
	}
}
