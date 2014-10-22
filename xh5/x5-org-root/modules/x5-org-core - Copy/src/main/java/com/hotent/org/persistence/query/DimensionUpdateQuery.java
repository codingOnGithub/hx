package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.DimensionQuery.Criteria;

public class DimensionUpdateQuery {
	
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

    public DimensionUpdateQuery() {
    	oredCriteria = new ArrayList<DimensionQuery.Criteria>();
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
    		this.dataClauses = new ArrayList<DimensionUpdateQuery.DataClause>();
		}
    	

    	public void setName(String name){
    		dataClauses.add(new DataClause("name_ = ",name));
    	}

    	public void setDimKey(String dimKey){
    		dataClauses.add(new DataClause("dim_key_ = ",dimKey));
    	}

    	public void setIsCombination(String isCombination){
    		dataClauses.add(new DataClause("is_combination_ = ",isCombination));
    	}

    	public void setIsSystem(String isSystem){
    		dataClauses.add(new DataClause("is_system_ = ",isSystem));
    	}

    	public void setSn(Integer sn){
    		dataClauses.add(new DataClause("sn_ = ",sn));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("status_ = ",status));
    	}

    	public void setShowType(String showType){
    		dataClauses.add(new DataClause("show_type_ = ",showType));
    	}

    	public void setGradeAuth(String gradeAuth){
    		dataClauses.add(new DataClause("grade_auth_ = ",gradeAuth));
    	}

    	public void setAllowType(String allowType){
    		dataClauses.add(new DataClause("allow_type_ = ",allowType));
    	}

    	public void setDesc(String desc){
    		dataClauses.add(new DataClause("desc_ = ",desc));
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