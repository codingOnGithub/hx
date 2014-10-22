/**
 * 广州宏天软件有限公司版权所有
 */
package com.hotent.bpmx.core.defxml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.activiti.engine.impl.bpmn.behavior.MultiInstanceActivityBehavior;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import com.hotent.base.db.mapping.MappingUtil;
import com.hotent.bpmx.core.defxml.entity.CallActivity;
import com.hotent.bpmx.core.defxml.entity.Definitions;
import com.hotent.bpmx.core.defxml.entity.EndEvent;
import com.hotent.bpmx.core.defxml.entity.ExclusiveGateway;
import com.hotent.bpmx.core.defxml.entity.FlowElement;
import com.hotent.bpmx.core.defxml.entity.InclusiveGateway;
import com.hotent.bpmx.core.defxml.entity.ManualTask;
import com.hotent.bpmx.core.defxml.entity.ObjectFactory;
import com.hotent.bpmx.core.defxml.entity.ParallelGateway;
import com.hotent.bpmx.core.defxml.entity.Process;
import com.hotent.bpmx.core.defxml.entity.ReceiveTask;
import com.hotent.bpmx.core.defxml.entity.RootElement;
import com.hotent.bpmx.core.defxml.entity.ScriptTask;
import com.hotent.bpmx.core.defxml.entity.SendTask;
import com.hotent.bpmx.core.defxml.entity.SequenceFlow;
import com.hotent.bpmx.core.defxml.entity.ServiceTask;
import com.hotent.bpmx.core.defxml.entity.StartEvent;
import com.hotent.bpmx.core.defxml.entity.SubProcess;
import com.hotent.bpmx.core.defxml.entity.Task;
import com.hotent.bpmx.core.defxml.entity.UserTask;
import com.hotent.bpmx.persistence.constants.NodeDefType;

/**
 * @功能描述：根据各类的输入参数获得TDefinitions对象
 * @开发公司：广州宏天软件有限公司
 * @作者：Winston Yan
 * @邮箱：yancm@jee-soft.cn
 * @创建时间：2013-12-6 上午11:21:24
 */
public class DefXmlUtil {
	private final static Log logger = LogFactory.getLog(MappingUtil.class);

	/**
	 * 根据配置文件的XML内容生成TDefinitions实体
	 * @param defXml
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 */
	public static Definitions getDefinitionsByXml(String bpmnXml) throws JAXBException, IOException {
		InputStream is = new ByteArrayInputStream(bpmnXml.getBytes("UTF-8"));
		return getDefinitions(is);
	}	
	
	/**
	 * 根据输入流，创建流程对象
	 * @param is
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 */
	public static Definitions getDefinitions(InputStream is) throws JAXBException, IOException{
		@SuppressWarnings("unchecked")
		JAXBElement<Definitions> jAXBElement = (JAXBElement<Definitions>) unmarshall(is,
				com.hotent.bpmx.core.defxml.entity.ObjectFactory.class,
				com.hotent.bpmx.core.defxml.entity.activiti.ObjectFactory.class,
				com.hotent.bpmx.core.defxml.entity.omgdc.ObjectFactory.class,
				com.hotent.bpmx.core.defxml.entity.omgdi.ObjectFactory.class,
				com.hotent.bpmx.core.defxml.entity.bpmndi.ObjectFactory.class,
				com.hotent.bpmx.core.defxml.entity.hotent.ObjectFactory.class
				);
		Definitions definitions = jAXBElement.getValue();
		return definitions;
	}

	/**
	 * 根据bpmn20流程定义xml文件，取得定义的流程
	 * @param bpmnxml 流程定义
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 */
	public static List<Process> getProcess(String bpmnxml) throws JAXBException, IOException{
		InputStream is = new ByteArrayInputStream(bpmnxml.getBytes("UTF-8"));
		return getProcess(is);
	}
			
