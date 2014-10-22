package com.hotent.org.persistence.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AttributeValueQuery {

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttributeValueQuery() {
        oredCriteria = new ArrayList<Criteria>();
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

    public void clear() {
        oredCriteria.clear();
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

        public Criteria andValIdIsNull() {
            addCriterion("val_id_ is null");
            return (Criteria) this;
        }

        public Criteria andValIdIsNotNull() {
            addCriterion("val_id_ is not null");
            return (Criteria) this;
        }

        public Criteria andValIdEqualTo(String value) {
            addCriterion("val_id_ =", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdNotEqualTo(String value) {
            addCriterion("val_id_ <>", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdGreaterThan(String value) {
            addCriterion("val_id_ >", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdGreaterThanOrEqualTo(String value) {
            addCriterion("val_id_ >=", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdLessThan(String value) {
            addCriterion("val_id_ <", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdLessThanOrEqualTo(String value) {
            addCriterion("val_id_ <=", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdLike(String value) {
            addCriterion("val_id_ like", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdNotLike(String value) {
            addCriterion("val_id_ not like", value, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdIn(List<String> values) {
            addCriterion("val_id_ in", values, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdNotIn(List<String> values) {
            addCriterion("val_id_ not in", values, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdBetween(String value1, String value2) {
            addCriterion("val_id_ between", value1, value2, "valId");
            return (Criteria) this;
        }

        public Criteria andValIdNotBetween(String value1, String value2) {
            addCriterion("val_id_ not between", value1, value2, "valId");
            return (Criteria) this;
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

        public Criteria andTextValIsNull() {
            addCriterion("text_val is null");
            return (Criteria) this;
        }

        public Criteria andTextValIsNotNull() {
            addCriterion("text_val is not null");
            return (Criteria) this;
        }

        public Criteria andTextValEqualTo(String value) {
            addCriterion("text_val =", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValNotEqualTo(String value) {
            addCriterion("text_val <>", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValGreaterThan(String value) {
            addCriterion("text_val >", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValGreaterThanOrEqualTo(String value) {
            addCriterion("text_val >=", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValLessThan(String value) {
            addCriterion("text_val <", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValLessThanOrEqualTo(String value) {
            addCriterion("text_val <=", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValLike(String value) {
            addCriterion("text_val like", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValNotLike(String value) {
            addCriterion("text_val not like", value, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValIn(List<String> values) {
            addCriterion("text_val in", values, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValNotIn(List<String> values) {
            addCriterion("text_val not in", values, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValBetween(String value1, String value2) {
            addCriterion("text_val between", value1, value2, "textVal");
            return (Criteria) this;
        }

        public Criteria andTextValNotBetween(String value1, String value2) {
            addCriterion("text_val not between", value1, value2, "textVal");
            return (Criteria) this;
        }

        public Criteria andDateValIsNull() {
            addCriterion("date_val is null");
            return (Criteria) this;
        }

        public Criteria andDateValIsNotNull() {
            addCriterion("date_val is not null");
            return (Criteria) this;
        }

        public Criteria andDateValEqualTo(Date value) {
            addCriterion("date_val =", value, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValNotEqualTo(Date value) {
            addCriterion("date_val <>", value, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValGreaterThan(Date value) {
            addCriterion("date_val >", value, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValGreaterThanOrEqualTo(Date value) {
            addCriterion("date_val >=", value, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValLessThan(Date value) {
            addCriterion("date_val <", value, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValLessThanOrEqualTo(Date value) {
            addCriterion("date_val <=", value, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValIn(List<Date> values) {
            addCriterion("date_val in", values, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValNotIn(List<Date> values) {
            addCriterion("date_val not in", values, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValBetween(Date value1, Date value2) {
            addCriterion("date_val between", value1, value2, "dateVal");
            return (Criteria) this;
        }

        public Criteria andDateValNotBetween(Date value1, Date value2) {
            addCriterion("date_val not between", value1, value2, "dateVal");
            return (Criteria) this;
        }

        public Criteria andLongValIsNull() {
            addCriterion("long_val is null");
            return (Criteria) this;
        }

        public Criteria andLongValIsNotNull() {
            addCriterion("long_val is not null");
            return (Criteria) this;
        }

        public Criteria andLongValEqualTo(Long value) {
            addCriterion("long_val =", value, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValNotEqualTo(Long value) {
            addCriterion("long_val <>", value, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValGreaterThan(Long value) {
            addCriterion("long_val >", value, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValGreaterThanOrEqualTo(Long value) {
            addCriterion("long_val >=", value, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValLessThan(Long value) {
            addCriterion("long_val <", value, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValLessThanOrEqualTo(Long value) {
            addCriterion("long_val <=", value, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValIn(List<Long> values) {
            addCriterion("long_val in", values, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValNotIn(List<Long> values) {
            addCriterion("long_val not in", values, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValBetween(Long value1, Long value2) {
            addCriterion("long_val between", value1, value2, "longVal");
            return (Criteria) this;
        }

        public Criteria andLongValNotBetween(Long value1, Long value2) {
            addCriterion("long_val not between", value1, value2, "longVal");
            return (Criteria) this;
        }

        public Criteria andDecValIsNull() {
            addCriterion("dec_val is null");
            return (Criteria) this;
        }

        public Criteria andDecValIsNotNull() {
            addCriterion("dec_val is not null");
            return (Criteria) this;
        }

        public Criteria andDecValEqualTo(Double value) {
            addCriterion("dec_val =", value, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValNotEqualTo(Double value) {
            addCriterion("dec_val <>", value, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValGreaterThan(Double value) {
            addCriterion("dec_val >", value, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValGreaterThanOrEqualTo(Double value) {
            addCriterion("dec_val >=", value, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValLessThan(Double value) {
            addCriterion("dec_val <", value, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValLessThanOrEqualTo(Double value) {
            addCriterion("dec_val <=", value, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValIn(List<Double> values) {
            addCriterion("dec_val in", values, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValNotIn(List<Double> values) {
            addCriterion("dec_val not in", values, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValBetween(Double value1, Double value2) {
            addCriterion("dec_val between", value1, value2, "decVal");
            return (Criteria) this;
        }

        public Criteria andDecValNotBetween(Double value1, Double value2) {
            addCriterion("dec_val not between", value1, value2, "decVal");
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