
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="messageRead.title"/><spr:message code="title.manage"/></title>
</head>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="messageRead.title"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="${returnUrl}"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		</div>
		<div class="panel-detail">
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%"><spr:message code="messageSend.subject"/>:</th>
					<td>${messageSend.subject}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="messageSend.userName"/>:</th>
					<td>${messageSend.userName}</td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="messageSend.sendTime"/>:</th>
					<td><fmt:formatDate value="${messageSend.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<th width="20%"><spr:message code="messageSend.content"/>:</th>
					<td>${messageSend.content}</td>
				</tr>
			</table>
			
		</div>
		<br/>
	    <c:if test="${replyList!=null}">
	    <div class="panel-data">
			<table id="dicTable" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
		   		<thead>
		    		<th style="width:13%"><spr:message code="messageReply.reply"/></th>
		    		<th><spr:message code="messageReply.content"/></th>
		    		<th style="width:15%"><spr:message code="messageReply.replyTime"/></th>	
		    		<!--<th style="width:6%">操作</th>-->
		    	</thead>					    	
		    	<tbody>
			    	<c:forEach items="${replyList}" var="replyItem">
			    		<tr style="<c:if test="${replyItem.isPrivate==1&&replyItem.replyId!=userId}">display:none</c:if>;height:50px" class="${status.index%2==0?'odd':'even'}">					    								    								    								    								    			
			    			<td>${replyItem.reply }</td> 
			    			<td>${replyItem.content }</td>
			    			<td><fmt:formatDate value="${replyItem.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 
			    			<!--<td><a href="del.ht?id=${replyItem.id}" class="link del" 
			    				style='${replyItem.replyId==userId?'display:':'display:none'}'>删除</a></td> -->				    								    			
			    		</tr>
			    	</c:forEach>
		    	</tbody>					    	
		     </table>
	     </div>	
		</c:if>							
				
</div>
</body>
</html>


