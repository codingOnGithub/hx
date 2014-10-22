package com.hotent.bpmx.core.engine.task;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.bpmx.api.cmd.TaskAgentCmd;
import com.hotent.bpmx.api.cmd.TaskCopyCmd;
import com.hotent.bpmx.api.cmd.TaskFinishCmd;
import com.hotent.bpmx.api.cmd.TaskRecoverCmd;
import com.hotent.bpmx.api.cmd.TaskSplitCmd;
import com.hotent.bpmx.api.cmd.TaskTurnCmd;
import com.hotent.bpmx.api.cmd.TaskUrgeCmd;
import com.hotent.bpmx.api.model.process.task.BpmTask;
import com.hotent.bpmx.api.service.BpmTaskService;
import com.hotent.org.api.model.User;

@Service
public class DefaultBpmTaskService implements BpmTaskService {

	@Override
	public List<BpmTask> getTasksByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getTasksByUserId(String userId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getTasksByUserId(String userId, QueryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAllTasks(QueryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAgentTasksByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAgentTasksByUserId(String userId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAgentTasksByUserId(String userId,
			QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAgentedToTasksByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAgentedToTasksByUserId(String userId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getAgentedToTasksByUserId(String userId,
			QueryFilter queryFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelAgentedToTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean finishTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean backTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commentTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean endTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean communicateTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BpmTask copyTask(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BpmTask copyToUsers(TaskCopyCmd taskCmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getTaksOfCopyToByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getTaksOfCopyToByUserId(String userId, Page page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> getTaksOfCopyToByUserId(String userId,
			QueryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BpmTask> splitTask(TaskSplitCmd taskSplitCmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void urgeTask(TaskUrgeCmd taskUrgeCmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockTask(TaskFinishCmd taskCmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unloackTask(TaskFinishCmd task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BpmTask> addSignTask(TaskFinishCmd cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agentTask(TaskAgentCmd taskAgentCmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelAgentTask(TaskAgentCmd taskAgentCmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turnTask(TaskTurnCmd taskTurnCmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recoverTask(TaskRecoverCmd taskRecoverCmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getTaskUsers(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}


}
