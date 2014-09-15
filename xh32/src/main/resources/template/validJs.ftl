<#if form?exists>
<#assign formName=form.formName>
<#assign fields=form.listField>

function showRequest(formData, jqForm, options) { 	
	return true;
} 
var __valid;
function showResponse(responseText, statusText)  { 
	var self=this;
	var obj=new com.hotent.form.ResultMessage(responseText);
	if(obj.isSuccess()){//成功
		$.ligerDialog.confirm( obj.getMessage()+$lang.operateTip.continueOperate,$lang.tip.msg,function(rtn){
			if(!rtn){
				var returnUrl=$("#returnUrl").val();
				if($("#returnUrl").length>0 && returnUrl!=""){
					location.href=returnUrl;
					return;
				}
				var linkBack=$("a.back");
				if(linkBack.length>0){
					var returnUrl=linkBack.attr("href");
					if(returnUrl!=""){
						location.href=returnUrl;
						return;
					}
				}
			}
			else{
				if(self.isReset==1){
					__valid.resetForm();
				}
			}
		});
		
    }else{//失败
    	$.ligerDialog.error( obj.getMessage(),$lang.tip.errorMsg);
    }
} 
function valid(showRequest,showResponse,isReset){

var options={};

if(showRequest )
	options.beforeSubmit=showRequest;

if(showResponse )
	options.success=showResponse;

if(isReset){
	options.isReset=isReset;
}

__valid=$("#${formName}Form").validate({
   
	rules: {
	<#list	fields as field>
		${field.formName}:{
			<#list	field.ruleList as rule>
			${rule.name}:<#if (rule.name=="required" && rule.ruleInfo!="true") || rule.name=="regex">"${rule.ruleInfo}"<#elseif rule.name=="equalTo" || rule.name=="compStartEndTime" || rule.name=="digitsSum">"#${rule.ruleInfo}"<#else>${rule.ruleInfo}</#if><#if rule_has_next>,</#if>
			</#list>
		}<#if field_has_next>,</#if>
		</#list>
	},
	messages: {
	<#list	fields as field>
		${field.formName}:{
			<#list	field.ruleList as rule>
			${rule.name}:"${rule.tipInfo}"<#if rule_has_next>,</#if>
			</#list>
		}<#if field_has_next>,</#if>
	</#list>
	},
	submitHandler:function(form){
		$(form).ajaxSubmit(options);
    },
    success: function(label) {
		label.removeClass("error");
	}
	});
}
<#else>
window.status=$lang.window.status;
</#if>
