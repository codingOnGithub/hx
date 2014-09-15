
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择要打印的模块</title>
<%@include file="/commons/include/get.jsp"%>
<script type="text/javascript" src="${ctx}/js/util/easyTemplate.js"></script>
<script type="text/javascript" src="${ctx}/js/lodop/LodopFuncs.js"></script>
<script type="text/javascript">
	
	var confObj = null;
	var modules = new Array();
	var newModules = new Array();
	$(function(){
		confObj = window.dialogArguments;
 	    if(confObj!=undefined && confObj!=null && confObj!=""){      //模态窗口的接收方法
 	    	if(confObj.modules!=undefined && confObj.modules!=null && confObj.modules!=""){
 	    		modules = confObj.modules;
 	    	}else{
 	    		modules = getShowModules("","","","","");
 	    		confObj.modules=modules	
 	    	} 	    	
 	    	if(confObj.root=='undefined' || confObj.root==null){
 	    		confObj.root="";
 	    	}      
 	    }else{        
 	    	modules = getShowModules("","","","","");
 	    	confObj ={
 	    		root:"",
 	    		modules:modules	
 	    	}; 	    	
 	    }
 	    
 	    var divObj = $('#tbodyModules');
 	    var html ="";
 	    if(modules.length>0){
 	    	for ( var i = 0; i < modules.length; i++) {
 	 			var module = modules[i];
 	 			module.key = i;
 	 			module.isShow = false;
 	 			newModules.push(module);
 	 		}
 			var html = "";
 			var template = $("#txtReceiveTemplate").val();
 			html = easyTemplate(template,newModules).toString();
 			
 	    }else{
 	    	html = '<tr><td colspan="2" >暂时没有要打印的模块</td></tr>';
 	    }
 	    divObj.html(html);
 	    
	}); 
	
	function returnModules(){

		$("input[name='id']").each(function(index){
			if($(this).attr("checked")){     //选中
				var num = $(this).attr("value");
				for ( var i = 0; i < newModules.length; i++) {
		 			var module = modules[i];
		 			if(num == module.key){
		 				module.isShow = true;
		 				break;
		 			}
		 		}
			}
		});
		
		var myTop = $("input[name='myTop']").val();
		/* if(isNaN(top)){
			alert("上下边距：请输入数字！");
			return;
		} */
		var myLeft = $("input[name='myLeft']").val();
		/* if(isNaN(left)){
			alert("左右边距：请输入数字！");
			return;
		} */
		var myWidth = $("input[name='myWidth']").val();
		var myHeight = $("input[name='myHeight']").val();
		var root = confObj.root;
		var conf ={
				root:root,
				myTop:myTop,
				myLeft:myLeft,
				myWidth:myWidth,
				myHeight:myHeight,
				modules:newModules
		};
		
	    window.returnValue = conf;    //返回
		window.close();
	} 
	
</script>
</head>
<body>
	
	<div align="center">
		<div class="panel">
			<table class="table-grid table-list" cellpadding="1" style="margin-top:10px;"  cellspacing="1" id="tableModules"  width="96%" >
				<thead>
	   				<tr>
	   					<th><input type="checkbox" id="chkall"/></th>
					    <th>打印模块名称</th>
	   				</tr>
	   			</thead>
				<tbody id="tbodyModules"></tbody>
		    </table>	
		    
			<table class="table-grid table-list" cellpadding="1" style="margin-top:10px;"  cellspacing="1" id="tableModules"  width="96%" >
				<thead>
	   				<tr>	   					
					    <th colspan="2">
					    	打印边距设置
					    </th>
	   				</tr>
	   			</thead>
				<tr>
		  			<td>
		  				上面边距：<input type="text" size="20" name="myTop" value="20px" /> 
		  			</td>
		  			<td>
		  				左面边距：<input type="text" size="20" name="myLeft" value="20px" /> 
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				打印宽度：<input type="text" size="20" name="myWidth" value="RightMargin:20px" /> 
		  			</td>
		  			<td>
		  				打印高度：<input type="text" size="20" name="myHeight" value="BottomMargin:20px" /> 
		  			</td>
		  		</tr>
		  		<tr>	   					
				    <td colspan="2" align="left">
				      <font color="red">相关设置说明：<br/>
				      1、 所有单位：in(英寸，缺省长度时单位)、cm(厘米) 、mm(毫米) 
				                     、pt(磅)、px(1/96英寸)和%(百分比)，如“10mm”表示10毫米。
				      2、  打印宽度：可以用RightMargin关键字转义为打印区域相对于纸
				                      张的“右面边距”， 如“RightMargin:0.9cm”。
				      3、 打印高度：可以用BottomMargin关键字转义为打印区域相对于纸
				                      张的“下面边距”， 如“BottomMargin:9mm”。
				      </font> 
				    </td>
	   			</tr>
		    </table>
			
		    
		</div>
		<div position="bottom"  class="bottom" style='margin-top:10px'>
				<a class='button' id="btnSelect" onclick="returnModules()" ><span class="icon ok"></span><span >选择</span></a>
				<a class="button" id="btnClose" style='margin-left:10px;'  onclick="window.close()"><span class="icon cancel"></span><span >取消</span></a>
		</div> 
	</div>
	
	<!-- HMTL模板拼写内容 -->
	<textarea id="txtReceiveTemplate"  style="display: none;">
			<#list data as myObj>
				<tr>
		  			<td>
		  				<input type="checkbox" class="pk" name="id" value="\${myObj.key}">
		  			</td>
		  			<td>
		  				\${myObj.name}
		  			</td>
		  		</tr>
   			</#list>
    </textarea>
	
	
</body>
</html>


