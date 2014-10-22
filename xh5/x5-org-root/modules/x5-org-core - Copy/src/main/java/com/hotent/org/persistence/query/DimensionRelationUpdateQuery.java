package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.DimensionRelationQuery.Criteria;

public class DimensionRelationUpdateQuery {
	
	protected UpdateClause updateClause;
	
	protected List<Criteria> oredCriteria;
	
	public UpdateClause getUpdateClause() {
		return updateClause;
	}
	
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

    public DimensionRelationUpdateQuery() {
    	oredCriteria = new ArrayList<Criteria>();
    	updateClause = new UpdateClause();
    }

    public static class UpdateClause {
    	protected List<DataClause> dataClauses;
    	
    	public UpdateClause() {
    		dataClauses = new ArrayList<DimensionRelationUpdateQuery.DataClause>();
		}
    	
    	public List<DataClause> getDataClauses() {
    		if(dataClauses.size()==0){
    			throw new OrgException("更新的数据，必须指定");
    		}
			return dataClauses;
		}

    	public void setDimId(String dimId){
    		dataClauses.add(new DataClause("dim_id_ = ",dimId));
    	}

    	public void setRelDimId(String relDimId){
    		dataClauses.add(new DataClause("rel_dim_id_ = ",relDimId));
    	}

    	public void setRelType(String relType){
    		dataClauses.add(new DataClause("rel_type_ = ",relType));
    	}
    	

    }
    
    public static class DataClause {
        private String property;

        private Object value;

        public String getProperty() {
			return property;
		}
        
        public Object getValue() {
            return value;
        }

        protected DataClause(String property, Object value) {
            this.value = value;
            this.property = property;
        }
    }
    
}