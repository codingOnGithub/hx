package com.hotent.bpmx.api.cmd;

/**
 * 任务催办命令接口
 * @author csx
 *
 */
public interface TaskUrgeCmd extends AbstractTaskActionCmd{

	/**
	 * 获取任务催办类型，如短信、邮件、内部消息等
	 * @return
	 */
	public String[] getUrgeType();
}
