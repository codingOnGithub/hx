
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="dictionary"/><spr:message code="title.manageList"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript">
		//向上
		function sortUp(obj) {
		
			var thisTr = $(obj).parents("tr");
			var prevTr = $(thisTr).prev();
			if (typeof (prevTr) != 'undefined' && prevTr!=null && prevTr!=''){
				var thisTrHtml = $(thisTr).html();
				var prevTrHtml = $(prevTr).html();
				if(prevTrHtml!=null){
					
					$(thisTr).html(prevTrHtml);
					$(prevTr).html(thisTrHtml);
					reSortListSn(thisTr);
				}
			}
		};
		
		// 向下
		function sortDown(obj) {
		
			var thisTr = $(obj).parents("tr");
			var nextTr = $(thisTr).next();
			if (typeof (nextTr) != 'undefined'&& nextTr!=null && nextTr!=''){
				var thisTrHtml = $(thisTr).html();
				var nextTrHtml = $(nextTr).html(); 
				if(nextTrHtml!=null){
					$(thisTr).html(nextTrHtml);
					$(nextTr).html(thisTrHtml);
					reSortListSn(thisTr);
				}
				
			}
		};
		
		//重新排序
		function reSortListSn(trObj,url){
			var table= $(trObj).parents("table");
		
			var checkbox=$(table).find(":checkbox[name='dicId']");
			var dicIds="";
			$.each( checkbox, function(i, c){
				dicIds+=$(c).val()+",";
			});
		
			if(dicIds.length>1){
				dicIds=dicIds.substring(0,dicIds.length-1);
					
				var form=new com.hotent.form.Form();
				form.creatForm("form", "${ctx}/platform/system/dictionary/sort.ht");
				form.addFormEl("dicIds", dicIds);
				form.submit();
					
			}
		};
		
		function addNew(parentId,typeId,returnUrl)
		{
			if(parentId==0)
			{
				 $.ligerDialog.warn($lang_system.dictionary.list.warn_msg_add_on_category);
				 return;
			}
			else
			{
				location.href="add1.ht?typeId="+typeId+"&returnUrl="+returnUrl;
			}
		}
	
	</script>
</head>
<body>
			<div class="panel">
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label">${ globalType.typeName}</span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link add\" onclick="addNew('${parentId}','${typeId}','${returnUrl}')" href="#"><span></span><spr:message code="menu.button.add"/></a></div>
							<div class="l-bar-separator"></div>
							<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
						</div>	
					</div>
				</div>
				</div>
				<div class="panel-body">
					<div class="panel-search">
							<form id="searchForm" method="post" action="list.ht">
									<ul class="row">
												<li><span class="label" style="display:none; ">nodePath:</span><input type="hidden" name="nodePath"  class="inputText" value="${nodePath }" readonly="readonly"/></li>
											
												<li><span class="label"><spr:message code="dictionary.itemKey"/>:</span><input type="text" name="itemKey"  class="inputText" value="${param['itemKey']}"/></li>
											
												<li><span class="label"><spr:message code="dictionary.itemName"/>:</span><input type="text" name="itemName"  class="inputText" value="${param['itemName']}"/></li>
											
									</ul>
							</form>
					</div>
					<br/>
					<div class="panel-data">
				    	<c:set var="checkAll">
							<input type="checkbox" id="chkall"/>
						</c:set>
					    <display:table name="dictionaryList" id="dictionaryItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
							<display:column title="${checkAll}" media="html" style="width:30px;">
								  	<input type="checkbox" class="pk" name="dicId" value="${dictionaryItem.dicId}">
							</display:column>
							<display:column property="itemName" titleKey="dictionary.itemName" style="text-align:left"></display:column>
							<display:column property="itemValue" titleKey="dictionary.itemValue" sortable="true" sortName="itemValue" style="text-align:left"></display:column>
							<display:column property="itemKey" titleKey="dictionary.itemKey" sortable="true" sortName="itemKey" ></display:column>
							
							
							<display:column titleKey="list.manage" media="html" style="width:300px;text-align:center">
								<a href="del.ht?dicId=${dictionaryItem.dicId}" class="link del"><spr:message code="operator.del"/></a>
								<a href="upd1.ht?dicId=${dictionaryItem.dicId}&returnUrl=${returnUrl}" class="link edit"><spr:message code="operator.edit"/></a>
								<a href="get.ht?dicId=${dictionaryItem.dicId}" class="link detail"><spr:message code="operator.detail"/></a>
								<a href="#" class="link moveup" onclick="sortUp(this)"><spr:message code="sort.up"/></a>
								<a href="#" class="link movedown" onclick="sortDown(this)"><spr:message code="sort.down"/></a>							
							</display:column>
						</display:table>
						
					</div>
				</div><!-- end of panel-body -->				
			</div> <!-- end of panel -->
</body>
</html>