	/**
	 * 根据输入流，取得定义的流程
	 * @param is
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 */
	public static List<Process> getProcess(InputStream is) throws JAXBException, IOException{
		
		List<Process> processes = new ArrayList<Process>();
		Definitions definitions = getDefinitions(is);
		List<JAXBElement<? extends RootElement>> bPMNElements =definitions.getRootElement();
		for(JAXBElement<? extends RootElement> jAXBe:bPMNElements){
			RootElement element =  jAXBe.getValue();
			if(element instanceof Process){
				processes.add((Process)element);
			}
		}
		return processes;
	}	
	
	public static List<FlowElement>  getFlowElements(String bpmnXml) throws JAXBException, IOException{
		Process process = DefXmlUtil.getProcess(bpmnXml).get(0);
		return getFlowElements(process);
	}
	
	public static List<FlowElement>  getFlowElements(Process process){
		List<FlowElement> flowElements = new ArrayList<FlowElement>();
		List<JAXBElement<? extends FlowElement>> jaxbElementFlowElements =  process.getFlowElement();
		for(JAXBElement<? extends FlowElement> jAXBElement:jaxbElementFlowElements){
			FlowElement flowElement = jAXBElement.getValue();
			flowElements.add(flowElement);
			
			if(flowElement instanceof SubProcess){
				flowElements.addAll(getFlowElements((SubProcess)flowElement));
			}			
		}
		return flowElements;
	}
	
	private static List<FlowElement>  getFlowElements(SubProcess subProcess){
		List<FlowElement> flowElements = new ArrayList<FlowElement>();
		List<JAXBElement<? extends FlowElement>> jaxbElementFlowElements =  subProcess.getFlowElement();
		for(JAXBElement<? extends FlowElement> jAXBElement:jaxbElementFlowElements){
			FlowElement flowElement = jAXBElement.getValue();
			flowElements.add(flowElement);
			
			if(flowElement instanceof SubProcess){
				flowElements.addAll(getFlowElements((SubProcess)flowElement));
			}			
		}
		return flowElements;
	}	
		
