<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title></title>
<%@include file="/commons/include/get.jsp"%>
<f:js pre="js/lang/view/platform/system" ></f:js>
<script type="text/javascript">
	$(function() {

		$("a.del").unbind("click");

		$("#btnDel").click(function() {
			delList('');
		});
		handlerDelOne();
	});

	function addWin(orgId, flag, path) {
		if (orgId == "0") {
			$.ligerDialog.warn($lang_system.sysOrg.select_left_orgNode);
			return;
		} else {
			location.href = "edit.ht?orgId=" + orgId + "&flag=LA&path=" + path;
		}
	}

	function delList(id) {
		var value;
		if (id == "") {
			var delId = "";
			//提交到后台服务器进行日志删除批处理的日志编号字符串
			var $aryId = $("input[type='checkbox'][class='pk']:checked");
			var len = $aryId.length;
			if (len == 0) {
				$.ligerDialog.warn($lang.operateTip.notSelectEdit);
				return;
			} else if (len > 0) {
				$aryId.each(function(i) {
					var obj = $(this);
					if (i < len - 1) {
						delId += obj.val() + ",";
					} else {
						delId += obj.val();
					}
				});
			}
			value = delId;
		} else {
			value = id;
		}
		var url = "orgdel.ht?orgId=" + value;
		$.ligerDialog.confirm($lang.operateTip.sureDelete, $lang.tip.msg, function(rtn) {
			if (rtn) {
				$.post(url, function(data) {
					var obj = new com.hotent.form.ResultMessage(data);
					if (obj.isSuccess()) {
						$.ligerDialog.success(obj.getMessage(), $lang.tip.msg,
								function(rtn) {
									var orgId = "${orgId}"
									location.href = "listById.ht?orgId="
											+ orgId;
									var treeNode = parent.getSelectNode();
									parent.loadTree(treeNode.demId);
								});
					} else {
						$.ligerDialog.err($lang.tip.error, obj.getMessage());
					}
				});
			}
		});
	}

	//处理删除一行
	function handlerDelOne() {
		$("table.table-grid td a.link.del").click(function() {
			if ($(this).hasClass('disabled'))
				return false;
			var ele = this;
			var url = ele.href;
			$.ligerDialog.confirm($lang.operateTip.sureDelete, $lang.tip.msg, function(rtn) {
				if (rtn) {
					$.post(url, function(data) {
						var obj = new com.hotent.form.ResultMessage(data);
						if (obj.isSuccess()) {
							var orgId = "${orgId}";
							location.href = "listById.ht?orgId=" + orgId;
							var treeNode = parent.getSelectNode();
							parent.loadTree(treeNode.demId);
						} else {
							$.ligerDialog.err($lang.tip.error, obj.getMessage());
						}
					});
				}
			});
			return false;
		});
	}

	function goUrl(orgId) {
		parent.parent.$("#A").attr("src", "get.ht?orgId=" + orgId + "&flag=1");
	}
	//树列表的收展
	function treeClick(obj) {
		var clazz = $(obj).attr("class");
		var id = $(obj).parents("tr").attr("id");
		if (clazz == "tree-list-minus") {
			toggleChild(id, "hide");
		} else if (clazz == "tree-list-plus") {
			toggleChild(id, "show");
		}
		//置换加减号
		$(obj).toggleClass("tree-list-minus");
		$(obj).toggleClass("tree-list-plus");
	};
	//子结点收展
	function toggleChild(parentId, type) {
		var child = $("tr[parentId='" + parentId + "']");
		$.each(child, function(i, c) {
			if (type == "hide") {
				$(c).hide();
			} else if (type == "show") {
				$(c).find("a[name='tree_a']").removeClass("tree-list-plus");
				$(c).find("a[name='tree_a']").addClass("tree-list-minus");
				$(c).show();
			}
			var id = $(c).attr("id");
			toggleChild(id, type);
		});
	};
</script>

<style type="text/css">
html {
	height: 100%
}

body {
	padding: 0px;
	margin: 0;
}
</style>

