package com.hotent.base.api.query;

public interface QueryField extends WhereClause{
    /**
     * 返回字段名
     * @return
     */
    public String getField();
    /**
     * 比较符
     * @return
     */
    public QueryOP getCompare();
    /**
     * 返回值
     * @return
     */
    public Object getValue();
    
}
