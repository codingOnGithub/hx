package com.hotent.core.table;

import com.hotent.core.table.ColumnModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;


import com.hotent.BaseTestCase;



public class MetaDataTest extends BaseTestCase {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@org.junit.Test
	public void getColumns() throws SQLException{
		Connection conn= jdbcTemplate.getDataSource().getConnection();
		
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from V_UserRole");
			ResultSetMetaData metadata=rs.getMetaData();
		
			//System.out.print( metadata.getColumnCount());
			int count=metadata.getColumnCount();
			for(int i=1;i<=count;i++){
				System.out.println( metadata.getColumnName(i) + ","  + metadata.getColumnTypeName(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static String getType(String type){

		type=type.toLowerCase();
		if(type.indexOf("number")>-1 )
			return ColumnModel.COLUMNTYPE_NUMBER;
		else if(type.indexOf("date")>-1){
			return ColumnModel.COLUMNTYPE_DATE;
		}
		else if(type.indexOf("char")>-1){
			return ColumnModel.COLUMNTYPE_VARCHAR;
		}
		return ColumnModel.COLUMNTYPE_VARCHAR;

	}

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@192.168.1.8:1521:orcl";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(url, "bpmx3", "bpmx3");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from v_userrole");
			ResultSetMetaData metadata=rs.getMetaData();
		
			//System.out.print( metadata.getColumnCount());
			int count=metadata.getColumnCount();
			for(int i=1;i<=count;i++){
				System.out.println( metadata.getColumnName(i) +"," +  getType(metadata.getColumnTypeName(i)) + "," + metadata.getColumnTypeName(i));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
