package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.AttributeQuery;

public class AttributeDeleteQuery {
	
	protected List<AttributeQuery.Criteria> oredCriteria;
	
	public List<AttributeQuery.Criteria> getOredCriteria() {
        return oredCriteria;
    }
	

    public void or(AttributeQuery.Criteria criteria) {
        oredCriteria.add(criteria);
    }


    public AttributeQuery.Criteria or() {
    	AttributeQuery.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public AttributeQuery.Criteria createCriteria() {
    	AttributeQuery.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }


    protected AttributeQuery.Criteria createCriteriaInternal() {
    	AttributeQuery.Criteria criteria = new AttributeQuery.Criteria();
        return criteria;
    }
    
	public void clear() {
		oredCriteria.clear();
	}

    public AttributeDeleteQuery() {
    	oredCriteria = new ArrayList<AttributeQuery.Criteria>();
    }
}