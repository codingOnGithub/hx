package com.hotent.bpmx.core.engine.task.plugins;

import com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin;
import com.hotent.bpmx.api.plugin.process.session.BpmTaskPluginSession;
import com.hotent.bpmx.core.engine.task.plugins.def.AssignUserPluginDef;

/**
 * 用户分配
 * 
 * @author ray
 * 
 */
public class AssignUserPlugin implements BpmTaskPlugin {

	protected AssignUserPluginDef def;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.bpmx.api.plugin.process.runtime.BpmTaskPlugin#execute(com.
	 *      hotent.bpmx.api.plugin.process.session.BpmTaskPluginSession)
	 */
	@Override
	public void execute(BpmTaskPluginSession bpmTaskPluginSession) {

	}

	public static AssignUserPlugin newInstance(AssignUserPluginDef def) {

		AssignUserPlugin plugin = new AssignUserPlugin();
		plugin.setDef(def);
		return plugin;
	}

	public AssignUserPluginDef getDef() {
		return def;
	}

	public void setDef(AssignUserPluginDef def) {
		this.def = def;
	}

}
