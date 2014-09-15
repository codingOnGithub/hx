package com.hotent.platform.service.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.system.ResourcesLangDao;
import com.hotent.platform.model.system.ResourcesLang;

/**
 * <pre>
 * 对象功能:系统语言资源表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-01-08 17:17:46
 * </pre>
 */
@Service
public class ResourcesLangService extends BaseService<ResourcesLang> {
	@Resource
	private ResourcesLangDao dao;

	public ResourcesLangService() {
	}

	@Override
	protected IEntityDao<ResourcesLang, Long> getEntityDao() {
		return dao;
	}

}
