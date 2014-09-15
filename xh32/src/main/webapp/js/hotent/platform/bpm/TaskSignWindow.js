//任务会签窗口  依赖于LigerWindow 
function TaskSignWindow(conf)
{
	var win=null;
	if(!conf) conf={};
	var defSetting={
		buttons:[
		         {
		        	 text:$lang.button.ok,
		        	 handler:function()
		        	 {
		        		 var form=$('#signTaskForm');
		        		 if(form!=null)
		        		 {
		        			 var isAgree=$('#signTaskForm :radio[name="isAgree"]:checked').val();
		        			 var content=$('#signTaskForm textarea[name="content"]').val();
		        			
		        			 $.post(conf.postUrl,
		        			 {
		        				 isAgree:isAgree,
		        				 content:content
			        		 },function(){
			        			 $.ligerDialog.success($lang_bpm.task.taskSign.success,$lang.tip.success);
			        			 if(win) win.close();
			        		 });
		        		 }
		        		 else
		        		 {
		        			 if(win) win.close();
		        		 }
		        	 }
		         }
		       ],
		       onReady:function(){
		    	  //TODO
		       }
	};
	jQuery.extend(defSetting,conf);
	win=new HtWindow(defSetting);
	return win;
};

