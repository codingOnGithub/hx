<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
	
	<%@include file="/commons/include/form.jsp" %>
	<f:js pre="js/lang/view/platform/system" ></f:js>
	<title></title>
	<script type="text/javascript" src="${ctx}/servlet/ValidJs?form=outMailUserSeting"></script>
	<script type="text/javascript">
		$(function() {
			function showRequest(formData, jqForm, options) { 
				return true;
			} 
			if(${outMailUserSeting.id ==null}){
				valid(showRequest,showResponse,1);
			}else{
				valid(showRequest,showResponse);
			}
			$("a.save").click(function() {
				var type=$("#recieveMailType").val();
				if(type=='pop3'){
					$('#imapHost').val('');
					$('#imapPort').val('');
				}else{
					$('#popHost').val('');
					$('#popPort').val('');
				}
				$('#mailType').val($('#recieveMailType').val());
				$('#outMailUserSetingForm').submit(); 
			});
			
			$("#mailAddress").blur(function(){
				var address=$("#mailAddress").val();
				if(address!=''){
					address=address.trim();
					var type=$("#recieveMailType").val();
					var s=address.substring(address.indexOf('@')+1,address.length+1).trim();
					$("#smtpHost").val('smtp.'+s);
					$("#popHost").val('pop'+'.'+s);
					if(type=='pop3'){
						if(s=='gmail.com'||s=='msn.com'||s=='live.cn'||s=='live.com'||s=='hotmail.com'){
							$("#popPort").val('995');
							$("#smtpPort").val('587');
						}else{
							$("#popPort").val('110');
							$("#smtpPort").val('25');
						}
					}else{
						if(s=='gmail.com'||s=='msn.com'||s=='live.cn'||s=='live.com'||s=='hotmail.com'){
							$("#imapPort").val('993');
							$("#smtpPort").val('587');
						}else{
							$("#imapPort").val('143');
							$("#smtpPort").val('25');
						}
						$("#imapHost").val('imap'+'.'+s);
					}
				}
			});
			
			$("#testConnect").click(function(){
				var mailAddress=$("#mailAddress").val();
				var password=$("#mailPass").val();
				var smtpHost=$("#smtpHost").val();
				var smtpPort=$("#smtpPort").val();
				var popHost=$("#popHost").val();
				var popPort=$("#popPort").val();
				var imapHost=$("#imapHost").val();
				var imapPort=$("#imapPort").val();
				var type=$("#recieveMailType").val();
				if(mailAddress==''&&password==''){
		    		alert($lang_system.outMail.please_input_mail_address);
		    		$("#mailAddress").focus();
		    		return false;
		    	}
				if(password==''){
		    		alert($lang_system.outMail.please_input_mail_password);
		    		$("#mailPass").focus();
		    		return false;
		    	}
				$.ligerDialog.waitting($lang_system.outMail.wail_link);
				$.post("test.ht?isEdit=1",{address:mailAddress,password:password,smtpHost:smtpHost,
				smtpPort:smtpPort,popHost:popHost,popPort:popPort,imapHost:imapHost,imapPort:imapPort,mailType:type},function(data){
					var obj=new com.hotent.form.ResultMessage(data);
					$.ligerDialog.closeWaitting();
					if(obj.isSuccess()){//成功
						$.ligerDialog.success($lang_system.outMail.email_success);
				    }else{//失败
				    	$.ligerDialog.err($lang.tip.error,$lang_system.outMail.test_link_fail,obj.getMessage());
				    	
				    }
				});
			});
			
			$("#recieveMailType").change(function(){
				var type=$("#recieveMailType").val();
				if(type=='pop3'){
					$("#imapH,#imapP").hide();
					$("#popH,#popP").show();
				}else{
					$("#imapP,#imapH").show();
					$("#popH,#popP").hide();
				}
			});
			
		});
	</script>
