<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hotent.platform.model.system.SysUser"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	
	<%@include file="/commons/include/get.jsp" %>
	<f:js pre="js/lang/view/platform/system"></f:js>
	<title><spr:message code="sysUser.title"/><spr:message code="title.manage"/></title>
	<script type="text/javascript" src="${ctx}/js/util/form.js"></script>
	<script type="text/javascript">
	
	function openUserUnder(userid,obj){
		if($(obj).hasClass('disabled')) return false;
		 
		var conf={};				
		var url=__ctx + "/platform/system/userUnder/list.ht?userId="+userid;
		conf.url=url;
		var dialogWidth=550;
		var dialogHeight=450;
		conf=$.extend({},{dialogWidth:dialogWidth ,dialogHeight:dialogHeight ,help:0,status:0,scroll:0,center:1},conf);
		var winArgs="dialogWidth="+conf.dialogWidth+"px;dialogHeight="+conf.dialogHeight
			+"px;help=" + conf.help +";status=" + conf.status +";scroll=" + conf.scroll +";center=" +conf.center;				
		var rtn=window.showModalDialog(url,"",winArgs);		
	}
	
	function syncToLdap(){
		var confirmContent=$lang_system.sysUser.syncToLdap.confirmContent;
		$.ligerDialog.confirm(confirmContent,function(data){
			if(data){
				sync();
			}else{
				return false;
			}
		});
		sync=function(conf){
			var url=__ctx + "/platform/system/sysUser/syncUser.ht";
			$.ligerDialog.waitting($lang_system.sysUser.syncToLdap.loading);
			$.post(url,function(data){
				$.ligerDialog.closeWaitting();
				var obj=new com.hotent.form.ResultMessage(data);
				if(obj.isSuccess()){
					$.ligerDialog.success($lang_system.sysUser.syncToLdap.syncSuccess,$lang.tip.msg,function(){
						location.href=location.href.getNewUrl();
					});
				}
				else{
					$.ligerDialog.err($lang.tip.error,$lang_system.sysUser.syncToLdap.syncFail,obj.getMessage());
				}
			})
		};
	}
	
	</script>
</head>
<body>
<c:set var="SysUser_EXPIRED" value="<%=SysUser.EXPIRED %>" />
<c:set var="SysUser_UN_EXPIRED" value="<%=SysUser.UN_EXPIRED %>"  />

