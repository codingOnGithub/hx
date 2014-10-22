package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.GroupRelationQuery.Criteria;

public class GroupRelationUpdateQuery {
	
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

    public GroupRelationUpdateQuery() {
    	oredCriteria = new ArrayList<Criteria>();
    	updateClause = new UpdateClause();
    }

    public static class UpdateClause {
    	protected List<DataClause> dataClauses;
    	
    	public UpdateClause() {
			this.dataClauses = new ArrayList<GroupRelationUpdateQuery.DataClause>();
		}
    	
    	public List<DataClause> getDataClauses() {
    		if(dataClauses.size()==0){
    			throw new OrgException("更新的数据，必须指定");
    		}
			return dataClauses;
		}
    	

    	public void setId(String id){
    		dataClauses.add(new DataClause("id_ = ",id));
    	}

    	public void setRelTypeId(String relTypeId){
    		dataClauses.add(new DataClause("rel_type_id_ = ",relTypeId));
    	}

    	public void setGroupId(String groupId){
    		dataClauses.add(new DataClause("group_id_ = ",groupId));
    	}

    	public void setRelGroupId(String relGroupId){
    		dataClauses.add(new DataClause("rel_group_id_ = ",relGroupId));
    	}

    	public void setCurrentDimId(String currentDimId){
    		dataClauses.add(new DataClause("current_dim_id_ = ",currentDimId));
    	}

    	public void setRelDimId(String relDimId){
    		dataClauses.add(new DataClause("rel_dim_id_ = ",relDimId));
    	}

    	public void setIsCombination(String isCombination){
    		dataClauses.add(new DataClause("is_combination_ = ",isCombination));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("status_ = ",status));
    	}

    	public void setStartTime(Date startTime){
    		dataClauses.add(new DataClause("start_time_ = ",startTime));
    	}

    	public void setEndTime(Date endTime){
    		dataClauses.add(new DataClause("end_time_ = ",endTime));
    	}

    	public void setCreateBy(String createBy){
    		dataClauses.add(new DataClause("create_by_ = ",createBy));
    	}

    	public void setCreateTime(Date createTime){
    		dataClauses.add(new DataClause("create_time_ = ",createTime));
    	}

    	public void setUpdateBy(String updateBy){
    		dataClauses.add(new DataClause("update_by_ = ",updateBy));
    	}

    	public void setUpdateTime(Date updateTime){
    		dataClauses.add(new DataClause("update_time_ = ",updateTime));
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