package com.hotent.bpmx.core.engine.def;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.FieldLogic;
import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryField;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.bpmx.api.constant.DesignerType;
import com.hotent.bpmx.api.model.process.def.BpmDefinition;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeConfig;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeDef;
import com.hotent.bpmx.api.model.process.nodedef.BpmNodeIdentityConfig;
import com.hotent.bpmx.api.service.BpmDefinitionService;
import com.hotent.bpmx.natapi.def.NatProDefinitionService;
import com.hotent.bpmx.persistence.constants.ProcDefStatus;
import com.hotent.bpmx.persistence.manager.BpmDefinitionManager;
import com.hotent.bpmx.persistence.manager.BpmNodeConfigManager;
import com.hotent.bpmx.persistence.manager.BpmNodeDefHelper;
import com.hotent.bpmx.persistence.model.BpmDefConfig;
import com.hotent.bpmx.persistence.model.DefaultBpmDefinition;
import com.hotent.bpmx.persistence.model.DefaultBpmNodeConfig;
import com.hotent.bpmx.persistence.model.query.BpmDefFieldSorts;
import com.hotent.bpmx.persistence.model.query.BpmDefQueryFields;

@Service
public class DefaultBpmDefinitionService implements BpmDefinitionService {
	private final Log logger = LogFactory.getLog(getClass());

	// 注入Actviti原生的流程定义服务
	@Resource(name = "proDefinitionServiceImpl")
	NatProDefinitionService natProDefinitionService;

	@Resource
	BpmDefinitionManager bpmDefinitionManager;

	@Resource
	BpmNodeConfigManager bpmNodeConfigManager;

	@Resource
	IdGenerator idGenerator;

	@Override
	public boolean deploy(BpmDefinition bpmDefinition) {
		// 数据有效性判断
		if (!isAvailable(bpmDefinition)){
			return false;
		}

		// 如果是新的流程定义标识
		boolean isNewDef = StringUtils.isEmpty(bpmDefinition.getDefId()) ? true: false;
		
		if(isNewDef){
			// 判断defKey是否存在，存在则不允许发布
			DefaultBpmDefinition mainBpmDefinition = bpmDefinitionManager.getMainByDefKey(bpmDefinition.getDefKey());
			if(mainBpmDefinition!=null){
				logger.error("defKey '" + bpmDefinition.getDefKey() +"' is exists ");				
				return false;
			}
		}
		
		// 调用native的xml转换接口，转换defXml为bpmnXml
		String bpmnXml = getBpmnXmlByDesignFile(bpmDefinition);
		if(StringUtils.isEmpty(bpmnXml)){
			return false;
		}
		
		// 调用native接口发布流程
		String deployId = null;
		try {
			deployId = natProDefinitionService.deploy(bpmDefinition.getName(),bpmnXml);
		} catch (UnsupportedEncodingException e) {
			logger.error("Invoke natProDefinitionService.deploy method error = "
					+ e.getMessage());
			return false;
		}

		

		// 根据该流程是否已持久化做分支处理
		if (isNewDef) { // 新流程定义
			bpmDefinition.setDefId(idGenerator.getSuid());
			bpmDefinition.setVersion(1);
			bpmDefinition.setMainDefId(bpmDefinition.getDefId());
			bpmDefinition.setMain(true);
			bpmDefinition.setStatus(BpmDefinition.STATUS.DEPLOY);
			bpmDefinition.setTestStatus(BpmDefinition.TEST_STATUS.TEST);
			
			String bpmnDefId=natProDefinitionService.getProcessDefinitionIdByDeployId(deployId);
			bpmDefinition.setBpmnDefId(bpmnDefId);
			bpmDefinition.setBpmnDeployId(deployId);
			bpmDefinition.setBpmnXml(bpmnXml);
			
			//设置基本的流程参数配置
			BpmDefConfig bpmDefConfig = new BpmDefConfig();
			DefaultBpmDefinition defaultBpmDefinition = (DefaultBpmDefinition) bpmDefinition;
			defaultBpmDefinition.setBpmDefConfig(bpmDefConfig);			
			
			//保存
			bpmDefinitionManager.create(defaultBpmDefinition);

			//根据bpmnXml，新增该流程的所有节点配置
			bpmNodeConfigManager.createNodeConfigs(bpmnXml,bpmDefinition.getDefId());
			
		} else {	// 已存在的流程定义
			//判断bpmnXml是否改变
			boolean isBpmnXmlChange = bpmnXml.equals(bpmDefinition.getBpmnXml())?false:true;

			DefaultBpmDefinition oldBpmDefinition = (DefaultBpmDefinition) bpmDefinition;
			
			//根据旧的流程定义克隆一份，并将新的流程定义作为主版本
			DefaultBpmDefinition newBpmDefinition =bpmDefinitionManager.cloneToMain(oldBpmDefinition);
			newBpmDefinition.setStatus(BpmDefinition.STATUS.DEPLOY);
			newBpmDefinition.setTestStatus(BpmDefinition.TEST_STATUS.TEST);
			
			String bpmnDefId=natProDefinitionService.getProcessDefinitionIdByDeployId(deployId);
			bpmDefinition.setBpmnDefId(bpmnDefId);
			newBpmDefinition.setBpmnDeployId(deployId);
			newBpmDefinition.setBpmnXml(bpmnXml);
			bpmDefinitionManager.update(newBpmDefinition);
			
			if(isBpmnXmlChange){
				//根据新的bpmnXml，调整更新流程的节点配置
				bpmNodeConfigManager.modifyNodeConfigs(bpmnXml,newBpmDefinition.getDefId());
			}
		}

		return true;
	}
	
