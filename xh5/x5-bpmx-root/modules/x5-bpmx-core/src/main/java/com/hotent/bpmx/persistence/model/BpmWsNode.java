package com.hotent.bpmx.persistence.model;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 对象功能:WebService节点配置 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-31 15:53:44
 */
public class BpmWsNode extends DefaultBpmNodeConfig implements Cloneable{
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since 1.0.0
	 */
	
	private static final long serialVersionUID = 1L;
	
	protected String  id; /*WS节点配置ID*/
	protected String  wsConfigs; /*配置JSON数据*/
	protected List<BpmWsNodeParams> bpmWsNodeParamsList=new ArrayList<BpmWsNodeParams>(); /*流程webservice节点参数列表*/
	public BpmWsNode(){
		super();
		wsConfigs = "";
	}
	public void setId(String id) 
	{
		this.id = id;
		setConfigId(id);
	}
	/**
	 * 返回 WS节点配置ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setWsConfigs(String wsConfigs) 
	{
		this.wsConfigs = wsConfigs;
	}
	/**
	 * 返回 配置JSON数据
	 * @return
	 */
	public String getWsConfigs() 
	{
		return this.wsConfigs;
	}
	public void setBpmWsNodeParamsList(List<BpmWsNodeParams> bpmWsNodeParamsList) 
	{
		this.bpmWsNodeParamsList = bpmWsNodeParamsList;
	}
	/**
	 * 返回 流程webservice节点参数列表
	 * @return
	 */
	public List<BpmWsNodeParams> getBpmWsNodeParamsList() 
	{
		return this.bpmWsNodeParamsList;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("wsConfigs", this.wsConfigs) 
		.toString();
	}
	
	public Object clone(){
		BpmWsNode wsNode = null;
		try {
			wsNode = (BpmWsNode)super.clone();
			for(BpmWsNodeParams params:bpmWsNodeParamsList){
				wsNode.getBpmWsNodeParamsList().add((BpmWsNodeParams)params.clone());
			}
		} catch (Exception e) {

		}
		return wsNode;
	}
}