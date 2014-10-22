package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.RelationTypeQuery.Criteria;

public class RelationTypeUpdateQuery {
	
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

    public RelationTypeUpdateQuery() {
    	oredCriteria = new ArrayList<Criteria>();
    	updateClause = new UpdateClause();
    }

    public static class UpdateClause {
    	protected List<DataClause> dataClauses;
    	
    	protected UpdateClause() {
    		this.dataClauses = new ArrayList<RelationTypeUpdateQuery.DataClause>();
    	}
    	
    	public List<DataClause> getDataClauses() {
    		if(dataClauses.size()==0){
    			throw new OrgException("更新的数据，必须指定");
    		}
			return dataClauses;
		}
    	
    	public void setName(String name){
    		dataClauses.add(new DataClause("name_ = ",name));
    	}

    	public void setKey(String key){
    		dataClauses.add(new DataClause("key_ = ",key));
    	}

    	public void setType(String type){
    		dataClauses.add(new DataClause("type_ = ",type));
    	}

    	public void setConstType(String constType){
    		dataClauses.add(new DataClause("const_type_ = ",constType));
    	}

    	public void setCurrentName(String currentName){
    		dataClauses.add(new DataClause("current_name_ = ",currentName));
    	}

    	public void setRelName(String relName){
    		dataClauses.add(new DataClause("rel_name_ = ",relName));
    	}

    	public void setCurrentDimId(String currentDimId){
    		dataClauses.add(new DataClause("current_dim_id_ = ",currentDimId));
    	}

    	public void setRelDimId(String relDimId){
    		dataClauses.add(new DataClause("rel_dim_id_ = ",relDimId));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("status_ = ",status));
    	}

    	public void setMemo(String memo){
    		dataClauses.add(new DataClause("memo_ = ",memo));
    	}

    	public void setCreateBy(String createBy){
    		dataClauses.add(new DataClause("create_by_ = ",createBy));
    	}

    	public void setCreateTime(Date createTime){
    		dataClauses.add(new DataClause("create_time_ = ",createTime));
    	}

    	public void setCreateSaasId(String createSaasId){
    		dataClauses.add(new DataClause("create_saas_id_ = ",createSaasId));
    	}

    	public void setUpdateBy(String updateBy){
    		dataClauses.add(new DataClause("update_by_ = ",updateBy));
    	}

    	public void setUpdateTime(Date updateTime){
    		dataClauses.add(new DataClause("update_time_ = ",updateTime));
    	}

    	public void setIsBidirectonal(String isBidirectonal){
    		dataClauses.add(new DataClause("is_bidirectonal = ",isBidirectonal));
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