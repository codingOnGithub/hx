package com.hotent.bpmx.api.engine;


public interface BpmxEngineFactory {
	
	public BpmxEngine getEngine();

	public BpmxEngine getEngine(String engineName);
}
