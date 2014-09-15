<%@page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<title>Choose Icons</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
	body{
		overflow:hidden;
	}
</style>
<script type="text/javascript">
	function selectIcon(t){
		if(t){
			window.returnValue = t;
		}
		window.close();
	}
</script>
</head>
<body>
	<iframe src="${ctx}/platform/form/bpmFormDef/icon.ht" width="100%" frameborder="0" height="100%"></iframe>
</body>
</html>