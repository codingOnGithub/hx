<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title>别名脚本编辑</title>
<f:js pre="js/lang/view/platform/system" ></f:js>
<style type="text/css">
	html,body{
		overflow:auto;
	}
	.para-info-table thead tr th{
		text-align:center;
	}
	.para-info-table tbody tr td{
		padding:5px;
	}
	input{
		width:180px;
		height:21px;
	}
</style>
<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js" ></script>
<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=conditionScript"></script>
<script type="text/javascript" src="${ctx}/js/hotent/platform/system/ScriptEdit.js"></script>
<script type="text/javascript">

	$(function(){
		$("select[name='className']").change(classNameChange).trigger("change");
		$('#aliasScriptEdit').ajaxForm({success:showResponse });
		checkAliasName();
	});
	
	//类名选择事件
	function classNameChange(){
		var className = $(this).val();
		if(!className)return;
		
		var match = /^.*\.(\w*)$/.exec(className),
			name = '';
		if(match!=null){
			name = match[1];
		}
		if(!name)return;
		name = name.toLowerCase().split("",1) + name.slice(1);
		$("input[name='classInsName']").val(name);
		var url = __ctx + '/platform/system/aliasScript/getMethodsByName.ht?name='+className;
		getMethods(url);
	};
	
	
	//类名选择事件
	function checkAliasName(){
		
		$("input[name='aliasName']").blur(function(){
			var me = $(this);
			var aliasName = me.val();
			var valueStr = $("input[name='id']").val();
			if(typeof(valueStr)!=undefined && valueStr!=null && valueStr!=''){  //修改时如果
				//$("input[name='aliasName']").attr("readonly","true");
				var oldName = $("#aliasNameOld").val();
				if(oldName==aliasName){
					return;                      //如果是修改情况，别名和以前的相同的就不用检测了
				}
			}
			var path =  __ctx + '/platform/system/aliasScript/checkAliasName.ht';
			if(typeof(aliasName)!=undefined&&aliasName!=null&&aliasName!=''){
				$.post(path,{aliasName:aliasName},function(data){			
					var json = eval("("+data+")");
					if(json.isSuccess==1){
						me.val("");
						alert(json.msg);
					}
			    });
			}
			
		});
	};
	
	
</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label">条件脚本编辑</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" onclick="save('aliasScriptEdit')" ><span></span>保存</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link preview" onclick="previewScript()" ><span></span>测试</a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span>返回</a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
		<form id="aliasScriptEdit" method="post" action="save.ht">
			<input id="typeName" type="hidden" value="text" />
			<div class="panel-detail">
			<input type="hidden" name="id" value="${aliasScript.id}"/>
			<input type="hidden" id="aliasNameOld" value="${aliasScript.aliasName}"/>
			<input type="hidden" id="methodName" value="${aliasScript.methodName}"/>
			<textarea class="hidden" name="argument">${aliasScript.argument}</textarea>
			<table class="table-detail" cellpadding="0" cellspacing="0" border="0">				 
				
				<tr>
					<th width="20%">脚本别名:</th>
					<td>
						<input type="text" name="aliasName" size="100" value="${aliasScript.aliasName}" validate="{'required':true}" />
					</td>
				</tr>
				<tr>
					<th width="20%">脚本描述:</th>
					<td>
						<input type="text" name="aliasDesc" size="100" value="${aliasScript.aliasDesc}"/>
					</td>
				</tr>
				
				<tr>
					<th width="20%">脚本所在类的类名:</th>
					<td>
						<select name="className">
							<c:forEach items="${implClasses}" var="implClass">
								<option value="${implClass.name}"<c:if test="${aliasScript.className eq implClass.name}"> selected="selected"</c:if>>${implClass.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%">类实例名:</th>
					<td>
						<input type="text" size="100" readonly="readonly" value="${aliasScript.classInsName}" name="classInsName" />
					</td>
				</tr>
				<tr>
					<th width="20%">方法名:</th>
					<td>
						<select name="methodName">
						</select>
					</td>
				</tr>
				<tr>
					<th width="20%">方法描述:</th>
					<td>
						<input type="text" size="100" name="methodDesc" value="${aliasScript.methodDesc}"/>
					</td>
				</tr>
				<tr>
					<th width="20%">返回值类型:</th>
					<td>
						<input type="text" size="100" readonly="readonly" name="returnType" value="${aliasScript.returnType}"/>
					</td>
				</tr>
				<tr>
					<th width="20%">参数信息:</th>
					<td id="paraInfo">
					
					</td>
				</tr> 
				<tr>
					<th width="20%">是否有效:</th>
					<td>
						<select name="enable">
							<option value="0"<c:if test="${aliasScript.enable eq 0}"> selected="selected"</c:if>>是</option>
							<option value="1"<c:if test="${aliasScript.enable eq 1}"> selected="selected"</c:if>>否</option>
						</select>
					</td>
				</tr>
			</table>
			</div>
		</form>
		</div>
	</div>
	
	<div class="hidden">
		<div id="para-txt">
			<table class="table-detail para-info-table" cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="25%">参数信息</th>
						<th width="25%">参数类型</th>
						<th width="25%">是否必填</th>
						<th width="25%">参数说明</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" name="paraName" value="${aliasScript.aliasName}"/></td>
						<td><span name="paraType"></span></td>
						<td><input type="checkbox" name="isRequired" value="1"></td>
						<td><input type="text" name="paraDesc"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div style="display: none;" id="template">
		<textarea name="script"></textarea>
		<select id="paraCt-temp" name="paraCt"></select>
	</div>
</body>
</html>

