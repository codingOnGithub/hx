package com.hotent.bpmx.core;

import junit.framework.TestCase;

public class BpmxConfigTest extends TestCase {

	public void testGetinstance() {
		BpmxCoreConfig.getInstance();
		BpmxCoreConfig.getInstance();
	}
}
