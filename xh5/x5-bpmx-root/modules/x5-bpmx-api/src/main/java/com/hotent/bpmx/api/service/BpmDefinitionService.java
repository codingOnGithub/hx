package com.hotent.bpmx.api.service;

import java.util.List;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.bpmx.api.model.process.def.BpmDefinition;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeIdentityConfig;

/**
 * 
 * 描述：流程定义服务接口
 * 构建组：x5-bpmx-api
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-11-14-下午5:41:22
 * 版权：广州宏天软件有限公司版权所有
 */
public interface BpmDefinitionService {
	/**
	 * 通过ID取得流程定义
	 * @param defId
	 * @return
	 */
	public BpmDefinition getBpmDefinitionByDefId(String defId);
	
	/**
	 * 根据流程定义Key获取流程主板本。
	 * @param defKey
	 * @return 
	 * BpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmDefinition getBpmDefinitionByDefKey(String defKey);
	/**
	 * 发布并生成新版本的流程定义
	 * <pre>
	 * 1.新发布流程。
	 * 	如果key在数据中已经存在，那么
	 * </pre>
	 * @param bpmDefinition
	 * @return
	 */
	public boolean deploy(BpmDefinition bpmDefinition);
	/**
	 * 保存流程设计的草稿
	 * @param bpmDefinition
	 * @return
	 */
	public boolean saveDraft(BpmDefinition bpmDefinition);
	
	/**
	 * 取得流程定义中的某个节点的定义
	 * @param defId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeDef getBpmNodeDef(String defId,String nodeId);
	
	/**
	 * 取得某个流程定义的开始节点的定义
	 * @param defId
	 * @return
	 */
	public BpmNodeDef getStartBpmNodeDef(String defId);
	
	/**
	 * 取得流程定义的结束节点
	 * @param defId
	 * @return
	 */
	public List<BpmNodeDef> getEndNode(String defId);
	
	/**
	 * 取得所有的流程定义节点列表
	 * @param defId
	 * @return
	 */
	public List<BpmNodeDef> getAllBpmNodeDefs(String defId);
	
	/**
	 * 判断当前流程定义是否存在外部子流程
	 * @param actDefId
	 * @return
	 */
	boolean hasExternalSubprocess(String actDefId);
	
	/**
	 * 通过流程定义ID删除流程定义
	 * @param defId
	 * @return
	 */
	public boolean removeBpmDefinition(String defId);
	
	/**
	 * 通过流程定义ID级联删除流程定义及相关配置项
	 * @param defId
	 * @return
	 */
	public boolean removeBpmDefinitionCascade(String defId);
	
	/**
	 * 禁用流程定义
	 * @param defId
	 * @return
	 */
	public boolean disabledBpmDefinition(String defId);
	
	/**
	 * 启用流程定义
	 * @param defId
	 * @return
	 */
	public boolean enabledBpmDefinition(String defId);
	/**
	 * 更新流程定义
	 * @param bpmDefinition
	 * @return
	 */
	public boolean updateBpmDefinition(BpmDefinition bpmDefinition);
	/**
	 * 更新流程定义的所属分类
	 * @param defId
	 * @param typeId 分类ID
	 * @return
	 */
	public boolean updateTreeType(String defId,String typeId);
	
	/**
	 * 通过流程定义取得该流程的所有版本（包括当前版本）
	 * @param defId
	 * @return
	 */
	public List<BpmDefinition> getAllVersions(String defId);
	
	/**
	 * 取得流程定义的所有历史版本
	 * @param defId
	 * @return
	 */
	public List<BpmDefinition> getAllHistoryVersions(String defId);
	
	/**
	 * 按条件取得符合条件的流程定义列表
	 * @param queryFilter
	 * @return
	 */
	public List<BpmDefinition> getAll(QueryFilter queryFilter);
	
	/**
	 * 取得用户授权的流程定义
	 * @param userId
	 * @return
	 */
	public List<BpmDefinition> getProcessDefinitionByUserId(String userId);
	
	/**
	 * 取得用户授权的流程定义,并按条件过滤
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public List<BpmDefinition> getProcessDefinitionByUserId(String userId,QueryFilter queryFilter);
	
	/**
	 * 返回流程定义中的设计生成的文件，并且以字符串格式返回
	 * @param defId
	 * @return
	 */
	public String getDesignFile(String defId);
	/**
	 * 返回流程定义中的BPMN格式，并且以字符串格式返回
	 * @param defId
	 * @return
	 */
	public String getBpmnFile(String defId);
	/**
	 * 取得分类下的流程定义列表
	 * @param typeId
	 * @return
	 */
	public List<BpmDefinition> getByType(String typeId);
	
	/**
	 * 取得分类下的流程定义列表,并分页返回
	 * @param typeId
	 * @param page
	 * @return
	 */
	public List<BpmDefinition> getByType(String typeId,Page page);
	/**
	 * 取得分类下的流程定义列表,并且可进行其他字段的过滤查询
	 * @param typeId
	 * @param filter
	 * @return
	 */
	public List<BpmDefinition> getByType(String typeId,QueryFilter filter);
	/**
	 * 检查流程定义的Code是否存在
	 * @param defCode
	 * @return
	 */
	public boolean isDefCodeExist(String defCode);
	/**
	 * 把流程定义包括相关的附加设置导出XML
	 * @param defId
	 * @return
	 */
	public String exportXml(String defId);
	/**
	 * 导入流程定义
	 * @param expXml
	 * @return
	 */
	public boolean importXml(String expXml);
	
	/**
	 * 取得某流程定义的所有节点配置
	 * @param defId
	 * @return 
	 * List<BpmNodeConfig>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmNodeConfig> getNodeConfigs(String defId);
	/**
	 * 取得某流程定义某节点的配置
	 * @param defId
	 * @param nodeId
	 * @return 
	 * BpmNodeConfig
	 * @exception 
	 * @since  1.0.0
	 */
	public BpmNodeConfig getNodeConfig(String defId,String nodeId);
	
	/**
	 * 获取某个流程的某个节点的执行人
	 * @param defId 流程定义Id
	 * @param nodeId 节点Id
	 * @return List<User>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<BpmNodeIdentityConfig> getBpmNodeUsers(String defId,String nodeId);
}
