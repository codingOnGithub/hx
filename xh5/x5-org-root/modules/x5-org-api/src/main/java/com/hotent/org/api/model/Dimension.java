package com.hotent.org.api.model;

import java.util.ArrayList;
import java.util.List;

import com.hotent.org.api.model.Group.Status;

/**
 * 
 * <pre>
 * 描述：用户组维度
 * 构建组：x5-org-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-20-上午11:15:18
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface Dimension {
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
		
		public static List<Status> getValidStatuses(){
			return new ArrayList<Status>(){
				{
					add(Status.actived);
				}
				{
					add(Status.locked);
				}
			};
		}
	}

	public static final char IS_COMBINATION_Y = 'Y';

	public static final char IS_COMBINATION_N = 'N';

	/**
	 * 维度ID
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String getDimId();

	/**
	 * 维度名称
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String getName();

	/**
	 * 维度Key
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String getDimKey();

	/**
	 * 维度所属用户组类型
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String getGroupType();

	/**
	 * 维度描述
	 * 
	 * @return String
	 * @exception
	 * @since 1.0.0
	 */
	public String getDesc();

	/**
	 * <pre>
	 * 是否为系统缺省的，若为系统的缺省的，即不允许进行删除及更新，
	 * 如角色、组织、职位、岗位类型的维度则系统默认需要带有，可进行自增加及删除
	 * </pre>
	 * 
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public boolean isDefault();

	/**
	 * 排序号
	 * 
	 * @return Integer
	 * @exception
	 * @since 1.0.0
	 */
	public Integer getSn();

	/**
	 * 返回状态
	 * 
	 * @return
	 */
	public Status getStatus();

}
