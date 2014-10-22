package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.hotent.base.api.query.FieldLogic;
import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.api.query.WhereClause;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.AttributeValueQuery;
import com.hotent.org.persistence.query.UserGroupRelationQuery;
import com.hotent.org.persistence.query.UserRelationQuery;
import com.hotent.org.persistence.query.UserQuery.Criterion;

public class UserGroupUpdateQuery {
	
	protected UpdateClause updateClause;
	
	protected List<UserGroupRelationQuery.Criteria> oredCriteria;
	
	public UpdateClause getUpdateClause() {
		return updateClause;
	}
	
	public List<UserGroupRelationQuery.Criteria> getOredCriteria() {
        return oredCriteria;
    }
	

    public void or(UserGroupRelationQuery.Criteria criteria) {
        oredCriteria.add(criteria);
    }


    public UserGroupRelationQuery.Criteria or() {
    	UserGroupRelationQuery.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public UserGroupRelationQuery.Criteria createCriteria() {
    	UserGroupRelationQuery.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }


    protected UserGroupRelationQuery.Criteria createCriteriaInternal() {
    	UserGroupRelationQuery.Criteria criteria = new UserGroupRelationQuery.Criteria();
        return criteria;
    }

    public UserGroupUpdateQuery() {
    	oredCriteria = new ArrayList<UserGroupRelationQuery.Criteria>();
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
    		this.dataClauses = new ArrayList<UserGroupUpdateQuery.DataClause>();
		}
    	
    	
    	//////////////////Set Clause
    	public void setCreateBy(String createBy){
    		dataClauses.add(new DataClause("CREATE_BY_ = ",createBy));
    	}

    	public void setCreateTime(Date createTime){
    		dataClauses.add(new DataClause("CREATE_TIME_ = ",createTime));
    	}

    	public void setDimId(String dimId){
    		dataClauses.add(new DataClause("DIM_ID_ = ",dimId));
    	}

    	public void setEndTime(Date endTime){
    		dataClauses.add(new DataClause("END_TIME_ = ",endTime));
    	}

    	public void setFrom(String from){
    		dataClauses.add(new DataClause("FROM_ = ",from));
    	}

    	public void setGroupId(String groupId){
    		dataClauses.add(new DataClause("GROUP_ID_ = ",groupId));
    	}

    	public void setId(String id){
    		dataClauses.add(new DataClause("ID_ = ",id));
    	}

    	public void setRelId(String relId){
    		dataClauses.add(new DataClause("REL_ID_ = ",relId));
    	}

    	public void setRelTypeId(String relTypeId){
    		dataClauses.add(new DataClause("REL_TYPE_ID_ = ",relTypeId));
    	}

    	public void setStartTime(Date startTime){
    		dataClauses.add(new DataClause("START_TIME_ = ",startTime));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("STATUS_ = ",status));
    	}

    	public void setUpdateBy(String updateBy){
    		dataClauses.add(new DataClause("UPDATE_BY_ = ",updateBy));
    	}

    	public void setUpdateTime(Date updateTime){
    		dataClauses.add(new DataClause("UPDATE_TIME_ = ",updateTime));
    	}

    	public void setUserId(String userId){
    		dataClauses.add(new DataClause("USER_ID_ = ",userId));
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