package com.hotent.org.persistence.model;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.org.api.model.AttributeValue;

/**
 * 对象功能:用户及组属性值 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-27 10:14:26
 */
public class DefaultAttributeValue extends AbstractModel<String> implements AttributeValue{
	protected String  valId; /*属性值ID*/
	protected String  attrId; /*属性ID*/
	protected String  attrKey; /*属性业务主键*/
	protected String  userId; /*用户ID*/
	protected String  groupId; /*用户组ID*/
	protected String  dataType; /*属性数据类型*/
	protected String  textVal; /*字符串属性值*/
	protected java.util.Date  dateVal; /*日期类型属性值*/
	protected Long  longVal; /*长整数类型属性值*/
	protected Double  decVal; /*双精度类型属性值*/
	@Override
	public void setId(String valId) {
		this.valId = valId;
	}
	@Override
	public String getId() {
		return valId;
	}	
	public void setValId(String valId) 
	{
		this.valId = valId;
	}
	/**
	 * 返回 属性值ID
	 * @return
	 */
	public String getValId() 
	{
		return this.valId;
	}
	public void setAttrId(String attrId) 
	{
		this.attrId = attrId;
	}
	/**
	 * 返回 属性ID
	 * @return
	 */
	public String getAttrId() 
	{
		return this.attrId;
	}
	public void setAttrKey(String attrKey) 
	{
		this.attrKey = attrKey;
	}
	/**
	 * 返回 属性业务主键
	 * @return
	 */
	public String getAttrKey() 
	{
		return this.attrKey;
	}
	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public String getUserId() 
	{
		return this.userId;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}
	/**
	 * 返回 用户组ID
	 * @return
	 */
	public String getGroupId() 
	{
		return this.groupId;
	}
	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}
	/**
	 * 返回 属性数据类型
	 * @return
	 */
	public String getDataType() 
	{
		return this.dataType;
	}
	public void setTextVal(String textVal) 
	{
		this.textVal = textVal;
	}
	/**
	 * 返回 字符串属性值
	 * @return
	 */
	public String getTextVal() 
	{
		return this.textVal;
	}
	public void setDateVal(java.util.Date dateVal) 
	{
		this.dateVal = dateVal;
	}
	/**
	 * 返回 日期类型属性值
	 * @return
	 */
	public java.util.Date getDateVal() 
	{
		return this.dateVal;
	}
	public void setLongVal(Long longVal) 
	{
		this.longVal = longVal;
	}
	/**
	 * 返回 长整数类型属性值
	 * @return
	 */
	public Long getLongVal() 
	{
		return this.longVal;
	}
	public void setDecVal(Double decVal) 
	{
		this.decVal = decVal;
	}
	/**
	 * 返回 双精度类型属性值
	 * @return
	 */
	public Double getDecVal() 
	{
		return this.decVal;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("valId", this.valId) 
		.append("attrId", this.attrId) 
		.append("attrKey", this.attrKey) 
		.append("userId", this.userId) 
		.append("groupId", this.groupId) 
		.append("dataType", this.dataType) 
		.append("textVal", this.textVal) 
		.append("dateVal", this.dateVal) 
		.append("longVal", this.longVal) 
		.append("decVal", this.decVal) 
		.toString();
	}
}