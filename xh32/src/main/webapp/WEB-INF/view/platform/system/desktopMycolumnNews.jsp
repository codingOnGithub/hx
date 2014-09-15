<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp"%>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title><spr:message code="desktopMycolumn.title"/><spr:message code="menu.button.setting"/></title>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMessageBox.js"></script>
	<f:link href="jquery-ui-portlet/jquery-ui-1.8.5.custom.css"></f:link>
	<f:link href="jquery-ui-portlet/jquery.portlet.css"></f:link>
    <script src="${ctx}/js/jquery-ui-portlet/lib/jquery-ui-1.8.14.custom.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery-ui-portlet/script/jquery.portlet.js"></script>
    <script type="text/javascript" src="${ctx}/js/util/easyTemplate.js "></script>
   	<script type="text/javascript" src="${ctx}/js/hotent/platform/desktop/desktopManage.js" ></script>
	<script type="text/javascript">	
	var nonactivated = 'nonactivated';
	var active = 'active';	
	$(function(){
		var desktop=new DesktopManage();
		desktop.init();
		$(".ui-sortable").live("click", function() {
			desktop.changeClass(this);
		});
		$("#addColumn").click(function(){
			desktop.addColumn();
		});
		$("#changeLayout").click(function(){
			desktop.changLayout();
		});
		$("#save").click(function(){
			desktop.saveLayout();
		});
	});
	
	</script>
<style type="text/css">
.nonactivated {
	background-color: #FFFFFF;
	
}
.active {
	background-color: #FFA500;
}
.row select {
margin-top: 0;
}
</style>
</head>
<body>
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="desktopMycolumn.title"/><spr:message code="menu.button.setting"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="save" href="#" ><span></span><spr:message code="desktopMycolumn.save"/></a>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-detail">
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td>
					<div class="row" style="margin-top: 10px; margin-left: 0px;">
						<ul>
							<li style="float: left;"><span class="label"><spr:message code="desktopMycolumn.news.tip"/>:</span></li>
							<li style="float: left;"><select id="columnName"
								name="columnName">
									<c:forEach items="${desktopColumnList}" var="t">
										<option value="${t.id}" name="${t.columnName}" moreUrl="${t.columnUrl}">${t.columnName}</option>
									</c:forEach>
							</select>&nbsp;&nbsp;
							<a class="button" id="addColumn"><span><spr:message code="desktopMycolumn.news.addColumn"/></span></a></li> 							
							<li style="float: left;">&nbsp;&nbsp;<span class="label"><spr:message code="desktopMycolumn.news.selectLayout"/>:</span>								
								<select id="colsSelect" name="cols">
									<c:forEach items="${desktopLayout}" var="t">
										<option value="${t.id}" cols="${t.cols}" widthStr="${t.width}"<c:if test="${desktopLayoutmap.cols==t.cols}">selected="selected"</c:if>  >${t.name}</option>
									</c:forEach>
							</select>
							</li>
							<li style="float: left;"><a class="button" id="changeLayout"><span><spr:message code="desktopMycolumn.news.changeLayout"/></span></a></li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="panel-body">
		<div id="myPage"></div>
	</div>
</body>
</html>