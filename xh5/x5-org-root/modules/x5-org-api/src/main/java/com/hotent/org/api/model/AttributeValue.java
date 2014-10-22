/**
 * @版权所有 2013-2015 广州宏天软件有限公司
 */
package com.hotent.org.api.model;

import java.math.BigDecimal;
import java.util.Date;

import com.hotent.org.api.model.Attribute.DataType;

/**
 * @功能描述：TODO
 * @开发公司：广州宏天软件有限公司
 * @作者：Winston Yan
 * @邮箱：yancm@jee-soft.cn
 * @创建时间：2013-11-27 下午9:05:06
 */
public interface AttributeValue {
	/**
	 * 返回属性值ID
	 * @return
	 */
	public String getValId();
	
	/**
	 * 返回属性ID
	 * @return
	 */
	public String getAttrId();
	
	/**
	 * 返回属性业务主键
	 * @return
	 */
	public String getAttrKey();
	
	/**
	 * 返回用户ID
	 * @return
	 */
	public String getUserId();
	
	/**
	 * 返回用户组ID
	 * @return
	 */
	public String getGroupId();
	
	/**
	 * 返回数据类型
	 * @return
	 */
	public DataType getDataType();
	
	/**
	 * 返回字符串属性值
	 * @return
	 */
	public String getTextVal();
	
	/**
	 * 返回日期属性值
	 * @return
	 */
	public Date getDateVal();
	
	/**
	 * 返回长整数属性值
	 * @return
	 */
	public Long getLongVal();
	
	/**
	 * 返回双精度属性值
	 * @return
	 */
	public Double getDecVal();
}
