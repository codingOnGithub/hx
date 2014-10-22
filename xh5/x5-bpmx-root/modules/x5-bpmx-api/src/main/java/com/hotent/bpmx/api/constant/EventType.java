package com.hotent.bpmx.api.constant;

/**
 * 
 * <pre> 
 * 描述：事件类型,如节点创建事件,节点结束事件,任务创建事件,任务结束事件等
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-13-下午6:14:33
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum EventType {

	START_EVENT("startEvent"),
	END_EVENT("endEvent"),
	TASK_CREATE_EVENT("taskCreate"),
	TASK_PRE_COMPLETE("preTaskComplete"),
	TASK_COMPLETE_EVENT("taskComplete"),
	TASK_SIGN_CREATE_EVENT("taskSignCreate");
	
	private String eventName=null;
	
	private EventType(String eventName) {
		this.eventName=eventName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Override
	public String toString() {
		return eventName;
	}
}
