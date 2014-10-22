package com.hotent.bpmx.extension.org.def;

import com.hotent.bpmx.api.plugin.org.def.BpmIdentityLinkDef;

/**
 * 
 * <pre> 
 * 描述：
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-23-下午9:22:43
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractBpmIdentityLinkDef implements BpmIdentityLinkDef{
	
	protected String configJson=null;
	
	@Override
	public String getJsonConfig() {
		return configJson;
	}
	/**
	 * 对Json进行格式化成Paramter参数
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public abstract void toParameters();
	
}
