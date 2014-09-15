
<%--
	time:2013-12-19 11:26:03
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>自定义别名脚本表明细</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">自定义别名脚本表详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<th width="20%">脚本的别名:</th>
				<td>${aliasScript.aliasName}</td>
			</tr>
			<tr>
				<th width="20%">脚本的描叙:</th>
				<td>${aliasScript.aliasDesc}</td>
			</tr>
			<tr>
				<th width="20%">调用类的路径:</th>
				<td>${aliasScript.className}</td>
			</tr>
			<tr>
				<th width="20%">调用类的对象名:</th>
				<td>${aliasScript.classInsName}</td>
			</tr>
			<tr>
				<th width="20%">调用的方法名:</th>
				<td>${aliasScript.methodName}</td>
			</tr>
			<tr>
				<th width="20%">调用的方法的描叙:</th>
				<td>${aliasScript.methodDesc}</td>
			</tr>
			<tr>
				<th width="20%">方法返回类型:</th>
				<td>${aliasScript.returnType}</td>
			</tr>
			<tr>
				<th width="20%">方法相关设置:</th>
				<td>${aliasScript.argument}</td>
			</tr>
			<tr>
				<th width="20%">是否禁用:</th>
				<td>
				    <c:if test="${aliasScript.enable eq 0}">是</c:if>
				    <c:if test="${aliasScript.enable eq 1}">否</c:if>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>

