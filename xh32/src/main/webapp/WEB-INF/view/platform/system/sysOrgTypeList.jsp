<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysOrgType.title"/><spr:message code="title.manage"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript">
		function sortTr(obj,isUp) {
			var thisTr = $(obj).parents("tr");
			if(isUp){
				var prevTr = $(thisTr).prev();
				if(prevTr.length>0){
					thisTr.insertBefore(prevTr);
				}
			}
			else{
				var nextTr = $(thisTr).next();
				if(nextTr.length>0){				
					thisTr.insertAfter(nextTr);
				}
			}
		};
		
		function save(){
			var ids='';
			var params='';
			$(':checkbox').each(function(){			
				ids+=$(this).val()+",";				
			});
			if(ids.length<1){		
				this.close();
				return;
			}
			$.post('${ctx }/platform/system/sysOrgType/multSave.ht',{'ids':ids,'params':params},function(responseText){
							var obj = new com.hotent.form.ResultMessage(responseText);
							if (obj.isSuccess()) {
								$.ligerDialog.confirm( obj.getMessage()+","+$lang.operateTip.continueOp,$lang.tip.msg, function(rtn) {
									if(rtn){
										window.location.reload(true);
									}else{
										window.location.href = "${ctx}/platform/system/demension/list.ht";
									}
								});
							} else {
								$.ligerDialog.err($lang.tip.error,$lang_system.sysOrg.sysOrgType.saveFail,obj.getMessage());
							}
			});
		}
	</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysOrgType.title"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="btnSave"  href="javascript:save()"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add"  href="edit.ht?demId=${demId}"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>			
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href=" ${ctx}/platform/system/demension/list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>	
			</div>
			
		</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysOrgTypeList" id="sysOrgTypeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysOrgTypeItem.id}">
				</display:column>
				
				<display:column property="name" titleKey="sysOrgType.name" sortable="true" sortName="name"></display:column>
				<display:column property="levels" titleKey="sysOrgType.levels" sortable="true" sortName="levels"></display:column>
				<display:column property="memo" titleKey="sysOrgType.memo" sortable="true" sortName="memo"></display:column>
				<display:column titleKey="list.manage" media="html" style="width:180px">
					<a alt='<spr:message code="menu.button.moveUp"/>' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;&nbsp;</a>
					<a alt='<spr:message code="menu.button.moveDown"/>' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;&nbsp;</a>
					<a href="del.ht?id=${sysOrgTypeItem.id}" class="link del"><spr:message code="menu.button.del"/></a>
					<a href="edit.ht?id=${sysOrgTypeItem.id}" class="link edit"><spr:message code="menu.button.edit"/></a>
				</display:column>
			</display:table>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


