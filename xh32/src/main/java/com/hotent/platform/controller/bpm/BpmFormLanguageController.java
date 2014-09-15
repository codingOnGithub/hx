package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormLanguage;
import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysLanguage;
import com.hotent.platform.model.system.SysTypeKey;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormLanguageService;
import com.hotent.platform.service.bpm.BpmNodeButtonService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.system.SysLanguageService;
import com.hotent.platform.service.system.SysTypeKeyService;
/**
 *<pre>
 * 对象功能:表单国际化资源 控制器类
 * 开发公司:hotent
 * 开发人员:helh wdr
 * 创建时间:2014-01-21 16:16:09
 *</pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmFormLanguage/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormLanguageController extends BaseController
{
	@Resource
	private BpmFormLanguageService bpmFormLanguageService;
	@Resource
	private SysLanguageService sysLanguageService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private SysTypeKeyService sysTypeKeyService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeButtonService bpmNodeButtonService;

	
	/**
	 * 添加或更新表单国际化资源。
	 * @param request
	 * @param response
	 * @param bpmFormLanguage 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新国际化资源",
			detail="")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		Integer typeid = RequestUtil.getInt(request, "typeId");
		String lanJsonStr = RequestUtil.getString(request, "lanJsonStr");
		try{
			JSONArray array = JSONArray.fromObject(lanJsonStr);
			if(null!=array && array.size()>0)
			{
			    for(Object obj : array)
			    {
			    	JSONObject jobject = (JSONObject)obj;
			    	String formid = jobject.getString("formId");
					String resKey = jobject.getString("resKey");
					JSONArray lanArray = jobject.getJSONArray("lanDetail");
					Map<String,String> map = new HashMap<String, String>();
					for(Object lanObj : lanArray){
						JSONObject lanObject = (JSONObject)lanObj;
						String lanType = lanObject.getString("lanType");
						String resValue = lanObject.getString("resValue");
						map.put(lanType, resValue);
					}
					bpmFormLanguageService.batchSaveUpdate(formid, resKey, map, typeid);
			    }
			}
			resultMsg = getText("controller.save.success");
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			
		}catch(Exception e){
			resultMsg =  getText("controller.save.fail");
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 BpmFormLanguage 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected JSONObject getFormObject(HttpServletRequest request) throws Exception {
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		return obj;
    }
	
	/**
	 * 取得表单国际化资源分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看表单国际化资源分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long formid = RequestUtil.getLong(request, "formid");
		QueryFilter queryFilter = new QueryFilter(request,"bpmFormLanguageItem");
		queryFilter.addFilter("formId", formid.toString());
		queryFilter.addFilter("typeId",BpmFormLanguage.BPM_FORM_TYPE);
		queryFilter.setPageBean(null);
		List<BpmFormLanguage> bpmFormLanguages = bpmFormLanguageService.getAll(queryFilter);
		List<BpmFormField> bpmFormFieldList =bpmFormFieldService.getByTableId(formid);
		Map<String,Map<String,String>> map = new HashMap<String, Map<String,String>>();
		List<SysLanguage> languages = sysLanguageService.getAll();
		for(BpmFormLanguage bpmFormLanguage : bpmFormLanguages){
			String resKey = bpmFormLanguage.getReskey();
			Map<String,String> keyValues = map.get(resKey);
			if(BeanUtils.isEmpty(keyValues)){
				keyValues = new HashMap<String, String>();
				map.put(resKey, keyValues);
			}
			keyValues.put(bpmFormLanguage.getLantype(),bpmFormLanguage.getResvalue());
		}
	
		ModelAndView mv=this.getAutoView().addObject("bpmFormLanguageMap",map)
				                          .addObject("bpmFormFieldList",bpmFormFieldList)
										  .addObject("formid",formid)
										  .addObject("languages",languages)
										  .addObject("typeId",BpmFormLanguage.BPM_FORM_TYPE);
		return mv;
	}
	
	/**
	 * 删除表单国际化资源
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除国际化资源",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#if typeid==1>" +
					"删除表单【${SysAuditLinkService.getBpmFormTableLink(Long.valueOf(formid))}】字段" +
					"<#list StringUtils.split(reskey,\",\") as item>" +
					"【${SysAuditLinkService.getFiledName(Long.valueOf(formid),item)}】</#list>" +
					"的国际化资源" +
					"<#elseif typeid==2>" +
					"删除流程定义【${SysAuditLinkService.getBpmDefinitionLink(formid)}】节点" +
					"<#list StringUtils.split(reskey,\",\") as item>" +
					"【${SysAuditLinkService.getNodeName(String.valueOf(formid),item)}】</#list>" +
					"的国际化资源" +
			"</#if>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			String formId = RequestUtil.getString(request, "formId");
			String[] reskeys = RequestUtil.getStringAryByStr(request, "resKey");
			bpmFormLanguageService.delByFormIdAndResKeys(formId, reskeys);
			message=new ResultMessage(ResultMessage.Success,  getText("controller.del.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.del.fail") + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * ========================================================流程国际化===================================
	 */
	
	
	/**
	 * 取得流程定义国际化资源分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("defList")
	@Action(description="查看流程定义国际化资源分页列表")
	public ModelAndView defList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String actDefId = RequestUtil.getString(request, "actdefid");
		QueryFilter queryFilter = new QueryFilter(request,"bpmFormLanguageItem",false);
		queryFilter.addFilter("formId",actDefId);
		queryFilter.addFilter("typeId",BpmFormLanguage.BPM_DEFINITION_TYPE);
		List<BpmFormLanguage> bpmFormLanguages = bpmFormLanguageService.getAll(queryFilter);
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		Map<String, FlowNode> nodeIdMap  = NodeCache.getByActDefId(actDefId);
		List<FlowNode> nodeIds = getFlowNodeList(nodeIdMap,bpmDefinition);
		Map<String,Map<String,String>> map = new HashMap<String, Map<String,String>>();
		List<SysLanguage> languages = sysLanguageService.getAll();
		for(BpmFormLanguage bpmFormLanguage : bpmFormLanguages){
			String nodeId = bpmFormLanguage.getReskey();
			Map<String,String> keyValues = map.get(nodeId);
			if(BeanUtils.isEmpty(keyValues)){
				keyValues = new HashMap<String, String>();
				map.put(nodeId, keyValues);
			}
			keyValues.put(bpmFormLanguage.getLantype(),bpmFormLanguage.getResvalue());
		}
		
		Map<String,String> languageMap = new HashMap<String,String>();
		for (SysLanguage sysLanguage : languages) {
			languageMap.put(sysLanguage.getLanguage(), StringUtil.isEmpty(sysLanguage.getMemo())?sysLanguage.getLanguage():sysLanguage.getMemo());
		}
		
		
		ModelAndView mv=this.getAutoView().addObject("bpmDefinitionLanguageMap",map)
										  .addObject("bpmNodeList",nodeIds)
										  .addObject("actDefId",actDefId)
										  .addObject("languages",languages)
										  .addObject("typeId",BpmFormLanguage.BPM_DEFINITION_TYPE)
										  .addObject("languageMap",languageMap);
		return mv;
	}
	
	/**
	 * 获取表单节点
	 * @param map
	 * @param bpmDefinition
	 * @return
	 */
	private List<FlowNode> getFlowNodeList(Map<String, FlowNode> map, BpmDefinition bpmDefinition) {
		List<FlowNode> list =  new ArrayList<FlowNode>();
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
			String nodeId = (String) iterator.next();	
			list.add(map.get(nodeId));
		}
		if(BeanUtils.isNotEmpty(bpmDefinition)){
			FlowNode flowNode = new FlowNode();
			flowNode.setNodeId(bpmDefinition.getDefKey());
			flowNode.setNodeName(bpmDefinition.getSubject());
			list.add(flowNode);
		}
		return list;
	}
	
	/**
	 * ========================================================操作按钮国际化===================================
	 */
	@RequestMapping("buttonList")
	//@Action(description="操作按钮国际化资源列表")
	public ModelAndView buttonList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long defId = RequestUtil.getLong(request,"defId",0);
		String nodeId= RequestUtil.getString(request,"nodeId");
		String actdefId= bpmDefinitionService.getById(defId).getActDefId();
		List<BpmNodeButton> list=null;
		if (StringUtil.isEmpty(nodeId)) {
			list=bpmNodeButtonService.getByStartForm(defId);
		}else {
			list=bpmNodeButtonService.getByDefNodeId(defId, nodeId);
		}
		
		
		//根据按钮主键获取对应的国际化资源
		Map<String,Map<String,String>> map = new HashMap<String, Map<String,String>>();
		if (BeanUtils.isNotEmpty(list)) {
			for (BpmNodeButton button:list) {
				List<BpmFormLanguage> bpmFormLanguageList=bpmFormLanguageService.getByFormIdAndResKey(button.getActdefid(),button.getId().toString(),  BpmFormLanguage.BPM_BUTTON_TYPE);
				if (BeanUtils.isNotEmpty(bpmFormLanguageList)) {
					Map<String,String> keyValues=new HashMap<String, String>();
					for(BpmFormLanguage bpmFormLanguage :bpmFormLanguageList){
						keyValues.put(bpmFormLanguage.getLantype(),bpmFormLanguage.getResvalue());
					}
					map.put(button.getId().toString()+"_Key", keyValues);
				}
			}
		}
		
		//获取语言表数据
		List<SysLanguage> languages=sysLanguageService.getAll();
		Map<String,String> languageMap = new HashMap<String,String>();
		for (SysLanguage sysLanguage : languages) {
			languageMap.put(sysLanguage.getLanguage(), StringUtil.isEmpty(sysLanguage.getMemo())?sysLanguage.getLanguage():sysLanguage.getMemo());
		}

		ModelAndView mv=this.getAutoView()
						.addObject("bpmNodeButtonList", list)
						.addObject("bpmFormLanguageMap",map)
						.addObject("actdefId",actdefId)
						.addObject("defId",defId)
						.addObject("languages",languages)
						.addObject("typeId",BpmFormLanguage.BPM_BUTTON_TYPE)
						.addObject("languageMap",languageMap);
		
		return mv;
	}
	
	/**
	 * ========================================================分类管理国际化===================================
	 */
	
	/**
	 * 取得流程定义国际化资源分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("typeList")
	@Action(description="查看分类管理国际化资源分页列表")
	public ModelAndView typeList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		QueryFilter queryFilter = new QueryFilter(request,"bpmFormLanguageItem",false);
		queryFilter.addFilter("typeId",BpmFormLanguage.SYS_TYPE_KEY);
		List<BpmFormLanguage> bpmFormLanguages = bpmFormLanguageService.getAll(queryFilter);
		List<SysTypeKey> sysTypeKeyList = sysTypeKeyService.getAll();
		Map<String,Map<String,String>> map = new HashMap<String, Map<String,String>>();
		List<SysLanguage> languages = sysLanguageService.getAll();
		for(BpmFormLanguage bpmFormLanguage : bpmFormLanguages){
			String key = bpmFormLanguage.getReskey();
			Map<String,String> keyValues = map.get(key);
			if(BeanUtils.isEmpty(keyValues)){
				keyValues = new HashMap<String, String>();
				map.put(key, keyValues);
			}
			keyValues.put(bpmFormLanguage.getLantype(),bpmFormLanguage.getResvalue());
		}
		
		ModelAndView mv=this.getAutoView().addObject("sysTypeKeyLanguageMap",map)
										  .addObject("languages",languages)
										  .addObject("sysTypeKeyList",sysTypeKeyList)
										  .addObject("typeId",BpmFormLanguage.SYS_TYPE_KEY);
		return mv;
	}
	
	
	/**
	 * 删除分类标志国际化资源
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("typeDel")
	@Action(description="删除分类标志国际化资源",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除分类标志国际化资源")
	public void typeDel(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			String[] reskeys = RequestUtil.getStringAryByStr(request, "resKey");
			bpmFormLanguageService.delByKeysAndTypeId(reskeys, BpmFormLanguage.SYS_TYPE_KEY);
			message=new ResultMessage(ResultMessage.Success,  getText("controller.del.success"));
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, getText("controller.del.fail") + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
}

