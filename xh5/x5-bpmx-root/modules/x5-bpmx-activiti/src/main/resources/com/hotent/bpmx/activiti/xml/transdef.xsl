<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:activiti="http://activiti.org/bpmn" 
	xmlns:bpmn2="http://www.omg.org/spec/BPMN/20100524/MODEL"
	xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI"
	>
	<xsl:param name="id" />
	<xsl:param name="name" />
	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="no" />
	<xsl:template match="/">
	<definitions targetNamespace="http://activiti.org/bpmn" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
		xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL" xmlns:bpmndi="http://www.omg.org/spec/BPMN/20100524/DI" 
		xmlns:dc="http://www.omg.org/spec/DD/20100524/DC" xmlns:di="http://www.omg.org/spec/DD/20100524/DI" 
		xsi:schemaLocation="http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd" >
		
		<xsl:if test="/bpmn2:definitions/bpmn2:collaboration">
			<xsl:copy-of select="/bpmn2:definitions/bpmn2:collaboration"></xsl:copy-of>	
		</xsl:if>
		
		<process>
				<xsl:attribute name="id">
					<xsl:value-of select="$id"></xsl:value-of>
				</xsl:attribute>
				<xsl:attribute name="name">
					<xsl:value-of select="$name"></xsl:value-of>
				</xsl:attribute>
				<extensionElements>
					<!-- 流程开始事件监听器 -->
					<activiti:executionListener  event="start" >
						<xsl:attribute name="delegateExpression" >${startEventListener}</xsl:attribute>
					</activiti:executionListener>
					<!-- 流程结束事件监听器 -->
					<activiti:executionListener  event="end" >
						<xsl:attribute name="delegateExpression" >${endEventListener}</xsl:attribute>
					</activiti:executionListener>
				</extensionElements>
				<xsl:apply-templates></xsl:apply-templates>
		</process>
		<xsl:if test="/bpmn2:definitions/bpmndi:BPMNDiagram">
			<xsl:copy-of select="/bpmn2:definitions/bpmndi:BPMNDiagram"></xsl:copy-of>	
		</xsl:if>
		
			
	</definitions>
	</xsl:template>
	
	
	
	
	<xsl:template match="bpmn2:laneSet">
		<xsl:copy-of select="."></xsl:copy-of>
	</xsl:template>
	
	<xsl:template match="bpmn2:subProcess">
	
		<xsl:for-each select=".">
			<subProcess>
				<xsl:call-template name="setAttrubute">
					<xsl:with-param name="obj" select="."></xsl:with-param>
				</xsl:call-template>
				
				<extensionElements>
					<!-- 流程开始事件监听器 -->
					<activiti:executionListener  event="start" >
						<xsl:attribute name="delegateExpression" >${subProcessStartListener}</xsl:attribute>
					</activiti:executionListener>
					<!-- 流程结束事件监听器 -->
					<activiti:executionListener  event="end" >
						<xsl:attribute name="delegateExpression" >${subProcessEndListener}</xsl:attribute>
					</activiti:executionListener>
				</extensionElements>
				
				<xsl:apply-templates select="bpmn2:startEvent">
				</xsl:apply-templates>
				<xsl:apply-templates select="bpmn2:endEvent">
				</xsl:apply-templates>
				<xsl:apply-templates select="bpmn2:userTask">
				</xsl:apply-templates>
				<xsl:apply-templates select="bpmn2:sequenceFlow">
				</xsl:apply-templates>
				
				<xsl:apply-templates select="bpmn2:subProcess">
				</xsl:apply-templates>
				<xsl:apply-templates select="bpmn2:exclusiveGateway">
				</xsl:apply-templates>
				<xsl:apply-templates select="bpmn2:parallelGateway">
				</xsl:apply-templates>
				<xsl:apply-templates select="bpmn2:inclusiveGateway">
				</xsl:apply-templates>
				
				<xsl:apply-templates select="bpmn2:callActivity">
				</xsl:apply-templates>
				
				<xsl:apply-templates select="bpmn2:task">
				</xsl:apply-templates>
				
				<xsl:apply-templates select="bpmn2:receiveTask">
				</xsl:apply-templates>
				
				<xsl:apply-templates select="bpmn2:serviceTask">
				</xsl:apply-templates>
				
				
				
		
				
			</subProcess>
		</xsl:for-each>
		
		
	
	</xsl:template>
	
	<xsl:template match="bpmn2:userTask">
		
		<userTask >
			<xsl:call-template name="setAttrubute">
					<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		
			
			<extensionElements>
					<xsl:choose>
						<xsl:when test="./bpmn2:multiInstanceLoopCharacteristics">
							<activiti:taskListener event="create" >
								<xsl:attribute name="delegateExpression" >${taskSignCreateListener}</xsl:attribute>
							</activiti:taskListener>	
						</xsl:when>
						<xsl:otherwise>
							<activiti:taskListener event="create" delegateExpression="${taskCreateListener}" >
								<xsl:attribute name="delegateExpression" >${taskCreateListener}</xsl:attribute>
							</activiti:taskListener>
						</xsl:otherwise>
					</xsl:choose>
					
					<activiti:taskListener event="complete" delegateExpression="${taskCompleteListener}"  >
						<xsl:attribute name="delegateExpression" >${taskCompleteListener}</xsl:attribute>
					</activiti:taskListener>
			</extensionElements>
			
			<xsl:if test="./bpmn2:multiInstanceLoopCharacteristics">
				<multiInstanceLoopCharacteristics activiti:elementVariable="assignee">
					<xsl:attribute name="isSequential">
						<xsl:choose>
							<xsl:when test="@isSequential='true'">true</xsl:when>
							<xsl:otherwise>false</xsl:otherwise>
						</xsl:choose>
					</xsl:attribute>
					<xsl:attribute name="activiti:collection">${taskUserAssignService.getSignUser(execution)}</xsl:attribute>
					<completionCondition>${signComplete.isComplete(execution) }</completionCondition>
				</multiInstanceLoopCharacteristics>
			</xsl:if>
		</userTask>
		
	</xsl:template>
	<xsl:template match="bpmn2:endEvent">
		
		<xsl:for-each select=".">
			<endEvent>
				<xsl:call-template name="setAttrubute">
					<xsl:with-param name="obj" select="."></xsl:with-param>
				</xsl:call-template>
				<xsl:if test="trigger='Error'">
					<errorEventDefinition>
						<xsl:attribute name="errorRef">
							<xsl:value-of select="'Error'"></xsl:value-of>
						</xsl:attribute>
					</errorEventDefinition>
				</xsl:if>
			</endEvent>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template match="bpmn2:startEvent">
		<startEvent activiti:initiator="startUser">
			<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		</startEvent>
	</xsl:template>
	
	<xsl:template match="bpmn2:sequenceFlow">
		 <sequenceFlow id="flow1" >
		 	<xsl:attribute name="id">
				<xsl:value-of select="@id"></xsl:value-of>
			</xsl:attribute>
			<xsl:attribute name="sourceRef">
				<xsl:value-of select="@sourceRef"></xsl:value-of>
			</xsl:attribute>
			<xsl:attribute name="targetRef">
				<xsl:value-of select="@targetRef"></xsl:value-of>
			</xsl:attribute>
		 </sequenceFlow>
		
	</xsl:template>
	
	
	
	<xsl:template match="bpmn2:callActivity">
		 <callActivity>
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
			
			<extensionElements>
				<activiti:executionListener event="start" >
					<xsl:attribute name="delegateExpression" >${callSubProcessStartListener}</xsl:attribute>
				</activiti:executionListener>
				<activiti:executionListener event="end">
					<xsl:attribute name="delegateExpression" >${callSubProcessEndListener}</xsl:attribute>
				</activiti:executionListener>
				<activiti:in source="outPassVars" target="innerPassVars" />
				<activiti:out source="innerPassVars" target="outPassVars" />
							
			</extensionElements>
			<xsl:if test="./bpmn2:multiInstanceLoopCharacteristics">
				<multiInstanceLoopCharacteristics activiti:elementVariable="assignee">
					<xsl:attribute name="isSequential">
						<xsl:choose>
							<xsl:when test="@isSequential='true'">true</xsl:when>
							<xsl:otherwise>false</xsl:otherwise>
						</xsl:choose>
					</xsl:attribute>
					<xsl:attribute name="activiti:collection">${taskUserAssignService.getExtSubProcessMultipleUser(execution)}</xsl:attribute>
				</multiInstanceLoopCharacteristics>
			</xsl:if>
			
			
			
		 </callActivity>
		 
		 
		
	</xsl:template>
	
	<xsl:template match="bpmn2:exclusiveGateway">
		 <exclusiveGateway>
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		 </exclusiveGateway>
		
	</xsl:template>
	
	<xsl:template match="bpmn2:parallelGateway">
		 <parallelGateway>
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		 </parallelGateway>
		
	</xsl:template>
	
	<xsl:template match="bpmn2:inclusiveGateway">
		 <inclusiveGateway>
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		 </inclusiveGateway>
		
	</xsl:template>
	
	
	<xsl:template match="bpmn2:task">
		 <task>
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		 </task>
		
	</xsl:template>
	
	<xsl:template match="bpmn2:receiveTask">
		 <receiveTask>
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
		 </receiveTask>
		
	</xsl:template>
	
	<xsl:template match="bpmn2:serviceTask">
		 <serviceTask activiti:delegateExpression="${serviceTask}">
		 	<xsl:call-template name="setAttrubute">
				<xsl:with-param name="obj" select="."></xsl:with-param>
			</xsl:call-template>
			<extensionElements>
			  <activiti:executionListener event="start" >
			  	<xsl:attribute name="delegateExpression">${autoTaskListener}</xsl:attribute>
			  </activiti:executionListener> 
			</extensionElements>
			
		 </serviceTask>
		
	</xsl:template>
	
	
	
	
	

	
	<xsl:template name="setAttrubute">
		<xsl:param name="obj" />
		<xsl:attribute name="id">
			<xsl:value-of select="$obj/@id"></xsl:value-of>
		</xsl:attribute>
		<xsl:attribute name="name">
			<xsl:value-of select="$obj/@name"></xsl:value-of>
		</xsl:attribute>
	</xsl:template>
	
</xsl:stylesheet>

