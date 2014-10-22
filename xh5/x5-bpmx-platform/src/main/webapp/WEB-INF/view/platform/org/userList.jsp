<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>User List</title>
		<script type="text/javascript">

		function getStatus(value,row,index){
			if(value=='actived')
				return "激活";
			if(value=='inactived')
				return "未激活";
			if(value=='locked')
				return "锁定";
		}
		</script>
	</head>
	<body  class="easyui-layout">
		<div data-options="region:'center',border:false" style="text-align:center;" >
				<div id="tb" style="padding:8px;height:auto;">
				    <div style="margin-bottom:5px">
				        <a href="edit.ht" class="easyui-linkbutton link" iconCls="icon-add" plain="true">添加</a>
				        <a href="#"  data-url="edit.ht"  class="easyui-linkbutton link" iconCls="icon-edit" plain="true">编辑</a>
				        <!-- 
				        <a href="${ctx}/platform/org/user/add.ht" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
				        <a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true">剪切</a>
				         -->
				         <a href="#"  data-url="get.ht"   class="easyui-linkbutton link" iconCls="icon-detail" plain="true">明细</a>
				        <a href="#" class="easyui-linkbutton link" data-url="remove.ht" iconCls="icon-remove" plain="true">移除</a>
				    </div>
				    <div class="foldBox searchBox" id="searchBox">
		                <div class="content">
							<form id="searchForm">
								<ul class="row">
									<li><span>姓名:</span><input type="text" name="Q^fullname_^SL"></li>
								</ul>
								<ul class="row">
									<li><span>账号:</span><input type="text" name="Q^account_^SL"></li>
								</ul>
								<ul class="row">
									<li><span>状态:</span>
										<select name="Q^status_^S">
											<option value="actived">激活</option>
											<option value="inactived">未激活</option>
											<option value="locked">锁定</option>
											<option value="deleted">已删除</option>
										</select>
									</li>
								</ul>
								<ul class="row">
									<li><span>来源:</span>
										<select name="Q^from_^S">
											<option value="actived">系统添加</option>
											<option value="import">导入</option>
											<option value="grade">分级添加</option>
										</select>
									</li>
								</ul>
								<div class="check_btn"><a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索</a></div>
							</form>
						</div>
				    </div>
				</div>
				<table id="userGridList" title="用户管理" class="easyui-datagrid"  fit=true singleSelect=false checkOnSelect=true 
					 url="${ctx}/platform/org/user/listJson.ht" iconCls="icon-save" pagination="true" toolbar="#tb">
				    <thead>
					    <tr>
					    	<th field="userId" checkbox="true" class="pk"></th>
						    <th field="account" width="80" sortable="true" sortName="account_" >账号</th>
						    <th field="fullname" width="80">姓名</th>
						    <th field="from" width="80" >来源</th>
						    <th field="status" width="80" formatter="getStatus">状态</th>
					    </tr>
				    </thead>
			    </table>
		</div>
	</body>
</html>