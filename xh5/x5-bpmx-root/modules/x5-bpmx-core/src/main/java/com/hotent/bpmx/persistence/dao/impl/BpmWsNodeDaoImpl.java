package com.hotent.bpmx.persistence.dao.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmNodeConfigDao;
import com.hotent.bpmx.persistence.dao.BpmWsNodeDao;
import com.hotent.bpmx.persistence.model.BpmWsNode;

@Repository
public class BpmWsNodeDaoImpl extends MyBatisDaoImpl<String, BpmWsNode> implements BpmWsNodeDao{
	@Resource
	private BpmNodeConfigDao defaultBpmNodeConfigDao;
	
    @Override
    public String getNamespace() {
        return BpmWsNode.class.getName();
    }

	/* (non-Javadoc)
	 * @see com.hotent.base.db.impl.MyBatisDaoImpl#create(java.lang.Object)
	 */
	@Override
	public void create(BpmWsNode entity) {
		defaultBpmNodeConfigDao.create(entity);
		super.create(entity);
	}

	/* (non-Javadoc)
	 * @see com.hotent.base.db.impl.MyBatisDaoImpl#update(java.lang.Object)
	 */
	@Override
	public void update(BpmWsNode entity) {
		defaultBpmNodeConfigDao.update(entity);
		super.update(entity);
	}

	/* (non-Javadoc)
	 * @see com.hotent.base.db.impl.MyBatisDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(String entityId) {		
		super.remove(entityId);
		defaultBpmNodeConfigDao.remove(entityId);
	}
	
}
