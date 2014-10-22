package com.hotent.bpmx.core.context;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import com.hotent.base.core.assertion.AssertValue;
import com.hotent.bpmx.api.engine.BpmxEngineFactory;
import com.hotent.bpmx.api.engine.BpmxEngine;
import com.hotent.bpmx.api.log.BpmLogService;

//@Service
public class DefaultBpmxContext implements BpmxEngineFactory {

	@Resource
	private List<BpmxEngine> bpmxEngines;
		
	private String defaultEngineName="x5";

	private static final Logger logger = Logger.getLogger(DefaultBpmxContext.class);

	@Override
	public BpmxEngine getEngine() {
		return this.getEngine(defaultEngineName);
	}

	@Override
	public BpmxEngine getEngine(String engineName) {
		logger.debug("获取BPMX引擎，名称为：" + engineName);

		if (AssertValue.isNotNullAndNotEmpty(bpmxEngines)) {
			for (BpmxEngine bpmxEngine : this.bpmxEngines) {
				logger.debug("找到引擎：" + bpmxEngine.getName());
				if (bpmxEngine.getName().equals(engineName)) {
					return bpmxEngine;
				}
			}
		}

		logger.warn("无法找到名称为：" + engineName + "的引擎。");

		return null;
	}
	
	public void setDefaultEngineName(String defaultEngineName) {
		this.defaultEngineName = defaultEngineName;
	}

	public void setBpmxEngines(List<BpmxEngine> bpmxEngines) {
		this.bpmxEngines = bpmxEngines;
	}
	
	
	
}
