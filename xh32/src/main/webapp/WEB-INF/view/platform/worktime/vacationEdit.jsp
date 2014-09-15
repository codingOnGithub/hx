<%--
	time:2012-02-20 09:25:49
	desc:edit the 法定假期设置
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="vacation.edit.title" /></title>
<script type="text/javascript" src="${ctx}/js/hotent/formdata.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#vacationForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					$('#vacationForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+$lang.operateTip.continueOperate,$lang.tip.msg, function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),$lang.tip.error);
			}
		}
		
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<c:choose>
					<c:when test="${vacation.id !=null}">
						<span class="tbar-label"><spr:message code="vacation.edit.span.edit" /></span>
					</c:when>
					<c:otherwise>
						<span class="tbar-label"><spr:message code="vacation.edit.span.add" /></span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save" /></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back" /></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="vacationForm" method="post" action="save.ht">
				<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
					<tr>
						<th width="20%"><spr:message code="vacation.name" />:</th>
						<td><input type="text" id="name" name="name"
							value="${vacation.name}" validate="{required:true}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="vacation.years" />:</th>
						<td><c:if test="${vacation.years==null}">
								<input type="text" id="years" name="years" value="${year}"
									class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy'})" />
							</c:if> <c:if test="${vacation.years!=null}">
								<input type="text" id="years" name="years"
									value="${vacation.years}" class="Wdate"
									onfocus="WdatePicker({dateFmt:'yyyy'})" />
							</c:if></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="vacation.statTime" />:</th>
						<td><input type="text" id="startTime" name="statTime"
							value="<fmt:formatDate value='${vacation.statTime}' pattern='yyyy-MM-dd'/>"
							class="inputText date" validate="{date:true,dateRangeStart:'endTime'}" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="vacation.endTime" />:</th>
						<td><input type="text" id="endTime" name="endTime"
							value="<fmt:formatDate value='${vacation.endTime}' pattern='yyyy-MM-dd'/>"
							class="inputText date" validate="{date:true,dateRangeEnd:'startTime'}"/></td>
					</tr>
				</table>
				<input type="hidden" name="id" value="${vacation.id}" />
			</form>

		</div>
	</div>
</body>
</html>
