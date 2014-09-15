<%--
	time:2011-11-28 10:17:09
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/commons/include/get.jsp"%>
<title><spr:message code="sysUser.title"/><spr:message code="menu.button.detail"/></title>
<script type="text/javascript" src="${ctx }/js/lg/plugins/ligerTab.js"></script>
<script type="text/javascript">
	$(function() {
		var h = $('body').height();
		$("#tabMyInfo").ligerTab({
		//	height : h - 60
		});
	
	});
	
	function returnBack() {
		location.href = "${ctx}/platform/system/sysUser/list.ht";
	}
</script>
</head>
<body>
	
	<c:if test="${canReturn==0}">
		<div class="panel-toolbar" id="pToolbar">
			<div class="toolBar">
				<div class="group">
					<a class="link back" onclick="returnBack()" ><span></span><spr:message code="menu.button.back"/></a>
				</div>
			</div>
		</div>
	</c:if>
	
	
	<div id="tabMyInfo" style="overflow: hidden; position: relative;">

		<div title="<spr:message code='sys.user.baseInfo'/>" tabid="userdetail"
			icon="${ctx}/styles/default/images/resicon/user.gif">
			<div class="panel-detail">
				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<td rowspan="10" align="center" width="26%">
							<div class="person_pic_div">
								<p><img src="${ctx}/${pictureLoad}" alt="<spr:message code='sysUser.personalPhoto'/>" /></p>
							</div>
						</td>
						<th width="18%" style="height: 48px !important"><spr:message code="sysUser.account"/>:</th>
						<td>${sysUser.account}</td>
					</tr>
					<tr>
						<th style="height: 48px !important"><spr:message code="sysUser.fullname"/>:</th>
						<td>${sysUser.fullname}</td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sys.user.sex"/>:</th>
						<td><c:choose>
								<c:when test="${sysUser.sex==1}">
									        <spr:message code="sys.user.boy"/>
								   	</c:when>
								<c:otherwise>
								            <spr:message code="sys.user.girl"/>      
								   	</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<th style="height: 48px !important"><spr:message code="sys.user.isLock"/>:</th>
						<td><c:choose>
								<c:when test="${sysUser.isLock==1}">
									       <spr:message code="sys.user.locked"/>
								   	</c:when>
								<c:otherwise>
								           <spr:message code="sys.user.unlock"/>      
								   	</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sys.user.isExpired"/>:</th>
						<td><c:choose>
								<c:when test="${sysUser.isExpired==1}">
									       <spr:message code="sys.user.expired"/>
								   	</c:when>
								<c:otherwise>
								           <spr:message code="sys.user.unExpired"/>       
								   	</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sys.user.state"/>:</th>
						<td><c:choose>
								<c:when test="${sysUser.status==1}">
										<spr:message code="sys.user.active"/>
								   	</c:when>
								<c:when test="${sysUser.status==0}">
										<spr:message code="sys.user.unactive"/>
								   	</c:when>
								<c:otherwise>
								        <spr:message code="menu.button.del"/>
								   	</c:otherwise>
							</c:choose></td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sysUser.createtime"/>:</th>
						<td>${f:shortDate(sysUser.createtime)}</td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sysUser.email"/>:</th>
						<td>${sysUser.email}</td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sysUser.mobile"/>:</th>
						<td>${sysUser.mobile}</td>
					</tr>

					<tr>
						<th style="height: 48px !important"><spr:message code="sysUser.phone"/>:</th>
						<td>${sysUser.phone}</td>
					</tr>

				</table>
			</div>
		</div>
		<c:if test="${isOtherLink==0}">
		<div title="<spr:message code='sysUser.dialog.orgName'/>" tabid="orgdetail"
			icon="${ctx}/styles/default/images/resicon/home.png">
			<div
				style="overflow-y: auto; overflow-x: hidden; border: 0px solid #6F8DC6;">
				<div class="panel-data">
					<table id="orgItem" class="table-grid" cellpadding="1"
						cellspacing="1">
						<thead>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sysOrg.orgName"/></th>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sys.user.isPrimary"/></th>
							<th style="width: 50%; text-align: center !important;"><spr:message code="sys.org.isDeptManager"/></th>
						</thead>
						<c:forEach items="${orgList}" var="orgItem" varStatus="status">
							<tr class="${status.index%2==0?'odd':'even'}">
								<td style="text-align: center;">${orgItem.orgName}</td>
								<td style="text-align: center;"><c:choose>
										<c:when test="${orgItem.isPrimary==1}">
											<spr:message code="yes"/>
									   	</c:when>
										<c:otherwise>
									       <spr:message code="no"/>  
									   	</c:otherwise>
									</c:choose></td>
								<!-- <td style="text-align: center;">${orgItem.chargeName}</td> -->
                                       <td style="text-align: center;">
                                       <c:choose>
											<c:when test="${orgItem.isCharge==1}">
												<spr:message code="yes"/>
										   	</c:when>
											<c:otherwise>
										       <spr:message code="no"/>  
										   	</c:otherwise>
									    </c:choose>
									</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

		</div>
		<div title="<spr:message code='sysUser.dialog.posName'/>" tabid="posdetail"
			icon="${ctx}/styles/default/images/nav-sales.png">
			<div
				style="overflow-y: auto; overflow-x: hidden; border: 0px solid #6F8DC6;">
				<div class="panel-data">
					<table id="posItem" class="table-grid" cellpadding="1"
						cellspacing="1">
						<thead>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sys.pos.posName"/></th>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sys.isMainPos"/></th>

						</thead>
						<c:forEach items="${posList}" var="posItem" varStatus="status">
							<tr class="${status.index%2==0?'odd':'even'}">
								<td style="text-align: center;">${posItem.posName}</td>
								<td style="text-align: center;"><c:choose>
										<c:when test="${posItem.isPrimary==1}">
										<spr:message code="yes"/>
								   	</c:when>
										<c:otherwise>
								        <spr:message code="no"/>   
								   	</c:otherwise>
									</c:choose></td>

							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div title="<spr:message code='sysUser.dialog.roleName'/>" tabid="roldetail"
			icon="${ctx}/styles/default/images/resicon/customer.png">
			<div
				style="overflow-y: auto; overflow-x: hidden; border: 0px solid #6F8DC6;">
				<div class="panel-data">
					<table id="rolItem" class="table-grid" cellpadding="1"
						cellspacing="1">
						<thead>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sys.user.roleName"/></th>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sys.user.systemName"/></th>

						</thead>
						<c:forEach items="${roleList}" var="rolItem" varStatus="status">
							<tr class="${status.index%2==0?'odd':'even'}">
								<td style="text-align: center;">${rolItem.roleName}</td>
								<td style="text-align: center;">${rolItem.systemName}</td>

							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div title="<spr:message code='sysUser.paramAttribute'/>" tabid="params"
			icon="${ctx}/styles/default/images/resicon/article.gif">
			<div
				style="overflow-y: auto; overflow-x: hidden; border: 0px solid #6F8DC6;">
				<div class="panel-detail">
					<table id="paramItem" class="table-grid" cellpadding="1"
						cellspacing="1">
						<thead>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sysUser.argument.name"/></th>
							<th style="width: 25%; text-align: center !important;"><spr:message code="sysUser.argument.value"/></th>
						</thead>
						<c:forEach items="${userParamList}" var="para" varStatus="status">
							<tr class="${status.index%2==0?'odd':'even'}">
								<td style="text-align: center;">${para.paramName}</td>
								<td style="text-align: center;">${para.paramValue}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	   </c:if>
	</div>
</body>
</html>
