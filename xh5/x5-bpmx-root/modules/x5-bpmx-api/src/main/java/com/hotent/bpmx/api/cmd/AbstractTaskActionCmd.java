package com.hotent.bpmx.api.cmd;

import java.util.Map;
/**
 * 
 * 描述：任务操作处理基础接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-15-上午10:23:08
 * 版权：广州宏天软件有限公司版权所有
 */
public interface AbstractTaskActionCmd {
	/**
	 * 获取任务ID
	 * @return
	 */
	public String getTaskId();
	/**
	 * 获取变量	
	 * @return
	 */
	public Map<String,Object> getVariables();
}
