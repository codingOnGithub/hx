
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="outMail.mailbox"/><spr:message code="title.manage"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript">
	function test(id){
		$.ligerDialog.waitting($lang_system.outMail.wail_link);
		var param={id:id};
		$.post("test.ht",param,function(data){
			$.ligerDialog.closeWaitting();
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){//成功
				$.ligerDialog.success($lang_system.outMail.email_success);
		    }else{//失败
		    	$.ligerDialog.error(obj.getMessage());
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
						<span class="tbar-label"><spr:message code="outMail.mailbox"/><spr:message code="title.manageList"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add" href="edit.ht?"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
						</div>	
					</div>
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label"><spr:message code="outMail.accountName"/>:</span><input type="text" name="Q_userName_SL"  class="inputText" value="${param['Q_userName_SL']}"/></li>
												<li><span class="label"><spr:message code="outMail.emailAddr"/>:</span><input type="text" name="Q_mailAddress_SL"  class="inputText" value="${param['Q_mailAddress_SL']}"/></li>
									</ul>
							</form>
					</div>
				</div>
				</div>
				<div class="panel-body">
					
					
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="outMailUserSetingList" id="outMailUserSetingItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="id" value="${outMailUserSetingItem.id}">
							</display:column>
							<display:column property="userName" titleKey="outMail.accountName" sortable="true" sortName="userName"></display:column>
							<display:column property="mailAddress" titleKey="outMail.emailAddr" sortable="true" sortName="mailAddress"></display:column>
							<display:column titleKey="outMail.isDefault" sortable="true" sortName="isDefault">
								<c:choose>
								 	<c:when test="${outMailUserSetingItem.isDefault==1}"><span class="green"><spr:message code="yes"/></span></c:when>
								 	<c:otherwise>
								 		<span class="red"><spr:message code="no"/></span>
								 	</c:otherwise>
								</c:choose>
							</display:column>
						
							<display:column titleKey="list.manage" media="html" style="width:50px;text-align:center" class="rowOps">
								<c:choose>
									<c:when test="${outMailUserSetingItem.isDefault==1}">
										<div style="float: left"><a class="link setting " style="color:gray;"><spr:message code="menu.button.setDefault"/></a></div>
										<div style="float: left"><a  style="color:gray;" class="link del"><spr:message code="menu.button.del"/></a></div>
									</c:when>
									<c:otherwise>
									<a style="float: left" class="link setting" href="setDefault.ht?id=${outMailUserSetingItem.id}"><span ><spr:message code="menu.button.setDefault"/></span></a>
									<a style="float: left" href="del.ht?id=${outMailUserSetingItem.id}" class="link del"><spr:message code="menu.button.del"/></a>
									</c:otherwise>
								</c:choose>
								<a style="float: left" href="edit.ht?id=${outMailUserSetingItem.id}" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a style="float: left" href="get.ht?id=${outMailUserSetingItem.id}" class="link detail"><spr:message code="menu.button.detail"/></a>
								<a style="float: left" href="#" onclick="test(${outMailUserSetingItem.id})" class="link test"><spr:message code="menu.button.test"/></a>
								<input type="hidden" id="outMailUserSetingId"value="${outMailUserSetingItem.id}">
							</display:column>
							
						</display:table>
						<hotent:paging tableId="outMailUserSetingItem"/>
					
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


