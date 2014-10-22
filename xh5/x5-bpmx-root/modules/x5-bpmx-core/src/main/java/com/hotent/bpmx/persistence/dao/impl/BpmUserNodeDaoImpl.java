package com.hotent.bpmx.persistence.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmUserNodeDao;
import com.hotent.bpmx.persistence.dao.BpmNodeConfigDao;
import com.hotent.bpmx.persistence.model.DefaultBpmUserNode;

@Repository
public class BpmUserNodeDaoImpl extends MyBatisDaoImpl<String, DefaultBpmUserNode> implements BpmUserNodeDao{
	@Resource
	private BpmNodeConfigDao defaultBpmNodeConfigDao;
	
    @Override
    public String getNamespace() {
        return DefaultBpmUserNode.class.getName();
    }

	/* (non-Javadoc)
	 * @see com.hotent.base.db.impl.MyBtaisDaoImpl#create(java.lang.Object)
	 */
	@Override
	public void create(DefaultBpmUserNode entity) {		
		defaultBpmNodeConfigDao.create(entity);		
		super.create(entity);
	}

	/* (non-Javadoc)
	 * @see com.hotent.base.db.impl.MyBtaisDaoImpl#update(java.lang.Object)
	 */
	@Override
	public void update(DefaultBpmUserNode entity) {
		defaultBpmNodeConfigDao.update(entity);
		super.update(entity);
	}

	/* (non-Javadoc)
	 * @see com.hotent.base.db.impl.MyBtaisDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(String entityId) {
		defaultBpmNodeConfigDao.remove(entityId);
		super.remove(entityId);
	}

}
