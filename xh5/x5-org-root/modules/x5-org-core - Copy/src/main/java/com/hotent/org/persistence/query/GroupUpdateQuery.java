package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.persistence.query.GroupQuery.Criteria;

public class GroupUpdateQuery {
	
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

    public GroupUpdateQuery() {
    	oredCriteria = new ArrayList<Criteria>();
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
    		this.dataClauses = new ArrayList<GroupUpdateQuery.DataClause>();
		}
    	
    	public void setName(String name){
    		dataClauses.add(new DataClause("name_ = ",name));
    	}

    	public void setKey(String key){
    		dataClauses.add(new DataClause("key_ = ",key));
    	}

    	public void setDimKey(String dimKey){
    		dataClauses.add(new DataClause("dim_key_ = ",dimKey));
    	}

    	public void setRankTypeKey(String rankTypeKey){
    		dataClauses.add(new DataClause("rank_type_key_ = ",rankTypeKey));
    	}

    	public void setStatus(String status){
    		dataClauses.add(new DataClause("status_ = ",status));
    	}

    	public void setDesc(String desc){
    		dataClauses.add(new DataClause("desc_ = ",desc));
    	}

    	public void setSn(Integer sn){
    		dataClauses.add(new DataClause("sn_ = ",sn));
    	}

    	public void setParentId(String parentId){
    		dataClauses.add(new DataClause("parent_id_ = ",parentId));
    	}

    	public void setDepth(Integer depth){
    		dataClauses.add(new DataClause("depth_ = ",depth));
    	}

    	public void setPath(String path){
    		dataClauses.add(new DataClause("path_ = ",path));
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

    	public void setForm(String form){
    		dataClauses.add(new DataClause("form_ = ",form));
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