	/**
	 * 根据xml获得任务集合
	 * @param xml
	 * @return 
	 * List<Task>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<Task> getTasks(String bpmnXml){
		try{
			Process process = DefXmlUtil.getProcess(bpmnXml).get(0);
			return getTasks(process);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}		
	}
	
	/**
	 * 根据xml中的流程获得任务列表（任务包括用户任务、服务任务、脚本任务、发送信息任务、人工任务，以后可以继续增加）
	 * @param process
	 * @return 
	 * List<Task>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<Task> getTasks(Process process){
		List<Task> tasksElements = new ArrayList<Task>();
		List<JAXBElement<? extends FlowElement>> jaxbElements = process.getFlowElement();
		for(JAXBElement<? extends FlowElement> jaxbElement:jaxbElements){
			FlowElement flowElement = jaxbElement.getValue();
			if(flowElement instanceof UserTask){				
				tasksElements.add((Task)flowElement);
			}else if(flowElement instanceof ServiceTask){
				tasksElements.add((Task)flowElement);
			}else if(flowElement instanceof ScriptTask){
				tasksElements.add((Task)flowElement);				
			}else if(flowElement instanceof SendTask){
				tasksElements.add((Task)flowElement);
			}else if(flowElement instanceof ManualTask){
				tasksElements.add((Task)flowElement);
			}else if(flowElement instanceof ReceiveTask){
				tasksElements.add((Task)flowElement);
			}
		}
		return tasksElements;
	}
	
	/**
	 * 获得所有SequenceFlow节点
	 * @param bpmnXml
	 * @return 
	 * List<SequenceFlow>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<SequenceFlow> getSequenceFlows(String bpmnXml){
		try{
			Process process = DefXmlUtil.getProcess(bpmnXml).get(0);
			return getSequenceFlows(process);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}		
	}
	/**
	 * 获得所有SequenceFlow节点
	 * @param process
	 * @return 
	 * List<SequenceFlow>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<SequenceFlow> getSequenceFlows(Process process){
		List<SequenceFlow> sequenceFlows = new ArrayList<SequenceFlow>();
		List<JAXBElement<? extends FlowElement>> jaxbElements = process.getFlowElement();
		for(JAXBElement<? extends FlowElement> jaxbElement:jaxbElements){
			FlowElement flowElement = jaxbElement.getValue();
			if(flowElement instanceof SequenceFlow){
				sequenceFlows.add((SequenceFlow)flowElement);
			}
		}
		return sequenceFlows;
	}	
	
	/**
	 * 判断流程设计是否正确
	 * @param bpmnXml
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isProcessCorrect(String bpmnXml) throws JAXBException, IOException{
		List<String> errorCodes = validProcess(bpmnXml);
		if(errorCodes.size()==0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断流程设计是否正确，返回错误编码
	 * @param bpmnXml
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 * List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public static List<String> validProcess(String bpmnXml) throws JAXBException, IOException{
		List<FlowElement> flowElements = getFlowElements(bpmnXml); 
		Map<String, FlowElement> map = new HashMap<String,FlowElement>();
		for(FlowElement flowElement:flowElements){
			map.put(flowElement.getId(), flowElement);
		}
		List<SequenceFlow> sequenceFlows = getSequenceFlows(bpmnXml);
		Set<FlowElement> startEvents = new HashSet<FlowElement>();
		Set<FlowElement> endEvents = new HashSet<FlowElement>();
		Set<FlowElement> sourceTasks = new HashSet<FlowElement>();
		Set<FlowElement> targetTasks = new HashSet<FlowElement>();
		Set<FlowElement> parallelGateways = new HashSet<FlowElement>();
		Set<FlowElement> inclusiveGateways = new HashSet<FlowElement>();
		
		List<String> errorCodes = new ArrayList<String>();
		for(SequenceFlow sequenceFlow:sequenceFlows){			
			if(!(sequenceFlow.getSourceRef() instanceof FlowElement)
					|| !(sequenceFlow.getTargetRef() instanceof FlowElement)){
				continue;
			}
			FlowElement sourceDef = (FlowElement)sequenceFlow.getSourceRef();
			FlowElement targetDef = (FlowElement)sequenceFlow.getTargetRef();
			FlowElement sourceElement = map.get(sourceDef.getId());
			FlowElement targetElement = map.get(targetDef.getId());
			
			//=== 处理开始节点和结束节点 ===
			//是开始节点，直接指向多个分支
			if(sourceElement instanceof StartEvent){
				if(startEvents.contains(sourceElement)){
					errorCodes.add("event.startEventMoreThanOneOutcome");
				}else{
					startEvents.add(sourceElement);
				}
			}			
			//有线条指向开始节点
			if(targetElement instanceof StartEvent){
				errorCodes.add("event.unableToStartEvent");				
			}
			if(targetElement instanceof EndEvent){
				endEvents.add(targetElement);						
			}
			
			//=== 处理任务 ===
			NodeDefType nodeDefType = getNodeDefType(sourceElement);
			if(nodeDefType!=null){ //是普通任务
				if(sourceTasks.contains(sourceElement)){
					//普通任务不能作为网关产生分支
					errorCodes.add("task.cannotUseAsGateway");
				}else{
					sourceTasks.add(sourceElement);
				}				
			}
			nodeDefType = getNodeDefType(targetElement);
			if(nodeDefType!=null){ //是普通任务
				targetTasks.add(targetElement);				
			}			
			
			//=== 处理网关 ===
			if(isPairGateway(sourceElement)){				
				if(sourceElement instanceof ParallelGateway){
					parallelGateways.add(sourceElement);
				}
				if(sourceElement instanceof InclusiveGateway){
					inclusiveGateways.add(sourceElement);
				}
			}
			if(isPairGateway(targetElement)){				
				if(targetElement instanceof ParallelGateway){
					parallelGateways.add(targetElement);
				}
				if(targetElement instanceof InclusiveGateway){
					inclusiveGateways.add(targetElement);
				}
			}
		}
		//每个任务节点都应该有入有出
		if(sourceTasks.size()!=targetTasks.size()){
			errorCodes.add("task.everyTaskHaveInOut");
		}	
		//必须有一个开始节点
		if(startEvents.size()!=1){
			errorCodes.add("event.processMustHaveOneStartEvent");
		}
		//至少需要有一个结束节点
		if(endEvents.size()==0){
			errorCodes.add("event.processMustHaveAtLeastOneEndEvent");
		}
		//同步网关必须成对出现
		if(parallelGateways.size()>0 && parallelGateways.size()%2==1){
			errorCodes.add("gateway.parallelGatewayMustBePaired");
		}
		//条件同步网关必须成对出现
		if(inclusiveGateways.size()>0 && inclusiveGateways.size()%2==1){
			errorCodes.add("gateway.inclusiveGatewayMustBePaired");
		}		
		return errorCodes;
	}
	
	/**
	 * 返回流程元素的类型枚举
	 * @param flowElement
	 * @return 
	 * NodeDefType
	 * @exception 
	 * @since  1.0.0
	 */
	public static NodeDefType getNodeDefType(FlowElement flowElement){
		if(flowElement instanceof UserTask){
			if(((UserTask) flowElement).getLoopCharacteristics()!=null){
				return NodeDefType.SignTask;
			}else{
				return NodeDefType.UserTask;
			}
		}else if(flowElement instanceof ServiceTask){
			return NodeDefType.ServiceTask;
		}else if(flowElement instanceof ScriptTask){
			return NodeDefType.ScriptTask;				
		}else if(flowElement instanceof SendTask){
			return NodeDefType.SendTask;
		}else if(flowElement instanceof ManualTask){
			return NodeDefType.ManualTask;
		}else if(flowElement instanceof ReceiveTask){
			return NodeDefType.ReceiveTask;
		}else if(flowElement instanceof StartEvent){
			return NodeDefType.StartEvent;
		}else if(flowElement instanceof EndEvent){
			return NodeDefType.EndEvent;
		}else if(flowElement instanceof SubProcess){
			return NodeDefType.SubProcess;
		}else if(flowElement instanceof CallActivity){
			return NodeDefType.CallActivity;
		}				
		return NodeDefType.Unknown;
	}
	
