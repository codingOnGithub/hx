package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.base.api.query.Direction;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Group.Status;
import com.hotent.org.persistence.query.DimensionQuery.OrderByClause;

public class GroupQuery {

	public static class FindQuery {

		protected boolean distinct;

		protected List<Criteria> oredCriteria;

		// TODO
		protected List<GroupRelationQuery.Criteria> oredGroupRelationCriteria;

		// TODO
		protected List<UserGroupRelationQuery.Criteria> oredUserGroupCriteria;

		// TODO
		protected List<AttributeValueQuery.Criteria> oredAttributeValueCriteria;

		protected OrderByClause orderByClause;
		public FindQuery() {
			oredCriteria = new ArrayList<Criteria>();
			oredUserGroupCriteria = new ArrayList<UserGroupRelationQuery.Criteria>();
			oredAttributeValueCriteria = new ArrayList<AttributeValueQuery.Criteria>();
			oredGroupRelationCriteria = new ArrayList<GroupRelationQuery.Criteria>();
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

		// /////////////////B oredUserGroupCriteria
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

		// //////////////////E oredUserGroupCriteria

		// /////////////////B oredGroupRelationCriteria
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

		// //////////////////E oredGroupRelationCriteria

		// /////////////////B oredAttributeValueCriteria
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

		// //////////////////join table
		public boolean isJoinUserGroupRelation() {
			for (UserGroupRelationQuery.Criteria c : oredUserGroupCriteria) {
				if (c.isValid()) {
					return true;
				}
			}
			return false;
		}

		public boolean isJoinAttributeValue() {
			for (AttributeValueQuery.Criteria c : oredAttributeValueCriteria) {
				if (c.isValid()) {
					return true;
				}
			}
			return false;
		}

		public boolean isJoinGroupRelation() {
			for (GroupRelationQuery.Criteria c : oredGroupRelationCriteria) {
				if (c.isValid()) {
					return true;
				}
			}
			return false;
		}

		public void clear() {
			oredCriteria.clear();
			oredUserGroupCriteria.clear();
			oredAttributeValueCriteria.clear();
			oredGroupRelationCriteria.clear();
			orderByClause.clear();
			distinct = false;
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
			oredCriteria = new ArrayList<Criteria>();
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
			oredCriteria = new ArrayList<Criteria>();
		}

	}

	// protected String orderByClause;

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

		public Criteria andGroupIdIsNull() {
			addCriterion("group_id_ is null");
			return (Criteria) this;
		}

