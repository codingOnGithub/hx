<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>

<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processRun.copyTo.title"/><spr:message code="operator.list"/></title>
<script type="text/javascript">
	function mark(t){
		var copyIds = "";
		if(t){
			var tr = $(t).parents("tr"),
				pk = $("input.pk",tr).val();

			if(!pk)return;
			copyIds = pk;
		}
		else{
			var idArr = [];

			$("input.pk").each(function(){
				var me = $(this),
					state = me.attr("checked");

				if(state)
					idArr.push(me.val());
			});
			if(idArr.length==0){
				$.ligerDialog.warn($lang.operateTip.selectMarkRecord,$lang.tip.msg);
				return;
			}
			copyIds = idArr.join(',');
		}
		var url = __ctx + '/platform/bpm/bpmProCopyto/mark.ht';
		var params={copyIds:copyIds};
		$.post(url,params,function(d){
			var json = eval("("+d+")");
			if(json.result){
				if(!t){
					$.ligerDialog.success(json.message,$lang.tip.msg,function(){
						location.href=location.href.getNewUrl();
					});
				}
			}
			else{
				$.ligerDialog.error(json.message,$lang.tip.msg);
			}
		});
	};
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processRun.copyTo.title"/><spr:message code="operator.list"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link done" onclick="mark()"><span></span><spr:message code="menu.button.markRead"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del" action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="${ctx}/platform/bpm/processRun/history.ht"><span></span><spr:message code="menu.button.back"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label"><spr:message code="processRun.copyTo.subject"/>:</span>
						<input type="text" name="Q_subject_SL"  class="inputText" value="${param['Q_subject_SL']}"/>
						<span class="label" style="margin:0 0 0 20px;"><spr:message code="processRun.copyTo.ccUname"/>:</span>
						<input type="text" name="Q_ccUname_SL"  class="inputText" value="${param['Q_ccUname_SL']}"/>
						<span class="label" style="margin:0 0 0 20px;"><spr:message code="processRun.copyTo.creator"/>:</span>
						<input type="text" name="Q_creator_SL"  class="inputText" value="${param['Q_creator_SL']}"/>
						<span class="label" style="margin:0 0 0 20px;"><spr:message code="processRun.copyTo.type"/>:</span>
						<select name="Q_cpType_L" class="select" style="width:60px;" value="${param['Q_cpType_L']}">
							<option value=""><spr:message code="select.all"/></option>
							<option value="1" <c:if test="${param['Q_cpType_L'] == 1}">selected</c:if>><spr:message code="processRun.copyTo.cc"/></option>
							<option value="2" <c:if test="${param['Q_cpType_L'] == 2}">selected</c:if>><spr:message code="processRun.copyTo.forward"/></option>
						</select>
						<span class="label" style="margin:0 0 0 20px;"><spr:message code="processRun.copyTo.isRead"/>:</span>
						<select name="Q_isReaded_L" class="select" style="width:60px;" value="${param['Q_isReaded_L']}">
							<option value=""><spr:message code="select.all"/></option>
							<option value="0" <c:if test="${param['Q_isReaded_L'] == 0}">selected</c:if>><spr:message code="processRun.copyTo.isReaded.noRead"/></option>
							<option value="1" <c:if test="${param['Q_isReaded_L'] == 1}">selected</c:if>><spr:message code="processRun.copyTo.isReaded.read"/></option>
						</select>
						<input type="hidden" name="runId" value="${runId}"/>
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmProCopytoList" id="bpmProCopytoItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="copyId"  value="${bpmProCopytoItem.copyId}">
				</display:column>
				<display:column property="subject" titleKey="processRun.copyTo.subject"></display:column>
				<display:column property="ccUname" titleKey="processRun.copyTo.ccUname"></display:column>
				<display:column property="creator" titleKey="processRun.copyTo.creator"></display:column>
				<display:column titleKey="processRun.copyTo.isRead" style="width:60px;" sortable="true" sortName="IS_READED">
					<c:choose>
						<c:when test="${bpmProCopytoItem.isReaded eq 0}"><span class="red close-message"><spr:message code="processRun.copyTo.isReaded.noRead"/></span></c:when>
						<c:when test="${bpmProCopytoItem.isReaded eq 1}"><span class="green open-message"><spr:message code="processRun.copyTo.isReaded.read"/></span></c:when>
					</c:choose>
				</display:column>
				<display:column  titleKey="processRun.copyTo.readTiem" sortable="true" sortName="READ_TIME">
					<fmt:formatDate value="${bpmProCopytoItem.readTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column titleKey="processRun.copyTo.type" style="width:45px;" sortable="true" sortName="CP_TYPE">
					<c:choose>
						<c:when test="${bpmProCopytoItem.cpType eq 1}"><span class="green"><spr:message code="processRun.copyTo.cc"/></span></c:when>
						<c:when test="${bpmProCopytoItem.cpType eq 2}"><span class="brown"><spr:message code="processRun.copyTo.forward"/></span></c:when>
					</c:choose>
				</display:column>
				<display:column titleKey="list.manage" media="html" style="width:180px;line-height:21px;">
					<a class="link del" href="del.ht?copyId=${bpmProCopytoItem.copyId}"><spr:message code="menu.button.del"/></a>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmProCopytoItem"/>
		</div>
	</div>
</body>
</html>


