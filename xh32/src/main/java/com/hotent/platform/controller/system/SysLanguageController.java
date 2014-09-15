package com.hotent.platform.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;

import org.springframework.web.servlet.ModelAndView;


import com.hotent.core.util.PinyinUtil;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysLanguage;
import com.hotent.platform.service.system.SysLanguageService;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.util.StringUtil;
/**
 *<pre>
 * 对象功能:系统支持的语言 控制器类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-05-25 18:29:05
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysLanguage/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SysLanguageController extends BaseController
{
	@Resource
	private SysLanguageService sysLanguageService;
	
	
	/**
	 * 添加或更新系统支持的语言。
	 * @param request
	 * @param response
	 * @param sysLanguage 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统支持的语言",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>系统支持的语言【语言：${sysLanguage.language}，备注：${sysLanguage.memo}】"
	)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		SysLanguage sysLanguage=getFormObject(request);
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("sysLanguage", sysLanguage);
			SysAuditThreadLocalHolder.putParamerter("isAdd", sysLanguage.getId()==null||sysLanguage.getId()==0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		//添加系统日志信息 -E

		try{
			if(sysLanguage.getId()==null||sysLanguage.getId()==0){
				sysLanguage.setId(UniqueIdUtil.genId());
				sysLanguageService.add(sysLanguage);
				resultMsg=getText("record.added",getText("controller.sysLanguage"));
			}else{
				//如果当前为默认，则先修改默认语言为非默认。
				if(sysLanguage.getIsdefault().intValue()==SysLanguage.DEFAULT_LANGUAGE){
					sysLanguageService.updNotDefault();
				}
			    sysLanguageService.update(sysLanguage);
				resultMsg=getText("record.updated",getText("controller.sysLanguage"));
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 SysLanguage 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected SysLanguage getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		SysLanguage sysLanguage = (SysLanguage)JSONObject.toBean(obj, SysLanguage.class);
		
		return sysLanguage;
    }
	
	/**
	 * 取得系统支持的语言分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看系统支持的语言分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter filter= new QueryFilter(request,"sysLanguageItem",false);
		List<SysLanguage> list=sysLanguageService.getAll(filter);
		ModelAndView mv=this.getAutoView().addObject("sysLanguageList",list);
		
		return mv;
	}
	
	/**
	 * 删除系统支持的语言
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除系统支持的语言",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除系统支持的语言" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=sysLanguageService.getById(Long.valueOf(item))/>" +
						"【语言：${entity.language}，备注：${entity.memo}】"+
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysLanguageService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, getText("controller.del.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.del.fail")+":" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 设置默认语言
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("setDefault")
	@Action(description="设置默认语言", detail="设置默认语言")
	public void setDefault(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long id =RequestUtil.getLong(request, "id");
			sysLanguageService.updNotDefault();
			sysLanguageService.setDefault(id);
			message=new ResultMessage(ResultMessage.Success, getText("controller.setDefault.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.setDefault.fail")+":" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 启用语言
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("enable")
	@Action(description="启用语言", detail="启用语言")
	public void enable(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long id =RequestUtil.getLong(request, "id");
			sysLanguageService.enable(id);
			message=new ResultMessage(ResultMessage.Success, getText("controller.setDefault.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.setDefault.fail")+":" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 禁用语言
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("disable")
	@Action(description="禁用语言", detail="禁用语言")
	public void disable(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long id =RequestUtil.getLong(request, "id");
			sysLanguageService.disable(id);
			message=new ResultMessage(ResultMessage.Success, getText("controller.setDefault.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.setDefault.fail")+":" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑系统支持的语言
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑系统支持的语言")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		SysLanguage sysLanguage=sysLanguageService.getById(id);
		
		return getAutoView().addObject("sysLanguage",sysLanguage).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得系统支持的语言明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看系统支持的语言明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		SysLanguage sysLanguage = sysLanguageService.getById(id);	
		return getAutoView().addObject("sysLanguage", sysLanguage);
	}
	
	/**
	 * 获取所有语言类型
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getAllLanguages")
	public String getAllLanguages(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return sysLanguageService.getAllLanguages();
	}
	
	/**
	 * 获取表单国际化资源key
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("getFormI18nKey")
	public String getFormI18nKey(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String formResValue = RequestUtil.getString(request, "formResValue");
		if(StringUtil.isEmpty(formResValue))
			return "";
		String pingyin = PinyinUtil.getPinYinHeadCharFilter(formResValue);
		return pingyin;
	}
}

