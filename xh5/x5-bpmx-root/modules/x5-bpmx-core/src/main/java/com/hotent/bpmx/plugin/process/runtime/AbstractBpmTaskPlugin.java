package com.hotent.bpmx.plugin.process.runtime;

import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;
import com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin;
/**
 * 
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-19-下午3:55:31
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractBpmTaskPlugin extends AbstractBpmPlugin implements BpmTaskPlugin{

	private BpmDelegateTask bpmDelegateTask;

	public BpmDelegateTask getBpmDelegateTask() {
		return bpmDelegateTask;
	}

	public void setBpmDelegateTask(BpmDelegateTask bpmDelegateTask) {
		this.bpmDelegateTask = bpmDelegateTask;
	}
	
	
}
