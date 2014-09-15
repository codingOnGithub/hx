var $lang_bpm = {
	// 代理模塊
	agent : {
		settingEdit : {
			settingWarn : '代理設置有問題',
			agentNotNull : '代理人不能為空!',
			partMustSelFlowDef : '部分代理必須選擇要代理的流程！ ',
			SelFlowDef : '请选择代理流程！',
			setAgentCondition : '請設置代理條件!',
			serverError : '後台出錯!',
			selectFlowFirst : '請先選擇流程!'
		},
		conditionEdit : {
			mustHaveOne : '請設置至少一個以上<B>代理人設置</B>!',
			noAgent : '第{0}個<B>代理人設置</B> 沒有指定代理人!<br/>'
		}
	},
	// 流程定義模塊
	bpmDefinition : {
		// 抄送人員
		copyUserList : {
			selectOneItem : '要編輯的規則必須而且只能選擇一項!'
		},
		// 引用流程
		defReferSelector : {
			selectFlow : '請選擇引用流程!'
		},
		// 流程分發
		editForkJoin : {
			fail : '編輯流程分發配置失敗'
		},
		imp : {
			selectZip : '請選擇*.zip文件進行導入!',
			importing : '正在導入中,請稍候...',
			result : '導入結果如下:',
			fail : '導入結果如下:',
			cleanWait : '正在清理,請耐心等候... ',
			updateSuccess : '更新成功',
			updateFail : '出錯了'
		},
		list : {
			update : {
				updateing : '正在更新...',
				success : '更新成功'
			},
			disable : {
				disable : '禁用',
				enable : '啟用',
				success : '成功'
			},
			cleanData:{
				success:'清除數據成功',
				fail:'清除數據失敗',
				confirm:'確定清除數據?'
			},
			cleanCache : "您確定要清空該流程定義的緩存嗎?",
			ccIpAddr : '應用服務器地址',
			ccStatus : '執行狀態',
			ccErrMsg : '錯誤消息',
			ccSuccess : '成功',
			ccError : '失敗',
			setCategory:'設置分類'

		},
		nodeSet : {
			flowEvent : '事件設置',
			globalFlowTimeDue : '全局時效提醒設置',
			flowCodition : '設置分支條件',
			mobileSet : '手機訪問設置',
			flowRule : '跳轉規則設置',
			flowVote : '會簽投票規則設置',
			flowTimeDue : '時效提醒設置',
			flowApproval : '常用語設置',
			userSet : '人員設置',
			flowForkJoin :{
				name: '流程分發彙總',
				fail:'編輯流程分發配置失敗'
			},
			webServiceSet :{
				name:'WebService設置',
				inputAdd:'请输入地址查询',
				notWebURL:'还没有填写webService地址!',
				webUrlError:'输入WebService地址是否错误或者链接异常，请检查！',
				webMethodWarn:'请先查询出webservice的方法',
				webMethodcho:'请选择webservice的方法',
				closeWindow:'是否关闭当前窗口?'
			},
			flowMessage : '消息參數',
			scriptSet : '設置腳本',
			viewSubFlow : '查看調用子過程示意圖',
			flowSet : '設置子過程',
			flowDue	: '任務催辦設置',
			informType : {
				name:'通知方式',
				fail:'編輯手機訪問設置失敗'
			}

		},
		otherParam : {
			insertTextTip : '請把編輯器設置為編輯模式',
			codeBeforeSetting : '流程編碼設置必須設置!',
			saveDurationVal : '流程實例存儲周期只能為正整數,請檢查!',
			failure : '流程定義其他參數設置失敗'
		},
		setCondition:{
			success_msg_rule:'编辑规则成功!',
			fail_msg_rule:'编辑规则失败,请检查条件表达式是否正确!'
		}
	},
	bpmDefRightList : {
		allUser : '所有用戶'
	},
	nodeSet : {
		selectForm : '請先選擇表單!',
		actForm:'請設置流程實例業務表單!'
	},
	nodeUser : {
		preview : {
			inputParams : '請輸入預覽參數!',
			notUser : '沒有選擇到人員!',
			joinProces : '參與流程',
			receiveNotify : '接收通知'
		},
		condition:{
			notUserSetting:'没有定义用户配置!'
		}
	},
	nodeButton : {
		initAllTip : '確認初始化按鈕嗎？ ',
		initTip : '確認初始化按鈕嗎？ ',
		clearAllTip : '確認清除該流程定義的所有按鈕嗎？ ',
		delTip : '確認清除該節點的所有按鈕嗎？ ',
		notDefButton : '沒有定義按鈕!'
	},
	bpmGangedSet : {
		selectCascadeTip : '只有下拉框才能進行級聯變換',
		chooseNotSetTip : '有選擇值未設置!',
		changeNotSetTip : '有變更字段未設置!',
		changeTypeNotSetTip : '有變更類型未設置!',
		changeType : {
			hide : '隱藏',
			noHide : '非隱藏',
			read : '只讀',
			noRead : '非只讀',
			required : '必填',
			noRequired : '非必填',
			blank : '置空',
			cascade : '級聯'
		},
		cascadeSet : '級聯設置',
		allNode : '所有節點',
		startNode : '起始節點',
		notBindForm : '該節點未綁定表單，而且未設置全局表單，不能進行聯動設置！ ',
		cascadeSetTip:'减少的选项至少需要设置一条记录'
	},
	task : {
		notSelectTask : "沒有選擇任務!",
		endProcess : '確認結束該流程嗎？ ',
		toStart : {
			warn_msg_opinion_length : "意見不能超過{0}字符",
			warn_msg_form_validate : "表单验证不成功,請檢查表單是否填寫正確！",
			warn_msg_form_subtable_require : "子表必須有一條記錄，請檢查！",
			warn_msg_syncpath_require : "在同步條件後的執行路徑中，您至少需要選擇一條路徑!",
			warn_msg_reject_opinion : '請填寫駁回意見! ',
			warn_msg_reject_start_opinion : '請填寫駁回意見! ',
			warn_msg_complete_or_terminate : '任務已經執行完成或被終止! ',
			warn_msg_curTask_notToDo:'当前任务不能转办!',
			success_msg_operate : '執行任務成功!',
			success_msg_reject : "駁回成功!",
			success_msg_reject_start : "駁回到發起人成功!",
			success_msg_form_save : "保存表單數據成功!",
			error_msg_no_form : "還沒有設置表單!",
			error_msg_complete : "執行任務失敗!",
			error_msg_terminate : "終止流程失敗!",
			error_msg_reject : "駁回任務失敗!",
			error_msg_reject_start : "駁回到發起人失敗!",
			error_msg_form_save : "保存表單數據失!",
			error_msg : "提交失敗 ",
			confirm_msg_operate : "您確定執行此操作嗎？"
		},
		incToolBarNode : {
			back : "返回"
		},
		toStartEnd : {
			warn_msg_opinion_require : '終止意見不能為空!',
			confirm_msg : '確認終止嗎？ '
		},
		startFlowForm : {
			confirm_msg_start : "確認啟動流程嗎?",
			warn_msg_form_require : '流程表單為空，請先設置流程表單!',
			warn_msg_form_validate : "表单验证不成功,請檢查表單數據是否填寫正確！",
			warn_msg_form_subtable_require : "请填写子表表单数据！",
			success_msg_start : "啟動流程成功!",
			success_msg_form_save : "保存表單數據成功!",
			error_msg_start : "啟動流程失敗!",
			error_msg_form_save : "保存表單數據失敗!"
		},
		list:{
			assigneelist:'执行人列表'
		},
		detail:{
			menuItem:{
				changePath : '更改执行路径',
				completeTask : '完成任务',
				approvalDetail : '审批明细',
				businessForm : '业务表单'
			},
			taskOperation:'更多任务操作',
			endTask:'确定结束该任务吗?'
		},
		selExePath:'请选择更换的目标节点!',
		selAssignee:'请选择执行人!',
		chanAssigneeReason:'请填写更改任务执行人原因!',
		taskSign:{
			success:'你成功进行了会签投票!'
		},
		addSign:{
			success:'你成功进行了补签!'
		},
		toTransTo:{
			error_message_num:'会签类型的人数必须大于1!',
			success:'加签成功！',
			failure:'加签失败！',
			sureSend:'确定发送流转意见？'
		}

	},
	timeTaskReminder : {
		inputName : '請輸入<span class="red">等級名稱</span>',
		hashRecord : '至少需要保留一條記錄!',
		methods : '<span class="red">提醒設置</span>中至少需要一條記錄',
		minute : '分鐘',
		approver : '審批人',
		approverUp : '審批人上級',
		approverUpUp : '審批人上上級',
		departmentHead : '審批人部門負責人',
		inner : '站內消息',
		mail : '郵件消息',
		sms : '手機短信'

	},
	messageCopyEdit : {
		inputTitle : '請填寫郵件或站內信的標題!',
		notDef : '未獲取流程定義的相關信息!'
	},
	// checkVersion.js
	checkVersion : {
		runMsg : '已發布了新版本!<br/>此版本已過期，無法啟動!',
		copyMsg : '已發布了新版本!<br/>此版本已過期，無法複製!'
	},
	batchApproval : {
		confirmApproval : '批量批准,只能批准“同意”,<br/>是否確認批量審批嗎？ ',
		approvalResult : '詳細審批結果如下：'
	},
	cancelAssign : {
		confirmCancel : '確認取消轉辦(代理)嗎？ ',
		cancelSuccess : '取消轉辦(代理)成功!',
		cancelFailure : '取消轉辦(代理)失敗!',
		confirmBatCancel : '確認批量取消轉辦(代理)嗎？ ',
		batCancelSuccess : '批量取消轉辦(代理)成功!',
		batCancelFailure : '批量取消轉辦(代理)失敗!'
	},
	commuReceiver : {
		title : '溝通接收人',
		reStart : '重啟審批人'
	},
	forward : {
		confirm : '確認轉發操作嗎?'
	},
	recover : {
		confirm : '確認撤銷此流程嗎?',
		opinion : '請填寫撤銷原因！ ',
		failure : '追回失敗',
		redoConfirm:'是否确认追回?',
		redoOpinion:'必须填写追回原因!'
	},
	communicate : {
		success : '發送給溝通人成功!',
		failure : '發送給溝通人失敗!',
		confirm : '確認發送溝通嗎？ '
	},
	assign : {
		confirm : '確認轉辦操作嗎?'
	},
	feedback : {
		success : '發送反饋意見成功!',
		failure : '發送反饋意見失敗!',
		confirm : '確認發送反饋嗎？ '
	},
	restartTask : {
		confirm : "確定重啟該任務",
		agreeConfirm : "是否同意該任務?",
		agreeSuccess : "同意該任務執行成功!",
		notAgreeConfirm : "是否反對該任務?",
		notAgreeSuccess : "反對該任務執行成功!"
	},
	bpmCommonWsSet:{
		waiting:'正在調用,請稍候...',
		error:'調用出錯了，出錯的詳細信息如下:\n',
		addCustom:'添加自定義參數',
		nullArgument:'參數名稱不能為空',
		nullAlias:'WS調用別名不能為空',
		cancelBind:'是否取消當前參數[{0}]的綁定配置?',
		loading:'正在加載中,請稍候...',
		deleteMethod:'刪除當前方法[{0}]?',
		fillinAdress:'還沒有填寫webService地址!',
		searching:'正在查詢中,請稍候...',
		errorAdress:'輸入WebService地址是否錯誤或者鏈接異常，請檢查!',
		searchMethod:'請先查詢出webservice的方法',
		selectMethod:'請選擇webservice的方法',
		closeCurWindow:'是否關閉當前窗口?'
	},
	bpmNodeScript:{
		edit:{
			success:'流程事件腳本編輯成功!',
			fail:'流程事件腳本編輯失敗!'
		}
	},
	taskReminder:{
		edit:{
			timeMeg:'辦結時間不能比催辦時間短',
			nameMeg:'請輸入任務任務催辦名稱'
		},
		content:{
			name:'名稱',
			type:'類型',
			desc:'注釋'
		},
		reminderFail:'任務催辦提醒失敗',
		selFlowVar:'請選擇流程變量！',
		selVarType:'請選擇變量操作類型變量！',
		inputFlowVarVal:'請輸入流程變量值！'
	},
	myDraft:{
		confirm:'草稿表单版本发生变化时候加载旧版本数据?'
	},
	bpmFormLanguage:{
		sureInit:'确定初始化国际化吗？'
	},
	bpmNodeWebService:{
		isDelCurBinding:'是否取消当前参数[{0}]的绑定配置?',
		isDelCurMethod:'删除当前方法[{0}]?'
	},
	processImage:{
		init:'<span style="color:red;line-height:24px;">未执行</span>',
		implementation :'任务执行情况',
		subFlow:{
			none:'无',
			taskName:'任务名称',
			exeFullname:'执行人',
			startTime:'开始时间',
			endTime:'结束时间',
			durTime:'时长',
			status:'状态',
			opinion:'意见'
		}
	},
	ruleSetting:{
		groupRule :'组合规则',
		splitRule :'拆分规则'
	},
	nodeUserUplow:{
		trRow:{
			del:'删除'
		},
		dimeRepeat:'维度重复。',
		inputDigital:'请输入数字。',
		same:'同',
		up:'上',
		down:'下',
		header:'负责人',
		level:'级'
	},
	typeSet:{
		warn_select_orgtype:'请选择部门类型，如没可选请先通知 管理员添加部门类型!',
		dimension:'维度',
		type:'类型',
		orgtype:'部门类型',
		searchStrategy:'查找策略'
	},
	startOrPrev:{
		selectUser:'请选择人员类型!',
		selectOrg:'请选择组织类型!',
		has:'具有'
	},
	selectInst:'请选择流程实例!',
	formVar:{
		warn_select_varType:'请选择表单变量类型!',
		warn_no_formVar:'没有表单变量!',
		warn_no_select_formVar:'没有选择表单变量!' ,
		varType:'类型【{0}】',
		varName:'变量名【{0}】'
	},
	processRun:{
		redo_success:'任务成功的被追回!',
		redo_fail:'任务已经完成或正在处理，不能进行追回处理'
	},
	language:{
		no_any_operate:'未做任何修改操作',
		save_fail:'保存国际化资源失败'
	}
};