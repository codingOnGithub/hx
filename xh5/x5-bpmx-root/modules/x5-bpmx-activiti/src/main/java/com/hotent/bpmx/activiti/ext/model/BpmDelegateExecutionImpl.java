package com.hotent.bpmx.activiti.ext.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateExecution;

import com.hotent.bpmx.api.model.delegate.BpmDelegateExecution;

/**
 * 流程执行实例的代理实例，代理DelegateExecution这个对象。
 * <pre> 
 * 描述：这个提供给bpm_core调用。
 * 构建组：x5-bpmx-activiti
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-18-上午10:41:16
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmDelegateExecutionImpl implements BpmDelegateExecution {
	
	private DelegateExecution delegateExecution=null;	
	
	public void setDelegateExecution(DelegateExecution delegateExecution){
		this.delegateExecution=delegateExecution;
	}

	@Override
	public Map<String, Object> getVariables() {
		return delegateExecution.getVariables();
	}

	@Override
	public Map<String, Object> getVariablesLocal() {
		return delegateExecution.getVariablesLocal();
	}

	@Override
	public Object getVariable(String variableName) {
		return delegateExecution.getVariable(variableName);
	}

	@Override
	public Object getVariableLocal(String variableName) {
		return delegateExecution.getVariableLocal(variableName);
	}

	@Override
	public Set<String> getVariableNames() {
		return delegateExecution.getVariableNames();
	}

	@Override
	public Set<String> getVariableNamesLocal() {
		return delegateExecution.getVariableNamesLocal();
	}

	@Override
	public void setVariable(String variableName, Object value) {
		delegateExecution.setVariable(variableName, value);
	}

	@Override
	public Object setVariableLocal(String variableName, Object value) {
		return delegateExecution.setVariableLocal(variableName, value);
	}

	@Override
	public void setVariables(Map<String, ? extends Object> variables) {
		delegateExecution.setVariables(variables);

	}

	@Override
	public void setVariablesLocal(Map<String, ? extends Object> variables) {
		delegateExecution.setVariablesLocal(variables);

	}

	@Override
	public boolean hasVariables() {
		return delegateExecution.hasVariables();
	}

	@Override
	public boolean hasVariablesLocal() {
		return delegateExecution.hasVariablesLocal();
	}

	@Override
	public boolean hasVariable(String variableName) {
		return delegateExecution.hasVariable(variableName);
	}

	@Override
	public boolean hasVariableLocal(String variableName) {
		return delegateExecution.hasVariableLocal(variableName);
	}

	@Override
	public void createVariableLocal(String variableName, Object value) {
		delegateExecution.createVariableLocal(variableName, value) ;

	}

	@Override
	public void removeVariable(String variableName) {
		delegateExecution.removeVariable(variableName);

	}

	@Override
	public void removeVariableLocal(String variableName) {
		delegateExecution.removeVariableLocal(variableName);

	}

	@Override
	public void removeVariables(Collection<String> variableNames) {
		delegateExecution.removeVariables(variableNames);

	}

	@Override
	public void removeVariablesLocal(Collection<String> variableNames) {
		delegateExecution.removeVariablesLocal(variableNames);

	}

	@Override
	public void removeVariables() {
		delegateExecution.removeVariables();

	}

	@Override
	public void removeVariablesLocal() {
		delegateExecution.removeVariablesLocal();

	}

	@Override
	public String getId() {
		return delegateExecution.getId();
	}

	@Override
	public String getProcessInstanceId() {
		return delegateExecution.getProcessInstanceId();
	}

	@Override
	public String getEventName() {
		return delegateExecution.getEventName();
	}

	@Override
	public String getProcessBusinessKey() {
		return delegateExecution.getProcessBusinessKey();
	}

	@Override
	public String getProcessDefinitionId() {
		return delegateExecution.getProcessDefinitionId();
	}

	@Override
	public String getParentId() {
		
		return delegateExecution.getParentId();
	}

	@Override
	public String getCurrentActivityId() {
		
		return delegateExecution.getCurrentActivityId();
	}

	@Override
	public String getCurrentActivityName() {
		
		return delegateExecution.getCurrentActivityId();
	}

//	@Override
//	public String updateProcessBusinessKey(String bzKey) {
//		
//		return delegateExecution.updateProcessBusinessKey(bzKey);
//	}

}
