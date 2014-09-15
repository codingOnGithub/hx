<%@ page pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
		
		<%@include file="/commons/include/customForm.jsp" %>
		<title><spr:message code="processRun.get.title" arguments="${processRun.subject}"/></title>
		<script type="text/javascript">
		var isExtForm=eval('${isExtForm}');
		$(function(){
			if(isExtForm){
				var formUrl=$('#divExternalForm').attr("formUrl");
				$('#divExternalForm').load(formUrl, function() {});
			}
		});
	</script>	
</head>
<body>
       <div class="l-layout-header"><spr:message code="processRun.get.title" arguments="${processRun.subject}"/></div>
       <div class="panel">
       <c:if test="${param.tab eq 1 }">
			<f:tab curTab="taskForm" tabName="process"/>
		</c:if>
		<div class="panel-body">
			<c:choose>
				<c:when test="${isExtForm==true }">
					<div id="divExternalForm" formUrl="${form}"></div>
				</c:when>
				<c:otherwise>
					${form}
				</c:otherwise>
			</c:choose>
	   </div>
      </div> 
</body>
</html>