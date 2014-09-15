<%--
	time:2012-02-20 14:57:31
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title><spr:message code="workTimeSetting.get.title" /></title>
	<%@include file="/commons/include/getById.jsp" %>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="workTimeSetting.get.span" /></span>
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="workTimeSetting.name" />:</th>
						<td>${workTimeSetting.name}</td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="workTimeSetting.memo" />:</th>
						<td>${workTimeSetting.memo}</td>
					</tr>
				</table>
					
				<br>
				
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="workTimeSetting.time" /></span>
				</div>
				
				<table id="workTimeItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
					<thead>
						<th width="20%"><spr:message code="workTime.startTime" /></th>
						<th width="20%"><spr:message code="workTime.endTime" /></th>
						<th width="50%"><spr:message code="workTimeSetting.memo" /></th>
					</thead>
					<tbody>
						<c:forEach items="${workTimelist}" var="workTimeItem">
							<tr>
								<td style="text-align: center;">
									${workTimeItem.startTime}
								</td>
								<td style="text-align: center;">
									${workTimeItem.endTime}
								</td>
								<td style="text-align: center;">
									${workTimeItem.memo}
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
				
		</div>
</div>

</body>
</html>
