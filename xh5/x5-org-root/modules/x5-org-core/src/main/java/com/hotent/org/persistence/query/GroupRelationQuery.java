package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.GroupRelation.Status;

public class GroupRelationQuery {

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

		public Criteria andRelTypeIdIsNull() {
			addCriterion("rel_type_id_ is null");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdIsNotNull() {
			addCriterion("rel_type_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdEqualTo(String value) {
			addCriterion("rel_type_id_ =", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdNotEqualTo(String value) {
			addCriterion("rel_type_id_ <>", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdGreaterThan(String value) {
			addCriterion("rel_type_id_ >", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdGreaterThanOrEqualTo(String value) {
			addCriterion("rel_type_id_ >=", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdLessThan(String value) {
			addCriterion("rel_type_id_ <", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdLessThanOrEqualTo(String value) {
			addCriterion("rel_type_id_ <=", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdLike(String value) {
			addCriterion("rel_type_id_ like", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdNotLike(String value) {
			addCriterion("rel_type_id_ not like", value, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdIn(List<String> values) {
			addCriterion("rel_type_id_ in", values, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdNotIn(List<String> values) {
			addCriterion("rel_type_id_ not in", values, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdBetween(String value1, String value2) {
			addCriterion("rel_type_id_ between", value1, value2, "relTypeId");
			return (Criteria) this;
		}

		public Criteria andRelTypeIdNotBetween(String value1, String value2) {
			addCriterion("rel_type_id_ not between", value1, value2, "relTypeId");
			return (Criteria) this;
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

		public Criteria andRelGroupIdIsNull() {
			addCriterion("rel_group_id_ is null");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdIsNotNull() {
			addCriterion("rel_group_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdEqualTo(String value) {
			addCriterion("rel_group_id_ =", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdNotEqualTo(String value) {
			addCriterion("rel_group_id_ <>", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdGreaterThan(String value) {
			addCriterion("rel_group_id_ >", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdGreaterThanOrEqualTo(String value) {
			addCriterion("rel_group_id_ >=", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdLessThan(String value) {
			addCriterion("rel_group_id_ <", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdLessThanOrEqualTo(String value) {
			addCriterion("rel_group_id_ <=", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdLike(String value) {
			addCriterion("rel_group_id_ like", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdNotLike(String value) {
			addCriterion("rel_group_id_ not like", value, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdIn(List<String> values) {
			addCriterion("rel_group_id_ in", values, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdNotIn(List<String> values) {
			addCriterion("rel_group_id_ not in", values, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdBetween(String value1, String value2) {
			addCriterion("rel_group_id_ between", value1, value2, "relGroupId");
			return (Criteria) this;
		}

		public Criteria andRelGroupIdNotBetween(String value1, String value2) {
			addCriterion("rel_group_id_ not between", value1, value2, "relGroupId");
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

		public Criteria andIsCombinationIsNull() {
			addCriterion("is_combination_ is null");
			return (Criteria) this;
		}

		public Criteria andIsCombinationIsNotNull() {
			addCriterion("is_combination_ is not null");
			return (Criteria) this;
		}

		public Criteria andIsCombinationEqualTo(String value) {
			addCriterion("is_combination_ =", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotEqualTo(String value) {
			addCriterion("is_combination_ <>", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationGreaterThan(String value) {
			addCriterion("is_combination_ >", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationGreaterThanOrEqualTo(String value) {
			addCriterion("is_combination_ >=", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationLessThan(String value) {
			addCriterion("is_combination_ <", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationLessThanOrEqualTo(String value) {
			addCriterion("is_combination_ <=", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationLike(String value) {
			addCriterion("is_combination_ like", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotLike(String value) {
			addCriterion("is_combination_ not like", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationIn(List<String> values) {
			addCriterion("is_combination_ in", values, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotIn(List<String> values) {
			addCriterion("is_combination_ not in", values, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationBetween(String value1, String value2) {
			addCriterion("is_combination_ between", value1, value2, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotBetween(String value1, String value2) {
			addCriterion("is_combination_ not between", value1, value2, "isCombination");
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

		public Criteria andStartTimeIsNull() {
			addCriterion("start_time_ is null");
			return (Criteria) this;
		}

		public Criteria andStartTimeIsNotNull() {
			addCriterion("start_time_ is not null");
			return (Criteria) this;
		}

		public Criteria andStartTimeEqualTo(Date value) {
			addCriterion("start_time_ =", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotEqualTo(Date value) {
			addCriterion("start_time_ <>", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeGreaterThan(Date value) {
			addCriterion("start_time_ >", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("start_time_ >=", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeLessThan(Date value) {
			addCriterion("start_time_ <", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeLessThanOrEqualTo(Date value) {
			addCriterion("start_time_ <=", value, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeIn(List<Date> values) {
			addCriterion("start_time_ in", values, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotIn(List<Date> values) {
			addCriterion("start_time_ not in", values, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeBetween(Date value1, Date value2) {
			addCriterion("start_time_ between", value1, value2, "startTime");
			return (Criteria) this;
		}

		public Criteria andStartTimeNotBetween(Date value1, Date value2) {
			addCriterion("start_time_ not between", value1, value2, "startTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeIsNull() {
			addCriterion("end_time_ is null");
			return (Criteria) this;
		}

		public Criteria andEndTimeIsNotNull() {
			addCriterion("end_time_ is not null");
			return (Criteria) this;
		}

		public Criteria andEndTimeEqualTo(Date value) {
			addCriterion("end_time_ =", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotEqualTo(Date value) {
			addCriterion("end_time_ <>", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeGreaterThan(Date value) {
			addCriterion("end_time_ >", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("end_time_ >=", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeLessThan(Date value) {
			addCriterion("end_time_ <", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeLessThanOrEqualTo(Date value) {
			addCriterion("end_time_ <=", value, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeIn(List<Date> values) {
			addCriterion("end_time_ in", values, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotIn(List<Date> values) {
			addCriterion("end_time_ not in", values, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeBetween(Date value1, Date value2) {
			addCriterion("end_time_ between", value1, value2, "endTime");
			return (Criteria) this;
		}

		public Criteria andEndTimeNotBetween(Date value1, Date value2) {
			addCriterion("end_time_ not between", value1, value2, "endTime");
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
			this.dataClauses = new ArrayList<SetData>();
		}

		public List<SetData> getdataClauses() {
			if (dataClauses.size() == 0) {
				throw new OrgException("更新的数据，必须指定");
			}
			return dataClauses;
		}

		public void setId(String id) {
			dataClauses.add(new SetData("id_ = ", id));
		}

		public void setRelTypeId(String relTypeId) {
			dataClauses.add(new SetData("rel_type_id_ = ", relTypeId));
		}

		public void setGroupId(String groupId) {
			dataClauses.add(new SetData("group_id_ = ", groupId));
		}

		public void setRelGroupId(String relGroupId) {
			dataClauses.add(new SetData("rel_group_id_ = ", relGroupId));
		}

		public void setCurrentDimId(String currentDimId) {
			dataClauses.add(new SetData("current_dim_id_ = ", currentDimId));
		}

		public void setRelDimId(String relDimId) {
			dataClauses.add(new SetData("rel_dim_id_ = ", relDimId));
		}

		public void setIsCombination(String isCombination) {
			dataClauses.add(new SetData("is_combination_ = ", isCombination));
		}

		public void setStatus(Status status) {
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