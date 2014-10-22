package com.hotent.bpmx.extension.org.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.hotent.bpmx.api.model.identity.BpmIdentity;
import com.hotent.bpmx.api.plugin.org.def.BpmIdentityLinkDef;
import com.hotent.bpmx.api.plugin.org.handler.BpmIdentityLinkHandler;
import com.hotent.bpmx.extension.org.def.StartUserLinkDef;
import com.hotent.bpmx.extension.org.model.DefaultBpmIdentity;
import com.hotent.org.api.model.User;
import com.hotent.org.api.service.UserService;

/**
 * 
 * <pre> 
 * 描述：当用户为发起人，获取返回其用户实体
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-23-下午9:14:17
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class StartUserCalHandler implements BpmIdentityLinkHandler{
	@Resource
	UserService userService;
	
	@Override
	public List<BpmIdentity> getBpmIdentites(BpmIdentityLinkDef linkDef) {
		StartUserLinkDef startUserLinkDef=(StartUserLinkDef)linkDef;
		User user=userService.getByKey(startUserLinkDef.getStartUserId());
		List<BpmIdentity> list=new ArrayList<BpmIdentity>();
		list.add(new DefaultBpmIdentity(user));
		return list; 
	}

}
