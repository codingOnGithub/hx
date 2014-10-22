package com.hotent.bpmx.core.engine;

import junit.framework.TestCase;

import com.hotent.bpmx.api.engine.BpmxEngineFactory;
import com.hotent.bpmx.api.engine.BpmxEngine;
import com.hotent.bpmx.api.service.BpmDefinitionService;
import com.hotent.bpmx.api.service.BpmTaskService;
import com.hotent.bpmx.core.BpmxCoreConfig;

public class DefaultBpmxEngineTest extends TestCase {
	private BpmxEngineFactory bpmxContext;
	private BpmxEngine bpmxEngine;

	public void testGetService() {

		BpmDefinitionService bpmDefinitionService = bpmxEngine.getBpmDefinitionService();

		assertNotNull(bpmDefinitionService);

		BpmTaskService bpmTaskService = bpmxEngine.getBpmTaskService();

		assertNotNull(bpmTaskService);

	}

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		bpmxContext = BpmxCoreConfig.getInstance().getBpmxContext();

		bpmxEngine = bpmxContext.getEngine();
	}
}
