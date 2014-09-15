package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import com.hotent.BaseTestCase;
import com.hotent.core.ldap.dao.LdapGroupDao;
import com.hotent.core.ldap.dao.LdapOrganizationDao;
import com.hotent.core.ldap.dao.LdapUserDao;
import com.hotent.core.ldap.model.LdapGroup;
import com.hotent.core.ldap.model.LdapOrganization;
import com.hotent.core.ldap.model.LdapUser;

public class LdapTest extends BaseTestCase{
	
	@Resource
	LdapUserDao ldapUserDao;
	@Resource
	LdapGroupDao ldapGroupDao;
	@Resource
	LdapOrganizationDao ldapOrganizationDao;
	
	@org.junit.Test
	public void getUsers(){
		List<LdapUser> list=ldapUserDao.get();
		for(LdapUser user:list){
			System.out.println(user.getsAMAccountName() + ",mobile:" + user.getTelephoneNumber() +"," +user.getHomePhone()  );
		}
		
		System.out.println("ok");
		//Assert.assertTrue(list.size()>0);
		
		
	}
	
	
	public void getGroup(){
		List<LdapGroup> list=ldapGroupDao.getAll();
		for(LdapGroup group:list){
			if(group.getName().equals("Users")){
				String[] members= group.getMembers();
				System.out.println("getGroup");
			}
			
			System.out.println(group.getName());
		}
	}
	
	
	public void getOrg(){
		List<LdapOrganization> orgList= ldapOrganizationDao.getAll();
		for(LdapOrganization group:orgList){
			
			System.out.println(group.getName() +"," + group.getDistinguishedName());
		}
		System.out.println(orgList.size());
	}
	

}
