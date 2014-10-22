package com.hotent.org.api.model;

import java.util.ArrayList;
import java.util.List;

import com.hotent.org.api.model.RelationType.Status;

/**
 * 
 * 描述：用户实体接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-上午11:39:09
 * 版权：广州宏天软件有限公司版权所有
 */
public interface User extends IdentityType{
	/**
	 * 状态。inactive 未激活（可登录，但直接跳到待激活页面）；actived 已激活（可以登录）；locked 锁定（不可登录）；deleted 已删除
	 * 
	 * @author RaiseDragon
	 * 
	 */
	public static enum Status {
		inactive("inactive"),actived("actived"), locked("locked"), deleted("deleted");

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
	 * 男性=Male
	 */
	public static final String SEX_MALE="Male";
	/**
	 * 女性=Female
	 */
	public static final String SEX_FAMALE="Female";
	
	/**
	 * 用户标识Id
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getUserId();
	/**
	 * 用户姓名
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFullname();
	/**
	 * 用户账号
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getAccount();
	/**
	 * 获取密码
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getPassword();
	
	/**
	 * 用户状态
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public Status getStatus();
	
}
