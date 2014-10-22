<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login In User</title>
</head>
<body>
	<div style="text-align: center">
		<h2>登录</h2>
		<form action="<%=request.getContextPath()%>/j_spring_security_check"
			method="post">
			<table align="center">
				<tr>
					<td>UserName:</td>
					<td><input type="text" name="username" value="" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" value="" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登录" />
						&nbsp;
						<input type="reset" value="重置"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%
		String error = request.getParameter("error");
		if (error != null && !"".equals(error)) {
			out.println("<span color='red'>登录出错!</span><br/>");
		}
	%>
</body>
</html>