<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<title>SYS_ZTREE管理asfd</title>
<%@include file="/commons/include/get.jsp" %>
<script type='text/javascript'>
$(function(){
		$("div[class='panel-toolbar']").each(function(){
			var toolbar=$(this);
			var toolbarClass=toolbar.attr("class");
			var toolbarHtml=toolbar.html();
			var search=toolbar.siblings(".panel-search");
			var searchClass=search.attr("class");
			if(searchClass){
				var searchHtml=search.html();
				toolbar.html(searchHtml);
				toolbar.attr("class",searchClass);
				search.html(toolbarHtml);
				search.attr("class",toolbarClass);
			}
		})
}) 
</script>	
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_ZTREE管理列表</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link search" id="btnSearch"><span></span>查询</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link add" href="edit.ht"><span></span>添加</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link update" id="btnUpd" action="edit.ht"><span></span>修改</a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link del"  action="del.ht"><span></span>删除</a></div>
				</div>	
			</div>
			<div class="panel-search">
				<form id="searchForm" method="post" action="list.ht">
					<div class="row">
						<span class="label">分类ID :</span><input type="text" name="Q_typeid_L"  class="inputText" />
						<span class="label">分类名称:</span><input type="text" name="Q_typename_S"  class="inputText" />
						<span class="label">分类别名:</span><input type="text" name="Q_alias_S"  class="inputText" />
						<span class="label">表名:</span><input type="text" name="Q_tablename_S"  class="inputText" />
					</div>
				</form>
			</div>
		</div>
		<div class="panel-body">
	    	<c:set var="checkAll">
				<input type="checkbox" id="chkall"/>
			</c:set>
		    <display:table name="sysZtreeList" id="sysZtreeItem" requestURI="list.ht" sort="external" cellpadding="1" cellspacing="1" class="table-grid">
				<display:column title="${checkAll}" media="html" style="width:30px;">
			  		<input type="checkbox" class="pk" name="id" value="${sysZtreeItem.typeid}">
				</display:column>
				<display:column property="typename" title="分类名称" sortable="true" sortName="TYPENAME"></display:column>
				<display:column property="alias" title="分类别名" sortable="true" sortName="ALIAS"></display:column>
				<display:column property="tablename" title="表名" sortable="true" sortName="TABLENAME"></display:column>
				<display:column property="idkey" title="IDKEY" sortable="true" sortName="IDKEY"></display:column>
				<display:column property="namekey" title="NAMEKEY" sortable="true" sortName="NAMEKEY"></display:column>
				<display:column property="pidkey" title="PIDKEY" sortable="true" sortName="PIDKEY"></display:column>
				<display:column property="rootpid" title="ROOTPID" sortable="true" sortName="ROOTPID"></display:column>
				<display:column property="roottitle" title="ROOTTITLE" sortable="true" sortName="ROOTTITLE"></display:column>
				<display:column property="expandslevel" title="EXPANDSLEVEL" sortable="true" sortName="EXPANDSLEVEL"></display:column>
				<display:column title="管理" media="html" style="width:220px">
					<a href="del.ht?id=${sysZtreeItem.typeid}" class="link del"><span></span>删除</a>
					<a href="edit.ht?id=${sysZtreeItem.typeid}" class="link edit"><span></span>编辑</a>
					<a href="get.ht?id=${sysZtreeItem.typeid}" class="link detail"><span></span>明细</a>
				</display:column>
			</display:table>
			<hotent:paging tableId="sysZtreeItem"/>
		</div><!-- end of panel-body -->				
	</div> <!-- end of panel -->
</body>
</html>