</head>
<body>
<div class="panel">
		<div class="panel-top">
			<div class="tbar-title">
			    <c:choose>
			        <c:when test="${outMailUserSeting.id !=null}">
			            <span class="tbar-label"><spr:message code="menu.button.edit"/><spr:message code="outMail.mailbox"/></span>
			        </c:when>
			        <c:otherwise>
			            <span class="tbar-label"><spr:message code="menu.button.add"/><spr:message code="outMail.mailbox"/></span>
			        </c:otherwise>
			    </c:choose>
			            
			   
				
			</div>
			<div class="panel-toolbar">
				<div class="toolBar">
					<div class="group"><a class="link save " id="dataFormSave" href="#"><span></span><spr:message code="menu.button.save"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link back" href="list.ht"><span></span><spr:message code="menu.button.back"/></a></div>
					<div class="l-bar-separator"></div>
					<div class="group"><a class="link test" id="testConnect" ><span></span><spr:message code="menu.button.testLink"/></a></div>
				</div>
			</div>
		</div>
		<div class="panel-body">
				<form id="outMailUserSetingForm" method="post" action="save.ht">
					
					<table class="table-detail" cellpadding="0" cellspacing="0" border="0">
						<tr>
							<th width="20%"><spr:message code="outMail.accountName"/>:  <span class="required">*</span></th>
							<td><input type="text" id="userName" name="userName" value="${outMailUserSeting.userName}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="outMailUserSeting.type"/>:  <span class="required">*</span></th>
							<td><select name="recieveMailType" id="recieveMailType">
									<option value="pop3" <c:if test="${outMailUserSeting.mailType=='pop3'}">selected="selected"</c:if> ><spr:message code="outMailUserSeting.type.pop3"/></option>
									<option value="imap" <c:if test="${outMailUserSeting.mailType=='imap' }">selected="selected"</c:if>> <spr:message code="outMailUserSeting.type.imap"/></option>
								</select>
							</td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="outMailUserSeting.mailAddress"/>:  <span id="required" class="">*</span></th>
							<td><input type="text" id="mailAddress" name="mailAddress" value="${outMailUserSeting.mailAddress}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="outMailUserSeting.mailPass"/>:  <span class="required">*</span></th>
							<td><input type="password" id="mailPass" name="mailPass" value="${outMailUserSeting.mailPass}"  class="inputText"/></td>
						</tr>
						<tr>
							<th width="20%"><spr:message code="outMailUserSeting.smtpHost"/>:  <span class="required">*</span></th>
							<td><input type="text" id="smtpHost" name="smtpHost" value="${outMailUserSeting.smtpHost}"  class="inputText"/></td>
						</tr>
						<tr>
				   			<th width="20%"><spr:message code="outMailUserSeting.smtpPort"/>:  <span class="required">*</span></th>
							<td><input type="text" id="smtpPort" name="smtpPort" value="${outMailUserSeting.smtpPort}"  class="inputText"/></td>
						</tr>
						<c:choose>
							<c:when test="${outMailUserSeting.mailType=='imap'}">
								<tr id="popH"  style="display: none">
									<th width="20%"><spr:message code="outMailUserSeting.popHost"/>:  <span class="required">*</span></th>
									<td><input type="text" id="popHost" name="popHost" value="${outMailUserSeting.popHost}"  class="inputText"/></td>
								</tr>
								<tr id="popP"  style="display: none">
									<th width="20%"><spr:message code="outMailUserSeting.popPort"/>:  <span class="required">*</span></th>
									<td><input type="text" id="popPort" name="popPort" value="${outMailUserSeting.popPort}"  class="inputText"/></td>
								</tr>
								<tr id="imapH" >
									<th width="20%"><spr:message code="outMailUserSeting.imapHost"/>:  <span class="required">*</span></th>
									<td><input type="text" id="imapHost" name="imapHost" value="${outMailUserSeting.imapHost}"  class="inputText"/></td>
								</tr>
								<tr id="imapP">
									<th width="20%"><spr:message code="outMailUserSeting.imapPort"/>:  <span class="required">*</span></th>
									<td><input type="text" id="imapPort" name="imapPort" value="${outMailUserSeting.imapPort}"  class="inputText"/></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr id="popH">
									<th width="20%"><spr:message code="outMailUserSeting.popHost"/>:  <span class="required">*</span></th>
									<td><input type="text" id="popHost" name="popHost" value="${outMailUserSeting.popHost}"  class="inputText"/></td>
								</tr>
								<tr id="popP">
									<th width="20%"><spr:message code="outMailUserSeting.popPort"/>:  <span class="required">*</span></th>
									<td><input type="text" id="popPort" name="popPort" value="${outMailUserSeting.popPort}"  class="inputText"/></td>
								</tr>
								<tr id="imapH" style="display: none">
									<th width="20%"><spr:message code="outMailUserSeting.imapHost"/>:  <span class="required">*</span></th>
									<td><input type="text" id="imapHost" name="imapHost" value="${outMailUserSeting.imapHost}"  class="inputText"/></td>
								</tr>
								<tr id="imapP" style="display: none">
									<th width="20%"><spr:message code="outMailUserSeting.imapPort"/>:  <span class="required">*</span></th>
									<td><input type="text" id="imapPort" name="imapPort" value="${outMailUserSeting.imapPort}"  class="inputText"/></td>
								</tr>
							</c:otherwise>
						</c:choose>
						
					</table>
					
					<input type="hidden" name="id" value="${outMailUserSeting.id}" />
					<input type="hidden" name="mailType" id="mailType" value="${outMailUserSeting.mailType}"/>
				</form>
		</div>
</div>
</body>
</html>
