package com.hotent.bpmx.persistence.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.api.plugin.process.def.BpmExecutionPluginDef;
import com.hotent.bpmx.api.plugin.process.def.BpmTaskPluginDef;

public class DefaultBpmNodeDef implements BpmNodeDef{
	private String nodeId;
	private String name;
	private String type;
	private String procDefId;
	private List<BpmNodeDef> incomeNodes = new ArrayList<BpmNodeDef>();
	private List<BpmNodeDef> outcomeNodes = new ArrayList<BpmNodeDef>();
	private List<BpmNodeDef> subNodes = new ArrayList<BpmNodeDef>();
	private Map<String,BpmNodeDef> subNodeMap = new HashMap<String, BpmNodeDef>();
	private List<String> attributeList = new ArrayList<String>();
	private List<BpmExecutionPluginDef> bpmExecutionPluginDefs=new ArrayList<BpmExecutionPluginDef>();
	private List<BpmTaskPluginDef> bpmTaskPluginDefs = new ArrayList<BpmTaskPluginDef>();
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProcDefId() {
		return procDefId;
	}
	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}
	public List<BpmNodeDef> getIncomeNodes() {
		return incomeNodes;
	}
	public void setIncomeNodes(List<BpmNodeDef> incomeNodes) {
		this.incomeNodes = incomeNodes;
	}
	public List<BpmNodeDef> getOutcomeNodes() {
		return outcomeNodes;
	}
	public void setOutcomeNodes(List<BpmNodeDef> outcomeNodes) {
		this.outcomeNodes = outcomeNodes;
	}

	public List<BpmNodeDef> getSubNodes() {
		return subNodes;
	}
	public void setSubNodes(List<BpmNodeDef> subNodes) {
		this.subNodes = subNodes;
	}
	public Map<String, BpmNodeDef> getSubNodeMap() {
		return subNodeMap;
	}
	public void setSubNodeMap(Map<String, BpmNodeDef> subNodeMap) {
		this.subNodeMap = subNodeMap;
	}
	public List<String> getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(List<String> attributeList) {
		this.attributeList = attributeList;
	}
	/**
	 * bpmExecutionPluginDefs
	 * @return  the bpmExecutionPluginDefs
	 * @since   1.0.0
	 */
	
	public List<BpmExecutionPluginDef> getBpmExecutionPluginDefs() {
		return bpmExecutionPluginDefs;
	}
	/**
	 * @param bpmExecutionPluginDefs the bpmExecutionPluginDefs to set
	 */
	public void setBpmExecutionPluginDefs(
			List<BpmExecutionPluginDef> bpmExecutionPluginDefs) {
		this.bpmExecutionPluginDefs = bpmExecutionPluginDefs;
	}
	/**
	 * bpmTaskPluginDefs
	 * @return  the bpmTaskPluginDefs
	 * @since   1.0.0
	 */
	
	public List<BpmTaskPluginDef> getBpmTaskPluginDefs() {
		return bpmTaskPluginDefs;
	}
	/**
	 * @param bpmTaskPluginDefs the bpmTaskPluginDefs to set
	 */
	public void setBpmTaskPluginDefs(List<BpmTaskPluginDef> bpmTaskPluginDefs) {
		this.bpmTaskPluginDefs = bpmTaskPluginDefs;
	}
	

}
