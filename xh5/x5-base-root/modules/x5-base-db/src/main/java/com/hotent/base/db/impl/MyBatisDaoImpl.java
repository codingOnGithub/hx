package com.hotent.base.db.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import com.hotent.base.api.BaseModel;
import com.hotent.base.api.Page;
import com.hotent.base.api.query.FieldSort;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.core.model.AbstractModel;
import com.hotent.base.db.mybatis.domain.DefaultPage;

/**
 * 对象功能:MyBatis 数据库访问操作基础类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2013-07-30
 */
public abstract class MyBatisDaoImpl<PK extends Serializable,T extends Serializable> extends AbstractDaoImpl<PK,T>{

    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
    
//    @Resource
//    protected IOnlineContext loginContext;

    /**
     * 按ID获取单一记录
     */
    protected final static String OP_GET=".get";
    /**
     * 按业务主键获取单一记录
     */
    protected final static String OP_GET_BY_ENTITY_KEY=".getByEntityKey";    
    /**
     * 按ID删除记录
     */
    protected final static String OP_DEL=".remove";
    /**
     * 按ID更新记录
     */
    protected final static String OP_UPD=".update";
    /**
     * 添加记录
     */
    protected final static String OP_CREATE=".create";
    /**
     * 查询记录列表
     */
    protected final static String OP_QUERY=".query";
    
