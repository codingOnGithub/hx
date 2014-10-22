package com.hotent.bpmx.extension.org.def;

/**
 * 
 * <pre> 
 * 描述：启动用户计算配置实体
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-23-下午9:23:34
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class StartUserLinkDef extends AbstractBpmIdentityLinkDef{
	private String startUserId;

	public String getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	@Override
	public void toParameters() {
		// TODO Auto-generated method stub
		
	}

}