		public Criteria andGroupIdIsNotNull() {
			addCriterion("group_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andGroupIdEqualTo(String value) {
			addCriterion("group_id_ =", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotEqualTo(String value) {
			addCriterion("group_id_ <>", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdGreaterThan(String value) {
			addCriterion("group_id_ >", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdGreaterThanOrEqualTo(String value) {
			addCriterion("group_id_ >=", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLessThan(String value) {
			addCriterion("group_id_ <", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLessThanOrEqualTo(String value) {
			addCriterion("group_id_ <=", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdLike(String value) {
			addCriterion("group_id_ like", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotLike(String value) {
			addCriterion("group_id_ not like", value, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdIn(List<String> values) {
			addCriterion("group_id_ in", values, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotIn(List<String> values) {
			addCriterion("group_id_ not in", values, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdBetween(String value1, String value2) {
			addCriterion("group_id_ between", value1, value2, "groupId");
			return (Criteria) this;
		}

		public Criteria andGroupIdNotBetween(String value1, String value2) {
			addCriterion("group_id_ not between", value1, value2, "groupId");
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

		public Criteria andDimIdIsNull() {
			addCriterion("dim_id_ is null");
			return (Criteria) this;
		}

		public Criteria andDimIdIsNotNull() {
			addCriterion("dim_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andDimIdEqualTo(String value) {
			addCriterion("dim_id_ =", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdNotEqualTo(String value) {
			addCriterion("dim_id_ <>", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdGreaterThan(String value) {
			addCriterion("dim_id_ >", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdGreaterThanOrEqualTo(String value) {
			addCriterion("dim_id_ >=", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdLessThan(String value) {
			addCriterion("dim_id_ <", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdLessThanOrEqualTo(String value) {
			addCriterion("dim_id_ <=", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdLike(String value) {
			addCriterion("dim_id_ like", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdNotLike(String value) {
			addCriterion("dim_id_ not like", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdIn(List<String> values) {
			addCriterion("dim_id_ in", values, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdNotIn(List<String> values) {
			addCriterion("dim_id_ not in", values, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdBetween(String value1, String value2) {
			addCriterion("dim_id_ between", value1, value2, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimIdNotBetween(String value1, String value2) {
			addCriterion("dim_id_ not between", value1, value2, "dimKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyIsNull() {
			addCriterion("rank_type_key_ is null");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyIsNotNull() {
			addCriterion("rank_type_key_ is not null");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyEqualTo(String value) {
			addCriterion("rank_type_key_ =", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyNotEqualTo(String value) {
			addCriterion("rank_type_key_ <>", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyGreaterThan(String value) {
			addCriterion("rank_type_key_ >", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyGreaterThanOrEqualTo(String value) {
			addCriterion("rank_type_key_ >=", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyLessThan(String value) {
			addCriterion("rank_type_key_ <", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyLessThanOrEqualTo(String value) {
			addCriterion("rank_type_key_ <=", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyLike(String value) {
			addCriterion("rank_type_key_ like", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyNotLike(String value) {
			addCriterion("rank_type_key_ not like", value, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyIn(List<String> values) {
			addCriterion("rank_type_key_ in", values, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyNotIn(List<String> values) {
			addCriterion("rank_type_key_ not in", values, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyBetween(String value1, String value2) {
			addCriterion("rank_type_key_ between", value1, value2, "rankTypeKey");
			return (Criteria) this;
		}

		public Criteria andRankTypeKeyNotBetween(String value1, String value2) {
			addCriterion("rank_type_key_ not between", value1, value2, "rankTypeKey");
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

		public Criteria andDescIsNull() {
			addCriterion("desc_ is null");
			return (Criteria) this;
		}

		public Criteria andDescIsNotNull() {
			addCriterion("desc_ is not null");
			return (Criteria) this;
		}

		public Criteria andDescEqualTo(String value) {
			addCriterion("desc_ =", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescNotEqualTo(String value) {
			addCriterion("desc_ <>", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescGreaterThan(String value) {
			addCriterion("desc_ >", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescGreaterThanOrEqualTo(String value) {
			addCriterion("desc_ >=", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescLessThan(String value) {
			addCriterion("desc_ <", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescLessThanOrEqualTo(String value) {
			addCriterion("desc_ <=", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescLike(String value) {
			addCriterion("desc_ like", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescNotLike(String value) {
			addCriterion("desc_ not like", value, "desc");
			return (Criteria) this;
		}

		public Criteria andDescIn(List<String> values) {
			addCriterion("desc_ in", values, "desc");
			return (Criteria) this;
		}

		public Criteria andDescNotIn(List<String> values) {
			addCriterion("desc_ not in", values, "desc");
			return (Criteria) this;
		}

		public Criteria andDescBetween(String value1, String value2) {
			addCriterion("desc_ between", value1, value2, "desc");
			return (Criteria) this;
		}

		public Criteria andDescNotBetween(String value1, String value2) {
			addCriterion("desc_ not between", value1, value2, "desc");
			return (Criteria) this;
		}

		public Criteria andSnIsNull() {
			addCriterion("sn_ is null");
			return (Criteria) this;
		}

		public Criteria andSnIsNotNull() {
			addCriterion("sn_ is not null");
			return (Criteria) this;
		}

		public Criteria andSnEqualTo(Integer value) {
			addCriterion("sn_ =", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotEqualTo(Integer value) {
			addCriterion("sn_ <>", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnGreaterThan(Integer value) {
			addCriterion("sn_ >", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnGreaterThanOrEqualTo(Integer value) {
			addCriterion("sn_ >=", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnLessThan(Integer value) {
			addCriterion("sn_ <", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnLessThanOrEqualTo(Integer value) {
			addCriterion("sn_ <=", value, "sn");
			return (Criteria) this;
		}

		public Criteria andSnIn(List<Integer> values) {
			addCriterion("sn_ in", values, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotIn(List<Integer> values) {
			addCriterion("sn_ not in", values, "sn");
			return (Criteria) this;
		}

		public Criteria andSnBetween(Integer value1, Integer value2) {
			addCriterion("sn_ between", value1, value2, "sn");
			return (Criteria) this;
		}

		public Criteria andSnNotBetween(Integer value1, Integer value2) {
			addCriterion("sn_ not between", value1, value2, "sn");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNull() {
			addCriterion("parent_id_ is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("parent_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(String value) {
			addCriterion("parent_id_ =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(String value) {
			addCriterion("parent_id_ <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(String value) {
			addCriterion("parent_id_ >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(String value) {
			addCriterion("parent_id_ >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(String value) {
			addCriterion("parent_id_ <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(String value) {
			addCriterion("parent_id_ <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLike(String value) {
			addCriterion("parent_id_ like", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotLike(String value) {
			addCriterion("parent_id_ not like", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<String> values) {
			addCriterion("parent_id_ in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<String> values) {
			addCriterion("parent_id_ not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(String value1, String value2) {
			addCriterion("parent_id_ between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(String value1, String value2) {
			addCriterion("parent_id_ not between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andDepthIsNull() {
			addCriterion("depth_ is null");
			return (Criteria) this;
		}

		public Criteria andDepthIsNotNull() {
			addCriterion("depth_ is not null");
			return (Criteria) this;
		}

		public Criteria andDepthEqualTo(Integer value) {
			addCriterion("depth_ =", value, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthNotEqualTo(Integer value) {
			addCriterion("depth_ <>", value, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthGreaterThan(Integer value) {
			addCriterion("depth_ >", value, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthGreaterThanOrEqualTo(Integer value) {
			addCriterion("depth_ >=", value, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthLessThan(Integer value) {
			addCriterion("depth_ <", value, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthLessThanOrEqualTo(Integer value) {
			addCriterion("depth_ <=", value, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthIn(List<Integer> values) {
			addCriterion("depth_ in", values, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthNotIn(List<Integer> values) {
			addCriterion("depth_ not in", values, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthBetween(Integer value1, Integer value2) {
			addCriterion("depth_ between", value1, value2, "depth");
			return (Criteria) this;
		}

		public Criteria andDepthNotBetween(Integer value1, Integer value2) {
			addCriterion("depth_ not between", value1, value2, "depth");
			return (Criteria) this;
		}

		public Criteria andPathIsNull() {
			addCriterion("path_ is null");
			return (Criteria) this;
		}

		public Criteria andPathIsNotNull() {
			addCriterion("path_ is not null");
			return (Criteria) this;
		}

		public Criteria andPathEqualTo(String value) {
			addCriterion("path_ =", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotEqualTo(String value) {
			addCriterion("path_ <>", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathGreaterThan(String value) {
			addCriterion("path_ >", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathGreaterThanOrEqualTo(String value) {
			addCriterion("path_ >=", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLessThan(String value) {
			addCriterion("path_ <", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLessThanOrEqualTo(String value) {
			addCriterion("path_ <=", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathLike(String value) {
			addCriterion("path_ like", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotLike(String value) {
			addCriterion("path_ not like", value, "path");
			return (Criteria) this;
		}

		public Criteria andPathIn(List<String> values) {
			addCriterion("path_ in", values, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotIn(List<String> values) {
			addCriterion("path_ not in", values, "path");
			return (Criteria) this;
		}

		public Criteria andPathBetween(String value1, String value2) {
			addCriterion("path_ between", value1, value2, "path");
			return (Criteria) this;
		}

		public Criteria andPathNotBetween(String value1, String value2) {
			addCriterion("path_ not between", value1, value2, "path");
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

		public Criteria andFormIsNull() {
			addCriterion("form_ is null");
			return (Criteria) this;
		}

		public Criteria andFormIsNotNull() {
			addCriterion("form_ is not null");
			return (Criteria) this;
		}

		public Criteria andFormEqualTo(String value) {
			addCriterion("form_ =", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormNotEqualTo(String value) {
			addCriterion("form_ <>", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormGreaterThan(String value) {
			addCriterion("form_ >", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormGreaterThanOrEqualTo(String value) {
			addCriterion("form_ >=", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormLessThan(String value) {
			addCriterion("form_ <", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormLessThanOrEqualTo(String value) {
			addCriterion("form_ <=", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormLike(String value) {
			addCriterion("form_ like", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormNotLike(String value) {
			addCriterion("form_ not like", value, "form");
			return (Criteria) this;
		}

		public Criteria andFormIn(List<String> values) {
			addCriterion("form_ in", values, "form");
			return (Criteria) this;
		}

		public Criteria andFormNotIn(List<String> values) {
			addCriterion("form_ not in", values, "form");
			return (Criteria) this;
		}

		public Criteria andFormBetween(String value1, String value2) {
			addCriterion("form_ between", value1, value2, "form");
			return (Criteria) this;
		}

		public Criteria andFormNotBetween(String value1, String value2) {
			addCriterion("form_ not between", value1, value2, "form");
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

	public static class UpdateClause {
		protected List<SetData> dataClauses;

		public List<SetData> getDataClauses() {
			if (dataClauses.size() == 0) {
				throw new OrgException("更新的数据，必须指定");
			}
			return dataClauses;
		}

		protected UpdateClause() {
			this.dataClauses = new ArrayList<SetData>();
		}

		public void setName(String name) {
			dataClauses.add(new SetData("name_ = ", name));
		}

		public void setKey(String key) {
			dataClauses.add(new SetData("key_ = ", key));
		}

		public void setDimId(String dimKey) {
			dataClauses.add(new SetData("dim_id_ = ", dimKey));
		}

		public void setRankTypeKey(String rankTypeKey) {
			dataClauses.add(new SetData("rank_type_key_ = ", rankTypeKey));
		}

		public void setStatus(Status status) {
			dataClauses.add(new SetData("status_ = ", status));
		}

		public void setDesc(String desc) {
			dataClauses.add(new SetData("desc_ = ", desc));
		}

		public void setSn(Integer sn) {
			dataClauses.add(new SetData("sn_ = ", sn));
		}

		public void setParentId(String parentId) {
			dataClauses.add(new SetData("parent_id_ = ", parentId));
		}

		public void setDepth(Integer depth) {
			dataClauses.add(new SetData("depth_ = ", depth));
		}

		public void setPath(String path) {
			dataClauses.add(new SetData("path_ = ", path));
		}

		public void setCreateBy(String createBy) {
			dataClauses.add(new SetData("create_by_ = ", createBy));
		}

		public void setCreateTime(Date createTime) {
			dataClauses.add(new SetData("create_time_ = ", createTime));
		}

		public void setCreateSaasId(String createSaasId) {
			dataClauses.add(new SetData("create_saas_id_ = ", createSaasId));
		}

		public void setUpdateBy(String updateBy) {
			dataClauses.add(new SetData("update_by_ = ", updateBy));
		}

		public void setUpdateTime(Date updateTime) {
			dataClauses.add(new SetData("update_time_ = ", updateTime));
		}

		public void setForm(String form) {
			dataClauses.add(new SetData("form_ = ", form));
		}

	}

	public static class OrderByClause {
		protected List<OrderBy> orderBys;

		public List<OrderBy> getOrderBys() {
			return orderBys;
		}

		public boolean isValid() {
			return orderBys != null && !orderBys.isEmpty();
		}

		public void clear() {
			orderBys.clear();
		}

		public OrderByClause() {
			this.orderBys = new ArrayList<OrderBy>();
		}

		public OrderByClause orderById(Direction direction) {
			orderBys.add(new OrderBy("id_  ", direction));
			return this;
		}

		public OrderByClause orderByDimId(Direction direction) {
			orderBys.add(new OrderBy("dim_id_  ", direction));
			return this;
		}

		public OrderByClause orderByName(Direction direction) {
			orderBys.add(new OrderBy("name_  ", direction));
			return this;
		}

		public OrderByClause orderByKey(Direction direction) {
			orderBys.add(new OrderBy("key_  ", direction));
			return this;
		}

		public OrderByClause orderByLevel(Direction direction) {
			orderBys.add(new OrderBy("level_  ", direction));
			return this;
		}

	}
	
}