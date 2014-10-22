package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.UserRelation.Status;

public class UserRelationQuery {
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

		public Criteria andRelIdIsNull() {
			addCriterion("rel_id_ is null");
			return (Criteria) this;
		}

		public Criteria andRelIdIsNotNull() {
			addCriterion("rel_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andRelIdEqualTo(String value) {
			addCriterion("rel_id_ =", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdNotEqualTo(String value) {
			addCriterion("rel_id_ <>", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdGreaterThan(String value) {
			addCriterion("rel_id_ >", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdGreaterThanOrEqualTo(String value) {
			addCriterion("rel_id_ >=", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdLessThan(String value) {
			addCriterion("rel_id_ <", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdLessThanOrEqualTo(String value) {
			addCriterion("rel_id_ <=", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdLike(String value) {
			addCriterion("rel_id_ like", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdNotLike(String value) {
			addCriterion("rel_id_ not like", value, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdIn(List<String> values) {
			addCriterion("rel_id_ in", values, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdNotIn(List<String> values) {
			addCriterion("rel_id_ not in", values, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdBetween(String value1, String value2) {
			addCriterion("rel_id_ between", value1, value2, "relId");
			return (Criteria) this;
		}

		public Criteria andRelIdNotBetween(String value1, String value2) {
			addCriterion("rel_id_ not between", value1, value2, "relId");
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

		public Criteria andRelUserIdIsNull() {
			addCriterion("rel_user_id_ is null");
			return (Criteria) this;
		}

		public Criteria andRelUserIdIsNotNull() {
			addCriterion("rel_user_id_ is not null");
			return (Criteria) this;
		}

		public Criteria andRelUserIdEqualTo(String value) {
			addCriterion("rel_user_id_ =", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdNotEqualTo(String value) {
			addCriterion("rel_user_id_ <>", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdGreaterThan(String value) {
			addCriterion("rel_user_id_ >", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("rel_user_id_ >=", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdLessThan(String value) {
			addCriterion("rel_user_id_ <", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdLessThanOrEqualTo(String value) {
			addCriterion("rel_user_id_ <=", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdLike(String value) {
			addCriterion("rel_user_id_ like", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdNotLike(String value) {
			addCriterion("rel_user_id_ not like", value, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdIn(List<String> values) {
			addCriterion("rel_user_id_ in", values, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdNotIn(List<String> values) {
			addCriterion("rel_user_id_ not in", values, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdBetween(String value1, String value2) {
			addCriterion("rel_user_id_ between", value1, value2, "relUserId");
			return (Criteria) this;
		}

		public Criteria andRelUserIdNotBetween(String value1, String value2) {
			addCriterion("rel_user_id_ not between", value1, value2, "relUserId");
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

		public Criteria andStatusGreaterThan(Status  value) {
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

		public void setRelTypeId(String relTypeId) {
			dataClauses.add(new SetData("rel_type_id_ = ", relTypeId));
		}

		public void setUserId(String userId) {
			dataClauses.add(new SetData("user_id_ = ", userId));
		}

		public void setRelUserId(String relUserId) {
			dataClauses.add(new SetData("rel_user_id_ = ", relUserId));
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

	}
}