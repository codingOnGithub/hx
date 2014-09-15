<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<title>编辑 lg多样化</title>
	<%@include file="/commons/include/customForm.jsp" %>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			var frm=$('#lgmoreLgForm').form();
			$("a.save").click(function() {
				frm.ajaxForm(options);
				if(frm.valid()){
					OfficePlugin.submit();
					if(WebSignPlugin.hasWebSignField){
						WebSignPlugin.submit();
					}
					$('#lgmoreLgForm').submit();
				}
			});
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm(obj.getMessage()+",是否继续操作","提示信息", function(rtn) {
					if(rtn){
						this.close();
					}else{
						window.location.href = "${ctx}/demo/LigerUI/lgmoreLg/list.ht";
					}
				});
			} else {
				$.ligerDialog.error(obj.getMessage(),"提示信息");
			}
		}
	</script>
</head>
<body>
<div class="panel">
	<div class="panel-top">
		<div class="tbar-title">
		    <c:choose>
			    <c:when test="${not empty lgmoreLgItem.id}">
			        <span class="tbar-label"><span></span>编辑lg多样化</span>
			    </c:when>
			    <c:otherwise>
			        <span class="tbar-label"><span></span>添加lg多样化</span>
			    </c:otherwise>	
		    </c:choose>
		</div>
		<div class="panel-toolbar">
			<div class="toolBar">
				<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span>保存</a></div>
				<div class="l-bar-separator"></div>
				<div class="group"><a class="link back" href="list.ht"><span></span>返回</a></div>
			</div>
		</div>
	</div>
	<form id="lgmoreLgForm" method="post" action="save.ht">
		<div type="custform">
			<div class="panel-detail">
				<table cellpadding="2" cellspacing="0" border="1" class="formTable">
 <tbody>
  <tr>
   <td colspan="8" class="formHead">lg多样化</td>
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">姓名:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;" isflag="tableflag"><input type="text" name="name" lablename="姓名" class="inputText" validate="{maxlength:50}" isflag="tableflag" value="${lgmoreLg.name}" /></span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">性别:</td>
   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="sex" lablename="性别" controltype="select" validate="{empty:false}"><option value="1" <c:if test='${lgmoreLg.sex=="1"}'>selected='selected'</c:if>>男</option><option value="0" <c:if test='${lgmoreLg.sex=="0"}'>selected='selected'</c:if>>女</option></select></span></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">生日:</td>
   <td style="width:15%;" class="formInput"><input name="birthday" type="text" class="Wdate" displaydate="0" validate="{empty:false}" value="<fmt:formatDate value='${lgmoreLg.birthday}' pattern='yyyy-MM-dd'/>" /></td>
  </tr>
  <tr>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">结婚日期:</td>
   <td style="width:15%;" class="formInput"><input name="jiehunDay" type="text" class="Wdate" displaydate="0" validate="{empty:false}" value="<fmt:formatDate value='${lgmoreLg.jiehunDay}' pattern='yyyy-MM-dd'/>" /></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">备注:</td>
   <td style="width:15%;" class="formInput"><textarea name="comment" validate="{empty:false}">${lgmoreLg.comment}</textarea></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">选择用户:</td>
   <td style="width:15%;" class="formInput">
    <div>
     <input name="selectUserID" type="hidden" lablename="选择用户ID" class="hidden" value="${lgmoreLg.selectUserID}" />
     <input name="selectUser" type="text" lablename="选择用户" validate="{empty:false}" readonly="readonly" linktype="4" refid="m:lgMore:selectUserID" value="${lgmoreLg.selectUser}" />
     <a href="javascript:;" class="link user" name="selectUser">选择</a>
     <a href="javascript:;" class="link reset" name="selectUser">重置</a>
    </div></td>
  </tr>
  <tr>
  <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">选择组织:</td>
   <td style="width:15%;" class="formInput">
    <div>
     <input name="selectOrgID" type="hidden" class="hidden" lablename="选择组织ID" value="${lgmoreLg.selectOrgID}" />
     <input name="selectOrg" type="text" lablename="选择组织" validate="{empty:false}" readonly="readonly" value="${lgmoreLg.selectOrg}" />
     <a href="javascript:;" class="link org" name="selectOrg">选择</a>
     <a href="javascript:;" class="link reset" name="selectOrg">重置</a>
    </div></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">选择角色:</td>
   <td style="width:15%;" class="formInput">
    <div>
     <input name="selectRoleID" type="hidden" class="hidden" lablename="选择角色ID" value="${lgmoreLg.selectRoleID}" />
     <input name="selectRole" type="text" lablename="选择角色" validate="{empty:false}" readonly="readonly" value="${lgmoreLg.selectRole}" />
     <a href="javascript:;" class="link role" name="selectRole">选择</a>
     <a href="javascript:;" class="link reset" name="selectRole">重置</a>
    </div></td>
   <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">选择岗位:</td>
   <td style="width:15%;" class="formInput">
    <div>
     <input name="selectJobID" type="hidden" class="hidden" lablename="选择岗位ID" value="${lgmoreLg.selectJobID}" />
     <input name="selectJob" type="text" lablename="选择岗位" validate="{empty:false}" readonly="readonly" value="${lgmoreLg.selectJob}" />
     <a href="javascript:;" class="link position" name="selectJob">选择</a>
     <a href="javascript:;" class="link reset" name="selectJob">重置</a>
    </div></td>
  </tr>
  <tr>
	  <td align="right" style="width:10%;" class="formTitle" nowrap="nowarp">下拉选项:</td>
	   <td style="width:15%;" class="formInput"><span name="editable-input" style="display:inline-block;padding:2px;" class="selectinput"><select name="selectOpinion" lablename="下拉选项" controltype="select" validate="{empty:false}"><option value="选择1" <c:if test='${lgmoreLg.selectOpinion=="选择1"}'>selected='selected'</c:if>>选择1</option><option value="选择2" <c:if test='${lgmoreLg.selectOpinion=="选择2"}'>selected='selected'</c:if>>选择2</option><option value="选择3" <c:if test='${lgmoreLg.selectOpinion=="选择3"}'>selected='selected'</c:if>>选择3</option><option value="选择4" <c:if test='${lgmoreLg.selectOpinion=="选择4"}'>selected='selected'</c:if>>选择4</option><option value="选择5" <c:if test='${lgmoreLg.selectOpinion=="选择5"}'>selected='selected'</c:if>>选择5</option></select></span></td>
  </tr>
 </tbody>
</table>
			</div>
		</div>
		<input type="hidden" name="id" value="${lgmoreLg.id}"/>
	</form>
</body>
</html>
