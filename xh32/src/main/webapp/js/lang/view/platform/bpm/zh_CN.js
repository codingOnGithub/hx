var $lang_bpm = {
	// 代理模块
	agent : {
		settingEdit : {
			settingWarn : '代理设置有问题',
			agentNotNull : '代理人不能为空!',
			partMustSelFlowDef : '部分代理必须选择要代理的流程！',
			SelFlowDef : '请选择代理流程！',
			setAgentCondition : '请设置代理条件!',
			serverError : '后台出错!',
			selectFlowFirst : '请先选择流程!'
		},
		conditionEdit : {
			mustHaveOne : '请设置至少一个以上 <B>代理人设置</B>!',
			noAgent : '第{0}个 <B>代理人设置</B> 没有指定代理人!<br/>'
		}
	},
	// 流程定义模块
	bpmDefinition : {
		// 抄送人员
		copyUserList : {
			selectOneItem : '要编辑的规则必须而且只能选择一项!'
		},
		// 引用流程
		defReferSelector : {
			selectFlow : '请选择引用流程!'
		},
		// 流程分发
		editForkJoin : {
			fail : '编辑流程分发配置失败'
		},
		imp : {
			selectZip : '请选择 *.zip文件进行导入!',
			importing : '正在导入中,请稍候...',
			result : '导入结果如下:',
			fail : '导入结果如下:',
			cleanWait : '正在清理,请耐心等候... ',
			updateSuccess : '更新成功',
			updateFail : '出错了'
		},
		list : {
			update : {
				updateing : '正在更新...',
				success : '更新成功'
			},
			disable : {
				disable : '禁用',
				enable : '启用',
				success : '成功'
			},
			cleanData:{
				success:'清除数据成功',
				fail:'清除数据失败',
				confirm:'确定清除数据?'
				
			},
			cleanCache:"您确定要清空该流程定义的缓存吗?",
			ccIpAddr:'应用服务器地址',
			ccStatus:'执行状态',
			ccErrMsg:'错误消息',
			ccSuccess:'成功',
			ccError:'失败',
			setCategory:'设置分类'
				
		},
		nodeSet : {
			flowEvent : '事件设置',
			globalFlowTimeDue : '全局时效提醒设置',
			flowCodition : '设置分支条件',
			mobileSet : '手机访问设置',
			flowRule : '跳转规则设置',
			flowVote : '会签投票规则设置',
			flowTimeDue : '时效提醒设置',
			flowApproval : '常用语设置',
			userSet : '人员设置',
			flowForkJoin :{
				name: '流程分发汇总',
				fail:'编辑流程分发配置失败'
			},
			webServiceSet :{
				name:'WebService设置',
				inputAdd:'请输入地址查询',
				notWebURL:'还没有填写webService地址!',
				webUrlError:'输入WebService地址是否错误或者链接异常，请检查！',
				webMethodWarn:'请先查询出webservice的方法',
				webMethodcho:'请选择webservice的方法',
				closeWindow:'是否关闭当前窗口?'
			},
			flowMessage : '消息参数',
			scriptSet : '设置脚本',
			viewSubFlow : '查看调用子过程示意图',
			flowSet : '设置子过程',
			flowDue	: '任务催办设置',
			informType : {
				name:'通知方式',
				fail:'编辑手机访问设置失败'
			}
		},
		otherParam : {
			insertTextTip : '请把编辑器设置为编辑模式',
			codeBeforeSetting : '流程编码设置必须设置!',
			saveDurationVal : '流程实例存储周期只能为正整数,请检查!',
			failure : '流程定义其他参数设置失败'
		},
		setCondition:{
			success_msg_rule:'编辑规则成功!',
			fail_msg_rule:'编辑规则失败,请检查条件表达式是否正确!'
		}
	},
	bpmDefRightList : {
		allUser : '所有用户'
	},
	nodeSet : {
		selectForm : '请先选择表单!',
		actForm:'请设置流程实例业务表单!'
	},
	nodeUser:{
		preview:{
			inputParams:'请输入预览参数!',
			notUser:'没有选择到人员!',
			joinProces:'参与流程',
			receiveNotify:'接收通知'
		},
		condition:{
			notUserSetting:'没有定义用户配置!'
		}
	},
	nodeButton : {
		initAllTip : '确认初始化按钮吗？',
		initTip : '确认初始化按钮吗？',
		clearAllTip : '确认清除该流程定义的所有按钮吗？',
		delTip : '确认清除该节点的所有按钮吗？',
		notDefButton : '没有定义按钮!'
	},
	bpmGangedSet : {
		selectCascadeTip : '只有下拉框才能进行级联变换',
		chooseNotSetTip : '有选择值未设置!',
		changeNotSetTip : '有变更字段未设置!',
		changeTypeNotSetTip : '有变更类型未设置!',
		changeType : {
			hide : '隐藏',
			noHide : '非隐藏',
			read : '只读',
			noRead : '非只读',
			required : '必填',
			noRequired : '非必填',
			blank : '置空',
			cascade : '级联'
		},
		cascadeSet : '级联设置',
		allNode : '所有节点',
		startNode : '起始节点',
		notBindForm : '该节点未绑定表单，而且未设置全局表单，不能进行联动设置！',
		cascadeSetTip:'减少的选项至少需要设置一条记录'
	},
	task:{
		notSelectTask:"没有选择任务!",
		endProcess:'确认结束该流程吗？',
		toStart:{
			warn_msg_opinion_length:"意见不能超过{0}字符",
			warn_msg_form_validate:"表单验证不成功,请检查表单是否填写正确！",
			warn_msg_form_subtable_require:"请填写子表表单数据！",
			warn_msg_syncpath_require:"在同步条件后的执行路径中，您至少需要选择一条路径!",
			warn_msg_reject_opinion:'请填写驳回意见! ',
			warn_msg_reject_start_opinion:'请填写驳回意见! ',
			warn_msg_complete_or_terminate:'任务已经执行完成或被终止! ',
			warn_msg_curTask_notToDo:'当前任务不能转办!',
			success_msg_operate:'执行任务成功!',
			success_msg_reject:"驳回成功!",
			success_msg_reject_start:"驳回到发起人成功!",
			success_msg_form_save:"保存表单数据成功!",
			error_msg_no_form:"还没有设置表单!",
			error_msg_complete:"执行任务失败!",
			error_msg_terminate:"终止流程失败!",
			error_msg_reject:"驳回任务失败!",
			error_msg_reject_start:"驳回到发起人失败!",
			error_msg_form_save:"保存表单数据失!",
			error_msg:"提交失败 ",
			confirm_msg_operate:"您确定执行此操作吗？"
		},
		incToolBarNode:{
			back:"返回"
		},
		toStartEnd:{
			warn_msg_opinion_require:'终止意见不能为空!',
			confirm_msg:'确认终止吗？'
		},
		startFlowForm:{
			confirm_msg_start:"确认启动流程吗?",
			warn_msg_form_require:'流程表单为空，请先设置流程表单!',
			warn_msg_form_validate:"表单验证不成功,请检查表单数据是否填写正确！",
			warn_msg_form_subtable_require:"子表必须有一条记录，请检查！",
			success_msg_start:"启动流程成功!",
			success_msg_form_save:"保存表单数据成功!",
			error_msg_start:"启动流程失败!",
			error_msg_form_save:"保存表单数据失败!"
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
		inputName : '请输入<span class="red">等级名称</span>',
		hashRecord : '至少需要保留一条记录!',
		methods : '<span class="red">提醒设置</span>中至少需要一条记录',
		minute : '分钟',
		approver : '审批人',
		approverUp : '审批人上级',
		approverUpUp : '审批人上上级',
		departmentHead : '审批人部门负责人',
		inner : '站内消息',
		mail : '邮件消息',
		sms : '手机短信'

	},
	messageCopyEdit : {
		inputTitle : '请填写邮件或站内信的标题!',
		notDef : '未获取流程定义的相关信息!'
	},
	// checkVersion.js
	checkVersion : {
		runMsg : '已发布了新版本!<br/>此版本已过期，无法启动!',
		copyMsg : '已发布了新版本!<br/>此版本已过期，无法复制!'
	},
	batchApproval : {
		confirmApproval : '批量批准,只能批准“同意”,<br/>是否确认批量审批吗？',
		approvalResult : '详细审批结果如下：'
	},
	cancelAssign : {
		confirmCancel : '确认取消转办(代理、流转)吗？',
		cancelSuccess : '取消转办(代理、流转)成功!',
		cancelFailure : '取消转办(代理、流转)失败!',
		confirmBatCancel : '确认批量取消转办(代理、流转)吗？',
		batCancelSuccess : '批量取消转办(代理、流转)成功!',
		batCancelFailure : '批量取消转办(代理、流转)失败!'
	},
	commuReceiver : {
		title : '沟通接收人',
		reStart : '重启审批人'
	},
	transToReceiver : {
		title : '流转接收人'
	},
	forward : {
		confirm : '确认转发操作吗?'
	},
	recover : {
		confirm : '确认撤销此流程吗?',
		opinion : '请填写撤销原因！',
		failure : '追回失败',
		redoConfirm:'是否确认追回?',
		redoOpinion:'必须填写追回原因!'
	},
	communicate : {
		success : '发送给沟通人成功!',
		failure : '发送给沟通人失败!',
		confirm : '确认发送沟通吗？'
	},
	assign : {
		confirm : '确认转办操作吗?'
	},
	feedback:{
		success:'发送反馈意见成功!',
		failure:'发送反馈意见失败!',
		confirm: '确认发送反馈吗？'
	},
	restartTask:{
		confirm:"确定重启该任务",
		agreeConfirm:"是否同意该任务?",
		agreeSuccess:"同意该任务执行成功!",
		notAgreeConfirm:"是否反对该任务?",
		notAgreeSuccess:"反对该任务执行成功!"
	},
	bpmCommonWsSet:{
		waiting:'正在调用,请稍候...',
		error:'调用出错了，出错的详细信息如下:\n',
		addCustom:'添加自定义参数',
		nullArgument:'参数名称不能为空',
		nullAlias:'WS调用别名不能为空',
		cancelBind:'是否取消当前参数[{0}]的绑定配置?',
		loading:'正在加载中,请稍候...',
		deleteMethod:'删除当前方法[{0}]?',
		fillinAdress:'还没有填写webService地址!',
		searching:'正在查询中,请稍候...',
		errorAdress:'输入WebService地址是否错误或者链接异常，请检查！',
		searchMethod:'请先查询出webservice的方法',
		selectMethod:'请选择webservice的方法',
		closeCurWindow:'是否关闭当前窗口?'
	},
	bpmNodeScript:{
		edit:{
			success:'流程事件脚本编辑成功!',
			fail:'流程事件脚本编辑失败!'
		}
	},
	taskReminder:{
		edit:{
			timeMeg:'办结时间不能比催办时间短',
			nameMeg:'请输入任务任务催办名称'
		},
		content:{
			name:'名称',
			type:'类型',
			desc:'注释'
		},
		reminderFail:'任务催办提醒失败',
		selFlowVar:'请选择流程变量！',
		selVarType:'请选择变量操作类型变量！',
		inputFlowVarVal:'请输入流程变量值！'
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
		selectType:'请选择类型!',
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