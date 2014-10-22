package com.hotent.bpmx.api.service;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.bpmx.api.cmd.TaskAgentCmd;
import com.hotent.bpmx.api.cmd.TaskCopyCmd;
import com.hotent.bpmx.api.cmd.TaskFinishCmd;
import com.hotent.bpmx.api.cmd.TaskRecoverCmd;
import com.hotent.bpmx.api.cmd.TaskSplitCmd;
import com.hotent.bpmx.api.cmd.TaskTurnCmd;
import com.hotent.bpmx.api.cmd.TaskUrgeCmd;
import com.hotent.bpmx.api.model.process.task.BpmTask;
import com.hotent.org.api.model.User;

/**
 * 
 * 描述：流程任务服务接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-8-上午10:51:13
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmTaskService {
	/**
	 * 按用户ID获取所有的待办任务列表
	 * @param userId
	 * @return 
	 * List<BpmTask>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmTask> getTasksByUserId(String userId);
	/**
	 * 按用户ID获取所有的待办任务列表
	 * @param userId
	 * @param page
	 * @return 
	 * List<BpmTask>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmTask> getTasksByUserId(String userId,Page page);
	/**
	 * 按用户ID及条件过滤获取所有的待办任务列表
	 * @param userId
	 * @param filter
	 * @return
	 */
	public List<BpmTask> getTasksByUserId(String userId,QueryFilter filter);
	/**
	 * 查找所有的任务列表并进行分页过滤查询
	 * @param filter
	 * @return
	 */
	public List<BpmTask> getAllTasks(QueryFilter filter);
	/**
	 * 获取别人代理给某一用户的任务列表
	 * @param userId
	 * @return
	 */
	public List<BpmTask> getAgentTasksByUserId(String userId);
	/**
	 * 获取别人代理给我的任务列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<BpmTask> getAgentTasksByUserId(String userId,Page page);
	/**
	 * 获取别人代理给某用户的任务列表
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public List<BpmTask> getAgentTasksByUserId(String userId,QueryFilter queryFilter);
	/**
	 * 获取某一用户代理给别人的任务列表
	 * @param userId
	 * @return
	 */
	public List<BpmTask> getAgentedToTasksByUserId(String userId);
	/**
	 * 获取某一用户代理给别人的任务列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<BpmTask> getAgentedToTasksByUserId(String userId,Page page);
	/**
	 * 获取某一用户代理给别人的任务列表
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public List<BpmTask> getAgentedToTasksByUserId(String userId,QueryFilter queryFilter);
	/**
	 * 取消任务代理
	 * @param taskCmd
	 * @return
	 */
	public boolean cancelAgentedToTask(TaskFinishCmd taskCmd);
	/**
	 * 完成任务
	 * @param taskId
	 * @return
	 */
	public boolean finishTask(TaskFinishCmd taskCmd);
	
	/**
	 * 任务回退
	 * @param taskId
	 * @return
	 */
	public boolean backTask(TaskFinishCmd taskCmd);
	/**
	 * 评论任务
	 * @param taskId
	 * @param comment 评论内容
	 * @return
	 */
	public boolean commentTask(TaskFinishCmd taskCmd);
	/**
	 * 结束任务
	 * @param taskId
	 * @param reason
	 * @return
	 */
	public boolean endTask(TaskFinishCmd taskCmd);
	/**
	 * 任务沟通
	 * @param taskCmd
	 * @return
	 */
	public boolean communicateTask(TaskFinishCmd taskCmd);
	/**
	 * 从一任务中复制另一任务
	 * @param taskId
	 * @return
	 */
	public BpmTask copyTask(String taskId);
	/**
	 * 任务抄送多个用户或用户组
	 * @param taskId
	 * @param userIds
	 * @return
	 */
	public BpmTask copyToUsers(TaskCopyCmd taskCmd);
	/**
	 * 获取抄送给某用户的任务
	 * @param userId
	 * @return
	 */
	public List<BpmTask> getTaksOfCopyToByUserId(String userId);
	
	/**
	 * 获取抄送给某用户的任务
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<BpmTask> getTaksOfCopyToByUserId(String userId,Page page);
	
	/**
	 * 获取抄送给某用户的任务
	 * @param userId
	 * @param filter
	 * @return
	 */
	public List<BpmTask> getTaksOfCopyToByUserId(String userId,QueryFilter filter);
	/**
	 * 任务分发处理
	 * @param taskSplitCmd
	 * @return 返回分发后的任务任务
	 */
	public List<BpmTask> splitTask(TaskSplitCmd taskSplitCmd);
	/**
	 * 任务催办
	 * @param taskUrgeCmd
	 */
	public void urgeTask(TaskUrgeCmd taskUrgeCmd);
	/**
	 * 锁定任务为某人执行
	 * 当一任务有多个候选执行人时，可锁定该任务的执行人及所属人为某个用户
	 * @param taskCmd 
	 * @exception 
	 * @since  1.0.0
	 */
	public void lockTask(TaskFinishCmd taskCmd);
	/**
	 * 解锁任务
	 * 当一任务有多个候选执行人时，并且该用户锁定了执行人，可通过解锁该任务，
	 * 允许其他候选人锁定执行
	 * @param task 
	 * @return void
	 * @exception 
	 * @since  1.0.0
	 */
	public void unloackTask(TaskFinishCmd task);
	/**
	 * 对某一任务进行加签，生成多个加签任务
	 * @param cmd
	 * @return 
	 * @return List<BpmTask>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmTask> addSignTask(TaskFinishCmd cmd);
	/**
	 * 代理任务给单个用户或多个用户或用户组
	 * @param taskAgentCmd 
	 * @return void
	 * @exception 
	 * @since  1.0.0
	 */
	public void agentTask(TaskAgentCmd taskAgentCmd);
	/**
	 * 取消单个代理任务
	 * @param taskAgentCmd 
	 * @return void
	 * @exception 
	 * @since  1.0.0
	 */
	public void cancelAgentTask(TaskAgentCmd taskAgentCmd);
	/**
	 * 任务转办
	 * @param taskTurnCmd 
	 * @return void
	 * @exception 
	 * @since  1.0.0
	 */
	public void turnTask(TaskTurnCmd taskTurnCmd);
	/**
	 * 任务追回
	 * @param taskRecoverCmd 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void recoverTask(TaskRecoverCmd taskRecoverCmd);
	
	/**
	 * 获取当前任务的执行人
	 * @param taskId
	 * @return 
	 * @return List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> getTaskUsers(String taskId);
}
