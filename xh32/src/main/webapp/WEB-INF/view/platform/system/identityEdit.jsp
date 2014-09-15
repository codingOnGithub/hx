<%--
	time:2012-02-03 14:40:59
	desc:edit the 流水号生成
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%  String islist=request.getParameter("islist");%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="operator.edit"/><spr:message code="identity.title"/></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=identity"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			valid(showRequest,showResponse);
			$("a.save").click(function() {
				$('#identityForm').submit(); 
			});
			function showResponse(responseText)  { 
				var obj=new com.hotent.form.ResultMessage(responseText);
				if(obj.isSuccess()){//成功
					$.ligerDialog.confirm(obj.getMessage()+","+$lang.operateTip.continueOp,$lang.tip.msg,function(rtn){
						if(!rtn){
							var returnUrl=$("#returnUrl").val();
							if($("#returnUrl").length>0 && returnUrl!=""){
								location.href=returnUrl;
								return;
							}
							var linkBack=$("a.back");
							if(linkBack.length>0){
								var returnUrl=linkBack.attr("href");
								if(returnUrl!=""){
									location.href=returnUrl;
									return;
								}
							}
						}
						else{
							if(${identity.id==0}){
								__valid.resetForm();
							}
						}
					});
					
			    }else{//失败
			    	$.ligerDialog.error( obj.getMessage(),$lang.tip.errorMsg);
			    }
			} 
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
					<c:choose>
						<c:when test="${identity.id == 0}">
							<spr:message code="operator.add"/><spr:message code="identity.title"/>
						</c:when>
						<c:otherwise>
							<spr:message code="operator.edit"/><spr:message code="identity.title"/>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<c:choose>
					<c:when test="${islist=='1'}">
					<div class="group"><a class="link back" href="showlist.ht"><span></span><spr:message code="menu.button.back"/></a></div>
					</c:when>
					<c:otherwise>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="identityForm" method="post" action="save.ht">
					
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="identity.name"/>: </th>
								<td><input type="text" id="name" name="name" value="${identity.name}"  class="inputText longInputText"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="identity.alias"/>: </th>
								<td><input type="text" id="alias" name="alias" value="${identity.alias}"  class="inputText longInputText"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="identity.rule"/>: </th>
								<td>
									
									<c:choose>
										<c:when test="${identity.id == 0}">
											<input type="text" id="rule" name="rule" size="80" value="{yyyy}{MM}{dd}{NO}"  class="inputText longInputText"/>
										</c:when>
										<c:otherwise>
											<input type="text" id="rule" name="rule" size="80" value="${identity.rule}"  class="inputText longInputText"/>
										</c:otherwise>
									</c:choose>
									<br>
									<spr:message code="identity.rule.patten"/>
									<ul>
										<li><spr:message code="identity.rule.patten.year"/></li>
										<li><spr:message code="identity.rule.patten.month.complement"/></li>
										<li><spr:message code="identity.rule.patten.month.uncomplement"/></li>
										<li><spr:message code="identity.rule.patten.date.complement"/></li>
										<li><spr:message code="identity.rule.patten.date.uncomplement"/></li>
										<li><spr:message code="identity.rule.patten.no"/></li>
										<li><spr:message code="identity.rule.patten.unno"/></li>
									</ul>
									
									
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="identity.genType"/>: </th>
								<td>
									<c:choose>
										<c:when test="${identity.id == 0}">
											<input type="radio"  name="genType" checked="checked" value="1"  /><spr:message code="identity.genType.date"/>
											<input type="radio"  name="genType"  value="2"  /><spr:message code="identity.genType.month"/>
											<input type="radio"  name="genType"  value="3"  /><spr:message code="identity.genType.year"/>
											<input type="radio"  name="genType"  value="0"  /><spr:message code="identity.genType.increase"/>
										</c:when>
										<c:otherwise>
											<input type="radio"  name="genType" <c:if test="${identity.genType==1}">checked="checked"</c:if>  value="1" /><spr:message code="identity.genType.date"/>
											<input type="radio"  name="genType" <c:if test="${identity.genType==2}">checked="checked"</c:if>  value="2" /><spr:message code="identity.genType.month"/>
											<input type="radio"  name="genType" <c:if test="${identity.genType==3}">checked="checked"</c:if>  value="3" /><spr:message code="identity.genType.year"/>
											<input type="radio"  name="genType" <c:if test="${identity.genType==0}">checked="checked"</c:if>  value="0" /><spr:message code="identity.genType.increase"/>
											
										</c:otherwise>
									</c:choose>
									
									<br>
									<spr:message code="identity.title"/><spr:message code="identity.rule"/>：
									<ul>
										<li><spr:message code="identity.rule.frequency.everydate"/></li>
										<li><spr:message code="identity.rule.frequency.sequence"/></li>
									</ul>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="identity.noLength"/>: </th>
								
								<td>
									<c:choose>
										<c:when test="${identity.id == 0}">
											<input type="text" id="noLength" name="noLength" value="5"  class="inputText"/>
										</c:when>
										<c:otherwise>
											<input type="text" id="noLength" name="noLength" value="${identity.noLength}"  class="inputText"/>
										</c:otherwise>
									</c:choose>
									<br>
									<spr:message code="identity.noLength.desc"/>
									<spr:message code="identity.noLength.descno"/>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="identity.initValue"/>: </th>
								<td>
									<c:choose>
										<c:when test="${identity.id == 0}">
											<input type="text" id="initValue" name="initValue" value="1"  class="inputText"/>
										</c:when>
										<c:otherwise>
											<input type="text" id="initValue" name="initValue" value="${identity.initValue}"  class="inputText"/>
										</c:otherwise>
									</c:choose>
									
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="identity.step"/>: </th>
								<td>
									<c:choose>
										<c:when test="${identity.id == 0}">
											<input type="text" id="step" name="step" value="1"  class="inputText"/>
										</c:when>
										<c:otherwise>
											<input type="text" id="step" name="step" value="${identity.step}"  class="inputText"/>
										</c:otherwise>
									</c:choose>
									<br>
									<spr:message code="identity.step.desc"/>
								</td>
							</tr>
							
						</table>
						<input type="hidden" name="id" value="${identity.id}" />
					
				</form>
		</div>
</div>
</body>
</html>
