
package com.hotent.demo.dao.ztree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.uuid.impl.UUIDUtil;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.demo.model.ztree.SysZtree;
import com.hotent.platform.model.system.GlobalType;

@Repository
public class SysZtreeDao extends BaseDao<SysZtree>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysZtree.class;
	}

	public SysZtree getTreeTypeByAlias(String alias) {
		return this.getUnique("getTreeTypeByAlias",alias);
	}

	public List<GlobalType> getTreeDatas(Map<String, Object> params) {
		SysZtree sysZtree=(SysZtree) params.get("sysZtree");
		String sql="";
		String idKey=sysZtree.getIdkey();
		String pIdKey=sysZtree.getPidkey();
		String id=(String) params.get("id");
		String dataParams=getReturnParmas(sysZtree.getDataparams());
		String tableName=sysZtree.getTablename();
		Long expandsLevel=sysZtree.getExpandslevel(); 
		String rootPId=sysZtree.getRootpid();
		String rootTitle=sysZtree.getRoottitle();
		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		// 异步 
		if (StringUtil.isEmpty(id)) { // 第一次加载
			String startOption=StringUtil.isNotEmpty(rootPId)?idKey+" = "+rootPId:" a."+pIdKey+" not in (select o."+idKey+" from "+tableName+" o ) ";
			sql = "select " + dataParams + " level+1 depth," +
					"(select count(*) from "+tableName+" t where t."+pIdKey+"=a."+idKey+")  childNodes" +
					" from " + tableName
					+ " a start with " + startOption
					+ " connect by prior " + idKey + " = " + pIdKey
					+ " and level < " + expandsLevel;
			List<Map<String, Object>> lists=jdbcTemplate.queryForList(sql);
			List<GlobalType> treeNodes=new ArrayList<GlobalType>();
			Long rootID=new Random().nextLong();
			for(Map<String, Object> m : lists){
				GlobalType globalType =(GlobalType)BeanUtils.convertMapToJavaBean(GlobalType.class, m);
				int rootDepth=StringUtil.isEmpty(globalType.getNodePath())?2:1;
				if(globalType.getDepth().intValue()==rootDepth){
					globalType.setParentId(rootID);
				}
				treeNodes.add(globalType);
			}
			GlobalType globalType=new GlobalType();
			globalType.setTypeName(rootTitle);
			globalType.setTypeId(rootID);
			globalType.setParentId(null); 
			globalType.setIsParent("true");
			treeNodes.add(0, globalType);
			return treeNodes;
		} else { // 第N次加载
			sql = "select " + dataParams + "" +
					"(select count(*) from "+tableName+" t where t."+pIdKey+"=a."+idKey+")  childNodes" +
					" from " + tableName
					+ " a where " + pIdKey + " = " + id ;
			List<Map<String, Object>> lists=jdbcTemplate.queryForList(sql);
			List<GlobalType> treeNodes=new ArrayList<GlobalType>();
			for(Map<String, Object> m : lists){
				GlobalType globalType =(GlobalType)BeanUtils.convertMapToJavaBean(GlobalType.class, m);
				treeNodes.add(globalType);
			}
			return treeNodes;
		}
	}
	
	private String getReturnParmas(String dataParams){
		JSONObject params = JSONObject.fromObject(dataParams.replaceAll("''", "\""));
		Iterator<?> it = params.keys();  
		StringBuffer sb= new StringBuffer();
        while (it.hasNext()) {  
    	    String key = (String) it.next();  
            String value = params.getString(key);  
            sb.append("a."+value+" ");
            sb.append(key+",");
        }
		return sb.toString();
	}
	

}
