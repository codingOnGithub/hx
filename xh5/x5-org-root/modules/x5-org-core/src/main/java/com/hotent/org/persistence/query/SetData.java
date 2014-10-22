package com.hotent.org.persistence.query;

public class SetData {
    private String property;

    private Object value;

    public String getProperty() {
		return property;
	}
    
    public Object getValue() {
        return value;
    }

    protected SetData(String property, Object value) {
        this.value = value;
        this.property = property;
    }
}