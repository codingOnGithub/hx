var $lang_bpm = {
	// Proxy module
	agent : {
		settingEdit : {
			settingWarn : 'proxy settings have a problem',
			agentNotNull : 'agents can not be empty!',
			partMustSelFlowDef : 'some agent must select the agent process! ',
			SelFlowDef : 'select the agent process!',
			setAgentCondition : 'Please set the proxy condition!',
			serverError : 'backstage wrong!',
			selectFlowFirst : 'Please select process!'
		},
		conditionEdit : {
			mustHaveOne : 'Please set at least one or more agents <B> set </B>!',
			noAgent : '{0} <B> first nominee set </B> No designated agent! <br/>'
		}
	},
	// Process definition module
	bpmDefinition : {
		// CC staff
		copyUserList : {
			selectOneItem : 'To edit the rules must and can only choose one!'
		},
		// Reference Flow
		defReferSelector : {
			selectFlow : 'Please select the reference flow!'
		},
		// Process Distribution
		editForkJoin : {
			fail : 'editing process distributed configuration failed'
		},
		imp : {
			selectZip : 'Please select the *. zip file to import!',
			importing : 'being imported, please wait ...',
			result : 'import results are as follows:',
			fail : 'import results are as follows:',
			cleanWait : 'being cleaned, please wait ...',
			updateSuccess : 'updated successfully',
			updateFail : 'wrong'
		},
		list : {
			update : {
				updateing : 'Updating ...',
				success : 'update successful'
			},
			disable : {
				disable : 'Disable',
				enable : 'Enable',
				success : 'success'
			},
			cleanData:{
				success:'清除数据成功',
				fail:'清除数据失败',
				confirm:'确定清除数据?'
				
			},
			cleanCache : "Are you sure you want to empty the cache of the process definition you?",
			ccIpAddr : 'application server address',
			ccStatus : 'execution state',
			ccErrMsg : 'error message',
			ccSuccess : 'success',
			ccError : 'Failed',
			setCategory:'设置分类'

		},
		nodeSet : {
			flowEvent : 'event settings',
			globalFlowTimeDue : 'Global Aging alert settings',
			flowCodition : 'Set branch condition',
			mobileSet : 'mobile access settings',
			flowRule : 'Jump rule set',
			flowVote : 'countersigned by the voting rules set',
			flowTimeDue : 'Aging alert settings',
			flowApproval : 'common language settings',
			userSet : 'Staffing',
			flowForkJoin :{
				name: 'Flow Distribution Summary',
				fail:'编辑流程分发配置失败'
			},
			webServiceSet :{
				name:'WebService settings',
				inputAdd:'请输入地址查询',
				notWebURL:'还没有填写webService地址!',
				webUrlError:'输入WebService地址是否错误或者链接异常，请检查！',
				webMethodWarn:'请先查询出webservice的方法',
				webMethodcho:'请选择webservice的方法',
				closeWindow:'是否关闭当前窗口?'
			},
			flowMessage : 'message parameter',
			scriptSet : 'setup script',
			viewSubFlow : 'Show Call subroutine schematic',
			flowSet : 'Set subFlow',
			flowDue	: '任務催辦設置',
			informType : {
				name:'通知方式',
				fail:'编辑手机访问设置失败'
			}

		},
		otherParam : {
			insertTextTip : 'Please editor to edit mode',
			codeBeforeSetting : 'processes encoding settings must be set!',
			saveDurationVal : 'memory cycle process instance can only be a positive integer, check!',
			failure : 'the process to define other parameters failed'
		},
		setCondition:{
			success_msg_rule:'编辑规则成功!',
			fail_msg_rule:'编辑规则失败,请检查条件表达式是否正确!'
		}
	},
	bpmDefRightList : {
		allUser : 'All Users'
	},
	nodeSet : {
		selectForm : 'Please select a form!',
		actForm:'请设置流程实例业务表单!'
	},
	nodeUser : {
		preview : {
			inputParams : 'Please enter preview parameters!',
			notUser : 'no choice to the staff!',
			joinProces : 'participate in the process',
			receiveNotify : 'Receive notification'
		},
		condition:{
			notUserSetting:'没有定义用户配置!'
		}
	},
	nodeButton : {
		initAllTip : 'Initialize button to confirm it? ',
		initTip : 'Initialize button to confirm it? ',
		clearAllTip : 'OK Clear definition of the process all the buttons do? ',
		delTip : 'OK Clear definition of the node all the buttons do? ',
		notDefButton : 'not defined button!'
	},
	bpmGangedSet : {
		selectCascadeTip : 'drop-down box can only be cascaded transformation',
		chooseNotSetTip : 'selective value is not set!',
		changeNotSetTip : 'field is not set to change!',
		changeTypeNotSetTip : 'There are the type of change is not set!',
		changeType : {
			hide : 'hidden',
			noHide : 'non-hidden',
			read : 'read only',
			noRead : 'non-read-only',
			required : 'Required',
			noRequired : 'optional',
			blank : 'blank',
			cascade : 'cascade'
		},
		cascadeSet : 'cascade settings',
		allNode : 'all nodes',
		startNode : 'start node',
		notBindForm : 'This node unbound form, and did not set a global form, can not be linked set! ',
		cascadeSetTip:'减少的选项至少需要设置一条记录'
	},
	task : {
		notSelectTask : "no choice task!",
		endProcess : 'confirm the end of the process do? ',
		toStart : {
			warn_msg_opinion_length : "comment can not exceed {0} characters",
			warn_msg_form_validate : "表单验证不成功,Please check whether the form is filled out correctly!",
			warn_msg_form_subtable_require : "There must be a child table records, check!",
			warn_msg_syncpath_require : "In synchronous execution path conditions, you at least need to choose a path!",
			warn_msg_reject_opinion : 'Please fill dismissed opinions!',
			warn_msg_reject_start_opinion : 'Please fill dismissed opinions!',
			warn_msg_complete_or_terminate : 'task has been completed or terminated execution!',
			warn_msg_curTask_notToDo:'当前任务不能转办!',
			success_msg_operate : 'mission success!',
			success_msg_reject : "dismissed success!",
			success_msg_reject_start : "dismissed the promoters success!",
			success_msg_form_save : "Save Form Data success!",
			error_msg_no_form : "do not set the form!",
			error_msg_complete : "mission failed!",
			error_msg_terminate : "terminate process fails!",
			error_msg_reject : "dismissed the mission failed!",
			error_msg_reject_start : "dismissed the promoters failed to!",
			error_msg_form_save : "save form data lost!",
			error_msg : "Commit failed",
			confirm_msg_operate : "Are you sure you do this?"
		},
		incToolBarNode : {
			back : "Back"
		},
		toStartEnd : {
			warn_msg_opinion_require : 'termination of views can not be empty!',
			confirm_msg : 'recognizes termination do? '
		},
		startFlowForm : {
			confirm_msg_start : "Confirm you start the process?",
			warn_msg_form_require : 'workflow form is empty, set the workflow form!',
			warn_msg_form_validate : "表单验证不成功,Please check whether the data fill in the form correctly!",
			warn_msg_form_subtable_require : "请填写子表表单数据！",
			success_msg_start : "start the process successfully!",
			success_msg_form_save : "Save Form Data success!",
			error_msg_start : "start the process failed!",
			error_msg_form_save : "save form data failed!"
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
		inputName : 'Please enter <span class="red"> class name </ span>',
		hashRecord : 'At least you want to keep a record!',
		methods : '<span class="red"> alert settings </span> in at least one record',
		minute : 'minute',
		approver : 'approver',
		approverUp : 'approver superiors',
		approverUpUp : 'approver on superiors',
		departmentHead : 'approver departments',
		inner : 'news station',
		mail : 'Mail Message',
		sms : 'SMS'

	},
	messageCopyEdit : {
		inputTitle : 'Please fill in the letter mail or station title!',
		notDef : 'No access to information related to the process definition!'
	},
	// CheckVersion.js
	checkVersion : {
		runMsg : 'has released a new version! <br/> This version has expired, can not start!',
		copyMsg : 'has released a new version! <br/> This version has expired and can not be copied!'
	},
	batchApproval : {
		confirmApproval : 'batch approval, can only approve "consent", <br/> confirm batch approval do? ',
		approvalResult : 'approval results in detail as follows:'
	},
	cancelAssign : {
		confirmCancel : 'Confirm Batch cancel a transfer to (agent) do? ',
		cancelSuccess : 'cancel turn to do (Agent) Success!',
		cancelFailure : 'cancel transfer office (agency) failed!',
		confirmBatCancel : 'Confirm Cancel transfer office (agency) do? ',
		batCancelSuccess : 'turn to do batch canceled (agency) successfully!',
		batCancelFailure : 'turn to do batch canceled (agency) failed!'
	},
	
	commuReceiver : {
		title : 'communication receiver',
		reStart : 'restart approver'
	},
	forward : {
		confirm : 'Confirm forwarding operation?'
	},
	recover : {
		confirm : 'confirm undo this process do?',
		opinion : 'Please fill out the reasons for withdrawal! ',
		failure : 'recovery failed',
		redoConfirm:'是否确认追回?',
		redoOpinion:'必须填写追回原因!'
	},
	communicate : {
		success : 'Send to communicate succeeded!',
		failure : 'Send to communicate people fail!',
		confirm : 'OK to send communication do? '
	},
	assign : {
		confirm : 'Confirm conversion to operate it?'
	},
	feedback : {
		success : 'Send Feedback successful!',
		failure : 'Send Feedback fail!',
		confirm : 'Confirm Send feedback you? '
	},
	restartTask : {
		confirm : "OK to restart the task",
		agreeConfirm : "Do you agree that the task?",
		agreeSuccess : "agree that the task is successful!",
		notAgreeConfirm : "whether the opposition to the task?",
		notAgreeSuccess : "opposition to the mission is successful!"
	},
	bpmCommonWsSet:{
		waiting:'Are calling,please wait...',
		error:'Wrong call,wrong details below:\n',
		addCustom:'Add custom parameters',
		nullArgument:'the name of argument can not be null',
		nullAlias:'WS calling alias can not be null',
		cancelBind:'Whether unbind the parameter [{0}]?',
		loading:'loading,please wait...',
		deleteMethod:'Delete current method [{0}]?',
		fillinAdress:'please fill in webService address!',
		searching:'Searching,please wait...',
		errorAdress:'Wrong WebService address or abnormal links,please check!',
		searchMethod:'please search the webservice method firstly',
		selectMethod:'please select the webservice method',
		closeCurWindow:'Wherther close current window?'
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