package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.query.util.QueryUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.platform.model.system.ResourcesLang;
import com.hotent.platform.service.system.ResourcesLangService;
/**
 *<pre>
 * 对象功能:系统语言资源表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-01-08 17:17:46
 *</pre>
 */
@Controller
@RequestMapping("/demo/bus/resourcesLang/")
public class ResourcesLangController extends BaseController
{
	@Resource
	private ResourcesLangService resourcesLangService;
	
	
	/**
	 * 添加或更新系统语言资源表。
	 * @param request
	 * @param response
	 * @param resourcesLang 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统语言资源表")
	public void save(HttpServletRequest request, HttpServletResponse response,ResourcesLang resourcesLang) throws Exception
	{
		String resultMsg=null;		
		try{
			if(resourcesLang.getId()==null||resourcesLang.getId()==0){
				resourcesLang.setId(UniqueIdUtil.genId());
				resourcesLangService.add(resourcesLang);
				resultMsg=getText("record.added",getText("controller.resourcesLang"));
			}else{
			    resourcesLangService.update(resourcesLang);
				resultMsg=getText("record.updated",getText("controller.resourcesLang"));
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得系统语言资源表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看系统语言资源表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String displayTagId = "resourcesLangItem";
		BusQueryRule busQueryRule = QueryUtil.getBusQueryRule(displayTagId,
				request);
		List<ResourcesLang> list=resourcesLangService.getAll(new QueryFilter(request,displayTagId),busQueryRule);
		
		return this.getAutoView()
				.addObject("resourcesLangList",list)
				.addObject("busQueryRule",busQueryRule);
	}
	
	/**
	 * 删除系统语言资源表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除系统语言资源表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			resourcesLangService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, getText("record.deleted",getText("controller.resourcesLang")));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("record.delete.fail",getText("controller.resourcesLang")) + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑系统语言资源表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑系统语言资源表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		ResourcesLang resourcesLang=resourcesLangService.getById(id);
		
		return getAutoView().addObject("resourcesLang",resourcesLang)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得系统语言资源表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看系统语言资源表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		ResourcesLang resourcesLang = resourcesLangService.getById(id);	
		return getAutoView().addObject("resourcesLang", resourcesLang);
	}
	
}
