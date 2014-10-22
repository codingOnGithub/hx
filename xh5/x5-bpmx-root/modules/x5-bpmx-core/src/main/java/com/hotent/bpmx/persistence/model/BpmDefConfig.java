package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.constants.Bool;
import com.hotent.base.core.model.AbstractModel;

/**
 * 对象功能:流程定义配置参数 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2014-01-04 15:35:49
 */
public class BpmDefConfig extends AbstractModel<String> implements Cloneable{
	protected String  id; /*流程定义ID*/
	protected String  informType; /*类型 - 通知类型*/
	protected String  startInfoType; /*类型 - 归档时发送消息通知发起人类型*/
	protected char  skipFirstNode; /*参数 - 启动后是否跳过第一个节点*/
	protected char  chooseFirstAssignee; /*参数 - 是否选择第一个节点的执行人*/
	protected char  skipSameUser; /*参数 - 相邻节点相同执行人跳过*/
	protected char  allowCopyTo; /*参数 - 允许抄送*/
	protected char  allowTransTo; /*参数 - 是否允许转办*/
	protected char  directStart; /*参数 - 允许直接启动*/
	protected char  delDraft; /*参数 - 允许删除草稿*/
	protected char  allowRef; /*参数 - 允许参考*/
	protected Integer  refCount;			/*允许参考的数量*/
	protected char  allowPrintForm; /*参数 - 允许打印表单*/
	public BpmDefConfig(){
		informType = "";
		skipFirstNode = Bool.FALSE.value();
		chooseFirstAssignee = Bool.FALSE.value();
		skipSameUser = Bool.FALSE.value();
		allowCopyTo = Bool.FALSE.value();
		allowTransTo = Bool.FALSE.value();
		directStart = Bool.FALSE.value();
		delDraft = Bool.FALSE.value();
		allowRef = Bool.FALSE.value();
		refCount=0;
		allowPrintForm = Bool.FALSE.value();
	}			
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	public void setInformType(String informType) 
	{
		this.informType = informType;
	}
	/**
	 * 返回 类型 - 通知类型
	 * @return
	 */
	public String getInformType() 
	{
		return this.informType;
	}
	public void setStartInfoType(String startInfoType) 
	{
		this.startInfoType = startInfoType;
	}
	/**
	 * 返回 类型 - 归档时发送消息通知发起人类型
	 * @return
	 */
	public String getStartInfoType() 
	{
		return this.startInfoType;
	}
	public void setSkipFirstNode(char skipFirstNode) 
	{
		this.skipFirstNode = skipFirstNode;
	}
	/**
	 * 返回 参数 - 启动后是否跳过第一个节点
	 * @return
	 */
	public char getSkipFirstNode() 
	{
		return this.skipFirstNode;
	}
	public void setChooseFirstAssignee(char chooseFirstAssignee) 
	{
		this.chooseFirstAssignee = chooseFirstAssignee;
	}
	/**
	 * 返回 参数 - 是否选择第一个节点的执行人
	 * @return
	 */
	public char getChooseFirstAssignee() 
	{
		return this.chooseFirstAssignee;
	}
	public void setSkipSameUser(char skipSameUser) 
	{
		this.skipSameUser = skipSameUser;
	}
	/**
	 * 返回 参数 - 相邻节点相同执行人跳过
	 * @return
	 */
	public char getSkipSameUser() 
	{
		return this.skipSameUser;
	}
	public void setAllowCopyTo(char allowCopyTo) 
	{
		this.allowCopyTo = allowCopyTo;
	}
	/**
	 * 返回 参数 - 允许抄送
	 * @return
	 */
	public char getAllowCopyTo() 
	{
		return this.allowCopyTo;
	}
	public void setAllowTransTo(char allowTransTo) 
	{
		this.allowTransTo = allowTransTo;
	}
	/**
	 * 返回 参数 - 是否允许转办
	 * @return
	 */
	public char getAllowTransTo() 
	{
		return this.allowTransTo;
	}
	public void setDirectStart(char directStart) 
	{
		this.directStart = directStart;
	}
	/**
	 * 返回 参数 - 允许直接启动
	 * @return
	 */
	public char getDirectStart() 
	{
		return this.directStart;
	}
	public void setDelDraft(char delDraft) 
	{
		this.delDraft = delDraft;
	}
	/**
	 * 返回 参数 - 允许删除草稿
	 * @return
	 */
	public char getDelDraft() 
	{
		return this.delDraft;
	}
	public void setAllowRef(char allowRef) 
	{
		this.allowRef = allowRef;
	}
	/**
	 * 返回 参数 - 允许参考
	 * @return
	 */
	public char getAllowRef() 
	{
		return this.allowRef;
	}
	public void setRefCount(Integer refCount) 
	{
		this.refCount = refCount;
	}
	/**
	 * 返回 允许参考的数量
	 * @return
	 */
	public Integer getRefCount() 
	{
		return this.refCount;
	}
	public void setAllowPrintForm(char allowPrintForm) 
	{
		this.allowPrintForm = allowPrintForm;
	}
	/**
	 * 返回 参数 - 允许打印表单
	 * @return
	 */
	public char getAllowPrintForm() 
	{
		return this.allowPrintForm;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("informType", this.informType) 
		.append("startInfoType", this.startInfoType) 
		.append("skipFirstNode", this.skipFirstNode) 
		.append("chooseFirstAssignee", this.chooseFirstAssignee) 
		.append("skipSameUser", this.skipSameUser) 
		.append("allowCopyTo", this.allowCopyTo) 
		.append("allowTransTo", this.allowTransTo) 
		.append("directStart", this.directStart) 
		.append("delDraft", this.delDraft) 
		.append("allowRef", this.allowRef) 
		.append("refCount", this.refCount) 
		.append("allowPrintForm", this.allowPrintForm) 
		.toString();
	}
	public Object clone() {
		BpmDefConfig obj=null;
		try{
			obj=(BpmDefConfig)super.clone();			
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}		
}