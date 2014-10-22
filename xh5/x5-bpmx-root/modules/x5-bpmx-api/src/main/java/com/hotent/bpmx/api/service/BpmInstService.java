package com.hotent.bpmx.api.service;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.bpmx.api.cmd.ProcessInstCmd;
import com.hotent.bpmx.api.model.process.inst.BpmProcessInstance;
import com.hotent.bpmx.api.model.process.task.BpmTaskOpinion;

/**
 * 
 * <pre> 
 * 描述：流程实例服务接口定义
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-15-下午9:32:07
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmInstService {
	/**
	 * 启动流程实例
	 * @param processInstCmd
	 * @return
	 */
	public BpmProcessInstance startProcessInst(ProcessInstCmd processInstCmd);
	
	/**
	 * 保存流程实例为草稿
	 * @param processInstCmd
	 * @return 
	 * BpmProcessInstance
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmProcessInstance saveDraft(ProcessInstCmd processInstCmd);
	/**
	 * 启动草稿的流程实例
	 * @param processInstance
	 * @return 
	 * BpmProcessInstance
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmProcessInstance startDraftProcessInstance(BpmProcessInstance processInstance);
	
	/**
	 * 取得某用户发起的流程实例列表
	 * @param userId
	 * @return
	 */
	public List<BpmProcessInstance> getProcessInstancesByUserId(String userId);
	/**
	 * 取得某用户发起的流程实例列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<BpmProcessInstance> getProcessInstancesByUserId(String userId,Page page);
	/**
	 * 取得某用户发起的流程实例列表
	 * @param userId
	 * @param queryFilter
	 * @return List<BpmProcessInstance>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmProcessInstance> getProcessInstancesByUserId(String userId,QueryFilter queryFilter);
	
	/**
	 * 取得我参与的流程实例列表
	 * @param userId
	 * @return
	 */
	public List<BpmProcessInstance> getAttendProcessInstancesByUserId(String userId);
	/**
	 * 取得我参与的流程实例列表
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<BpmProcessInstance> getAttendProcessInstancesByUserId(String userId,Page page);
	/**
	 * 取得我参与的流程实例列表
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public List<BpmProcessInstance> getAttendProcessInstancesByUserId(String userId,QueryFilter queryFilter);
	
	/**
	 * 取得某个流程实例的所有的审批意见列表
	 * @param processInstId
	 * @return
	 */
	public List<BpmTaskOpinion> getTaskOpinions(String processInstId);
	/**
	 * 获取某个用户的草稿流程实例列表
	 * @param userId
	 * @param queryFilter
	 * @return 
	 * List<BpmProcessInstance>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmProcessInstance> getDraftsByUserId(String userId,QueryFilter queryFilter);
	
	/**
	 * 取得所有的流程实例
	 * @param queryFilter
	 * @return
	 */
	public List<BpmProcessInstance> getAll(QueryFilter queryFilter);
	/**
	 * 删除流程实例
	 * @param processInstId
	 * @return
	 */
	public boolean removeProcessInstance(String processInstId);
	/**
	 * 挂起流程实例
	 * @param processInstId
	 * @return
	 */
	public boolean suspendProcessInstance(String processInstId);
	/**
	 * 恢复挂起的流程实例
	 * @param processInstId
	 * @return
	 */
	public boolean recoverProcessInstance(String processInstId);
	/**
	 * 结束流程实例
	 * @param processInstId
	 * @return
	 */
	public boolean endProcessInstance(String processInstId);
	/**
	 * 通过Id获得流程实例明细
	 * @param processInstId
	 * @return
	 */
	public BpmProcessInstance getProcessInstance(String processInstId);
	
	
}
