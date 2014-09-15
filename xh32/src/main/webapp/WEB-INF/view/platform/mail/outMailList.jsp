<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title></title>
<%@include file="/commons/include/get.jsp" %>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript" src="${ctx}/js/lg/plugins/ligerLayout.js"></script>
<script type="text/javascript">
	$(function(){
		$("div.group > a.link.del").unbind("click");
		delSelect();
	});
	function emailsync(type){
		var outMailSetId=$('#outMailSetId').val();
		
		$.ligerDialog.waitting($lang_system.outMail.wail_sync);
		$.post("sync.ht",{id:outMailSetId,types:type},function(data){
			$.ligerDialog.closeWaitting();
			var obj=new com.hotent.form.ResultMessage(data);
			if(obj.isSuccess()){
				$.ligerDialog.success(obj.getMessage(),$lang_system.outMail.email_success,function(){
					window.location.href="list.ht?id="+outMailSetId+"&types=1";
				});
			}else{
				
				$.ligerDialog.err($lang.tip.error,$lang_system.outMail.syncEmail_fail,obj.getMessage());
			}
			
		});
	}
	
	function delSelect(){
		$("div.group > a.link.del").click(function(){
			var outMailSetId=$("#outMailSetId").val();
			var type=$("#types").val();
			var tip="";
			var action=$(this).attr("action");
			var $aryId = $("input[type='checkbox'][disabled!='disabled'][class='pk']:checked");
			if($aryId.length==0){
				$.ligerDialog.warn($lang.operateTip.selectRecord);
				return false;
			}
			var delId="";
			var len=$aryId.length;
			$aryId.each(function(i){
				var obj=$(this);
				if(i<len-1){
					delId+=obj.val() +",";
				}else{
					delId+=obj.val();
				}
			});
			var url=action +"?mailId=" +delId +"&outMailSetId="+outMailSetId+"&types="+type;
			if(type==1){
				tip=$lang_system.outMail.sure_moveto_dustbin;
			}else{
				tip=$lang_system.outMail.sure_delete_email;
			}
			$.ligerDialog.confirm(tip,$lang.tip.msg,function(rtn) {
				if(rtn) {
					var form=new com.hotent.form.Form();
					form.creatForm("form", action);
					form.addFormEl("mailId", delId);
					form.addFormEl("outMailSetId",outMailSetId);
					form.addFormEl("types",type);
					form.submit();
				}
			});
			return false;
		});
	}
		
	function emailedit(type){
		var defaultMail='${outMailUserSet.userName}';
		if(defaultMail==null||defaultMail.trim()==""){
			$.ligerDialog.confirm($lang_system.outMail.emailedit,$lang.tip.msg,function(rtn){
				if(rtn){
					window.location.href="../../mail/outMailUserSeting/edit.ht";
				}
			});
		}else{
			window.location.href="edit.ht";
		}
	}
