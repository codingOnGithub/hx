package com.hotent.bpmx.api.model.process.def;

/**
 * 
 * 描述：流程变量定义模型
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-14-下午4:13:47
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmVariableDef {
	public static final String VAR_TYPE_STRING="string";		/* 用流程变量实例的变量值字段存储 */
	public static final String VAR_TYPE_INT="int";						/* 用流程变量实例的变量值字段存储 */
	public static final String VAR_TYPE_FLOAT="float";			/* 用流程变量实例的变量值字段存储 */
	public static final String VAR_TYPE_DOUBLE="double";		/* 用流程变量实例的变量值字段存储 */
	public static final String VAR_TYPE_DATE="date";				/* 用流程变量实例的变量值字段存储 */
	public static final String VAR_TYPE_JSON="json";  				/* 用流程变量大数据值的大文本字段存储 */
	public static final String VAR_TYPE_XML="xml";					/* 用流程变量大数据值的大文本字段存储 */
	public static final String VAR_TYPE_BYTES="bytes";			/* 用流程变量大数据值的二进制字段存储 */
	/**
	 * Id
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVarId();
	/**
	 * 变量标识名称 
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName();
	/**
	 * 变量Key
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getVarKey();
	/**
	 * 变量类型
	 * string,int,float,double,date,json,xml,db
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDataType();
	/**
	 * 流程节点ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getNodeId();	
	/**
	 * 流程定义ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcDefId();
	/**
	 * 是否必需
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean isRequired();
}
