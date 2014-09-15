
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/system" ></f:js>
<title><spr:message code="userUnder.title"/><spr:message code="title.manage"/></title>
<script type="text/javascript" src="${ctx }/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript">
	$(function() {
		$('#reload').attr('href',window.location.href);
		$("div.group > a.link.primary").click(function()
			{	
				var conf={};				
				var url=__ctx + "/platform/system/userUnder/myLeaders.ht";
				conf.url=url;
				var dialogWidth=550;
				var dialogHeight=250;
				conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
				var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
					+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;				
				var rtn=window.showModalDialog(url,"",winArgs);			
			});
	});
	function dlgCallBack(userIds, fullnames) {
		if (userIds.length > 0) {
			var userId=$("#userId").val();
			var id = userIds.split(",");
			for(var i=0;i<id.length;i++){
				if(id[i]==userId){
					alert($lang_system.sysUser.userUnder);
					return false;
				}
			}
			$.post("addUnderUser.ht",
				   {userIds: userIds, userNames:fullnames,userId:userId},
					function(response){
						reload.click();
					 });			
		}
	};

	function add() {
		UserDialog({
			callback : dlgCallBack,
			isSingle : false
		});
	}
	
	
</script>
</head>
  <base target="_self">
<body>
    <a id="reload"  href="<spr:message code='userUnder.reload'/>" style="display:none"></a>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="userUnder.title"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>					
					<div class="group">
						<f:a alias="addUnder" css="link add"  href="javascript:add();"><span></span><spr:message code="menu.button.add"/></f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
					<c:if test="${showMyleader}"><div class="group"><a class="link primary" ><span></span><spr:message code="userUnder.leader"/></a></div></c:if>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht?userId=${userId}">
							<ul class="row">									
								<li><span class="label"><spr:message code="userUnder.underusername"/>:</span><input type="text" name="Q_underusername_SL"  class="inputText" value="${param['Q_underusername_SL']}"/>	</li>										
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="userUnderList" id="userUnderItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="id" value="${userUnderItem.id}">
					</display:column>
					
					<display:column property="underusername" titleKey="userUnder.underusername" sortable="true" sortName="underusername"></display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
						<a href="del.ht?id=${userUnderItem.id}" class="link del"><spr:message code="menu.button.del"/></a>
						
					</display:column>
				</display:table>
				<hotent:paging tableId="userUnderItem" showExplain="false"/>
				<input type="hidden" id="userId" name="userId" value="${userId}" />
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


