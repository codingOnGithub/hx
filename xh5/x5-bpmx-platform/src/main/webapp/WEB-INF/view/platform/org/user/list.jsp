<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User List</title>
	</head>
	<body>
		<table id="userGridList" title="Load Data" class="easyui-datagrid" style="width:900px;height:auto" 
		 url="${ctx}/platform/org/user/listJson.ht" iconCls="icon-save" pagination="true">
		    <thead>
			    <tr>
				    <th field="userId" width="80">User Id</th>
				    <th field="account" width="80">Account</th>
			    </tr>
		    </thead>
	    </table>
	</body>
</html>