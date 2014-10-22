package com.hotent.bpmx.api.model.process.def;

import com.hotent.base.api.BaseModel;
import com.hotent.base.api.TreeType;

/**
 * 
 * 构建组：x5-bpmx-api
 * 描述：流程定义实体接口
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-7-下午2:38:36
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmDefinition extends BaseModel{
	public final static class STATUS{
		/**
		 * 草稿
		 */
		public final static String DRAFT="draft";
		/**
		 * 已发布
		 */
		public final static String DEPLOY="deploy";
		/**
		 * 禁用
		 */
		public final static String FORBIDDEN="forbidden";
		/**
		 * 禁用流程实例
		 */
		public final static String FORBIDDEN_INSTANCE="forbidden_instance";		
	}
	
	
	public final static class TEST_STATUS{
		/**
		 * 测试状态
		 */
		public final static String TEST="test";
		/**
		 * 运营状态
		 */
		public final static String RUN="run";
	}
	/**
	 * 流程定义ID
	 * @return
	 */
	public String getDefId();
	
	public void setDefId(String _defId);
	/**
	 * 流程名称
	 * @return
	 */
	public String getName();
	
	public void setName(String _name);
	/**
	 * 流程实例标题规则
	 * @return
	 */
	public String getInstSubjectRule();
	
	public void setInstSubjectRule(String _instSubjectRule);
	/**
	 * 流程业务主键
	 * @return
	 */
	public String getDefKey();
	
	public void setDefKey(String _defKey);
	/**
	 * 流程定义描述
	 * @return
	 */
	public String getDesc();
	
	public void setDesc(String _desc);
	/**
	 * 获取流程定义的分类
	 */
	public TreeType getType();
	
	public void setType(TreeType _treeType);
	/**
	 * 返回流程状态
	 * @return
	 */
	public String getStatus();
	
	public void setStatus(String _status);
	/**
	 * 测试状态
	 * @return
	 */
	public String getTestStatus();
	
	public void setTestStatus(String _testStatus);
	/**
	 * 流程的通知类型
	 * @return
	 */
	public String getInformType();	
	
	public void setInformType(String _informType);
	
	public void setDesigner(String designer);
	
	/**
	 * 设计器。
	 * @param designer
	 * @return  String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getDesigner();
	
	/**
	 * 数据 - 返回流程定义的XML
	 * @return
	 */
	public String getDefXml();
	
	public void setDefXml(String _defXml);
	/**
	 * 数据 - 获取转化成BPMN的XML
	 * @return
	 */
	public String getBpmnXml();
	
	public void setBpmnXml(String _bpmnXml);
	/**
	 * BPMN - 流程定义ID 建议跟DefId一致
	 * @return
	 */
	public String getBpmnDefId();
	
	public void setBpmnDefId(String _bpmnDefId);
	/**
	 * BPMN - 流程发布ID
	 * @return
	 */
	public String getBpmnDeployId();
	
	public void setBpmnDeployId(String _bpmnDeployId);
	/**
	 * 版本 - 当前版本号
	 * @return
	 */
	public Integer getVersion();
	
	public void setVersion(Integer _version);
	/**
	 * 版本 - 主版本定义ID
	 * @return
	 */
	public String getMainDefId();
	
	public void setMainDefId(String _mainDefId);
	/**
	 * 版本 - 是否主版本
	 * @return
	 */
	public boolean isMain();	
	
	public void setMain(boolean _isMain);
	/**
	 * 版本 - 变更原因
	 * @return
	 */
	public String getReason();
	
	public void setReason(String _reason);
	/**
	 * 参数 - 启动后是否跳过第一个节点
	 * @return
	 */
	public boolean isSkipFirstNode();
	
	public void setIsSkipFirstNode(boolean isSkipFirstNode);
	/**
	 * 参数 - 是否选择第一个节点的执行人
	 * @return
	 */
	public boolean isChooseFirstAssignee();	
	
	public void setIsChooseFirstAssignee(boolean isChooseFirstAssignee);
	/**
	 * 其它 - 返回流程帮助附件ID
	 * @return
	 */
	public String getHelpId();	

	public void setHelpId(String _helpId);
}
