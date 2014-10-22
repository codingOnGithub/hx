package com.hotent.bpmx.activiti.ext.listener;

import com.hotent.bpmx.api.constant.EventType;

/**
 * 
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-activiti
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-14-上午10:40:48
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class StartEventListener extends AbstractExecutionListener {

	@Override
	public EventType getEventType() {
		return EventType.START_EVENT;
	}

	
}
