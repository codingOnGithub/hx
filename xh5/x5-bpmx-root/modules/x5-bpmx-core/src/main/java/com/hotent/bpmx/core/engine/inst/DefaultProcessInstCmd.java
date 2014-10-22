package com.hotent.bpmx.core.engine.inst;

import java.util.HashMap;
import java.util.Map;

import com.hotent.bpmx.api.cmd.ProcessInstCmd;
/**
 * 
 * <pre> 
 * 描述：流程实例启动命令参数对象
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-10-上午11:08:48
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DefaultProcessInstCmd implements ProcessInstCmd {
	/**
	 * 流程BPMN定义ID,bpmnDefId\ProcDefId\flowKey\三值只需要设置一值即可
	 */
	private String bpmnDefId;
	/**
	 * 流程定义ID，bpmnDefId\ProcDefId\flowKey\三值只需要设置一值即可
	 */
	private String procDefId;
	/**
	 * 流程定义Key，bpmnDefId\ProcDefId\flowKey\三值只需要设置一值即可
	 */
	private String flowKey;
	/**
	 * 流程变量值
	 */
	private Map<String,Object> variables=new HashMap<String, Object>();
	/**
	 * 流程实例从标题
	 */
	private String subject;
	/**
	 * 业务主键
	 */
	private String businessKey;
	/**
	 * 业务对象JSON值
	 */
	private String businessData;
	/**
	 * 启动用户ID
	 */
	private String startUserId;
	
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getBpmnDefId()
	 */
	public String getBpmnDefId() {
		return bpmnDefId;
	}
	
	public void setBpmnDefId(String bpmnDefId) {
		this.bpmnDefId = bpmnDefId;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getProcDefId()
	 */
	public String getProcDefId() {
		return procDefId;
	}
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getFlowKey()
	 */
	public String getFlowKey() {
		return flowKey;
	}
	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getVariables()
	 */
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getSubject()
	 */
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getBusinessKey()
	 */
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hotent.bpmx.api.cmd.ProcessInstCmd#getBusinessData()
	 */
	public String getBusinessData() {
		return businessData;
	}
	public void setBusinessData(String businessData) {
		this.businessData = businessData;
	}


	@Override
	public String getStartUserId() {
		return this.startUserId;
	}

	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}

	
}
