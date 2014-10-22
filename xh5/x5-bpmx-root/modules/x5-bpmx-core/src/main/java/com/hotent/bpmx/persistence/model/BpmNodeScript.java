package com.hotent.bpmx.persistence.model;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:bpm_node_script entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-19 16:11:20
 */
public class BpmNodeScript extends AbstractModel<String> implements Cloneable{
	protected String  id; /*脚本节点配置ID*/
	protected String  configId; /*节点配置ID*/
	protected String  script; /*脚本*/
	protected String  scriptType; /*脚本类型。before.事件前置脚本；after.事件后置脚本；script 脚本节点；assign 任务分配脚本*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 脚本节点配置ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setConfigId(String configId) 
	{
		this.configId = configId;
	}
	/**
	 * 返回 节点配置ID
	 * @return
	 */
	public String getConfigId() 
	{
		return this.configId;
	}
	public void setScript(String script) 
	{
		this.script = script;
	}
	/**
	 * 返回 脚本
	 * @return
	 */
	public String getScript() 
	{
		return this.script;
	}
	public void setScriptType(String scriptType) 
	{
		this.scriptType = scriptType;
	}
	/**
	 * 返回 脚本类型。before.事件前置脚本；after.事件后置脚本；script 脚本节点；assign 任务分配脚本
	 * @return
	 */
	public String getScriptType() 
	{
		return this.scriptType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("configId", this.configId) 
		.append("script", this.script) 
		.append("scriptType", this.scriptType) 
		.toString();
	}
	
	public BpmNodeScript clone(){
		BpmNodeScript bpmNodeScript = null;
		try{
			bpmNodeScript = (BpmNodeScript)super.clone();
		}catch(Exception e){
			
		}
		return bpmNodeScript;
	}
}