</script>
</head>
<body>
	<input name="outMailSetId" id="outMailSetId" type="hidden" value="${outMailUserSet.id}"/>
	<input name="types" id="types" type="hidden" value="${types}"/>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">${outMailUserSet.userName}(${outMailUserSet.mailAddress})
					<c:choose>
						<c:when test="${types==1}"><spr:message code="outMail.inbox"/></c:when>
						<c:when test="${types==2}"><spr:message code="outMail.outbox"/></c:when>
						<c:when test="${types==3}"><spr:message code="outMail.draftbox"/></c:when>
						<c:otherwise><spr:message code="outMail.dustbin"/></c:otherwise>
					</c:choose> 
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link addMessage " href="#"  onclick="emailedit(${types})"><span></span><spr:message code="menu.button.email"/></a></div>
					<c:if test="${types==1}">
						<div class="l-bar-separator"></div>
						<div class="group"><a class="link reload" onclick="emailsync()" id="emailsync"><span></span><spr:message code="menu.button.syncEmail"/></a></div>
					</c:if>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  id="deleteBtn" action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<ul class="row">
						<li><span class="label"><spr:message code="outMail.title"/>:</span><input type="text"  width="500" name="Q_title_SL"  class="inputText"  value="${param['Q_title_SL']}"/></li>
						<div class="row_date">
						<li><span class="label"><spr:message code="outMail.mailDate"/> <spr:message code="search.from"/>:</span><input  name="Q_beginmailDate_DL"  class="inputText date" value="${param['Q_beginmailDate_DL']}"/></li>
						<li><span class="label"><spr:message code="search.to"/>: </span><input  name="Q_endmailDate_DG" class="inputText date" value="${param['Q_endmailDate_DG']}"/></li>
					    </div>
					    <c:if test="${types==1}">
					    <li><span class="label"><spr:message code="outMail.isRead"/>:</span>
                           <select name="Q_isRead_S" value="${param['Q_isRead_S']}">
	                            <option value=""><spr:message code="search.select.all"/></option>
	                            <option value="1" <c:if test="${param['Q_isRead_S'] == 1}">selected</c:if>><spr:message code="outMail.isRead.read"/></option>
	                            <option value="0" <c:if test="${param['Q_isRead_S'] == 0}">selected</c:if>><spr:message code="outMail.isRead.notread"/></option>
                           </select></li></c:if>                                                																							
					</ul>
					<input type="hidden" name="id" value="${outMailSetId}"/>
					<input type="hidden" name="types" value="${types}"/>
				</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
				<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="outMailList" id="outMailItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" export="false"  class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="mailId" value="${outMailItem.mailId}">
					</display:column>
					<display:column property="senderAddresses" titleKey="outMail.sender" sortable="true" sortName="senderAddresses" style="width:80px;"></display:column>
					<display:column titleKey="outMail.title" sortable="true" sortName="title" style="width:380px;" href="get.ht" paramId="mailId" paramProperty="mailId">
						<a href="get.ht?mailId=${outMailItem.mailId}&outMailSetId=${outMailSetId}">${outMailItem.title}</a>
					</display:column>
					<display:column  titleKey="outMail.mailDate" sortable="true" sortName="mailDate"  style="width:50px;">
						<fmt:formatDate value="${outMailItem.mailDate}" pattern="yyyy-MM-dd"/>
					</display:column>
					<c:if test="${types==1}">
						<display:column  titleKey="outMail.isRead" sortable="true" sortName="isRead" style="text-align:center;width:30px;">
					      <c:choose>
						   <c:when test="${outMailItem.isRead==0 }">
							<span class="red"><spr:message code="outMail.isRead.notread"/></span>
						   </c:when>
						   <c:otherwise>
						   	<span class="green"><spr:message code="outMail.isRead.read"/></span>
						   </c:otherwise>
					       </c:choose>
				         </display:column>
			         </c:if>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
						<c:choose>
						 	<c:when test="${outMailItem.types==4 }">
								<a href="del.ht?mailId=${outMailItem.mailId}&outMailSetId=${outMailSetId}&types=${outMailItem.types}" id="delEnd" class="link del"><spr:message code="menu.button.delete"/></a>
							</c:when>
							<c:when test="${outMailItem.types==3 }">
								<a href="edit.ht?mailId=${outMailItem.mailId}&outMailSetId=${outMailSetId}&types=${outMailItem.types}" id="delEnd" class="link edit"><spr:message code="menu.button.edit"/></a>
								<a href="del.ht?mailId=${outMailItem.mailId}&outMailSetId=${outMailSetId}&types=${outMailItem.types}" class="link del"><spr:message code="menu.button.del"/></a>
							</c:when>
							<c:otherwise>
								<a href="del.ht?mailId=${outMailItem.mailId}&outMailSetId=${outMailSetId}&types=${outMailItem.types}" class="link del"><spr:message code="menu.button.del"/></a>
							</c:otherwise>
						</c:choose>
						<a href="get.ht?mailId=${outMailItem.mailId}&outMailSetId=${outMailSetId}" class="link preview"><spr:message code="menu.button.check"/></a>
					</display:column>
				</display:table>
				<hotent:paging tableId="outMailItem"/>
			
		</div>				
	</div>
</body>
</html>


