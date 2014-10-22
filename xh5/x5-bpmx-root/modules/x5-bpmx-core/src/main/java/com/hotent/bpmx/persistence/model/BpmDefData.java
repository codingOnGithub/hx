package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.core.model.AbstractModel;
import com.hotent.bpmx.api.constant.DesignerType;

/**
 * 对象功能:@名称：BPM_DEF_DATA 【流程定义大数据值】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-13 16:17:48
 */
public class BpmDefData extends AbstractModel<String> implements Cloneable{
	protected String  id; /*流程定义ID*/
	protected DesignerType designer;
	
	protected String  defXml; /*流程定义XML*/
	protected String  bpmnXml; /*流程定义BPMN格式XML*/
	public BpmDefData(){
		defXml = "";
		bpmnXml = "";
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getId() 
	{
		return this.id;
	}
	
	public DesignerType getDesigner() {
		return designer;
	}
	public void setDesigner(DesignerType designer) {
		this.designer = designer;
	}
	
	
	public void setDefXml(String defXml) 
	{
		this.defXml = defXml;
	}
	/**
	 * 返回 流程定义XML
	 * @return
	 */
	public String getDefXml() 
	{
		return this.defXml;
	}
	public void setBpmnXml(String bpmnXml) 
	{
		this.bpmnXml = bpmnXml;
	}
	/**
	 * 返回 流程定义BPMN格式XML
	 * @return
	 */
	public String getBpmnXml() 
	{
		return this.bpmnXml;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("defXml", this.defXml) 
		.append("bpmnXml", this.bpmnXml) 
		.toString();
	}
	public Object clone() {
		BpmDefData obj=null;
		try{
			obj=(BpmDefData)super.clone();			
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}			
}