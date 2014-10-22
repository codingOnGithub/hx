package com.hotent.org.api.model;

import java.util.ArrayList;
import java.util.List;

import com.hotent.base.api.BaseModel;
/**
 * 
 * 描述：抽象用户组类型 
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-14-下午6:11:56
 * 版权：广州宏天软件有限公司版权所有
 */
public interface Group extends IdentityType,BaseModel{
	public static enum Status {
		inactive("inactive"),
		actived("actived"), 
		locked("locked"),
		deleted("deleted");
		
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
	
	/**
	 * @功能描述：用户组类型常量
	 */
	public final static class GROUP_TYPE{
		/**
		 * 用户组类型-组织=org
		 */
		public final static String ORG="org";
		/**
		 * 用户组类型-岗位=pos
		 */
		public final static String POS="pos";		
		/**
		 * 用户组类型-职位=job
		 */
		public final static String JOB="job";
		/**
		 * 用户组类型-角色=role
		 */
		public final static String ROLE="role";		
		/**
		 * 用户组类型-用户组=team
		 */
		public final static String TEAM="team";
		
		/**
		 * 用户组类型-用户组=other
		 */
		public final static String OTHER="other";
	}

	/**
	 * 组ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getGroupId();
	
	/**
	 * 返回组名
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName();
	
	/**
	 * 返回用户组业务主键
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getKey();
	
	/**
	 * 返回所属维度业务主键
	 * @return
	 * @since  1.0.0 
	 */
	public String getDimId();
	
	/**
	 * 返回状态
	 * @return
	 */
	public Status getStatus();
	
	/**
	 * 返回用户组的类型
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getType();
	
	/**
	 * 返回描述
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDesc();
	
	/**
	 * 是否删除
	 * @return
	 */
	public boolean isDeleted();
	
		
}
