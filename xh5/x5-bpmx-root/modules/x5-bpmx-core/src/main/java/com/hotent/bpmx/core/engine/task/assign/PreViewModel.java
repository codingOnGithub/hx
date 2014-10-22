package com.hotent.bpmx.core.engine.task.assign;

/**
 * 预览枚举。
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-18-下午3:36:35
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum PreViewModel {
	
	START_USER,
	PRE_VIEW_USER,
	FORM_USER,
	FORM_ORG,
	FORM_POS,
	FORM_ROLE,
	START_ORG,
	PRE_ORG;
	
//	/** 发起人*/
//	public final static int START_USER=1;
//	/**上个任务执行人*/
//	public final static int PRE_VIEW_USER=2;
//	/**表单用户变量*/
//	public final static int FORM_USER=3;
//	/**表单组织变量*/
//	public final static int FORM_ORG=4;		
//	/**表单岗位变量*/
//	public final static int FORM_POS=6;	
//	/** 表单角色变量*/
//	public final static int FORM_ROLE=7;
//	/**发起人的组织*/
//	public final static int START_ORG=8;
//	/**上一个执行人的部门*/
//	public final static int PRE_ORG=9;
	
	
	public String getName(){
		String tmp="";
		switch (this) {
			case START_USER:
				tmp= "发起人";
				break;
			case PRE_VIEW_USER:
				tmp="上个任务执行人";
				break;
			case FORM_USER:
				tmp="表单用户变量";
				break;
			case FORM_ORG:
				tmp="表单组织变量";
				break;
			case FORM_POS:
				tmp="表单岗位变量";
				break;
			case START_ORG:
				tmp="发起人的组织";
				break;
			case PRE_ORG:
				tmp="上一个执行人的组织";
				break;
				
			
		}
		return tmp;
	}
	
	public String toString(){
		return this.getName();
	}
	
	public static void main(String[] args) {
		System.out.println(PreViewModel.FORM_ORG);
	}

}
