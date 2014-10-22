package com.hotent.bpmx.persistence.model;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.base.api.TreeType;
import com.hotent.base.core.constants.Bool;
import com.hotent.base.core.model.AbstractModel;
import com.hotent.bpmx.api.constant.DesignerType;
import com.hotent.bpmx.api.model.process.def.BpmDefinition;

/**
 * 对象功能:@名称：BPM_DEFINITION 【流程定义】 entity对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2013-12-12 18:02:04
 */
public class DefaultBpmDefinition extends AbstractModel<String> implements BpmDefinition,Cloneable{
	protected String  defId; /*流程定义ID*/
	protected String  parentDefId; /*所属主流程定义ID*/
	protected String  name; /*流程名称*/
	protected String  instSubjectRule; /*流程实例标题规则*/
	protected String  defKey; /*流程业务主键*/
	protected String  desc; /*流程描述*/
	protected String  typeId; /*所属分类ID*/
	protected String  status; /*流程状态。草稿、发布、禁用*/
	protected String  testStatus; /*测试状态*/
	protected String  informType; /*通知类型*/
	protected String  bpmnDefId; /*BPMN - 流程定义ID*/
	protected String  bpmnDeployId; /*BPMN - 流程发布ID*/
	protected Integer  version;			/*版本 - 当前版本号*/
	protected String  mainDefId; /*版本 - 主版本流程ID*/
	protected char  isMain; /*版本 - 是否主版本*/
	protected String  reason; /*版本 - 变更理由*/
	protected String  helpId; /*其它 - 流程帮助附件ID*/
	protected String  createBy; /*创建人ID*/
	protected java.util.Date  createTime; /*创建时间*/
	protected String  createOrgId; /*创建者所属组织ID*/
	protected String  updateBy; /*更新人ID*/
	protected java.util.Date  updateTime; /*更新时间*/
	
	protected BpmDefConfig bpmDefConfig = new BpmDefConfig();
	protected BpmDefData bpmDefData = new BpmDefData(); 
	
