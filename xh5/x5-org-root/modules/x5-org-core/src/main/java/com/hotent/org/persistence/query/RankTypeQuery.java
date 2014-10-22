package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;

public class RankTypeQuery {
	public static class FindQuery {

		protected boolean distinct;

		protected List<Criteria> oredCriteria;

		
		protected OrderByClause orderByClause;
		
		public FindQuery() {
			oredCriteria = new ArrayList<Criteria>();
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

		public void clear() {
			oredCriteria.clear();
			orderByClause = null;
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

		public Criteria andLevelIsNull() {
			addCriterion("level_ is null");
			return (Criteria) this;
		}

		public Criteria andLevelIsNotNull() {
			addCriterion("level_ is not null");
			return (Criteria) this;
		}

		public Criteria andLevelEqualTo(Integer value) {
			addCriterion("level_ =", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotEqualTo(Integer value) {
			addCriterion("level_ <>", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThan(Integer value) {
			addCriterion("level_ >", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("level_ >=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThan(Integer value) {
			addCriterion("level_ <", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThanOrEqualTo(Integer value) {
			addCriterion("level_ <=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelIn(List<Integer> values) {
			addCriterion("level_ in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotIn(List<Integer> values) {
			addCriterion("level_ not in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelBetween(Integer value1, Integer value2) {
			addCriterion("level_ between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotBetween(Integer value1, Integer value2) {
			addCriterion("level_ not between", value1, value2, "level");
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

		public void setDimId(String dimId) {
			dataClauses.add(new SetData("dim_id_ = ", dimId));
		}

		public void setName(String name) {
			dataClauses.add(new SetData("name_ = ", name));
		}

		public void setKey(String key) {
			dataClauses.add(new SetData("key_ = ", key));
		}

		public void setLevel(Integer level) {
			dataClauses.add(new SetData("level_ = ", level));
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