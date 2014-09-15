var $lang_system = {
	identity : {
		rule_validate_msg : 'template name can not be the same name!'
	},
	sysTemplate : {
		list : {
			export_validate_msg : 'Please select the template to export!'
		},
		import_ : {
			file_validate_msg : 'Please select the *. xml file to import!',
			waitting_msg : 'being imported, please wait ...',
			result : 'import results are as follows:'
		}
	},
	script : {
		list : {
			update_waitting_msg : 'Updating, please wait ...'
		},
		dialog : {
			validate_msg_select : 'do not select the script!'
		},
		import_ : {
			file_validate_msg : 'Please select the *. xml file to import!',
			waitting_msg : 'being imported, please wait ...',
			result : 'import results are as follows:',
			error : 'custom table import failed'

		}

	},
	conditionScript : {
		dialog : {
			validate_msg_select : 'Please select a conditional script! '
		},
		edit:{
			selectDialog:'Please select dialog which parameter【{0}】 need to bind',
			selectReturnValue:'Please select return value which parameter【{0}】 binded'
		}
	},
	sysTestResult : {
		list : {
			validate_msg_args : 'there is a required parameter not filled',
			validate_msg_user : 'must select the users',
			tip_msg_start : 'may take a long time, whether to continue?',
			waitting : 'Please wait, is testing ...',
			validate_msg_require_actDefKey : 'must fill process definition KEY'
		}
	},
	scheduler : {
		jobList : {
			validate_msg_select_require : 'Please select the record! ',
			validate_msg_select_single : 'can only select a record! ',
			confirm_msg_del : 'confirm delete this? '
		},
		jobAdd : {
			error_msg_addJob : 'Add Task failed',
			validate_msg_jobName_require : 'Task Name Required.',
			validate_msg_jobName_max : 'task name up to 100 characters.',
			validate_msg_jobClass_require : 'task class is mandatory.',
			validate_msg_jobClass_max : 'task classes up to 100 characters.',
			validate_msg_class:'请先输入任务类名再点击验证按钮'
		},
		triggerAdd : {
			error_msg_add : 'Add Scheduled Task Failed',
			validate_msg_triggerName_require : 'Plan Name Required.',
			validate_msg_triggerName_max : 'task up to 128 characters.'
		}
	},
	globalType : {
		tree:{
			globalMenu:{
				text_menu_add:'Add categories',
				text_menu_import:'Import',
				text_menu_export:'Export',
				text_menu_del:'Delete',
				text_menu_refresh:'Refresh',
				text_menu_sort:'Sort'
			},
			reportTypeMenu:{
				text_menu_add:'Add categories',
				text_menu_edit:'Edit Category',
				text_menu_del:'Delete Category'
			},
			flowTypeMenu:{
				text_menu_add:'Add categories',
				text_menu_edit:'Edit Category',
				text_menu_del:'Delete Category'
			},
			dicMenu:{
				text_menu_add:'Add dictionary',
				text_menu_edit:'Edit dictionary',
				text_menu_del:'Delete',
				text_menu_sort:'Sort'
			}
		},
		edit : {
			validate_msg_NaN : 'Please enter a non-numeric',
			validate_msg_nonValidChar : 'you can not enter symbol class character'
		},
		dialog : {
			validate_msg_sameForm : 'can not be added to the same form! ',
			success_msg : 'added successfully, continue to add it?',
			success_msg_add : 'Add categories to success!',
			success_msg_update : 'modified classification success!'
		},
		flowTypeDialog : {
			validate_msg_select : 'Please select category!'
		},
		import_ : {
			file_validate_msg : 'Please select the *. xml file to import!',
			error : 'system of classification import failed'
		},
		list : {
			validate_msg_category : 'Please select the left of the specific classification!'
		},
		sortList : {
			success_msg : 'Successfully sort!',
			error_msg : 'Sort classification failed'
		},
		upd1 : {
			error_msg_exists : 'node key {0} already exists, can not be saved! '
		},
		confirm:'此操作将删除改分类下所有的子节点，如果是数据字典分类字典数据也将删除，确认删除吗？'
	},
	dictionary : {
		tree : {
			warn_msg_load_noNode : 'no choice nodes',
			text_menu_add : 'add dictionary entries',
			text_menu_sort : 'sort',
			text_menu_edit : 'edit dictionary entries',
			text_menu_del : 'Delete',
			success_msg_del : 'delete dictionary entries successfully!',
			error_msg_del : 'delete dictionary entries failed!',
			confirm_msg_del : 'This will delete all of the following dictionary entry and dictionary entries, confirm delete this? '
		},
		edit : {
			success_msg_add : 'Add dictionary successful!',
			error_msg_update : 'Add dictionary successful!',
			error_msg_save : 'save the dictionary information failed'
		},
		list : {
			warn_msg_add_on_category : 'Please select the categories on the left to add specific data dictionary!'
		},
		sortList : {
			success_msg_sort : 'dictionary sort is complete!',
			error_msg_sort : 'Sort classification fails'
		},
		treeList : {
			warn_msg_sort : 'Please select the categories on the left to add specific data dictionary!'
		}
	},
	sysTypeKey : {
		list : {
			warn_msg_no_sort : 'no sort operation, select OK to sort!',
			error_msg_save : 'Save systematic classification key definition of'
		}
	},
	sysDataSource : {
		list : {
			warn_msg_test_no_record : 'Please select the record!',
			waitting_msg_test : 'connection is being tested, please wait ...',
			success_msg_test : 'connection is successful!',
			error_msg_test : 'Connection failed! <br/> Reason: {0}'
		},
		edit : {
			waitting_msg_test : 'Connecting please wait ...',
			success_msg_test : 'connection is successful!',
			error_msg_test : 'Connection failed! <br/> Reason: {0}',
			text_msg_url_oracle : 'jdbc: oracle: thin: @ host: 1521: database instance',
			text_msg_url_sql2005 : 'jdbc: sqlserver :/ / host: 1433; databaseName = database name;',
			text_msg_url_mysql : 'jdbc: mysql :/ / host: 3306 / database name? useUnicode = true & characterEncoding = utf-8',
			text_msg_url_db2 : 'jdbc: db2 :/ / host: 50000 / database name: currentSchema = model name; retrieveMessagesFromServerOnGetMessage = true;',
			text_msg_url_h2 : 'jdbc: h2: path / database name; schema = schema name',
			text_msg_url_dm : 'jdbc: dm :/ / host: 5236 / database instance'
		}
	},
	resources:{
		addResource:{
			warn_msg_no_parent:'请从左边的资源树上选择父节点！',
			warn_msg_column_parent:'选择的父节点是必须栏目节点！',
			success_msg:'添加为菜单成功！',
			error_msg:'添加为菜单失败！'
		},
		tree:{
			text_menu_add:'增加节点',
			text_menu_edit:'编辑节点',
			text_menu_del:'删除节点',
			text_menu_import:'导入资源',
			text_menu_export:'导出资源',
			text_menu_sort:'节点排序',
			text_menu_move:'移动节点',
			text_menu_editUrl:'编辑URL',
			warn_msg_root:'该节点为系统节点 ,不允许该操作'
		},
		edit:{
			completedNode:'请把资源的名称输入完整',
			success_msg_add:'添加节点成功,继续添加吗?',
			success_msg_edit:'编辑节点成功!',
			error_msg_edit:'编辑节点失败'
		},
		dialog:{
			noPicture:'没有选择图标!',
			createFolder:'新建文件夹',
			delFolder:'删除文件夹',
			error_msg_setPicture:'设置文件图标失败',
			success_msg_setPicture:'设置文件图标成功',
			selectPic:'请选择图标文件'
		},
		import_:{
			selectFile:'请选择 *.xml文件进行导入!',
			fail:'系统资源导入失败'
		},
		moveTree:{
			nullNode:'目标节点为空,请选择!'
		},
		sortList:{
			success_msg:'资源排序完成!',
			fail_msg:'资源排序失败'
		},
		urlEdit:{
			fail_msg:'保存系统资源URL失败'
		}
	},
	subSystem : {
		import_ : {
			warn_msg_select_file : 'Please select the *. xml file to import!',
			error_msg : 'subsystem resource import failed'
		}
	},
	sysAcceptIp : {
		edit : {
			warn_msg_ip_valid : 'IP address filled in incorrectly, can not be saved! '
		}
	},
	indexRebuild : {
		list : {
			warn_msg_no_index : 'Please select the index! ',
			text_msg_table_name : 'Table name:',
			text_msg_index_name : 'index name:',
			text_msg_error_title : 'Please enter a name and description for the index rebuild plan',
			text_msg_error_button_ok : 'OK',
			warn_msg_job_name : 'index rebuilding program name can not be empty',
			text_msg_error_button_cancel : 'Cancel',
			error_msg_save : 'Save the index failed'
		}
	},
	sysLogSwitch : {
		management : {
			text_msg_status_open : 'open',
			text_msg_status_close : 'off'
		}
	},
	sysCalendar:{
		save:{
			failure:'Failed to save the system calendar!',
			fail:'Save this monthly data failed!'
		},
		selectOpt:'Does not have any selected item, please select the date!',
		confirmSave:'Do you want to save the data this month?'
	},
	workTimeSetting:{
		startTimeFormatError:'Start time input format is incorrect',
		endTimeFormatError:'End time input format is incorrect',
		noShiftTime:'No added shift time'
	},
	calendarAssign:{
		setCalendarFirst:'You need to set up a working calendar'
	},
	desktopColumn:{
		confirm:'Initialize the form template will be loaded from the file system template part, you determine the initialization?'
	},
	desktopLayout:{
		saveLayout:{
			success:'Save Layout success!',
			fail:'Save Layout failure!'
		}
	},
	desktopLayoutcol:{
		setNoColumns:'Not yet set out part！',
		selectOneColumn:'Please select a column！',
		misconfigured:'More path misconfigured'
	},
	reportTemplate : {
		upload_validate_msg : 'Please upload the template file',
		file_validate_msg : 'Please select the *.cpt report template file',
		fail_msg_save : 'Failed to save report templates'
	},
	fineReportDS : {
		connect : {
			waitting : 'Connecting,Please wait.',
			alter : 'Modify the data connection',
			submitting : 'Submitting'
		},
		submit : {
			conflict : 'Submit conflict! Please refresh the page and then modify the submitted',
			success : 'Submitted successfully!',
			fail : 'Failure to submit!'
		}
	},
	sysReport : {
		selectReturnValue : 'Please select the return value of dialog',
		selectDialog : 'Please select the Customize dialog box',
		continueSet : 'The report contains parameters,continue Setting?',
		success_msg_add : 'Add report successfully',
		file_validate_msg : 'Please select a file ended with *.zip or *.jrxml to upload!'
	},
	sysUser:{
		isContinue:'是否继续操作',
		pictureType:'请选择*png,*gif,*jpg类型图片!',
		noRecord:'你还没选择任何记录!',
		noNode:'你还没选择任何节点!',
		noPosNode:'no position node!',
		noRolNode:'no role node!',
		removing:'移除',
		added:'增加',
		confirmDelOrg:'确认要删除此组织吗，其下组织也将被删除？',
		syncToLdap:{
			confirmContent:'<font color="red">与AD服务器同步会将AD的用户同步到系统数据库，您确定要进行同步吗？</font>',
			loading:'正在同步AD用户，请等待...',
			syncSuccess:'同步用户成功!',
			syncFail:'同步用户失败!'
		},
		userUnder:'不能将自己添加为下属!',
		param:{
			paramRepeat:'参数名重复。',
			inputDigital:'请输入数字。',
			inputDate:'请输入日期',
			input:'请输入值',
			saveFail:'保存人员参数属性失败',
			condNotEmpty:'条件不能为空'
		}
	},
	sysOrg:{
		delConfirm:'确认要删除此组织吗，其下组织也将被删除？',
		notfilter:'[未]',
		searchFail:'查询组织人员失败',
		select:{
			selByPos:'按岗位查找',
			selByOrg:'按组织查找',
			selByRole:'按角色查找'
		},
		param:{
			noselect:'你还没选择组织!',
			saveFail:'保存组织参数属性失败'
		},
		warn_select_leastOne:'至少要选择一个岗位!',
		warn_select_onlyOne:'最多只能选择一个岗位!',
		select_left_orgNode:'请选择左边组织节点!',
		add_orgInfor_success:'添加组织信息成功!',
		edit_orgInfor_success:'编辑组织信息成功!',
		add_orgInfor_fail:'添加组织信息失败',
		assignRoles_fail:'分配角色授权失败',
		select_orgId:'请选择组织ID!',
		sysOrgType:{
			editFail:'编辑组织结构类型失败',
			saveFail:'保存组织结构类型失败',
			inputName:'请输入名称'
		}
	},
	sysRole:{
		copy:{
			nullName:'角色名必填',
			nullAlias:'别名必填',
			tooLongName:'角色名最多20个字符',
			tooLongAlias:'别名最多20个字符',
			tooLongRemark:'备注最多256个字符',
			ok:'角色资源分配成功，是否继续',
			fail:'角色资源分配出错',
			nameNotSame:'角色名不能相同！',
			aliasNotSame:'别名不能相同！',
			saveFail:'保存角色信息失败'
		}
	},
	imageQtip:{
		error_msg_loadImage:'未能成功加载图片，可能已被删除...',
		iamge_msg_loading:'图片加载中...',
		attachment_preview:'附件预览'
	},
	seal:{
		edit:{
			error_msg_upload:'印章上传到服务器出错',
			error_msg_add:'添加印章失败',
			all:'all'
		},
		NtkoSignManage:{
			uploadByIE:'不能装载印章管理控件，必须使用IE内核浏览器。可能需要在浏览器的Internet选项安全设置中修改ActiveX配置。按提示加载控件',
			error_msg_printSeal:'打开印章出错!',
			noSeal:'没有印章!',
			error_msg_createSeal:'创建印章错误',
			success_msg_save:'保存印章到本地文件成功!',
			error_msg_save:'保存印章到本地文件失败!'
		},
		addSign:{
			selectValidPicture:'请选择有效图片',
			addTrustedSites:'请把本网站添加到可信站点再进行操作'
		}
	},
	sysOfficeTemplate:{
		upload_validate_msg:'请上传模板文件',
		error_msg_save:'保存系统模板失败'
	},
	sysCodegen:{
		error_msg_customForm:'还未选择任何自定义表单',
		error_msg_customTable:'还未选择任何自定义表',
		error_msg_template:'还未选择任何模版',
		folderPath:'请填写打包文件存放的路径!'
	},
	sysCodeTemplate:{
		confirm_msg_init:'初始化表单模板将会导致自定义模板信息丢失，sure initialize?'
	},
	messageSend:{
		executorList:'执行人列表',
		selectReceiver:'请选择收信人或收信组织',
		readMsg:{
			fail:'查看未读信息失败'
		}
	},
	outMail:{
		wail_sync:'正在同步,请耐心等候...',
		wail_send:'正在发送,请您耐心等待...',
		wail_link:'正在测试连接,请您耐心等待...',
		email_success:'成功',
		syncEmail_fail:'同步邮件出错!',
		sure_moveto_dustbin:'确认要将邮件移至垃圾箱？',
		sure_delete_email:'确认要彻底删除邮件吗？',
		emailedit:'请先设置默认邮箱<br/>或者在"邮箱配置管理里设置"!',
		please_input_address:'请选择要填入的地址',
		please_input_mail_address:'请先填写邮箱地址!',
		please_input_mail_password:'请先填写邮箱密码!',
		test_link_fail:'测试连接失败'
	}
};