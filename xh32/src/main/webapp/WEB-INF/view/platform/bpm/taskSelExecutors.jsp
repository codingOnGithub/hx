<%@page pageEncoding="UTF-8" %>
<script type="text/javascript">
<!--
	function resetUser(){
		$("#_executors_").val('');
		$("#spanSelectUser").html('');
	}
//-->
</script>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<table class="table-grid">
		<thead>
		<tr>
			<th height="28" width="20%"><spr:message code="start.startFlowForm.selExecutors"/></th>
			<td>
				<input type="hidden" id="_executors_" name="_executors_">
				<span id="spanSelectUser"></span>
				<a href="javascript:;" class="link grant" onclick="selExeUsers()"><spr:message code="menu.button.choose"/>...</a>
				<a href="javascript:;" class="link reset" onclick="resetUser()"><spr:message code="menu.button.reset"/></a>
				</td>
		</tr>
		</thead>			
</table>