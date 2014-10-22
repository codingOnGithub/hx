package com.hotent.bpmx.plugin.process.runtime;

import com.hotent.bpmx.api.constant.EventType;
import com.hotent.bpmx.api.plugin.process.def.BpmPluginDef;
import com.hotent.bpmx.api.plugin.process.parse.BpmPluginParser;

/**
 * 
 * <pre> 
 * 描述：抽象插件运行实例
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-19-下午3:38:37
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public abstract class AbstractBpmPlugin implements BpmPluginParser{
	
	protected EventType eventType;
	
	protected BpmPluginDef bpmPluginDef;
	
	public BpmPluginDef getBpmPluginDef() {
		return bpmPluginDef;
	}

	public void setBpmPluginDef(BpmPluginDef bpmPluginDef) {
		this.bpmPluginDef = bpmPluginDef;
	}
	
	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	
}
