<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://www.jee-soft.cn/functions" %>
<%@ taglib prefix="spr" uri="http://www.springframework.org/tags"%>
<display:table name="taskOpinionList" id="taskOpinionItem" requestURI="list.ht" sort="external" cellpadding="0" cellspacing="0" class="table-grid">
	<display:column titleKey="list.orderNo" style="width:30px;">
	  ${taskOpinionItem_rowNum}
	</display:column>
	<display:column property="taskName" titleKey="taskOpinion.taskName"></display:column>
	<display:column  titleKey="taskOpinion.startTime">
		<fmt:formatDate value="${taskOpinionItem.startTime}" pattern="yyyy-MM-dd HH:mm"/>
	</display:column>
	<display:column  titleKey="taskOpinion.endTime">
		<fmt:formatDate value="${taskOpinionItem.endTime}" pattern="yyyy-MM-dd HH:mm"/>
	</display:column>
	<display:column titleKey="taskOpinion.durTime">
	  ${f:getTime(taskOpinionItem.durTime)}
	</display:column>
	<display:column property="exeFullname" titleKey="taskOpinion.exeUser"></display:column>
	<display:column property="opinion" titleKey="taskOpinion.opinion" ></display:column>
	<display:column titleKey="taskOpinion.checkStatus">
		<f:taskStatus status="${taskOpinionItem.checkStatus}" flag="0"></f:taskStatus>
</display:column>
</display:table>
