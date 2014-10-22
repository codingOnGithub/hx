package com.hotent.org.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 描述：关系类型接口
 * 构建组：x5-org-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-9-上午9:08:32
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface RelationType {
	public static enum Type {
		group("group"), 
		user("user"), 
		usergroup("usergroup");
		
		private final String value;

		private Type(String value) {
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
	
	public static enum IsBidirection {
		Y('Y'), 
		N('N');
		
		private final char value;

		private IsBidirection(char value) {
			this.value = value;
		}
		
		public char getValue() {
			return value;
		}
		@Override
		public String toString() {
			return getValue()+"";
		}
	}
	
	public static enum Status {
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
	
	/*关系约束类型。1对1=one2one；1对多=one2many；多对1=many2one；多对多=many2many*/
	public static enum ConstType {
		ONE2ONE("one2one"), 
		ONE2MANY("one2many"),
		MANY2MANY("many2many"),
		MANY2ONE("many2one");
		
		private final String value;

		private ConstType(String value) {
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
	
}
