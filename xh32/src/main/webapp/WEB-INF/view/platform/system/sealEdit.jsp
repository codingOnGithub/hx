<%--
	time:2012-08-29 11:26:00
	desc:edit the 电子印章
--%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	<%@include file="/commons/include/form.jsp" %>
	<%@include file="/js/msg.jsp"%>
	<title><spr:message code="title.edit"/><spr:message code="seal"/></title>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<c:set var="fullpath" value="E:/workspace/bpm/src/main/webapp" />
	<script type="text/javascript" src="${ctx}/js/hotent/CustomValid.js"></script>
	<script type="text/javascript" src="${ctx}/js/ntkosign/NtkoSignManage.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/SysDialog.js"></script>
	<script type="text/javascript" src="${ctx}/js/hotent/platform/system/FlexUploadDialog.js"></script>
	<style type="text/css">
		.displaynone{
			display:none;
		}
	</style>
	<script type="text/javascript">
		$(function() {
			var options={};
			if(showResponse){
				options.success=showResponse;
			}
			
			$('body').addClass('displaynone');
			var ntkoSignManage=new NtkoSignManage();
			var attachmentId='${seal.attachmentId}';
			ntkoSignManage.load('divSeal',attachmentId);
			var ntkoSignObj=ntkoSignManage.getntkoSignObject();
			
			if(!attachmentId.isEmpty()){
				$('#sealPassword').val(ntkoSignObj.Password);
				$('#sealPasswordConform').val(ntkoSignObj.Password);
			}
			
// 			var ntkoSignObject=ntkoSignManage.getntkoSignObject();
			var fmt=$('#sealForm').form();
// 			function showRequest(formData, jqForm, options) { 
// 				return true;
// 			} 

            //初始化选择显示区域
            initSelect($('#rightType').attr('value'));
			
			$("a.add").click(function(){
				var retval = ntkoSignManage.newSign();
				var ntkoSignObj=ntkoSignManage.getntkoSignObject();
				if(retval!=-1){
					$('#sealName').val(ntkoSignObj.SignName);
					$('#belongName').val(ntkoSignObj.SignUser);
					$('#sealPassword').val(ntkoSignObj.Password);
					$('#sealPasswordConform').val(ntkoSignObj.Password);
					$('#showImageId').val(retval);   //图片缩略ID
				}
			});
			
			
			$("a.upload").click(function(){
				ntkoSignManage.openFromLocal();
				var ntkoSignObj=ntkoSignManage.getntkoSignObject();
	        	$('#sealName').val(ntkoSignObj.SignName);
				$('#belongName').val(ntkoSignObj.SignUser);
				$('#sealPassword').val(ntkoSignObj.Password);
				$('#sealPasswordConform').val(ntkoSignObj.Password);
			});
			
			
			$("a.download").click(function(){
				ntkoSignManage.saveToLocal();
			});
			
			$("a.save").click(function() {
				var rtn=fmt.valid();
				if(!rtn){
					return false;
				}
				var ntkoSignObj=ntkoSignManage.getntkoSignObject();
				ntkoSignObj.SignName = $("#sealName").val();
				ntkoSignObj.SignUser = $("#belongName").val();
				ntkoSignObj.Password = $("#sealPassword").val();
				var result = ntkoSignManage.saveSign();
				if(result){
					$('#attachmentId').val(result);
					var url="save.ht";
					var form = $("#sealForm");
					form.ajaxForm(options);
					form.submit();
				}else{
					$.ligerDialog.error($lang_system.seal.edit.error_msg_upload);
					return false;
				}
			});
			
			$('body').removeClass('displaynone');
		});
		
		function showResponse(responseText) {
			var obj = new com.hotent.form.ResultMessage(responseText);
			if (obj.isSuccess()) {
				$.ligerDialog.confirm( obj.getMessage()+$lang.operateTip.continueOperate, $lang.tip.confirm, function(rtn) {
					if(rtn){
					}else{
						window.returnValue=true;
						this.close();
					}
					if(!rtn){
						window.returnValue=true;
						location.href='list.ht';
					}
					else{
						location.reload();
					}
				});
			} else {
				$.ligerDialog.err($lang.tip.error,$lang_system.seal.edit.error_msg_add,obj.getMessage());
			}
		}
		
		
		// 选择器
        function selectRightDlg(){
			var flag = $('#rightType').attr('value');
        	var ids = $('#rightIds').attr('value');
			var names = $('#rightNames').attr('value');
			if(flag=="user"){
    	        UserDialog({
    	        	selectUserIds:ids,
    	        	selectUserNames:names,
    		        callback:function(userIds,userNames){
    		        	$('#rightNames').attr('value',userNames);
    		        	$('#rightIds').attr('value',userIds);
    		        	$('#rightNameArea').attr('value',userNames);
    		        }
    	        });
        	}else if(flag=="role"){
        		RoleDialog({
        			ids:ids,
    	        	names:names,
    		        callback:function(userIds,userNames){
    		        	$('#rightNames').attr('value',userNames);
    		        	$('#rightIds').attr('value',userIds);
    		        	$('#rightNameArea').attr('value',userNames);
    		        }
    	        });
        	}else if(flag=="org"){    //flag == org
    	        OrgDialog({
    	        	ids:ids,
    	        	names:names,
    		        callback:function(userIds,userNames){
    		        	$('#rightNames').attr('value',userNames);
    		        	$('#rightIds').attr('value',userIds);
    		        	$('#rightNameArea').attr('value',userNames);
    		        }
    	        });
        	}
        }
		
        // 判断选择组织或用户
        function changeRight(obj){
        	var newType = obj.value;
        	var oldType = $('#rightTypeHidden').attr('value');              	        	
        	if(newType==oldType){
        		var userIds = $('#rightIdsHidden').attr('value');
        		var userNames =	$('#rightNamesHidden').attr('value');
        		$('#rightNames').attr('value',userNames);
	        	$('#rightIds').attr('value',userIds);
	        	$('#rightNameArea').attr('value',userNames);
        	}else{
           		$('#rightIds').attr('value','');
           		$('#rightNameArea').attr('value','');
           		$('#rightNames').attr('value','');
        	}
        	
        	initSelect(newType);
        }
        
        // 判断选择显示初始化
        function initSelect(newType){
        	if(newType=='all'){
        		$('#rightNameArea').show();   //选择显示区域
        		$('#selectRightDlg').hide();   //选择按钮隐藏
    			$('#rightNameArea').attr('value',$lang_system.seal.edit.all);
        		$('#rightNames').attr('value',$lang_system.seal.edit.all);
    		}else if(newType=='not'){
    			$('#selectRightDlg').hide();   //选择按钮隐藏
    			$('#rightNameArea').hide();   //选择显示区域隐藏
    		}else{
    			$('#rightNameArea').show();   //选择显示区域
    			$('#selectRightDlg').show();   //选择按钮显示
    		}
        }	   
        
        
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top" >
			<div class="tbar-title">
				<span class="tbar-label"><spr:message code="title.edit"/><spr:message code="seal"/></span>
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save" id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
					<div class="group"><a class="link add" ><span></span><spr:message code="seal.newButton"/></a></div>					
					<div class="group"><a class="link download" ><span></span><spr:message code="menu.button.export"/></a></div>
					<div class="group"><a class="link upload" ><span></span><spr:message code="menu.button.import"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="sealForm" method="post" action="save.ht">
					
						<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<th><spr:message code="seal.seal"/>：</th>
								<td><div id="divSeal"></div></td>
							<tr>
								<th width="20%"><spr:message code="seal.sealName"/>:  <span class="required">*</span></th>
								<td><input type="text" id="sealName" name="sealName" validate="{required:true,maxlength:50}" value="${seal.sealName}" tipId="sealNameError" class="inputText"/><span id="sealNameError"></span></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="seal.belongName"/>:  <span class="required">*</span></th>
								<td><input type="text" id="belongName" name="belongName" value="${seal.belongName}"  validate="{required:true,maxlength:50}" tipId="belongNameError" class="inputText"/><span id="belongNameError"></span></td>
							</tr>
							<tr>
								<th width="20%"><spr:message code="seal.sealPassword"/>:  <span class="required">*</span></th>
								<td><input type="password" id="sealPassword" name="sealPassword"  validate="{required:true,minlength:6,maxlength:30}" tipId="sealPasswordError" class="inputText"/><span id="sealPasswordError"></span></td>
							</tr>			
							<tr>
								<th width="20%"><spr:message code="seal.sealPassword.conform"/>:  <span class="required">*</span></th>
								<td><input type="password" id="sealPasswordConform" name="sealPasswordConform" validate="{equalTo:'sealPassword'}" tipId="sealPasswordConformError" class="inputText"/><span id="sealPasswordConformError"></span></td>
							</tr>
						
							<tr>
								<th width="20%"><spr:message code="seal.right.confirm"/>:</th>
								<td>
								   <table id='rightTable' width='100%'>
								        <tr>
											<th width="20%"><spr:message code="seal.rightType"/>:</th>
											<td><select id="rightType" name="rightType"
												style="width: 60px;" onchange="changeRight(this)">
													<c:forEach var="typeItem" items="${typeList}">
														<c:choose>
															<c:when test="${typeItem.id==sealRightMap.rightType}">
																<option value="${typeItem.id}" selected="selected">${typeItem.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${typeItem.id}">${typeItem.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
											</select></td>
										</tr>
										<tr>
											<th width="20%"><spr:message code="seal.rightNames"/>:</th>
											<td>
												<ul>
													<li style="float: left;">
													    <textarea rows="5" cols="150" id="rightNameArea" name="rightNameArea" readonly="readonly" class="inputText">${sealRightMap.rightNames}</textarea> 
														<input type="hidden" id="rightNames" name="rightNames" value="${sealRightMap.rightNames}" /> 
														<input type="hidden" id="rightIds" name="rightIds" value="${sealRightMap.rightIds}" /></li>
														
														<!-- 原始的数据 -->
														<input type="hidden" id="rightTypeHidden" name="rightTypeHidden" value="${sealRightMap.rightType}" /> 
														<input type="hidden" id="rightNamesHidden" name="rightNamesHidden" value="${sealRightMap.rightNames}" /> 
														<input type="hidden" id="rightIdsHidden" name="rightIdsHidden" value="${sealRightMap.rightIds}" /></li>
														
													<li style="float: left;">&nbsp;</li>
													<li style="float: left;"><a id='selectRightDlg' href='#' class='button' onclick="selectRightDlg();" ><span>...</span></a></li>
												</ul>
											</td>
										</tr>
								   </table>
								  							
								</td>
							</tr>
						</table>
						<!-- Hidden filed -->
						<input type="hidden" id="belongId" name="belongId" value="${seal.belongId}"/>
						<input type="hidden" id="sealId" name="sealId" value="${seal.sealId}"/>
						<input type="hidden" id="attachmentId" name="attachmentId" value="${seal.attachmentId}"/>
				        <input type="hidden" id="showImageId" name="showImageId" value="${seal.showImageId}"/>

				</form> 
		</div>
</div>
</body>
</html>
