package com.hotent.bpmx.api.constant;

/**
 * 人员抽取类型。
 * <pre> 
 * 描述：人员抽取类型。
 * 构建组：x5-bpmx-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-18-下午2:26:27
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum ExtractType {
	EXACT_NOEXACT("noexact"),
	EXACT_EXACT_USER("exactuser"),
	EXACT_EXACT_SECOND("exactsecond"),
	EXACT_USER_GROUP("usergroup");
	
	private String name="";
	private ExtractType(String name){
		this.name=name;
	}
	
	public String toString(){
		return this.name;
	}
	
	public static void main(String[] args) {
		System.out.println(ExtractType.EXACT_EXACT_USER);
	}
	
}
