package com.hotent.bpmx.api.def;

import java.util.List;

import com.hotent.bpmx.api.model.process.def.BpmProcessDef;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;

/**
 * 
 * <pre> 
 * 描述：流程定义描述访问器
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-11-下午8:43:14
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmDefinitionAccessor {
	/**
	 * 通过流程定义ID返回所有的节点定义
	 * @param processDefinitionId
	 * @return 
	 * List<BpmNodeDef>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmNodeDef> getNodeDefs(String processDefinitionId);
	/**
	 * 通过流程定义及节点定义ID获取流程节点定义
	 * @param processDefinitionId
	 * @param nodeId
	 * @return 
	 * BpmNodeDef
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmNodeDef getBpmNodeDef(String processDefinitionId,String nodeId);
	/**
	 * 通过流程定义ID获取流程定义的实体描述
	 * @param processDefId
	 * @return 
	 * BpmProcessDef
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmProcessDef getBpmProcessDef(String processDefId);
}
