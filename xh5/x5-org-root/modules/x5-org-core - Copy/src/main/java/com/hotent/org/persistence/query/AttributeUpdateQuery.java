package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.AttributeQuery;

public class AttributeUpdateQuery {
	
	protected UpdateClause updateClause;
	
	protected List<AttributeQuery.Criteria> oredCriteria;
	
	public UpdateClause getUpdateClause() {
		return updateClause;
	}
	
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

    public AttributeUpdateQuery() {
    	oredCriteria = new ArrayList<AttributeQuery.Criteria>();
    	updateClause = new UpdateClause();
    }

    public static class UpdateClause {
    	protected List<DataClause> dataClauses;
    	
    	public List<DataClause> getDataClauses() {
    		if(dataClauses.size()==0){
    			throw new OrgException("更新的数据，必须指定");
    		}
			return dataClauses;
		}
    	
    	protected UpdateClause() {
    		this.dataClauses = new ArrayList<AttributeUpdateQuery.DataClause>();
		}
    	
    	
    	//////////////////Set Clause

    	public void setName(String name){
    		dataClauses.add(new DataClause("name_ = ",name));
    	}

    	public void setAttrKey(String attrKey){
    		dataClauses.add(new DataClause("attr_key_ = ",attrKey));
    	}

    	public void setBelongType(String belongType){
    		dataClauses.add(new DataClause("belong_type_ = ",belongType));
    	}

    	public void setDataType(String dataType){
    		dataClauses.add(new DataClause("data_type_ = ",dataType));
    	}

    	public void setDesc(String desc){
    		dataClauses.add(new DataClause("desc_ = ",desc));
    	}

    	public void setCreateOrgId(String createOrgId){
    		dataClauses.add(new DataClause("create_org_id_ = ",createOrgId));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("status_ = ",status));
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