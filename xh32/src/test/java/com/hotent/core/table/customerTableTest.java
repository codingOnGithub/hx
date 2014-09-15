package com.hotent.core.table;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.ITableOperator;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:conf/app-testdb.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class customerTableTest  {

	@Resource
	private ITableOperator tableOperator;

	@Test
	public void customerTable() throws SQLException{
	
		//		创建表 
		ColumnModel cm = new ColumnModel();
		cm.setName("test_id");
		cm.setIsPk(true);
		cm.setComment("测试ID");
		cm.setColumnType(ColumnModel.COLUMNTYPE_INT);
		cm.setIntLen(18);
		List<ColumnModel> columnList = new ArrayList<ColumnModel>();
		columnList.add(cm);
		TableModel tm = new TableModel();
		tm.setName("test01");
		tm.setComment("测试表");
		tm.setColumnList(columnList);
		tableOperator.createTable(tm);
//		ColumnModel cm = new ColumnModel();
//		cm.setName("test_id");
//		cm.setPk(true);
//		cm.setComment("测试ID");
//		cm.setColumnType(ColumnModel.COLUMNTYPE_INT);
//		cm.setIntLen(18);
//		List<ColumnModel> columnList = new ArrayList<ColumnModel>();
//		columnList.add(cm);
//		TableModel tm = new TableModel();
//		tm.setName("test01");
//		tm.setComment("测试表");
//		tm.setColumnList(columnList);
//		tableOperator.createTable(tm);
//		ColumnModel cm = new ColumnModel();
//		cm.setName("test_id");
//		cm.setPk(true);
//		cm.setComment("测试ID");
//		cm.setColumnType(ColumnModel.COLUMNTYPE_INT);
//		cm.setIntLen(18);
//		List<ColumnModel> columnList = new ArrayList<ColumnModel>();
//		columnList.add(cm);
//		TableModel tm = new TableModel();
//		tm.setName("test01");
//		tm.setComment("测试表");
//		tm.setColumnList(columnList);
//		tableOperator.createTable(tm);
		
		//		创建有键的表
//		ColumnModel cm = new ColumnModel();
//		cm.setName("test2_id");
//		cm.setPk(true);
//		cm.setComment("测试ID");
//		cm.setColumnType(ColumnModel.COLUMNTYPE_INT);
//		cm.setIntLen(18);
//		
//		ColumnModel cm2 = new ColumnModel();
//		cm2.setName("test_id");
//		cm2.setComment("外键ID");
//		cm2.setFk(true);
//		cm2.setFkRefTable("test01");
//		cm2.setFkRefColumn("test_id");
//		cm2.setColumnType(ColumnModel.COLUMNTYPE_INT);
//		cm2.setIntLen(18);
//		
//		List<ColumnModel> columnList = new ArrayList<ColumnModel>();
//		columnList.add(cm);
//		columnList.add(cm2);
//		
//		TableModel tm = new TableModel();
//		tm.setName("test02");
//		tm.setComment("测试表2");
//		tm.setColumnList(columnList);
//		tableOperator.createTable(tm);
		
		//		更新表注释
//		tableOperator.updateTableComment("test01", "表注释已更新");
		
		//		添加字段
//		ColumnModel cm = new ColumnModel();
//		cm.setName("test_04");
//		cm.setComment("测试添加列");
//		cm.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
//		cm.setCharLen(1024);
//		tableOperator.addColumn("test01", cm);
		
		//		修改列
//		ColumnModel cm = new ColumnModel();
//		cm.setName("test_06");
//		cm.setComment("修改列时修改了注释2");
//		cm.setColumnType(ColumnModel.COLUMNTYPE_INT);
//		cm.setIntLen(18);
//		tableOperator.updateColumn("test01", "test_05", cm);
		
	}
	
}