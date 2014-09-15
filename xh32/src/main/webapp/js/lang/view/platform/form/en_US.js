var $lang_form = {
	bpmFormTemplate : {
		notSameName : 'template name can not be the same name!',
		initConfirm : 'Initialize the form template will result in a custom template information is lost, sure you initialize it? '
	},
	// Custom query
	bpmFormQuery : {
		selectTreeField : {
			selectRightInput : 'Please select the right of the input box!',
			selectLeftField : 'Please select the left of the field!',
			selectLeftOneField : 'can only choose a left field!'
		},
		displayField : 'Please fill in the fields mapped tree',
		conditionVal : 'Please fill condition field value',
		rtnField : 'Please select the return field',
		expandRightArea:'请先展开右边目标区域',
		get : {
			postParam : 'POST parameter',
			callMethod : 'method called',
			callMethodTip : 'This method is defined in the {0}',
			callbackMethod : 'callback',
			callbackMethodTip : 'check the return value of {0} is {1} an instance of class',
			otherNotes : 'Additional Notes',
			otherNotes1 : 'POST parameter is the page in {0} {1} is the number of articles per page, pagination queries need to pass these two parameters, do not pass means no paging queries;',
			otherNotes2 : '{0} is a universal form query alias {1} is used as the query field name, field value;',
			otherNotes3 : 'return value data in the error message {0} holds, no error is blank, {1} holds the result of the query, {2} Indicates whether paging (0: no paging, 1: Paging) {3} and {4}, respectively, the total number of records the query results and the total number of pages paged. '
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
		selectTableView : 'Please select a database table or view',
		notView : 'No view!',
		notSetColumn : 'the currently selected table (view) is not set to the displayed columns!',
		selectTreeField : {
			selectRightInput : 'Please select the right of the input box!',
			selectLeftField : 'Please select the left of the field!',
			selectLeftOneField : 'can only choose a left field!'
		},
		displayField : 'Please fill in the fields mapped tree',
		conditionVal : 'Please fill condition field value',
		rtnField : 'Please select the return field',
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
		resetTip : 'reset operation will delete the physical table and reset the state of the table is not generated. Whether to reset the table? ',
		del : {
			mainTip : 'if you delete the table? <br/> deletion will be deleted along with the child table?',
			subTip : 'if you delete the table?'
		},
		gen : {
			mainTip : 'will be generated along with the child table, you want to continue?',
			subTip : 'whether to generate the main table?',
			success : 'generate success',
			failure : 'Build failed'
		},
		exportWarn : 'Please select the main table for export!',
		ExternalTables:{
			notSameMame:'The main table and sub table name cannot be the same!',
			saveExternalTables:'Save external table definition of failure！',
			selectTable:'Please select a table!',
			isExist:'This table has been exist',
			waiting:'Is to query the data table, please wait...',
			manage:'The data source management'
		},
		edit : {
			backTip : 'custom table has been modified, whether it be saved?',
			generatorSubToMain : 'you sure you want to generate sub-tables do?',
			selectMainTable : 'Please select an master table!',
			selectSubTable:'Please select an subtable！',
			notFields : 'did not add field information',
			saveFailure : 'Add a custom table failed'
		}
	},
	// EditTable.js page
	editTable : {
		control : {
			text : 'single-line text box',
			textarea : 'multi-line text box',
			ckeditor : 'Rich Text Box',
			dictionary : 'Data Dictionary',
			userSingle : 'People Picker (radio)',
			userMulti : 'People Picker (multiple choice)',
			roleSingle : 'Role Selector (radio)',
			roleMulti : 'Role Selector (multiple choice)',
			orgSingle : 'Organization selector (radio)',
			orgMulti : 'Organization selector (multiple choice)',
			posSingle : 'positions selector (radio)',
			posMulti : 'positions selector (multiple choice)',
			hidedomaid :'hidden box',
			attachement : 'File Upload',
			select : 'drop-down option',
			checkbox : 'checkbox',
			radio : 'radio button',
			officeControl : 'office control',
			datepicker : 'date controls',
			processInstance : 'Process reference',
			webSignControl : 'webSign control',
			pictureShowControl : 'pictureShow control'
		},
		varFrom : {
			formInput : 'form input',
			scriptDisplay : 'script operation (display)',
			scriptHide : 'Script operator (not shown)',
			serialNumber : 'Serial'
		},
		dataType:{
			varchar:'文字',
			number:'数字',
			date:'日期',
			clob:'大文本'
		},
		uniqueName : 'field already exists',
		word : 'can only begin with a letter, allowing letters, numbers, and the underscore',
		quotation : 'can not have a quote',
		tableNameRequired : 'Table Name Required',
		tableNameMaxlength : 'table name up to 20 characters long.',
		tableDescMaxlength : 'comment up to 200 characters.',
		fieldNameRequired : 'Field Name Required',
		digits : 'Fill digital',
		charLenRequired : 'Text Length Required',
		intLenRequired : 'Integer Length Required',
		decimalLenRequired : 'Decimal Length Required'
	},
	// FieldManage.js page
	fieldManage : {
		notCheckedRow : 'have not checked out!',
		isRequired : 'as required columns that can not be deleted!'
	},
	// BpmFormTableTeam.js page
	bpmFormTableTeam : {
		delTeam : 'delete the current group settings?',
		moveAdd : 'This field has been added grouping, remove and then add!',
		selectAddTeam : 'Please select the group you want to add! ',
		selectTeamField : 'Please select the grouping field! '
	},
	index : {
		checkedField : 'Please select the field!',
		NotSpaces:'Index name cannot contain spaces！',
		idxName : 'index name first character is a letter, the maximum length of 18, only alphanumeric or underscore!'
	},
	bpmFormDef : {
		list : {
			newFormDef : 'New Forms',
			publish : 'Confirm publish? ',
			delFormDef : 'OK to delete the custom forms do? ',
			newVersion : 'confirm the new version now? ',
			setCategory : 'Set Category',
			selectCategory : 'Please select',
			setVersionNo : 'set the version number',
			message:'表单绑定的流程定义列表',
			delChoFormDef:'确认删除所选自定义表单吗?',
			delResult:'删除结果如下:',
			inputVersion : 'Please enter the version information!'
		},
		newFormDef : {
			select : 'Please you choose to generate the table'
		},
		edit : {
			sourceModeSave : 'You can not save the form source code mode',
			sourceModeNextStep:'You can not do the next step in source code mode',
			sourceModeActive : 'not in the source code mode switching pages',
			sourceModeDel : 'You can not delete the page source code mode',
			newPage : 'new page',
			page1 : 'Page 1',
			msg_save_fail:'保存表单设计失败',
			msg_valid_fail:'验证表单设计失败',
			changeTemplateSure:'Confirm change template?'
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
				length:'长度'
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
	// PageTab.js
	pageTab : {
		insertPage : 'is inserted after the current page',
		delCurPage : 'Delete this page',
		newPage : 'new page',
		page : 'Page'
	},
	// Permission.js
	permission : {
		user : 'user',
		role : 'role',
		org : 'Organization',
		orgMgr : 'head of the organization',
		pos : 'post',
		everyone : 'all',
		none : 'None',
		edit:'edit',
		required:'required',
		readonly:'readonly',
		hidden:'hidden',
		waiting_msg:'正在加载表单权限,请稍候...'
	},
	rights : {
		initRights : 'This will delete all the associated privileges this form, you sure you reset the permissions settings?'
	},
	imp : {
		selectXml : 'Please select the *. xml file to import!',
		importing : 'being imported, please wait ...',
		result : 'import results are as follows:',
		failure : 'Import failed!'
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
		argument:'Add a resource request to pass the appropriate parameters!',
		url:'Add a resource request to the resource URL!'
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