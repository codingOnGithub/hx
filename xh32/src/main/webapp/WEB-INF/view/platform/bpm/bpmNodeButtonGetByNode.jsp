<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/bpm" ></f:js>
<title><spr:message code="bpmNodeButton.title"/></title>

<base target="_self"> 
<script type="text/javascript">

$(function(){
	$("a.add").click(function(){
		var url=__ctx + "/platform/bpm/bpmNodeButton/edit.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";	
		$.gotoDialogPage(url);
	});
	$("a.save").unbind("click").click(save);
	$("a.link.init").unbind("click");
	init();
});


function save(){
	var aryId=[];
	$("tr.trNodeBtn").each(function(){
		aryId.push($(this).attr("id"));
	});
	if(aryId.length==0){
		$.ligerDialog.warn($lang_bpm.nodeButton.notDefButton,$lang.tip.msg);
		return;
	}
	var ids=aryId.join(",");
	var url="sort.ht";
	$.post(url,{ids:ids},function(responseText){
		var obj=new com.hotent.form.ResultMessage(responseText);
		if(obj.isSuccess()){//成功
			$.ligerDialog.success($lang.save.success,$lang.tip.msg,function(){
				var nurl =__ctx + "/platform/bpm/bpmNodeButton/getByNode.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(nurl);
			});
			
		}
		else{
			$.ligerDialog.err($lang.tip.msg,$lang.save.failure,obj.getMessage());
		}
	})
}


function sortTr(obj,isUp) {
	var thisTr = $(obj).parents("tr");
	if(isUp){
		var prevTr = $(thisTr).prev();
		if(prevTr){
			thisTr.insertBefore(prevTr);
		}
	}
	else{
		var nextTr = $(thisTr).next();
		if(nextTr){
			thisTr.insertAfter(nextTr);
		}
	}
};


function init(){
	$("a.link.init").click(function(){
		var ele=this;
		$.ligerDialog.confirm($lang_bpm.nodeButton.initTip,$lang.tip.msg,function(rtn) {
			if(rtn){
				var url =__ctx + "/platform/bpm/bpmNodeButton/init.ht?defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}";
				$.gotoDialogPage(url);
			}
		});
		return false;
	});
}

//删除所有的操作按钮
function delAll(){
	$("a.link.delAll").click(function(){
		$.ligerDialog.confirm($lang_bpm.nodeButton.delTip,$lang.tip.msg,function(rtn) {
			if(rtn){
				var aryId=[];
				$("tr.trNodeBtn").each(function(){
					aryId.push($(this).attr("id"));
				});
				if(aryId.length==0){
					$.ligerDialog.warn($lang_bpm.nodeButton.notDefButton,$lang.tip.msg);
					return;
				}
				var ids=aryId.join(",");
				var url =__ctx + "/platform/bpm/bpmNodeButton/delAll.ht?ids="+ids;
				$.gotoDialogPage(url);
			}
		});
		return false;
	});
}
</script>

