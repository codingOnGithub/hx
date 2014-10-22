package com.hotent.bpmx.core.context;

import junit.framework.TestCase;

import com.hotent.bpmx.api.engine.BpmxEngineFactory;
import com.hotent.bpmx.api.engine.BpmxEngine;
import com.hotent.bpmx.core.BpmxCoreConfig;

public class DefaultBpmxContextTest extends TestCase {

	private BpmxEngineFactory bpmxContext;

	public void testGetEngine() {

		BpmxEngine bpmxEngine = bpmxContext.getEngine();

		assertNotNull(bpmxEngine);

	}

	@Override
	protected void setUp() throws Exception {

		super.setUp();
		bpmxContext = BpmxCoreConfig.getInstance().getBpmxContext();
	}
}
