package com.hotent.bpmx.core.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hotent.base.db.id.UniqueIdUtil;
import com.hotent.bpmx.api.constant.TaskStatus;
import com.hotent.bpmx.api.model.delegate.BpmDelegateTask;
import com.hotent.bpmx.api.model.process.task.BpmTask;
import com.hotent.bpmx.persistence.model.DefaultBpmTask;
/**
 * 
 * <pre> 
 * 描述：BPM常用解析工具类
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-10-下午2:52:16
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmUtil {
	/**
	 * 根据规则模版获取标题。
	 * <pre>
	 * 示例：
	 * String rule="{某个部门}申请{时间}公司{活动}";
	 * Map<String,String> map=new HashMap<String,String>();
	 * map.put("部门","测试部");
	 * map.put("时间","周末");
	 * map.put("活动","踏青");
	 * String subject=BpmUtil.getTitleByRule(rule,map);
	 * 返回:
	 * 测试部申请周末公司踏青
	 * </pre>
	 * @param titleRule		主题规则
	 * @param map			变量map，用于替换规则中的变量。
	 * @return  String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getTitleByRule(String titleRule, Map<String, Object> map) {
		if (StringUtils.isEmpty(titleRule)) return "";
		Pattern regex = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = regex.matcher(titleRule);
		while (matcher.find()) {
			String tag = matcher.group(0);
			String rule = matcher.group(1);
			String[] aryRule = rule.split(":");
			String name = "";
			if (aryRule.length == 1) {
				name = rule;
			} else {
				name = aryRule[1];
			}
			if (map.containsKey(name)) {
				Object obj = map.get(name);
				if (obj != null) {
					try {
						titleRule = titleRule.replace(tag, obj.toString());
					} catch (Exception e) {
						titleRule = titleRule.replace(tag, "");
					}
				} else {
					titleRule = titleRule.replace(tag, "");
				}
			} else {
				titleRule = titleRule.replace(tag, "");
			}
		}
		return titleRule;
	}
	
	/**
	 * 将activi的任务转换成BpmTask对象实例。
	 * @param delegateTask
	 * @return 
	 * BpmTask
	 * @exception 
	 * @since  1.0.0
	 */
	public static BpmTask convertTask(BpmDelegateTask delegateTask ){
		String id=UniqueIdUtil.getSuid();
		
		DefaultBpmTask  task=new DefaultBpmTask();
		task.setId(id);
		task.setAssigneeId(delegateTask.getAssignee());
		
		task.setTaskId(delegateTask.getId());
		task.setDueTime(delegateTask.getDueDate());
		task.setSuspendState((short)delegateTask.getSuspensionState());
		task.setExecId(delegateTask.getExecutionId());
		task.setProcInstId(delegateTask.getProcessInstanceId());
		task.setName(delegateTask.getName());
		task.setSubject(delegateTask.getName());
		task.setNodeId(delegateTask.getTaskDefinitionKey());
		task.setOwnerId(delegateTask.getOwner());
		task.setPriority((long)delegateTask.getPriority());
		task.setProcDefId(delegateTask.getProcessDefinitionId());
		task.setStatus(TaskStatus.NORMAL.name());
		
		return task;
		
	}
}
