package com.hotent.core.util;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.BaseTestCase;
import com.hotent.core.db.JdbcHelper;

public class testJdbc extends BaseTestCase {
	
	@Resource
	private JdbcTemplate  jdbcTemplate;
	

	//@org.junit.Test
	public void testJdbc() throws Exception{
		JdbcHelper jdbcHelper=JdbcHelper.getInstance();
		jdbcHelper.init("oracle", "oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@192.168.1.8:1521:orcl", "testcms", "testcms");
		String sql="insert into test (name,createdate,age) values ('zhangyg', to_date('2004-05-07','yyyy-mm-dd hh24:mi:ss'),'33')";

		jdbcHelper.execute(sql, new Object[]{});
		
		String sql1="insert into test (name,createdate,age) values (?, ?,?)";
		Object[] obj={"老李",new Date(),"33"};
		jdbcHelper.execute(sql1, obj);
	}
	
	@org.junit.Test
	public void queryForMap(){
		String sql="select * from bpm.sys_role where roleId=1324008410326";
		Map<String,Object> map=jdbcTemplate.queryForMap(sql);
		System.out.println(map.size());
	}
	
	

}
