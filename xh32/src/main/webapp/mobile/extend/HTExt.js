
/**
 * 扩展 container by cjj
 */

Ext.override(Ext.Container, {  
    getCmpByName : function(name) {  
        var getByName = function(container, name) {  
            var items = container.items;  
            if (items != null) {  
                for (var i = 0; i < items.getCount(); i++) {  
                    var comp = items.get(i);  
                    var cp = getByName(comp, name);  
                    if (cp != null)  
                        return cp;  
                    if (comp.getName && name == comp.getName()) {  
                        return comp;  
                        break;  
                    }  
                }  
            }  
            return null;  
        };  
        return getByName(this, name);  
    }  
}); 

Ext.ns('HT');
/**
 * 转换时间
 */
HT.parseLongTime = function(v,dateFormat){
	if(Ext.isEmpty(v))
		return "";
	return Ext.Date.format( new Date(v),(dateFormat?dateFormat:'Y-m-d H:i:s'));
};
HT.parseLongDate = function(v,dateFormat){
	return HT.parseLongTime((v,dateFormat?dateFormat:'Y-m-d'));
};

HT.loadingText =  lang.tip.loadingText;

/**
 * 加载
 */
HT.loadMask = function(message){
 	Ext.Viewport.setMasked({
	            xtype: 'loadmask',
	            message: message?message:HT.loadingText
	 });
}
/**
 * 取消
 */
HT.unMask = function(){
 	Ext.Viewport.unmask();
}


/** 添加消息提示组件 */
HT.toast = function(conf){
	var a ={};
	if(Ext.isObject(conf))Ext.applyIf(a, conf);else a.message = conf;
	Ext.Viewport.setMasked({
	        xtype: 'loadmask',
            cls: 'message',
            message: a.message,
            transparent: true,
            indicator: false 
	 });
	 setTimeout(function() {
	 	Ext.Viewport.unmask();
	 },a.timeout?a.timeout:1200);
}
	 
/**
 * 分页数据
 */
Ext.define('HT.Store',  {
	extend:'Ext.data.Store',
	name:'htStore',
	constructor: function (conf) {
		conf = conf || {};
			
		Ext.apply(conf,{
			model:conf.model,
			pageSize:conf.pageSize?conf.pageSize:10,// 对应的每页数据量
			currentPage:conf.currentPage?conf.currentPage:1,// 当前页
			autoLoad: conf.autoLoad?conf.autoLoad:true,
		    proxy: {
		        type: "ajax",
		        url: conf.url,
		        pageParam: conf.pageParam?conf.pageParam:'page', // 设置page参数，默认为page
		        limitParam: conf.limitParam?conf.limitParam:'pageSize', // 设置pageSize参数，默认为pageSize
		        reader: {
		            type: 'json',
		            rootProperty: conf.rootProperty?conf.rootProperty:'results',// 顶部数据
		            totalProperty :conf.totalProperty?conf.totalProperty:'totalCounts'// 总记录数
		        }
		    }	
	    });
    	this.callParent([conf]);
	}
});

/**
 * 分页
 */
Ext.define('HT.List', {
    extend: 'Ext.List',    
    name: 'htList',
    constructor: function (conf) {		
    	conf = conf || {};		
      	Ext.apply(conf,{
      			title:conf.title?conf.title:'',
                store: conf.store,
      			scrollable:true,
            	loadingText:conf.loadingText?conf.loadingText:HT.loadingText,
                emptyText:'<p class="no-searches">'+( conf.emptyText?conf.emptyText:lang.tip.noRecords)+'</p>'
         });
          	
    	this.callParent([conf]);
	}
});

/**
 * 分页
 */
Ext.define('HT.PageList', {
    extend: 'Ext.List',    
    name: 'htPageList',
    constructor: function (conf) {		
    	conf = conf || {};	

      	Ext.apply(conf,{
      			title:conf.title?conf.title:'',
                store: conf.store,
            	loadingText:conf.loadingText?conf.loadingText:HT.loadingText,
                plugins: [{
				　　　　xclass: 'Ext.plugin.PullRefresh',
				　　　　pullText: conf.pullText?conf.pullText:lang.tip.pullText,
				　　　　releaseText: conf.releaseText?conf.releaseText:lang.tip.releaseText,
				　　　　loading:  conf.loading?conf.loading:lang.tip.loading,
					 lastUpdatedText:lang.tip.lastUpdatedText,
					 loadingText :HT.loadingText,
					 loadedText:HT.loadingText,
					 lastUpdatedDateFormat:'Y-m-d h:iA',
					 listeners : {
						latestfetched : conf.latestfetched
					}
                },{
                	xclass: 'Ext.plugin.ListPaging',
            　　　　　　　		loadMoreText: conf.loadMoreText?conf.loadMoreText:lang.tip.loadMoreText,
            　　　　　　　　		noMoreRecordsText: conf.noMoreRecordsText?conf.noMoreRecordsText:lang.tip.noMoreRecordsText,
            　　　　　　　　		autoPaging: true// 设置为TRUE将自动触发
            　　　　　　}],
                emptyText: '<p class="no-searches">'+( conf.emptyText?conf.emptyText:lang.tip.noRecords)+'</p>',
                itemTpl:conf.itemTpl,
                listeners:[{
                	"beforerefresh" : function(){
                		this.loadMark.enable();
                	}}
                ]
                
         });
          	
    	this.callParent([conf]);
	}
});


/**
 * sencha Touch中的TextArea 支持滑动
 */