<c:set var="SysUser_LOCKED" value="<%=SysUser.LOCKED %>"/>
<c:set var="SysUser_UN_LOCKED" value="<%=SysUser.UN_LOCKED %>"/>
	<div class="panel">

			<div class="panel-top">
				<div class="tbar-title">
					<span class="tbar-label"><spr:message code="sysUser.title"/><spr:message code="title.manageList"/></span>
				</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<f:a alias="searchUser" css="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="addUser" css="link add" href="edit.ht"><span></span><spr:message code="menu.button.add"/></f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="delUser" css="link del" action="del.ht"><span></span><spr:message code="menu.button.del"/></f:a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<f:a alias="syncToLdap" css="link reload" showNoRight="false" onclick="syncToLdap(this)"><span></span><spr:message code="menu.button.syncToLdap"/></f:a>
					</div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht">
						<ul class="row">
							<li><span class="label"><spr:message code="sysUser.fullname"/>:</span><input type="text" name="Q_fullname_SL"  class="inputText"  value="${param['Q_fullname_SL']}"/></li>				
						    <li><span class="label"><spr:message code="sysUser.account"/>:</span><input type="text" name="Q_account_SL"  class="inputText"  value="${param['Q_account_SL']}"/></li>
							<li><span class="label"><spr:message code="sysUser.from"/>:</span>
							<select name="Q_fromType_S" class="select" value="${param['Q_fromType_S']}">
								<option value="">--<spr:message code="menu.button.choose"/>--</option>
								<option value="<%=SysUser.FROMTYPE_DB %>"><spr:message code="sysUser.formType.DB"/></option>
								<option value="<%=SysUser.FROMTYPE_AD %>"><spr:message code="sysUser.formType.AD"/></option>
							</select>
							</li>
							<li>
								<span class="label"><spr:message code="sysUser.status"/>:</span>
								<select name="Q_status_S" class="select"  value="${param['Q_status_S']}">
									<option value="">--<spr:message code="menu.button.choose"/>--</option>
									<option value="<%=SysUser.STATUS_OK %>"><spr:message code="sys.user.active"/></option>
									<option value="<%=SysUser.STATUS_NO %>"><spr:message code="sys.user.unactive"/></option>
									<option value="<%=SysUser.STATUS_Del %>"><spr:message code="menu.button.del"/></option>
								</select>
							</li>
							<div class="row_date">
							<li><span class="label"><spr:message code="sysUser.createtime"/><spr:message code="search.from"/>:</span><input type="text" id="Q_begincreatetime_DL" name="Q_begincreatetime_DL"  class="inputText Wdate" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'Q_endcreatetime_DG\');}'})" value="${param['Q_begincreatetime_DL']}"/></li>
							<li><span class="label"><spr:message code="search.to"/></span><input type="text" id="Q_endcreatetime_DG" name="Q_endcreatetime_DG"  class="inputText Wdate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'Q_begincreatetime_DL\');}'})"  value="${param['Q_endcreatetime_DG']}"/></li>
							</div>
							<li><span class="label"><spr:message code="sys.user.isLock"/>:</span>
							<select name="Q_isLock_S" class="select"  value="${param['Q_isLock_S']}">
								<option value="">--<spr:message code="menu.button.choose"/>--</option>
								<option value="${SysUser_LOCKED}"  <c:if test="${param['Q_isLock_S'] == 'SysUser_LOCKED' }">selected</c:if>><spr:message code="sys.user.locked"/> </option>
								<option value="${SysUser_UN_LOCKED}" <c:if test="${param['Q_isLock_S'] == 'SysUser_UN_LOCKED' }">selected</c:if>><spr:message code="sys.user.unlock"/> </option>
							</select>
							</li>
							<li><span class="label"><spr:message code="sys.user.isExpired"/>:</span>	
							<select name="Q_isExpired_S" class="select"  value="${param['Q_isExpired_S']}">
								<option value="">--<spr:message code="menu.button.choose"/>--</option>
							<option value="${SysUser_EXPIRED}"  <c:if test="${param['Q_isExpired_S'] == 'SysUser_EXPIRED'}">selected</c:if>><spr:message code="sys.user.expired"/> </option>
							<option value="${SysUser_UN_EXPIRED}" <c:if test="${param['Q_isExpired_S'] == 'SysUser_UN_EXPIRED' }">selected</c:if>><spr:message code="sys.user.unExpired"/> </option>
							</select></li>
						</ul>
					</form>
			</div>

		</div>
		<div class="panel-body">
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysUserList" id="sysUserItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;text-align:center;">
						  	<input type="checkbox" class="pk" name="userId" value="${sysUserItem.userId}">
					</display:column>
					<display:column property="fullname" titleKey="sysUser.fullname" sortable="true" sortName="fullname" style="text-align:left"></display:column>
					<display:column property="account" titleKey="sysUser.account" sortable="true" sortName="account" style="text-align:left"></display:column>
					<display:column  titleKey="sysUser.createtime" sortable="true" sortName="createtime">
						<fmt:formatDate value="${sysUserItem.createtime}" pattern="yyyy-MM-dd"/>
					</display:column>
					<display:column titleKey="sysUser.isExpired" sortable="true" sortName="isExpired">
						<c:choose>
							<c:when test="${sysUserItem.isExpired==1}">
								<span class="red"><spr:message code="sys.user.expired"/></span>
						   	</c:when>
					       	<c:otherwise>
						    	<span class="green"><spr:message code="sys.user.unExpired"/></span>
						   	</c:otherwise>
						</c:choose>
					</display:column>
	                <display:column titleKey="sysUser.isLock" sortable="true" sortName="isLock">
						<c:choose>
							<c:when test="${sysUserItem.isLock==1}">
								<span class="red"><spr:message code="sys.user.locked"/></span>
						   	</c:when>
					       	<c:otherwise>
					       		<span class="green"><spr:message code="sys.user.unlock"/></span>
						   	</c:otherwise>
						</c:choose>
					</display:column>
                	<display:column titleKey="sysUser.status" sortable="true" sortName="status">
						<c:choose>
							<c:when test="${sysUserItem.status==1}">
								<span class="green"><spr:message code="sys.user.active"/></span>
								
						   	</c:when>
						   	<c:when test="${sysUserItem.status==0}">
						   		<span class="red"><spr:message code="sys.user.unactive"/></span>
								
						   	</c:when>
					       	<c:otherwise>
					       		<span class="red"><spr:message code="menu.button.del"/></span>
						        
						   	</c:otherwise>
						</c:choose>
					</display:column>
					<display:column titleKey="sysUser.from" sortable="true" sortName="fromType">
						<c:choose>
							<c:when test="${sysUserItem.fromType==1}">
								<span class="brown">AD</span>
								
						   	</c:when>
						   	<c:when test="${sysUserItem.fromType==0}">
						   		<span class="green"><spr:message code="sysUser.formType.DB"/></span>
						   	</c:when>
					       	<c:otherwise>
					       		<span class="red"><spr:message code="face.entry.unknow"/></span>
						   	</c:otherwise>
						</c:choose>
					</display:column>		
					<display:column titleKey="title.manage" media="html" style="text-align:center;width:13%;" class="rowOps">
					    <f:a alias="userUnder" css="link primary" href="#" onclick="openUserUnder('${sysUserItem.userId}',this)"><spr:message code="sysUser.userUnder"/></f:a>
						<f:a alias="delUser" css="link del" href="del.ht?userId=${sysUserItem.userId}" ><spr:message code="menu.button.del"/></f:a>
						<f:a alias="updateUserInfo" css="link edit" href="edit.ht?userId=${sysUserItem.userId}" ><spr:message code="menu.button.edit"/></f:a>
						<f:a alias="userInfo" css="link detail" href="get.ht?userId=${sysUserItem.userId}"><spr:message code="menu.button.detail"/></f:a>
						<f:a alias="setParams" css="link parameter" href="${ctx}/platform/system/sysUserParam/editByUserId.ht?userId=${sysUserItem.userId}" ><spr:message code="sysUser.paramAttribute"/></f:a>
						<f:a alias="resetPwd" css="link resetPwd" href="resetPwdView.ht?userId=${sysUserItem.userId}"><spr:message code="sysUser.resetPwd"/></f:a>
						<f:a alias="setStatus" css="link setting" href="editStatusView.ht?userId=${sysUserItem.userId}"><spr:message code="sysUser.setStatus"/></f:a>
						<c:if test="${cookie.origSwitch==null}">
							<f:a alias="switch" css="link switchuser" target="_top" href="${ctx}/j_spring_security_switch_user?j_username=${sysUserItem.account}" ><spr:message code="sysUser.switchUser"/></f:a>
						</c:if>
					</display:column>
				</display:table>
				<hotent:paging tableId="sysUserItem"/>
			
		</div>
	</div>
</body>
</html>


