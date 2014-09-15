<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/form.jsp" %>
	<title><spr:message code="sysParam.orgParam"/><spr:message code="operator.search"/><spr:message code="menu.button.preview"/></title>
	<%-- <link href="${ctx}/styles/ligerUI/ligerui-all.css" rel="stylesheet" type="text/css" /> --%>
	<f:link href="Aqua/css/ligerui-all.css"></f:link>
	<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
	<script type="text/javascript">
	$(function() {
		init();
	});
	
	function init(){
		var obj = window.dialogArguments;
		if(obj==null) return;
		var postFlag=$("#postFlag").val();
		if(postFlag==1) return;	
		if(obj.flag==0 ){
			var url=obj.url;
			var param=obj.params;			
			$.post(url, { orgParam:param,postflag:1 },
				function(data) {
 					 $('#result').html(data);
			});
		}		
	}
</script>
</head>
<body id="result">
	<table id="sysParamItem" cellpadding="1" cellspacing="1"  class="table-grid">
		<head>
			<th style="text-align: center;"><spr:message code="userUnder.user"/></th>
		</head>
		<tbody>
			<c:forEach items="${userList}" var="u">
				<tr>
					<td style="text-align: center;">${u.fullname } </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>					
	<input type="hidden" id="postFlag" name="postFlag" value="${postFlag}"/> 			
	<div position="bottom"  class="bottom" style='margin-top:10px'>
			<a href='#' class='button'  onclick="window.close()"><span ><spr:message code="menu.button.back"/></span></a>
	</div>
</body>
</html>