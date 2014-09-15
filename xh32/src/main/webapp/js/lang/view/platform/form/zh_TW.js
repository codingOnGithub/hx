var $lang_form = {
	bpmFormTemplate : {
		notSameName : '模板名不能同名!',
		initConfirm : '初始化表單模板將會導致自定義模板信息丟失，確定初始化嗎？ '
	},
	// 自定義查詢
	bpmFormQuery : {
		selectTreeField : {
			selectRightInput : "請選擇右邊的輸入框!",
			selectLeftField : "請選擇左邊的字段!",
			selectLeftOneField : "只能選擇一個左邊的字段!"
		},
		displayField : "請填寫映射樹的字段",
		conditionVal : "請填寫條件字段的值",
		rtnField : "請選擇返回字段",
		expandRightArea:'请先展开右边目标区域',
		get : {
			postParam : "POST參數",
			callMethod : "調用的方法",
			callMethodTip : "該方法定義在{0}中",
			callbackMethod : "回調方法",
			callbackMethodTip : "查詢的返回值{0}是{1}類的一個實例",
			otherNotes : "其他說明",
			otherNotes1 : "POST的參數中{0}是頁碼{1}是每頁條數，分頁查詢時需要傳遞這2個參數，不傳表示不進行分頁查詢；",
			otherNotes2 : "{0}是通用表單查詢別名，{1}是用作查詢的字段名、字段值；",
			otherNotes3 : "返回值data中，{0}存放錯誤信息，沒有出錯則為空白，{1}存放了查詢的結果，{2}表示是否分頁(0:不分頁,1:分頁),{3}和{4}分別記錄了查詢結果的總數和分頁的總頁數。"
		},
		edit:{
			selectTableView:'请先选择数据库表或视图',
			waiting_msg:'正在查询，请等待...',
			alias_validate_null:'别名不能为空',
			alias_validate_zh:'别名不能为中文',
			form_edit_fail:'编辑通用表单失败'
		}
	},
	bpmFormDialog : {
		selectTableView : "請先選擇數據庫表或視圖",
		notView : "沒有找到視圖!",
		notSetColumn : "當前選擇的表(視圖)未設置顯示的列!",
		selectTreeField : {
			selectRightInput : "請選擇右邊的輸入框!",
			selectLeftField : "請選擇左邊的字段!",
			selectLeftOneField : "只能選擇一個左邊的字段!"
		},
		displayField : "請填寫映射樹的字段",
		conditionVal : "請填寫條件字段的值",
		rtnField : "請選擇返回字段",
		expandRightArea:'请先展开右边目标区域',
		callDialog:'对话框调用说明',
		paramDialog:'对话框参数传入',
		previewDialog:'对话框调用代码预览',
		selectDS:'请选择数据源!',
		selectDialog:'请选择参数【{0}】所要绑定的对话框！',
		selectReturnValue:'请选择参数【{0}】对应的对话框返回值！',
		dynamic_incoming:'动态传入',
		dynamic_incoming_qtip:'勾选表示此参数为动态传入的参数，否则为查询参数'
	},
	bpmFormTable : {
		resetTip : '重置操作將刪除物理表，並重置表的狀態為未生成。是否重置該表?',
		del : {
			mainTip : '是否刪除該表嗎?<br/>刪除將連同子表一起刪除?',
			subTip : '是否刪除該表嗎?'
		},
		gen : {
			mainTip : '將連同子表一起生成,是否繼續?',
			subTip : '是否生成該主表?',
			success : '生成成功',
			failure : '生成失敗'
		},
		exportWarn : "請選擇主表進行導出!",
		ExternalTables:{
			notSameMame:'主表和子表名稱不能相同!',
			saveExternalTables:'保存外部表定義失敗！',
			selectTable:'請先選擇壹個表!',
			isExist:'此表定義已經添加',
			waiting:'正在查詢數據表，請等待...',
			manage:'數據源管理'
		},
		edit : {
			backTip : '自定義表已修改，是否進行保存?',
			generatorSubToMain : '確定要生成子表嗎?',
			selectMainTable : "請選擇所屬主表!",
			selectSubTable:'請選擇子表！',
			notFields : "沒有添加字段信息",
			saveFailure : "添加自定義表失敗"
		}
	},
	// editTable.js頁面
	editTable : {
		control : {
			text : '單行文本框',
			textarea : '多行文本框',
			ckeditor : '富文本框',
			dictionary : '數據字典',
			userSingle : '人員選擇器(單選)',
			userMulti : '人員選擇器(多選)',
			roleSingle : '角色選擇器(單選)',
			roleMulti : '角色選擇器(多選)',
			orgSingle : '組織選擇器(單選)',
			orgMulti : '組織選擇器(多選)',
			posSingle : '崗位選擇器(單選)',
			posMulti : '崗位選擇器(多選)',
			hidedomaid:'隱藏域',
			attachement : '文件上傳',
			select : '下拉選項',
			checkbox : '複選框',
			radio : '單選按鈕',
			officeControl : 'office控件',
			datepicker : '日期控件',
			processInstance : '流程引用',
			webSignControl : 'WebSign控件',
			pictureShowControl : '圖片展示控件'
		},
		varFrom : {
			formInput : '表單輸入',
			scriptDisplay : '腳本運算(顯示)',
			scriptHide : '腳本運算(不顯示)',
			serialNumber : '流水號'
		},
		dataType:{
			varchar:'文字',
			number:'数字',
			date:'日期',
			clob:'大文本'
		},
		uniqueName : "字段已存在",
		word : "只能為字母開頭,允許字母、數字和下劃線",
		quotation : "不能有引號",
		tableNameRequired : "表名必填",
		tableNameMaxlength : "表名最多20 個字符.",
		tableDescMaxlength : "註釋 最多200字符.",
		fieldNameRequired : "字段名稱必填",
		digits : "填寫數字",
		charLenRequired : "文字長度必填",
		intLenRequired : "整數長度必填",
		decimalLenRequired : "小數長度必填"
	},
	// fieldManage.js頁面
	fieldManage : {
		notCheckedRow : "還沒有選中列!",
		isRequired : "該列為必填列,不能刪除!"
	},
	// BpmFormTableTeam.js頁面
	bpmFormTableTeam : {
		delTeam : "刪除當前分組設置?",
		moveAdd : "該字段已經添加分組,請先移除再添加!",
		selectAddTeam : "請選擇要添加的分組！",
		selectTeamField : "請選擇分組的字段！"
	},
	index : {
		checkedField : "請選擇字段!",
		NotSpaces:'索引名不能包含空格 ！',
		idxName : "索引名稱首字符為字母,最大長度18,只​​能字母數字或下劃線!"
	},
	bpmFormDef : {
		list : {
			newFormDef : "新建表單",
			publish : '確認發布嗎？ ',
			delFormDef : '確定刪除該自定義表單嗎？ ',
			newVersion : '確認新建版本嗎？ ',
			setCategory : '設置分類',
			selectCategory : '請選擇分類',
			setVersionNo : "設置版本號",
			message:'表单绑定的流程定义列表',
			delChoFormDef:'确认删除所选自定义表单吗?',
			delResult:'删除结果如下:',
			inputVersion : "請輸入版本信息!"
		},
		newFormDef : {
			select : "請您選擇要生成的表"
		},
		edit : {
			sourceModeSave : '不能在源代碼模式下保存表單',
			sourceModeNextStep:'不能在源代码模式下执行下一步',
			sourceModeActive : '不能在源代碼模式下切換頁面',
			sourceModeDel : '不能在源代碼模式下刪除頁面',
			newPage : "新頁面",
			page1 : "頁面1",
			msg_save_fail:'保存表单设计失败',
			msg_valid_fail:'验证表单设计失败',
			changeTemplateSure:'确认更换模板吗?'
		},
		designEdit:{
			valid_rule_getting:'正在获取验证规则...',
			serial_number_getting:'正在获取流水号...',
			field_used:'字段名已在使中，需要引用并设置该字段吗？',
			field_abandoned:'字段名已被弃置，需要重新引用和设置该字段吗？',
			validRule:'验证规则',
			none:'无',
			value:'值',
			option:'选项',
			column:'列数',
			row:'行数',
			checkOption:'复选框选项',
			radioOption:'单选按钮选项',
			selectOption:'下拉框选项',
			selectDictionary:'选择数据字典',
			otherSetting:'其他设定',
			singleSelect:'单选',
			showCurUser:'显示当前用户',
			inputText:'按钮文本',
			directUpLoad:'直接附件上传',
			showCurOrg:'显示当前部门',
			subTable_insert_error:'子表中不能插入子表!',
			onlyone_websigncontrol:'注意:页面只允许有一个web印章控件!',
			col:'列',
			controlWidth:'控件宽度',
			controlHeight:'控件高度',
			dataFormat:'数据格式',
			field:{
				comment:'字段注释',
				name:'字段名称',
				dataType:'数据类型'
			},
			options:{
				required:'必填',
				list:'显示到列表',
				query:'作为查询条件',
				flowVar:'是否流程变量',
				reference:'作为超连接',
				webSign:'是否支持Web印章验证'				
			},
			subTable:{
				tableName:'明细表名称',
				tableDesc:'明细表注释',
				addDataTemplate:'添加数据模式',
				displaySubTable:'默认显示子表',
				blockModel:'块模式',
				inlineModel:'行模式',
				windowModel:'弹窗模式'
			},
			aboutDate:'日期相关',
			dateString:'日期字符',
			dateFormat:'格式',
			displayDate:'当前日期字符',
			displayScript:'脚本(显示)',
			valueSource:'值来源'
		},
		dataFormat:{
			varchar:{
				length:'长度',
			},
			number:{
				thousandth:'千分位',
				coin:'货币',
				rmb:'￥人民币',
				dollar:'$美元',
				decimal_:'小数位',
				integer:'整数位'
			},
			date:{
				curDate:'当前时间'
			}
		}
	},
	// pageTab.js
	pageTab : {
		insertPage : '在當前頁後插入',
		delCurPage : '刪除當前頁',
		newPage : '新頁面',
		page : '頁'
	},
	// Permission.js
	permission : {
		user : '用戶',
		role : '角色',
		org : '組織',
		orgMgr : '組織負責人',
		pos : '崗位',
		everyone : '所有人',
		none : '無',
		edit:'编辑',
		required:'必填',
		readonly:'只读',
		hidden:'隐藏',
		waiting_msg:'正在加载表单权限,请稍候...'
	},
	rights : {
		initRights : "這個操作刪除此表單所有相關的權限,是否確定重置權限設置?"
	},
	imp : {
		selectXml : "請選擇 *.xml文件進行導入!",
		importing : "正在導入中,請稍候...",
		result : "導入結果如下:",
		failure : "導入失敗!"
	},
	bpmDataTemplate:{
		save:{
			sortField:'排序字段不能设置超过3个，请检查！',
			manageField:'功能按钮出现重复的类型，请检查！'
		},
		preview:'请设置完信息保存后预览!',
		edit:{
			empty:'请设置完信息保存后编辑模板!'
		},
		filter:{
			tip1:'该脚本为 groovyScript脚本 ，返回值为可执行的sql语句',
			tip2:'其中的map为系统所封装的一个参数',
			tip3:'在脚本中使用map.get("field")可以获取表单提交时所携带的field参数值，脚本应拼接并返回任意的可执行的sql语句.返回的sql中要含有ID',
			commonCondition:'普通条件',
			alert:'请输入过滤名称或key!'
		},
		editTemplate:{
			msg_edit_fail:'编辑自定'
		},
		importData:{
			waitting_msg:'正在导入中,请稍候...',
			file_validate_msg:'请选择 *.xls或.xlsx文件进行导入!'
		},
		dateRange:'日期范围'
	},
	addResourceDialog:{
		argument:'添加資源請求，要求傳入適當的參數!',
		url:'添加資源請求，要求傳入要添加的資源的URL!'
	},
	Export:{
		exportAll:'导出全部数据',
		exportSelected:'导出选中数据',
		exportCurrent:'导出当前页数据',
		exportField:'导出字段',
		alert:'请选择导出的!',
		notRecord:'当前页没有记录!'
	}
};