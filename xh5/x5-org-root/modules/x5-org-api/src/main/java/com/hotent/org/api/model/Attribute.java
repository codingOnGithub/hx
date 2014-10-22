/**
 * @版权所有 2013-2015 广州宏天软件有限公司
 */
package com.hotent.org.api.model;

import java.util.ArrayList;
import java.util.List;

import com.hotent.org.api.model.Group.Status;

/**
 * @功能描述：TODO
 * @开发公司：广州宏天软件有限公司
 * @作者：Winston Yan
 * @邮箱：yancm@jee-soft.cn
 * @创建时间：2013-11-27 下午8:54:38
 */
public interface Attribute {
	/**
	 * 状态。actived 已激活；locked 锁定（禁用）；deleted 已删除,
	 * 
	 * @author RaiseDragon
	 * 
	 */
	public static enum Status {
		actived("actived"), locked("locked"), deleted("deleted");

		private final String value;

		private Status(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}

		public static List<Status> getValidStatuses() {
			return new ArrayList<Status>() {
				{
					add(Status.actived);
				}
				{
					add(Status.locked);
				}
			};
		}
	}

	/**
	 * 属性归属类型：group：组属性；user：用户属性
	 * 
	 * @author RaiseDragon
	 * 
	 */
	public static enum BelongType {
		group("group"), user("user");

		private final String value;

		private BelongType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}
	}

	/**
	 * 属性数据类型。text 字符串；date 日期；long 长整数；double 双精度浮点。
	 * 
	 * @author RaiseDragon
	 * 
	 */
	public static enum DataType {
		TEXT("TEXT"), DATE("DATE"), LONG("LONG"), DOUBLE("DOUBLE");

		private final String value;

		private DataType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return getValue();
		}
	}

	/**
	 * 返回属性ID
	 * 
	 * @return
	 */
	public String getAttrId();

	/**
	 * 返回属性名称
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * 返回属性业务主键
	 * 
	 * @return
	 */
	public String getAttrKey();

	/**
	 * 返回属性归属类型
	 * 
	 * @return
	 */
	public BelongType getBelongType();

	/**
	 * 返回数据类型
	 * 
	 * @return
	 */
	public DataType getDataType();

	/**
	 * 返回描述
	 * 
	 * @return
	 */
	public String getDesc();

	/**
	 * 返回创建者所属组织ID
	 * 
	 * @return
	 */
	public String getCreateOrgId();

	/**
	 * 返回状态
	 * 
	 * @return
	 */
	public Status getStatus();
}
