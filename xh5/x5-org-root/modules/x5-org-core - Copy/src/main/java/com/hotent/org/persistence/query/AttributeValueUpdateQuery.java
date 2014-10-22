package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.AttributeValueQuery.Criteria;

public class AttributeValueUpdateQuery {
	
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
    
    public AttributeValueUpdateQuery() {
    	this.oredCriteria = new ArrayList<Criteria>();
    	this.updateClause = new UpdateClause();
    }

    public static class UpdateClause {
    	protected List<DataClause> dataClauses;
    	
    	public List<DataClause> getDataClauses() {
    		if(dataClauses.size()==0){
    			throw new OrgException("更新的数据，必须指定");
    		}
			return dataClauses;
		}
    	
    	public void setAttrId(String attrId){
    		dataClauses.add(new DataClause("attr_id_ = ",attrId));
    	}

    	public void setAttrKey(String attrKey){
    		dataClauses.add(new DataClause("attr_key_ = ",attrKey));
    	}

    	public void setUserId(String userId){
    		dataClauses.add(new DataClause("user_id_ = ",userId));
    	}

    	public void setGroupId(String groupId){
    		dataClauses.add(new DataClause("group_id_ = ",groupId));
    	}

    	public void setDataType(String dataType){
    		dataClauses.add(new DataClause("data_type_ = ",dataType));
    	}

    	public void setTextVal(String textVal){
    		dataClauses.add(new DataClause("text_val = ",textVal));
    	}

    	public void setDateVal(Date dateVal){
    		dataClauses.add(new DataClause("date_val = ",dateVal));
    	}

    	public void setLongVal(Long longVal){
    		dataClauses.add(new DataClause("long_val = ",longVal));
    	}

    	public void setDecVal(Double decVal){
    		dataClauses.add(new DataClause("dec_val = ",decVal));
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