package com.hotent.bpmx.persistence.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.base.core.util.AppUtil;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.core.defxml.DefXmlUtil;
import com.hotent.bpmx.core.defxml.entity.FlowElement;
import com.hotent.bpmx.persistence.constants.NodeDefType;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeDef;

public class BpmNodeDefCache {
	private static Map<String, Map<String, BpmNodeDef>> bpmNodeDefsMap = new HashMap<String, Map<String, BpmNodeDef>>();		
	
	/**
	 * 根据流程定义ID获得该流程定义的节点Map
	 * @param defId
	 * @return 
	 * Map<String,BpmNodeDef>
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String, BpmNodeDef> getBpmNodeDefMap(String defId){
		init(defId);
		//TODO
		return null;
	}
	
	/**
	 * 根据流程定义ID和节点ID或者节点定义
	 * @param defId
	 * @param nodeId
	 * @return 
	 * BpmNodeDef
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmNodeDef getBpmNodeDef(String defId,String nodeId){
		init(defId);
		//TODO
		return null;
	}
	
	/**
	 * 根据流程定义ID获得该流程起始事件
	 * @param defId
	 * @return 
	 * BpmNodeDef
	 * @exception 
	 * @since  1.0.0
	 */
	public static BpmNodeDef getStartEvent(String defId){
		init(defId);
		//TODO
		return null;
	}
	
	/**
	 * 根据流程定义ID获得该流程结束事件
	 * @param defId
	 * @return 
	 * List<BpmNodeDef>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<BpmNodeDef> getEndEvents(String defId){
		init(defId);
		//TODO
		return null;
	}	
	
	/**
	 * 根据流程定义ID获得该流程起始节点集合
	 * @param defId
	 * @return 
	 * List<BpmNodeDef>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<BpmNodeDef> getStartNodes(String defId){
		init(defId);
		//TODO
		return null;
	}
	
	/**
	 * 判断是否起始事件节点
	 * @param defId
	 * @param nodeId
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isStartEvent(String defId,String nodeId){
		init(defId);
		//TODO
		return false;
	}
	
	/**
	 * 判断是否结束事件节点
	 * @param defId
	 * @param nodeId
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isEndEvent(String defId,String nodeId){
		init(defId);
		//TODO
		return false;
	}
	
	/**
	 * 判断是否会签节点
	 * @param defId
	 * @param nodeId
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isSignTaskNode(String defId,String nodeId){
		return false;
	}
	
	/**
	 * 验证传入的节点是否是该类型（即第三个枚举参数）
	 * @param defId
	 * @param nodeId
	 * @param nodeDefType
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean validNodeDefType(String defId,String nodeId,NodeDefType nodeDefType){
		return false;
	}
	
	/**
	 * 判断该流程里面是否包含外部子流程节点
	 * @param defId
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isContainCallActivity(String defId){
		return false;
	}
	
	/**
	 * 清除该流程的缓存Map
	 * @param defId 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public static void clear(String defId){
		//TODO
	}
	
	private synchronized static void init(String defId){
		if(bpmNodeDefsMap.get(defId)==null){
			BpmDefinitionManager bpmDefinitionManager = (BpmDefinitionManager)AppUtil.getBean(BpmDefinitionManager.class);
			DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId); 
			try {
				//获得该流程所有的节点
				List<FlowElement> flowElements = DefXmlUtil.getFlowElements(defaultBpmDefinition.getBpmnXml());
				
				//将其放到Map中
				List<BpmNodeDef> bpmNodeDefList = new ArrayList<BpmNodeDef>();
				Map<String, BpmNodeDef> bpmNodeDefMap = new HashMap<String, BpmNodeDef>();				
				
				for(FlowElement flowElement:flowElements){					
					BpmNodeDef bpmNodeDef = build(flowElement);
					bpmNodeDefMap.put(flowElement.getId(), bpmNodeDef);
					bpmNodeDefList.add(bpmNodeDef);
				}
				
				//填充关联变量
				for(BpmNodeDef bpmNodeDef:bpmNodeDefList){
					fillRelates(bpmNodeDef, bpmNodeDefMap);	
				}				
				
				bpmNodeDefsMap.put(defId, bpmNodeDefMap);				
			} catch (Exception e) {
				// TODO: handle exception
			}								
		}
	}
	private static BpmNodeDef build(FlowElement flowElement){
		DefaultBpmNodeDef defaultBpmNodeDef = new DefaultBpmNodeDef();
		defaultBpmNodeDef.setNodeId(flowElement.getId());
		defaultBpmNodeDef.setName(flowElement.getName());
		defaultBpmNodeDef.setType(DefXmlUtil.getNodeDefType(flowElement).toString());		
		return defaultBpmNodeDef;
	}	
	private static void fillRelates(BpmNodeDef bpmNodeDef,Map<String, BpmNodeDef> bpmNodeDefMap){
		//TODO 填充该节点的输入节点集合、输出节点集合、子流程节点集合、子流程节点Map、属性集合
		
	}
}
