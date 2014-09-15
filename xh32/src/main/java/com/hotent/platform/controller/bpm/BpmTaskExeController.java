package com.hotent.platform.controller.bpm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmTaskExeService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * <pre>
 * 对象功能:任务转办代理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-03-27 12:02:35
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmTaskExe/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmTaskExeController extends BaseController {
	@Resource
	private BpmTaskExeService bpmTaskExeService;
	@Resource
	private BpmService bpmService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private  BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	Map<String, IMessageHandler> handlersMap;
	/**
	 * 添加或更新任务转办代理。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assignSave")
	@Action(description = "添加任务转办代理",execOrder=ActionExecOrder.AFTER,
			detail=
					"<#if StringUtil.isNotEmpty(isSuccess)>" +
					"添加任务【${SysAuditLinkService.getProcessRunDetailLink(taskId)}】转办代理给【${SysAuditLinkService.getSysUserLink(Long.valueOf(assigneeId))}】" +
					"成功" +
					"<#else>添加任务转办代理失败</#if>")
	public void assignSave(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String resultMsg = null;
		try {
			String taskId = RequestUtil.getString(request, "taskId");
			Long assigneeId = RequestUtil.getLong(request, "assigneeId");
			String assigneeName = RequestUtil.getString(request, "assigneeName");
			String memo = RequestUtil.getString(request, "memo");
			
			String informType = RequestUtil.getStringAry(request, "informType");
		
			
			SysUser sysUser = ContextUtil.getCurrentUser();
			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (BeanUtils.isEmpty(taskEntity)){
				writeResultMessage(response.getWriter(), getText("controller.bpmTaskExe.dealWith"),ResultMessage.Fail);
				return;
			}
			String assignee = taskEntity.getAssignee();
			//任务人不为空且和当前用户不同。
			if(ServiceUtil.isAssigneeNotEmpty(assignee) && assignee.equals(assigneeId)){
				writeResultMessage(response.getWriter(), getText("controller.bpmTaskExe.notAssignee"),ResultMessage.Fail);
				return;
			}
			if(ServiceUtil.isAssigneeNotEmpty(assignee) ){	
				if(!assignee.equals(sysUser.getUserId().toString())){
					writeResultMessage(response.getWriter(),getText("controller.bpmTaskExe.notExecutors"),ResultMessage.Fail);
					return;
				}
			}
			
			
			ProcessRun processRun = processRunService.getByActInstanceId(new Long( taskEntity.getProcessInstanceId()));
			
			String actDefId=processRun.getActDefId();
			
			boolean rtn= bpmDefinitionService.allowDivert(actDefId);
			if(!rtn){
				writeResultMessage(response.getWriter(), getText("controller.bpmTaskExe.notAllowAssignee"),ResultMessage.Fail);
				return;
			}
			
			boolean isAssign=bpmTaskExeService.getByIsAssign(new Long(taskId));
			if(isAssign){
				writeResultMessage(response.getWriter(), getText("controller.bpmTaskExe.hasAssignee"),ResultMessage.Fail);
				return;
			}
			
			
			BpmTaskExe bpmTaskExe = new BpmTaskExe();
			bpmTaskExe.setId(UniqueIdUtil.genId());
			bpmTaskExe.setTaskId(new Long(taskId));
			bpmTaskExe.setAssigneeId(assigneeId);
			bpmTaskExe.setAssigneeName(assigneeName);
			bpmTaskExe.setOwnerId(sysUser.getUserId());
			bpmTaskExe.setOwnerName(sysUser.getFullname());
			bpmTaskExe.setSubject(processRun.getSubject());
			bpmTaskExe.setStatus(BpmTaskExe.STATUS_INIT);
			bpmTaskExe.setMemo(memo);
			bpmTaskExe.setCratetime(new Date());
			bpmTaskExe.setActInstId(new Long(taskEntity.getProcessInstanceId()));
			bpmTaskExe.setTaskDefKey(taskEntity.getTaskDefinitionKey());
			bpmTaskExe.setTaskName(taskEntity.getName());
			bpmTaskExe.setAssignType(BpmTaskExe.TYPE_TRANSMIT);
			bpmTaskExe.setRunId(processRun.getRunId());
			bpmTaskExe.setTypeId(processRun.getTypeId());
			bpmTaskExe.setInformType(informType);
			bpmTaskExeService.assignSave(bpmTaskExe);
			SysAuditThreadLocalHolder.putParamerter("isSuccess", "true");
			writeResultMessage(response.getWriter(), getText("controller.bpmTaskExe.success"),ResultMessage.Success);
		} 
		catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(),resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
		
	}

	/**
	 * 转办事宜
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = " 转办事宜")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmTaskExeItem");

		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		filter.addFilter("exceptDefStatus", 3);
		List<BpmTaskExe> list = bpmTaskExeService.accordingMattersList(filter);
		ModelAndView mv = this.getAutoView().addObject("bpmTaskExeList", list);

		return mv;
	}
	
	
	/**
	 * 转办事宜
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("accordingMattersList")
	@Action(description = " 转办事宜")
	public ModelAndView accordingMattersList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmTaskExeItem");
		Long userId = ContextUtil.getCurrentUserId();
		filter.addFilter("ownerId", userId);
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		
		List<BpmTaskExe> list = bpmTaskExeService.accordingMattersList(filter);
		ModelAndView mv = this.getAutoView().addObject("bpmTaskExeList", list);

		return mv;
	}

	/**
	 * 取得我收到的任务分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myTaskList")
	@Action(description = "查看我收到的任务")
	public ModelAndView myTaskList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmTaskExeItem");
		filter.addFilter("assigneeId", ContextUtil.getCurrentUserId());
		 filter.addFilter("assignType",BpmTaskExe.TYPE_TRANSMIT);
		List<BpmTaskExe> list = bpmTaskExeService.getAll(filter);
		ModelAndView mv = this.getAutoView().addObject("bpmTaskExeList", list);

		return mv;
	}
	
	/**
	 * 取消转办
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("cancel")
	@Action(description = "取消转办/代理")
	public void cancel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		String opinion=RequestUtil.getString(request, "opinion");
		String informType=RequestUtil.getString(request, "informType");
//		SysUser sysUser = ContextUtil.getCurrentUser();

		String bpmTaskExeType=null;
		
		
		try {
			BpmTaskExe bpmTaskExe = bpmTaskExeService.getById(id);
			
			if(BpmTaskExe.TYPE_ASSIGNEE == bpmTaskExe.getAssignType()){
				bpmTaskExeType=getText("controller.bpmTaskExe.cancel.assignee");
			}else if(BpmTaskExe.TYPE_TRANSMIT == bpmTaskExe.getAssignType()){
				bpmTaskExeType=getText("controller.bpmTaskExe.cancel.transmit");
			}else if(BpmTaskExe.TYPE_TRANSTO == bpmTaskExe.getAssignType()){
				bpmTaskExeType=getText("controller.bpmTaskExe.cancel.trans");;
			}
			Object [] args = {bpmTaskExeType};
			Long taskId= bpmTaskExe.getTaskId();
			TaskEntity taskEntity=bpmService.getTask(taskId.toString());
			if(taskEntity==null){
				writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, getText("controller.bpmTaskExe.cancel.taskEnd")));
				return ;
			}
			Long ownerId = bpmTaskExe.getOwnerId();
			if(ownerId==null){
				writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, getText("controller.bpmTaskExe.cancel.origExecutors")));
				return ;
			}
			SysUser sysUser=sysUserService.getById(ownerId);
			if(sysUser==null){
				writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, getText("controller.bpmTaskExe.cancel.origExecutors")));
				return ;
			}
			//取消转办/代理。
			bpmTaskExeService.cancel(bpmTaskExe, sysUser,opinion,informType);
			// 记录日志
			ProcessRun processRun = processRunService.getByActInstanceId(bpmTaskExe.getActInstId());
			String memo = getText("controller.bpmTaskExe.cancel.flowMemo",processRun.getSubject())+ "," +getText("controller.bpmTaskExe.addRunLong.memo",sysUser.getFullname(),bpmTaskExe.getSubject());
			bpmRunLogService.addRunLog(processRun.getRunId(),BpmRunLog.OPERATOR_TYPE_BACK, memo);
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, getText("controller.bpmTaskExe.cancel.success",args)));
		} catch (Exception e) {
			Object [] args = {bpmTaskExeType};
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, getText("controller.bpmTaskExe.cancel.fail",args) + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(
						ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}

	}
	
	private void addRunLong(List<BpmTaskExe> list){
		SysUser sysUser=ContextUtil.getCurrentUser();
		for(BpmTaskExe bpmTaskExe:list){
			String memo =getText("controller.bpmTaskExe.addRunLong.memo",sysUser.getFullname(),bpmTaskExe.getSubject());
			bpmRunLogService.addRunLog(bpmTaskExe.getRunId(),BpmRunLog.OPERATOR_TYPE_BACK, memo);
		}
	}

	/**
	 * 批量取消转办
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("cancelBat")
	@Action(description = "批量取消转办")
	public void cancelBat(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			SysUser sysUser = ContextUtil.getCurrentUser();
			String idStr = RequestUtil.getString(request, "ids");
			String opinion=RequestUtil.getString(request, "opinion");
			String informType=RequestUtil.getString(request, "informType");
			if(StringUtil.isEmpty(idStr)){
				writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, getText("controller.bpmTaskExe.cancelBat.pleaseSelect")));
				return;
			}
			List<BpmTaskExe> list= bpmTaskExeService.cancelBat(idStr, opinion, informType, sysUser);
			//添加日志。
			addRunLong(list);

			String message=MessageUtil.getMessage();
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, message));
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, getText("controller.bpmTaskExe.cancelBat.fail")));
		}
	}

	/**
	 * 删除任务转办代理
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除任务转办代理")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			bpmTaskExeService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, getText("controller.del.success"));
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, getText("controller.del.fail")+":"+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑任务转办代理
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑任务转办代理")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		String returnUrl = RequestUtil.getPrePage(request);
		BpmTaskExe bpmTaskExe = bpmTaskExeService.getById(id);

		return getAutoView().addObject("bpmTaskExe", bpmTaskExe).addObject(
				"returnUrl", returnUrl);
	}

	/**
	 * 取得任务转办代理明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看任务转办代理明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		BpmTaskExe bpmTaskExe = bpmTaskExeService.getById(id);
		return getAutoView().addObject("bpmTaskExe", bpmTaskExe);
	}

	/**
	 * 转办窗口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assign")
	@Action(description = "转办窗口")
	public ModelAndView assign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = RequestUtil.getString(request, "taskId");
		SysTemplate sysTemplate = sysTemplateService.getDefaultByUseType(SysTemplate.USE_TYPE_DELEGATE);
		
		return this.getAutoView()
				.addObject("taskId", taskId)
				.addObject("sysTemplate", sysTemplate)
				.addObject("handlersMap",handlersMap);
	}
	
	
	/**
	 * 是否允许转办
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("isAssigneeTask")
	@Action(description = "是否允许转办")
	public void isAssigneeTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long taskId = RequestUtil.getLong(request, "taskId");
		ResultMessage result  = null;
		if(!bpmTaskExeService.isAssigneeTask(taskId))
			result = new ResultMessage(ResultMessage.Success, getText("controller.bpmTaskExe.isAssigneeTask.allow"));
		else
			result = new ResultMessage(ResultMessage.Fail, getText("controller.bpmTaskExe.isAssigneeTask.notAllow"));
	
		writeResultMessage(response.getWriter(), result);
	}
	
	@RequestMapping("batCancel")
	@Action(description="批量取消")
	public ModelAndView batCancel(HttpServletRequest request) throws Exception
	{
//		String ids=RequestUtil.getString(request,"ids");
//		List<String> paramList=new ArrayList<String>();
//		paramList.add(ids);
		return getAutoView()
//				.addObject("param",paramList)
				.addObject("handlersMap",handlersMap);
				
	}
	
	@RequestMapping("cancelDialog")
	@Action(description="批量取消")
	public ModelAndView cancelDialog(HttpServletRequest request) throws Exception
	{
		return getAutoView()
				.addObject("handlersMap",handlersMap);
				
	}
}
