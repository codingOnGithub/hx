
<%--
	time:2011-11-16 16:34:16
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="title.edit"/><spr:message code="sysDataSource"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=sysDataSource"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${sysDataSource.id ==null }){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				$('#sysDataSourceForm').submit(); 
			});
			
			$('#testConnect').click(function() {
				var rtn=$('#sysDataSourceForm').valid(); 
			
				if(!rtn){
					return;
				}
				var data = {};
				$('#sysDataSourceForm input').each(function(i, d) {
					data[$(d).attr('name')] = $(d).val();
				});
				$.ligerDialog.waitting($lang_system.sysDataSource.edit.waitting_msg_test);
				$.post('testConnectByForm.ht', data, function(data) {
					$.ligerDialog.closeWaitting();
					var d = data[0];
					if(d.success) {
						$.ligerDialog.success('<p><font color="green">'+$lang_system.sysDataSource.edit.success_msg_test+'</font></p>');
					} else {
						$.ligerDialog.error('<p><font color="red">' + String.format($lang_system.sysDataSource.edit.error_msg_test,d.msg) + '</font></p>');
					}
				});
				
			});
			
			$("#selDbType").change(function(){
				var type=$(this).val();
				var driver="";
				var jdbcUrl="";
				switch(type){
					case "oracle":
						driver="oracle.jdbc.OracleDriver";
						jdbcUrl=$lang_system.sysDataSource.edit.text_msg_url_oracle;
						break;
					case "mssql":
						driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
						jdbcUrl=$lang_system.sysDataSource.edit.text_msg_url_sql2005;
						break;
					case "mysql":
						driver="com.mysql.jdbc.Driver";
						jdbcUrl=$lang_system.sysDataSource.edit.text_msg_url_mysql;
						break;
					case "db2":
						driver="com.ibm.db2.jcc.DB2Driver";
						jdbcUrl=$lang_system.sysDataSource.edit.text_msg_url_db2;
						break;
					case "h2":
						driver="org.h2.Driver";
						jdbcUrl=$lang_system.sysDataSource.edit.text_msg_url_h2;
						break;
					case "dm":
						driver="dm.jdbc.driver.DmDriver";
						jdbcUrl=$lang_system.sysDataSource.edit.text_msg_url_dm;
						break;
					default:
						driver="";
						jdbcUrl="";
						break;
				}
				$("#driverName").val(driver);
				$("#url").val(jdbcUrl);
			});
		});
		
		
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${sysDataSource.id !=null }">
			            <span class="tbar-label"><spr:message code="title.edit"/><spr:message code="sysDataSource"/></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="title.add"/><spr:message code="sysDataSource"/></span>
			        </c:otherwise>
			    </c:choose>				
			</div>
			<c:if test="${canReturn==0}">
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link test" id="testConnect"><span></span><spr:message code="sysDataSource.edit.button.test"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>
			</div>
			</c:if>
		</div>
		<div class="panel-body">

				<form id="sysDataSourceForm" method="post" action="save.ht">
				
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th width="20%"><spr:message code="sysDataSource.name"/>:<span class="required">*</span> </th>
								<td ><input type="text" id="name" name="name" value="${sysDataSource.name}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysDataSource.alias"/>:<span class="required">*</span> </th>
								<td >
									<c:choose>
										<c:when test="${sysDataSource.id==null}">
											<input type="text" id="alias" name="alias" value=""  class="inputText"/></td>
										</c:when>
										<c:otherwise>
											${sysDataSource.alias}
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysDataSource.dbType"/>:<span class="required">*</span> </th>
								<td >
									
									<select id="selDbType"  name="dbType">
										<option value=""><spr:message code="select.pleaseChoose"/></option>
										<option value="oracle" <c:if test="${sysDataSource.dbType=='oracle'}">selected</c:if>  >ORACLE</option>
										<option value="mssql" <c:if test="${sysDataSource.dbType=='mssql'}">selected</c:if>>SQLSERVER</option>
										<option value="mysql" <c:if test="${sysDataSource.dbType=='mysql'}">selected</c:if>>MYSQL</option>
										<option value="db2" <c:if test="${sysDataSource.dbType=='db2'}">selected</c:if>>DB2</option>
										<option value="h2" <c:if test="${sysDataSource.dbType=='h2'}">selected</c:if>>H2</option>
										<option value="dm" <c:if test="${sysDataSource.dbType=='dm'}">selected</c:if>><spr:message code="sysDataSource.dbType.dm"/></option>
									</select>
								</td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysDataSource.driverName"/>:<span class="required">*</span> </th>
								<td ><input type="text" id="driverName" style="width:300px" name="driverName" value="${sysDataSource.driverName}" size="60"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysDataSource.url"/>:<span class="required">*</span> </th>
								<td ><input type="text" id="url" name="url"  style="width:400px"  value="${sysDataSource.url}"  class="inputText" size="100"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysDataSource.userName"/>: </th>
								<td ><input type="text" id="userName" name="userName" value="${sysDataSource.userName}"  class="inputText"/></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="sysDataSource.password"/>: </th>
								<td ><input type="text" id="password" name="password" value="${sysDataSource.password}"  class="inputText"/></td>
							</tr>
						</table>
						<input type="hidden" id="returnUrl" value="${returnUrl}" />
						<input type="hidden" name="id" value="${sysDataSource.id}" />
					
				</form>
		</div>
</div>
</body>
</html>

