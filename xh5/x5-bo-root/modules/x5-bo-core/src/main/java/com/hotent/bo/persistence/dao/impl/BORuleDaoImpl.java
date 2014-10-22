package com.hotent.bo.persistence.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.bo.persistence.dao.BORuleDao;
import com.hotent.bo.persistence.model.BORule;

@Repository
public class BORuleDaoImpl extends MyBatisDaoImpl<String, BORule> implements BORuleDao{
    @Override
    public String getNamespace() {
        return BORule.class.getName();
    }

	@Override
	public List<BORule> getByAttributeId(String attributeId) {
		return getByKey("getByAttributeId", buildMap("attributeId", attributeId));
	}
	
	@Override
	public List<BORule> getRules(Map params)
	{
		return getByKey("getRules", params);
	}
	
	@Override
	public void removeRules(Map params)
	{
		deleteByKey("removeRules", params);
	}

	@Override
	public List<BORule> getRulesByAttrId(Map params)
	{
		return getByKey("getRulesByAttrId", params);
	}
}
