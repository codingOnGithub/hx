package com.hotent.bpmx.api.model.process.inst;

import java.util.Date;

import com.hotent.base.api.BaseModel;

/**
 * 
 * 构建组：x5-bpmx-api
 * 描述：流程实例实体接口
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-7-下午2:36:53
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmProcessInstance extends BaseModel{
	/**
	 * 草稿=draft
	 */
	public final static String STATUS_DRAFT="draft";
	/**
	 * 运行中=running
	 */
	public final static String STATUS_RUNNING="running";
	/**
	 * 挂起=pending
	 */
	public final static String STATUS_PENDING="pending";
	/**
	 * 结束
	 */
	public final static String STATUS_END="end";
	/**
	 * 审批结果-未审批=uncheck
	 */
	public final static String RESULT_TYPE_INIT="uncheck";
	/**
	 * 审批结果-审批通过=pass
	 */
	public final static String RESULT_TYPE_PASS="pass";
	/**
	 * 审批结果-审批不通过=refuse
	 */
	public final static String RESULT_TYPE_REFUSE="refuse";
	
	/**
	 * 返回流程实例主键ID
	 * @return
	 */
	public String getId();
	/**
	 * 流程引擎实例ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getProcInstId();
	
	/**
	 * 实例标题
	 * @return
	 */
	public String getSubject();	
	/**
	 * 流程定义ID
	 */
	public String getProcDefId();
	
	/**
	 * 流程名称
	 * @return
	 */
	public String getProcDefName();
	/**
	 * 关联的业务主键
	 * @return
	 */
	public String getBizKey();
	/**
	 * 返回绑定的表单Key
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getFormKey();
	/**
	 * 状态
	 * @return
	 */
	public String getStatus();
	/**
	 * 结束时间
	 * @return
	 */
	public Date getEndTime();
	/**
	 * 持续时间，从开始时间至结束时间中有效的时间(毫秒)
	 * @return
	 */
	public Long getDuration();
	/**
	 * 实例所属的分类 
	 * @return
	 */
	public String getTypeId();
	
	
	
}
