package com.hotent.demo.service.ztree;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.demo.dao.ztree.SysZtreeDao;
import com.hotent.demo.model.ztree.SysZtree;
import com.hotent.platform.model.system.GlobalType;


@Service
public class SysZtreeService extends BaseService<SysZtree>
{
	@Resource
	private SysZtreeDao dao;
	
	public SysZtreeService()
	{
	}
	
	@Override
	protected IEntityDao<SysZtree,Long> getEntityDao() 
	{
		return dao;
	}

	public SysZtree getTreeTypeByAlias(String alias) {
		// TODO Auto-generated method stub
		return dao.getTreeTypeByAlias(alias);
	}

	public List<GlobalType> getTreeDatas(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
		return dao.getTreeDatas(params);
	}
	
}
