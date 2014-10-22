package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.List;

import com.hotent.base.api.query.Direction;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Attribute.Status;
import com.hotent.org.persistence.query.UserQuery.Criteria;

public abstract class AttributeQuery {
	public static class FindQuery {

		protected boolean distinct;

		protected List<Criteria> oredCriteria;

		protected List<AttributeValueQuery.Criteria> oredAttributeValueCriteria;
		protected OrderByClause orderByClause;
		public FindQuery() {
			oredCriteria = new ArrayList<Criteria>();
			oredAttributeValueCriteria = new ArrayList<AttributeValueQuery.Criteria>();
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

		
		public boolean isJoinAttributeValue() {
			for (AttributeValueQuery.Criteria c : oredAttributeValueCriteria) {
				if (c.isValid()) {
					return true;
				}
			}
			return false;
		}
		
		public void clear() {
			oredCriteria.clear();
			oredAttributeValueCriteria.clear();
			orderByClause = null;
			distinct = false;
		}
	}

	public static class UpdateQuery {
		protected UpdateClause updateClause;

		protected List<AttributeQuery.Criteria> oredCriteria;

		public UpdateClause getUpdateClause() {
			return updateClause;
		}

		public List<AttributeQuery.Criteria> getOredCriteria() {
			return oredCriteria;
		}

		public void or(AttributeQuery.Criteria criteria) {
			oredCriteria.add(criteria);
		}

		public AttributeQuery.Criteria or() {
			AttributeQuery.Criteria criteria = createCriteriaInternal();
			oredCriteria.add(criteria);
			return criteria;
		}

		public AttributeQuery.Criteria createCriteria() {
			AttributeQuery.Criteria criteria = createCriteriaInternal();
			if (oredCriteria.size() == 0) {
				oredCriteria.add(criteria);
			}
			return criteria;
		}

		protected AttributeQuery.Criteria createCriteriaInternal() {
			AttributeQuery.Criteria criteria = new AttributeQuery.Criteria();
			return criteria;
		}

		public void clear() {
			oredCriteria.clear();
		}

		public UpdateQuery() {
			oredCriteria = new ArrayList<AttributeQuery.Criteria>();
			updateClause = new UpdateClause();
		}
	}

	public static class DeleteQuery {

		protected List<AttributeQuery.Criteria> oredCriteria;

		public List<AttributeQuery.Criteria> getOredCriteria() {
			return oredCriteria;
		}

		public void or(AttributeQuery.Criteria criteria) {
			oredCriteria.add(criteria);
		}

		public AttributeQuery.Criteria or() {
			AttributeQuery.Criteria criteria = createCriteriaInternal();
			oredCriteria.add(criteria);
			return criteria;
		}

		public AttributeQuery.Criteria createCriteria() {
			AttributeQuery.Criteria criteria = createCriteriaInternal();
			if (oredCriteria.size() == 0) {
				oredCriteria.add(criteria);
			}
			return criteria;
		}

		protected AttributeQuery.Criteria createCriteriaInternal() {
			AttributeQuery.Criteria criteria = new AttributeQuery.Criteria();
			return criteria;
		}

		public void clear() {
			oredCriteria.clear();
		}

