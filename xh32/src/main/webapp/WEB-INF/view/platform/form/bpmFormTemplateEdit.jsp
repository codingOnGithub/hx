<%--
	time:2012-01-09 16:35:25
	desc:edit the 表单模板
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<%@include file="/commons/include/form.jsp"%>
<title><spr:message code="operator.edit"/><spr:message code="bpmFormTemplate.title"/></title>
<script type="text/javascript"
	src="${ctx}/servlet/ValidJs?form=bpmFormTemplate"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/codemirror.js"></script>
	<script type="text/javascript" src="${ctx}/js/javacode/InitMirror.js"></script>
<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${bpmFormTemplate.templateId==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				if(InitMirror.editor!=null){
					$('#html').text(InitMirror.editor.getCode());
				}
				$('#bpmFormTemplateForm').submit(); 
			});
			
			$('#selectMacro').change(function() {
				changeDesc();
			});
			changeDesc();
		});
		
		function show(templateType) {
			if(templateType =='main'|| templateType =='subTable') {
				$('#macro').css('display', '');
				changeDesc();
			} else {
				$('#macro').css('display', 'none');
			}
		}
		
		function changeDesc() {
			$('#macroDesc').text(($('#macroDescs').val($('#selectMacro').val()).find('option:selected').text()));
		}
		
	</script>
</head>
<body>
	<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
				<span class="tbar-label"> <c:choose>
						<c:when test="${bpmFormTemplate.templateId==null}">
							<spr:message code="operator.add"/><spr:message code="bpmFormTemplate.title"/>
						</c:when>
						<c:otherwise>
							<spr:message code="operator.edit"/><spr:message code="bpmFormTemplate.title"/>
						</c:otherwise>
					</c:choose>
				</span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group">
						<a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a>
					</div>
					<div class="l-bar-separator"></div>
					<div class="group">
						<a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form id="bpmFormTemplateForm" method="post" action="save.ht">

				<table class="table-detail" cellpadding="0" cellspacing="0"
					border="0">
					<tr>
						<th width="20%"><spr:message code="bpmFormTemplate.templateName"/>:<span class="required">*</span>
						</th>
						<td><input type="text" id="templateName" name="templateName"
							value="${bpmFormTemplate.templateName}" class="inputText" /></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormTemplate.alias"/>:<span class="required">*</span>
						</th>
						<td><input type="text" id="alias" name="alias"
							value="${bpmFormTemplate.alias}" class="inputText" /><span
							id="aliasInfo" style="color: red"></span></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormTemplate.templateType"/><span class="required">*</span>
						</th>
						<td><input type="radio" name="templateType" value="main"
							onclick="show('main')"
							<c:if test="${bpmFormTemplate.templateType=='main' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.main"/> &nbsp; <input type="radio" name="templateType"
							value="subTable" onclick="show('subTable')"
							<c:if test="${bpmFormTemplate.templateType=='subTable' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.subTable"/> &nbsp; <input type="radio" name="templateType" value="macro"
							onclick="show('macro')"
							<c:if test="${bpmFormTemplate.templateType=='macro' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.macro"/> &nbsp; <input type="radio" name="templateType" value="list"
							onclick="show('list')"
							<c:if test="${bpmFormTemplate.templateType=='list' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.list"/> &nbsp; <input type="radio" name="templateType"
							value="detail" onclick="show('detail')"
							<c:if test="${bpmFormTemplate.templateType=='detail' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.detail"/> &nbsp; <input type="radio" name="templateType"
							value="tableManage" onclick="show('tableManage')"
							<c:if test="${bpmFormTemplate.templateType=='tableManage' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.tableManage"/>
							 &nbsp; <input type="radio" name="templateType"
							value="dataTemplate" onclick="show('dataTemplate')"
							<c:if test="${bpmFormTemplate.templateType=='dataTemplate' }"> checked="checked"</c:if> />
							<spr:message code="bpmFormTemplate.templateType.dataTemplate"/></td>
					</tr>
					<tbody id="macro"
						style="<c:if test="${bpmFormTemplate.templateType =='macro'}">display:none</c:if>
													<c:if test="${bpmFormTemplate.templateType =='list'}">display:none</c:if>
													<c:if test="${bpmFormTemplate.templateType =='detail'}">display:none</c:if>">
						<tr>
							<th width="20%"><spr:message code="bpmFormTemplate.macro"/>:</th>
							<td><select name="macroTemplateAlias" id="selectMacro">
									<c:forEach items="${macroTemplates}" var="template">
										<option value="${template.alias}"
											<c:if test="${bpmFormTemplate.macroTemplateAlias == template.alias}">selected</c:if>>${template.templateName}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="bpmFormTemplate.macroDesc"/>:</th>
							<td id="macroDesc"></td>
						</tr>
					</tbody>
					<tr>
						<th width="20%"><spr:message code="bpmFormTemplate.html"/>:</th>
						<td><textarea id="html" name="html" cols=100 rows=20 codemirror="true" mirrorheight="400px">${fn:escapeXml(bpmFormTemplate.html)}</textarea></td>
					</tr>
					<tr>
						<th width="20%"><spr:message code="bpmFormTemplate.templateDesc"/>:</th>
						<td><textarea id="templateDesc" name="templateDesc" cols=100
								rows=10>${bpmFormTemplate.templateDesc}</textarea></td>
					</tr>
				</table>
				<input type="hidden" name="templateId"
					value="${bpmFormTemplate.templateId}" />
			</form>

		</div>
	</div>
	<select id="macroDescs" style="display: none">
		<c:forEach items="${macroTemplates}" var="template">
			<option value="${template.alias}">${template.templateDesc}</option>
		</c:forEach>
	</select>
</body>
</html>
