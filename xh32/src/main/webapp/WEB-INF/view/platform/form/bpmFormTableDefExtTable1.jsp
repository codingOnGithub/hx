<%--
	time:2012-01-05 12:01:21
	desc:edit the 脚本管理
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<%@include file="/commons/include/get.jsp"%>

<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/form" ></f:js>
	<title><spr:message code="operator.select"/><spr:message code="bpmFormTable.externalTables"/></title>

	<script type="text/javascript" src="${ctx}/js/util/tableHeadFix.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/tabOperator.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#btnSearch").click(searchTables);
			$("a.run").click(handNextClick);
			//$("#tbTableList").chromatable({height:"350px",width:"100%"});
		});
		 
		function handNextClick(){
			var tableName=$("#tableName").val();
			var ds=$("#dataSource").val();
			if(tableName==""){
				$.ligerDialog.warn($lang_form.bpmFormTable.ExternalTables.selectTable);
				return;
			}
			var parameter="tablename=" + tableName +"&dataSource=" + ds;
			$.get("isTableNameExternalExisted.ht",parameter, function(data) {
				if(data=="true"){
					$.ligerDialog.warn($lang_form.bpmFormTable.ExternalTables.isExist);
				}
				else{
					$("#scriptForm").submit();
				}
			});
		}
		
		var tdTemplate="<tr style='cursor:pointer'><td style='width:300px;'>#tbname</td><td>#comment</td></tr>";
		
		function searchTables(){
			var ds=$("#dataSource").val();
			var tbName=$("#tbName").val();
			var url=__ctx +"/platform/form/bpmFormTable/getTableList.ht";
			var tableObj=$("#tbTableList");
			$.ligerDialog.waitting($lang_form.bpmFormTable.ExternalTables.waiting);
			$.post(url, { ds:ds, table: tbName },function(data) {
				$.ligerDialog.closeWaitting();
				$("tr:gt(0)",tableObj).remove();
				var success=data.success;
				if(success=='true'){
					var obj=data.tables;
					for(var key in obj){
						var comment=obj[key];
						var tr=tdTemplate.replace("#tbname",key).replace("#comment",comment);
						tableObj.append(tr);
					}
					$("#tbTableList tr:gt(0)").click(handClick);
				}
				else{
					$.ligerDialog.error($lang.tip.errorMsg);
				}
		    });
		}
		
		function handClick(){
			var obj=$(this);
			obj.addClass("over").siblings().removeClass("over");
			var tableName=$("td:first",obj).text();
			$("#tableName").val(tableName);
		}
		
		function sysDatasourceMgr(){
			var url=__ctx + "/platform/system/sysDataSource/list.ht";
			//{tabid:id,text:txt,url:url,icon:icon}
			addTabItem({tabid:"sysDatasource",text:$lang_form.bpmFormTable.ExternalTables.manage,url:url})
		}
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="operator.select"/><spr:message code="bpmFormTable.externalTables"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link run" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.next"/></a>
					</div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="scriptForm" method="post" action="defExtTable2.ht">
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="operator.select"/><spr:message code="bpmFormTable.DataSource"/>: </th>
								<td width="30%">
									<select id="dataSource" name="dataSource">
										<option value="LOCAL"><spr:message code="bpmFormTable.Local.DataSource"/></option>
										<c:forEach items="${dsList}" var="ds">
											<option value="${ds.alias}">${ ds.name} </option>
										</c:forEach>
									</select>
									&nbsp;
									<a href="#" onclick="sysDatasourceMgr();"><spr:message code="bpmFormTable.DataSource.Manage"/>...</a>
								</td>
								<th width="20%"><spr:message code="bpmFormTable.query.table"/>: </th>
								<td width="30%">
									<input type="text" name="tbName" id="tbName" class="inputText"> &nbsp; <a href="#" id="btnSearch" class="link search"><spr:message code="menu.button.search"/></a>
								</td>
							</tr>
						</table>
						<br/>
						<table id="tbTableList" class="table-grid table-list" width="80%" >
							<thead>
								<th style="text-align: center;width:300px;"><spr:message code="bpmFormTable.tableName"/></th><th style="text-align: center;"><spr:message code="bpmFormTable.tableDesc"/></th>
							</thead>
							<tr>
								<td colspan="2"><spr:message code="bpmFormTable.DataSource.Message"/></td>
							</tr>
						</table>
						<input type="hidden" value="" name="tableName" id="tableName"/>
				</form>
		</div>
</div>
</body>
</html>
