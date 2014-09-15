var $lang_system = {
	identity : {
		rule_validate_msg : '模板名不能同名!'
	},
	sysTemplate : {
		list : {
			export_validate_msg : '請選擇模板進行導出!'
		},
		import_ : {
			file_validate_msg : '請選擇*.xml文件進行導入!',
			waitting_msg : '正在導入中,請稍候...',
			result : '導入結果如下:'
		}
	},
	script : {
		list : {
			update_waitting_msg : '正在更新,請耐心等候... '
		},
		dialog : {
			validate_msg_select : '還沒有選擇腳本!'
		},
		import_ : {
			file_validate_msg : '請選擇*.xml文件進行導入!',
			waitting_msg : '正在導入中,請稍候...',
			result : '導入結果如下:',
			error : '自定義表導入失敗'

		}

	},
	conditionScript : {
		dialog : {
			validate_msg_select : '請選擇一個條件腳本！ '
		},
		edit:{
			selectDialog:'請選擇參數【{0}】所要綁定的對話框！',
			selectReturnValue:'請選擇參數【{0}】對應的對話框返回值！'
		}
	},
	sysTestResult : {
		list : {
			validate_msg_args : '有必填參數未填寫',
			validate_msg_user : '必須選擇用戶',
			tip_msg_start : '可能需要較長時間，是否繼續?',
			waitting : '請稍後,正在測試...',
			validate_msg_require_actDefKey : '必須填寫流程定義KEY'
		}
	},
	scheduler : {
		jobList : {
			validate_msg_select_require : '請選擇記錄！ ',
			validate_msg_select_single : '只能選擇一條記錄！ ',
			confirm_msg_del : '確認刪除嗎？ '
		},
		jobAdd : {
			error_msg_addJob : '添加任務失敗',
			validate_msg_jobName_require : '任務名稱必填.',
			validate_msg_jobName_max : '任務名稱最多100個字符.',
			validate_msg_jobClass_require : '任務類必填.',
			validate_msg_jobClass_max : '任務類最多100個字符.',
			validate_msg_class:'请先输入任务类名再点击验证按钮'
		},
		triggerAdd : {
			error_msg_add : '添加計劃任務失敗',
			validate_msg_triggerName_require : '計劃名稱必填.',
			validate_msg_triggerName_max : '任務最多128 個字符.'
		}
	},
	globalType : {
		tree : {
			globalMenu:{
				text_menu_add:'增加分類',
				text_menu_import:'導入',
				text_menu_export:'導出',
				text_menu_del:'删除',
				text_menu_refresh:'刷新',
				text_menu_sort:'排序'
			},
			reportTypeMenu : {
				text_menu_add : '增加分類',
				text_menu_edit : '編輯分類',
				text_menu_del : '刪除分類'
			},
			flowTypeMenu:{
				text_menu_add:'增加分類',
				text_menu_edit:'編輯分類',
				text_menu_del:'刪除分類'
			},
			dicMenu:{
				text_menu_add:'增加字典分類',
				text_menu_edit:'編輯分類',
				text_menu_del:'删除',
				text_menu_sort:'排序'
			}
		},
		edit : {
			validate_msg_NaN : '請輸入非數字',
			validate_msg_nonValidChar : '不能輸入符號類字符'
		},
		dialog : {
			validate_msg_sameForm : '不能添加到同一表單！ ',
			success_msg : '添加成功，繼續添加嗎?',
			success_msg_add : '添加分類成功!',
			success_msg_update : '修改分類成功!'
		},
		flowTypeDialog : {
			validate_msg_select : '請選擇分類!'
		},
		import_ : {
			file_validate_msg : '請選擇*.xml文件進行導入!',
			error : '系統分類導入失敗'
		},
		list : {
			validate_msg_category : '請選擇左邊具體的分類!'
		},
		sortList : {
			success_msg : '排序分類完成!',
			error_msg : '排序分類失敗'
		},
		upd1 : {
			error_msg_exists : '節點key{0}已存在，不能保存！ '
		},
		confirm:'此操作将删除改分类下所有的子节点，如果是数据字典分类字典数据也将删除，确认删除吗？'
	},
	dictionary : {
		tree : {
			warn_msg_load_noNode : '沒有選擇節點',
			text_menu_add : '增加字典項',
			text_menu_sort : '排序',
			text_menu_edit : '編輯字典項',
			text_menu_del : '刪除',
			success_msg_del : '刪除字典項成功!',
			error_msg_del : '刪除字典項失敗!',
			confirm_msg_del : '將刪除該字典項及下面的所有字典項目，確認刪除嗎？ '
		},
		edit : {
			success_msg_add : '添加字典成功!',
			error_msg_update : '添加字典成功!',
			error_msg_save : '保存字典信息失敗'
		},
		list : {
			warn_msg_add_on_category : '請選擇左邊具體的分類才能添加數據字典!'
		},
		sortList : {
			success_msg_sort : '字典排序完成!',
			error_msg_sort : '排序分類失敗'
		},
		treeList : {
			warn_msg_sort : '請選擇左邊具體的分類才能添加數據字典!'
		}
	},
	sysTypeKey : {
		list : {
			warn_msg_no_sort : '還沒有進行排序操作，請選擇行進行排序!',
			error_msg_save : '保存系統分類鍵定義失敗'
		}
	},
	sysDataSource : {
		list : {
			warn_msg_test_no_record : '請選擇記錄!',
			waitting_msg_test : '正在測試連接，請等待...',
			success_msg_test : '連接成功!',
			error_msg_test : '連接失敗!<br/>原因：{0}'
		},
		edit : {
			waitting_msg_test : '正在連接請稍後...',
			success_msg_test : '連接成功!',
			error_msg_test : '連接失敗!<br/>原因：{0}',
			text_msg_url_oracle : 'jdbc:oracle:thin:@主機:1521:數據庫實例',
			text_msg_url_sql2005 : 'jdbc:sqlserver://主機:1433;databaseName=數據庫名;',
			text_msg_url_mysql : 'jdbc:mysql://主機:3306/數據庫名?useUnicode=true&characterEncoding=utf-8',
			text_msg_url_db2 : 'jdbc:db2://主機:50000/數據庫名:currentSchema=模式名;retrieveMessagesFromServerOnGetMessage=true;',
			text_msg_url_h2 : 'jdbc:h2:路徑/數據庫名;schema=模式名',
			text_msg_url_dm : 'jdbc:dm://主機:5236/數據庫實例'
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
			warn_msg_select_file : '請選擇*.xml文件進行導入!',
			error_msg : '子系統資源導入失敗'
		}
	},
	sysAcceptIp : {
		edit : {
			warn_msg_ip_valid : 'IP地址填寫有誤，不能保存！ '
		}
	},
	indexRebuild : {
		list : {
			warn_msg_no_index : '請選擇索引！ ',
			text_msg_table_name : '表名：',
			text_msg_index_name : '索引名：',
			text_msg_error_title : '請輸入索引重建計劃名稱和描述',
			text_msg_error_button_ok : '確定',
			warn_msg_job_name : '索引重建計劃名不能為空',
			text_msg_error_button_cancel : '取消',
			error_msg_save : '保存索引失敗'
		}
	},
	sysLogSwitch : {
		management : {
			text_msg_status_open : '開啟',
			text_msg_status_close : '關閉'
		}
	},
	sysCalendar:{
		save:{
			failure:'保存系統日曆失敗!',
			fail:'保存此月數據失敗!'
		},
		selectOpt:'還沒有任何選中項,請先選擇日期!',
		confirmSave:'要保存此月數據嗎？'
	},
	workTimeSetting:{
		startTimeFormatError:'開始時間輸入格式有誤',
		endTimeFormatError:'結束時間輸入格式有誤',
		noShiftTime:'沒有添加班次時間'
	},
	calendarAssign:{
		setCalendarFirst:'請先進行工作日曆設置'
	},
	desktopColumn:{
		confirm:'初始化表單模板將會從文件系統中加載欄目模版，確定初始化嗎？'
	},
	desktopLayout:{
		saveLayout:{
			success:'保存佈局成功!',
			fail:'保存佈局失败!'
		}
	},
	desktopLayoutcol:{
		setNoColumns:'還未設置列欄目！',
		selectOneColumn:'請選擇一列！',
		misconfigured:'欄目更多路徑配置有誤'
	},
	reportTemplate : {
		upload_validate_msg : '請上傳模板文件',
		file_validate_msg : '請選擇*.cpt報表模板文件',
		fail_msg_save : '保存報表模板失敗'
	},
	fineReportDS : {
		connect : {
			waitting : '正在連接請稍後...',
			alter : '數據連接修改',
			submitting : '正在提交'
		},
		submit : {
			conflict : '提交衝突!請刷新頁面後再修改提交',
			success : '提交成功!',
			fail : '提交失败!'
		}
	},
	sysReport : {
		selectReturnValue : '請選擇對話框返回值',
		selectDialog : '請選擇自定義對話框',
		continueSet : '報表中包含參數，是否繼續設置？',
		success_msg_add : '添加報表成功',
		file_validate_msg : '請選擇 *.zip或*.jrxml文件進行上傳!'
	},
	sysUser:{
		isContinue:'是否继续操作',
		pictureType:'请选择*png,*gif,*jpg类型图片!',
		noRecord:'你还没选择任何记录!',
		noNode:'你还没选择任何节点!',
		noPosNode:'你还没选择任何岗位节点!',
		noRolNode:'你还没选择任何角色节点!',
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
			all:'所有'
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
		confirm_msg_init:'初始化表单模板将会导致自定义模板信息丢失，确定初始化吗?'
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