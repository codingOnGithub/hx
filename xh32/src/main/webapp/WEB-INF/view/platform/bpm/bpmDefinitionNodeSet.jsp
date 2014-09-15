<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/commons/include/html_doctype.html" %>
<html>
<head>
<%@include file="/commons/include/form.jsp" %>    
    <title><spr:message code="bpmDefinition.nodeSet.title"/></title>
    
   	<%-- <link href="${ctx}/styles/default/css/jquery.qtip.css" rel="stylesheet" />
   	<link href="${ctx}/styles/default/css/definition.node.css" rel="stylesheet" /> --%>
   	<f:link href="jquery.qtip.css"></f:link>
   	<f:link href="definition.node.css"></f:link>
   	<f:js pre="js/lang/view/platform/bpm" ></f:js>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/SignRuleWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowRuleWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowVarWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ForkConditionWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowEventWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowForkJoinWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowMessageWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowWebServiceWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowReminderWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowApprovalItemWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/ViewSubFlowWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/InformTypeWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/hotent/platform/bpm/FlowSetWindow.js" ></script>
    <script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery.qtip.js" ></script>
    <script type="text/javascript">
    		var defId ="${bpmDefinition.defId}";
    		var actDefId ="${bpmDefinition.actDefId}";
    		var deployId= "${bpmDefinition.actDeployId}";
            var menu;
            //判断参数是否加载
            var isOtherParamFrm=false;
            var currentObj=null;
            $(function (){
            	
            	setContainerHeightWidth();
            	
                 $("div.flowNode").each(function(index){                	
                	$(this).bind({mouseenter:function(){
                		currentObj=$(this);
                	}});
                	currentObj=$(this);
                	var type=currentObj.attr("type"),title=currentObj.attr("title");
                	var items= getItems(index,type);
                	if(!items)return;
                	if(items.length==0)return;
                	var item,html=['<div class="edui-menu-body">'];
                	while(item=items.pop()){
                		if(item.id=='-'){
                			html.push('<div class="edui-menuitem edui-menuseparator"><div class="edui-menuseparator-inner"></div></div>');
                		}
                		else{
                			html.push('<div class="edui-menuitem edui-for-'+item.id+'" onmouseover="$(this).addClass(\'edui-state-hover\')" onmouseout="$(this).removeClass(\'edui-state-hover\')" onclick="clickHandler(this.id)" id="'+item.id+'"><div class="edui-box edui-icon"></div><div class="edui-box edui-label edui-menuitem-label">'+item.text+'</div></div>');
                		}
                	}
                	html.push('</div>');
   			      $(this).qtip({  
   			        content:{
   			        	text:html.join(''),
   			        	title:{
   			        		text:title
   			        		}
   			        	},  
   			        position: {
   			        	at:'center',
   			        	target:'event',
   			        	adjust: {
   			        		x:-15,
   			        		y:-15
   		   				},  
   		   				viewport: $(window)
   			        },
   			        show:{   			        	
	   			     	effect: function(offset) {
	   						$(this).slideDown(200);
	   					}
   			        },   			     	
   			        hide: {
   			        	event:'click mouseleave',
   			        	leave: false,
   			        	fixed:true,
   			        	delay:300
   			        	},  
   			        style: {
   			       	  classes:'ui-tooltip-light ui-tooltip-shadow'
   			        } 			    
   			      });
   			    }); 
              //  var  swidth = ${shapeMeta.width};
              // var width= $(document.body).width();//浏览器当前窗口文档body的高度 
         	  //	if(swidth>width)
     		  //		  $('.panel-container').css('width','${shapeMeta.width}px');
               
            });
            
            function setContainerHeightWidth(){
            	var h=document.documentElement.clientHeight ;
            	var w=$(".panel-container").width();
            	$("#divContainer").height(h-120).width(w);
            	
            	
            }
            
            function getItems(index,type) {
            	var items=[];
            	switch(type){
            		case "startEvent":				
            			items= [{id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.flowEvent}];
            			break;
            		/*
            		case "subStartEvent":
            			items= [ {id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.flowEvent}];
            			break;
            		*/
            		case "endEvent":
            			items= [{id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.flowEvent}];
            			break;
            		case "parallelGateway":
            			items= [];
            			break;
            		case "inclusiveGateway":
            		case "exclusiveGateway":
            			items= [{id:'flowCodition', text: $lang_bpm.bpmDefinition.nodeSet.flowCodition }];
            			break;
            		case "multiUserTask":
            			items= [{id:'-'},
            			        {id:'flowRule', text: $lang_bpm.bpmDefinition.nodeSet.flowRule },
                		        {id:'flowVote', text: $lang_bpm.bpmDefinition.nodeSet.flowVote },
                		        {id:'-'},
                		        {id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.flowEvent },
                		        {id:'flowDue', text: $lang_bpm.bpmDefinition.nodeSet.flowDue },
                		        {id:'-'},
                		        {id:'flowApproval', text: $lang_bpm.bpmDefinition.nodeSet.flowApproval },
                		        {id:'informType',text:$lang_bpm.bpmDefinition.nodeSet.informType.name}];
            			break;
            		case "userTask":
            			if(index==2){
            				items= [{id:'-'},
                    		        {id:'flowRule', text: $lang_bpm.bpmDefinition.nodeSet.flowRule },
                    		        {id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.flowEvent },
                    		        {id:'-'},
                    		        {id:'flowDue', text: $lang_bpm.bpmDefinition.nodeSet.flowDue },
                    		        {id:'flowApproval', text: $lang_bpm.bpmDefinition.nodeSet.flowApproval },
                    		        {id:'-'},
                    		        {id:'informType',text:$lang_bpm.bpmDefinition.nodeSet.informType.name}];
            			}else{
            				items= [{id:'-'},
                    		        {id:'flowRule', text: $lang_bpm.bpmDefinition.nodeSet.flowRule },
                    		        {id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.flowEvent },
                    		        {id:'-'},
                    		        {id:'flowDue', text: $lang_bpm.bpmDefinition.nodeSet.flowDue },
                    		        {id:'flowForkJoin', text: $lang_bpm.bpmDefinition.nodeSet.flowForkJoin.name },
                    		        {id:'-'},
                    		        {id:'flowApproval', text: $lang_bpm.bpmDefinition.nodeSet.flowApproval },
                    		        {id:'informType',text:$lang_bpm.bpmDefinition.nodeSet.informType.name}];
            			}
            			break;
            		case "webService":
            			items= [{id:'webServiceSet',text:$lang_bpm.bpmDefinition.nodeSet.webServiceSet.name}];
            			break;
            		case "email":
            			items= [{id:'flowMessage', text: $lang_bpm.bpmDefinition.nodeSet.flowMessage }];
            			break;
            		case "script":
            			items= [{id:'flowEvent', text: $lang_bpm.bpmDefinition.nodeSet.scriptSet }];
            			break;
            		case "callActivity":
            			items= [
            			        {id: 'viewSubFlow',text: $lang_bpm.bpmDefinition.nodeSet.viewSubFlow},
            			        {id: 'flowSet', text: $lang_bpm.bpmDefinition.nodeSet.flowSet}];
            			break;
            		case "subProcess":
            			items= [{id: 'flowEvent',text: $lang_bpm.bpmDefinition.nodeSet.flowEvent}];
            			break;
            	}
            	
            
            	return items;
            }

            var signRule;
            var flowRule;
            var forkCondition;

            function clickHandler(itemId){
            	//节点类型
            	var type=currentObj.attr("type");            	
            	//任务id
            	var activitiId=currentObj.attr("id");
            	var activityName=currentObj.attr("title");
            	
            	if(itemId=="flowVote" && type=="multiUserTask"){
            		SignRuleWindow({actDefId:actDefId,activitiId:activitiId,defId:defId});
            	}
            	else if(itemId=="flowRule"){
            		FlowRuleWindow({deployId:deployId,actDefId:actDefId,nodeId:activitiId,nodeName:activityName});
            	}
            	else if(itemId=="webServiceSet"){
            		//alert("webservice设置");
                	FlowWebServiceWindow({actDefId:actDefId,nodeId:activitiId,defId:defId});
            	}
            	else if(itemId=="flowCodition"){
            		ForkConditionWindow({defId:defId,deployId:deployId,nodeId:activitiId});
           		}
            	else if(itemId=="flowMessage"){
            		FlowMessageWindow({actDefId:actDefId,nodeId:activitiId});
            	}
            	else if(itemId=="flowEvent"){
            		//开始事件 后置脚本 type startEvent
            		//结束事件 前置脚本 endEvent
            		//用户任务 多实例任务 前后 userTask  multiUserTask
            		//脚本节点 脚本节点 script
            		FlowEventWindow({type:type,actDefId:actDefId,activitiId:activitiId,defId:defId});
            	}
            	else if(itemId=="flowDue"){
            		FlowReminderWindow({actDefId:actDefId,nodeId:activitiId});
            	}else if(itemId=="flowForkJoin"){
            		FlowForkJoinWindow({actDefId:actDefId,nodeId:activitiId,activityName:activityName});
            	}else if(itemId=="flowApproval"){ // 常用语设置
            		FlowApprovalItemWindow({activitiId:activitiId,defId:defId,actDefId:actDefId});
            	}else if(itemId=="viewSubFlow"){
            		ViewSubFlowWindow({nodeId:activitiId,defId:defId,actDefId:actDefId});
            	}else if(itemId=="informType"){
            		InformTypeWindow({nodeId:activitiId,defId:defId,actDefId:actDefId});
            	}else if(itemId=="flowSet"){
            		FlowSetWindow({nodeId:activitiId,defId:defId,actDefId:actDefId});
            	}
            	
            }
     </script> 
	<style type="text/css"> 
	     
	    div.flowNode{cursor:pointer;}
	</style>
</head>
<body>
	<div class="panel">
			<jsp:include page="incDefinitionHead.jsp">
			    	<jsp:param value="<spr:message code='bpmDefinition.nodeSet.title'/>" name="title"/>
			</jsp:include>
		<div id="panel-container"  class="panel-container" >
			<f:tab curTab="nodeSet" tabName="flow"/>
			<div id="divContainer" style="overflow: auto;" >
				<div style=" margin:20px auto 0;position: relative;background:url('${ctx}/bpmImage?deployId=${bpmDefinition.actDeployId}')  no-repeat;width:${shapeMeta.width}px;height:${shapeMeta.height+100}px;">
						${shapeMeta.xml} 
				</div>
			</div>
			</div>
	</div>
</body>
</html>