</head>
<body>
  <c:if test="${buttonFlag}">  
	    <jsp:include page="incDefinitionHead.jsp">
	   		<jsp:param value="<spr:message code='bpmNodeButton.title.list'/>" name="title"/>
		</jsp:include>
	    <f:tab curTab="button" tabName="flow"/>
   </c:if>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="bpmNodeButton.title.list"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link add" href="#"><span></span><spr:message code="menu.button.add"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link save" id="btnUpd" action="sort.ht"><span></span><spr:message code="menu.button.saveSort"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link init" id="bntInit" href="#"><span></span><spr:message code="bpmNodeButton.button.initButton"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a  class="link delAll"  href="deByDefNodeId.ht?defId=${defId}&nodeId=${nodeId}" ><span></span><spr:message code="menu.button.delAll"/></a></div>
					<c:if test="${buttonFlag}"> 
						<div class="l-bar-separator"></div>
						<div class="group"><a  class="link back"  href="list.ht?defId=${defId}"><span></span><spr:message code="menu.button.back"/></a></div>
					</c:if>
				</div>	
			</div>
		</div>
		</div>
		<div class="panel-body">
			<table cellpadding="1" cellspacing="1" class="table-grid table-list">
				<thead>
				<tr>
					<th><spr:message code="list.orderNo"/></th>
					<th><spr:message code="bpmNodeButton.btnname"/></th>
					<th><spr:message code="bpmNodeButton.operatortype"/></th>
					<th><spr:message code="bpmNodeButton.sn"/></th>
					<th><spr:message code="list.manage"/></th>
				</tr>
				</thead>
			
				
				<c:forEach items="${btnList}" var="item" varStatus="status">
				
				<tr class="trNodeBtn" id="${item.id}">
					<td>
						${status.index +1}
					</td>
						<td >
							${item.btnname} 
						</td>
					<td nowrap="nowrap">
						<c:choose>
							<c:when test="${item.isstartform==1 }">
								<c:choose>
									<c:when test="${item.operatortype==1 }"><spr:message code="bpmNodeButton.button.start"/></c:when>
									<c:when test="${item.operatortype==2 }"><spr:message code="bpmNodeButton.button.image"/></c:when>
									<c:when test="${item.operatortype==3 }"><spr:message code="bpmNodeButton.button.print"/></c:when>
									<c:when test="${item.operatortype==4 }"><spr:message code="bpmNodeButton.button.sms"/></c:when>
									<c:when test="${item.operatortype==5 }"><spr:message code="bpmNodeButton.button.email"/></c:when>
									<c:when test="${item.operatortype==6 }"><spr:message code="bpmNodeButton.button.saveDraft"/></c:when>
								    <c:when test="${item.operatortype==14 }"><spr:message code="bpmNodeButton.button.webSignature"/></c:when>
								    <c:when test="${item.operatortype==15 }"><spr:message code="bpmNodeButton.button.handSignature"/></c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${item.operatortype==1 }"><spr:message code="bpmNodeButton.button.complete"/></c:when>
									<c:when test="${item.operatortype==2 }"><spr:message code="bpmNodeButton.button.oppose"/></c:when>
									<c:when test="${item.operatortype==3 }"><spr:message code="bpmNodeButton.button.abstent"/></c:when>
									<c:when test="${item.operatortype==4 }"><spr:message code="bpmNodeButton.button.back"/></c:when>
									<c:when test="${item.operatortype==5 }"><spr:message code="bpmNodeButton.button.backToStart"/></c:when>
									<c:when test="${item.operatortype==6 }"><spr:message code="bpmNodeButton.button.assignee"/></c:when>
									<c:when test="${item.operatortype==7 }"><spr:message code="bpmNodeButton.button.addsign"/></c:when>
									<c:when test="${item.operatortype==8 }"><spr:message code="bpmNodeButton.button.saveForm"/></c:when>
									<c:when test="${item.operatortype==9 }"><spr:message code="bpmNodeButton.button.image"/></c:when>
									<c:when test="${item.operatortype==10 }"><spr:message code="bpmNodeButton.button.print"/></c:when>
									<c:when test="${item.operatortype==11}"><spr:message code="bpmNodeButton.button.history"/></c:when>
									<c:when test="${item.operatortype==14 }"><spr:message code="bpmNodeButton.button.webSignature"/></c:when>
									<c:when test="${item.operatortype==15 }"><spr:message code="bpmNodeButton.button.handSignature"/></c:when>
									<c:when test="${item.operatortype==16 }"><spr:message code="bpmNodeButton.button.commu"/></c:when>
									<c:when test="${item.operatortype==17 }"><spr:message code="bpmNodeButton.button.plusSign"/></c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
						
					</td>
					<td>
						<a alt='<spr:message code="operator.moveup"/>' href='#' class='link moveup' onclick='sortTr(this,true)'>&nbsp;</a>
				        <a alt='<spr:message code="operator.movedown"/>' href='#' class='link movedown' onclick='sortTr(this,false)'>&nbsp;</a>
					</td>
					<td>
						<a class="link edit" href="edit.ht?id=${item.id}&defId=${defId}&nodeId=${nodeId}&buttonFlag=${buttonFlag}"><spr:message code="menu.button.edit"/></a>
						<a class="link del" href="del.ht?id=${item.id}&buttonFlag=${buttonFlag}"><spr:message code="menu.button.del"/></a>
					</td>
					
				</tr>
				</c:forEach>
			</table>
		</div>
	</div><!-- end of panel-body -->				
</div> <!-- end of panel -->
</body>
</html>





