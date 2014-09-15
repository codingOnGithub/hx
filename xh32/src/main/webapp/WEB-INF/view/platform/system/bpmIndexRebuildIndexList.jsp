<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="indexRebuild"/><spr:message code="title.manageList"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" /> --%>
<f:link href="jquery.qtip.css"></f:link>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript">
	$(function(){
		$("a.unsupport-rebuild-index").each(function(){
			var msg=$(this).attr("msg")
			$(this).qtip({
				content:msg
			});	
		});
		
	});
	function editRebuildJob() {
		var indexesAry = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		if (indexesAry.length == 0) {
			$.ligerDialog.warn($lang_system.indexRebuild.list.warn_msg_no_index);
			return false;
		} else {
			var temhtml=$('#divJob').html();
			var defDesc="";
			indexesAry.each(function(i){
					defDesc+=$lang_system.indexRebuild.list.text_msg_table_name+$(indexesAry[i]).attr('table')+",";
					defDesc+=$lang_system.indexRebuild.list.text_msg_index_name+$(indexesAry[i]).val()+";\n";
			});
			$('#jobDescription1').val(defDesc);
			$.ligerDialog.open({
				width : 600,
				height : 400,
				type : 'error',
				title : $lang_system.indexRebuild.list.text_msg_error_title,
				target : $('#frmJob'),
				buttons : [ {
					text : $lang.button.ok,
					onclick : function(button,dialog){
						var jobName = $('#jobName1').val();
						var jobDescription = $('#jobDescription1').val();
						if ($.trim(jobName) != '') {
							$('#jobName').val(jobName);
							$('#jobDescription').val($.trim(jobDescription));
							saveRebuidJob();
							dialog.close();
							$('#divJob').html(temhtml);
						} else {
							$.ligerDialog.warn($lang_system.indexRebuild.list.warn_msg_job_name);
	
						}
					}
				}, {
					text : $lang.button.cancel,
					onclick : function(button,dialog){
						dialog.close();
						$('#divJob').html(temhtml);
					}
				} ]
			});
			
		}
	}
	function saveRebuidJob() {
		var url = __ctx + "/platform/system/bpmIndexRebuild/saveJob.ht";
		var indexAry=new Array();
		var indexeNames = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
		indexeNames.each(function(){
			var name = $(this).val();
// 			var table = $(this).next('input[name="indexTable"]').val();
			var table=$(this).attr('table');
			var isCustom=$(this).attr('custom');
			var isPrimaryKeyIndex=$(this).attr('primaryKeyIndex');
			var index={
				name:name,
				table:table,
				isCustom:isCustom,
				isPrimaryKeyIndex:isPrimaryKeyIndex
			};
			indexAry.push(index);
		});
		var params={
				indexes:JSON2.stringify(indexAry),
				jobName:$('#jobName').val(),
				jobDescription:$('#jobDescription').val()
		};
		$.post(url,params, function(data) {
			var obj = new com.hotent.form.ResultMessage(data);
			if (obj.isSuccess()) {
				$.ligerDialog.success(obj.getMessage(),$lang.tip.success,function(){
					window.close();
				});
				
				
			} else {
				$.ligerDialog.err($lang.tip.error,$lang_system.indexRebuild.list.error_msg_save,obj.getMessage());
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
				<span class="tbar-label"><spr:message code="indexRebuild"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<c:choose>
							<c:when test="${dbType=='db2'}">
								<a id="editRebuildJob" class="link add disabled unsupport-rebuild-index" msg="<spr:message code="indexRebuild.list.createPlan.msg.db2"/>" href="#"><span></span><spr:message code="indexRebuild.list.createPlan"/></a>
							</c:when>
							<c:when test="${dbType=='h2'}">
								<a id="editRebuildJob" class="link add disabled unsupport-rebuild-index" msg="<spr:message code="indexRebuild.list.createPlan.msg.h2"/>" href="#"><span></span><spr:message code="indexRebuild.list.createPlan"/></a>
							</c:when>
							<c:otherwise>
								<a id="editRebuildJob" class="link add" href="#" onclick="editRebuildJob()"><span></span><spr:message code="indexRebuild.job.add"/></a>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group"><a id="searchIndex" class="link search" href="#" ><span></span><spr:message code="menu.button.search"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm">
					<ul class="row">
			            <li><span class="label"><spr:message code="indexRebuild.indexName"/>:</span><input type="text" name="Q_indexName_S"  class="inputText" value="${param['Q_indexName_S']}"/></li>
			            <li><span class="label"><spr:message code="indexRebuild.tableName"/>:</span><input type="text" name="Q_tableName_S"  class="inputText" value="${param['Q_tableName_S']}"/></li>
					</ul>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
					
				<form id="frmIndexRebuid">
			    	<c:set var="checkAll">
						<input type="checkbox" id="chkall"/>
					</c:set>
					
					<c:set var="dbIndexList">
						<table id="indexList" class="table-grid table-list" cellpadding="1" cellspacing="1">
					   		<thead>
					   			<tr>
					   				<th>${checkAll}&nbsp;<spr:message code="list.check"/></th>
					   				<th><spr:message code="indexRebuild.indexName"/></th>
					   				<th><spr:message code="indexRebuild.tableName"/></th>
					   				<th><spr:message code="indexRebuild.isCustomTable"/></th>
					   				<th><spr:message code="indexRebuild.isPkIndex"/></th>
					   				<th><spr:message code="indexRebuild.tableDesc"/></th>
					   			</tr>
					   		</thead>
					   		<tbody>
					   			<c:forEach items="${indexList}" var="indexMap">
					   				<tr>
					   					<td width="100px">
					   						<input class="pk"  type="checkbox" value="${indexMap['index'].indexName}" 
					   						table="${indexMap['index'].indexTable}"
					   						custom="${indexMap['isCustom'] }"
					   						primaryKeyIndex="${indexMap['index'].pkIndex }"
					   						/>
					   						<input name="indexTable" type="hidden" value="${indexMap['index'].indexTable}"/>
					   					</td>
					   					<td >${indexMap['index'].indexName }</td>
					   					<td >${indexMap['index'].indexTable }</td>
					   					<c:choose>
					   						<c:when test="${indexMap['isCustom'] }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
					   					<c:choose>
					   						<c:when test="${indexMap['index'].pkIndex }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
										<td>${indexMap['tableComment'] }</td>
									</tr>
					   			</c:forEach>
					   		</tbody>
					   	</table>
					</c:set>
					<c:set var="dbTableList">
						<table id="indexList" class="table-grid table-list" cellpadding="1" cellspacing="1">
					   		<thead>
					   			<tr>
					   				<th>${checkAll}&nbsp;<spr:message code="list.check"/></th>
					   				<th><spr:message code="indexRebuild.indexName"/></th>
					   				<th><spr:message code="indexRebuild.tableName"/></th>
					   				<th><spr:message code="indexRebuild.isCustomTable"/></th>
					   				<th><spr:message code="indexRebuild.isPkIndex"/></th>
					   				<th><spr:message code="indexRebuild.tableDesc"/></th>
					   			</tr>
					   		</thead>
					   		<tbody>
					   			<c:forEach items="${indexList}" var="indexMap">
					   				<tr>
					   					<td width="100px">
					   						<input class="pk"  type="checkbox" value="${indexMap['index'].indexName}" 
					   						table="${indexMap['index'].indexTable}"
					   						custom="${indexMap['isCustom'] }"
					   						primaryKeyIndex="${indexMap['index'].pkIndex }"
					   						/>
					   					</td>
					   					<td >${indexMap['index'].indexName }</td>
					   					<td >${indexMap['index'].indexTable}</td>
					   					<c:choose>
					   						<c:when test="${indexMap['isCustom'] }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
					   					<c:choose>
					   						<c:when test="${indexMap['index'].pkIndex }">
					   							<td >√</td>
					   						</c:when>
					   						<c:otherwise>
					   							<td >X</td>
					   						</c:otherwise>
					   					</c:choose>
										<td>${indexMap['tableComment'] }</td>
									</tr>
					   			</c:forEach>
					   		</tbody>
					   	</table>
					</c:set>
					<c:if test="${dbType=='mysql'}">${dbTableList }</c:if>
					<c:if test="${dbType=='oracle'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='sqlserver'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='db2'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='h2'}">${dbIndexList }</c:if>
					<c:if test="${dbType=='dm'}">${dbIndexList }</c:if>
				   	<input name="jobName" id="jobName" type="hidden" value=""/>
				   	<input name="jobDescription" id="jobDescription" type="hidden" value=""/>
			   	</form>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
	<div id="divJob" style="display:none">
  		<form id="frmJob" >
  			<table class="table-grid table-list">
  				<tr>
  					<td><spr:message code="indexRebuild.job.name"/>:</td>
  					<td><input id="jobName1" class="inputText"/></td>
  				</tr>
  				<tr>
  					<td><spr:message code="indexRebuild.job.desc"/>:</td>
  					<td><textarea id="jobDescription1" cols="30" rows="10" style="height:200px;width:400px;"></textarea></td>
  				</tr>
  			</table>
  		</form>
  	</div>
</body>
</html>


