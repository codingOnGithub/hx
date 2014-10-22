package com.hotent.bpmx.api.cmd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.bpmx.api.model.identity.BpmIdentity;



/**
 * 流程启动或者下一步对象参数封装对象。
 * <pre> 
 * 描述： 流程启动或者下一步对象参数封装对象。
 * 构建组：x5-bpmx-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-1-20-上午8:46:11
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class ProcessCmd {
	/**
	 * 流程定义ID
	 */
	private String actDefId="";
	/**
	 * 流程定义KEY。
	 */
	private String flowKey="";
	
	/**
	 * 任务ID。
	 */
	private String taskId="";
	
	/**
	 * 目标任务节点ID。
	 */
	private String destinationNodeId="";
	/**
	 * 流程实例标题。
	 */
	private String subject="";
	
	/**
	 * 是否启动流程。
	 */
	private boolean isStart=false;
	
	/**
	 * 是否管理员。
	 */
	private boolean isAdmin=false;
	
	
	
	/**
	 * 驳回。
	 * <pre>
	 * 0，正常跳转。
	 * 1，驳回
	 * 2，驳回到发起人。
	 * 则表示回退
	 * </pre>
	 */
	private Integer  isBack=0;
	/**
	 * 追回任务
	 */
	private boolean isRecover=false;
	
	
	
	/**
	 * 投票状态。
	 * <pre>
	 * 0=弃权， 1=同意
	 * 2=反对， 3=驳回
	 * 4=追回
	 * 5=会签通过
	 * 6=会签不通过
	 * </pre>
	 */
	private Short voteAgree=1;
	
	/**
	 * 投票意见
	 */
	private String voteContent="";
	/**
	 * 投票控件名称
	 */
	private String voteFieldName="";
	
	
	
	/**
	 * 业务主键。
	 */
	private String businessKey="";
	
	/**
	 * 表单数据。
	 * 数据格式为JSON。
	 */
	private String formData="";
	
	/**
	 * 表单map对象。
	 */
	private Map<String,Object> formDataMap=new HashMap<String,Object>();
	
	/**
	 * 目标节点和执行人。
	 */
	private Map<String, List<BpmIdentity>> destNodeExecutor=new HashMap<String, List<BpmIdentity>>();
	
	
	/**
	 * 通知类型。
	 */
	private String infoFormType="";


	public String getActDefId() {
		return actDefId;
	}


	public void setActDefId(String actDefId) {
		this.actDefId = actDefId;
	}


	public String getFlowKey() {
		return flowKey;
	}


	public void setFlowKey(String flowKey) {
		this.flowKey = flowKey;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getDestinationNodeId() {
		return destinationNodeId;
	}


	public void setDestinationNodeId(String destinationNodeId) {
		this.destinationNodeId = destinationNodeId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public boolean isStart() {
		return isStart;
	}


	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public Integer getIsBack() {
		return isBack;
	}


	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
	}


	public boolean isRecover() {
		return isRecover;
	}


	public void setRecover(boolean isRecover) {
		this.isRecover = isRecover;
	}


	public Short getVoteAgree() {
		return voteAgree;
	}


	public void setVoteAgree(Short voteAgree) {
		this.voteAgree = voteAgree;
	}


	public String getVoteContent() {
		return voteContent;
	}


	public void setVoteContent(String voteContent) {
		this.voteContent = voteContent;
	}


	public String getVoteFieldName() {
		return voteFieldName;
	}


	public void setVoteFieldName(String voteFieldName) {
		this.voteFieldName = voteFieldName;
	}


	public String getBusinessKey() {
		return businessKey;
	}


	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}


	public String getFormData() {
		return formData;
	}


	public void setFormData(String formData) {
		this.formData = formData;
	}


	public Map<String, Object> getFormDataMap() {
		return formDataMap;
	}


	public void setFormDataMap(Map<String, Object> formDataMap) {
		this.formDataMap = formDataMap;
	}


	public Map<String, List<BpmIdentity>> getDestNodeExecutor() {
		return destNodeExecutor;
	}


	public void setDestNodeExecutor(Map<String, List<BpmIdentity>> destNodeExecutor) {
		this.destNodeExecutor = destNodeExecutor;
	}


	public String getInfoFormType() {
		return infoFormType;
	}


	public void setInfoFormType(String infoFormType) {
		this.infoFormType = infoFormType;
	}

	public String toString(){
		return "actDefId:" +actDefId +",flowKey:" + flowKey + ",taskId:" + taskId
				+"destinationNodeId:"+ destinationNodeId + ",subject:" + subject +
				",isStart:" + isBack + ",isAdmin:" + isAdmin +",isBack:" + isBack+
				",isRecover:" + isRecover +",voteAgree:" + voteAgree +",voteContent:"+ voteContent+
				",voteFieldName:"+voteFieldName +",businessKey:" + businessKey +",formData:" + formData+
				",informType:" + infoFormType;
	}
	

}
