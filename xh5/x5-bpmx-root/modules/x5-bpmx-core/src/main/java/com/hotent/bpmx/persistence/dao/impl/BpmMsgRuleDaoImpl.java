package com.hotent.bpmx.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmMsgRuleDao;
import com.hotent.bpmx.persistence.model.BpmMsgRule;


@Repository
public class BpmMsgRuleDaoImpl extends MyBatisDaoImpl<String, BpmMsgRule> implements BpmMsgRuleDao{

    @Override
    public String getNamespace() {
        return BpmMsgRule.class.getName();
    }

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmMsgRuleDao#queryByDefId(java.lang.String)
	 */
	@Override
	public List<BpmMsgRule> queryForDef(String procDefId) {
		return this.getByKey("queryForDef", buildMap("procDefId", procDefId));
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmMsgRuleDao#queryByConfigId(java.lang.String)
	 */
	@Override
	public List<BpmMsgRule> queryForNode(String configId) {
		return this.getByKey("queryForNode", buildMap("configId", configId));
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmMsgRuleDao#queryForMsgNode(java.lang.String)
	 */
	@Override
	public List<BpmMsgRule> queryForMsgNode(String configId) {
		return this.getByKey("queryForMsgNode", buildMap("configId", configId));
	}

	@Override
	public void removeByConfigId(String configId) {
		sqlSessionTemplate.delete(getNamespace() + ".removeByConfigId", configId);
	}

	
}
