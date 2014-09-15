FormDefMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		if(this.menuMenu==null){
			this.menuMenu = $.ligerMenu({top: 100, left: 100, width: 200, items:
                [
                { text:$lang_js.globalType.menu.addType,key:'addType', click: handler },
                { text:$lang_js.globalType.menu.editType,key: 'editType', click: handler  },
                { text:$lang_js.globalType.menu.delType,key: 'delType', click: handler } ]
                });
			
		
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 200, items:
		        [{ text: $lang_js.globalType.menu.addType,key:'addType', click: handler  }]
		         });
		};

		if(isRoot==1){
			return this.rootMenu;
		}
		else{
			return this.menuMenu;
		}
	};
};
