package com.hotent.base.db.jdbc;

import org.junit.Before;
import org.junit.Test;

public class JdbcHelperTest {

	private  JdbcHelper<?, ?> jdbcHelper;
	
	@Before
	public   void init() throws Exception{
//		dataSource=new DefaultDataSource();
//		dataSource.setAlias("MySQL");
//		dataSource.setDbType("mysql");
//		dataSource.setDriverName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/x5");
//		dataSource.setUserName("root");
//		dataSource.setPassword("000");

		String alias = "alias";
		String className ="com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/x5";
		String user = "root";
		String pwd = "000";
		jdbcHelper = JdbcHelper.getInstance();
		jdbcHelper.init(alias, className, url, user, pwd);
	}
	
	
	
	@Test
	public void execute() {
//		String sql = "update  test_table set test2='测试'  where id=1";
//		
//		int i = jdbcHelper.execute(sql);
		
		String sql2 = "INSERT INTO test_table(id, test2, test4, test5) VALUES(10, '测试2', 'xxxxx', '2014-01-25 10:24:14')";
		 jdbcHelper.execute(sql2);
		
	}
}
