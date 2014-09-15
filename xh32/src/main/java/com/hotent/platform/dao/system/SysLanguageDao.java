package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysLanguage;
/**
 *<pre>
 * 对象功能:系统支持的语言 Dao类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-12-26 18:29:05
 *</pre>
 */
@Repository
public class SysLanguageDao extends BaseDao<SysLanguage>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysLanguage.class;
	}
	
	public void updNotDefault(){
		this.update("updNotDefault", null);
	}
	
	public void setDefault(Long id){
		this.update("setDefault", id);
	}
	
	public void enable(Long id){
		this.update("enable", id);
	}
	
	public void disable(Long id){
		this.update("disable", id);
	}
	
	public List<SysLanguage> getUserableLangauge(){
		return this.getBySqlKey("getUserableLangauge");
	}
	
	public SysLanguage getByLanguage(String language){
		return getUnique("getByLanguage", language);
	}
}
