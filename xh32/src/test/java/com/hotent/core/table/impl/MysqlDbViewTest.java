package com.hotent.core.table.impl;

import com.hotent.core.table.impl.MysqlDbView;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.platform.model.system.SysDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-testdb.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class MysqlDbViewTest {
	
//	@Resource
	static MysqlDbView mysqlDbView;
	
	@BeforeClass
	public static void init(){
		mysqlDbView=new MysqlDbView();
		SysDataSource dataSource=new SysDataSource();
		dataSource.setAlias("MySQL");
		dataSource.setDbType("mysql");
		dataSource.setDriverName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setUserName("root");
		dataSource.setPassword("root");
		
		try {
			mysqlDbView.setDataSource(dataSource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetType() {
		assertNotNull(mysqlDbView);
//		fail("Not yet implemented");
		try {
			List<String> views = mysqlDbView.getViews("mEss");
			for(String view:views){
				System.out.println(view);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetViews() {
		String type=mysqlDbView.getType("date");
		System.out.println(type);
	}

}
