package com.hotent.platform.controller.system;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.engine.IScript;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;

import com.hotent.platform.model.system.AliasScript;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.AliasScriptService;
import com.hotent.core.web.ResultMessage;
/**
 *<pre>
 * 对象功能:自定义别名脚本表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/aliasScript/")
public class AliasScriptController extends BaseController
{
	@Resource
	private AliasScriptService aliasScriptService;
	
	@Resource
	private GroovyScriptEngine groovyScriptEngine;
	
	/**
	 * 添加或更新自定义别名脚本表。
	 * @param request
	 * @param response
	 * @param aliasScript 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新自定义别名脚本表")
	public void save(HttpServletRequest request, HttpServletResponse response,AliasScript aliasScript) throws Exception
	{
		String resultMsg=null;		
		try{
			if(aliasScript.getId()==null||aliasScript.getId()==0){
				aliasScript.setId(UniqueIdUtil.genId());
				aliasScriptService.add(aliasScript);
				resultMsg=getText("record.added",getText("controller.aliasScript.aliasScript"));
			}else{
			    aliasScriptService.update(aliasScript);
				resultMsg=getText("record.updated",getText("controller.aliasScript.aliasScript"));
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得自定义别名脚本表分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看自定义别名脚本表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AliasScript> list=aliasScriptService.getAll(new QueryFilter(request,"aliasScriptItem"));
		ModelAndView mv=this.getAutoView().addObject("aliasScriptList",list);
		return mv;
	}
	
	/**
	 * 删除自定义别名脚本表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除自定义别名脚本表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			aliasScriptService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, getText("record.deleted",getText("controller.aliasScript.aliasScript")));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("record.delete.fail",getText("controller.aliasScript.aliasScript")) + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑自定义别名脚本表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑自定义别名脚本表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		//获取IScript的实现类
		List<Class<?>> implClasses = FileUtil.getAllClassesByInterface(IScript.class, false);
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AliasScript aliasScript=aliasScriptService.getById(id);
		//添加系统日志信息 -B
		SysAuditThreadLocalHolder.putParamerter("isAdd", aliasScript==null );
		return getAutoView().addObject("aliasScript",aliasScript)
							.addObject("implClasses",implClasses)
							.addObject("returnUrl", returnUrl);		
	}

	/**
	 * 取得自定义别名脚本表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看自定义别名脚本表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AliasScript aliasScript = aliasScriptService.getById(id);	
		return getAutoView().addObject("aliasScript", aliasScript);
	}
	
	/**
	 * 通过类名获取类的所有方法
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getMethodsByName")
	@ResponseBody	
	public String getMethodsByName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String name = RequestUtil.getString(request, "name");
		JSONObject jobject = new JSONObject(); 		
		try{
			JSONArray jarray = aliasScriptService.getMethodsByName(name);
			jobject.accumulate("result", true).accumulate("methods", jarray);
		}
		catch(Exception ex){
			jobject.accumulate("result", false).accumulate("message", ex.getMessage());
		}
		return jobject.toString();
	}
	
	
	/**
	 * 测试自定义别名脚本内容
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("preview")
	@Action(description="测试自定义别名脚本内容")
	public void preview(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter writer = response.getWriter();
		Long id=RequestUtil.getLong(request,"id");
		String scriptStr=RequestUtil.getString(request, "scriptStr", "");
		String alias=RequestUtil.getString(request, "alias", "");
		Map returnMap=new HashMap();
		if("".equals(scriptStr)){
			returnMap.put("isSuccess", 1);
			returnMap.put("msg", getText("controller.aliasScript.getInforFail"));
			returnMap.put("result", "");
		}else{
			//[{"paraName":"argo","paraType":"java.lang.String","paraValue":"te"},{"paraName":"arg1","paraType":"java.lang.String","paraValue":"gg"}]
			Map map=new HashMap();
			boolean mark = false;
			if(!"".equals(alias)){
				JSONArray array = JSONArray.fromObject(alias);
				for (int i = 0; i < array.size(); i++){
					JSONObject obj = array.getJSONObject(i);
					String paraValue = obj.getString("paraValue");
					String paraType = obj.getString("paraType");
					if(obj.containsKey("isRequired")&&"1".equals(obj.getString("isRequired"))){						
						if(StringUtil.isEmpty(paraValue)){
							mark = true;	
							returnMap.put("isSuccess", 2);
							returnMap.put("msg", getText("controller.aliasScript.preview.paraEmpty",obj.getString("paraName")));
							returnMap.put("result", "");
							break;
						}
					}
					
					Object javaObj = paraValue;
					//类型转换
					if(obj.containsKey("paraType")&&StringUtil.isNotEmpty(paraType)&&StringUtil.isNotEmpty(paraValue)){
						javaObj = this.getValueObject(paraValue,paraType);
					}
					map.put(obj.getString("paraName"),javaObj);
				}
			}
			if(!mark){
				map.put("userId",1);
				Object result = groovyScriptEngine.executeObject(scriptStr, map);	
				returnMap.put("isSuccess", 0);
				returnMap.put("msg", getText("controller.aliasScript.preview.getInforSucc"));
				returnMap.put("result", result);
			}
			
		}
		JSONObject obj = JSONObject.fromObject(returnMap);
		writer.print(obj.toString());
	}
	
	
	/**
	 * 取得自定义别名脚本表明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toPreview")
	@Action(description="查看自定义别名脚本表明细")
	public ModelAndView toPreview(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String scriptStr=RequestUtil.getString(request, "scriptStr", "");
		String alias=RequestUtil.getString(request, "alias", "");	
		return getAutoView().addObject("scriptStr", scriptStr).addObject("alias", alias);
	}
	
	/**
	 * 检查自定义别名脚本的别名有没有重复
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkAliasName")
	@Action(description="测试自定义别名脚本内容")
	public void checkAliasName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter writer = response.getWriter();
		String aliasName=RequestUtil.getString(request, "aliasName", "");
		Long userId = ContextUtil.getCurrentUserId();
		Map returnMap=new HashMap();
		if("".equals(aliasName)){
			returnMap.put("isSuccess", 2);
			returnMap.put("msg", getText("controller.aliasScript.checkAliasName.isEmpty"));
		}else{
			Map map=new HashMap();		
			map.put("aliasName",aliasName);
			AliasScript as= aliasScriptService.getAliasScriptByName(userId, map);
			if(BeanUtils.isNotEmpty(as)&&BeanUtils.isNotEmpty(as.getAliasName())){
				returnMap.put("isSuccess", 1);
				returnMap.put("msg", getText("controller.aliasScript.checkAliasName.unUsable",aliasName));
			}else{
				returnMap.put("isSuccess", 0);
				returnMap.put("msg", getText("controller.aliasScript.checkAliasName.usable",aliasName));
			}
		}
		JSONObject obj = JSONObject.fromObject(returnMap);
		writer.print(obj.toString());
	}
	
	
	/**
	 * 通过别名获取脚本并执行别名脚本返回数据  	//@ResponseBody
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("executeAliasScript")
	@Action(description="测试自定义别名脚本内容")
	public void executeAliasScript(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PrintWriter writer = response.getWriter();
		String aliasName=RequestUtil.getString(request, "aliasName", "");
		Long userId = ContextUtil.getCurrentUserId();
		Map returnMap=new HashMap();
		if("".equals(aliasName)){
			returnMap.put("isSuccess", 1);
			returnMap.put("msg", "别名为空，执行失败！");
			returnMap.put("result", "");
		}else{
			Map map=new HashMap();	
			map.put("aliasName",aliasName);
			AliasScript as= aliasScriptService.getAliasScriptByName(userId, map);
			if(BeanUtils.isNotEmpty(as)&&BeanUtils.isNotEmpty(as.getAliasName())){
				if("1".equals(as.getEnable().toString())){       //别名脚本禁用了！（ 0为可用，1为禁用）
					returnMap.put("isSuccess", 1);
					returnMap.put("msg", getText("controller.aliasScript.executeAliasScript.unUsable",aliasName));
					returnMap.put("result", "");
				}else{
					String scriptStr ="";
					scriptStr += as.getClassInsName()+"."+as.getMethodName();
					String jsonStr = as.getArgument();
					boolean mark = false;
					if(StringUtil.isNotEmpty(jsonStr)&&!"[]".equals(jsonStr)){
						JSONArray arry = JSONArray.fromObject(jsonStr);
						String str = "";
						for (int i = 0; i < arry.size(); i++){
							JSONObject obj = arry.getJSONObject(i);
							String paraName = obj.getString("paraName");
							String paraValue = RequestUtil.getString(request, paraName, "");
							String paraType = obj.getString("paraType");
							if(obj.containsKey("isRequired")&&"1".equals(obj.getString("isRequired"))){						
								if(StringUtil.isEmpty(paraValue)){
									mark = true;	
									returnMap.put("isSuccess", 1);
									returnMap.put("msg", getText("controller.aliasScript.executeAliasScript.aliasAndpara",aliasName,paraName));
									returnMap.put("result", "");
									break;
								}
							}
							
							Object javaObj = paraValue;
							//类型转换
							if(obj.containsKey("paraType")&&StringUtil.isNotEmpty(paraType)&&StringUtil.isNotEmpty(paraValue)){
								javaObj = this.getValueObject(paraValue,paraType);
							}
							map.put(paraName,javaObj);
							//map.put(paraName,paraValue);
							str += paraName+",";
						}
						str = str.substring(0, str.length()-1);
						scriptStr += "("+str+")";
					}else{
						scriptStr +="()";
					}
					
					if(!mark){
						map.put("userId",1);
						Object result = groovyScriptEngine.executeObject(scriptStr, map);	
						returnMap.put("isSuccess", 0);
						returnMap.put("msg", getText("controller.aliasScript.preview.getInforSucc"));
						returnMap.put("result", result);
					}
				}
			}else{
				returnMap.put("isSuccess", 1);
				returnMap.put("msg", getText("controller.aliasScript.executeAliasScript.aliasNonentity",aliasName));
				returnMap.put("result", "");
			}
		}
		JSONObject obj = JSONObject.fromObject(returnMap);
		writer.print(obj.toString());
	}
	
	
	/**
	 * 别名脚本开发帮助
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("goToHelp")
	@Action(description="查看自定义别名脚本表明细")
	public ModelAndView goToHelp(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		return getAutoView();
	}
	
	public Object getValueObject(String paraValue,String paraType){
		Object javaObj = paraValue;
		paraType = paraType.toLowerCase();
		if(paraType.contains("string")){
			javaObj = paraValue.toString();
		}else if(paraType.contains("date")){
			javaObj = TimeUtil.convertString(paraValue, "yyyy-MM-dd");
		}else if(paraType.contains("long")){
			javaObj = Long.parseLong(paraValue);
		}else if(paraType.contains("byte")){
			javaObj = paraValue.getBytes();
		}else if(paraType.contains("short")){
			javaObj = Short.parseShort(paraValue);
		}else if(paraType.contains("float")){
			javaObj = Float.parseFloat(paraValue);
		}else if(paraType.contains("char")){
			char[] a = paraValue.toCharArray();
			javaObj = a[0];
		}else if(paraType.contains("double")){
			javaObj = Double.parseDouble(paraValue);
		}else if(paraType.contains("boolean")){
			javaObj = Boolean.parseBoolean(paraValue);
		}else if(paraType.contains("int")){
			javaObj =  Integer.parseInt(paraValue);
		}else if(paraType.contains("map")){	
			//暂时不处理
		}else if(paraType.contains("list")){
			//暂时不处理
		}
		return javaObj;
	}
	
}
