
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<title>SYS_ZTREE明细</title>
<%@include file="/commons/include/customForm.jsp"%>
<script type="text/javascript">
	//放置脚本
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">SYS_ZTREE详细信息</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">SYS_ZTREE</td>
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:TYPENAME">分类名称</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.typename}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:ALIAS">分类别名</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.alias}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:IDKEY">用于tree字段的ID字段 如orgId</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.idkey}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:NAMEKEY">用于tree字段的Name字段 如orgName</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.namekey}</span></td>
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:PIDKEY">用于tree字段的parentID字段 如orgParentId</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.pidkey}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:URLS">默认加载数据的url</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.urls}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:ROOTPID">用做根节点的ID</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.rootpid}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:ROOTTITLE"> &nbsp;用于根节点的名称</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.roottitle}</span></td>
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:EXPANDSLEVEL"> 默认展开的层数</span>:</td>
   <td style="width:15%;" class="formInput">${sysZtree.expandslevel}</td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:DATAPARAMS">用于查询的params的返回值字段集合。如果为空，则表示返回查询的所有字段</span>:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag">${sysZtree.dataparams}</span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:CALLBACKS">默认的js代码 包括onclick、onRightClick、等</span>:</td>
   <td style="width:15%;" class="formInput"><textarea name="callbacks" validate="{empty:false}" readonly="readonly">${sysZtree.callbacks}</textarea></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:ASYNCS">对应于ztree的异步设置,方便扩展</span>:</td>
   <td style="width:15%;" class="formInput"><textarea name="asyncs" validate="{empty:false}" readonly="readonly">${sysZtree.asyncs}</textarea></td>
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:CHECKS">对应于ztree的check设置 &nbsp;,方便扩展</span>:</td>
   <td style="width:15%;" class="formInput"><textarea name="checks" validate="{empty:false}" readonly="readonly">${sysZtree.checks}</textarea></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:DATAS">对应于ztree的data设置,方便扩展</span>:</td>
   <td style="width:15%;" class="formInput"><textarea name="datas" validate="{empty:false}" readonly="readonly">${sysZtree.datas}</textarea></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:EDITS">对应于ztree的edit设置,方便扩展</span>:</td>
   <td style="width:15%;" class="formInput"><textarea name="edits" validate="{empty:false}" readonly="readonly">${sysZtree.edits}</textarea></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp"><span i18nkey="m:sys_ztree:VIEWS">对应于ztree的view设置,方便扩展</span>:</td>
   <td style="width:15%;" class="formInput"><textarea name="views" validate="{empty:false}" readonly="readonly">${sysZtree.views}</textarea></td>
  </tr>
 </tbody>
</table>
</body>
</html>