	@Override
	public boolean saveDraft(BpmDefinition bpmDefinition) {
		//先判断是否可以处理
		if(bpmDefinition==null
				|| StringUtils.isEmpty(bpmDefinition.getDefKey())
				|| (StringUtils.isNotEmpty(bpmDefinition.getStatus()) && !bpmDefinition.getStatus().equals(ProcDefStatus.DRAFT))){
			return false;
		}
		//设置状态
		bpmDefinition.setStatus(ProcDefStatus.DRAFT.getKey());
		//每保存一次，版本号加1
		Integer maxVersion = bpmDefinitionManager.getMaxVersion(bpmDefinition.getDefKey());
		bpmDefinition.setVersion(maxVersion+1);
		
		bpmDefinitionManager.update((DefaultBpmDefinition)bpmDefinition);
		
		return true;
	}

	@Override
	public BpmNodeDef getBpmNodeDef(String defId, String nodeId) {		
		LinkedHashMap<String,BpmNodeDef> bpmNodeDefMap = BpmNodeDefHelper.getBpmNodeDefMap(defId); 
		return bpmNodeDefMap.get(nodeId);
	}

	@Override
	public BpmNodeDef getStartBpmNodeDef(String defId) {
		List<BpmNodeDef> bpmNodeDefs = getAllBpmNodeDefs(defId);
		return bpmNodeDefs.size()>0?bpmNodeDefs.get(0):null;
	}
	
	@Override
	public List<BpmNodeDef> getAllBpmNodeDefs(String defId) {
		return BpmNodeDefHelper.getBpmNodeDefList(defId);
	}

	@Override
	public List<BpmNodeDef> getEndNode(String defId) {
		List<BpmNodeDef> endNodeDefs = new ArrayList<BpmNodeDef>();
		List<BpmNodeDef> bpmNodeDefs = getAllBpmNodeDefs(defId);
		for(BpmNodeDef bpmNodeDef:bpmNodeDefs){
			if(bpmNodeDef.getOutcomeNodes().size()==0){
				endNodeDefs.add(bpmNodeDef);
			}
		}
		return endNodeDefs;
	}	

	@Override
	public boolean removeBpmDefinition(String defId) {
		bpmDefinitionManager.remove(defId);
		return true;
	}

	@Override
	public boolean removeBpmDefinitionCascade(String defId) {
		bpmDefinitionManager.removeCascade(defId);
		return false;
	}

	@Override
	public boolean disabledBpmDefinition(String defId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		defaultBpmDefinition.setStatus(BpmDefinition.STATUS.FORBIDDEN);
		bpmDefinitionManager.update(defaultBpmDefinition);
		return true;
	}

	@Override
	public boolean enabledBpmDefinition(String defId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		defaultBpmDefinition.setStatus(BpmDefinition.STATUS.DEPLOY);
		bpmDefinitionManager.update(defaultBpmDefinition);
		return true;
	}

	@Override
	public boolean updateBpmDefinition(BpmDefinition bpmDefinition) {
		// 进行数据有效性判断
		if(!isAvailable(bpmDefinition)){
			return false;
		}

		// 调用native的xml转换接口，转换defXml为bpmnXml
		String bpmnXml = getBpmnXmlByDesignFile(bpmDefinition);
		if(StringUtils.isEmpty(bpmnXml)){
			return false;
		}
		
		//判断bpmnXml是否改变
		boolean isBpmnXmlChange = bpmnXml.equals(bpmDefinition.getBpmnXml())?false:true;
		
		Integer maxVersion = bpmDefinitionManager.getMaxVersion(bpmDefinition.getDefKey());
		bpmDefinition.setVersion(maxVersion+1);
		
		if(isBpmnXmlChange){
			// 保存流程
			bpmDefinition.setBpmnXml(bpmnXml);													
			//根据新的bpmnXml，调整更新流程的节点配置
			bpmNodeConfigManager.modifyNodeConfigs(bpmnXml,bpmDefinition.getDefId());			
		}
		
		bpmDefinitionManager.update((DefaultBpmDefinition) bpmDefinition);
		
		return true;
	}


