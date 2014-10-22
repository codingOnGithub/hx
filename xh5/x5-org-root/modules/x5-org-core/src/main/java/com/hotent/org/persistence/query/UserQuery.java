package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.User.Status;

public class UserQuery {
	
	public static class FindQuery{

		    protected boolean distinct;

		    protected List<Criteria> oredCriteria;
		    
		    //TODO
		    protected List<UserGroupRelationQuery.Criteria> oredUserGroupCriteria;
		    
		    //TODO
		    protected List<AttributeValueQuery.Criteria> oredAttributeValueCriteria;
		    
		    //TODO
		    protected List<UserRelationQuery.Criteria> oredUserRelationCriteria;
		    
		    
		    protected OrderByClause orderByClause;
		    
		    public FindQuery() {
		        oredCriteria = new ArrayList<Criteria>();
		        oredUserGroupCriteria = new ArrayList<UserGroupRelationQuery.Criteria>();
		        oredAttributeValueCriteria = new ArrayList<AttributeValueQuery.Criteria>();
		        oredUserRelationCriteria = new ArrayList<UserRelationQuery.Criteria>();
		        orderByClause = new OrderByClause();
		    }


public OrderByClause getOrderByClause() {
			return orderByClause;
		}

		    public void setDistinct(boolean distinct) {
		        this.distinct = distinct;
		    }

		    public boolean isDistinct() {
		        return distinct;
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
		    
		    ///////////////////B oredUserGroupCriteria
		    public List<UserGroupRelationQuery.Criteria> getOredUserGroupCriteria() {
		        return oredUserGroupCriteria;
		    }
		    

		    public void orUserGroupCriteria(UserGroupRelationQuery.Criteria userGroupCriteria) {
		    	oredUserGroupCriteria.add(userGroupCriteria);
		    }

		 
		    public UserGroupRelationQuery.Criteria orUserGroupCriteria() {
		    	UserGroupRelationQuery.Criteria userGroupCriteria = createUserGroupCriteriaInternal();
		        oredUserGroupCriteria.add(userGroupCriteria);
		        return userGroupCriteria;
		    }

		  
		    public UserGroupRelationQuery.Criteria createUserGroupCriteria() {
		    	UserGroupRelationQuery.Criteria userGroupCriteria = createUserGroupCriteriaInternal();
		        if (oredUserGroupCriteria.size() == 0) {
		        	oredUserGroupCriteria.add(userGroupCriteria);
		        }
		        return userGroupCriteria;
		    }

		 
		    protected UserGroupRelationQuery.Criteria createUserGroupCriteriaInternal() {
		    	UserGroupRelationQuery.Criteria userGroupCriteria = new UserGroupRelationQuery.Criteria();
		        return userGroupCriteria;
		    }
		    ////////////////////E oredUserGroupCriteria
		    
		    
		    ///////////////////B  oredUserRelationCriteria
		    public List<UserRelationQuery.Criteria> getOredUserRelationCriteria() {
		        return oredUserRelationCriteria;
		    }
		    

		    public void orUserRelationCriteria(UserRelationQuery.Criteria userRelationriteria) {
		    	oredUserRelationCriteria.add(userRelationriteria);
		    }

		 
		    public UserRelationQuery.Criteria orUserRelationCriteria() {
		    	UserRelationQuery.Criteria userRelationriteria = createUserRelationCriteriaInternal();
		    	oredUserRelationCriteria.add(userRelationriteria);
		        return userRelationriteria;
		    }

		  
		    public UserRelationQuery.Criteria createUserRelationCriteria() {
		    	UserRelationQuery.Criteria userRelationriteria = createUserRelationCriteriaInternal();
		        if (oredUserRelationCriteria.size() == 0) {
		        	oredUserRelationCriteria.add(userRelationriteria);
		        }
		        return userRelationriteria;
		    }

		 
		    protected UserRelationQuery.Criteria createUserRelationCriteriaInternal() {
		    	UserRelationQuery.Criteria userRelationriteria = new UserRelationQuery.Criteria();
		        return userRelationriteria;
		    }
		    ////////////////////E oredUserRelationCriteria
		    
			///////////////////B oredAttributeValueCriteria
			public List<AttributeValueQuery.Criteria> getOredAttributeValueCriteria() {
				return oredAttributeValueCriteria;
			}

			public void orAttributeValueCriteria(AttributeValueQuery.Criteria attributeValueCriteria) {
				oredAttributeValueCriteria.add(attributeValueCriteria);
			}

			public AttributeValueQuery.Criteria orAttributeValueCriteria() {
				AttributeValueQuery.Criteria attributeValueCriteria = createAttributeValueCriteriaInternal();
				oredAttributeValueCriteria.add(attributeValueCriteria);
				return attributeValueCriteria;
			}

			public AttributeValueQuery.Criteria createAttributeValueCriteria() {
				AttributeValueQuery.Criteria attributeValueCriteria = createAttributeValueCriteriaInternal();
				if (oredAttributeValueCriteria.size() == 0) {
					oredAttributeValueCriteria.add(attributeValueCriteria);
				}
				return attributeValueCriteria;
			}

			protected AttributeValueQuery.Criteria createAttributeValueCriteriaInternal() {
				AttributeValueQuery.Criteria attributeValueCriteria = new AttributeValueQuery.Criteria();
				return attributeValueCriteria;
			}

			// //////////////////E oredAttributeValueCriteria
		    
		   
		    public void clear() {
		        oredCriteria.clear();
		        oredUserGroupCriteria.clear();
		        oredAttributeValueCriteria.clear();
		        oredUserRelationCriteria.clear();
		        orderByClause = null;
		        distinct = false;
		    }
		    
//		    public void isJoinGroup(){
//		    	for(UserGroupRelationQuery.Criteria c:oredUserGroupCriteria){
//		    		if(c.isValid()){
//		    			return true;
//		    		}
//		    	}
//		    	return false;
//		    }

		    public boolean isJoinUserGroupRelation(){
		    	for(UserGroupRelationQuery.Criteria c:oredUserGroupCriteria){
		    		if(c.isValid()){
		    			return true;
		    		}
		    	}
		    	return false;
		    }

		    
		    public boolean isJoinAttributeValue(){
		    	for(AttributeValueQuery.Criteria c:oredAttributeValueCriteria){
		    		if(c.isValid()){
		    			return true;
		    		}
		    	}
		    	return false;
		    }
		    
		    public boolean isJoinUserRelation(){
		    	for(UserRelationQuery.Criteria c:oredUserRelationCriteria){
		    		if(c.isValid()){
		    			return true;
		    		}
		    	}
		    	return false;
		    }
	}
	
	
	public static class UpdateQuery {
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

