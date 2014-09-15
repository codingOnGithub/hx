<%--
	time:2011-12-05 17:00:54
--%>
<%@page language="java" pageEncoding="UTF-8" import="com.hotent.platform.model.system.Resources"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/getById.jsp" %>
	<title><spr:message code="res.sub.detail"/></title>
</head>
<body>
	<c:if test="${resources!=null}">
		<div class="panel">
			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label">${resources.resName}</span>
				</div>
				
			</div>
			<div class="panel-body">
				
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="res.res.name"/>:</th>
								<td style="padding:2px 2px 2px 2px;">
									<div style="border:1px solid #dcdcdc;border-bottom:none;">
										<table style="width:100%;">
											<c:forEach var="res" items="${msgList}">
												<tr>
													<th>${res.lanMemo} :</th>
													<td><input type="text" value="${res.lanMsg}" readonly="readonly"  class="inputText longInputText"/></td>
												</tr>
											</c:forEach>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="res.res.other"/>:</th>
								<td>${resources.alias}</td>
							</tr>
							
							<tr>
								<th width="20%"><spr:message code="res.res.pic"/>:</th>
								<td>
									<img alt="" src="${resources.icon}">
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="res.default.addr"/>:</th>
								<td>${resources.defaultUrl}</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="res.is.bar"/>:</th>
								<td>
									<c:if test="${resources.isFolder==0}"><spr:message code="no"/></c:if>
									<c:if test="${resources.isFolder==1}"><spr:message code="yes"/></c:if>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="res.view.menu"/>:</th>
								<td>
									<c:if test="${resources.isDisplayInMenu==0}"><spr:message code="no"/></c:if>
									<c:if test="${resources.isDisplayInMenu==1}"><spr:message code="yes"/></c:if>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="res.open.default"/>:</th>
								<td>
									<c:if test="${resources.isOpen==0}"><spr:message code="no"/></c:if>
									<c:if test="${resources.isOpen==1}"><spr:message code="yes"/></c:if>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="res.bro.sort"/>:</th>
								<td>
									${resources.sn}
								</td>
							</tr>
					</table>
				
			</div>
			<c:if test="${resources.isFolder==0}">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="res.res.url"/></span>
					</div>
				</div>
				<div class="panel-body">
					
						<table width="100%" id="resourcesUrlItem" class="table-grid table-list" id="0" cellpadding="1" cellspacing="1">
					   		<tr>
					   			<th width="25%"><spr:message code="res.name"/></th>
					    		<th width="35%">URL</th>
					    		<th width="10%"><spr:message code="res.default.url"/></th>
					    	</tr>
					    	<tbody>
					    	<c:forEach items="${resourcesUrlList}" var="resourcesUrlItem" varStatus="status">
					    		<tr class="${status.index%2==0?'even':'odd'}">
					    			<td style="text-align:left">
					    				${resourcesUrlItem.name}
					    			</td>
					    			<td style="text-align:left">
					    				${resourcesUrlItem.url}
					    			</td>
					    			<td>
					    				<c:if test="${resourcesUrlItem.url==resources.defaultUrl}"><spr:message code="yes"/></c:if>
					    				<c:if test="${resourcesUrlItem.url!=resources.defaultUrl}"><spr:message code="no"/></c:if>
					    			</td>
					    		</tr>
					    	</c:forEach>
					    	
					    	</tbody>
						</table>
						<c:if test="${resourcesUrlList=='[]'}">
					   	 	<div  width="90%">
						    	<spr:message code="res.no.url"/>
						    </div>
						</c:if>
				</div>
			</c:if>
		</div>
	</c:if>
</body>
</html>
