package com.hotent.platform.dao.system;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import com.hotent.BaseTestCase;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.Identity;

public class IdentityDaoTest extends BaseTestCase {
	
	@Resource
	IdentityDao identityDao;
	
	@Test
	public void getAll(){
		List<Identity> list=identityDao.getAll();
		for(Identity identity:list){
			System.out.println("getAll:" +identity.getName());
		}
	}
	
	
	//@Test
	public void getByAlias(){
		Identity rtn=identityDao.getByAlias("order");
		System.out.println(rtn.getName());
	}
	@Test
	public void testCalendar() {
		Calendar now = Calendar.getInstance();
		String year = now.get(Calendar.YEAR) + "";
		int month = now.get(Calendar.MONTH);//从0开始计算
		int day = now.get(Calendar.DATE);
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
	}
	
	
	public void add() throws Exception{
		Identity identity=new Identity();
		identity.setId(UniqueIdUtil.genId());
		identity.setAlias("DHD");
		identity.setCurDate("20120303");
		
		identity.setGenType((short) 1);
		identity.setInitValue(1);
		identity.setName("订货单");
		identity.setNoLength(5);
		identity.setRule("DHD_{yyyy}{MM}{dd}_{NO}}");
		identity.setStep((short)1);
		
		
		identityDao.add(identity);
		System.out.println("add");
	}
	

}
