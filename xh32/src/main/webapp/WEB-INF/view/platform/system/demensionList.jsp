<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

	<%@include file="/commons/include/get.jsp" %>
		<title><spr:message code="demension.title"/><spr:message code="title.manage"/></title>
	<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerWindow.js" ></script>
	<script type="text/javascript" >
		function openDialog(demId){
			var url="${ctx }/platform/system/sysOrgType/list.ht?demId="+demId;
			var winArgs="dialogWidth=800px;dialogHeight=540px;help=0;status=0;scroll=1;center=1;resizable=1;";
			url=url.getNewUrl();	
			var rtn=window.showModalDialog(url,null,winArgs);
		}
		
	</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="demension.title"/><spr:message code="title.manageList"/>
						</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add"  href="edit.ht?demId=0"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label"><spr:message code="demension.demName"/>:</span><input type="text" name="Q_demName_SL"  class="inputText" value="${param['Q_demName_SL']}"/></li>
											
												<li><span class="label"><spr:message code="demension.demDesc"/>:</span><input type="text" name="Q_demDesc_SL"  class="inputText" value="${param['Q_demDesc_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="demensionList" id="demensionItem" requestURI="list.ht" 
					    	sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
								  	<input type="checkbox" class="pk" name="demId" value="${demensionItem.demId}" ${demensionItem.demId==1?"disabled='disabled'":""}>
							</display:column>
							<display:column property="demName" titleKey="demension.demName" sortable="true" sortName="demName" style="text-align:left"></display:column>
							<display:column property="demDesc" titleKey="demension.demDesc" sortable="true" sortName="demDesc" style="text-align:left"></display:column>
							<display:column titleKey="list.manage" media="html" style="width:180px;white-space:nowrap;" >
							
							<c:choose>
							<c:when test='${demensionItem.demId eq 1}'>
							<a href=" ${ctx }/platform/system/sysOrgType/list.ht?demId=${demensionItem.demId}"   class="link setting"><spr:message code="demension.typemanege"/></a>
							</c:when>
							<c:otherwise>
							<a href='del.ht?demId=${demensionItem.demId}'  class='link del'><spr:message code="menu.button.del"/></a>
							<a href='edit.ht?demId=${demensionItem.demId}' class="link edit"><spr:message code="menu.button.edit"/></a>
							<a href="${ctx }/platform/system/sysOrgType/list.ht?demId=${demensionItem.demId}"   class="link setting"><spr:message code="demension.typemanege"/></a>
							</c:otherwise>
							</c:choose>
								
							</display:column>
						</display:table>
						<hotent:paging tableId="demensionItem"/>
					
				</div>			
			</div>
</body>
</html>


