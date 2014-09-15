package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysOrgRoleManageDao;
import com.hotent.platform.model.system.SysOrgRole;
import com.hotent.platform.model.system.SysUser;

public class SysOrgServiceTestCase extends BaseTestCase{
	@Resource
	private SysOrgDao sysOrgDao;
	
	@Resource
	private SysOrgRoleManageDao sysOrgRoleManageDao;
	
	public void getAssignRoleByOrgId()
	{
		List<SysOrgRole> list= sysOrgRoleManageDao.getAssignRoleByOrgId(1343786700554L);
		System.out.println(list.size());
	}
	
	@org.junit.Test
	public void getClassInfo(){
		String a = SysUser.class.getName();
		System.out.println(a);
	}
	
	
//	public void testCase(){
//
//		for(int i=0;i<=2;i++){
//			List<ISysOrg> sysOrgList=sysOrgDao.getByDepth(i);
//			for(ISysOrg sysOrg:sysOrgList){
//				if(sysOrg.getOrgId()==0){
//					sysOrg.setPath("-1.0.");
//					sysOrgDao.update(sysOrg);
//				}else if( sysOrg.getOrgSupId()!=null){
//					ISysOrg orgSup=sysOrgDao.getById(sysOrg.getOrgSupId());
//					sysOrg.setPath(orgSup.getPath() + sysOrg.getOrgId() + ".");
//					sysOrgDao.update(sysOrg);
//				}
//			}
//		}
//	}
	
	
}
