package com.hotent.bpmx.core.engine.inst;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.bpmx.api.cmd.ProcessInstCmd;
import com.hotent.bpmx.api.model.process.inst.BpmProcessInstance;
import com.hotent.bpmx.api.model.process.task.BpmTaskOpinion;
import com.hotent.bpmx.api.service.BpmInstService;
import com.hotent.bpmx.core.context.ContextUtil;
import com.hotent.bpmx.natapi.inst.NatProInstanceService;
import com.hotent.bpmx.persistence.manager.BpmDefinitionManager;
import com.hotent.bpmx.persistence.manager.BpmProcessInstanceManager;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmProcessInstance;
import com.hotent.org.api.model.User;

/**
 * 
 * <pre> 
 * 描述：流程实例服务类
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-23-上午7:39:10
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@Service
public class DefaultProcessInstanceService implements BpmInstService{
	@Resource
	BpmProcessInstanceManager bpmProcessInstanceManager;
	@Resource
	BpmDefinitionManager bpmDefinitionManager;
	@Resource
	NatProInstanceService natProInstanceService;
	
	@Override
	public BpmProcessInstance startProcessInst(ProcessInstCmd processInstCmd) {
		User currentUser=ContextUtil.getCurrentUser();
		
		DefaultBpmProcessInstance instance=new DefaultBpmProcessInstance();
		instance.setBizKey(processInstCmd.getBusinessKey());
		DefaultBpmDefinition bpmDefinition=null;
		
		if(StringUtils.isNotEmpty(processInstCmd.getProcDefId())){
			bpmDefinition=bpmDefinitionManager.get(processInstCmd.getProcDefId());
		}else if(StringUtils.isNotEmpty(processInstCmd.getFlowKey())){
			bpmDefinition=bpmDefinitionManager.getMainByDefKey(processInstCmd.getFlowKey());
		}else{
			bpmDefinition=bpmDefinitionManager.getByBpmnDefId(processInstCmd.getBpmnDefId());
		}
		
		instance.setProcDefId(bpmDefinition.getDefId());
		instance.setBpmnDefId(bpmDefinition.getBpmnDefId());
		instance.setProcDefName(bpmDefinition.getName());
		//设置实例标题
		String subject=bpmProcessInstanceManager.getSubject(bpmDefinition, processInstCmd);
		instance.setSubject(subject);
		//启动流程实例
		String processInstId=natProInstanceService.startProcessInstance(bpmDefinition.getBpmnDefId(), instance.getBizKey(),processInstCmd.getVariables());
		instance.setBpmnInstId(processInstId);
		instance.setBizData(processInstCmd.getBusinessData());
		//设置创建用户ID
		instance.setCreateBy(currentUser.getUserId());
		instance.setStatus(BpmProcessInstance.STATUS_RUNNING);
		bpmProcessInstanceManager.create(instance);
		return instance;
	}

	@Override
	public BpmProcessInstance saveDraft(ProcessInstCmd processInstCmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BpmProcessInstance startDraftProcessInstance(
			BpmProcessInstance processInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getProcessInstancesByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getProcessInstancesByUserId(String userId,
			Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getProcessInstancesByUserId(String userId,
			QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getAttendProcessInstancesByUserId(
			String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getAttendProcessInstancesByUserId(
			String userId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getAttendProcessInstancesByUserId(
			String userId, QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTaskOpinion> getTaskOpinions(String processInstId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getDraftsByUserId(String userId,
			QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmProcessInstance> getAll(QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeProcessInstance(String processInstId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean suspendProcessInstance(String processInstId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean recoverProcessInstance(String processInstId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean endProcessInstance(String processInstId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BpmProcessInstance getProcessInstance(String processInstId) {
		// TODO Auto-generated method stub
		return null;
	}

}
