package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.ResourcesLang;

/**
 * <pre>
 * 对象功能:系统语言资源表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-01-08 17:17:46
 * </pre>
 */
@Repository
public class ResourcesLangDao extends BaseDao<ResourcesLang> {
	@Override
	public Class<?> getEntityClass() {
		return ResourcesLang.class;
	}

	public List<ResourcesLang> getByResId(Long resId) {
		return this.getBySqlKey("getByResId", resId);
	}

}