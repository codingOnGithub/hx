<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="processCenter.copytoMatters"/></title>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/SelectUtil.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/UserInfo.js"></script>
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
				$.ligerDialog.warn($lang.operateTip.selectRecord,$lang.tip.msg);
				return;
			}
			copyIds = idArr.join(',');
		}
		var url = __ctx + '/platform/bpm/bpmProCopyto/mark.ht';
		var parsms={copyIds:copyIds};
		$.post(url,parsms,function(d){
			if(t){
				return;
			}
			var json = eval("("+d+")");
			if(json.result){
				$.ligerDialog.success(json.message,$lang.tip.msg,function(){
					location.href=location.href.getNewUrl();	
				});
			}
			else{
				$.ligerDialog.error(json.message,$lang.tip.msg);
			}
		});
	};
	
	

	function showDetail(obj){
		//mark(obj);
		var url = $(obj).attr("action");
		jQuery.openFullWindow(url);
	};

	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="processCenter.copytoMatters"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">	
					
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a href="#" class="link reset" onclick="$.clearQueryForm();"><span></span><spr:message code="menu.button.reset"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link done" onclick="mark()"><span></span><spr:message code="menu.button.markRead"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="myList.ht?porIndex=${porIndex}&tabIndex=${tabIndex}">
					<ul class="row">
						<li><span class="label"><spr:message code="processCenter.subject"/>:</span><input type="text" name="Q_subject_SUPL"  class="inputText" value="${param['Q_subject_SUPL']}"/></li>
						<li><span class="label"><spr:message code="processCenter.creator"/>:</span><input type="text"   id="creator" name="Q_creator_SL" class="inputText"  value="${param['Q_creator_SL']}" onclick="selectUser('creatorId','creator');" readonly="readonly"  />
							<input type="button" value="..." onclick="selectUser('creatorId','creator');">
							<input type="hidden" id="tagIds" name="Q_tagIds_SL"  value="${param['Q_tagIds_SL']}" /></li>
						<%-- <li><span class="label">流程编码:</span><input  type="text" name="Q_codebefore_SUPL" class="inputText"  value="${param['Q_codebefore_SUPL']}" /></li> --%>
						
						<li><span class="label"><spr:message code="processCenter.type"/>:</span><select name="Q_cpType_L" class="select" style="width:60px;" value="${param['Q_cpType_L']}">
								<option value=""><spr:message code="search.select.allSelect"/></option>
								<option value="1" <c:if test="${param['Q_cpType_L'] == 1}">selected</c:if>><spr:message code="processCenter.ccType.cc"/></option>
								<option value="2" <c:if test="${param['Q_cpType_L'] == 2}">selected</c:if>><spr:message code="processCenter.ccType.forward"/></option>
							</select>
						</li>
						<li><span class="label"><spr:message code="processCenter.isRead"/>:</span><select name="Q_isReaded_L" class="select" style="width:60px;" value="${param['Q_isReaded_L']}">
								<option value=""><spr:message code="search.select.allSelect"/></option>
								<option value="0" <c:if test="${param['Q_isReaded_L'] == 0}">selected</c:if>><spr:message code="processCenter.readStatus.notRead"/></option>
								<option value="1" <c:if test="${param['Q_isReaded_L'] == 1}">selected</c:if>><spr:message code="processCenter.readStatus.read"/></option>
							</select>
						</li>
						
						<div class="row_date">
						<li><span class="label"><spr:message code="processCenter.createtime"/>&nbsp;<spr:message code="search.from"/>:</span><input  name="Q_begincreatetime_DL"  class="inputText datePicker" datetype="1"  value="${param['Q_begincreatetime_DL']}" /></li>
						<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endcreatetime_DG" class="inputText datePicker" datetype="2" value="${param['Q_endcreatetime_DG']}"/></li>
						</div>
						<div class="row_date">
						<li><span class="label"><spr:message code="processCenter.ccTime"/>&nbsp;<spr:message code="search.from"/>:</span><input  name="Q_beginccTime_DL"  class="inputText datePicker" datetype="1"  value="${param['Q_beginccTime_DL']}" /></li>
						<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endccTime_DG" class="inputText datePicker" datetype="2" value="${param['Q_endccTime_DG']}"/></li>
						</div>
						
					</ul>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="bpmProCopytoList" id="bpmProCopytoItem" requestURI="myList.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="copyId" value="${bpmProCopytoItem.copyId}">
				</display:column>
				<display:column titleKey="processCenter.subject"  sortable="true" sortName="subject">
					<a onclick="showDetail(this)" href="#${bpmProCopytoItem.runId}" action="${ctx}/platform/bpm/processRun/info.ht?link=1&runId=${bpmProCopytoItem.runId}&copyId=${bpmProCopytoItem.copyId}" title='${bpmProCopytoItem.subject}'>${f:subString(bpmProCopytoItem.subject)}</a>
				</display:column>
				<display:column  titleKey="processCenter.creator" sortable="true" sortName="creator">
					<a href="javascript:userDetail('${bpmProCopytoItem.createId}');">${bpmProCopytoItem.creator}</a>
				</display:column>
				<display:column titleKey="processCenter.isRead" style="width:60px;" sortable="true" sortName="IS_READED">
					<c:choose>
						<c:when test="${bpmProCopytoItem.isReaded eq 0}"><span class="red close-message"><spr:message code="processCenter.readStatus.notRead"/></span></c:when>
						<c:when test="${bpmProCopytoItem.isReaded eq 1}"><span class="green open-message"><spr:message code="processCenter.readStatus.read"/></span></c:when>
					</c:choose>
				</display:column>
				<display:column  titleKey="processCenter.createtime" >
						<fmt:formatDate value="${bpmProCopytoItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column  titleKey="processCenter.ccTime" sortable="true" sortName="CC_TIME" >
					<fmt:formatDate value="${bpmProCopytoItem.ccTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</display:column>
				<display:column titleKey="processCenter.type" style="width:45px;" sortable="true" sortName="CP_TYPE">
					<c:choose>
						<c:when test="${bpmProCopytoItem.cpType eq 1}"><span class="green"><spr:message code="processCenter.ccType.cc"/></span></c:when>
						<c:when test="${bpmProCopytoItem.cpType eq 2}"><span class="brown"><spr:message code="processCenter.ccType.forward"/></c:when>
					</c:choose>
				</display:column>
			</display:table>
			<hotent:paging tableId="bpmProCopytoItem"/>
		</div>				
	</div>
</body>
</html>


