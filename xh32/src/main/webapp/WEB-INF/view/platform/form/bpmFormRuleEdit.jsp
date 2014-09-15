<%--
	time:2012-01-11 10:53:15
	desc:edit the 表单验证规则
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="operator.edit"/> <spr:message code="bpmFormRule.title"/></title>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerDialog.js"></script>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=bpmFormRule"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${bpmFormRule.id==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#bpmFormRuleForm').submit(); 
			});
			
			handvVlidRule();
		});
		
		function testReg(rule,toValid){
			var reg= new RegExp(rule);
			var rtn= reg.test(toValid);
			return rtn;
		}
		
		function handvVlidRule(){
			$("#btnValid").click(function(){
				var rule=$('#rule').val();
				var toValid=$('#toValid').val();
				var rtn=testReg(rule,toValid);
				if (rtn){
					$.ligerDialog.success('<p><font color="green"><spr:message code="bpmFormRule.checkRule.success"/><br></font></p>');
				}else{
					$.ligerDialog.error('<p><font color="red"><spr:message code="bpmFormRule.checkRule.failure"/><br></font></p>');
				}
			});
		}
		
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">
				<c:if test="${bpmFormRule.id==null }"><spr:message code="operator.add"/> <spr:message code="bpmFormRule.title"/></c:if>
				<c:if test="${bpmFormRule.id!=null }"><spr:message code="operator.edit"/> <spr:message code="bpmFormRule.title"/></c:if>
			    </span>
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
				<form id="bpmFormRuleForm" method="post" action="save.ht">
				
						<table class="table-detail"  width="60%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="bpmFormRule.name"/>: </th>
								<td><input size="60%" type="text" id="name" name="name" value="${bpmFormRule.name}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmFormRule.rule"/>: </th>
								<td >
								<input size="60%" id="rule" name="rule" value="${bpmFormRule.rule}" class="inputText"/>
								</tr><tr>
								<th><spr:message code="bpmFormRule.toValid"/>:</th>
								<td><input size="60%"  type="text"  id="toValid"  name="toValid" class="inputText" >
								<a class="button" id="btnValid"   ><span class="icon valid"></span><span><spr:message code="bpmFormRule.checkRule"/></span></a></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmFormRule.tipInfo"/>: </th>
								<td>
								<input size="60%" id="tipInfo" name="tipInfo" value="${bpmFormRule.tipInfo}" class="inputText"/>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="bpmFormRule.memo"/>: </th>
								<td>
								<textarea rows="4" cols="30" id="memo" name="memo" style="width:60%">${bpmFormRule.memo}</textarea>
							</tr>
						</table>
						<input type="hidden" name="id" value="${bpmFormRule.id}" />
					
				</form>
		</div>
</div>
</body>
</html>
