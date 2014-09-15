package demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.util.Dom4jUtil;

public class NodeOutDemo {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[]args){
		Document document=Dom4jUtil.load("D:/dev/bpmx3/bpm/src/test/java/demo/demo.xml");
		Element el=document.getRootElement();
		
		Map<String, FlowNode> map=new HashMap<String, FlowNode>();
		
		Element processEl=(Element)el.selectSingleNode("./process");

		Iterator<Element> its=processEl.elementIterator();
		
		while(its.hasNext()){
			Element nodeEl=its.next();
			
			String nodeType=nodeEl.getName(); 
			
			if("startEvent".equals(nodeType) || "userTask".equals(nodeType) 
					|| "parallelGateway".equals(nodeType) || "inclusiveGateway".equals(nodeType) ||
					"exclusiveGateway".equals(nodeType) || "endEvent".equals(nodeType) || "serviceTask".equals(nodeType)||"subProcess".equals(nodeType)){
				String nodeName=nodeEl.attributeValue("name");
				String nodeId=nodeEl.attributeValue("id");
				
				System.out.println("tag:" + nodeType + " name:" + nodeId + "id:" + nodeId);
			
				FlowNode flowNode=new FlowNode(nodeId, nodeName, nodeType);
				
				map.put(nodeId, flowNode);
				
			}
		}
		System.out.println("xxx");
		List<Node> seqFlowList=document.selectNodes("/definitions/process//sequenceFlow");
		for(int i=0;i<seqFlowList.size();i++){
			Element  seqFlow=(Element)seqFlowList.get(i);
			String sourceRef=seqFlow.attributeValue("sourceRef");
			String targetRef=seqFlow.attributeValue("targetRef");
			String id=seqFlow.attributeValue("id");
			System.out.println("id:" + id + " sourceRef:" + sourceRef + " targetRef:" + targetRef);
			
			FlowNode sourceFlowNode=map.get(sourceRef);
			FlowNode targetFlowNode=map.get(targetRef);
			
			if(sourceFlowNode!=null && targetFlowNode!=null){
			sourceFlowNode.getNextFlowNodes().add(targetFlowNode);
			targetFlowNode.getPreFlowNodes().add(sourceFlowNode);
			}
		}
		
		Iterator<FlowNode> flowNodeIt=map.values().iterator();
		
		while(flowNodeIt.hasNext()){
			FlowNode fnode=flowNodeIt.next();
			System.out.println("nodeId:" + fnode.getNodeId() + "name:" + fnode.getNodeName());
			
			for(FlowNode preNode:fnode.getPreFlowNodes()){
				System.out.println("pre:nodeId:" + preNode.getNodeId() + "name:" + preNode.getNodeName());
			}
			
			for(FlowNode preNode:fnode.getNextFlowNodes()){
				System.out.println("next:nodeId:" + preNode.getNodeId() + "name:" + preNode.getNodeName());
			}
			
		}
	}
}