		public DeleteQuery() {
			oredCriteria = new ArrayList<AttributeQuery.Criteria>();
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
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andAttrIdIsNull() {
			addCriterion("attr_id_ is null");
			return (Criteria) this;
		}

		public Criteria andAttrIdIsNotNull() {
			addCriterion("attr_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andAttrIdEqualTo(String value) {
			addCriterion("attr_id_ =", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdNotEqualTo(String value) {
			addCriterion("attr_id_ <>", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdGreaterThan(String value) {
			addCriterion("attr_id_ >", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdGreaterThanOrEqualTo(String value) {
			addCriterion("attr_id_ >=", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdLessThan(String value) {
			addCriterion("attr_id_ <", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdLessThanOrEqualTo(String value) {
			addCriterion("attr_id_ <=", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdLike(String value) {
			addCriterion("attr_id_ like", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdNotLike(String value) {
			addCriterion("attr_id_ not like", value, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdIn(List<String> values) {
			addCriterion("attr_id_ in", values, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdNotIn(List<String> values) {
			addCriterion("attr_id_ not in", values, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdBetween(String value1, String value2) {
			addCriterion("attr_id_ between", value1, value2, "attrId");
			return (Criteria) this;
		}

		public Criteria andAttrIdNotBetween(String value1, String value2) {
			addCriterion("attr_id_ not between", value1, value2, "attrId");
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

		public Criteria andAttrKeyIsNull() {
			addCriterion("attr_key_ is null");
			return (Criteria) this;
		}

		public Criteria andAttrKeyIsNotNull() {
			addCriterion("attr_key_ is not null");
			return (Criteria) this;
		}

		public Criteria andAttrKeyEqualTo(String value) {
			addCriterion("attr_key_ =", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyNotEqualTo(String value) {
			addCriterion("attr_key_ <>", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyGreaterThan(String value) {
			addCriterion("attr_key_ >", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyGreaterThanOrEqualTo(String value) {
			addCriterion("attr_key_ >=", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyLessThan(String value) {
			addCriterion("attr_key_ <", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyLessThanOrEqualTo(String value) {
			addCriterion("attr_key_ <=", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyLike(String value) {
			addCriterion("attr_key_ like", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyNotLike(String value) {
			addCriterion("attr_key_ not like", value, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyIn(List<String> values) {
			addCriterion("attr_key_ in", values, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyNotIn(List<String> values) {
			addCriterion("attr_key_ not in", values, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyBetween(String value1, String value2) {
			addCriterion("attr_key_ between", value1, value2, "attrKey");
			return (Criteria) this;
		}

		public Criteria andAttrKeyNotBetween(String value1, String value2) {
			addCriterion("attr_key_ not between", value1, value2, "attrKey");
			return (Criteria) this;
		}

		public Criteria andBelongTypeIsNull() {
			addCriterion("belong_type_ is null");
			return (Criteria) this;
		}

		public Criteria andBelongTypeIsNotNull() {
			addCriterion("belong_type_ is not null");
			return (Criteria) this;
		}

		public Criteria andBelongTypeEqualTo(String value) {
			addCriterion("belong_type_ =", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeNotEqualTo(String value) {
			addCriterion("belong_type_ <>", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeGreaterThan(String value) {
			addCriterion("belong_type_ >", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeGreaterThanOrEqualTo(String value) {
			addCriterion("belong_type_ >=", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeLessThan(String value) {
			addCriterion("belong_type_ <", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeLessThanOrEqualTo(String value) {
			addCriterion("belong_type_ <=", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeLike(String value) {
			addCriterion("belong_type_ like", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeNotLike(String value) {
			addCriterion("belong_type_ not like", value, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeIn(List<String> values) {
			addCriterion("belong_type_ in", values, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeNotIn(List<String> values) {
			addCriterion("belong_type_ not in", values, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeBetween(String value1, String value2) {
			addCriterion("belong_type_ between", value1, value2, "belongType");
			return (Criteria) this;
		}

		public Criteria andBelongTypeNotBetween(String value1, String value2) {
			addCriterion("belong_type_ not between", value1, value2, "belongType");
			return (Criteria) this;
		}

		public Criteria andDataTypeIsNull() {
			addCriterion("data_type_ is null");
			return (Criteria) this;
		}

		public Criteria andDataTypeIsNotNull() {
			addCriterion("data_type_ is not null");
			return (Criteria) this;
		}

		public Criteria andDataTypeEqualTo(String value) {
			addCriterion("data_type_ =", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeNotEqualTo(String value) {
			addCriterion("data_type_ <>", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeGreaterThan(String value) {
			addCriterion("data_type_ >", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
			addCriterion("data_type_ >=", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeLessThan(String value) {
			addCriterion("data_type_ <", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeLessThanOrEqualTo(String value) {
			addCriterion("data_type_ <=", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeLike(String value) {
			addCriterion("data_type_ like", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeNotLike(String value) {
			addCriterion("data_type_ not like", value, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeIn(List<String> values) {
			addCriterion("data_type_ in", values, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeNotIn(List<String> values) {
			addCriterion("data_type_ not in", values, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeBetween(String value1, String value2) {
			addCriterion("data_type_ between", value1, value2, "dataType");
			return (Criteria) this;
		}

		public Criteria andDataTypeNotBetween(String value1, String value2) {
			addCriterion("data_type_ not between", value1, value2, "dataType");
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

		public Criteria andCreateOrgIdIsNull() {
			addCriterion("create_org_id_ is null");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdIsNotNull() {
			addCriterion("create_org_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdEqualTo(String value) {
			addCriterion("create_org_id_ =", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdNotEqualTo(String value) {
			addCriterion("create_org_id_ <>", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdGreaterThan(String value) {
			addCriterion("create_org_id_ >", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdGreaterThanOrEqualTo(String value) {
			addCriterion("create_org_id_ >=", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdLessThan(String value) {
			addCriterion("create_org_id_ <", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdLessThanOrEqualTo(String value) {
			addCriterion("create_org_id_ <=", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdLike(String value) {
			addCriterion("create_org_id_ like", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdNotLike(String value) {
			addCriterion("create_org_id_ not like", value, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdIn(List<String> values) {
			addCriterion("create_org_id_ in", values, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdNotIn(List<String> values) {
			addCriterion("create_org_id_ not in", values, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdBetween(String value1, String value2) {
			addCriterion("create_org_id_ between", value1, value2, "createOrgId");
			return (Criteria) this;
		}

		public Criteria andCreateOrgIdNotBetween(String value1, String value2) {
			addCriterion("create_org_id_ not between", value1, value2, "createOrgId");
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

		public List<SetData> getdataClauses() {
			if (dataClauses.size() == 0) {
				throw new OrgException("更新的数据，必须指定");
			}
			return dataClauses;
		}

		protected UpdateClause() {
			this.dataClauses = new ArrayList<SetData>();
		}

		// ////////////////Set Clause

		public void setName(String name) {
			dataClauses.add(new SetData("name_ = ", name));
		}

		public void setAttrKey(String attrKey) {
			dataClauses.add(new SetData("attr_key_ = ", attrKey));
		}

		public void setBelongType(String belongType) {
			dataClauses.add(new SetData("belong_type_ = ", belongType));
		}

		public void setDataType(String dataType) {
			dataClauses.add(new SetData("data_type_ = ", dataType));
		}

		public void setDesc(String desc) {
			dataClauses.add(new SetData("desc_ = ", desc));
		}

		public void setCreateOrgId(String createOrgId) {
			dataClauses.add(new SetData("create_org_id_ = ", createOrgId));
		}

		public void setStatus(Status status) {
			dataClauses.add(new SetData("status_ = ", status));
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
		
		public OrderByClause orderById(Direction direction){
			orderBys.add(new OrderBy("id_  ", direction));
			return this;
		}

		public OrderByClause orderByDimId(Direction direction){
			orderBys.add(new OrderBy("dim_id_  ", direction));
			return this;
		}

		public OrderByClause orderByName(Direction direction){
			orderBys.add(new OrderBy("name_  ", direction));
			return this;
		}

		public OrderByClause orderByKey(Direction direction){
			orderBys.add(new OrderBy("key_  ", direction));
			return this;
		}

		public OrderByClause orderByLevel(Direction direction){
			orderBys.add(new OrderBy("level_  ", direction));
			return this;
		}
		
	}
}