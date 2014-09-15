<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="WebService.list"/><spr:message code="operator.manageList"/></title>
<script type="text/javascript">
	var win;
	function test(setId){
		var url=__ctx+'/platform/bpmCommonWsSet/bpmCommonWsSet/test.ht?setId='+setId;
		var winArgs="dialogWidth=840px;dialogHeight=600px;help=0;status=0;scroll=1;center=1";
		window.showModalDialog(url,"",winArgs);
	};
	
	function developHelp(){
		if(!win){
			var obj=$("#develop_help_div");
			win= $.ligerDialog.open({ target:obj , height: 430,width:610, modal :true}); 
		}
		win.show();
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="WebService.list"/><spr:message code="operator.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span><spr:message code="menu.button.alter"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a onclick="developHelp()" class="link copyTo"><span></span><spr:message code="WebService.developHelp"/></a></div>
					
				</div>
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label"><spr:message code="WebService.alias"/>:</span><input type="text" name="Q_alias_SL"  class="inputText" />
						<span class="label"><spr:message code="WebService.wsdlUrl"/>:</span><input type="text" name="Q_wsdlUrl_SL"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmCommonWsSetList" id="bpmCommonWsSetItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${bpmCommonWsSetItem.id}">
				</display:column>
				<display:column property="alias" titleKey="WebService.alias" sortable="true" sortName="alias"></display:column>
				<display:column property="wsdlUrl" titleKey="WebService.wsdlUrl" sortable="true" sortName="wsdlUrl" maxLength="80"></display:column>
				<display:column titleKey="list.manage" media="html" style="width:220px">
					<a href="del.ht?id=${bpmCommonWsSetItem.id}" class="link del"><spr:message code="operator.del"/></a>
					<a href="edit.ht?id=${bpmCommonWsSetItem.id}" class="link edit"><spr:message code="operator.edit"/></a>
					<a href="###" onclick="test(${bpmCommonWsSetItem.id})" class="link detail"><spr:message code="WebService.operator.test"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmCommonWsSetItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	
	<div id="develop_help_div" class="hidden" style="font-size:13px;">
		<table id="callCodeTable" class="table-grid table-list">
			<tr>
				<td><spr:message code="WebService.callMethod"/>：</td>
				<td style="text-align: left;">
					①<spr:message code="WebService.help1"/><br />
					Map&lt;String,Object> map = new HashMap&lt;String, Object>();<br />
					map.put("test",obj);<br />
					②<spr:message code="WebService.help2"/><br />
					String xml = WebserviceHelper.executeXml(alias, map);<br />
				</td>
			</tr>
			<tr>
				<td><spr:message code="WebService.resolveReturnValue"/>：</td>
				<td style="text-align: left;">
					①<spr:message code="WebService.help3"/><br />
					Document doc= Dom4jUtil.loadXml(xml);<br />
					List&lt;Node> nodes = doc.selectNodes("//string");<br />
					<span class="red">②<spr:message code="WebService.help4"/><br /></span>
			    	&lt;?xml version="1.0" encoding="UTF-8" ?> <br />
			        &lt;root xmlns="http://WebXml.com.cn/"> <br />
			        &nbsp;&nbsp;&nbsp;&nbsp;&lt;string>test&lt;/string><br />
			      	&lt;/root><br />
			      	<span class="red"><spr:message code="WebService.help5"/><br/></span>
			      	Document doc= Dom4jUtil.loadXml(xml);<br />
			      	Dom4jXPath path = new Dom4jXPath("//ns:string");<br />
					path.addNamespace("ns", "http://WebXml.com.cn/");<br />
					List&lt;Node> nodes = path.selectNodes(doc);
				</td>
			</tr>
		</table>
	</div>
</body>
</html>


