GlobalMenu=function(){
	{
		this.rootMenu=null;
		this.menuMenu=null;
		this.treeNode=null;
		this.menuMenuFlat=null;
	};
	this.getMenu=function(treeNode,handler){
		this.treeNode=treeNode;
		var isRoot=0;
		if(treeNode.isRoot) isRoot=1;
		
		if(this.menuMenu==null){
			this.menuMenu=$.ligerMenu({ top: 100, left: 100, width: 150, items:
		        [{ text:$lang_js.globalType.menu.addType,key: $lang_js.globalType.menu.addType, click:handler  },
			        { text:$lang_system.globalType.tree.globalMenu.text_menu_del, click:handler },
			        {text:$lang_system.globalType.tree.globalMenu.text_menu_export,click:handler},
			        {text:$lang_system.globalType.tree.globalMenu.text_menu_import,click:handler},
			        {text:$lang_system.globalType.tree.globalMenu.text_menu_sort,click:handler},
			        { text: $lang_system.globalType.tree.globalMenu.text_menu_refresh, click: handler }]
			        });
			
			
		};
		if(this.menuMenuFlat==null){
				this.menuMenuFlat=$.ligerMenu({ top: 100, left: 100, width: 150, items:
			        [   { text: $lang_system.globalType.tree.globalMenu.text_menu_del, click:handler },
				        {text:$lang_system.globalType.tree.globalMenu.text_menu_export,click:handler},
				        {text:$lang_system.globalType.tree.globalMenu.text_menu_import,click:handler}]
				        });
		}
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 150, items:
		        [{ text: $lang_js.globalType.menu.addType, click: handler  },
		         {text:$lang_system.globalType.tree.globalMenu.text_menu_export,click:handler},
		         {text:$lang_system.globalType.tree.globalMenu.text_menu_import,click:handler},
		         {text:$lang_system.globalType.tree.globalMenu.text_menu_sort,click:handler},
		         { text: $lang_system.globalType.tree.globalMenu.text_menu_refresh, click: handler  }]
		         });
		};

		if(isRoot==1){
			return this.rootMenu;
		}
		else{
		
			if(treeNode.type==0){
			
				return this.menuMenuFlat;
			}
				
			return this.menuMenu;
		}
		
		
	};
};

FlowTypeMenu=function(){
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
                { text:$lang_js.globalType.menu.addType,key: 'addType', click: handler },
                { text:$lang_js.globalType.menu.editType,key: 'editType', click: handler  },
                { text:$lang_js.globalType.menu.delType,key: 'delType', click: handler }
                ]
                });
			
		
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 200, items:
		        [{ text:$lang_js.globalType.menu.addType,key: 'addType', click: handler  }]
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

ReportTypeMenu=function(){
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
			this.menuMenu = $.ligerMenu({top: 100, left: 100, width: 150, items:
                [
                { text: $lang_system.globalType.tree.reportTypeMenu.text_menu_add, click: handler },
                { text: $lang_system.globalType.tree.reportTypeMenu.text_menu_edit, click: handler  },
                { text: $lang_system.globalType.tree.reportTypeMenu.text_menu_del, click: handler }
                ]
                });
			
		
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 150, items:
		        [{ text: $lang_system.globalType.tree.reportTypeMenu.text_menu_add, click: handler  }]
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

/**
 * 数据字典菜单。
 * @returns {DiclMenu}
 */
DicMenu=function(){
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
			var items=[{ text: $lang_system.globalType.tree.dicMenu.text_menu_add, click:handler  },
				         { text: $lang_system.globalType.tree.dicMenu.text_menu_edit, click: handler },
				         { text: $lang_system.globalType.tree.dicMenu.text_menu_sort, click: handler },
					     { text: $lang_system.globalType.tree.dicMenu.text_menu_del, click:handler }];
			if(treeNode.type==0){
				items.splice(0, 1);
			}
			this.menuMenu=$.ligerMenu({ top: 100, left: 100, width: 160, items:items});
		};
	
		if(this.rootMenu==null){
			this.rootMenu=$.ligerMenu({ top: 100, left: 100, width: 160, items:
		        [{ text: $lang_system.globalType.tree.dicMenu.text_menu_add, click: handler  },
		         { text: $lang_system.globalType.tree.dicMenu.text_menu_sort, click: handler }]
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
