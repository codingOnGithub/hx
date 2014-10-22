package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.List;

import com.hotent.base.api.query.Direction;
import com.hotent.org.api.OrgException;
import com.hotent.org.api.model.Dimension.Status;

public class DimensionQuery {

	public static class FindQuery {
		protected OrderByClause orderByClause;

		protected boolean distinct;

		protected List<Criteria> oredCriteria;

		protected List<DimensionRelationQuery.Criteria> oredDimensionRelationCriteria;
		

		protected List<RankTypeQuery.Criteria> oredRankTypeCriteria;

		public FindQuery() {
			oredCriteria = new ArrayList<Criteria>();
			oredRankTypeCriteria = new ArrayList<RankTypeQuery.Criteria>();
			oredDimensionRelationCriteria = new ArrayList<DimensionRelationQuery.Criteria>();
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

		// /////////////////B oredRankTypeCriteria
		public List<RankTypeQuery.Criteria> getOredRankTypeCriteria() {
			return oredRankTypeCriteria;
		}

		public void orRankTypeCriteria(RankTypeQuery.Criteria RankTypeCriteria) {
			oredRankTypeCriteria.add(RankTypeCriteria);
		}

		public RankTypeQuery.Criteria orRankTypeCriteria() {
			RankTypeQuery.Criteria RankTypeCriteria = createRankTypeCriteriaInternal();
			oredRankTypeCriteria.add(RankTypeCriteria);
			return RankTypeCriteria;
		}

		public RankTypeQuery.Criteria createRankTypeCriteria() {
			RankTypeQuery.Criteria RankTypeCriteria = createRankTypeCriteriaInternal();
			if (oredRankTypeCriteria.size() == 0) {
				oredRankTypeCriteria.add(RankTypeCriteria);
			}
			return RankTypeCriteria;
		}

		protected RankTypeQuery.Criteria createRankTypeCriteriaInternal() {
			RankTypeQuery.Criteria RankTypeCriteria = new RankTypeQuery.Criteria();
			return RankTypeCriteria;
		}

		// //////////////////E oredRankTypeCriteria
		// /////////////////B oredDimensionRelationCriteria
		public List<DimensionRelationQuery.Criteria> getOredDimensionRelationCriteria() {
			return oredDimensionRelationCriteria;
		}

		public void orDimensionRelationCriteria(DimensionRelationQuery.Criteria DimensionRelationCriteria) {
			oredDimensionRelationCriteria.add(DimensionRelationCriteria);
		}

		public DimensionRelationQuery.Criteria orDimensionRelationCriteria() {
			DimensionRelationQuery.Criteria DimensionRelationCriteria = createDimensionRelationCriteriaInternal();
			oredDimensionRelationCriteria.add(DimensionRelationCriteria);
			return DimensionRelationCriteria;
		}

		public DimensionRelationQuery.Criteria createDimensionRelationCriteria() {
			DimensionRelationQuery.Criteria DimensionRelationCriteria = createDimensionRelationCriteriaInternal();
			if (oredDimensionRelationCriteria.size() == 0) {
				oredDimensionRelationCriteria.add(DimensionRelationCriteria);
			}
			return DimensionRelationCriteria;
		}

		protected DimensionRelationQuery.Criteria createDimensionRelationCriteriaInternal() {
			DimensionRelationQuery.Criteria DimensionRelationCriteria = new DimensionRelationQuery.Criteria();
			return DimensionRelationCriteria;
		}

		// /////////////////E oredDimensionRelationCriteria

		public boolean isJoinDimensionRelation() {
			for (DimensionRelationQuery.Criteria c : oredDimensionRelationCriteria) {
				if (c.isValid()) {
					return true;
				}
			}
			return false;
		}

		public boolean isJoinRankType() {
			for (RankTypeQuery.Criteria c : oredRankTypeCriteria) {
				if (c.isValid()) {
					return true;
				}
			}
			return false;
		}

		public void clear() {
			oredCriteria.clear();
			oredRankTypeCriteria.clear();
			oredDimensionRelationCriteria.clear();
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
			oredCriteria = new ArrayList<DimensionQuery.Criteria>();
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
			oredCriteria = new ArrayList<DimensionQuery.Criteria>();
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

		public Criteria andDimKeyIsNull() {
			addCriterion("dim_key_ is null");
			return (Criteria) this;
		}

		public Criteria andDimKeyIsNotNull() {
			addCriterion("dim_key_ is not null");
			return (Criteria) this;
		}

		public Criteria andDimKeyEqualTo(String value) {
			addCriterion("dim_key_ =", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyNotEqualTo(String value) {
			addCriterion("dim_key_ <>", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyGreaterThan(String value) {
			addCriterion("dim_key_ >", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyGreaterThanOrEqualTo(String value) {
			addCriterion("dim_key_ >=", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyLessThan(String value) {
			addCriterion("dim_key_ <", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyLessThanOrEqualTo(String value) {
			addCriterion("dim_key_ <=", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyLike(String value) {
			addCriterion("dim_key_ like", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyNotLike(String value) {
			addCriterion("dim_key_ not like", value, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyIn(List<String> values) {
			addCriterion("dim_key_ in", values, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyNotIn(List<String> values) {
			addCriterion("dim_key_ not in", values, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyBetween(String value1, String value2) {
			addCriterion("dim_key_ between", value1, value2, "dimKey");
			return (Criteria) this;
		}

		public Criteria andDimKeyNotBetween(String value1, String value2) {
			addCriterion("dim_key_ not between", value1, value2, "dimKey");
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

		public Criteria andIsCombinationEqualTo(Character value) {
			addCriterion("is_combination_ =", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotEqualTo(Character value) {
			addCriterion("is_combination_ <>", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationGreaterThan(Character value) {
			addCriterion("is_combination_ >", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationGreaterThanOrEqualTo(Character value) {
			addCriterion("is_combination_ >=", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationLessThan(Character value) {
			addCriterion("is_combination_ <", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationLessThanOrEqualTo(Character value) {
			addCriterion("is_combination_ <=", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationLike(Character value) {
			addCriterion("is_combination_ like", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotLike(Character value) {
			addCriterion("is_combination_ not like", value, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationIn(List<Character> values) {
			addCriterion("is_combination_ in", values, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotIn(List<Character> values) {
			addCriterion("is_combination_ not in", values, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationBetween(Character value1, Character value2) {
			addCriterion("is_combination_ between", value1, value2, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsCombinationNotBetween(Character value1, Character value2) {
			addCriterion("is_combination_ not between", value1, value2, "isCombination");
			return (Criteria) this;
		}

		public Criteria andIsSystemIsNull() {
			addCriterion("is_system_ is null");
			return (Criteria) this;
		}

		public Criteria andIsSystemIsNotNull() {
			addCriterion("is_system_ is not null");
			return (Criteria) this;
		}

		public Criteria andIsSystemEqualTo(String value) {
			addCriterion("is_system_ =", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemNotEqualTo(String value) {
			addCriterion("is_system_ <>", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemGreaterThan(String value) {
			addCriterion("is_system_ >", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemGreaterThanOrEqualTo(String value) {
			addCriterion("is_system_ >=", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemLessThan(String value) {
			addCriterion("is_system_ <", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemLessThanOrEqualTo(String value) {
			addCriterion("is_system_ <=", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemLike(String value) {
			addCriterion("is_system_ like", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemNotLike(String value) {
			addCriterion("is_system_ not like", value, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemIn(List<String> values) {
			addCriterion("is_system_ in", values, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemNotIn(List<String> values) {
			addCriterion("is_system_ not in", values, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemBetween(String value1, String value2) {
			addCriterion("is_system_ between", value1, value2, "isSystem");
			return (Criteria) this;
		}

		public Criteria andIsSystemNotBetween(String value1, String value2) {
			addCriterion("is_system_ not between", value1, value2, "isSystem");
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

		public Criteria andShowTypeIsNull() {
			addCriterion("show_type_ is null");
			return (Criteria) this;
		}

		public Criteria andShowTypeIsNotNull() {
			addCriterion("show_type_ is not null");
			return (Criteria) this;
		}

		public Criteria andShowTypeEqualTo(String value) {
			addCriterion("show_type_ =", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotEqualTo(String value) {
			addCriterion("show_type_ <>", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeGreaterThan(String value) {
			addCriterion("show_type_ >", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeGreaterThanOrEqualTo(String value) {
			addCriterion("show_type_ >=", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeLessThan(String value) {
			addCriterion("show_type_ <", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeLessThanOrEqualTo(String value) {
			addCriterion("show_type_ <=", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeLike(String value) {
			addCriterion("show_type_ like", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotLike(String value) {
			addCriterion("show_type_ not like", value, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeIn(List<String> values) {
			addCriterion("show_type_ in", values, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotIn(List<String> values) {
			addCriterion("show_type_ not in", values, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeBetween(String value1, String value2) {
			addCriterion("show_type_ between", value1, value2, "showType");
			return (Criteria) this;
		}

		public Criteria andShowTypeNotBetween(String value1, String value2) {
			addCriterion("show_type_ not between", value1, value2, "showType");
			return (Criteria) this;
		}

		public Criteria andGradeAuthIsNull() {
			addCriterion("grade_auth_ is null");
			return (Criteria) this;
		}

		public Criteria andGradeAuthIsNotNull() {
			addCriterion("grade_auth_ is not null");
			return (Criteria) this;
		}

		public Criteria andGradeAuthEqualTo(String value) {
			addCriterion("grade_auth_ =", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthNotEqualTo(String value) {
			addCriterion("grade_auth_ <>", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthGreaterThan(String value) {
			addCriterion("grade_auth_ >", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthGreaterThanOrEqualTo(String value) {
			addCriterion("grade_auth_ >=", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthLessThan(String value) {
			addCriterion("grade_auth_ <", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthLessThanOrEqualTo(String value) {
			addCriterion("grade_auth_ <=", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthLike(String value) {
			addCriterion("grade_auth_ like", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthNotLike(String value) {
			addCriterion("grade_auth_ not like", value, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthIn(List<String> values) {
			addCriterion("grade_auth_ in", values, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthNotIn(List<String> values) {
			addCriterion("grade_auth_ not in", values, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthBetween(String value1, String value2) {
			addCriterion("grade_auth_ between", value1, value2, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andGradeAuthNotBetween(String value1, String value2) {
			addCriterion("grade_auth_ not between", value1, value2, "gradeAuth");
			return (Criteria) this;
		}

		public Criteria andAllowTypeIsNull() {
			addCriterion("allow_type_ is null");
			return (Criteria) this;
		}

		public Criteria andAllowTypeIsNotNull() {
			addCriterion("allow_type_ is not null");
			return (Criteria) this;
		}

		public Criteria andAllowTypeEqualTo(String value) {
			addCriterion("allow_type_ =", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeNotEqualTo(String value) {
			addCriterion("allow_type_ <>", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeGreaterThan(String value) {
			addCriterion("allow_type_ >", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeGreaterThanOrEqualTo(String value) {
			addCriterion("allow_type_ >=", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeLessThan(String value) {
			addCriterion("allow_type_ <", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeLessThanOrEqualTo(String value) {
			addCriterion("allow_type_ <=", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeLike(String value) {
			addCriterion("allow_type_ like", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeNotLike(String value) {
			addCriterion("allow_type_ not like", value, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeIn(List<String> values) {
			addCriterion("allow_type_ in", values, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeNotIn(List<String> values) {
			addCriterion("allow_type_ not in", values, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeBetween(String value1, String value2) {
			addCriterion("allow_type_ between", value1, value2, "allowType");
			return (Criteria) this;
		}

		public Criteria andAllowTypeNotBetween(String value1, String value2) {
			addCriterion("allow_type_ not between", value1, value2, "allowType");
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
	}

	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
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

		public UpdateClause setName(String name) {
			dataClauses.add(new SetData("name_ = ", name));	return this;
		}

		public UpdateClause setDimKey(String dimKey) {
			dataClauses.add(new SetData("dim_key_ = ", dimKey));	return this;
		}

		public UpdateClause setIsCombination(String isCombination) {
			dataClauses.add(new SetData("is_combination_ = ", isCombination));	return this;
		}

		public UpdateClause setIsSystem(String isSystem) {
			dataClauses.add(new SetData("is_system_ = ", isSystem));	return this;
		}

		public UpdateClause setSn(Integer sn) {
			dataClauses.add(new SetData("sn_ = ", sn));	return this;
		}

		public UpdateClause setStatus(Status status) {
			dataClauses.add(new SetData("status_ = ", status));	return this;
		}

		public UpdateClause setShowType(String showType) {
			dataClauses.add(new SetData("show_type_ = ", showType));	return this;
		}
		

		public UpdateClause setGradeAuth(String gradeAuth) {
			dataClauses.add(new SetData("grade_auth_ = ", gradeAuth));
			return this;
		}

		public UpdateClause setAllowType(String allowType) {
			dataClauses.add(new SetData("allow_type_ = ", allowType));
			return this;
		}

		public UpdateClause setDesc(String desc) {
			dataClauses.add(new SetData("desc_ = ", desc));
			return this;
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