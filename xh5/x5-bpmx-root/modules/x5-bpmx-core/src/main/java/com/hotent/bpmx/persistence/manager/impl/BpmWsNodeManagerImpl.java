package com.hotent.bpmx.persistence.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.hotent.bpmx.persistence.dao.BpmWsNodeParamsDao;
import com.hotent.bpmx.persistence.model.BpmWsNodeParams;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.persistence.dao.BpmWsNodeDao;
import com.hotent.bpmx.persistence.model.BpmWsNode;
import com.hotent.bpmx.persistence.manager.BpmWsNodeManager;

@Service("bpmWsNodeManager")
public class BpmWsNodeManagerImpl extends AbstractManagerImpl<String, BpmWsNode> implements BpmWsNodeManager{
	@Resource
	BpmWsNodeDao bpmWsNodeDao;
	@Resource
	BpmWsNodeParamsDao bpmWsNodeParamsDao;
	@Override
	protected Dao<String, BpmWsNode> getDao() {
		return bpmWsNodeDao;
	}
	/**
	 * 创建实体包含子表实体
	 */
	public void create(BpmWsNode bpmWsNode){
    	super.create(bpmWsNode);
    	bpmWsNodeParamsDao.delByMainId(bpmWsNode.getId());
    	List<BpmWsNodeParams> bpmWsNodeParamsList=bpmWsNode.getBpmWsNodeParamsList();
    	for(BpmWsNodeParams bpmWsNodeParams:bpmWsNodeParamsList){
    		bpmWsNodeParamsDao.create(bpmWsNodeParams);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	bpmWsNodeParamsDao.delByMainId(entityId);
		
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public BpmWsNode get(String entityId){
    	BpmWsNode bpmWsNode=super.get(entityId);
    	List<BpmWsNodeParams> bpmWsNodeParamsList=bpmWsNodeParamsDao.getByMainId(entityId);
    	bpmWsNode.setBpmWsNodeParamsList(bpmWsNodeParamsList);
    	return bpmWsNode;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(BpmWsNode bpmWsNode){
    	super.update(bpmWsNode);
    	bpmWsNodeParamsDao.delByMainId(bpmWsNode.getId());
    	List<BpmWsNodeParams> bpmWsNodeParamsList=bpmWsNode.getBpmWsNodeParamsList();
    	for(BpmWsNodeParams bpmWsNodeParams:bpmWsNodeParamsList){
    		bpmWsNodeParamsDao.create(bpmWsNodeParams);
    	}
    }
	
}
