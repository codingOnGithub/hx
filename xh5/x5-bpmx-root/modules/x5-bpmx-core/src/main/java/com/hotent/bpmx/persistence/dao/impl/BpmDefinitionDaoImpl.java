package com.hotent.bpmx.persistence.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bpmx.persistence.dao.BpmDefinitionDao;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;

@Repository
public class BpmDefinitionDaoImpl extends MyBatisDaoImpl<String, DefaultBpmDefinition> implements BpmDefinitionDao{

    @Override
    public String getNamespace() {
        return DefaultBpmDefinition.class.getName();
    }

	@Override
	public void updateMainDefId(String mainDefId, String defKey) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("mainDefId", mainDefId);
		params.put("defKey", defKey);
		updateByKey("updateMainDefId", params);
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmDefinitionDao#getByDefKey(java.lang.String)
	 */
	@Override
	public DefaultBpmDefinition getMainByDefKey(String defKey) {
		return this.getUnique("getMainByDefKey", buildMap("defKey", defKey));
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmDefinitionDao#getMaxVersion(java.lang.String)
	 */
	@Override
	public DefaultBpmDefinition getMaxVersion(String defKey) {
		return this.getUnique("getMaxVersion", buildMap("defKey", defKey));
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmDefinitionDao#queryByDefKey(java.lang.String)
	 */
	@Override
	public List<DefaultBpmDefinition> queryByDefKey(String defKey) {
		return this.getByKey("queryByDefKey", buildMap("defKey", defKey));
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.persistence.dao.BpmDefinitionDao#queryHistorys(java.lang.String)
	 */
	@Override
	public List<DefaultBpmDefinition> queryHistorys(String defKey) {
		return this.getByKey("queryHistorys", buildMap("defKey", defKey));
	}
	
	@Override
	public DefaultBpmDefinition getByBpmnDefId(String bpmnDefId) {
		return this.getUnique("getByBpmnDefId", buildMap("bpmnDefId",bpmnDefId));
	}
	
}
