package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.constants.Bool;
import com.hotent.bpmx.api.model.process.nodedef.BpmUserNode;

/**
 * 
 * <pre> 
 * 描述：bpm_user_node entity对象
 * 构建组：x5-bpmx-core
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2013-12-16-下午10:47:31
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class DefaultBpmUserNode extends DefaultBpmNodeConfig implements BpmUserNode,Cloneable{
	protected String  id; /*节点配置ID*/
	protected String  bizType; /*业务类型。normal 普通用户节点；fork 分发任务节点；join 汇总任务节点；*/
	protected char  isAllowBack; /*参数 - 是否允许回退*/
	protected char  isAllowBackToStart; /*参数 - 是否允许回退至发起人*/
	protected char  isHideOpinionField; /*参数 - 是否隐藏意见输入框*/
	protected char  isHideExecPath; /*参数 - 是否隐藏执行路径*/
	
	public DefaultBpmUserNode(){
		super();
		isAllowBack = Bool.FALSE.value();
		isAllowBackToStart = Bool.FALSE.value();
		isHideOpinionField = Bool.FALSE.value();
		isHideExecPath = Bool.FALSE.value();
	}
	
	public void setId(String id) 
	{
		this.id = id;
		setConfigId(id);
	}
	/**
	 * 返回 节点配置ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setBizType(String bizType) 
	{
		this.bizType = bizType;
	}
	/**
	 * 返回 业务类型。normal 普通用户节点；fork 分发任务节点；join 汇总任务节点；
	 * @return
	 */
	public String getBizType() 
	{
		return this.bizType;
	}
	public void setIsAllowBack(char isAllowBack) 
	{
		this.isAllowBack = isAllowBack;
	}
	/**
	 * 返回 参数 - 是否允许回退
	 * @return
	 */
	public char getIsAllowBack() 
	{
		return this.isAllowBack;
	}
	public void setIsAllowBackToStart(char isAllowBackToStart) 
	{
		this.isAllowBackToStart = isAllowBackToStart;
	}
	/**
	 * 返回 参数 - 是否允许回退至发起人
	 * @return
	 */
	public char getIsAllowBackToStart() 
	{
		return this.isAllowBackToStart;
	}
	public void setIsHideOpinionField(char isHideOpinionField) 
	{
		this.isHideOpinionField = isHideOpinionField;
	}
	/**
	 * 返回 参数 - 是否隐藏意见输入框
	 * @return
	 */
	public char getIsHideOpinionField() 
	{
		return this.isHideOpinionField;
	}
	public void setIsHideExecPath(char isHideExecPath) 
	{
		this.isHideExecPath = isHideExecPath;
	}
	/**
	 * 返回 参数 - 是否隐藏执行路径
	 * @return
	 */
	public char getIsHideExecPath() 
	{
		return this.isHideExecPath;
	}
	
	/* (non-Javadoc)
	 * @see com.hotent.bpmx.api.model.BpmUserNode#isAllowBack()
	 */
	@Override
	public boolean isAllowBack() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.api.model.BpmUserNode#isAllowBackToStart()
	 */
	@Override
	public boolean isAllowBackToStart() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.api.model.BpmUserNode#isHideOpinionField()
	 */
	@Override
	public boolean isHideOpinionField() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.hotent.bpmx.api.model.BpmUserNode#isHideExecPath()
	 */
	@Override
	public boolean isHideExecPath() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		String toString =new ToStringBuilder(this)
		.append("id", this.id) 
		.append("bizType", this.bizType) 
		.append("isAllowBack", this.isAllowBack) 
		.append("isAllowBackToStart", this.isAllowBackToStart) 
		.append("isHideOpinionField", this.isHideOpinionField) 
		.append("isHideExecPath", this.isHideExecPath)		
		.toString();
		return toString + super.toString();
	}

	public Object clone(){
		DefaultBpmUserNode userNode = null;
		try {
			userNode = (DefaultBpmUserNode)super.clone();
		} catch (Exception e) {

		}
		return userNode;
	}
	
	
}