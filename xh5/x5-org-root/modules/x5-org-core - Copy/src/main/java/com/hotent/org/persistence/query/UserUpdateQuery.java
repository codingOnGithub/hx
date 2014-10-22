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
import com.hotent.org.persistence.query.UserQuery.Criteria;
import com.hotent.org.persistence.query.UserQuery.Criterion;

public class UserUpdateQuery {
	
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

    public UserUpdateQuery() {
    	oredCriteria = new ArrayList<UserQuery.Criteria>();
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
    		this.dataClauses = new ArrayList<UserUpdateQuery.DataClause>();
		}
    	

    	public void setAccount(String account){
    		dataClauses.add(new DataClause("account_ = ",account));
    	}

    	public void setFullname(String fullname){
    		dataClauses.add(new DataClause("fullname_ = ",fullname));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("status_ = ",status));
    	}

    	public void setPwd(String pwd){
    		dataClauses.add(new DataClause("pwd_ = ",pwd));
    	}

    	public void setSex(String sex){
    		dataClauses.add(new DataClause("sex_ = ",sex));
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

    	public void setFrom(String from){
    		dataClauses.add(new DataClause("from_ = ",from));
    	}

    	public void setMobile(String mobile){
    		dataClauses.add(new DataClause("mobile_ = ",mobile));
    	}

    	public void setEmail(String email){
    		dataClauses.add(new DataClause("email_ = ",email));
    	}

    	public void setAddress(String address){
    		dataClauses.add(new DataClause("address_ = ",address));
    	}

    	public void setQq(String qq){
    		dataClauses.add(new DataClause("qq_ = ",qq));
    	}

    	public void setPhone(String phone){
    		dataClauses.add(new DataClause("phone_ = ",phone));
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