Ext.define('HT.TextArea', {
   extend: 'Ext.form.TextArea',
   xtype:'scrollTextArea',
   initialize: function() {
      this.callParent();
      this.element.dom.addEventListener(
         Ext.feature.has.Touch ? 'touchstart' : 'mousedown',
         this.handleTouchListener = Ext.bind(this.handleTouch, this),
         false);
      this.element.dom.addEventListener(
         Ext.feature.has.Touch ? 'touchmove' : 'mousemove',
         this.handleMoveListener = Ext.bind(this.handleMove, this),
         false);
      this.moveListenersAttached = true;
   },
   destroy: function() {
         if (this.moveListenersAttached) {
         this.moveListenersAttached = false;
         this.element.dom.removeEventListener(
            Ext.feature.has.Touch ? 'touchstart' : 'mousedown',
            this.handleTouchListener,
            false);
         this.element.dom.removeEventListener(
            Ext.feature.has.Touch ? 'touchmove' : 'mousemove',
            this.handleMoveListener,
            false);
         this.handleTouchListener = this.handleMoveListener = null;
      };
      this.callParent();
   },
   handleTouch: function(e) {
      this.lastY = e.pageY;
   },
  handleMove: function(e) {
      var textArea = e.target;
      var top = textArea.scrollTop <= 0;
      var bottom = textArea.scrollTop + textArea.clientHeight >= textArea.scrollHeight;
      var up = e.pageY > this.lastY;
      var down = e.pageY < this.lastY;
      this.lastY = e.pageY;
 
      if((top && up) || (bottom && down))    e.preventDefault();
  
      if(!(top && bottom))    e.stopPropagation();
  }
});




/**
 * 提交表单
 * 
 * @example
 * 
 * <pre>
 *  使用以下所示：
 * 	$postForm({
 * 	formPanel : this.formPanel,
 * 	scope : this,
 * 	url : __ctxPath + '/admin/saveBook.do',
 * 	params : {},
 * 	callback : function(fp, action) {
 * 		if (this.callback) {
 * 			this.callback.call(this.scope);
 * 		}
 * 		this.close();
 * 	}
 *  });
 * </pre>
 * 
 * @param {}
 *            conf
 */
var $postForm = function(conf) {
	var scope = conf.scope ? conf.scope : this,
		waitMsg = Ext.isEmpty(conf.waitMsg) ? lang.tip.waitMsg : conf.waitMsg,
		successMsg = Ext.isEmpty(conf.successMsg)
			? lang.tip.successMsg
			: conf.successMsg,
		failureMsg = Ext.isEmpty(conf.failureMsg)
			? lang.tip.failureMsg
			: conf.failureMsg,
	formPanel =conf.formPanel
	// Mask the form
    formPanel.setMasked({
            xtype: 'loadmask',
            message: waitMsg
        });
	formPanel.submit({
			scope : scope,
			url : conf.url,
			method : 'post',
			params : conf.params,
			// waitMsg : waitMsg,
			success : function(fp, action) {
				formPanel.unmask();
				if (conf.callback) {
					conf.callback.call(scope, fp, action);
				}
				if (conf.success) {
					conf.success.call(scope, fp, action);
				}
			},
			failure : function(fp, action) {
				formPanel.unmask();
				if (!Ext.isEmpty(action) && !Ext.isEmpty(action.msg)) {
					failureMsg = action.msg;
				}
				Ext.Msg.alert('', action.msg);	
				if (conf.callback) {
					conf.callback.call(scope, fp, action);
				}
			}
		});
};

/**
 * 扩展String的方法 var str=String.format("姓名:{0},性别:{1}","ray","男"); alert(str);
 * 
 * @return {}
 */
String.format=function(){
	var template=arguments[0];
	var args=arguments;
	var str=template.replace(/\{(\d+)\}/g,function(m,i){
		var k=parseInt(i)+1;
		return args[k];
	});
	return str;
};



// 增加连接超时为一分钟
Ext.override(Ext.data.Connection, {
	request : function(options) {
        options = options || {};
        options.timeout=1000*60;
        options.failure = function() {
        	Ext.Viewport.unmask();
			Ext.Msg.alert("错误", "链接失败");
		};
        var me = this,
            scope = options.scope || window,
            username = options.username || me.getUsername(),
            password = options.password || me.getPassword() || '',
            async, requestOptions, request, headers, xhr;

        if (me.fireEvent('beforerequest', me, options) !== false) {
            requestOptions = me.setOptions(options, scope);

            if (this.isFormUpload(options) === true) {
                this.upload(options.form, requestOptions.url, requestOptions.data, options);
                return null;
            }

            // if autoabort is set, cancel the current transactions
            if (options.autoAbort === true || me.getAutoAbort()) {
                me.abort();
            }

            // create a connection object
            xhr = this.getXhrInstance();

            async = options.async !== false ? (options.async || me.getAsync()) : false;

            // open the request
            if (username) {
                xhr.open(requestOptions.method, requestOptions.url, async, username, password);
            } else {
                xhr.open(requestOptions.method, requestOptions.url, async);
            }

            headers = me.setupHeaders(xhr, options, requestOptions.data, requestOptions.params);

            // create the transaction object
            request = {
                id: ++Ext.data.Connection.requestId,
                xhr: xhr,
                headers: headers,
                options: options,
                async: async,
                timeout: setTimeout(function() {
                    request.timedout = true;
                    me.abort(request);
                }, options.timeout || me.getTimeout())
            };
            me.requests[request.id] = request;

            // bind our statechange listener
            if (async) {
                xhr.onreadystatechange = Ext.Function.bind(me.onStateChange, me, [request]);
            }

            // start the request!
            xhr.send(requestOptions.data);
            if (!async) {
                return this.onComplete(request);
            }
            return request;
        } else {
            Ext.callback(options.callback, options.scope, [options, undefined, undefined]);
            return null;
        }
    }
});