package com.hotent.platform.controller.system;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.ResourcesService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:子系统资源 控制器类 开发公司:广州宏天软件有限公司 开发人员:csx 创建时间:2011-12-05 17:00:54
 */
@Controller
@RequestMapping("/platform/system/resources/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class ResourcesFormController extends BaseFormController {
	@Resource
	private ResourcesService resourcesService;
	

	/**
	 * 添加或更新子系统资源。
	 * 
	 * @param request
	 * @param response
	 * @param resources
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新子系统资源",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>子系统资源" +
					"【${SysAuditLinkService.getResourcesLink(Long.valueOf(resId))}】"
			)
	public void save(HttpServletRequest request, HttpServletResponse response,Resources resources, BindingResult bindResult) throws Exception {
		PrintWriter out=response.getWriter();
		ResultMessage resultMessage = validForm("resources", resources,bindResult, request);
		if (resultMessage.getResult() == ResultMessage.Fail) {
			writeResultMessage(response.getWriter(), resultMessage);
			return;
		}
		String skinStyle = ContextUtil.getCurrentUserSkin(request);
		boolean isadd=resources.getResId() == null;
		
		String icon = resources.getIcon();
		if(StringUtils.isNotEmpty(icon))
			icon = icon.replace(request.getContextPath(), "").replace("/"+skinStyle+"/", "/{0}/");
		resources.setIcon(icon);

		String defaultUrl = resources.getDefaultUrl();
		if (defaultUrl != null) {
			defaultUrl = defaultUrl.trim();
			if(defaultUrl.equals("")){
				defaultUrl=null;
			}
			resources.setDefaultUrl(defaultUrl);
		}

		String[] aryName = request.getParameterValues("name");
		String[] aryUrl = request.getParameterValues("url");

		String resNames =  request.getParameter("resNames");
		JSONArray array = JSONArray.fromObject(resNames);
		if (resources.getResId() == null) {
			Integer rtn = resourcesService.isAliasExists(resources);
			if (rtn > 0) {
				writeResultMessage(response.getWriter(), getText("controller.resources.alias.alreadyExists"),ResultMessage.Fail);
				return;
			}
			try {
				long resId= resourcesService.addRes(resources, aryName, aryUrl);
				if(null!=array && array.size()>0)
				{
				    for(int p=0;p<array.size();p++)
				    {
					JSONObject obj = array.getJSONObject(p);
					resourcesService.addLanRes( UniqueIdUtil.genId(),resId , obj.get("lanType").toString(),obj.get("lanMsg").toString());
				    }
				}
				//删除缓存。
//				SecurityUtil.removeCacheBySystemId( resources.getSystemId());
				String result="{result:1,resId:"+resId+",operate:'add'}";
				out.print(result);
			}
			catch (Exception e) {
				writeResultMessage(response.getWriter(), getText("record.add.fail",getText("controller.resources")),ResultMessage.Fail);
			}
		} 
		else {
			Integer rtn = resourcesService.isAliasExistsForUpd(resources);
			if (rtn > 0) {
				writeResultMessage(response.getWriter(), getText("controller.resources.alias.alreadyExists"),ResultMessage.Fail);
				return;
			}
			try {
				resourcesService.updRes(resources, aryName, aryUrl);
				if(null!=array && array.size()>0)
				{
				    for(int p=0;p<array.size();p++)
				    {
					JSONObject obj = array.getJSONObject(p);
					resourcesService.updLanRes(new Long(obj.get("resId").toString()) , obj.get("lanType").toString(),obj.get("lanMsg").toString());
				    }
				}
				//删除缓存。
//				SecurityUtil.removeCacheBySystemId( resources.getSystemId());
				
				String result="{result:1,operate:'edit'}";
				out.print(result);
			} catch (Exception e) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					resultMessage = new ResultMessage(ResultMessage.Fail,getText("controller.update.fail")+":" + str);
					response.getWriter().print(resultMessage);
				} else {
					String message = ExceptionUtil.getExceptionMessage(e);
					resultMessage = new ResultMessage(ResultMessage.Fail, message);
					response.getWriter().print(resultMessage);
				}
			}
		}
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("resources", resources);
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
			SysAuditThreadLocalHolder.putParamerter("resId", resources.getResId().toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

	}

	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * 
	 * @param resId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute
	protected Resources getFormObject(@RequestParam("resId") Long resId,
			Model model) throws Exception {
		logger.debug("enter Resources getFormObject here....");
		Resources resources = null;
		if (resId != null) {
			resources = resourcesService.getById(resId);
		} 
		else {
			resources = new Resources();
		}
		return resources;
	}

}
