package com.hotent.bpmx.api.constant;

/**
 * 设计器类型。
 * web：web设计器
 * flash:flash设计器。
 * eclipse:eclipse客户端设计出来的。
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-2-8-上午11:51:07
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum DesignerType {
	
	WEB,ECLIPSE,FLASH;
	
	
	public static void main(String[] args) {
		System.out.println(DesignerType.ECLIPSE.name());
	}
	
	
}
