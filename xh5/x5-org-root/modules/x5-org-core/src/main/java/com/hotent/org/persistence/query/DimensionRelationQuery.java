package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.base.api.query.Direction;
import com.hotent.org.api.OrgException;

public class DimensionRelationQuery {
	public static class FindQuery {

		protected boolean distinct;

		protected List<Criteria> oredCriteria;

		protected OrderByClause orderByClause;

		public FindQuery() {
			oredCriteria = new ArrayList<Criteria>();
			orderByClause = new OrderByClause();

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

		public OrderByClause getOrderByClause() {
			return orderByClause;
		}

		public void clear() {
			oredCriteria.clear();
			distinct = false;
			orderByClause.clear();
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

		public Criteria andDimIdIsNull() {
			addCriterion("dim_id_ is null");
			return (Criteria) this;
		}

		public Criteria andDimIdIsNotNull() {
			addCriterion("dim_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andDimIdEqualTo(String value) {
			addCriterion("dim_id_ =", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdNotEqualTo(String value) {
			addCriterion("dim_id_ <>", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdGreaterThan(String value) {
			addCriterion("dim_id_ >", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdGreaterThanOrEqualTo(String value) {
			addCriterion("dim_id_ >=", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdLessThan(String value) {
			addCriterion("dim_id_ <", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdLessThanOrEqualTo(String value) {
			addCriterion("dim_id_ <=", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdLike(String value) {
			addCriterion("dim_id_ like", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdNotLike(String value) {
			addCriterion("dim_id_ not like", value, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdIn(List<String> values) {
			addCriterion("dim_id_ in", values, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdNotIn(List<String> values) {
			addCriterion("dim_id_ not in", values, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdBetween(String value1, String value2) {
			addCriterion("dim_id_ between", value1, value2, "dimId");
			return (Criteria) this;
		}

		public Criteria andDimIdNotBetween(String value1, String value2) {
			addCriterion("dim_id_ not between", value1, value2, "dimId");
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

		public Criteria andRelTypeIsNull() {
			addCriterion("rel_type_ is null");
			return (Criteria) this;
		}

		public Criteria andRelTypeIsNotNull() {
			addCriterion("rel_type_ is not null");
			return (Criteria) this;
		}

		public Criteria andRelTypeEqualTo(String value) {
			addCriterion("rel_type_ =", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeNotEqualTo(String value) {
			addCriterion("rel_type_ <>", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeGreaterThan(String value) {
			addCriterion("rel_type_ >", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeGreaterThanOrEqualTo(String value) {
			addCriterion("rel_type_ >=", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeLessThan(String value) {
			addCriterion("rel_type_ <", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeLessThanOrEqualTo(String value) {
			addCriterion("rel_type_ <=", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeLike(String value) {
			addCriterion("rel_type_ like", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeNotLike(String value) {
			addCriterion("rel_type_ not like", value, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeIn(List<String> values) {
			addCriterion("rel_type_ in", values, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeNotIn(List<String> values) {
			addCriterion("rel_type_ not in", values, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeBetween(String value1, String value2) {
			addCriterion("rel_type_ between", value1, value2, "relType");
			return (Criteria) this;
		}

		public Criteria andRelTypeNotBetween(String value1, String value2) {
			addCriterion("rel_type_ not between", value1, value2, "relType");
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

		public UpdateClause() {
			dataClauses = new ArrayList<SetData>();
		}

		public List<SetData> getdataClauses() {
			if (dataClauses.size() == 0) {
				throw new OrgException("更新的数据，必须指定");
			}
			return dataClauses;
		}

		public void setRelTypeId(String relTypeId) {
			dataClauses.add(new SetData("rel_type_id_ = ", relTypeId));
		}

		public void setUserId(String userId) {
			dataClauses.add(new SetData("user_id_ = ", userId));
		}

		public void setGroupId(String groupId) {
			dataClauses.add(new SetData("group_id_ = ", groupId));
		}

		public void setDimId(String dimId) {
			dataClauses.add(new SetData("dim_id_ = ", dimId));
		}

		public void setStatus(String status) {
			dataClauses.add(new SetData("status_ = ", status));
		}

		public void setStartTime(Date startTime) {
			dataClauses.add(new SetData("start_time_ = ", startTime));
		}

		public void setEndTime(Date endTime) {
			dataClauses.add(new SetData("end_time_ = ", endTime));
		}

		public void setCreateBy(String createBy) {
			dataClauses.add(new SetData("create_by_ = ", createBy));
		}

		public void setCreateTime(Date createTime) {
			dataClauses.add(new SetData("create_time_ = ", createTime));
		}

		public void setUpdateBy(String updateBy) {
			dataClauses.add(new SetData("update_by_ = ", updateBy));
		}

		public void setUpdateTime(Date updateTime) {
			dataClauses.add(new SetData("update_time_ = ", updateTime));
		}

		public void setFrom(String from) {
			dataClauses.add(new SetData("from_ = ", from));
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