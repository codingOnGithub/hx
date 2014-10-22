package com.hotent.bo.persistence.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.api.Dao;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.bo.persistence.dao.BOAttributeDao;
import com.hotent.bo.persistence.dao.BODefDao;
import com.hotent.bo.persistence.dao.BOPackageDao;
import com.hotent.bo.persistence.dao.BORuleDao;
import com.hotent.bo.persistence.dao.BORuleRefDao;
import com.hotent.bo.persistence.manager.BODefManager;
import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_TYPE;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.BODef.BODEF_IS_MAIN;
import com.hotent.bo.persistence.model.BODef.BODEF_IS_MASTER;
import com.hotent.bo.persistence.model.BOPackage;
import com.hotent.bo.persistence.model.BOPackage.BOPACKAGE_STATUS;
import com.hotent.bo.persistence.model.BORule;
import com.hotent.bo.persistence.model.BORule.BORULE_BELONG_TYPE;
import com.hotent.bo.persistence.model.BORuleRef;

/**
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bo-core
 * 作者：xucx
 * 邮箱:xucx@jee-soft.cn
 * 日期:2014-1-25-上午10:34:00
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@Service("bODefManager")
public class BODefManagerImpl extends AbstractManagerImpl<String, BODef> implements BODefManager{
	@Resource
	BODefDao bODefDao;
	@Resource
	BOAttributeDao bOAttributeDao;	
	
	@Resource
	BOPackageDao bOPackageDao;
	
	@Resource
	BORuleDao bORuleDao;
	
	@Resource
	BORuleRefDao bORuleRefDao;
	
	@Override
	protected Dao<String, BODef> getDao() {
		return bODefDao;
	}
	
	@Resource
	IdGenerator idGenerator;
	
	/**
	 * 创建实体包含子表实体
	 */
	public void create(BODef bODef){
    	super.create(bODef);
    	bOAttributeDao.delByMainId(bODef.getId());
    	List<BOAttribute> bOAttributeList=bODef.getBOAttributeList();
    	for(BOAttribute bOAttribute:bOAttributeList){
    		bOAttributeDao.create(bOAttribute);
    	}
    }
	
	/**
	 * 删除记录包含子表记录
	 */
	public void remove(String entityId){
		super.remove(entityId);
    	bOAttributeDao.delByMainId(entityId);
		
	}
	
	/**
	 * 批量删除包含子表记录
	 */
	public void removeByIds(String[] entityIds){
		for(String id:entityIds){
			this.remove(id);
		}
	}
    
	/**
	 * 获取实体
	 */
    public BODef get(String entityId){
    	BODef bODef=super.get(entityId);
    	List<BOAttribute> bOAttributeList=bOAttributeDao.getByMainId(entityId);
    	bODef.setBOAttributeList(bOAttributeList);
    	return bODef;
    }
    
    /**
     * 更新实体同时更新子表记录
     */
    public void update(BODef bODef){
    	super.update(bODef);
    	bOAttributeDao.delByMainId(bODef.getId());
    	List<BOAttribute> bOAttributeList=bODef.getBOAttributeList();
    	for(BOAttribute bOAttribute:bOAttributeList){
    		bOAttributeDao.create(bOAttribute);
    	}
    }
    
	@Override
	public List<BOAttribute> getByMainId(String defId) {
		return bOAttributeDao.getByMainId(defId);
	}
	
    /**
     * 获取实体(加载所有业务所有内容)
     * @param entityId
     * @return 
     * BODef
     * @exception 
     * @since  1.0.0
     */
	@Override
    public BODef loadBODef(String entityId){
    	BODef bODef=super.get(entityId);
    	//没有查到对象直接为空
    	if(BeanUtils.isEmpty(bODef)){
   			return null;                
   		}
		
    	//加载主对象共用规则
    	Map map = new HashMap();
    	map.put("defId", entityId);
    	map.put("belongType", BORULE_BELONG_TYPE.DEF);
    	List<BORule> bORuleList= bORuleDao.getRules(map);
    	bODef.setBORuleList(bORuleList);
    	
    	//加载属性及其规则和依赖对象
    	List<BOAttribute> bOAttributeList=bOAttributeDao.getByMainId(entityId);
    	for(BOAttribute boAttribute : bOAttributeList){	
    		//加载属性的个性规则
    		Map baMap = new HashMap();
    		baMap.put("attrId", boAttribute.getId());
    		List<BORule> attRuleList= bORuleDao.getRulesByAttrId(baMap);
    		boAttribute.setAttRuleList(attRuleList);    
    		//加载属性依赖对象
    		if(BOATTRIBUTE_TYPE.REF.equals(boAttribute.getType())&&BeanUtils.isNotEmpty(boAttribute.getRefId())){   		
    			BODef refBODef = loadBODef(boAttribute.getRefId());  //递归加载
    			boAttribute.setRefBODef(refBODef);
    		}	
		}
    	bODef.setBOAttributeList(bOAttributeList); 
    	return bODef;
    }
	
    
    /**
     * 删除实体(实体删除所有相关内容)
     * @param entityId
     * @return 
     * BODef
     * @exception 
     * @since  1.0.0
     */
    @Override
    public void removeBODef(String entityId){
    	removeBODefOrAtt(entityId,true);
    }
    
	
    /**
     * 删除实体(实体删除所有相关内容)或者删除实体相关的其它内容
     * @param entityId
     * @return 
     * BODef
     * @exception 
     * @since  1.0.0
     */
    private void removeBODefOrAtt(String entityId,boolean mark){
    	//删除属性及其规则和依赖对象
    	List<BOAttribute> bOAttributeList=bOAttributeDao.getByMainId(entityId);
    	for(BOAttribute boAttribute : bOAttributeList){    		
    		//删除属性的规则中间表
    		Map baMapRef = new HashMap();
    		baMapRef.put("attrId", boAttribute.getId());
    		bORuleRefDao.removeRulesRefs(baMapRef);   		
    		//删除属性的规则
    		Map baMap = new HashMap();
    		baMap.put("attrId", boAttribute.getId());
    		baMap.put("defId", entityId);
    		baMap.put("belongType",BORULE_BELONG_TYPE.ATTR);
    		bORuleDao.removeRules(baMap);   		
    		//删除加载属性依赖对象
    		if(BOATTRIBUTE_TYPE.REF.equals(boAttribute.getType())&&BeanUtils.isNotEmpty(boAttribute.getRefId())){   		
    			//递归删除
    			removeBODef(boAttribute.getRefId());     
    		}
    		bOAttributeDao.remove(boAttribute.getId());  
		}
    	
    	//删除主对象共用规则
    	Map map = new HashMap();
    	map.put("defId", entityId);
    	map.put("belongType", BORULE_BELONG_TYPE.DEF);
    	bORuleDao.removeRules(map);    	
    	
    	//判断删除业务对象 
    	if(mark){
    		bODefDao.remove(entityId);      
    	}	
    }
    
    

    /**
     * 发布实体（包括发布新版本）
     * @param bODef   对象
     * @return 
     * @exception 
     * @since  1.0.0
     */
	@Override
	public void deploy(BODef bODef) {
		BODef oldBODef = null;
		String defId = bODef.getId();
		//到数据库中是否存在业务对象
		if(BeanUtils.isNotEmpty(defId)){
			oldBODef = bODefDao.get(defId);   
		}
		
		//存在则表示是发布新版本操作，否则为第一次发布操作
		if(BeanUtils.isNotEmpty(oldBODef)&&BeanUtils.isNotEmpty(oldBODef.getId())){  
			//重新发布版本操作
			String suid = idGenerator.getSuid();
			bODef.setId(suid);
			//通过code获取当前是存在了多少版本，重新发布时要加1
			Map map = new HashMap();
	    	map.put("code", oldBODef.getCode());
	    	BODef maxVersionBODef = bODefDao.getMaxVersionBODef(map);   
			Long version = maxVersionBODef.getVersion()+1l;          
			bODef.setVersion(version);
			//同一业务对象的code是一样的
			bODef.setCode(oldBODef.getCode());
			bODef.setIsMain(BODEF_IS_MAIN.MAIN_YES);       
			saveOrUpdateBODef(bODef, "copy",null,null);
		}else{
			//第一版本
			String suid = idGenerator.getSuid();
			bODef.setId(suid);
			bODef.setVersion(1l);  
			String code = idGenerator.getSuid();
			bODef.setCode(code);
			saveOrUpdateBODef(bODef, "deploy",null,null);
		}
	}

	/**
     * 修改实体
     * @param bODef   对象
     * @return 
     * @exception 
     * @since  1.0.0
     */
	@Override
	public void modify(BODef bODef) {	
		BODef oldBODef = null;
		String defId = bODef.getId();
		//到数据库中是否存在业务对象
		if(BeanUtils.isNotEmpty(defId)){
			oldBODef = bODefDao.get(defId);    
		} 
		//存在则做修改操作
		if(BeanUtils.isNotEmpty(oldBODef)&&BeanUtils.isNotEmpty(oldBODef.getId())){  
			bODef.setId(oldBODef.getId());
			bODef.setVersion(oldBODef.getVersion());
			bODef.setCode(oldBODef.getCode());
			bODef.setIsMain(oldBODef.getIsMain());
			saveOrUpdateBODef(bODef, "update",null,null);
		}	
	}
	

	/**
	 * 增加或者更新完整个业务对象
	 * @param bODef  	
	 * @param type   // deploy or copy  or update or recursion
	 * @param bODefMap   // 保存已做保存操作的业务对象容器（初始化时可以为空）
	 * @param mainBODef 主对象容器  （初始化时可以为空）
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	private void saveOrUpdateBODef(BODef bODef, String type,Map<String,BODef> bODefMap,BODef mainBODef){
		//数据保存顺序（数据库外键有约束）：先保存PackAge、再到BODef、然后公用规则、之后到引用对象再到 属性，最后是属性修改规则  
		if(BeanUtils.isEmpty(bODefMap)){
			bODefMap = new HashMap<String, BODef>();  
		}
		if(BeanUtils.isEmpty(mainBODef)){
			mainBODef = new BODef();
		}
		
		Set<String> setKey = bODefMap.keySet();  
		//业务对象MAP容器中没有对象名称时表示：该对象没有处理
		if(!setKey.contains(bODef.getName())){	
			//默认管理员  (当前用户)
			bODef.setCreateBy("1");       
			bODef.setCreateTime(new Date());   	
			//保存包名 
	    	String cagetory = bODef.getPackage();
	    	BOPackage bp = new BOPackage();
	    	//保存所有包 并 获取得最后一层包对象
	    	if(BeanUtils.isNotEmpty(cagetory)){
	    		bp = createPackage(cagetory);                  
	    	}
	    	bODef.setPackageId(bp.getPackageId());  
	    	
	    	//是否是主版本的
	    	if(BODEF_IS_MASTER.MASTER_YES==bODef.getIsMaster()){
	    		mainBODef = bODef;
	    	}else{
	    		String newId = idGenerator.getSuid();
    			bODef.setId(newId);        
	    		bODef.setPackageId(mainBODef.getPackageId());
	    		bODef.setCode(mainBODef.getCode());
	    		bODef.setPackage(mainBODef.getPackage());
	    		bODef.setVersion(mainBODef.getVersion());
	    		//是发布和发布新版本的话的情况，版本改为主版本
	    		if("deploy".equals(type)||"copy".equals(type)){             	  
	    			bODef.setIsMain(BODEF_IS_MAIN.MAIN_YES);       
		    	}else{
		    		bODef.setIsMain(mainBODef.getIsMain());
		    	}
	    	}
	    	
	    	//保存对象（修改：update，发布新版本对象：copy,发布对象：deploy,递归调用：recursion）;
			if("update".equals(type)){             
				//删除业务实体的对应的附属内容（保留业务实体自己的信息）
				removeBODefOrAtt(bODef.getId(),false);       
	        	bODefDao.update(bODef);                 
	    	}else if("copy".equals(type)){
	    		//当是主表时再做是否是主版本的更新操作 ，并把业务对象之前的所有版本都更新为非主版本
	    		if(BODEF_IS_MASTER.MASTER_YES==bODef.getIsMaster()){   
	    			Map map = new HashMap();
		    		map.put("isMain", BODEF_IS_MAIN.MAIN_NO);
		    		map.put("code", bODef.getCode());
		    		bODefDao.updateBoDefsIsMain(map);         
	    		}
	    		bODefDao.create(bODef);                
	        }else{
	        	bODefDao.create(bODef);                 
	        }
			//放入已做保存操作的业务对象MAP容器
			bODefMap.put(bODef.getName(),bODef);   
			
	    	//保存 对象共用规则
	    	List<BORule> boRuleList = bODef.getBORuleList();
	    	for(BORule boRule : boRuleList){
	    		String brId = idGenerator.getSuid();
	    		boRule.setId(brId);
	    		boRule.setDefId(bODef.getId());
	    		bORuleDao.create(boRule);           
			}		    	
	    	if(BODEF_IS_MASTER.MASTER_YES==bODef.getIsMaster()){
	    		//生成了规则ID的共用规则LIST，再重新设置到主版本中
	    		mainBODef.setBORuleList(boRuleList);     
	    	}
	    	
	    	//保存 对象属性（包括其依赖对象和个性规则）
	    	List<BOAttribute> bOAttributeList = bODef.getBOAttributeList();
	    	for(BOAttribute boAttribute : bOAttributeList){
	    		String baId = idGenerator.getSuid();
	    		boAttribute.setId(baId);
	    		boAttribute.setDefId(bODef.getId());
	    			    		
	    		//处理属性的依赖对象
	    		if(BOATTRIBUTE_TYPE.REF.equals(boAttribute.getType())&&BeanUtils.isNotEmpty(boAttribute.getRefBODef())&&BeanUtils.isNotEmpty(boAttribute.getRefBODef().getName())){   		
	    			BODef refBODef = boAttribute.getRefBODef();
	    			//依赖对象做保存操作
	    			saveOrUpdateBODef(refBODef, "recursion",bODefMap,mainBODef);      
	    			//在保存操作的业务对象MAP容器中获取要依赖的对象
	    			refBODef = bODefMap.get(refBODef.getName());          
	        		boAttribute.setRefId(refBODef.getId());
	    		}	
	    		bOAttributeDao.create(boAttribute);      
	    			
	    		//处理属性的个性规则
	    		List<BORule> attRuleList = boAttribute.getAttRuleList();
	    		for(BORule boRule : attRuleList){
	    			BORuleRef bORuleRef = new BORuleRef();
	    			//依赖规则时就对象的属性个性规则的中间关联表进行设置 （依赖对象在数据中已存在了）  
	    			if(BORULE_BELONG_TYPE.DEF.equals(boRule.getBelongType())){     		
	    				//把依赖的规则ID设置到中间表对象中
	    				List<BORule> mainBoRuleList = mainBODef.getBORuleList();
	    				for (BORule mainBoRule : mainBoRuleList){
	    					if(boRule.getName().equals(mainBoRule.getName())){     //找到主业务对象中公共规则的对应规则内容
	    						bORuleRef.setRuleId(mainBoRule.getId()); 
	    						break;
	    					}  
						}	      
	    			//不是依赖规则时就保存对象的属性个性规则
	    			}else{                        
	        			String brId = idGenerator.getSuid();
		        		boRule.setId(brId);
		        		boRule.setAttrId(baId);
		        		boRule.setDefId(bODef.getId());
	        			bORuleDao.create(boRule); 
	        			//把对象的属性个性规则的ID保存到中间表对象
		        		bORuleRef.setRuleId(brId);
	        		}
	    			//保存对象的属性个性规则的中间表内容 
	    			String brfId = idGenerator.getSuid();
	        		bORuleRef.setId(brfId); 
	        		bORuleRef.setAttrId(baId);
	    			bORuleRefDao.create(bORuleRef);  //保存规则中间表对象
				}
			}       		    	
		}	
	}
	
	private BOPackage createPackage(String cagetory) {
		//执行 实体对象路径保存
		String []  strs = cagetory.split("\\.");
		String path ="";
		BOPackage bp = new BOPackage();
		for (int i = 0; i < strs.length; i++){
			String name = strs[i];
			path += "."+name;
			//去掉前面的 "."
			if(i==0){
				path = path.substring(1, path.length());         
			}
			Map map = new HashMap();     
			map.put("name", name);
			//查询节点是否已经存在
			if(i>0){
				map.put("parentId", bp.getPackageId());  
			}else{
				map.put("depth", 1);      
			}	
			BOPackage oldBp = bOPackageDao.getBOPackageByMap(map);	
			
			//处理包的保存
			BOPackage newBp = new BOPackage();
			if(BeanUtils.isNotEmpty(oldBp)&&BeanUtils.isNotEmpty(oldBp.getId())){
				bp = oldBp;		  
			}else{	
				String suid = idGenerator.getSuid();
				newBp.setPackageId(suid);
				newBp.setDepth((long) (i+1));   
				//默认为管理工作员
				newBp.setCreateBy("1");     
				newBp.setCreateTime(new Date());
				newBp.setName(name);    
				newBp.setPath(path);      
				if(i>0&&BeanUtils.isNotEmpty(bp.getPackageId())){
					newBp.setParentId(bp.getPackageId());
				}
				//状态。inactive=未激活；actived=激活(默认)；forbidden=禁用
				newBp.setStatus(BOPACKAGE_STATUS.ACTIVED);    
				bOPackageDao.create(newBp);	     
				bp = newBp;   
			}
		}
		return bp;
	}

}
