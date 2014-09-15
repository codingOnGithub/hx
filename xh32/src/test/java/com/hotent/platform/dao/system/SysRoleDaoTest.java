package com.hotent.platform.dao.system;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-testdb.xml"})
public class SysRoleDaoTest {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void testAdd(){
		long start=System.currentTimeMillis();
		for(int i=1;i<100000;i++){
			String sql="insert into ORDERS (id,NAME) values (" +i +",'商品订单')";
			String sqlItem="insert into ORDERITEM (ORDERID,NAME,AMOUNT) values (" +i +",'商品订单',10)";
			
			jdbcTemplate.update(sql);
			jdbcTemplate.update(sqlItem);
		}
		long end=System.currentTimeMillis();
		
		System.out.println(end-start);
		
		
	}

}
