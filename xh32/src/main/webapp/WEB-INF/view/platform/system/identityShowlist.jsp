<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerLayout.js" ></script>
<title><spr:message code="identity.title"/><spr:message code="operator.manageList"/></title>
<script type="text/javascript">
	$(function(){
		//$("#defLayout").ligerLayout({ topHeight: 65,bottomHeight:40,allowTopResize:false,allowBottomResize:false});
		
		$("tr.odd,tr.even").unbind("hover");
		$("tr.odd,tr.even").click(function(){
			$(this).siblings().removeClass("over").end().addClass("over");
			$(this).find(':radio').attr('checked', 'checked');
		});
	})

	function selectTable(){
		var obj=$("#identityItem tr.over");
	
		if(obj.length>0){
			var objInput=$("input.pk",obj);
			var aryTb=objInput.val().split(",");
			parent.getTable(aryTb[0],aryTb[1]);
		}else{
			alert($lang.operateTip.selectRecord);
		}
	}
	 
</script>
</head>



<body>
	
	<div id="defLayout">
		<div position="top" title='<spr:message code="operator.select" /><spr:message code="identity.identity" />' >
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="group"><a class="link add" href="edit.ht?islist=1"><span></span><spr:message code="menu.button.add"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="showlist.ht">
					<ul class="row">
							<li><span class="label"><spr:message code="identity.name"/>:</span><input type="text" name="Q_Name_SL"  class="inputText" size="15" value="${param['Q_Name_SL']}"/></li>
					</ul>		
				</form>
			</div>
		</div>
		<div position="center"  style="overflow: auto;">
	    	  	<display:table name="identityList" id="identityItem" requestURI="showlist.ht" sort="external"  export="false"  class="table-grid">
				<display:column  media="html" style="width:30px;">
					  	<input type="radio" name="tableId" value="${identityItem.id}">
						<input  type="hidden" class="pk"  value="${identityItem.alias },${identityItem.name}">
				</display:column>
				<display:column property="name" titleKey="identity.name" sortable="true" sortName="name"></display:column>
				<display:column property="alias" titleKey="identity.alias" sortable="true" sortName="alias"></display:column>
				<display:column property="rule" titleKey="identity.rule" sortable="true" sortName="rule"></display:column>
			</display:table>
			<hotent:paging tableId="identityItem"/>
		</div>
		<div position="bottom"  class="bottom">
			<a href='#' class='button'  onclick="selectTable()" ><span class="icon ok"></span><span><spr:message code="operator.select" /></span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="parent.close()"><span class="icon cancel"></span><span ><spr:message code="operator.cancel" /></span></a>
		</div>
	</div> 
</body>

</html>