	    public UpdateQuery() {
	    	oredCriteria = new ArrayList<UserQuery.Criteria>();
	    	updateClause = new UpdateClause();
	    }
	}

	public static class DeleteQuery {
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

	    public DeleteQuery() {
	    	oredCriteria = new ArrayList<UserQuery.Criteria>();
	    }
	
	}
		
   
 
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value,property));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2, property));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id_ is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id_ is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id_ =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id_ <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id_ >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id_ >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id_ <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id_ <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id_ like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id_ not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id_ in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id_ not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id_ between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id_ not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account_ is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account_ is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account_ =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account_ <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account_ >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account_ >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account_ <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account_ <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account_ like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account_ not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account_ in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account_ not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account_ between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account_ not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNull() {
            addCriterion("fullname_ is null");
            return (Criteria) this;
        }

        public Criteria andFullnameIsNotNull() {
            addCriterion("fullname_ is not null");
            return (Criteria) this;
        }

        public Criteria andFullnameEqualTo(String value) {
            addCriterion("fullname_ =", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotEqualTo(String value) {
            addCriterion("fullname_ <>", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThan(String value) {
            addCriterion("fullname_ >", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameGreaterThanOrEqualTo(String value) {
            addCriterion("fullname_ >=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThan(String value) {
            addCriterion("fullname_ <", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLessThanOrEqualTo(String value) {
            addCriterion("fullname_ <=", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameLike(String value) {
            addCriterion("fullname_ like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotLike(String value) {
            addCriterion("fullname_ not like", value, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameIn(List<String> values) {
            addCriterion("fullname_ in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotIn(List<String> values) {
            addCriterion("fullname_ not in", values, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameBetween(String value1, String value2) {
            addCriterion("fullname_ between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andFullnameNotBetween(String value1, String value2) {
            addCriterion("fullname_ not between", value1, value2, "fullname");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status_ is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status_ is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Status value) {
            addCriterion("status_ =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Status value) {
            addCriterion("status_ <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Status value) {
            addCriterion("status_ >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Status value) {
            addCriterion("status_ >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Status value) {
            addCriterion("status_ <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Status value) {
            addCriterion("status_ <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(Status value) {
            addCriterion("status_ like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(Status value) {
            addCriterion("status_ not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Status> values) {
            addCriterion("status_ in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Status> values) {
            addCriterion("status_ not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Status value1, Status value2) {
            addCriterion("status_ between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Status value1, Status value2) {
            addCriterion("status_ not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPwdIsNull() {
            addCriterion("pwd_ is null");
            return (Criteria) this;
        }

        public Criteria andPwdIsNotNull() {
            addCriterion("pwd_ is not null");
            return (Criteria) this;
        }

        public Criteria andPwdEqualTo(String value) {
            addCriterion("pwd_ =", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotEqualTo(String value) {
            addCriterion("pwd_ <>", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThan(String value) {
            addCriterion("pwd_ >", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("pwd_ >=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThan(String value) {
            addCriterion("pwd_ <", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("pwd_ <=", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdLike(String value) {
            addCriterion("pwd_ like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotLike(String value) {
            addCriterion("pwd_ not like", value, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdIn(List<String> values) {
            addCriterion("pwd_ in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotIn(List<String> values) {
            addCriterion("pwd_ not in", values, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdBetween(String value1, String value2) {
            addCriterion("pwd_ between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andPwdNotBetween(String value1, String value2) {
            addCriterion("pwd_ not between", value1, value2, "pwd");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex_ is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex_ is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex_ =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex_ <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex_ >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex_ >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex_ <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex_ <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex_ like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex_ not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex_ in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex_ not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex_ between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex_ not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by_ is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by_ is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by_ =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by_ <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by_ >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by_ >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by_ <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by_ <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by_ like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by_ not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by_ in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by_ not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by_ between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by_ not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time_ is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time_ is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time_ =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time_ <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time_ >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time_ >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time_ <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time_ <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time_ in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time_ not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time_ between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time_ not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdIsNull() {
            addCriterion("create_saas_id_ is null");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdIsNotNull() {
            addCriterion("create_saas_id_ is not null");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdEqualTo(String value) {
            addCriterion("create_saas_id_ =", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdNotEqualTo(String value) {
            addCriterion("create_saas_id_ <>", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdGreaterThan(String value) {
            addCriterion("create_saas_id_ >", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_saas_id_ >=", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdLessThan(String value) {
            addCriterion("create_saas_id_ <", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdLessThanOrEqualTo(String value) {
            addCriterion("create_saas_id_ <=", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdLike(String value) {
            addCriterion("create_saas_id_ like", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdNotLike(String value) {
            addCriterion("create_saas_id_ not like", value, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdIn(List<String> values) {
            addCriterion("create_saas_id_ in", values, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdNotIn(List<String> values) {
            addCriterion("create_saas_id_ not in", values, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdBetween(String value1, String value2) {
            addCriterion("create_saas_id_ between", value1, value2, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andCreateSaasIdNotBetween(String value1, String value2) {
            addCriterion("create_saas_id_ not between", value1, value2, "createSaasId");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by_ is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by_ is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by_ =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by_ <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by_ >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by_ >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by_ <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by_ <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by_ like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by_ not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by_ in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by_ not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by_ between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by_ not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time_ is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time_ is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time_ =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time_ <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time_ >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time_ >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time_ <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time_ <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time_ in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time_ not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time_ between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time_ not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andFromIsNull() {
            addCriterion("from_ is null");
            return (Criteria) this;
        }

        public Criteria andFromIsNotNull() {
            addCriterion("from_ is not null");
            return (Criteria) this;
        }

        public Criteria andFromEqualTo(String value) {
            addCriterion("from_ =", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotEqualTo(String value) {
            addCriterion("from_ <>", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromGreaterThan(String value) {
            addCriterion("from_ >", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromGreaterThanOrEqualTo(String value) {
            addCriterion("from_ >=", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLessThan(String value) {
            addCriterion("from_ <", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLessThanOrEqualTo(String value) {
            addCriterion("from_ <=", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLike(String value) {
            addCriterion("from_ like", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotLike(String value) {
            addCriterion("from_ not like", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromIn(List<String> values) {
            addCriterion("from_ in", values, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotIn(List<String> values) {
            addCriterion("from_ not in", values, "from");
            return (Criteria) this;
        }

        public Criteria andFromBetween(String value1, String value2) {
            addCriterion("from_ between", value1, value2, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotBetween(String value1, String value2) {
            addCriterion("from_ not between", value1, value2, "from");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile_ is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile_ is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile_ =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile_ <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile_ >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile_ >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile_ <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile_ <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile_ like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile_ not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile_ in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile_ not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile_ between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile_ not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email_ is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email_ is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email_ =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email_ <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email_ >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email_ >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email_ <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email_ <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email_ like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email_ not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email_ in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email_ not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email_ between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email_ not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address_ is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address_ is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address_ =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address_ <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address_ >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address_ >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address_ <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address_ <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address_ like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address_ not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address_ in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address_ not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address_ between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address_ not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq_ is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq_ is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq_ =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq_ <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq_ >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq_ >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq_ <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq_ <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq_ like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq_ not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq_ in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq_ not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq_ between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq_ not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone_ is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone_ is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone_ =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone_ <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone_ >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone_ >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone_ <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone_ <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone_ like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone_ not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone_ in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone_ not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone_ between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone_ not between", value1, value2, "phone");
            return (Criteria) this;
        }
        
//        
//        //TODO
//        public Criteria andMemberOfGroup(String groupId) {
//            addCriterion("phone_ not between", value1, "groupId");
//            return (Criteria) this;
//        } 
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table xog_user
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 11 19:26:31 CST 2014
     */
    public static class Criteria extends GeneratedCriteria {
    	
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table xog_user
     *
     * @mbggenerated Tue Feb 11 19:26:31 CST 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;
        
        private String property;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }
        
        public String getProperty() {
			return property;
		}

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value,String property) {
            super();
            this.condition = condition;
            this.value = value;
            this.property = property;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue,String property) {
            super();
            this.condition = condition;
            this.value = value;
            this.property = property;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
        
    }
    

    public static class UpdateClause {
    	protected List<SetData> dataClauses;
    	
    	public List<SetData> getdataClauses() {
    		if(dataClauses.size()==0){
    			throw new OrgException("更新的数据，必须指定");
    		}
			return dataClauses;
		}
    	
    	protected UpdateClause() {
    		this.dataClauses = new ArrayList<SetData>();
		}
    	

    	public void setAccount(String account){
    		dataClauses.add(new SetData("account_ = ",account));
    	}

    	public void setFullname(String fullname){
    		dataClauses.add(new SetData("fullname_ = ",fullname));
    	}

    	public void setStatus(Status status){
    		dataClauses.add(new SetData("status_ = ",status));
    	}

    	public void setPwd(String pwd){
    		dataClauses.add(new SetData("pwd_ = ",pwd));
    	}

    	public void setSex(String sex){
    		dataClauses.add(new SetData("sex_ = ",sex));
    	}

    	public void setCreateBy(String createBy){
    		dataClauses.add(new SetData("create_by_ = ",createBy));
    	}

    	public void setCreateTime(Date createTime){
    		dataClauses.add(new SetData("create_time_ = ",createTime));
    	}

    	public void setCreateSaasId(String createSaasId){
    		dataClauses.add(new SetData("create_saas_id_ = ",createSaasId));
    	}

    	public void setUpdateBy(String updateBy){
    		dataClauses.add(new SetData("update_by_ = ",updateBy));
    	}

    	public void setUpdateTime(Date updateTime){
    		dataClauses.add(new SetData("update_time_ = ",updateTime));
    	}

    	public void setFrom(String from){
    		dataClauses.add(new SetData("from_ = ",from));
    	}

    	public void setMobile(String mobile){
    		dataClauses.add(new SetData("mobile_ = ",mobile));
    	}

    	public void setEmail(String email){
    		dataClauses.add(new SetData("email_ = ",email));
    	}

    	public void setAddress(String address){
    		dataClauses.add(new SetData("address_ = ",address));
    	}

    	public void setQq(String qq){
    		dataClauses.add(new SetData("qq_ = ",qq));
    	}

    	public void setPhone(String phone){
    		dataClauses.add(new SetData("phone_ = ",phone));
    	}

    }
    public static class OrderByClause{
		protected List<OrderBy> orderBys;
		public List<OrderBy> getOrderBys() {
			return orderBys;
		}
		
		public boolean isValid(){
			return orderBys!=null && !orderBys.isEmpty();
		}
		
		public void clear() {
			orderBys.clear();
		}

		public OrderByClause() {
			this.orderBys = new ArrayList<OrderBy>();
		}

		
	}    
}