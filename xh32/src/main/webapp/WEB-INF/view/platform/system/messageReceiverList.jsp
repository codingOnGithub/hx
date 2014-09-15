
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="messageReceiver.title"/><spr:message code="title.manage"/></title>
	<script type="text/javascript">
	function mark(t){
		var ids = "";
		if(t){
			var tr = $(t).parents("tr"),
				pk = $("input.pk",tr).val();

			if(!pk)return;
			ids = pk;
		}
		else{
			var idArr = [];

			$("input.pk").each(function(){
				var me = $(this),
					state = me.attr("checked");

				if(state)
					idArr.push(me.val());
			});
			if(idArr.length==0){
				$.ligerDialog.warn($lang.operateTip.selectRecord,$lang.tip.msg);
				return;
			}
			ids = idArr.join(',');
		}
		var url = __ctx + '/platform/system/messageReceiver/mark.ht';
		var params={ids:ids};
		$.post(url,params,function(d){
			if(t){
				return;
			}
			var json = eval("("+d+")");
			if(json.result){
				$.ligerDialog.success(json.message,$lang.tip.msg,function(){
					location.href=location.href.getNewUrl();	
				});
			}
			else{
				$.ligerDialog.error(json.message,$lang.tip.msg);
			}
		});
	};
</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="messageReceiver.title"/><spr:message code="title.manageList"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>						
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link done" onclick="mark()"><span></span><spr:message code="menu.button.markRead"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
								        <li><span class="label"><spr:message code="messageSend.subject"/>:</span><input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/></li>																						
										<li>
											<span class="label"><spr:message code="message.messageType"/>:</span>												
											<select name="Q_messageType_S" class="select" value="${param['Q_messageType_S']}">
											    <option value=""><spr:message code="search.select.all"/></option>
												<option value="1" <c:if test="${param['Q_messageType_S'] == 1}">selected</c:if>><spr:message code="message.messageType.personal"/></option>
												<option value="2" <c:if test="${param['Q_messageType_S'] == 2}">selected</c:if>><spr:message code="message.messageType.schedule"/></option>
												<option value="3" <c:if test="${param['Q_messageType_S'] == 3}">selected</c:if>><spr:message code="message.messageType.planTask"/></option>
												<option value="4" <c:if test="${param['Q_messageType_S'] == 4}">selected</c:if>><spr:message code="message.messageType.systemMsg"/></option>
												<option value="5" <c:if test="${param['Q_messageType_S'] == 5}">selected</c:if>><spr:message code="message.messageType.agent"/> </option>
												<option value="6" <c:if test="${param['Q_messageType_S'] == 6}">selected</c:if>><spr:message code="message.messageType.flow"/> </option>
										    </select>
									    </li>
									    
									    <li>
										    <span class="label"><spr:message code="bpmProCopyto.isReaded"/>:</span>												
											<select name="Q_receiveTime_S" class="select" value="${param['Q_receiveTime_S']}">
											    <option value=""><spr:message code="search.select.all"/></option>
												<option value="1" <c:if test="${param['Q_receiveTime_S'] == 1}">selected</c:if>><spr:message code="bpmProCopyto.isReaded.read"/></option>
												<option value="2" <c:if test="${param['Q_receiveTime_S'] == 2}">selected</c:if>><spr:message code="bpmProCopyto.isReaded.noRead"/></option>
										    </select>
									    </li>
									    
									    <div class="row_date">
										<li><span class="label"><spr:message code="messageSend.sendTime"/> <spr:message code="search.from"/>:</span><input  name="Q_beginreceiveTime_DL"  class="inputText datetime" value="${param['Q_beginreceiveTime_DL']}"/></li>
										<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endreceiveTime_DG" class="inputText datetime" value="${param['Q_endreceiveTime_DG']}"/></li>
									    </div>							
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="messageReceiverList" id="messageReceiverItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${messageReceiverItem.rid}">
							</display:column>
							<display:column property="userName" titleKey="messageSend.userName" sortable="true" sortName="userName"></display:column>
							<display:column property="subject" titleKey="messageSend.subject" sortable="true" sortName="subject"></display:column>
							<display:column titleKey="message.messageType" sortable="true" sortName="messageType" style="text-align:center;">
							<c:choose>
								<c:when test="${messageReceiverItem.messageType==1}">
								       <spr:message code="message.messageType.personal"/>
							   	</c:when>
							   	<c:when test="${messageReceiverItem.messageType==2}">
								        <spr:message code="message.messageType.schedule"/>
							   	</c:when>
							   	<c:when test="${messageReceiverItem.messageType==3}">
								       <spr:message code="message.messageType.planTask"/>
							   	</c:when>
							   	<c:when test="${messageReceiverItem.messageType==4}">
					                   <spr:message code="message.messageType.systemMsg"/>
							   	</c:when>
							   	<c:when test="${messageReceiverItem.messageType==5}">
					                  <spr:message code="message.messageType.agent"/>
							   	</c:when>
							   	<c:when test="${messageReceiverItem.messageType==6}">
					                  <spr:message code="message.messageType.flow"/>
							   	</c:when>
						       	<c:otherwise>
					                  <spr:message code="message.messageType.other"/>                 
							   	</c:otherwise>
						    </c:choose>
							</display:column>	
							<display:column titleKey="messageSend.sendTime" sortable="true" sortName="sendTime" style="text-align:center;">
								<fmt:formatDate value="${messageReceiverItem.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
							</display:column>							
							<display:column  titleKey="messageRead.receiveTime" sortable="true" sortName="receiveTime" style="text-align:center;">
								<c:choose>
									<c:when test="${messageReceiverItem.receiveTime!=null}">
										<fmt:formatDate value="${messageReceiverItem.receiveTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</c:when>
									<c:otherwise>
										<spr:message code="message.state.noRead"/>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
								<c:if test="${messageReceiverItem.rid!=null}">
									<a href="del.ht?id=${messageReceiverItem.rid}" class="link del"><spr:message code="operator.del"/></a>
								</c:if>
								<c:if test="${messageReceiverItem.canReply==1}">
								<a href="${ctx}/platform/system/messageReply/edit.ht?messageId=${messageReceiverItem.id}" class="link edit"><spr:message code="message.reply"/></a>
								</c:if>
								<a href="${ctx}/platform/system/messageRead/list.ht?messageId=${messageReceiverItem.id}" class="link detail"><spr:message code="operator.detail"/></a>
							</display:column>
						</display:table>
						<hotent:paging tableId="messageReceiverItem"/>
				
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


