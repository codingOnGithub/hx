package com.hotent.bpmx.persistence.manager;

import java.util.List;

import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.manager.api.Manager;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.query.BpmDefFieldSorts;
import com.hotent.bpmx.persistence.model.query.BpmDefQueryFields;

public interface BpmDefinitionManager extends Manager<String, DefaultBpmDefinition>{
	/**
	 * 
	 * 查询流程定义实体，并查询它的一对一子表的流程定义数据
	 * @param entityId
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getWithConfig(String entityId);

	/**
	 * 查询流程定义实体，并查询它的一对一子表的数据
	 * @param entityId
	 * @return  包含一对一子表数据的流程定义实体
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getWithCascade(String entityId);
	
	/**
	 * 
	 * 根据流程业务主键查询和该主键一致的流程定义（该定义记录为主版本）
	 * @param defKey
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getMainByDefKey(String defKey);

	/**
	 * 根据流程主键查询所有流程定义
	 * @param defKey
	 * @return 
	 * List<DefaultBpmDefinition>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<DefaultBpmDefinition> queryByDefKey(String defKey);
	
	/**
	 * 根据流程主键查询所有历史流程定义
	 * @param defKey
	 * @return 
	 * List<DefaultBpmDefinition>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<DefaultBpmDefinition> queryHistorys(String defKey);
	
	/**
	 * 根据流程主键，将相同主键的所有历史流程定义的主流程ID更新为传入的主流程ID
	 * @param mainDefId
	 * @param defKey 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateMainDefId(String mainDefId,String defKey);
	
	/**
	 * 对传入的流程定义克隆一份，将新产生的流程定义设置为主版本，并更新其它版本的流程定义为历史版本
	 * （传入的流程定义可以是主版本，也可以是历史版本）
	 * @param bpmDefinition
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition cloneToMain(DefaultBpmDefinition bpmDefinition);
	
	/**
	 * 根据流程主键获得最大的版本号的流程定义
	 * @param defKey
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getMaxVersionDef(String defKey);
	
	/**
	 * 根据流程主键获得最大的版本号
	 * @param defKey
	 * @return
	 */
	public Integer getMaxVersion(String defKey);
	
	/**
	 * 根据查询条件、条件关系、分页对象（其中包含排序）进行流程定义的查询。（这个方法支持分页）
	 * 单元测试类见BpmDefinitionManagerTest
	 * @param bpmDefQueryFields 查询条件
	 * @param fieldRelation 条件关系
	 * @param page 分页对象
	 * @return
	 */
	public List<DefaultBpmDefinition> query(BpmDefQueryFields bpmDefQueryFields,FieldRelation fieldRelation,DefaultPage page);
	
	/**
	 * 根据查询条件、条件关系、排序条件进行流程定义的查询。（这个方法不支持分页）
	 * @param bpmDefQueryFields 查询条件
	 * @param fieldRelation 条件关系
	 * @param bpmDefFieldSorts 分页对象
	 * @return
	 */
	public List<DefaultBpmDefinition> query(BpmDefQueryFields bpmDefQueryFields,FieldRelation fieldRelation,BpmDefFieldSorts bpmDefFieldSorts);
	
	/**
	 * 级联删除流程，包括历史数据、流程实例、任务实例、流程节点配置、流程本身
	 * @param entityId 流程定义主键ID
	 */
	public void removeCascade(String entityId);
	/**
	 * 按bpmn定义ID获取流程定义
	 * @param bpmnDefId
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getByBpmnDefId(String bpmnDefId);
}
