<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<%@include file="/commons/include/getById.jsp" %>
	<f:js pre="js/lang/view/platform/system"></f:js>
	<title><spr:message code="desktopMycolumn.title"/></title>
<%--     <link href="${ctx}/js/desktop/inettuts.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/js/desktop/inettuts.js.css" rel="stylesheet" type="text/css" /> --%>
    <f:link href="desktop/inettuts.css"></f:link>
    <f:link href="desktop/inettuts.js.css"></f:link>
    <script type="text/javascript" src="${ctx}/js/lg/plugins/ligerMessageBox.js"></script>  
    <script type="text/javascript" src="${ctx}/js/lg/plugins/htButtons.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/desktop/desktopShow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/desktop/desktopTemplete.js" ></script>
	<script type="text/javascript">
		var nums=1; 
		var nonactivated = 'nonactivated';
		var active = 'active';
		//保存布局
		function save(){
			var layoutID=${desktopLayoutmap["id"]};//栏目ID
			var layoutCols=${desktopLayoutmap["cols"]};//栏目列数
			com.hotent.platform.desktop.saveCol(layoutID,"desktopMycolumn",layoutCols);
		}
		$(function () { 
			var desktop="${desktop}";
			var columnId=${desktopLayoutmap["id"]};//栏目ID
			var cols=${desktopLayoutmap["cols"]};//栏目列数
			var width='${desktopLayoutmap["widths"]}';//栏目宽度
			new com.hotent.platform.desktop.myTemp(cols,columnId,width,1,desktop);
        	$("span.htbtn").htButtons();
        	$('#itemColumn1').val(0);
        	$('#itemColumn2').val(0);
        	$('#itemColumn3').val(0);
	   	}); 
	</script>
</head>
<body> 
	<input name="itemColumn1" id="itemColumn1" type="hidden" value="0"/> 
	<input name="itemColumn2" id="itemColumn2" type="hidden" value="0"/> 
	<input name="itemColumn3" id="itemColumn3" type="hidden" value="0"/> 
	<input name="itemcols" id="itemcols" type="hidden" value="0"/> 
	<div class="hide-panel">
	<div class="panel-top">
		<div class="tbar-title">
			<span class="tbar-label"><spr:message code="desktopMycolumn.title"/></span>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link save" id="save" href="#" onclick="save()"><span></span><spr:message code="desktopMycolumn.save"/></a>  
				</div>
				<div class="l-bar-separator"></div>				
				<div class="group">
					<a class="link back" href="../desktopMycolumn/show.ht"><span></span><spr:message code="menu.button.back"/></a>
				</div>
				<div class="l-bar-separator"></div>				
				<div class="group">
					<a class="link undo" href="../desktopMycolumn/news.ht?id=${desktopLayoutmap.id}"><span></span><spr:message code="desktopMycolumn.reset"/></a>
				</div>
				<div class="l-bar-separator"></div>	
				<div class="group">
					<table ><tr width="50%"><td>
						&nbsp;&nbsp;<span class="label"><spr:message code="desktopMycolumn.news.tip"/>:</span>
						<select id="columnName" name="columnName">
							<c:forEach items="${desktopColumnmap}" var="t">
								<option value="${t.key}" title="${t.value}">${t.value}</option>
							</c:forEach>
						</td><td>
						</select>&nbsp;&nbsp;<span class="htbtn" id="addThisColumn" onclick="addThisColumn('${desktop}')">&nbsp;&nbsp;<spr:message code="desktopMycolumn.news.addColumn"/></span>
					</td></tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div id="colshtml"></div>
	<div id="columnss">
        <ul id="column1" class="column">
            <li class="widget color-green" id="intro">
            </li>
            <li class="widget color-red">  
            </li>
        </ul>
        <ul id="column2" class="column">
            <li class="widget color-blue"></li>
            <li class="widget color-yellow"></li>
        </ul>
    </div>
</body>
</html>