	@Override
	public void setId(String id) {
		setDefId(id);
	}
	@Override
	public String getId() {
		return defId;
	}	
	public void setDefId(String defId) 
	{
		this.defId = defId;
		bpmDefConfig.setId(defId);
		bpmDefData.setId(defId);		
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getDefId() 
	{
		return this.defId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 流程名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setInstSubjectRule(String instSubjectRule) 
	{
		this.instSubjectRule = instSubjectRule;
	}
	/**
	 * 返回 流程实例标题规则
	 * @return
	 */
	public String getInstSubjectRule() 
	{
		return this.instSubjectRule;
	}
	public void setDefKey(String defKey) 
	{
		this.defKey = defKey;
	}
	/**
	 * 返回 流程业务主键
	 * @return
	 */
	public String getDefKey() 
	{
		return this.defKey;
	}
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	/**
	 * 返回 流程描述
	 * @return
	 */
	public String getDesc() 
	{
		return this.desc;
	}
	public void setTypeId(String typeId) 
	{
		this.typeId = typeId;
	}
	/**
	 * 返回 所属分类ID
	 * @return
	 */
	public String getTypeId() 
	{
		return this.typeId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}
	/**
	 * 返回 流程状态。草稿、发布、禁用
	 * @return
	 */
	public String getStatus() 
	{
		return this.status;
	}
	public void setTestStatus(String testStatus) 
	{
		this.testStatus = testStatus;
	}
	/**
	 * 返回 测试状态
	 * @return
	 */
	public String getTestStatus() 
	{
		return this.testStatus;
	}
	public void setInformType(String informType) 
	{
		this.informType = informType;
	}
	/**
	 * 返回 通知类型
	 * @return
	 */
	public String getInformType() 
	{
		return this.informType;
	}
	public void setBpmnDefId(String bpmnDefId) 
	{
		this.bpmnDefId = bpmnDefId;
	}
	/**
	 * 返回 BPMN - 流程定义ID
	 * @return
	 */
	public String getBpmnDefId() 
	{
		return this.bpmnDefId;
	}
	public void setBpmnDeployId(String bpmnDeployId) 
	{
		this.bpmnDeployId = bpmnDeployId;
	}
	/**
	 * 返回 BPMN - 流程发布ID
	 * @return
	 */
	public String getBpmnDeployId() 
	{
		return this.bpmnDeployId;
	}
	public void setVersion(Integer version) 
	{
		this.version = version;
	}
	/**
	 * 返回 版本 - 当前版本号
	 * @return
	 */
	public Integer getVersion() 
	{
		return this.version;
	}
	public void setMainDefId(String mainDefId) 
	{
		this.mainDefId = mainDefId;
	}
	/**
	 * 返回 版本 - 主版本流程ID
	 * @return
	 */
	public String getMainDefId() 
	{
		return this.mainDefId;
	}
	public void setIsMain(char isMain) 
	{
		this.isMain = isMain;
	}
	/**
	 * 返回 版本 - 是否主版本
	 * @return
	 */
	public char getIsMain() 
	{
		return this.isMain;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}
	/**
	 * 返回 版本 - 变更理由
	 * @return
	 */
	public String getReason() 
	{
		return this.reason;
	}
	public void setHelpId(String helpId) 
	{
		this.helpId = helpId;
	}
	/**
	 * 返回 其它 - 流程帮助附件ID
	 * @return
	 */
	public String getHelpId() 
	{
		return this.helpId;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}
	/**
	 * 返回 创建人ID
	 * @return
	 */
	public String getCreateBy() 
	{
		return this.createBy;
	}
	public void setCreateTime(java.util.Date createTime) 
	{
		this.createTime = createTime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreateTime() 
	{
		return this.createTime;
	}
	public void setCreateOrgId(String createOrgId) 
	{
		this.createOrgId = createOrgId;
	}
	/**
	 * 返回 创建者所属组织ID
	 * @return
	 */
	public String getCreateOrgId() 
	{
		return this.createOrgId;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}
	/**
	 * 返回 更新人ID
	 * @return
	 */
	public String getUpdateBy() 
	{
		return this.updateBy;
	}
	public void setUpdateTime(java.util.Date updateTime) 
	{
		this.updateTime = updateTime;
	}
	/**
	 * 返回 更新时间
	 * @return
	 */
	public java.util.Date getUpdateTime() 
	{
		return this.updateTime;
	}
	
	@Override
	public TreeType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setType(TreeType _treeType) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 设计器。
	 * @param designer 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void setDesigner(String designer){
		this.bpmDefData.designer=DesignerType.valueOf(designer);
	}
	
	/**
	 * 设计器。
	 * @param designer
	 * @return  String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDesigner(){
		return this.bpmDefData.designer.toString();
	}
	
	@Override
	public String getDefXml() {
		return bpmDefData.defXml;
	}
	public void setDefXml(String defXml){
		bpmDefData.defXml = defXml;
	}
	@Override
	public String getBpmnXml() {
		return bpmDefData.bpmnXml;
	}
	public void setBpmnXml(String bpmnXml){
		bpmDefData.bpmnXml = bpmnXml;
	}
	@Override
	public boolean isMain() {		
		return isMain==Bool.TRUE.value()?true:false;
	}
	@Override
	public void setMain(boolean _isMain) {
		if(_isMain){
			isMain=Bool.TRUE.value();
		}else{
			isMain=Bool.FALSE.value();
		}
	}	
	@Override
	public boolean isSkipFirstNode() {
		return bpmDefConfig.getSkipFirstNode()==Bool.TRUE.value()?true:false;
	}
	@Override
	public boolean isChooseFirstAssignee() {
		return bpmDefConfig.getChooseFirstAssignee()==Bool.TRUE.value()?true:false;
	}
	@Override
	public void setIsSkipFirstNode(boolean isSkipFirstNode) {
		bpmDefConfig.setSkipFirstNode(isSkipFirstNode?Bool.TRUE.value():Bool.FALSE.value());
	}
	@Override
	public void setIsChooseFirstAssignee(boolean isChooseFirstAssignee) {
		bpmDefConfig.setChooseFirstAssignee(isChooseFirstAssignee?Bool.TRUE.value():Bool.FALSE.value());
	}
	
	public BpmDefConfig getBpmDefConfig() {
		return bpmDefConfig;
	}
	public void setBpmDefConfig(BpmDefConfig bpmDefConfig) {
		this.bpmDefConfig = bpmDefConfig;
	}
	public BpmDefData getBpmDefData() {
		return bpmDefData;
	}
	public void setBpmDefData(BpmDefData bpmDefData) {
		this.bpmDefData = bpmDefData;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("defId", this.defId) 
		.append("parentDefId", this.parentDefId) 
		.append("name", this.name) 
		.append("instSubjectRule", this.instSubjectRule) 
		.append("defKey", this.defKey) 
		.append("desc", this.desc) 
		.append("typeId", this.typeId) 
		.append("status", this.status) 
		.append("testStatus", this.testStatus) 
		.append("informType", this.informType) 
		.append("bpmnDefId", this.bpmnDefId) 
		.append("bpmnDeployId", this.bpmnDeployId) 
		.append("version", this.version) 
		.append("mainDefId", this.mainDefId) 
		.append("isMain", this.isMain) 
		.append("reason", this.reason) 
		.append("helpId", this.helpId) 
		.append("createBy", this.createBy) 
		.append("createTime", this.createTime) 
		.append("createOrgId", this.createOrgId) 
		.append("updateBy", this.updateBy) 
		.append("updateTime", this.updateTime) 
		.toString();
	}

	public Object clone() {
		DefaultBpmDefinition obj=null;
		try{
			obj=(DefaultBpmDefinition)super.clone();
			obj.setBpmDefConfig((BpmDefConfig)bpmDefConfig.clone());
			obj.setBpmDefData((BpmDefData)bpmDefData.clone());
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}		
}