package com.hotent.bpmx.activiti.ext.model;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;

/**
 * 任务代理类，提供给bpm_core进行调用。
 * <pre> 
 * 描述：这个类是DELEGATETASK的代理类。
 * 构建组：x5-bpmx-activiti
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-18-上午10:38:59
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmDelegateTaskImpl implements BpmDelegateTask {
	
	private TaskEntity task=null;
	
	public void setDelegateTask(DelegateTask delegateTask){
		this.task=task;
	}

	@Override
	public Map<String, Object> getVariables() {
		
		return task.getVariables();
	}

	@Override
	public Map<String, Object> getVariablesLocal() {
		return task.getVariablesLocal();
	}

	@Override
	public Object getVariable(String variableName) {
		
		return task.getVariable(variableName);
	}

	@Override
	public Object getVariableLocal(String variableName) {
		
		return task.getVariableLocal(variableName);
	}

	@Override
	public Set<String> getVariableNames() {
		
		return task.getVariableNames();
	}

	@Override
	public Set<String> getVariableNamesLocal() {
		
		return task.getVariableNamesLocal();
	}

	@Override
	public void setVariable(String variableName, Object value) {
		task.setVariable(variableName, value);
		
	}

	@Override
	public Object setVariableLocal(String variableName, Object value) {
		return task.setVariableLocal(variableName, value);
	}

	@Override
	public void setVariables(Map<String, ? extends Object> variables) {
		task.setVariables(variables);
		
	}

	@Override
	public void setVariablesLocal(Map<String, ? extends Object> variables) {
		task.setVariablesLocal(variables);
		
	}

	@Override
	public boolean hasVariables() {
		return task.hasVariables();
	}

	@Override
	public boolean hasVariablesLocal() {
		return task.hasVariablesLocal();
	}

	@Override
	public boolean hasVariable(String variableName) {
		return task.hasVariable(variableName);
	}

	@Override
	public boolean hasVariableLocal(String variableName) {
		return task.hasVariableLocal(variableName);
	}

	@Override
	public void createVariableLocal(String variableName, Object value) {
		 task.createVariableLocal(variableName, value);
		
	}

	@Override
	public void removeVariable(String variableName) {
		task.removeVariable(variableName);
		
	}

	@Override
	public void removeVariableLocal(String variableName) {
		task.removeVariableLocal(variableName);
		
	}

	@Override
	public void removeVariables(Collection<String> variableNames) {
		task.removeVariables(variableNames);
		
	}

	@Override
	public void removeVariablesLocal(Collection<String> variableNames) {
		task.removeVariablesLocal(variableNames);
	}

	@Override
	public void removeVariables() {
		task.removeVariables();
		
	}

	@Override
	public void removeVariablesLocal() {
		task.removeVariablesLocal();
		
	}
	

	

	@Override
	public String getId() {
		
		return task.getId();
	}

	@Override
	public String getName() {
		return task.getName();
		
	}

	@Override
	public void setName(String name) {
		task.setName(name);
	}

	@Override
	public String getDescription() {
		return task.getDescription();
	}

	@Override
	public void setDescription(String description) {
		task.setDescription(description);
		
	}

	@Override
	public int getPriority() {
		return task.getPriority();
	}

	@Override
	public void setPriority(int priority) {
		task.setPriority(priority);
	}

	@Override
	public String getProcessInstanceId() {
		return this.task.getProcessInstanceId();
	}

	@Override
	public String getExecutionId() {
		
		return task.getExecutionId();
	}

	@Override
	public String getProcessDefinitionId() {
		
		return task.getProcessDefinitionId();
	}

	@Override
	public Date getCreateTime() {
		return task.getCreateTime();
	}

	@Override
	public String getTaskDefinitionKey() {
		return task.getTaskDefinitionKey();
	}

	@Override
	public String getEventName() {
		return task.getEventName();
	}	
	
	//挂起状态。
	@Override
	public int getSuspensionState(){
		return task.getSuspensionState();
	}
	

	@Override
	public void addCandidateUser(String userId) {
		task.addCandidateUser(userId);
		
	}

	@Override
	public void addCandidateUsers(Collection<String> candidateUsers) {
		task.addCandidateUsers(candidateUsers);
	}

	@Override
	public void addCandidateGroup(String groupId) {
		task.addCandidateGroup(groupId);
	}

	@Override
	public void addCandidateGroups(Collection<String> candidateGroups) {
		task.addCandidateGroups(candidateGroups);
	}

	@Override
	public String getOwner() {
		return task.getOwner();
	}

	@Override
	public void setOwner(String owner) {
		task.setOwner(owner);
	}

	@Override
	public String getAssignee() {
		return task.getAssignee();
	}

	@Override
	public void setAssignee(String assignee) {
		task.setAssignee(assignee);
	}

	@Override
	public Date getDueDate() {
		return task.getDueDate();
	}

	@Override
	public void setDueDate(Date dueDate) {
		task.setDueDate(dueDate);
	}

	@Override
	public void addUserIdentityLink(String userId, String identityLinkType) {
		task.addUserIdentityLink(userId, identityLinkType);
	}

	@Override
	public void addGroupIdentityLink(String groupId, String identityLinkType) {
		task.addGroupIdentityLink(groupId, identityLinkType);
	}

	@Override
	public void deleteCandidateUser(String userId) {
		task.deleteCandidateUser(userId);
		
	}

	@Override
	public void deleteCandidateGroup(String groupId) {
		task.deleteCandidateGroup(groupId);
	}

	@Override
	public void deleteUserIdentityLink(String userId, String identityLinkType) {
		task.deleteUserIdentityLink(userId, identityLinkType);
		
	}

	@Override
	public void deleteGroupIdentityLink(String groupId, String identityLinkType) {
		task.deleteGroupIdentityLink(groupId, identityLinkType);
		
	}



}
