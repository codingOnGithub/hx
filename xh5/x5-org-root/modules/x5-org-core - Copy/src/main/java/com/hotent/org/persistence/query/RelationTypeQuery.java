package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelationTypeQuery {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    //TODO
    protected List<UserGroupRelationQuery.Criteria> oredUserGroupCriteria;
    
    //TODO
    protected List<UserRelationQuery.Criteria> oredUserRelationCriteria;
    
    //TODO
    protected List<GroupRelationQuery.Criteria> oredGroupRelationCriteria;
    

    public RelationTypeQuery() {
        oredCriteria = new ArrayList<Criteria>();
        oredUserGroupCriteria = new ArrayList<UserGroupRelationQuery.Criteria>();
        oredUserRelationCriteria = new ArrayList<UserRelationQuery.Criteria>();
        oredGroupRelationCriteria = new ArrayList<GroupRelationQuery.Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
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
    ///////////////////B  oredGroupRelationCriteria
    public List<GroupRelationQuery.Criteria> getOredGroupRelationCriteria() {
        return oredGroupRelationCriteria;
    }
    

    public void orGroupRelationCriteria(GroupRelationQuery.Criteria userRelationriteria) {
    	oredGroupRelationCriteria.add(userRelationriteria);
    }

 
    public GroupRelationQuery.Criteria orGroupRelationCriteria() {
    	GroupRelationQuery.Criteria userRelationriteria = createGroupRelationCriteriaInternal();
    	oredGroupRelationCriteria.add(userRelationriteria);
        return userRelationriteria;
    }

  
    public GroupRelationQuery.Criteria createGroupRelationCriteria() {
    	GroupRelationQuery.Criteria userRelationriteria = createGroupRelationCriteriaInternal();
        if (oredGroupRelationCriteria.size() == 0) {
        	oredGroupRelationCriteria.add(userRelationriteria);
        }
        return userRelationriteria;
    }

 
    protected GroupRelationQuery.Criteria createGroupRelationCriteriaInternal() {
    	GroupRelationQuery.Criteria userRelationriteria = new GroupRelationQuery.Criteria();
        return userRelationriteria;
    }
    ////////////////////E oredGroupRelationCriteria
    
    public void clear() {
        oredCriteria.clear();
        oredGroupRelationCriteria.clear();
        oredUserGroupCriteria.clear();
        oredUserRelationCriteria.clear();
        orderByClause = null;
        distinct = false;
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
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id_ is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id_ is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id_ =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id_ <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id_ >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id_ >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id_ <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id_ <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id_ like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id_ not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id_ in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id_ not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id_ between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id_ not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name_ is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name_ is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name_ =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name_ <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name_ >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name_ >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name_ <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name_ <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name_ like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name_ not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name_ in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name_ not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name_ between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name_ not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andKeyIsNull() {
            addCriterion("key_ is null");
            return (Criteria) this;
        }

        public Criteria andKeyIsNotNull() {
            addCriterion("key_ is not null");
            return (Criteria) this;
        }

        public Criteria andKeyEqualTo(String value) {
            addCriterion("key_ =", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotEqualTo(String value) {
            addCriterion("key_ <>", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThan(String value) {
            addCriterion("key_ >", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyGreaterThanOrEqualTo(String value) {
            addCriterion("key_ >=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThan(String value) {
            addCriterion("key_ <", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLessThanOrEqualTo(String value) {
            addCriterion("key_ <=", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyLike(String value) {
            addCriterion("key_ like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotLike(String value) {
            addCriterion("key_ not like", value, "key");
            return (Criteria) this;
        }

        public Criteria andKeyIn(List<String> values) {
            addCriterion("key_ in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotIn(List<String> values) {
            addCriterion("key_ not in", values, "key");
            return (Criteria) this;
        }

        public Criteria andKeyBetween(String value1, String value2) {
            addCriterion("key_ between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andKeyNotBetween(String value1, String value2) {
            addCriterion("key_ not between", value1, value2, "key");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type_ is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type_ is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type_ =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type_ <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type_ >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type_ >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type_ <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type_ <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type_ like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type_ not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type_ in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type_ not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type_ between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type_ not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andConstTypeIsNull() {
            addCriterion("const_type_ is null");
            return (Criteria) this;
        }

        public Criteria andConstTypeIsNotNull() {
            addCriterion("const_type_ is not null");
            return (Criteria) this;
        }

        public Criteria andConstTypeEqualTo(String value) {
            addCriterion("const_type_ =", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotEqualTo(String value) {
            addCriterion("const_type_ <>", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeGreaterThan(String value) {
            addCriterion("const_type_ >", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeGreaterThanOrEqualTo(String value) {
            addCriterion("const_type_ >=", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeLessThan(String value) {
            addCriterion("const_type_ <", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeLessThanOrEqualTo(String value) {
            addCriterion("const_type_ <=", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeLike(String value) {
            addCriterion("const_type_ like", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotLike(String value) {
            addCriterion("const_type_ not like", value, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeIn(List<String> values) {
            addCriterion("const_type_ in", values, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotIn(List<String> values) {
            addCriterion("const_type_ not in", values, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeBetween(String value1, String value2) {
            addCriterion("const_type_ between", value1, value2, "constType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotBetween(String value1, String value2) {
            addCriterion("const_type_ not between", value1, value2, "constType");
            return (Criteria) this;
        }

        public Criteria andCurrentNameIsNull() {
            addCriterion("current_name_ is null");
            return (Criteria) this;
        }

        public Criteria andCurrentNameIsNotNull() {
            addCriterion("current_name_ is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentNameEqualTo(String value) {
            addCriterion("current_name_ =", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameNotEqualTo(String value) {
            addCriterion("current_name_ <>", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameGreaterThan(String value) {
            addCriterion("current_name_ >", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameGreaterThanOrEqualTo(String value) {
            addCriterion("current_name_ >=", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameLessThan(String value) {
            addCriterion("current_name_ <", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameLessThanOrEqualTo(String value) {
            addCriterion("current_name_ <=", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameLike(String value) {
            addCriterion("current_name_ like", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameNotLike(String value) {
            addCriterion("current_name_ not like", value, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameIn(List<String> values) {
            addCriterion("current_name_ in", values, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameNotIn(List<String> values) {
            addCriterion("current_name_ not in", values, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameBetween(String value1, String value2) {
            addCriterion("current_name_ between", value1, value2, "currentName");
            return (Criteria) this;
        }

        public Criteria andCurrentNameNotBetween(String value1, String value2) {
            addCriterion("current_name_ not between", value1, value2, "currentName");
            return (Criteria) this;
        }

        public Criteria andRelNameIsNull() {
            addCriterion("rel_name_ is null");
            return (Criteria) this;
        }

        public Criteria andRelNameIsNotNull() {
            addCriterion("rel_name_ is not null");
            return (Criteria) this;
        }

        public Criteria andRelNameEqualTo(String value) {
            addCriterion("rel_name_ =", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameNotEqualTo(String value) {
            addCriterion("rel_name_ <>", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameGreaterThan(String value) {
            addCriterion("rel_name_ >", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameGreaterThanOrEqualTo(String value) {
            addCriterion("rel_name_ >=", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameLessThan(String value) {
            addCriterion("rel_name_ <", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameLessThanOrEqualTo(String value) {
            addCriterion("rel_name_ <=", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameLike(String value) {
            addCriterion("rel_name_ like", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameNotLike(String value) {
            addCriterion("rel_name_ not like", value, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameIn(List<String> values) {
            addCriterion("rel_name_ in", values, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameNotIn(List<String> values) {
            addCriterion("rel_name_ not in", values, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameBetween(String value1, String value2) {
            addCriterion("rel_name_ between", value1, value2, "relName");
            return (Criteria) this;
        }

        public Criteria andRelNameNotBetween(String value1, String value2) {
            addCriterion("rel_name_ not between", value1, value2, "relName");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdIsNull() {
            addCriterion("current_dim_id_ is null");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdIsNotNull() {
            addCriterion("current_dim_id_ is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdEqualTo(String value) {
            addCriterion("current_dim_id_ =", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdNotEqualTo(String value) {
            addCriterion("current_dim_id_ <>", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdGreaterThan(String value) {
            addCriterion("current_dim_id_ >", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdGreaterThanOrEqualTo(String value) {
            addCriterion("current_dim_id_ >=", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdLessThan(String value) {
            addCriterion("current_dim_id_ <", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdLessThanOrEqualTo(String value) {
            addCriterion("current_dim_id_ <=", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdLike(String value) {
            addCriterion("current_dim_id_ like", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdNotLike(String value) {
            addCriterion("current_dim_id_ not like", value, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdIn(List<String> values) {
            addCriterion("current_dim_id_ in", values, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdNotIn(List<String> values) {
            addCriterion("current_dim_id_ not in", values, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdBetween(String value1, String value2) {
            addCriterion("current_dim_id_ between", value1, value2, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andCurrentDimIdNotBetween(String value1, String value2) {
            addCriterion("current_dim_id_ not between", value1, value2, "currentDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdIsNull() {
            addCriterion("rel_dim_id_ is null");
            return (Criteria) this;
        }

        public Criteria andRelDimIdIsNotNull() {
            addCriterion("rel_dim_id_ is not null");
            return (Criteria) this;
        }

        public Criteria andRelDimIdEqualTo(String value) {
            addCriterion("rel_dim_id_ =", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdNotEqualTo(String value) {
            addCriterion("rel_dim_id_ <>", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdGreaterThan(String value) {
            addCriterion("rel_dim_id_ >", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdGreaterThanOrEqualTo(String value) {
            addCriterion("rel_dim_id_ >=", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdLessThan(String value) {
            addCriterion("rel_dim_id_ <", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdLessThanOrEqualTo(String value) {
            addCriterion("rel_dim_id_ <=", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdLike(String value) {
            addCriterion("rel_dim_id_ like", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdNotLike(String value) {
            addCriterion("rel_dim_id_ not like", value, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdIn(List<String> values) {
            addCriterion("rel_dim_id_ in", values, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdNotIn(List<String> values) {
            addCriterion("rel_dim_id_ not in", values, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdBetween(String value1, String value2) {
            addCriterion("rel_dim_id_ between", value1, value2, "relDimId");
            return (Criteria) this;
        }

        public Criteria andRelDimIdNotBetween(String value1, String value2) {
            addCriterion("rel_dim_id_ not between", value1, value2, "relDimId");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status_ =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status_ <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status_ >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status_ >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status_ <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status_ <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status_ like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status_ not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status_ in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status_ not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status_ between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status_ not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo_ is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo_ is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo_ =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo_ <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo_ >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo_ >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo_ <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo_ <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo_ like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo_ not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo_ in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo_ not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo_ between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo_ not between", value1, value2, "memo");
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

        public Criteria andIsBidirectonalIsNull() {
            addCriterion("is_bidirectonal is null");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalIsNotNull() {
            addCriterion("is_bidirectonal is not null");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalEqualTo(String value) {
            addCriterion("is_bidirectonal =", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalNotEqualTo(String value) {
            addCriterion("is_bidirectonal <>", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalGreaterThan(String value) {
            addCriterion("is_bidirectonal >", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalGreaterThanOrEqualTo(String value) {
            addCriterion("is_bidirectonal >=", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalLessThan(String value) {
            addCriterion("is_bidirectonal <", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalLessThanOrEqualTo(String value) {
            addCriterion("is_bidirectonal <=", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalLike(String value) {
            addCriterion("is_bidirectonal like", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalNotLike(String value) {
            addCriterion("is_bidirectonal not like", value, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalIn(List<String> values) {
            addCriterion("is_bidirectonal in", values, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalNotIn(List<String> values) {
            addCriterion("is_bidirectonal not in", values, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalBetween(String value1, String value2) {
            addCriterion("is_bidirectonal between", value1, value2, "isBidirectonal");
            return (Criteria) this;
        }

        public Criteria andIsBidirectonalNotBetween(String value1, String value2) {
            addCriterion("is_bidirectonal not between", value1, value2, "isBidirectonal");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

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

        protected Criterion(String condition, Object value) {
            super();
            this.condition = condition;
            this.value = value;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.betweenValue = true;
        }
    }
}