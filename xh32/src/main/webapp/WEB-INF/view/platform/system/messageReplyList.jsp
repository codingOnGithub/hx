
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="messageReply.title"/><spr:message code="title.manage"/></title>
</head>
<body>
<div class="panel">
<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="messageReply.title"/><spr:message code="title.detail"/></span>
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
					<th width="20%"><spr:message code="messageSend.content"/>:</th>
					<td>${messageSend.content}</td>
				</tr>
			</table>					
		</div>
		<div class="panel-data">
			<table id="dicTable" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
				   		<thead>
				    		<th style="width:18%"><spr:message code="messageReply.reply"/></th>
				    		<th><spr:message code="messageReply.content"/></th>
				    		<th style="width:18%"><spr:message code="messageReply.replyTime"/></th>			    		
				    	</thead>					    	
				    	<tbody>
				    	<c:forEach items="${replyList}" var="replyItem">
				    		<tr class="${status.index%2==0?'odd':'even'}">					    								    								    								    								    			
				    			<td>${replyItem.reply }</td> 
				    			<td>${replyItem.content }</td>
				    			<td><fmt:formatDate value="${replyItem.replyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td> 					    								    			
				    		</tr>
				    	</c:forEach>					    						    					    	
				    </tbody>					    	
		     </table>								
		</div>		
</div>
</body>
</html>


