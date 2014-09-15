<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head> 
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="title.edit"/><spr:message code="desktopMycolumn.title"/></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=desktopMycolumn"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#desktopMycolumnForm').submit(); 
			});
		});
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${desktopMycolumn.id !=null }">
			            <span class="tbar-label"><spr:message code="title.edit"/><spr:message code="desktopMycolumn.title"/></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="title.add"/><spr:message code="desktopMycolumn.title"/></span>
			        </c:otherwise>
			    </c:choose>
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="desktopMycolumnForm" method="post" action="save.ht">
				<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.userId"/>: </th>
						<td><input type="text" id="userId" name="userId" value="${desktopMycolumn.userId}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.layoutId"/>: </th>
						<td><input type="text" id="layoutId" name="layoutId" value="${desktopMycolumn.layoutId}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.columnId"/>: </th>
						<td><input type="text" id="columnId" name="columnId" value="${desktopMycolumn.columnId}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.col"/>: </th>
						<td><input type="text" id="col" name="col" value="${desktopMycolumn.col}"  class="inputText"/></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="desktopMycolumn.sn"/>: </th>
						<td><input type="text" id="sn" name="sn" value="${desktopMycolumn.sn}"  class="inputText"/></td>
					</tr>
				</table>
				</div>
				<input type="hidden" name="id" value="${desktopMycolumn.id}" />
			</form>
		</div>
	</div>
</body>
</html>
