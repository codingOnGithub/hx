package com.hotent.bpmx.api.engine;

import com.hotent.bpmx.api.service.BpmDefinitionService;
import com.hotent.bpmx.api.service.BpmTaskService;
/**
 * 
 * <pre> 
 * 描述：流程引擎接口类
 * 构建组：x5-bpmx-api
 * 作者：huangli
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-19-下午6:01:43
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmxEngine {
	/**
	 * 引擎名称
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getName();
	/**
	 * 获取任务服务
	 * @return 
	 * BpmTaskService
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmTaskService getBpmTaskService();
	/**
	 * 获取流程定义服务
	 * @return 
	 * BpmDefinitionService
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmDefinitionService getBpmDefinitionService();
	
	
}
