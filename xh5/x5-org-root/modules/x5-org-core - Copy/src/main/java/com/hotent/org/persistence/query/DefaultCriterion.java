package com.hotent.org.persistence.query;

import java.util.List;

import com.hotent.org.api.query.Criterion;

public class DefaultCriterion implements Criterion {
    private String condition;

    private Object value;

    private Object secondValue;
    
    private String property;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#getCondition()
	 */
    @Override
	public String getCondition() {
        return condition;
    }

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#getValue()
	 */
    @Override
	public Object getValue() {
        return value;
    }

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#getSecondValue()
	 */
    @Override
	public Object getSecondValue() {
        return secondValue;
    }
    
    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#getProperty()
	 */
    @Override
	public String getProperty() {
		return property;
	}

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#isNoValue()
	 */
    @Override
	public boolean isNoValue() {
        return noValue;
    }

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#isSingleValue()
	 */
    @Override
	public boolean isSingleValue() {
        return singleValue;
    }

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#isBetweenValue()
	 */
    @Override
	public boolean isBetweenValue() {
        return betweenValue;
    }

    /* (non-Javadoc)
	 * @see com.hotent.org.api.sss#isListValue()
	 */
    @Override
	public boolean isListValue() {
        return listValue;
    }

    protected DefaultCriterion(String condition) {
        super();
        this.condition = condition;
        this.noValue = true;
    }

    protected DefaultCriterion(String condition, Object value,String property) {
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

    protected DefaultCriterion(String condition, Object value, Object secondValue,String property) {
        super();
        this.condition = condition;
        this.value = value;
        this.property = property;
        this.secondValue = secondValue;
        this.betweenValue = true;
    }
}