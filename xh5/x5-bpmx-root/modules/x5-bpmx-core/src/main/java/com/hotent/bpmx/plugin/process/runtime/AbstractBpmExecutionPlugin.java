package com.hotent.bpmx.plugin.process.runtime;

import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;
import com.hotent.bpmx.api.plugin.process.runtime.BpmExecutionPlugin;
/**
 * 
 * <pre> 
 * 描述：非任务执行插件基础类
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-19-下午3:51:53
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractBpmExecutionPlugin extends AbstractBpmPlugin implements BpmExecutionPlugin{
	
	protected BpmDelegateExecution bpmDelegateExecution;

	public BpmDelegateExecution getBpmDelegateExecution() {
		return bpmDelegateExecution;
	}

	public void setBpmDelegateExecution(BpmDelegateExecution bpmDelegateExecution) {
		this.bpmDelegateExecution = bpmDelegateExecution;
	}
	
	
}
