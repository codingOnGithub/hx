package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:流程webservice节点参数 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-31 16:22:39
 */
public class BpmWsNodeParams extends AbstractModel<String> implements Cloneable{
	protected String  id; /*节点参数ID*/
	protected String  configId; /*WS节点配置ID*/
	protected String  paramType; /*参数类型【in：表示输入参数，out：输出参数】*/
	protected String  varId; /*变量id*/
	protected String  wsName; /*WS变量名称*/
	protected String  dataType; /*变量类型【变量类型，string long date,等】*/
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 节点参数ID
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
	 * 返回 WS节点配置ID
	 * @return
	 */
	public String getConfigId() 
	{
		return this.configId;
	}
	public void setParamType(String paramType) 
	{
		this.paramType = paramType;
	}
	/**
	 * 返回 参数类型【in：表示输入参数，out：输出参数】
	 * @return
	 */
	public String getParamType() 
	{
		return this.paramType;
	}
	public void setVarId(String varId) 
	{
		this.varId = varId;
	}
	/**
	 * 返回 变量id
	 * @return
	 */
	public String getVarId() 
	{
		return this.varId;
	}
	public void setWsName(String wsName) 
	{
		this.wsName = wsName;
	}
	/**
	 * 返回 WS变量名称
	 * @return
	 */
	public String getWsName() 
	{
		return this.wsName;
	}
	public void setDataType(String dataType) 
	{
		this.dataType = dataType;
	}
	/**
	 * 返回 变量类型【变量类型，string long date,等】
	 * @return
	 */
	public String getDataType() 
	{
		return this.dataType;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("configId", this.configId) 
		.append("paramType", this.paramType) 
		.append("varId", this.varId) 
		.append("wsName", this.wsName) 
		.append("dataType", this.dataType) 
		.toString();
	}
	public Object clone(){
		BpmWsNodeParams wsNodeParams = null;
		try {
			wsNodeParams = (BpmWsNodeParams)super.clone();
		} catch (Exception e) {

		}
		return wsNodeParams;
	}
}