    /**
     * 返回当前实体的命名空间字符串名称
     */
    public abstract String getNamespace();
    /*
     * (non-Javadoc)
     * @see com.hotent.base.db.api.Dao#create(java.lang.Object)
     */
    public void create(T entity) {
        if(entity instanceof AbstractModel){
        	@SuppressWarnings("unchecked")
			AbstractModel<String> model = (AbstractModel<String>)entity;
            if(model.getId()==null){
                model.setId(idGenerator.getSuid());
            }
            if(model.getCreateTime()==null){
            	model.setCreateTime(new java.util.Date());
            }
        }
        sqlSessionTemplate.insert(getNamespace() + OP_CREATE, entity);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.core.db.IDao#update(java.lang.Object)
     */
    public void update(T entity) {
        if(entity instanceof BaseModel){
            BaseModel model = (BaseModel)entity;
            model.setUpdateTime(new java.util.Date());
//            if(loginContext!=null){                
//                model.setUpdateBy(loginContext.getUserAccount());
//            }
        }        
        sqlSessionTemplate.update(getNamespace() + OP_UPD, entity);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.db.api.Dao#remove(java.io.Serializable)
     */
    public void remove(PK entityId) {
         sqlSessionTemplate.delete(getNamespace() + OP_DEL, entityId);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.db.api.Dao#get(java.io.Serializable)
     */
    public T get(PK entityId) {
        @SuppressWarnings("unchecked")
		T selectOne = (T)sqlSessionTemplate.selectOne(getNamespace() + OP_GET,entityId);
		return selectOne;
    }    
    
    /*
     * (non-Javadoc)
     * @see com.hotent.core.db.IDao#removeByIds(PK[])
     */
    public void removeByIds(PK... ids) {
        if(ids!=null){
            for(PK pk:ids){
                remove(pk);
            }
        }
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.db.api.Dao#getAll()
     */
    public List<T> getAll() {
        return sqlSessionTemplate.selectList(getNamespace() + OP_QUERY, null);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.db.api.Dao#getAllByPage(com.hotent.base.api.Page)
     */
    public List<T> getAllByPage(Page page){
    	DefaultPage p=( DefaultPage)page;
        return sqlSessionTemplate.selectList(getNamespace() + OP_QUERY, null,p);
    }
    /*
     * (non-Javadoc)
     * @see com.hotent.base.db.api.Dao#query(com.hotent.base.api.query.QueryFilter)
     */
    public List<T> query(QueryFilter queryFilter) {
    	Map<String,Object> params=queryFilter.getParams();
    	//构建动态条件SQL
    	String dynamicWhereSql=queryFilter.getFieldLogic().getSql();
    	if(StringUtils.isNotEmpty(dynamicWhereSql)){
    		params.put("whereSql", dynamicWhereSql);
    	}
    	//构建排序SQL
    	if(queryFilter.getFieldSortList().size()>0){
	    	StringBuffer sb=new StringBuffer();
			for(FieldSort fieldSort: queryFilter.getFieldSortList()){
				sb.append(fieldSort.getField()).append(" ").append(fieldSort.getDirection()).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
			params.put("orderBySql", sb.toString());
    	}
    	if(queryFilter.getPage()==null){
            return sqlSessionTemplate.selectList(getNamespace() + OP_QUERY, params);	
    	}else{
    		DefaultPage p=new DefaultPage(new RowBounds(queryFilter.getPage().getStartIndex(), queryFilter.getPage().getPageSize()));
    		return sqlSessionTemplate.selectList(getNamespace() + OP_QUERY, params,p);
    	}
    }
    /**
     * 根据在Map配置文件中的Sql Key及参数获取唯一实体
     * @param sqlKey Mapping文件的sql id
     * @param params 参数集合
     * @return 有唯一值则返回一个实体，无值返回null，结果大于1，则抛错。
     */
    public T getUnique(String sqlKey,Map<String,Object> params){
    	return (T) sqlSessionTemplate.selectOne(sqlKey, params);
    	
    }    
    /**
     * 根据在Map配置文件中的Sql Key及参数获取唯一实体
     * @param sqlKey Mapping文件的sql id
     * @param param 参数
     * @return 有唯一值则返回一个实体，无值返回null，结果大于1，则抛错。
     */
    public T getUnique(String sqlKey,Object params){
    	return (T) sqlSessionTemplate.selectOne(sqlKey, params);
    	
    }
    
	/**
	 * 返回单条数据，如 select count(*) from table_a 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	public Object getOne(String sqlKey,Object params)
	{
		return sqlSessionTemplate.selectOne(getNamespace() + "." + sqlKey, params);
	}
    
    /**
     * 根据在Map配置文件中的Sql Key及参数获取实列表
     * @param sqlKey
     * @param params
     * @return
     */
    public List<T> getByKey(String sqlKey,Map<String,Object> params){
    	return sqlSessionTemplate.selectList(getNamespace() + "." + sqlKey , params);
    }
    /**
     * 根据在Map配置文件中的Sql Key及参数分页获取实列表
     * @param sqlKey
     * @param params
     * @param page
     * @return
     */
    public List<T> getByKey(String sqlKey,Map<String,Object> params,Page page){
    	return sqlSessionTemplate.selectList(getNamespace() + "." + sqlKey , params,new RowBounds(page.getStartIndex(), page.getPageSize()));
    }
    
    
    /**
     * 根据在Map配置文件中的Sql Key及参数获取实列表
     * @param sqlKey
     * @param page
     * @return
     */
    public List<T> getByKey(String sqlKey,Object param){
    	return sqlSessionTemplate.selectList(getNamespace() + "." + sqlKey , param);
    }
    
    
    /**
     * 根据在Map配置文件中的Sql Key及参数分页获取实列表
     * @param sqlKey
     * @param param
     * @param page
     * @return
     */
    public List<T> getByKey(String sqlKey,Object param,Page page){
    	return sqlSessionTemplate.selectList(getNamespace() + "." + sqlKey , param,new RowBounds(page.getStartIndex(), page.getPageSize()));
    }
    
    /**
     * 根据在Map配置文件中的Sql Key及参数更新数据
     * @param sqlKey
     * @param params
     */
    public void updateByKey(String sqlKey,Map<String,Object> params){
    	sqlSessionTemplate.update(getNamespace() + "." + sqlKey, params);
    	
    }
    
    /**
     * 根据在Map配置文件中的Sql Key及参数更新数据
     * @param sqlKey
     * @param params
     */
    public void updateByKey(String sqlKey,Object params){
    	sqlSessionTemplate.update(getNamespace() + "." + sqlKey, params);
    	
    }
    
    /**
     * 根据在Map配置文件中的Sql Key更新数据
     * @param sqlKey
     */
    public void updateByKey(String sqlKey){
    	sqlSessionTemplate.update(getNamespace() + "." + sqlKey);
    }
    /**
     * 根据在Map配置文件中的Sql Key及参数删除数据
     * @param sqlKey
     * @param params
     */
    public void deleteByKey(String sqlKey,Map<String,Object> params){
    	sqlSessionTemplate.delete(getNamespace() + "." + sqlKey, params);
    }
    
    /**
     * 根据在Map配置文件中的Sql Key及参数删除数据
     * @param sqlKey
     * @param params
     */
    public void deleteByKey(String sqlKey,Object params){
    	sqlSessionTemplate.delete(getNamespace() + "." + sqlKey, params);
    }
    
    /**
     * 根据在Map配置文件中的Sql Key删除数据
     * @param sqlKey
     */
    public void deleteByKey(String sqlKey){
    	sqlSessionTemplate.delete(getNamespace() + "." + sqlKey);
    }
    protected Map<String,Object> buildMap(String paramName,Object paramValue){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(paramName, paramValue);
        return map;
    }
    /**
     * 获得一个MapBuilder，通过它快速添加参数。
     * @return
     */
    protected MapBuilder buildMapBuilder() {    	
		return new MapBuilder();
	}
    /**
     * <pre> 
     * 描述：使用例子：buildMapBuilder().addParam("procDefId",procDefId).addParam("nodeId",nodeId).getParams()
     * 构建组：x5-base-db
     * 作者：winston yan
     * 邮箱:yancm@jee-soft.cn
     * 日期:2014-1-18-上午10:47:36
     * 版权：广州宏天软件有限公司版权所有
     * </pre>
     */
    protected class MapBuilder{
    	Map<String, Object> map = new HashMap<String, Object>();
    	protected MapBuilder(){    		
    	}
    	public MapBuilder addParam(String paramKey,String paramValue){
    		map.put(paramKey, paramValue);
    		return this;
    	}
    	public Map<String, Object> getParams(){
    		return map;
    	}
    }
}