</head>
<body>
	<c:choose>
		<c:when test="${action=='global' }">
			<f:tab curTab="type" tabName="sysOrg" />
		</c:when>
		<c:otherwise>
			<f:tab curTab="type" tabName="sysOrgGrade" />
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${empty sysOrg}">
			<div style="text-align: center; margin-top: 10%;"><spr:message code="sysUserOrg.emptyOrg"/></div>
		</c:when>
		<c:otherwise>
			<div class="hide-panel">
				<div class="panel-top">
					<div class="tbar-title">
						<span class="tbar-label"><spr:message code="sys.org.orgSunTitle"/></span>
					</div>
					<div class="panel-toolbar">
						<div class="toolBar">
							<div class="group">
								<a class="link search" id="btnSearch"><span></span><spr:message code="menu.button.search"/></a>
							</div>
							<div class="l-bar-separator"></div>
							<!--  <div class="group"><a class="link add"  href="#" onclick="addWin('${orgId}','LA','${path}')"><spr:message code="menu.button.add"/></a></div> 
						<div class="l-bar-separator"></div>-->
							<div class="group">
								<a class="link del" id="btnDel" href="#"><span></span><spr:message code="menu.button.del"/></a>
							</div>
							<div class="l-bar-separator"></div>
						</div>
					</div>
					<div class="panel-search">
						<form id="searchForm" method="post" action="listById.ht">
							<ul class="row">
								<li><span class="label"><spr:message code="sys.org.orgName"/>:</span><input type="text"
									name="Q_orgName_SL" class="inputText"
									value="${param['Q_orgName_SL']}" /> <input type="hidden"
									name="orgId" class="inputText" value="${orgId}"
									readonly="readonly" value="${param['orgId']}" /> <input
									type="hidden" name="Q_path" class="inputText" value="${path}"
									readonly="readonly" value="${param['Q_path']}" /></li>
							</ul>
						</form>
					</div>
				</div>
			</div>
			<div class="panel-body">

				<c:set var="checkAll">
					<input type="checkbox" id="chkall" />
				</c:set>

				<table id="dicTable" class="table-grid table-list" id="0"
					cellpadding="1" cellspacing="1">
					<thead>
						<th width="30px"><input type="checkbox" id="chkall"></th>
						<th><spr:message code="sys.org.orgName"/></th>
						<th><spr:message code="sysOrg.dimension"/></th>
						<th><spr:message code="sysOrg.orgDesc"/></th>
						<th><spr:message code="sys.org.parentOrg"/></th>
						<th><spr:message code="sys.org.orgType"/></th>
						<th><spr:message code="list.manage"/></th>
					</thead>
					<tbody>
						<c:forEach items="${sysOrgList}" var="sysOrgItem"
							varStatus="status">
							<tr id="${sysOrgItem.orgId }" parentId="${sysOrgItem.orgSupId }"
								class="${status.index%2==0?'odd':'even'}">
								<td style="width: 30px; text-align: center;"><input
									type="checkbox" class="pk" name="orgId"
									value="${sysOrgItem.orgId}"></td>
								<td nowrap="nowrap">${f:returnSpace(sysOrgItem.path)} <a
									name="tree_a" class="tree-list-minus" onclick="treeClick(this)"><span
										class="tree-list-span">${sysOrgItem.orgName }</span></a>
								</td>
								<td>${sysOrgItem.demName }</td>
								<td>${sysOrgItem.orgDesc }</td>
								<td>${sysOrgItem.orgSupName }</td>
								<td><c:choose>
										<c:when test="${sysOrgItem.orgType==1}">
								        <spr:message code="sysOrg.group"/>
							   	</c:when>
										<c:when test="${sysOrgItem.orgType==2}">
								       <spr:message code="sysOrg.company"/> 
							   	</c:when>
										<c:when test="${sysOrgItem.orgType==3}">
								       <spr:message code="sysOrg.department"/>
							   	</c:when>
										<c:otherwise>
							              <spr:message code="sysOrg.otherOrgan"/>      
							   	</c:otherwise>
									</c:choose></td>
								<td nowrap="nowrap" style="width: 255px;"><a
									href="orgdel.ht?orgId=${sysOrgItem.orgId}" class="link del"><spr:message code="menu.button.del"/></a>
									<a
									href="edit.ht?orgId=${sysOrgItem.orgId}&demId=${sysOrgItem.demId}&flag=LE&path=${path}"
									class="link edit"><spr:message code="menu.button.edit"/></a> <a
									href="edit.ht?orgId=${sysOrgItem.orgId}&demId=${sysOrgItem.demId}&action=add"
									class="link add"><spr:message code="menu.button.addSub"/></a> <a
									href="get.ht?orgId=${sysOrgItem.orgId}&flag=1&path=${path}"
									class="link detail"><spr:message code="menu.button.detail"/></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<hotent:paging tableId="sysOrgItem" />

			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>