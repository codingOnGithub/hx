
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp"%>
	<title><spr:message code="sysTypeKey"/><spr:message code="title.list"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript">
		$(function(){
			$("tr.odd,tr.even").unbind("hover");
			$("#sysTypeKeyItem").delegate("tbody>tr", "click", function() {
				$("#sysTypeKeyItem>tbody>tr").removeClass("over");
				$(this).addClass("over");
			});
			var hasChange=false;
			$("#moveupField").click(function(){
				move(true);
				hasChange=true;
			});
			
			$("#movedownField").click(function(){
				move(false);
				hasChange=true;
			});
			
			$("#saveSeqence").click(function(){
				if(!hasChange){
					$.ligerDialog.warn($lang_system.sysTypeKey.list.warn_msg_no_sort,$lang.tip.warn);
					return;
				}
				var aryTypeId=new Array();
				$("input[name='typeId']").each(function(i,obj){
					aryTypeId.push(obj.value);
				});
				var ids=aryTypeId.join(",");
				$.post("saveSequence.ht",{ids:ids},function(data){
					var obj=new com.hotent.form.ResultMessage(data);
					if(obj.isSuccess()){
						$.ligerDialog.success(obj.getMessage(),$lang.tip.msg,function(){
							location.reload();
						});
					}
					else{
						$.ligerDialog.err($lang.tip.errorMsg,$lang_system.sysTypeKey.list.error_msg_save,obj.getMessage());
					}
				});
			});
		});
		
		
		/**
		 * 移动行
		 */
		 function move(direct){
			var objTr=$("#sysTypeKeyItem>tbody .over");
			if(objTr.length==0) return;
			
			if(direct){
				var prevObj=objTr.prev();
				if(prevObj.length>0){
					objTr.insertBefore(prevObj);	
				}
			}
			else{
				var nextObj=objTr.next();
				if(nextObj.length>0){
					objTr.insertAfter(nextObj);
				}
			}
		};
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysTypeKey"/><spr:message code="title.list"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="moveupField" class="link moveup"><span></span><spr:message code="menu.button.moveUp"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="movedownField" class="link movedown"><span></span><spr:message code="menu.button.moveDown"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="saveSeqence" class="link save"><span></span><spr:message code="menu.button.saveSort"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link comment" href="${ctx}/platform/bpm/bpmFormLanguage/typeList.ht"><span></span><spr:message code="menu.button.international"/></a></div>

				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="sysTypeKey.typeName"/>:</span><input type="text" name="Q_typeName_SL" class="inputText" value="${param['Q_typeName_SL']}"/></li>
						<li><span class="label"><spr:message code="sysTypeKey.typeKey"/>:</span><input type="text"
							name="Q_typeKey_SL" class="inputText" value="${param['Q_typeKey_SL']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
					
				<display:table name="sysTypeKeyList" id="sysTypeKeyItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
					<display:column titleKey="sysTypeKey.sn" media="html" style="text-align:center;width:30px;" >
						${sysTypeKeyItem_rowNum}
						<input type="hidden" name="typeId" value="${sysTypeKeyItem.typeId}"/>
					</display:column>
					<display:column property="typeName" titleKey="sysTypeKey.typeName" sortable="true" sortName="typeName"></display:column>
					<display:column property="typeKey" titleKey="sysTypeKey.typeKey" sortable="true" sortName="typeKey"></display:column>
					<display:column titleKey="sysTypeKey.flag" sortable="true" sortName="flag" style="text-align:center;">
						<c:choose>
							<c:when test='${sysTypeKeyItem.flag ==1}'>
						 		<span class="red"><spr:message code="sysTypeKey.flag.system"/></span>
					  	 	</c:when>
						  	 <c:otherwise>
							 	
							 	<span class="green"><spr:message code="sysTypeKey.flag.custom"/></span>
						  	 </c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="sysTypeKey.type" sortable="true" sortName="flag" style="text-align:center;">
						<c:choose>
							<c:when test="${sysTypeKeyItem.type==0}">
								<div title="<spr:message code='sysTypeKey.type.tile'/>" class="icon-list">&nbsp;</div>
						   	</c:when>
					       	<c:otherwise>
						        <div title="<spr:message code='sysTypeKey.type.tree'/>" class="icon-tree">&nbsp;</div>
						   	</c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
					<c:choose>
					  <c:when test='${sysTypeKeyItem.flag ==0}'>
						 <f:a alias="delKey" href="del.ht?typeId=${sysTypeKeyItem.typeId}" css="link del"><spr:message code="operator.del"/></f:a>
					     <a href="edit.ht?typeId=${sysTypeKeyItem.typeId}" class="link edit"><spr:message code="operator.edit"/></a>
					  </c:when>
					</c:choose>
					</display:column>
				</display:table>
		</div>
		<!-- end of panel-body -->
	</div>
	<!-- end of panel -->
</body>
</html>


