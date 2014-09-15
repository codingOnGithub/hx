<%--
	time:2012-02-23 17:43:35
	desc:edit the 组织或人员参数属性
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="menu.button.edit"/><spr:message code="sysUoParam.title"/></title>
	
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/Share.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysParam"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${sysParam.paramId ==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#sysParamForm').submit(); 
			});
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysParam.paramId !=null}">
			            <span class="tbar-label"><spr:message code="menu.button.edit"/><spr:message code="sysUoParam.title"/></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="menu.button.add"/><spr:message code="sysUoParam.title"/></span>
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
			
				<form id="sysParamForm" method="post" action="save.ht">
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="sysParam.paramName"/>: </th>
							<td><input type="text" id="paramName" name="paramName" value="${sysParam.paramName}"  class="inputText" onblur="Share.getKeyName(this,paramKey)"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="sysParam.paramKey"/>: </th>
							<td><input type="text" id="paramKey" name="paramKey" value="${sysParam.paramKey}"  class="inputText"/></td>
						</tr>
						
						<tr>
							<th width="20%"><spr:message code="sysParam.type"/>: </th>
							<td>
								<select id="effect" name="effect" >
									<option value="1" <c:if test="${sysParam.effect==1}">selected="selected"</c:if>><spr:message code="sysParam.userParam"/></option>
									<option value="2" <c:if test="${sysParam.effect==2}">selected="selected"</c:if>><spr:message code="sysParam.orgParam"/></option>
								</select>
							</td>
						</tr>
						
						<tr>
							<th width="20%"><spr:message code="sysParam.dataType"/>: </th>
							<td>
								<select id="dataType" name="dataType" >
									<c:forEach items="${dataTypeMap}" var="t">
										<option value="${t.key }" <c:if test="${sysParam.dataType==t.key}">selected="selected"</c:if>>${t.value }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					<input type="hidden" name="paramId" value="${sysParam.paramId}" />
				</form>
			
		</div>
</div>
</body>
</html>
