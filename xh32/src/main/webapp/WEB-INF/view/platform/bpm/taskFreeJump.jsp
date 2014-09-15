<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
	<table class="table-detail">
		<tr>
			<th width="15%"><spr:message code="task.tranTask.jumpNode"/></th>
			<td width="35%">
				<select id="destTask" name="destTask" onchange="changeDestTask(this)">
					<c:forEach items="${jumpNodeMap}" var="item">
						<optgroup label="${item.key}">
							<c:forEach items="${item.value}" var="node">
								<option value="${node.key}">${node.value}</option>
							</c:forEach>
						</optgroup>
					</c:forEach>
				</select>
			</td>
			<th width="15%"><spr:message code="task.tranTask.nodeExeUsers"/></th>
			<td width="35%">
				<input type="hidden" id="lastDestTaskId" name="lastDestTaskId" value="">
				<span id="jumpUserDiv"></span>
				<a href="#" id="jumpUserLink" class="link grant" onclick="selectExeUsers(this)"><spr:message code="menu.button.choose"/>..</a>
			</td>
		</tr>
	</table>