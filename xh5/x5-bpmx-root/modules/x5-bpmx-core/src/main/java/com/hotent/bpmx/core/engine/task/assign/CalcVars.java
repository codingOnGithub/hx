package com.hotent.bpmx.core.engine.task.assign;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程上下文变量。
 * 包括流程变量，流程实例，发起人，当前人等变量。
 * <pre> 
 * 描述：流程上下文变量。
 * 构建组：x5-bpmx-core
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2013-12-18-下午3:19:10
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class CalcVars {
	
	/**
	 * 流程发起人
	 */
	private  Long startUserId;
	/**
	 * 上一步执行人
	 */
	private Long prevExecUserId;
	/**
	 * 流程实例Id
	 */
	private String actInstId;
	
	/**
	 * 流程变量
	 */
	private Map<String,Object> vars=new HashMap<String, Object>();
	
	public CalcVars(Long startUserId,Long preExecUserId,String actInstId,Map<String,Object> vars){
		this.startUserId=startUserId;
		this.prevExecUserId=preExecUserId;
		this.actInstId=actInstId;
		this.vars=vars;
	}

	public Long getStartUserId() {
		return startUserId;
	}

	public void setStartUserId(Long startUserId) {
		this.startUserId = startUserId;
	}

	public Long getPrevExecUserId() {
		return prevExecUserId;
	}

	public void setPrevExecUserId(Long prevExecUserId) {
		this.prevExecUserId = prevExecUserId;
	}

	public String getActInstId() {
		return actInstId;
	}

	public void setActInstId(String actInstId) {
		this.actInstId = actInstId;
	}

	public Map<String, Object> getVars() {
		return vars;
	}

	public void setVars(Map<String, Object> vars) {
		this.vars = vars;
	}
	
	

	
	
}
