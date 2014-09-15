package com.hotent.core.table.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.core.table.ITableOperator;
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.platform.model.form.BpmFormTableIndex;

public class JDBCTest {
	static String url = "jdbc:sqlserver://localhost:1433;databaseName=bpm"; // 数据库名pubs
	static String cls = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String user = "sa";
	static String password = "root"; // 与sa用户对应的密码,(这是我机子上与sa用户对应的密码.)
	static Connection conn;

//	@BeforeClass
	public static void init() {
		try {
			Class.forName(cls).newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Test
	public void test_select() {
		String tableName="test";
		String indexName="idx";
		String sql = "select IDX.NAME as index_name,IDX.TYPE as index_type,"
		+"OBJ.NAME as index_table,OBJ.TYPE as table_type, "
		+"IDX.is_disabled as index_status,IDX.IS_UNIQUE as is_unique, "
		+"IDX.IS_PRIMARY_KEY as is_pk_index, "
		+"col.name as column_name "
	+"from  sys.indexes  IDX  "
		+"JOIN SYS.OBJECTS OBJ ON IDX.object_id=OBJ.OBJECT_ID  "
		+"join sys.index_columns idc on obj.object_id=idc.object_id and idx.index_id=idc.index_id " 
		+"join sys.columns col ON col.object_id=idc.OBJECT_ID and col.column_id = idc.column_id "
	+"WHERE 1=1 AND IDX.NAME LIKE '%"+indexName+"%' AND OBJ.NAME LIKE '%"+tableName+"%' ";
		System.out.println(sql);
		try {
			Statement statement=conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int n = rs.getMetaData().getColumnCount();
			for(int i=0;i<n;i++){
				System.out.println(rs.getMetaData().getColumnName(i+1));
			}
			 while(rs.next())   {
				   System.out.println(rs.getString(1));
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	protected void displayColumnName(ResultSet rs) throws Exception{
		int n = rs.getMetaData().getColumnCount();
		for(int i=0;i<n;i++){
			System.out.println(rs.getMetaData().getColumnName(i+1));
		}
	}
	@Test
	public void ddd(){
		String str = EncryptUtil.encryptSha256("123");
		System.out.println(str);
		str = EncryptUtil.encryptSha256("1234");
		System.out.println(str);
		str = EncryptUtil.encryptSha256("123456");
		System.out.println(str);
		str = EncryptUtil.encryptSha256("1111");
		System.out.println(str);
		str = EncryptUtil.encryptSha256("ad");
		System.out.println(str);
		str = EncryptUtil.encryptSha256("admin");
		System.out.println(str);
		
	}
}