	@Override
	public List<BpmDefinition> getAllVersions(String defId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		List<DefaultBpmDefinition> defaultBpmDefinitions= bpmDefinitionManager.queryByDefKey(defaultBpmDefinition.getDefKey());
		return convertBpmDefinitions(defaultBpmDefinitions);
	}

	@Override
	public List<BpmDefinition> getAllHistoryVersions(String defId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		List<DefaultBpmDefinition> defaultBpmDefinitions= bpmDefinitionManager.queryHistorys(defaultBpmDefinition.getDefKey());
		return convertBpmDefinitions(defaultBpmDefinitions);
	}

	@Override
	public List<BpmDefinition> getAll(QueryFilter queryFilter) {
		List<DefaultBpmDefinition> defaultBpmDefinitions= bpmDefinitionManager.query(queryFilter);
		return convertBpmDefinitions(defaultBpmDefinitions);
	}

	@Override
	public boolean hasExternalSubprocess(String actDefId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDesignFile(String defId) {
		if(StringUtils.isEmpty(defId)){
			return "";
		}
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		if(defaultBpmDefinition!=null){
			return defaultBpmDefinition.getDefXml();
		}
		return "";
	}

	@Override
	public String getBpmnFile(String defId) {
		if(StringUtils.isEmpty(defId)){
			return "";
		}
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		if(defaultBpmDefinition!=null){
			return defaultBpmDefinition.getDefXml();
		}
		return "";
	}

	@Override
	public List<BpmDefinition> getByType(String typeId) {
		BpmDefQueryFields bpmDefQueryFields = new BpmDefQueryFields();
		bpmDefQueryFields.addTypeId(typeId);
		BpmDefFieldSorts bpmDefFieldSorts = new BpmDefFieldSorts();
		bpmDefFieldSorts.addDefId();
		List<DefaultBpmDefinition> defaultBpmDefinitions = bpmDefinitionManager.query(bpmDefQueryFields, FieldRelation.AND, bpmDefFieldSorts);
		return convertBpmDefinitions(defaultBpmDefinitions);
	}

	@Override
	public List<BpmDefinition> getByType(String typeId, Page page) {
		BpmDefQueryFields bpmDefQueryFields = new BpmDefQueryFields();
		bpmDefQueryFields.addTypeId(typeId);
		BpmDefFieldSorts bpmDefFieldSorts = new BpmDefFieldSorts();
		bpmDefFieldSorts.addDefId();
		DefaultPage defaultPage = new DefaultPage();
		defaultPage.setOrders(bpmDefFieldSorts.getFieldSorts());
		defaultPage.setPage(page.getPageNo());
		defaultPage.setLimit(page.getPageSize());
		List<DefaultBpmDefinition> defaultBpmDefinitions = bpmDefinitionManager.query(bpmDefQueryFields, FieldRelation.AND, defaultPage);
		return convertBpmDefinitions(defaultBpmDefinitions);
	}

	@Override
	public List<BpmDefinition> getByType(String typeId, QueryFilter filter) {
		if(StringUtils.isEmpty(typeId) || filter==null){
			return new ArrayList<BpmDefinition>();
		}
		DefaultQueryFilter defaultQueryFilter = (DefaultQueryFilter)filter;		
		if(defaultQueryFilter.getFieldLogic()==null || defaultQueryFilter.getFieldLogic().getWhereClauses().size()==0){
			FieldLogic andFieldLogic=new DefaultFieldLogic(FieldRelation.AND);
			defaultQueryFilter.setFieldLogic(andFieldLogic);
		}
		defaultQueryFilter.getFieldLogic().getWhereClauses().add(new DefaultQueryField("TYPE_ID_", QueryOP.EQUAL, typeId));
		List<DefaultBpmDefinition> defaultBpmDefinitions = bpmDefinitionManager.query(defaultQueryFilter);
		return convertBpmDefinitions(defaultBpmDefinitions);				
	}

	@Override
	public boolean isDefCodeExist(String defCode) {
		List<DefaultBpmDefinition> defaultBpmDefinitions = bpmDefinitionManager.queryByDefKey(defCode);
		if(defaultBpmDefinitions.size()>0){
			return true;
		}
		return false;		
	}

	@Override
	public String exportXml(String defId) {
		//TODO
		return null;
	}

	@Override
	public boolean importXml(String expXml) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTreeType(String defId, String typeId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		if(defaultBpmDefinition!=null){
			defaultBpmDefinition.setTypeId(typeId);
			bpmDefinitionManager.update(defaultBpmDefinition);
			return true;
		}
		return false;
	}

	@Override
	public List<BpmDefinition> getProcessDefinitionByUserId(String userId) {
		BpmDefQueryFields bpmDefQueryFields = new BpmDefQueryFields();
		bpmDefQueryFields.addCreateBy(userId);
		BpmDefFieldSorts bpmDefFieldSorts = new BpmDefFieldSorts();
		bpmDefFieldSorts.addDefId();
		List<DefaultBpmDefinition> defaultBpmDefinitions = bpmDefinitionManager.query(bpmDefQueryFields, FieldRelation.AND, bpmDefFieldSorts);
		return convertBpmDefinitions(defaultBpmDefinitions);
	}

	@Override
	public List<BpmDefinition> getProcessDefinitionByUserId(String userId,
			QueryFilter queryFilter) {
		if(StringUtils.isEmpty(userId) || queryFilter==null){
			return new ArrayList<BpmDefinition>();
		}
		DefaultQueryFilter defaultQueryFilter = (DefaultQueryFilter)queryFilter;		
		if(defaultQueryFilter.getFieldLogic()==null || defaultQueryFilter.getFieldLogic().getWhereClauses().size()==0){
			FieldLogic andFieldLogic=new DefaultFieldLogic(FieldRelation.AND);
			defaultQueryFilter.setFieldLogic(andFieldLogic);
		}
		defaultQueryFilter.getFieldLogic().getWhereClauses().add(new DefaultQueryField("CREATE_BY_", QueryOP.EQUAL, userId));
		List<DefaultBpmDefinition> defaultBpmDefinitions = bpmDefinitionManager.query(defaultQueryFilter);
		return convertBpmDefinitions(defaultBpmDefinitions);				
	}

	@Override
	public List<BpmNodeConfig> getNodeConfigs(String defId) {
		List<DefaultBpmNodeConfig> defaultBpmNodeConfigs = bpmNodeConfigManager.queryBpmNodeConfigs(defId);
		return convertBpmNodeConfigs(defaultBpmNodeConfigs);
	}

	@Override
	public BpmNodeConfig getNodeConfig(String defId, String nodeId) {
		DefaultBpmNodeConfig defaultBpmNodeConfig = bpmNodeConfigManager.getByDefIdAndNodeId(defId, nodeId);
		return (BpmNodeConfig)defaultBpmNodeConfig;
	}

	@Override
	public List<BpmNodeIdentityConfig> getBpmNodeUsers(String defId,
			String nodeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BpmDefinition getBpmDefinitionByDefId(String defId) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.get(defId);
		return (BpmDefinition)defaultBpmDefinition;
	}

	@Override
	public BpmDefinition getBpmDefinitionByDefKey(String defKey) {
		DefaultBpmDefinition defaultBpmDefinition = bpmDefinitionManager.getMainByDefKey(defKey);
		return (BpmDefinition)defaultBpmDefinition;
	}	
	
	/**
	 *
	 * 判断流程定义的数据是否有效（即包含必须的一些数据）
	 * @param bpmDefinition
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private boolean isAvailable(BpmDefinition bpmDefinition){
		if (bpmDefinition == null
				|| StringUtils.isEmpty(bpmDefinition.getName())
				|| StringUtils.isEmpty(bpmDefinition.getDefKey())
				|| StringUtils.isEmpty(bpmDefinition.getDefXml())) {
			logger.error("DefaultBpmDefinitionService.deploy data error, bpmDefinition="
					+ bpmDefinition);
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 根据根据设计文件转换成标准的bpmn20定义文件。
	 * @param bpmDefinition
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	private String getBpmnXmlByDesignFile(BpmDefinition bpmDefinition){
		DesignerType designType=DesignerType.valueOf(bpmDefinition.getDesigner());
		
		String bpmnXml =DefTransFormUtil.transForm(designType, bpmDefinition.getDefKey(), bpmDefinition.getName(), bpmDefinition.getDefXml());
		
		return bpmnXml;
	}

	private List<BpmDefinition> convertBpmDefinitions(List<DefaultBpmDefinition> defaultBpmDefinitions){
		List<BpmDefinition> bpmDefinitions = new ArrayList<BpmDefinition>();
		for(DefaultBpmDefinition _defaultBpmDefinition:defaultBpmDefinitions){
			bpmDefinitions.add((BpmDefinition)_defaultBpmDefinition);
		}
		return bpmDefinitions;
	}
		
	private List<BpmNodeConfig> convertBpmNodeConfigs(List<DefaultBpmNodeConfig> defaultBpmNodeConfigs){
		List<BpmNodeConfig> bpmNodeConfigs = new ArrayList<BpmNodeConfig>();
		for(DefaultBpmNodeConfig defaultBpmNodeConfig:defaultBpmNodeConfigs){
			BpmNodeConfig bpmNodeConfig = (BpmNodeConfig)defaultBpmNodeConfig;
			bpmNodeConfigs.add(bpmNodeConfig);
		}
		return bpmNodeConfigs;
	}

}
