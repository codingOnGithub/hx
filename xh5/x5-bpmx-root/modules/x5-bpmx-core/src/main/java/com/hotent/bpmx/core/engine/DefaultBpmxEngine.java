package com.hotent.bpmx.core.engine;

import javax.annotation.Resource;
import com.hotent.bpmx.api.engine.BpmxEngine;
import com.hotent.bpmx.api.service.BpmDefinitionService;
import com.hotent.bpmx.api.service.BpmTaskService;

//@Service
public class DefaultBpmxEngine implements BpmxEngine {

	@Resource
	private BpmTaskService bpmTaskService;

	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	
	@Override
	public String getName() {
		return "x5";
	}

	@Override
	public BpmTaskService getBpmTaskService() {
		return bpmTaskService;
	}

	@Override
	public BpmDefinitionService getBpmDefinitionService() {
		return bpmDefinitionService;
	}
	
	

}
