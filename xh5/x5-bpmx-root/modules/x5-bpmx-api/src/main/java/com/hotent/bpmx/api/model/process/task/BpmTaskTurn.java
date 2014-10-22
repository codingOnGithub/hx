package com.hotent.bpmx.api.model.process.task;

import java.util.Date;

import com.hotent.base.api.TreeType;

/**
 * 任务的执行更改
 * 如任务的转办与代理
 * @author csx
 *
 */
public interface BpmTaskTurn {
	/**
	 * 代理=agent
	 */
	public final static String TRUN_TYPE_AGENT="agent";
	/**
	 * 转办=shift
	 */
	public final static String TRUN_TYPE_SHIFT="shift";
	/**
	 * 正在运行=running
	 */
	public final static String STATUS_RUNNING="running";
	/**
	 * 已经完成=finish
	 */
	public final static String STATUS_FINISH="finish";
	/**
	 * 取消=cancel
	 */
	public final static String STATUS_CANCEL="cancel";
	
	/**
	 * ID
	 */
	public String getId();
	/**
	 * 任务ID
	 * @return
	 */
	public String getTaskId();
	/**
	 * 任务名称
	 * @return
	 */
	public String getTaskName();
	/**
	 * 待办事项标题
	 * @return
	 */
	public String getTaskSubject();	
	/**
	 * 任务节点ID
	 * @return
	 */
	public String getNodeId();
	/**
	 * 流程实例ID
	 * @return
	 */
	public String getProcInstId();	
	/**
	 * 任务所属人ID
	 * @return
	 */
	public String getOwnerId();
	/**
	 * 任务所属人名
	 * @return
	 */
	public String getOwenerName();
	/**
	 * 任务的执行人ID
	 * @return
	 */
	public String getExecUserId();
	/**
	 * 任务执行人名
	 * @return
	 */
	public String getExecUserName();	
	/**
	 * 任务承接人ID
	 */
	public String getAssigneeId();	
	/**
	 * 任务承接人名
	 * @return
	 */
	public String getAssigneeName();
	/**
	 * 任务状态
	 * @return
	 */
	public String getStatus();
	/**
	 * 任务的转发类型
	 * 如代理=TRUN_TYPE_AGENT
	 * 转办=TRUN_TYPE_AGENT
	 * @return
	 */
	public String getTurnType();
	/**
	 * 待办所属的分类 与流程定义的分类一致
	 * @return
	 */
	public TreeType getType();	
	/**
	 * 任务备注
	 * @return
	 */
	public String getMemo();
	/**
	 * 任务创建时间
	 * @return
	 */
	public Date getCreatetime();
	/**
	 * 任务完成时间
	 * @return
	 */
	public Date getFinishTime();
}
