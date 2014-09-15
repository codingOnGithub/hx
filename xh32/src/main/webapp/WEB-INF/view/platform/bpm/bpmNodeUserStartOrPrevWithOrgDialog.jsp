<%@page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
	<head>
		<%@include file="/commons/include/form.jsp" %>
		<title><spr:message code="bpmNodeUser.startOrPrev.title"/></title>
		<f:js pre="js/lang/view/platform/bpm" ></f:js>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/hotent/platform/form/CommonDialog.js"></script>
		<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
		<script type="text/javascript">
			$(function(){
			
			});
			
			function save(){
				var objUserType=$("[name='userType']:checked");
				var objType=$("[name='type']:checked");
				if(objType.length==0){
					$.ligerDialog.warn($lang_bpm.startOrPrev.selectOrg,$lang.tip.msg);
					return ;
				}
				if(objUserType.length==0){
					$.ligerDialog.warn($lang_bpm.startOrPrev.selectUser,$lang.tip.msg);
					return ;
				}
				var varType=objType.val();
				var varTypeName=objType.attr("memo");
				var varUserType=objUserType.val();
				var varUserTypeName=objUserType.attr("memo");
				
				var obj={};
			
				obj.json="{userType:\""+varUserType +"\",type:\""+varType+"\"}";
				obj.description=$lang.operateType.and+varUserTypeName +$lang_bpm.startOrPrev.has+varTypeName;
				window.returnValue= obj;
				window.close();
			}
			
		</script>
	</head>
	
<body style="overflow: hidden;">
	<div class="panel">
		<div class="hide-panel">
			<div class="panel-top">
				<div class="tbar-title">
				    <span class="tbar-label"><spr:message code="bpmNodeUser.startOrPrev.userFormVar"/></span>
				</div>
				<div class="panel-toolbar">
					<div class="toolBar">
						<div class="group"><a class="link save" id="dataFormSave" onclick="save();" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link close" onclick="window.close()" href="#"><span></span><spr:message code="menu.button.close"/></a></div>
					</div>
				</div>
			</div>
		</div>
		<div style="text-align: center;padding-top: 10px;">
			
				<table class="table-grid" width="90%">
				 
					<tr>
						<td><spr:message code="bpmNodeUser.startOrPrev.userType"/></td>
						<td align="left">
				            <input type="radio" name="userType" value="start" memo="<spr:message code='bpmNodeUser.startOrPrev.sponsor'/>" <c:if test="${userType eq 'start'}">checked="checked"</c:if> />
				                                     <spr:message code="bpmNodeUser.startOrPrev.sponsor"/>
				            <input type="radio" name="userType" value="prev"  memo="<spr:message code='bpmNodeUser.startOrPrev.preViewUser'/>" <c:if test="${userType eq 'prev'}">checked="checked"</c:if> />
				                                  <spr:message code="bpmNodeUser.startOrPrev.preViewUser"/> 
						</td> 
					</tr>
					
					<tr>
						<td><spr:message code="bpmNodeUser.startOrPrev.orgType"/></td>
						<td align="left">
				            <input type="radio" name="type" value="org" memo="<spr:message code='bpmNodeUser.startOrPrev.sameOrg'/>" <c:if test="${type eq 'org'}">checked="checked"</c:if> />
                                                                                     <spr:message code="bpmNodeUser.startOrPrev.sameOrg"/>
                            <input type="radio" name="type" value="job"  memo="<spr:message code='bpmNodeUser.startOrPrev.sameJob'/>" <c:if test="${type eq 'job'}">checked="checked"</c:if> />
                                                                                    <spr:message code="bpmNodeUser.startOrPrev.sameJob"/>
                            <input type="radio" name="type" value="pos"  memo="<spr:message code='bpmNodeUser.startOrPrev.samePos'/>" <c:if test="${type eq 'pos'}">checked="checked"</c:if> />
                                                                                  <spr:message code="bpmNodeUser.startOrPrev.samePos"/> 
						</td> 
					</tr>
					
					
					
				</table>	
			
			
		</div>
		
	</div>
</body>
</html>