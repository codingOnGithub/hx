package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.hotent.org.persistence.query.RankTypeQuery.Criteria;

public class RankTypeDeleteQuery {
	
	protected List<Criteria> oredCriteria;
	
	public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }
	

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }


    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }


    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public RankTypeDeleteQuery() {
    	oredCriteria = new ArrayList<Criteria>();
    }

}