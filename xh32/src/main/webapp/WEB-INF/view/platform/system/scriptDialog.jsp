<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
	<%@include file="/commons/include/get.jsp" %>
	<title><spr:message code="script"/><spr:message code="operator.manageList"/></title>
	<script type="text/javascript">
		window.name="win";
		var tmpScript="";
		$(function(){
			$("#layoutMain").ligerLayout({ rightWidth: 350, height: '100%'});
		});
		
		function selectScript()
		{
			if(tmpScript==""){
				$.ligerDialog.warn($lang_system.script.dialog.validate_msg_select,$lang.tip.msg);
				return ;
			}
			window.returnValue= tmpScript;
			window.close();
		}
		
		function ifrloaded(){
			$("#scriptListFrame").contents().find("tr.even,tr.odd").click(function(){
				var obj=$(this);
				var memo=$("textarea[name='memo']",obj).val();
				tmpScript=$("textarea[name='script']",obj).val();
				$("#tdMemo").text(memo);
				$("#tdScript").text(tmpScript);
				
			});
		}
	</script>
	<style type="text/css">
				div.bottom{text-align: center;}
				div.bottom input {width:65px;margin: 8px 10px;font-size: 14px;height: 23px;}
				html { overflow-x: hidden; }
	</style>
</head>
<body>
<div id="layoutMain" style="width:100%">
      <div position="right" title="<spr:message code='script.dialog.preview' />" width="300"> 
    	 <div class="panel-detail">
	    	 <table class="table-detail" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<th width="20%"><spr:message code="script.memo" />: </th>
					<td id="tdMemo"></td>
				</tr>
			
				<tr>
					<th width="20%"><spr:message code="script.script" />: </th>
					<td id="tdScript"></td>
				</tr>
			</table>
		</div>
     </div>	 
      <div position="center" id="framecenter"> 
      	<!-- 
          <div class="panel-search">
					<form id="searchForm" method="post" action="dialog.ht" target="win">
							<ul class="row">
								<li><span class="label"><spr:message code="script.script" /><spr:message code="script.category" />:</span>
								<select name="Q_category_S">
									<option value=""><spr:message code="search.select.all" /></option>
									<c:forEach items="${categoryList }" var="catName">
										<option value="${catName}">${catName}</option>
									</c:forEach>
								</select>
								
								<input type="submit" value="<spr:message code='menu.button.search' />"></li>
							</ul>
					</form>
			</div>
			<div class="panel-data" style="overflow:auto;height:450px;">
			    <display:table name="scriptList" id="scriptItem" requestURI="list.ht"  cellpadding="1" cellspacing="1" class="table-grid">
					<display:column  titleKey="script.name" style="text-align:left" >
						${scriptItem.name}
						<textarea name="memo" style="display: none;">${scriptItem.memo}</textarea>
						<textarea name="script" style="display: none;">${scriptItem.script}</textarea>
					</display:column>
				</display:table>
				<hotent:paging tableId="scriptItem" showExplain="false" showPageSize="false" />
			</div>
			-->
			<iframe onload="ifrloaded()" id="scriptListFrame" name="scriptListFrame" height="100%" width="100%" frameborder="0" src="${ctx}/platform/system/script/selector.ht"></iframe>
      </div> 
      <div position="bottom"  class="bottom" style='margin-top:10px;'>
			<a href='#' class='button'  onclick="selectScript()" ><span class="icon ok"></span><span ><spr:message code="operator.select" /></span></a>
			<a href='#' class='button' style='margin-left:10px;' onclick="window.close()"><span class="icon cancel"></span><span ><spr:message code="menu.button.cancel" /></span></a>
	 </div>
	
</div> 


</body>
</html>


