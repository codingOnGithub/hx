package com.hotent.base.db.model;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.hotent.base.api.query.QueryField;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.core.util.time.DateFormatUtil;

public class DefaultQueryField implements QueryField{
	
    //字段
    private String field;
    //比较符
    private QueryOP compare;
    //比较值
    private Object value;
    //字段前缀名，一般用于表的别名，如user.
    //private String preFieldName="";

    public DefaultQueryField() {
    }

    public DefaultQueryField(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public DefaultQueryField(String field, QueryOP compare, Object value) {
        this.field = field;
        this.compare = compare;

        if (QueryOP.LIKE.equals(compare)) {
            this.value = "%" + value + "%";
        } else if (QueryOP.LEFT_LIKE.equals(compare)) {
            this.value = "%" + value;
        } else if (QueryOP.RIGHT_LIKE.equals(compare)) {
            this.value = value + "%";
        } else if (QueryOP.IN.equals(compare)) {
       	 	this.value = getInValueSql();
        } else {
            this.value = value;
        }

    }

    /**
     * 针对in查询方式，根据传入的value的类型做不同的处理。
     * value 是 string，则分隔字符串，然后合并为符合in规范的字符串。
     * value 是 list，则直接合并为符合in规范的字符串。
     * @return
     */
    private String getInValueSql(){    	
       	if(value instanceof String){			//字符串形式，通过逗号分隔
    		StringBuilder sb = new StringBuilder();
        	sb.append("(");
        	StringTokenizer st =new StringTokenizer(value.toString(),",");
        	while(st.hasMoreTokens()){
        		sb.append("\"");
        		sb.append(st.nextToken());
        		sb.append("\"");
        		sb.append(",");
        	}        	
        	sb = new StringBuilder(sb.substring(0, sb.length()-1));
        	sb.append(")");
        	return sb.toString();
    	}else if(value instanceof List){		//列表形式
    		List<Object> objList = (List<Object>)value;
    		StringBuilder sb = new StringBuilder();
    		sb.append("(");
    		for(Object obj:objList){        			
    			sb.append("\"");
    			sb.append(obj.toString());
    			sb.append("\"");
    			sb.append(",");    	        	
    		}
    		sb = new StringBuilder(sb.substring(0, sb.length()-1));
    		sb.append(")");
        	return sb.toString();        		
    	}  
       	return "";
    }
    
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public QueryOP getCompare() {
        return compare;
    }

    public void setCompare(QueryOP compare) {
        this.compare = compare;
    }

    public String getSql() {
        if (compare == null) {
            compare = QueryOP.EQUAL;
        }
        //String sql=preFieldName+"." + field;
        String fieldParam = "#{" + field + "}";
        String sql = field;
        if (QueryOP.EQUAL.equals(compare)) {
            sql += " = " + fieldParam;
        } else if (QueryOP.LESS.equals(compare)) {
            sql += " < " + fieldParam;
        } else if (QueryOP.LESS_EQUAL.equals(compare)) {
            sql += " <= " + fieldParam;
        } else if (QueryOP.GREAT.equals(compare)) {
            sql += " > " + fieldParam;
        } else if (QueryOP.GREAT_EQUAL.equals(compare)) {
            sql += " >= " + fieldParam;
        } else if (QueryOP.LEFT_LIKE.equals(compare)) {
            sql += " like " + fieldParam;
        } else if (QueryOP.RIGHT_LIKE.equals(compare)) {
            sql += " like  " + fieldParam;
        } else if (QueryOP.LIKE.equals(compare)) {
            sql += " like  " + fieldParam;
        } else if (QueryOP.IS_NULL.equals(compare)) {
            sql += " is null " + fieldParam;
        } else if (QueryOP.NOTNULL.equals(compare)) {
            sql += " is not null";
        }  else if(QueryOP.IN.equals(compare)) {
        	sql += " in  " + this.value;
        }  else if(QueryOP.BETWEEN.equals(compare)) {
        	sql += getBetweenSql();
        } else {
            sql += " =  " + fieldParam;
        }
        return sql;
    }
	private String getBetweenSql(){
		StringBuilder sb = new StringBuilder();
		sb.append(" between ");
		if(this.value instanceof List){
			List<Object> objList = (List<Object>)value;
			for(int i=0;i<objList.size();i++){
				Object obj = objList.get(i);
				if(i==1){
					sb.append(" and ");
				}
				if(obj instanceof Date){
					String dateString = DateFormatUtil.format((Date)obj);
					sb.append("\"" + dateString + "\"");
				}else{
					sb.append("\"" + obj.toString() + "\"");
				}
			}
		}
		sb.append(" ");
		return sb.toString();
	}
}