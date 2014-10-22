package com.hotent.bpmx.persistence.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.base.core.util.time.DateUtil;
import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bpmx.api.cmd.ProcessInstCmd;
import com.hotent.bpmx.api.model.process.def.BpmDefinition;
import com.hotent.bpmx.core.util.BpmUtil;
import com.hotent.bpmx.persistence.dao.BpmProcessInstanceDao;
import com.hotent.bpmx.persistence.manager.BpmProcessInstanceManager;
import com.hotent.bpmx.persistence.model.DefaultBpmProcessInstance;

@Service("bpmProcessInstanceManager")
public class BpmProcessInstanceManagerImpl extends AbstractManagerImpl<String, DefaultBpmProcessInstance> implements BpmProcessInstanceManager {
	@Resource
	BpmProcessInstanceDao bpmProcessInstanceDao;

	@Override
	protected Dao<String, DefaultBpmProcessInstance> getDao() {
		return bpmProcessInstanceDao;
	}

	@Override
	public String getSubject(BpmDefinition bpmDefinition, ProcessInstCmd processInstCmd) {

		// 若设置了标题，则直接返回该标题，否则按后台的标题规则返回
		if (StringUtils.isNotEmpty(processInstCmd.getSubject())) {
			return processInstCmd.getSubject();
		}

		String rule = bpmDefinition.getInstSubjectRule();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", bpmDefinition.getInstSubjectRule());
		// SysUser user = ContextUtil.getCurrentUser();
		// map.put(BpmConst.StartUser, user.getUsername());
		map.put("startDate", DateUtil.getCurrentDate());
		map.put("startTime", DateUtil.getCurrentTime());
		map.put("businessKey", processInstCmd.getBusinessKey());
		map.putAll(processInstCmd.getVariables());
		rule = BpmUtil.getTitleByRule(rule, map);

		// if(BpmDefinition.STATUS_TEST.equals(bpmDefinition.getStatus())){
		// if(StringUtil.isEmpty(bpmDefinition.getTestStatusTag())){
		// return BpmDefinition.TEST_TAG+"-"+rule;
		// }else{
		// return bpmDefinition.getTestStatusTag()+"-"+rule;
		// }
		// }
		return rule;

	}
}
