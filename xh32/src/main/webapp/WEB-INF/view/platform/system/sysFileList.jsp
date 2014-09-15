<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<style type="text/css">
.file_name{
	text-decoration:none;
	color:black;
}
</style>
<%@include file="/commons/include/get.jsp" %>
<title><spr:message code="sysFile"/><spr:message code="title.manageList"/></title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" /> --%>
<f:link href="jquery.qtip.css"></f:link>
<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ImageQtip.js" ></script>
<script type="text/javascript">
	$(function() {
		$("a.file_name").each(function() {
			var path = $(this).attr("path");			
			//图片类型
			if (/\w+.(png|gif|jpg)/gi.test(path)) {
				ImageQtip.drawImg(this,"${ctx}/"+path,{maxWidth:265});
			}
		});
	});
</script>
</head>
<body>
	<div class="panel">
	<div class="hide-panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="sysFile"/><spr:message code="title.manageList"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span><spr:message code="menu.button.del"/></a></div>
				</div>	
			</div>
			<div class="panel-search">
					<form id="searchForm" method="post" action="list.ht">
							<ul class="row">
										<li><span class="label"><spr:message code="sysFile.fileName"/>:</span><input type="text" name="Q_fileName_SL"  class="inputText"  value="${param['Q_fileName_SL']}" />	</li>											
										<li><span class="label"><spr:message code="sysFile.creator"/>:</span><input type="text" name="Q_creator_SL"  class="inputText"  value="${param['Q_creator_SL']}"/></li>
										<li><span class="label"><spr:message code="sysFile.ext"/>:</span><input type="text" name="Q_ext_SL"  class="inputText"   value="${param['Q_ext_SL']}"/></li>									
										<div class="row_date">
										<li><span class="label"><spr:message code="sysFile.createtime"/><spr:message code="search.from"/>:</span><input type="text"  name="Q_begincreatetime_DL"  class="inputText date" value="${param['Q_begincreatetime_DL']}"/></li>
										<li><span class="label"><spr:message code="search.to"/>: </span><input type="text" name="Q_endcreatetime_DG" class="inputText date" value="${param['Q_endcreatetime_DG']}"/></li>
										</div>
							</ul>
					</form>
			</div>
		</div>
		</div>
		<div class="panel-body">
			
			
		    	<c:set var="checkAll">
					<input type="checkbox" id="chkall"/>
				</c:set>
			    <display:table name="sysFileList" id="sysFileItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1"   class="table-grid">
					<display:column title="${checkAll}" media="html" style="width:30px;">
						  	<input type="checkbox" class="pk" name="fileId" value="${sysFileItem.fileId}">
					</display:column>
					<display:column media="html" titleKey="sysFile.fileName" sortable="true" sortName="fileName">
						<a href="javascript:;" class="file_name" path="${sysFileItem.filePath}">${sysFileItem.fileName}</a>
					</display:column>
					<display:column titleKey="sysFile.createtime" sortable="true" sortName="createtime">
						<fmt:formatDate value="${sysFileItem.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</display:column>
					<display:column property="ext" titleKey="sysFile.ext" sortName="ext" sortable="true"></display:column>							
				    <display:column property="note" titleKey="sysFile.size" sortable="true" sortName="note" maxLength="80"></display:column>
					<display:column property="creator" titleKey="sysFile.creator"  ></display:column>
					<display:column titleKey="list.manage" media="html" style="width:180px;text-align:center">
						<f:a alias="delFile" href="del.ht?fileId=${sysFileItem.fileId}" css="link del"><spr:message code="operator.del"/></f:a>
						<a href="get.ht?fileId=${sysFileItem.fileId}" class="link detail"><spr:message code="operator.detail"/></a>
		                <c:choose>
							<c:when test="${sysFile.delFlag eq 1}"><font color="red"><spr:message code="sysFile.delFlag"/></font></c:when>
							<c:otherwise><a href="download.ht?fileId=${sysFileItem.fileId }" target="_blank" class="link download"><spr:message code="operator.download"/></a></c:otherwise>
						</c:choose>
					</display:column>
				</display:table>
				<hotent:paging tableId="sysFileItem"/>
			
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


