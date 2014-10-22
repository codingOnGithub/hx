<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User Add</title>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		
			<table align="center">
				<tr>
					<td>UserId:</td>
					<td><input type="text" id="username" value="" /></td>
				</tr>
				<tr>
					<td>Account:</td>
					<td><input type="password" name="password" value="" /></td>
				</tr>
			</table>
		
	</body>
</html>