	/**
	 * 根据流程ID获得外部子流程的流程定义Key的集合。
	 * @param defId
	 * @return 
	 * Set<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public static Set<String> getCallActivityDefKeys(String defId){
		//TODO
		return null;
	}
	
	/**
	 * 判断一个元素是否是网关
	 * @param flowElement
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private static boolean isGateway(FlowElement flowElement){
		if(flowElement instanceof ParallelGateway
				|| flowElement instanceof InclusiveGateway
				|| flowElement instanceof ExclusiveGateway){
			return true;
		}
		return false;
	}	
	
	/**
	 * 判断一个元素是否是配对出现的网关
	 * @param flowElement
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private static boolean isPairGateway(FlowElement flowElement){
		if(flowElement instanceof ParallelGateway
				|| flowElement instanceof InclusiveGateway){
			return true;
		}
		return false;
	}
	
	public static OutputStream marshall(Object jaxbElement,OutputStream os) throws JAXBException{
		JAXBContext jctx = JAXBContext.newInstance(ObjectFactory.class);
		Marshaller marshaller = jctx.createMarshaller();
		marshaller.marshal(jaxbElement, os);
		return os;
	}
	
	public static Object unmarshall(InputStream is,Class<? extends Object>... classes) throws JAXBException, IOException{
		JAXBContext jctx = ContextFactory.newInstance(classes);
		Unmarshaller unmarshaller = jctx.createUnmarshaller();
		Object obj = unmarshaller.unmarshal(is);
		return obj;
	}
	
	public static Object unmarshall(String bpmnxml,Class<? extends Object> classes) throws JAXBException, IOException{
		InputStream is = new ByteArrayInputStream(bpmnxml.getBytes());		
		return unmarshall(is,classes);
	}
}
