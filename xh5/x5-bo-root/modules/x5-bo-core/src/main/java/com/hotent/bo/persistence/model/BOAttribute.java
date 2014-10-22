package com.hotent.bo.persistence.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:xbo_attr entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:何一帆
 * 创建时间:2014-01-21 10:38:10
 */
public class BOAttribute extends AbstractModel<String>{
	public final static class BOATTRIBUTE_TYPE{
		/**
		 * 基本数据类型
		 */
		public final static String BASE = "base";
		/**
		 * 引用类型
		 */
		public final static String REF = "ref";
	}
	
	public final static class BOATTRIBUTE_REF_TYPE{
		/**
		 * 有一个引用类型数据
		 */
		public final static String HAS_ONE = "hasone";
		/**
		 * 有多个引用类型数据
		 */
		public final static String HAS_MANY = "hasmany";
	}
	
	public final static class BOATTRIBUTE_DATA_TYPE{
		/**
		 * 字符串
		 */
		public final static String STRING = "string";
		/**
		 * 数字
		 */
		public final static String NUMBER = "number";
		/**
		 * 日期
		 */
		public final static String DATE = "date";
		/**
		 * 布尔
		 */
		public final static String BOOLEAN = "boolean";
	}
	
	public final static class BOATTRIBUTE_IS_REQUIRED{
		/**
		 * 末激活
		 */
		public final static char REQUIRED_YES = 'Y';
		/**
		 * 已激活
		 */
		public final static char REQUIRED_NO = 'N';


	}
	
	public final static class BOATTRIBUTE_IS_LIST{
		/**
		 * 末激活
		 */
		public final static char LIST_YES = 'Y';
		/**
		 * 已激活
		 */
		public final static char LIST_NO = 'N';


	}
	
	protected String  id; /*属性定义ID*/
	protected String  defId; /*从属对象定义ID*/
	protected String  name; /*属性名称*/
	protected String  desc; /*属性描述*/
	protected String  type; /*属性类型。base=基本；ref=对象引用（hasOne | hasMany 等）*/
	protected char  isList; /*是否列表属性*/
	protected String  dataType; /*数据类型。string=字符串；number=数值；datetime=日期（长日期，通过显示格式来限制）；*/
	protected String  defaultValue; /*基本默认值*/
	protected String  format; /*基本类型显示格式*/
	protected char  isRequired; /*是否必填*/
	protected String  refType; /*对象引用类型。hasOne=单个对象；hasMany=对象集合；*/
	protected String  refId; /*对象引用ID*/  
	
	protected List<BORule> attRuleList=new ArrayList<BORule>();    //属性自己的规则  /*xbo_rule列表*/ 
	
	protected BODef  refBODef;    /*引用对象*/
	
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 属性定义ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setDefId(String defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 从属对象定义ID
	 * @return
	 */
	public String getDefId() 
	{
		return this.defId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 属性名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 属性描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 属性类型。base=基本；ref=对象引用（hasOne | hasMany 等）
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setIsList(char isList) 
	{
		this.isList = isList;
	}
	/**
	 * 返回 是否列表属性
	 * @return
	 */
	public char getIsList() 
	{
		return this.isList;
	}
	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}
	/**
	 * 返回 数据类型。string=字符串；number=数值；datetime=日期（长日期，通过显示格式来限制）；
	 * @return
	 */
	public String getDataType() 
	{
		return this.dataType;
	}
	public void setDefaultValue(String defaultValue) 
	{
		this.defaultValue = defaultValue;
	}
	/**
	 * 返回 基本默认值
	 * @return
	 */
	public String getDefaultValue() 
	{
		return this.defaultValue;
	}
	public void setFormat(String format) 
	{
		this.format = format;
	}
	/**
	 * 返回 基本类型显示格式
	 * @return
	 */
	public String getFormat() 
	{
		return this.format;
	}
	public void setIsRequired(char isRequired) 
	{
		this.isRequired = isRequired;
	}
	/**
	 * 返回 是否必填
	 * @return
	 */
	public char getIsRequired() 
	{
		return this.isRequired;
	}
	public void setRefType(String refType) 
	{
		this.refType = refType;
	}
	/**
	 * 返回 对象引用类型。hasOne=单个对象；hasMany=对象集合；
	 * @return
	 */
	public String getRefType() 
	{
		return this.refType;
	}
	public void setRefId(String refId) 
	{
		this.refId = refId;
	}
	/**
	 * 返回 对象引用ID
	 * @return
	 */
	public String getRefId() 
	{
		return this.refId;
	}
	
	
	/**
	 * 返回 属性的个性规则
	 * @return
	 */
	public List<BORule> getAttRuleList()
	{
		return attRuleList;
	}
	public void setAttRuleList(List<BORule> attRuleList)
	{
		this.attRuleList = attRuleList;
	}
	
	/**
	 * 返回 对象引用
	 * @return
	 */
	public BODef getRefBODef()
	{
		return refBODef;
	}
	public void setRefBODef(BODef refBODef)
	{
		this.refBODef = refBODef;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("defId", this.defId) 
		.append("name", this.name) 
		.append("desc", this.desc) 
		.append("type", this.type) 
		.append("isList", this.isList) 
		.append("dataType", this.dataType) 
		.append("defaultValue", this.defaultValue) 
		.append("format", this.format) 
		.append("isRequired", this.isRequired) 
		.append("refType", this.refType) 
		.append("refId", this.refId) 
		.toString();
	}
}