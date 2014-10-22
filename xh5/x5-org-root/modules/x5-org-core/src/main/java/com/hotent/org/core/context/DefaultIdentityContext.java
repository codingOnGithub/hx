package com.hotent.org.core.context;

import javax.annotation.Resource;

import com.hotent.org.api.context.IdentityContext;
import com.hotent.org.api.service.AttributeService;
import com.hotent.org.api.service.AttributeValueService;
import com.hotent.org.api.service.DimensionService;
import com.hotent.org.api.service.GroupService;
import com.hotent.org.api.service.UserService;

public class DefaultIdentityContext implements IdentityContext {
	
	/**
	 * 内部数据模式
	 */
	public final static String INTEGRATION_TYPE_DEFAULT="default";
	/**
	 * 外部数据源整合模式
	 */
	public final static String INTEGRATION_TYPE_EXTERNAL="external";
	/**
	 * 同步模式
	 */
	public final static String INTEGRATION_TYPE_SYNC="synchronous";

	public String integrationType=INTEGRATION_TYPE_DEFAULT;
	
	@Resource
	private UserService userService;
	@Resource
	private GroupService groupService;
	@Resource
	private AttributeService attributeService;
	@Resource
	private AttributeValueService attributeValueService;
	@Resource
	private DimensionService dimensionService;
	
	@Override
	public UserService getUserService() {
		return userService;
	}

	@Override
	public GroupService getGroupService() {
		return groupService;
	}

	@Override
	public AttributeService getAttributeService() {
		return attributeService;
	}

	@Override
	public AttributeValueService getAttributeValueService() {
		return attributeValueService;
	}

	@Override
	public DimensionService getDimensionService() {
		return dimensionService;
	}

	public String getIntegrationType() {
		return integrationType;
	}

	public void setIntegrationType(String integrationType) {
		this.integrationType = integrationType;
	}


}
