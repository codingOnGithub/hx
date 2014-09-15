<%@page import="com.hotent.platform.model.system.GlobalType"%>
<%@page import="com.hotent.core.util.AppConfigUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/include/html_doctype.html" %>
<html>
    <head>
		<%@include file="/commons/include/get.jsp" %>
        <title>新建流程</title>
	<%@include file="/js/tree/util/htTree.jsp" %>
	<script type="text/javascript">
	$(function(){
		var options={
			
		}
		var TreeType={
			//"alias":"org",
			//"tablename":"sys_org",
			//"idkey":"orgId",
			//"namekey":"orgName",
			//"pidkey":"orgSupId",
			//"urls" : "",
			//"rootpid":"100",
			//"roottitle":"nihao",
			 "expandslevel":"2"
			//,"dataparams":"{'typeId': 'orgId','typeName':'orgName','nodePath':'path','parentId':'orgSupId'}",
			
		}
		var setting={
			callback:{
			}
		}
		options.TreeType=TreeType;
		options.setting=setting;
		$.initHtTree($(".ztree"),options);
	})
	</script>
    <body>
      	<div id="layout" class="panel-top">
   			<div class="ztree" alias="ded" ></div>
            <div position="center" title="我的树">
            	<iframe id="defFrame" height="100%" width="100%" frameborder="0" src="${ctx}/demo/ztree/sysZtree/list.ht"></iframe>
            </div>
        </div>
    </body>
</html>
