package com.hotent.bpmx.api.model.delegate;

import java.util.Collection;
import java.util.Date;
/**
 * <pre> 
 * 描述：流程代理任务
 * 构建组：x5-bpmx-native-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-17-下午6:36:07
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmDelegateTask extends BpmVariable {

	String getId();

	String getName();

	void setName(String name);

	String getDescription();

	void setDescription(String description);

	int getPriority();

	void setPriority(int priority);

	String getProcessInstanceId();

	String getExecutionId();

	String getProcessDefinitionId();

	Date getCreateTime();

	String getTaskDefinitionKey();

	String getEventName();
	
	//挂起状态。
	int getSuspensionState();

	void addCandidateUser(String userId);

	void addCandidateUsers(Collection<String> candidateUsers);

	void addCandidateGroup(String groupId);

	void addCandidateGroups(Collection<String> candidateGroups);

	String getOwner();

	void setOwner(String owner);

	String getAssignee();

	void setAssignee(String assignee);

	Date getDueDate();

	void setDueDate(Date dueDate);

	void addUserIdentityLink(String userId, String identityLinkType);

	void addGroupIdentityLink(String groupId, String identityLinkType);

	void deleteCandidateUser(String userId);

	void deleteCandidateGroup(String groupId);

	void deleteUserIdentityLink(String userId, String identityLinkType);

	void deleteGroupIdentityLink(String groupId, String identityLinkType);

}
