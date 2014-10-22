package com.hotent.bpmx.api.constant;

/**
 * 任务状态。
 * 普通任务
 * 代理任务
 * 转交任务
 * 
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bpmx-api
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-2-15-下午11:27:12
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public enum TaskStatus {
	NORMAL,
	AGENT,
	DELIVERTO,
	TRANSFORMING,
	TRANSFORMED
}
