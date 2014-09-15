<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	function beforeClick(operatorType){<c:if test="${not empty mapButton.button}">
		switch(operatorType){<c:forEach items="${mapButton.button }" var="btn"  ><c:if test="${not empty btn.prevscript}">
				case ${btn.operatortype}:
				${btn.prevscript}
				break;</c:if></c:forEach>
			}</c:if>
	}
	
	function afterClick(operatorType){<c:if test="${not empty mapButton.button}">
		switch(operatorType){<c:forEach items="${mapButton.button }" var="btn" ><c:if test="${not empty btn.afterscript}">
			case ${btn.operatortype}:
				${btn.afterscript}
				break;</c:if></c:forEach>
			}</c:if>
	}
	
</script>
<div class="noprint">
	<div class="panel-toolbar">
		<div class="toolBar">
			<c:choose>
				<c:when test="${ isManage==0 }">
						<c:choose>
						<c:when test="${empty mapButton }">
							<c:if test="${isSignTask && isAllowDirectExecute}">
								<div class="group">
									<spr:message code="task.incToolBarNode.privilege"/>：<input type="checkbox" value="1" id="chkDirectComplete"><label for="chkDirectComplete"><span></span><spr:message code="task.incToolBarNode.terminate.direct"/></label>
								</div>
							</c:if>
						<div class="group"><a id="btnAgree" class="link agree"><span></span><spr:message code="bpmNodeButton.button.complete"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${isSignTask==true}">
							<div class="group"><a id="btnNotAgree" class="link notAgree"><span></span><spr:message code="bpmNodeButton.button.oppose"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a id="btnAbandon" class="link abandon"><span></span><spr:message code="bpmNodeButton.button.abstent"/></a></div>
							<div class="l-bar-separator"></div>
							<c:if test="${isAllowRetoactive==true}">
								<div class="group"><a class="link flowDesign" onclick="showAddSignWindow()"><span></span><spr:message code="bpmNodeButton.button.addsign"/></a></div>
								<div class="l-bar-separator"></div>
							</c:if>
						</c:if>
						<div class="group"><a id="btnSave" class="link save" ><span></span><spr:message code="bpmNodeButton.button.saveForm"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${isCanAssignee}">
							<div class="group"><a id="btnForward" class="link goForward " onclick="changeAssignee()"><span></span><spr:message code="bpmNodeButton.button.assignees"/></a></div>
							<div class="l-bar-separator"></div>
						</c:if>
						<c:if test="${isCanBack}">
						<div class="group"><a id="btnReject" class="link reject" ><span></span><spr:message code="bpmNodeButton.button.back"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a id="btnRejectToStart" class="link rejectToStart" ><span></span><spr:message code="bpmNodeButton.button.backToStart"/></a></div>
						<div class="l-bar-separator"></div>
						</c:if>
						
						<div class="group"><a class="link setting" onclick="showTaskUserDlg()"><span></span><spr:message code="bpmNodeButton.button.image"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link search" onclick="showTaskOpinions()"><span></span><spr:message code="bpmNodeButton.button.history"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link sendMessage" onclick="showTaskCommunication()"><span></span><spr:message code="bpmNodeButton.button.commu"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${isSignTask==false && task.description!='39'}">
							<div class="group"><a class="link switchuser" onclick="showTaskTransTo()"><span></span><spr:message code="bpmNodeButton.button.plusSign"/></a></div>
							<div class="l-bar-separator"></div>
						</c:if>
						<div class="group"><a class="link print" onclick="window.print();"><span></span><spr:message code="bpmNodeButton.button.print"/></a></div>
						
						<c:if test="${isExtForm}">
							<c:choose>
								<c:when test="${!empty detailUrl && !empty form}">
									<div class="l-bar-separator"></div>
									<div class="group"><a class="link edit" onclick="openForm('${form}')" ><span></span><spr:message code="bpmNodeButton.button.editForm"/></a></div>
								</c:when>
							</c:choose>
						</c:if>
					</c:when>
					<c:otherwise>
						<c:if test="${not empty mapButton.button}">
							<c:if test="${isSignTask && isAllowDirectExecute}">
								<div class="group">
									<spr:message code="task.incToolBarNode.privilege"/>：<input type="checkbox" value="1" id="chkDirectComplete"><label for="chkDirectComplete"><spr:message code="task.incToolBarNode.terminate.direct"/></label>
								</div>
							</c:if>
							<c:forEach items="${mapButton.button }" var="btn"  varStatus="status">
								<c:choose>
									<c:when test="${btn.operatortype==1 }">
										<!--  同意-->
										<div class="group"><a id="btnAgree" class="link agree"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==2 }">
										<!-- 反对-->
										<div class="group"><a id="btnNotAgree" class="link notAgree"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==3 }">
										<!--弃权-->
										<c:if test="${isSignTask==true}">
										<div class="group"><a id="btnAbandon" class="link abandon"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
										</c:if>
									</c:when>
									
									<c:when test="${btn.operatortype==4 }">
										<!--驳回-->
										<c:if test="${isCanBack}">
										<div class="group"><a id="btnReject" class="link reject"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
										</c:if>
									</c:when>
									<c:when test="${btn.operatortype==5 }">
										<!--驳回到发起人-->
										<c:if test="${isCanBack && toBackNodeId!=task.taskDefinitionKey}">
											<div class="group"><a id="btnRejectToStart" class="link rejectToStart"><span></span>${btn.btnname }</a></div>
											<div class="l-bar-separator"></div>
										</c:if>
									</c:when>
									<c:when test="${btn.operatortype==6 && isCanAssignee}">
										<!--交办-->
										<div class="group"><a id="btnForward" class="link goForward" onclick="changeAssignee()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==7 }">
										<c:if test="${isSignTask==true}">
										<!--补签-->
										<c:if test="${isAllowRetoactive==true}">
											<div class="group"><a class="link flowDesign" onclick="showAddSignWindow()"><span></span>${btn.btnname }</a></div>
											<div class="l-bar-separator"></div>
										</c:if>
										</c:if>
									</c:when>
									<c:when test="${btn.operatortype==8 }">
										<!--保存表单-->
										<div class="group"><a id="btnSave" class="link save" ><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==9 }">
										<!--流程图-->
										<div class="group"><a class="link setting" onclick="showTaskUserDlg()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==10 }">
										<!--打印-->
										<div class="group"><a class="link print" onclick="window.print();"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==11 }">
										<!--审批历史-->
										<div class="group"><a class="link history" onclick="showTaskOpinions()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==14 }">
										<!--Web签章-->
										<div class="group"><a class="link addWebSigns" onclick="addWebSigns()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==15 }">
										<!--手写签章-->
										<div class="group"><a class="link addHangSigns" onclick="addHangSigns()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==16 }">
										<!--沟通-->
										<div class="group"><a class="link sendMessage" onclick="showTaskCommunication()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
									<c:when test="${btn.operatortype==17 && task.description!='39'}">
										<!--加签-->
										<div class="group"><a class="link switchuser" onclick="showTaskTransTo()"><span></span>${btn.btnname }</a></div>
										<div class="l-bar-separator"></div>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${isExtForm}">
							<c:choose>
								<c:when test="${!empty detailUrl && !empty form}">
									<div class="l-bar-separator"></div>
									<div class="group"><a class="link edit" onclick="openForm('${form}')" ><span></span><spr:message code="bpmNodeButton.button.editForm"/></a></div>
								</c:when>
							</c:choose>
						</c:if>
					</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
						<c:if test="${isSignTask && isAllowDirectExecute}">
								<div class="group">
									<spr:message code="task.incToolBarNode.privilege"/>：<input type="checkbox" value="1" id="chkDirectComplete"><label for="chkDirectComplete"><spr:message code="task.incToolBarNode.terminate.direct"/></label>
								</div>
						</c:if>
						<div class="group"><a id="btnAgree" class="link agree"><span></span><spr:message code="bpmNodeButton.button.complete"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${isSignTask==true}">
							<div class="group"><a id="btnNotAgree" class="link notAgree"><span></span><spr:message code="bpmNodeButton.button.oppose"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a id="btnAbandon" class="link abandon"><span></span><spr:message code="bpmNodeButton.button.abstent"/></a></div>
							<div class="l-bar-separator"></div>
							<c:if test="${isAllowRetoactive==true}">
								<div class="group"><a class="link flowDesign" onclick="showAddSignWindow()"><span></span><spr:message code="bpmNodeButton.button.addsign"/></a></div>
								<div class="l-bar-separator"></div>
							</c:if>
						</c:if>
						<div class="group"><a id="btnSave" class="link save" ><span></span><spr:message code="bpmNodeButton.button.saveForm"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${isCanBack}">
							<div class="group"><a id="btnReject" class="link reject" ><span></span><spr:message code="bpmNodeButton.button.back"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a id="btnRejectToStart" class="link rejectToStart" ><span></span><spr:message code="bpmNodeButton.button.backToStart"/></a></div>
							<div class="l-bar-separator"></div>
						</c:if>
						<div class="group"><f:a alias="endProcess" css="link abandon"  id="btnEnd" href="#"><span></span><spr:message code="bpmNodeButton.button.end"/></f:a></div>
						<div class="l-bar-separator"></div>
						
						<div class="group"><a class="link setting" onclick="showTaskUserDlg()"><span></span><spr:message code="bpmNodeButton.button.image"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link search" onclick="showTaskOpinions()"><span></span><spr:message code="bpmNodeButton.button.history"/></a></div>
						<div class="l-bar-separator"></div>
						
						<div class="group"><a class="link print" onclick="window.print();"><span></span><spr:message code="bpmNodeButton.button.print"/></a></div>
						<div class="l-bar-separator"></div>
						
						<div class="group"><a class="link sendMessage" onclick="showTaskCommunication();"><span></span><spr:message code="bpmNodeButton.button.commu"/></a></div>
						<div class="l-bar-separator"></div>
						<c:if test="${isSignTask==false && task.description!='39'}">
							<div class="group"><a class="link switchuser" onclick="showTaskTransTo()"><span></span><spr:message code="bpmNodeButton.button.plusSign"/></a></div>
							<div class="l-bar-separator"></div>
						</c:if>
						<!--Web签章-->
						<div class="group"><a class="link addWebSigns" onclick="addWebSigns()"><span></span><spr:message code="bpmNodeButton.button.webSignature"/></a></div>
						<div class="l-bar-separator"></div>
						<!--手写签章-->
						<div class="group"><a class="link addHangSigns" onclick="addHangSigns()"><span></span><spr:message code="bpmNodeButton.button.handSignature"/></a></div>
						<div class="l-bar-separator"></div>
									
						<c:if test="${isExtForm}">
							<c:choose>
								<c:when test="${!empty detailUrl && !empty form}">
									<div class="l-bar-separator"></div>
									<div class="group"><a class="link edit" onclick="openForm('${form}')" ><spr:message code="bpmNodeButton.button.editForm"/></a></div>
								</c:when>
							</c:choose>
						</c:if>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ isManage==0 }">
				<c:if test="${fn:indexOf(bpmNodeSet.jumpType,'1')!=-1}">
					<span style="height:30px;line-height:30px;"><input type="radio" checked="checked" name="jumpType" onclick="chooseJumpType(1)" value="1" />&nbsp;<spr:message code="task.incToolBarNode.normalJump"/></span>
				</c:if>
				<c:if test="${fn:indexOf(bpmNodeSet.jumpType,'2')!=-1}">
					<span style="height:30px;line-height:30px;"><input type="radio" name="jumpType" onclick="chooseJumpType(2)" value="2" />&nbsp;<spr:message code="task.incToolBarNode.selectPathJump"/></span>
				</c:if>
				<c:if test="${fn:indexOf(bpmNodeSet.jumpType,'3')!=-1}">
					<span style="height:30px;line-height:30px;"><input type="radio" name="jumpType" onclick="chooseJumpType(3)" value="3" />&nbsp;<spr:message code="task.incToolBarNode.freeJump"/></span>
				</c:if>
			</c:when>
			<c:otherwise>
				<span style="height:30px;line-height:30px;"><input type="radio" checked="checked" name="jumpType" onclick="chooseJumpType(1)" value="1" />&nbsp;<spr:message code="task.incToolBarNode.normalJump"/></span>
				<span style="height:30px;line-height:30px;"><input type="radio" name="jumpType" onclick="chooseJumpType(2)" value="2" />&nbsp;<spr:message code="task.incToolBarNode.selectPathJump"/></span>
				<span style="height:30px;line-height:30px;"><input type="radio" name="jumpType" onclick="chooseJumpType(3)" value="3" />&nbsp;<spr:message code="task.incToolBarNode.freeJump"/></span>
			</c:otherwise>
		</c:choose>
		<c:if test="${bpmDefinition.allowRefer==1}">
			<!-- 流程参考 -->
			<div class="group"><a id="btnReference" class="link reference" onclick="reference()"><span></span><spr:message code="task.incToolBarNode.reference"/></a></div>
		</c:if>
			<c:if test="${bpmDefinition.attachment!=''}">
				<%@include file="incHelp.jsp" %>
			</c:if>
		</div>	
	</div>
	
</div>