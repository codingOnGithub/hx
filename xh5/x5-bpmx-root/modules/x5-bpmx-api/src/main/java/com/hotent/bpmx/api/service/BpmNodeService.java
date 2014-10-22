package com.hotent.bpmx.api.service;

import java.util.List;
import java.util.Map;
import com.hotent.org.api.model.User;

public interface BpmNodeService {
	/**
	 * 取得某个流程定义的某个节点的具体执行人员(流程定义节点人员预览)
	 * @param defId
	 * @param nodeId
	 * @param variables
	 * @return 
	 * List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> getNodeUsersDefIdNodeId(String defId,String nodeId,Map<String,Object> variables);
	/**
	 * 取得某个流程实例的某个节点的具体执行人员
	 * @param instId
	 * @param nodeId
	 * @param variables
	 * @return 
	 * List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<User> getNodeUsersByInstIdNodeId(String instId,String nodeId,Map<String,Object> variables);
	
	
}
