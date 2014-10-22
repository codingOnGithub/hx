package com.hotent.bpmx.persistence.dao;
import java.util.List;

import com.hotent.base.db.api.Dao;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;

/**
 * 
 * <pre> 
 * 描述：流程定义
 * 构建组：x5-bpmx-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-23-上午8:41:25
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface BpmDefinitionDao extends Dao<String, DefaultBpmDefinition> {
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
	 * 根据流程业务主键查询和该主键一致的流程定义（该定义记录为主版本）
	 * @param defKey
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getMainByDefKey(String defKey);
	
	/**
	 * 获得流程业务主键相同的最大版本号记录
	 * @param defKey
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getMaxVersion(String defKey);
	
	/**
	 * 根据流程业务主键查询流程定义记录
	 * @param defKey
	 * @return 
	 * List<DefaultBpmDefinition>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<DefaultBpmDefinition> queryByDefKey(String defKey);
	
	/**
	 * 根据流程业务主键查询流程定义历史记录
	 * @param defKey
	 * @return 
	 * List<DefaultBpmDefinition>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<DefaultBpmDefinition> queryHistorys(String defKey);
	
	/**
	 * 按BpmnDefID获取流程定义
	 * @param bpmnDefId
	 * @return 
	 * DefaultBpmDefinition
	 * @exception 
	 * @since  1.0.0
	 */
	public DefaultBpmDefinition getByBpmnDefId(String bpmnDefId);
}
