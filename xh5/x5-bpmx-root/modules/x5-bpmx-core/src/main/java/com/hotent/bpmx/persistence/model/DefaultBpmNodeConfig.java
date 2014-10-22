package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.constants.Bool;
import com.hotent.base.core.model.AbstractModel;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;

/**
 * 对象功能:@名称：BPM_NODE_CONFIG 【节点基本配置】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-12 16:57:33
 */
public class DefaultBpmNodeConfig extends AbstractModel<String> implements BpmNodeConfig,Cloneable{
	protected String  configId; /*节点配置ID*/
	protected String  nodeId; /*节点ID*/
	protected String  nodeName; /*节点名称（快照）*/
	protected String  procDefId; /*流程定义ID*/
	protected String  nodeType; /*节点类型。user：用户节点；msg：消息节点；script：脚本节点；ws：WEBSERVICE节点。*/
	protected Integer  sn;			/*排序号*/
	protected String  memo; /*配置备注*/
	protected char  isSettingSub; /*is_setting_sub_*/
	
	public DefaultBpmNodeConfig(){
		isSettingSub=Bool.FALSE.value();
	}
	
	@Override
	public void setId(String id) {
		this.configId = id;
	}
	@Override
	public String getId() {
		return configId;
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
	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 节点ID
	 * @return
	 */
	public String getNodeId() 
	{
		return this.nodeId;
	}
	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}
	/**
	 * 返回 节点名称（快照）
	 * @return
	 */
	public String getNodeName() 
	{
		return this.nodeName;
	}
	public void setProcDefId(String procDefId) 
	{
		this.procDefId = procDefId;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getProcDefId() 
	{
		return this.procDefId;
	}
	public void setNodeType(String nodeType) 
	{
		this.nodeType = nodeType;
	}
	/**
	 * 返回 节点类型。user：用户节点；msg：消息节点；script：脚本节点；ws：WEBSERVICE节点。
	 * @return
	 */
	public String getNodeType() 
	{
		return this.nodeType;
	}
	public void setSn(Integer sn) 
	{
		this.sn = sn;
	}
	/**
	 * 返回 排序号
	 * @return
	 */
	public Integer getSn() 
	{
		return this.sn;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 配置备注
	 * @return
	 */
	public String getMemo() 
	{
		return this.memo;
	}
	public void setIsSettingSub(char isSettingSub) 
	{
		this.isSettingSub = isSettingSub;
	}
	/**
	 * 返回 is_setting_sub_
	 * @return
	 */
	public char getIsSettingSub() 
	{
		return this.isSettingSub;
	}	
	public boolean isSettingSub(){
		return isSettingSub==Bool.TRUE.value()?true:false;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("configId", this.configId) 
		.append("nodeId", this.nodeId) 
		.append("nodeName", this.nodeName) 
		.append("procDefId", this.procDefId) 
		.append("nodeType", this.nodeType) 
		.append("sn", this.sn) 
		.append("memo", this.memo) 
		.toString();
	}

	public Object clone(){
		DefaultBpmNodeConfig nodeConfig = null;
		try{
			nodeConfig = (DefaultBpmNodeConfig)super.clone();
		}catch(Exception e){			
		}
		return nodeConfig;
		
	}

}