
package com.hotent.demo.controller.ztree;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.demo.model.ztree.SysZtree;
import com.hotent.demo.service.ztree.SysZtreeService;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.system.SysDataSourceService;
/**
 * 对象功能:SYS_ZTREE 控制器类
 */
@Controller
@RequestMapping("/demo/ztree/sysZtree/")
public class SysZtreeController extends BaseController
{
	@Resource
	private SysZtreeService sysZtreeService;
	@Resource
	private SysDataSourceService sysDataSourceService;
	/**
	 * 添加或更新SYS_ZTREE。
	 * @param request
	 * @param response
	 * @param sysZtree 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新SYS_ZTREE")
	public void save(HttpServletRequest request, HttpServletResponse response,SysZtree sysZtree) throws Exception
	{
		String resultMsg=null;		
		try{
			if(sysZtree.getTypeid()==null||sysZtree.getTypeid().intValue()==0){
				Long typeid=UniqueIdUtil.genId();
				sysZtree.setTypeid(typeid);
				sysZtreeService.add(sysZtree);
				resultMsg=getText("添加","SYS_ZTREE");
			}else{
			    sysZtreeService.update(sysZtree);
				resultMsg=getText("更新","SYS_ZTREE");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得SYS_ZTREE分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看SYS_ZTREE分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysZtree> list=sysZtreeService.getAll(new QueryFilter(request,"sysZtreeItem"));
		ModelAndView mv=this.getAutoView().addObject("sysZtreeList",list);
		
		return mv;
	}
	
	/**
	 * 删除SYS_ZTREE
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除SYS_ZTREE")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			sysZtreeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除SYS_ZTREE成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑SYS_ZTREE
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑SYS_ZTREE")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Long canReturn=RequestUtil.getLong(request,"canReturn",0);
		String returnUrl=RequestUtil.getPrePage(request);
		
		SysZtree sysZtree= null;
		if (id != 0) {
			sysZtree = sysZtreeService.getById(id);
		} else {
			sysZtree = new SysZtree();
			sysZtree.setTypeid(0l);
		}
		List<SysDataSource> dsList = sysDataSourceService.getAll();
		return getAutoView().addObject("sysZtree",sysZtree)
							.addObject("returnUrl", returnUrl)
							.addObject("dsList", dsList)
							.addObject("canReturn", canReturn);
	}

	/**
	 * 取得SYS_ZTREE明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看SYS_ZTREE明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long typeid=RequestUtil.getLong(request,"id");
		SysZtree sysZtree=sysZtreeService.getById(typeid);
		return getAutoView().addObject("sysZtree", sysZtree);
	}
	
	/**
	 * 根据别名取得SysZtree对象
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTreeTypeByAlias")
	@Action(description="根据别名取得SysZtree对象")
	public void getTreeTypeByAlias(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String alias=RequestUtil.getString(request,"alias");
		SysZtree sysZtree=sysZtreeService.getTreeTypeByAlias(alias);
		JSONArray json=new JSONArray();
		json.add(sysZtree); 
		PrintWriter out = response.getWriter();
		out.print(json.toString());
	}
	
	/**
	 * 通过tableName和查询字段  查询TreeNodes数据
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTreeDatas")
	@ResponseBody
	@Action(description="通过tableName和查询字段  查询TreeNodes数据")
	public List<GlobalType> getTreeDatas(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<GlobalType> treeDatas=sysZtreeService.getTreeDatas(getParams(request));
		JSONArray json=new JSONArray();
		json.add(treeDatas); 
		return treeDatas;
	}
	private Map<String,Object> getParams(HttpServletRequest request){
		String treeType=RequestUtil.getString(request,"treeType"); 
		JSONObject json = JSONObject.fromObject(treeType);
		SysZtree sysZtree = (SysZtree)JSONObject.toBean(json, SysZtree.class);
		String id=RequestUtil.getString(request,"typeId");
		String parentId=RequestUtil.getString(request,"parentId");
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("sysZtree", sysZtree);
		params.put("id", id);
		params.put("parentId", parentId);
		return params;
	}
	/**
	 * 设置字段
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		String dsName = "";
		String objectName = "";
		int istable = 0;
		ModelAndView mv = this.getAutoView();
		if (id == 0) {
			dsName = RequestUtil.getString(request, "dsName");
			istable = RequestUtil.getInt(request, "istable");
			objectName = RequestUtil.getString(request, "objectName");
		} else {
			SysZtree sysZtree = sysZtreeService.getById(id);
//			String isTable=sysZtree.getIstable()+""; StringUtil.isNotEmpty(isTable)&&!isTable.equals("null")?
			istable =sysZtree.getIstable().intValue();
			dsName = sysZtree.getDsname();
			objectName = sysZtree.getTablename();
			mv.addObject("sysZtree", sysZtree);
		}

		TableModel tableModel;
		// 表
		if (istable == 1) {
			BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
			tableModel = meta.getTableByName(objectName);
		}
		// 视图处理
		else {
			IDbView dbView = TableMetaFactory.getDbView(dsName);
			tableModel = dbView.getModelByViewName(objectName);
		}
		mv.addObject("tableModel", tableModel);

		return mv;
	}
	
}
