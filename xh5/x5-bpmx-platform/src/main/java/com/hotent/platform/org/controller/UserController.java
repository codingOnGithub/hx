package com.hotent.platform.org.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.base.api.model.ResultMessage;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.org.api.model.User;
import com.hotent.org.persistence.manager.UserManager;
import com.hotent.org.persistence.model.DefaultUser;
import com.hotent.web.controller.GenericController;
import com.hotent.web.json.PageJson;
import com.hotent.web.util.RequestUtil;

/**
 * 
 * <pre> 
 * 描述：用户管理
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-10-下午3:29:34
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@Controller
@RequestMapping("/platform/org/user/")
public class UserController extends GenericController{
	@Resource
	UserManager userManager;
	
	@Resource
	IdGenerator idGenerator;
	
	@RequestMapping("listJson")
	public @ResponseBody PageJson listJson(HttpServletRequest request,HttpServletResponse reponse) throws Exception{

		QueryFilter queryFilter=getQuerFilter(request);
		
		PageList<DefaultUser> userList=(PageList<DefaultUser>)userManager.query(queryFilter);
		return new PageJson(userList);
	}
	
	@RequestMapping("edit")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String userId=RequestUtil.getString(request, "userId");
		DefaultUser user=null;
		if(StringUtil.isNotEmpty(userId)){
			user=userManager.get(userId);
		}
		return getAutoView().addObject("user", user);
	}
	
	@RequestMapping("save")
	public void save(HttpServletRequest request,HttpServletResponse response,DefaultUser user) throws Exception{
		String resultMsg=null;
		String userId=user.getUserId();
		try {
			if(StringUtil.isEmpty(userId)){
				user.setId(idGenerator.getSuid());
				user.setStatus(User.Status.actived);
				user.setFrom("system");
				userManager.create(user);
				resultMsg=getText("添加",getText("系统用户"));
			}else{
				userManager.update(user);
				resultMsg=getText("更新", getText("系统用户"));
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.SUCCESS);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.FAIL);
		}
	}
	
	@RequestMapping("remove")
	public void remove(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try {
			String[] aryIds=RequestUtil.getStringAryByStr(request, "userId");
			userManager.removeByIds(aryIds);
			message=new ResultMessage(ResultMessage.SUCCESS, "删除用户成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.FAIL, "删除用户失败");
		}
		writeResultMessage(response.getWriter(), message);
	}
}
