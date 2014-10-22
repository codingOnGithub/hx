package com.hotent.bpmx.persistence.util;

import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.core.defxml.entity.ScriptTask;
import com.hotent.bpmx.core.defxml.entity.SendTask;
import com.hotent.bpmx.core.defxml.entity.ServiceTask;
import com.hotent.bpmx.core.defxml.entity.Task;
import com.hotent.bpmx.core.defxml.entity.UserTask;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;

public class BpmNodeConfigUtil {
	public static DefaultBpmNodeConfig buildDefaultBpmNodeConfig(Task task,String procDefId,Integer sn){
		DefaultBpmNodeConfig bpmNodeConfig = new DefaultBpmNodeConfig();
		bpmNodeConfig.setNodeId(task.getId());
		bpmNodeConfig.setNodeName(task.getName());
		bpmNodeConfig.setProcDefId(procDefId);
		bpmNodeConfig.setSn(sn);
		if(task instanceof UserTask){
			bpmNodeConfig.setNodeType(BpmNodeConfig.NODE_TYPE.USER);			
		}else if(task instanceof ScriptTask){
			bpmNodeConfig.setNodeType(BpmNodeConfig.NODE_TYPE.SCRIPT);
		}else if(task instanceof SendTask){
			bpmNodeConfig.setNodeType(BpmNodeConfig.NODE_TYPE.MSG);
		}else if(task instanceof ServiceTask){
			bpmNodeConfig.setNodeType(BpmNodeConfig.NODE_TYPE.WEB_SERVICE);
		}
		return bpmNodeConfig;
	}
}
