<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title><spr:message code="desktopMycolumn.title"/></title>
	<%@include file="/commons/include/get.jsp" %>
<%--     <link href="${ctx}/js/desktop/inettuts.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/js/desktop/inettuts.js.css" rel="stylesheet" type="text/css" /> --%>
    <f:js pre="js/lang/view/platform/system"></f:js>
    <f:link href="desktop/inettuts.css"></f:link>
    <f:link href="desktop/inettuts.js.css"></f:link>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/desktop/desktopTemplete.js" ></script>
	<script type="text/javascript"> 
		//保存布局
		function save(){
			var layoutID=${desktopLayoutmap["id"]};//栏目ID
			var layoutCols=${desktopLayoutmap["cols"]};//栏目列数
			com.hotent.platform.desktop.saveCol(layoutID,"desktopMycolumn",layoutCols);
		}
		$(function () {
			var cols=${desktopLayoutmap["cols"]};//栏目列数			
			if(cols==null)window.location.href="warm.ht";
			var desktop="${desktop}";
			var columnId=${desktopLayoutmap["id"]};//栏目ID
			var width='${desktopLayoutmap["widths"]}';//栏目宽度
			new com.hotent.platform.desktop.myTemp(cols,columnId,width,0,desktop);
	   	}); 
	</script>
</head>
<body> 
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
					<a class="link edit" id="change" href="#"><span></span><spr:message code="desktopMycolumn.edit"/></a>
				</div>
				<div class="group">
				   <span><spr:message code="desktopMycolumn.show.tip"/></span>
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
