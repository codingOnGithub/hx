$(function(){
	initLinkButtontag();
});

//查询
function handlerEditor()
{
	$("a.link[iconCls='icon-edit'],a.link[iconCls='icon-detail']").click(function(){
		   var $datagrid= $(this).parents('.datagrid').find('.easyui-datagrid').eq(0);
		   var $pk= $datagrid.find('th.pk');
		   var pkStr='Id';
		   var pk='';
		   if($pk.length>0){
			   pkStr= $pk.eq(0).attr('field');
		   }
		   
		   var records = $datagrid.datagrid('getChecked');
			
		   if(records==null||records.length>1){
				$.topCall.message({base:{type:'alert',title:'温馨提示',msg:'请选择一行记录!',icon:'error'}});
				return;
		   }
		   var url=$(this).attr('data-url');
		   if(url==null || url==''){
		 	    $.topCall.message({base:{type:'alert',title:'温馨提示',msg:'未找到配置参数[data-url]!',icon:'error'}});
				return;
		   }
		   if(url.indexOf('?')!=-1){
			    url+='&'+pkStr+'='+records[0][pkStr];
		   }else{
				url+='?'+pkStr+'='+records[0][pkStr];
		   }
		   window.location.href=url;
	});
}

function handlerSearch(){
	$("[iconCls='icon-search']").click(function(){
		if(!$(this).hasClass('disabled')) {
			var params=$("#searchForm").serializeArray();
			var data={};
			$(params).each(function(){
				var name=this.name;
				var value=this.value;
				var str='{"'+name+'":"'+value+'"}'
				if(value!=''){
					var json=eval('('+str+')');
					data=$.extend(data,json);
				}
			});
			$('#userGridList').datagrid('load',data);
		}
	});
}

function handlerDelSelect(){
	//单击删除超链接的事件处理
	$("a.link[iconCls='icon-remove']").click(function()
	{	
		var $datagrid= $(this).parents('.datagrid').find('.easyui-datagrid').eq(0);
		if($(this).hasClass('disabled')) return false;
		
		var $pk= $datagrid.find('th.pk');
		var pkStr='Id';
		var pk='';
		if($pk.length>0){
		   pkStr= $pk.eq(0).attr('field');
		}
		
	   var records = $datagrid.datagrid('getChecked');
			
	   if(records.length==0){
			$.topCall.message({base:{type:'alert',title:'温馨提示',msg:'请选择记录!',icon:'error'}});
			return;
	   }
		
	   var url=$(this).attr('data-url');
	   if(url==null || url==''){
	 	    $.topCall.message({base:{type:'alert',title:'温馨提示',msg:'未找到配置参数[data-url]!',icon:'error'}});
			return;
	   }
	   var delId="";
	   var len=records.length;
	   $(records).each(function(i){
			var obj=this;
			if(i<len-1){
				delId+=obj[pkStr] +",";
			}
			else{
				delId+=obj[pkStr];
			}
		});
	   if(url.indexOf('?')!=-1){
		    url+='&'+pkStr+'='+delId;
	   }else{
			url+='?'+pkStr+'='+delId;
	   }
	   $.topCall.message({base:{type:'confirm',title:'温馨提示',msg:'确认移除所选记录',fn:function(rtn){
		   if(rtn){
			   $.post(url,function(responseText){
				   alert(responseText);
				 
			   });
		   }else{
			   this.window.close;
		   }
	   }}});
		
	});
}

/**
 * 初始化信息
 */
function initLinkButtontag(){
	//编辑;
	handlerEditor();
	//查询
	handlerSearch();
	//移除选中行
	handlerDelSelect();
	
}

