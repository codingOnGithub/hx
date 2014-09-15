<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<div class="panel-top">
		<div class="panel-detail">
			<table class="table-detail">
				<tr>
					<th nowrap="nowrap" width="120"><spr:message code="task.toSign.signUser"/></th>
					<td>${curUser.fullname}</td>
				</tr>
				<tr>
					<th nowrap="nowrap"><spr:message code="task.toSign.opinions"/></th>
					<td>
						<textarea rows="3" cols="78" id="voteContent" name="voteContent" maxlength="512"></textarea>
						<input type="hidden" id="isSignTask" name="isSignTask" value="true"/>
					</td>
				</tr>
				<c:if test="${not empty bpmNodeSign}">
					<tr>
						<th><spr:message code="task.toSign.decideType"/></th>
						<td>
							<spr:message code="task.toSign.vote"/>
							<font color='green'>
							<b>
							<c:choose>
								<c:when test="${bpmNodeSign.decideType==1}"><spr:message code="task.toSign.agree"/></c:when>
								<c:otherwise><spr:message code="task.toSign.objection"/></c:otherwise>
							</c:choose>
							</b>
							</font>
							<spr:message code="task.toSign.voteAmount"/>
							<c:choose>
								<c:when test="${bpmNodeSign.voteType==1}"><spr:message code="task.toSign.percentage"/></c:when>
								<c:otherwise><spr:message code="task.toSign.to"/></c:otherwise>
							</c:choose>
							<b>${bpmNodeSign.voteAmount}</b><spr:message code="task.toSign.compelete"/>
						</td>
					</tr>
				</c:if>	
			</table>
			</div>
	</div>
	<br/>
	<div class="panel-body">
		<display:table name="signDataList" id="taskSignDataItem" cellpadding="1" cellspacing="1" class="table-grid">
			<display:column property="voteUserName" titleKey="task.toSign.vote.name"></display:column>
			<display:column  titleKey="task.toSign.vote.time">
				<fmt:formatDate value="${taskSignDataItem.voteTime}" pattern="yyyy-MM-dd HH:mm"/>
			</display:column>
			<display:column titleKey="task.toSign.vote.isAgree">
				<c:choose>
					<c:when test="${taskSignDataItem.isAgree==1}"><font color='green'><spr:message code="task.toSign.agree"/></font></c:when>
					<c:when test="${taskSignDataItem.isAgree==2}"><font color='red'><spr:message code="task.toSign.objection"/></font></c:when>
					<c:when test="${taskSignDataItem.isAgree==0}"><font color='gray'><spr:message code="task.toSign.abstention"/></font></c:when>
					<c:when test="${taskSignDataItem.isAgree==5}"><font color='green'><spr:message code="task.toSign.agreed"/></font></c:when>
					<c:when test="${taskSignDataItem.isAgree==6}"><font color='red'><spr:message code="task.toSign.objectioned"/></font></c:when>
				</c:choose>
			</display:column>
			<display:column property="signNums" titleKey="task.toSign.vote.number"/>
			<display:column titleKey="task.toSign.vote.OpinionCom">
				<c:choose>
					<c:when test="${empty taskSignDataItem.voteTime}"><spr:message code="task.toSign.notSigning"/></c:when>
					<c:otherwise>${taskSignDataItem.content}</c:otherwise>
				</c:choose>
			</display:column>
		</display:table>
	</div>
