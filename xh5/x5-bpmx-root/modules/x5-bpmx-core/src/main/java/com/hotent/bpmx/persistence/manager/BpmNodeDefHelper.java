package com.hotent.bpmx.persistence.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;

import com.hotent.base.core.util.AppUtil;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.core.defxml.DefXmlUtil;
import com.hotent.bpmx.core.defxml.entity.FlowElement;
import com.hotent.bpmx.core.defxml.entity.Process;
import com.hotent.bpmx.core.defxml.entity.SequenceFlow;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeDef;

public class BpmNodeDefHelper {
	private static Map<String, LinkedHashMap<String, DefaultBpmNodeDef>> bpmNodeDefsMap = new HashMap<String, LinkedHashMap<String, DefaultBpmNodeDef>>();
	private static Map<String, String> bpmnXmlMap = new HashMap<String, String>();

	public static LinkedHashMap<String,DefaultBpmNodeDef> getDefaultBpmNodeDefMap(String defId){
		init(defId);
		LinkedHashMap<String, DefaultBpmNodeDef> bpmNodeDefMap = bpmNodeDefsMap.get(defId);
		return bpmNodeDefMap;
	}
	
	public static LinkedHashMap<String,BpmNodeDef> getBpmNodeDefMap(String defId){
		return convertToBpmNodeDef(getDefaultBpmNodeDefMap(defId));
	}
	
	public static List<BpmNodeDef> getBpmNodeDefList(String defId){
		return convertToList(getBpmNodeDefMap(defId));
	}
	
	private static void init(String defId) {
		BpmDefinitionManager bpmDefinitionManager = (BpmDefinitionManager) AppUtil
				.getBean(BpmDefinitionManager.class);
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager
				.get(defId);
		try {
			if (defaultBpmDefinition != null
					&& StringUtils
							.isNotEmpty(defaultBpmDefinition.getBpmnXml())) {
				// 从缓存中取bpmnXml
				String bpmnXml = bpmnXmlMap
						.get(defaultBpmDefinition.getDefId());
				// 如果为空或和新的bpmnXml不一致，重新初始化
				if (StringUtils.isEmpty(bpmnXml)
						|| !defaultBpmDefinition.getBpmnXml().equals(bpmnXml)) {
					bpmnXml = defaultBpmDefinition.getBpmnXml();
					LinkedHashMap<String, DefaultBpmNodeDef> bpmNodeDefMap = buildBpmNodeDefMap(
							bpmnXml, defId);
					bpmnXmlMap.put(defId, bpmnXml);
					bpmNodeDefsMap.put(defId, bpmNodeDefMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static LinkedHashMap<String, DefaultBpmNodeDef> buildBpmNodeDefMap(
			String bpmnXml, String defId) throws JAXBException, IOException {
		Process process = DefXmlUtil.getProcess(bpmnXml).get(0);
		List<FlowElement> flowElements = DefXmlUtil.getFlowElements(process);
		LinkedHashMap<String, DefaultBpmNodeDef> bpmNodeDefs = new LinkedHashMap<String, DefaultBpmNodeDef>();
		for (FlowElement flowElement : flowElements) {
			bpmNodeDefs.put(flowElement.getId(), build(flowElement, defId));
		}
		putSourceAndTargets(bpmNodeDefs, process);
		return bpmNodeDefs;
	}

	private static DefaultBpmNodeDef build(FlowElement flowElement, String defId) {
		DefaultBpmNodeDef defaultBpmNodeDef = new DefaultBpmNodeDef();
		defaultBpmNodeDef.setNodeId(flowElement.getId());
		defaultBpmNodeDef.setName(flowElement.getName());
		defaultBpmNodeDef.setType(DefXmlUtil.getNodeDefType(flowElement).toString());
		defaultBpmNodeDef.setProcDefId(defId);
		return defaultBpmNodeDef;
	}

	private static void putSourceAndTargets(
			LinkedHashMap<String, DefaultBpmNodeDef> bpmNodeDefs,
			Process process) {
		List<SequenceFlow> sequenceFlows = DefXmlUtil.getSequenceFlows(process);
		for (SequenceFlow sequenceFlow : sequenceFlows) {
			FlowElement sourceDef = (FlowElement) sequenceFlow.getSourceRef();
			FlowElement targetDef = (FlowElement) sequenceFlow.getTargetRef();

			DefaultBpmNodeDef sourceNodeDef = bpmNodeDefs
					.get(sourceDef.getId());
			DefaultBpmNodeDef targetNodeDef = bpmNodeDefs
					.get(targetDef.getId());
			if (sourceNodeDef != null) {
				sourceNodeDef.getOutcomeNodes().add(targetNodeDef);
			}
			if (targetDef != null) {
				targetNodeDef.getIncomeNodes().add(sourceNodeDef);
			}
		}
	}
	
	private static LinkedHashMap<String,BpmNodeDef> convertToBpmNodeDef(LinkedHashMap<String,DefaultBpmNodeDef> defaultBpmNodeDefMap){
		LinkedHashMap<String,BpmNodeDef> bpmNodeDefs = new LinkedHashMap<String,BpmNodeDef>();
		for(Iterator<String> it = defaultBpmNodeDefMap.keySet().iterator();it.hasNext();){
			String key = it.next();
			bpmNodeDefs.put(key, (BpmNodeDef)defaultBpmNodeDefMap.get(key));
		}
		return bpmNodeDefs;
	}
	
	private static List<BpmNodeDef> convertToList(LinkedHashMap<String,BpmNodeDef> bpmNodeDefMap){
		List<BpmNodeDef> bpmNodeDefs = new ArrayList<BpmNodeDef>();
		for(Iterator<String> it = bpmNodeDefMap.keySet().iterator();it.hasNext();){
			String key = it.next();
			bpmNodeDefs.add(bpmNodeDefMap.get(key));
		}
		return bpmNodeDefs;
	}	
}
