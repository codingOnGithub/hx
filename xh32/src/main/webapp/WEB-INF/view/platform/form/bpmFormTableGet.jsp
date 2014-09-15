<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="bpmFormTable.title"/><spr:message code="operator.detail"/></title>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmFormTable.title"/><spr:message code="operator.detail"/></span>
			</div>
			<c:if test="${canReturn==0}"> 
			<div class="panel-toolbar">
				<div class="toolBar">
					<c:choose>
						<c:when test="${canClose==1}">
							<div class="group">
								<a class="link close" href="###" onclick="window.close()"><span></span><spr:message code="menu.button.close"/></a>
							</div>						
						</c:when>
						<c:otherwise>
							<div class="group">
								<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
							</div>	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			</c:if>
		</div>

		<div class="panel-body">
				  <div class="table-top">
							<div class="table-top-left">
										<span class="label"> <spr:message code="bpmFormTable.tableName"/>:&nbsp;<b>${table.tableName}</b> 
										&nbsp;&nbsp;&nbsp;<spr:message code="bpmFormTable.tableDesc"/>:&nbsp;<b>${table.tableDesc }</b>
												&nbsp;&nbsp;&nbsp;<spr:message code="bpmFormTable.isMain"/>:<b> <c:choose>
														<c:when test="${table.isMain == 1 }">
													<spr:message code="bpmFormTable.mainTable"/>
												</c:when>
														<c:otherwise>
													<spr:message code="bpmFormTable.subTable"/>&nbsp;&nbsp;&nbsp;<spr:message code="bpmFormTable.mainTableId"/>:${mainTable}
												</c:otherwise>
													</c:choose>
											</b> <spr:message code="bpmFormTable.isExternalTables"/>:<b> <c:choose>
														<c:when test="${table.isExternal == 1 }">
													<spr:message code="yes"/>
												</c:when>
														<c:otherwise>
													<spr:message code="no"/>
												</c:otherwise>
													</c:choose>
											</b>
										</span>
						</div>												
					</div>						
				<table id="list" cellpadding="1" cellspacing="1" class="table-grid">
					<tr>
						<th ><spr:message code="list.orderNo"/></th>
						<th ><spr:message code="bpmFormField.fieldName"/></th>
						<th><spr:message code="bpmFormField.fieldDesc"/></th>
						<th><spr:message code="bpmFormField.fieldType"/></th>
						<th><spr:message code="bpmFormField.isRequired"/></th>
						<th><spr:message code="bpmFormField.isList"/></th>
						<th><spr:message code="bpmFormField.isQuery"/></th>
						<th><spr:message code="bpmFormField.isFlowVar"/></th>
					</tr>
					<c:if test="${fn:length(fields) == 0}">
						<tr>
							<td colspan="8"><spr:message code="search.select.none"/></td>
						</tr>
					</c:if>
					<c:forEach items="${fields}" var="field" varStatus="status">
						<c:if test="${field.isHidden == 0 }">
							<tr>
								<td>${status.index + 1}</td>
								<td>${field.fieldName }</td>
								<td>${field.fieldDesc }</td>
								<td><c:if test="${field.fieldType == 'varchar'}"><spr:message code="bpmFormField.fieldType.varchar"/>(${field.charLen })</c:if>
									<c:if test="${field.fieldType == 'number'}"><spr:message code="bpmFormField.fieldType.number"/>(${field.intLen }, ${field.decimalLen })</c:if>
									<c:if test="${field.fieldType == 'date'}"><spr:message code="bpmFormField.fieldType.date"/></c:if> <c:if
										test="${field.fieldType == 'clob'}"><spr:message code="bpmFormField.fieldType.clob"/></c:if></td>
								<td><c:if test="${field.isRequired == 1 }">√</c:if></td>
								<td><c:if test="${field.isList == 1 }">√</c:if></td>
								<td><c:if test="${field.isQuery == 1 }">√</c:if></td>
								<td><c:if test="${field.isFlowVar == 1 }">√</c:if></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
					
				<c:if test="${table.isMain == 1 && fn:length(subList)>0}">
					<br>
					<table  cellpadding="1" cellspacing="1" class="table-grid">
						<tr>
							<td colspan="4"><spr:message code="bpmFormTable.subTableList"/></td>
						</tr>
						<tr>
							<th ><spr:message code="list.orderNo"/></th>
							<th ><spr:message code="bpmFormTable.tableName"/></th>
							<th ><spr:message code="bpmFormTable.tableDesc"/></th>
							<th><spr:message code="menu.button.detail"/></th>
						</tr>
						<c:forEach items="${subList}" var="table" varStatus="status">
							<tr>
								<td>${status.index + 1}</td>
								<td>${table.tableName }</td>
								<td>${table.tableDesc }</td>
								<td><a href="get.ht?tableId=${table.tableId}&canClose=${canClose}" class="link detail"><spr:message code="menu.button.detail"/></a></td>
							</tr>
						</c:forEach>
					</table>
					
				</c:if>
				
			
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>