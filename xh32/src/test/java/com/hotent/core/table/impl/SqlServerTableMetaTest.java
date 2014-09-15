package com.hotent.core.table.impl;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.platform.model.system.SysDataSource;

public class SqlServerTableMetaTest {

	static SqlServerTableMeta sqlServerTableMeta;

	@BeforeClass
	public static void init() {
		sqlServerTableMeta = new SqlServerTableMeta();

		SysDataSource dataSource = new SysDataSource();
		dataSource.setAlias("sqlserver2005");
		dataSource.setDbType("sql2005");
		dataSource
				.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://localhost:1433;databasename=test");
		dataSource.setUserName("sa");
		dataSource.setPassword("tak6995227");

		try {
			sqlServerTableMeta.setDataSource(dataSource);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	// bug
	public void testGetTableByName() {
		TableModel tableModel = sqlServerTableMeta.getTableByName("table1");
		System.out.println(tableModel.getName());
		List<ColumnModel> cols = tableModel.getColumnList();
		for (ColumnModel col : cols) {
			System.out.println(col.getName() + "|" + col.getColumnType()+"|"+col.getIsNull());
		}
	}

	// @Test
	public void testGetTablesByName() {
		Map<String, String> map = sqlServerTableMeta.getTablesByName("table1");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
	}

	// @Test
	// public void testGetColumnsByTableName(){
	// List<ColumnModel> cols =
	// sqlServerTableMeta.getColumnsByTableName("table1");
	// for(ColumnModel col:cols){
	// System.out.println(col.getName()+"|"+col.getColumnType());
	// }
	// }

}
