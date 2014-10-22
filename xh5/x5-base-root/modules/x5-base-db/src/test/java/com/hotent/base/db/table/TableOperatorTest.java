package com.hotent.base.db.table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.base.api.db.ITableOperator;
import com.hotent.base.api.db.model.Column;
import com.hotent.base.db.DbBaseTest;
import com.hotent.base.db.table.model.DefaultColumn;
import com.hotent.base.db.table.model.DefaultTable;

/**
 * 表操作测试类
 * 
 * @author hugh zhuang
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TableOperatorTest extends DbBaseTest {
	// logger
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ITableOperator tableOperator;

	@Test
	// @Rollback
	public void createTable() throws SQLException {

		// 创建简单表 TestOK
		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test_id");
		// cm.setIsPk(true);
		// cm.setComment("测试ID");
		// cm.setColumnType(Column.COLUMN_TYPE_INT);
		// cm.setIntLen(18);
		// List<Column> columnList = new ArrayList<Column>();
		// columnList.add(cm);
		//
		// DefaultTable tm = new DefaultTable();
		// tm.setName("test01");
		// tm.setComment("测试表");
		// tm.setColumnList(columnList);
		// tableOperator.createTable(tm);

		// ============测试各种类型 TestOK===================
		List<Column> columnList1 = new ArrayList<Column>();
		DefaultColumn cm1 = new DefaultColumn();
		// 主键
		cm1.setName("id");
		cm1.setIsPk(true);
		cm1.setComment("测试ID");
		cm1.setColumnType(Column.COLUMN_TYPE_INT);
		cm1.setIntLen(18);
		columnList1.add(cm1);

		// 字符串
		DefaultColumn cm2 = new DefaultColumn();
		cm2.setName("test2");
		cm2.setComment("字符串");
		cm2.setColumnType(Column.COLUMN_TYPE_VARCHAR);
		cm2.setCharLen(128);
		columnList1.add(cm2);
		// 数字
		DefaultColumn cm3 = new DefaultColumn();
		cm3.setName("test3");
		cm3.setComment("数字");
		cm3.setColumnType(Column.COLUMN_TYPE_NUMBER);
		cm3.setIntLen(5);
		cm3.setDecimalLen(3);

		DefaultColumn cm4 = new DefaultColumn();
		cm4.setName("test4");
		cm4.setComment("大文本");
		cm4.setColumnType(Column.COLUMN_TYPE_CLOB);
		columnList1.add(cm4);

		DefaultColumn cm5 = new DefaultColumn();
		cm5.setName("test5");
		cm5.setComment("日期");
		cm5.setColumnType(Column.COLUMN_TYPE_DATE);
		columnList1.add(cm5);

		DefaultTable tm1 = new DefaultTable();
		tm1.setName("test_table");
		tm1.setComment("测试表");
		tm1.setColumnList(columnList1);
		tableOperator.createTable(tm1);

		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test_id");
		// cm.setPk(true);
		// cm.setComment("测试ID");
		// cm.setColumnType(DefaultColumn.COLUMNTYPE_INT);
		// cm.setIntLen(18);
		// List<DefaultColumn> columnList = new ArrayList<DefaultColumn>();
		// columnList.add(cm);
		// DefaultTable tm = new DefaultTable();
		// tm.setName("test01");
		// tm.setComment("测试表");
		// tm.setColumnList(columnList);
		// tableOperator.createTable(tm);
		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test_id");
		// cm.setPk(true);
		// cm.setComment("测试ID");
		// cm.setColumnType(DefaultColumn.COLUMNTYPE_INT);
		// cm.setIntLen(18);
		// List<DefaultColumn> columnList = new ArrayList<DefaultColumn>();
		// columnList.add(cm);
		// DefaultTable tm = new DefaultTable();
		// tm.setName("test01");
		// tm.setComment("测试表");
		// tm.setColumnList(columnList);
		// tableOperator.createTable(tm);

		// 创建有键的表
		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test2_id");
		// cm.setPk(true);
		// cm.setComment("测试ID");
		// cm.setColumnType(DefaultColumn.COLUMNTYPE_INT);
		// cm.setIntLen(18);
		//
		// DefaultColumn cm2 = new DefaultColumn();
		// cm2.setName("test_id");
		// cm2.setComment("外键ID");
		// cm2.setFk(true);
		// cm2.setFkRefTable("test01");
		// cm2.setFkRefColumn("test_id");
		// cm2.setColumnType(DefaultColumn.COLUMNTYPE_INT);
		// cm2.setIntLen(18);
		//
		// List<DefaultColumn> columnList = new ArrayList<DefaultColumn>();
		// columnList.add(cm);
		// columnList.add(cm2);
		//
		// DefaultTable tm = new DefaultTable();
		// tm.setName("test02");
		// tm.setComment("测试表2");
		// tm.setColumnList(columnList);
		// tableOperator.createTable(tm);

		// 更新表注释
		// tableOperator.updateTableComment("test01", "表注释已更新");

		// 添加字段
		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test_04");
		// cm.setComment("测试添加列");
		// cm.setColumnType(DefaultColumn.COLUMNTYPE_VARCHAR);
		// cm.setCharLen(1024);
		// tableOperator.addColumn("test01", cm);

		// 修改列
		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test_06");
		// cm.setComment("修改列时修改了注释2");
		// cm.setColumnType(DefaultColumn.COLUMNTYPE_INT);
		// cm.setIntLen(18);
		// tableOperator.updateColumn("test01", "test_05", cm);

	}

	// 删除表 TestOK
	// @Test
	public void dorpTable() throws SQLException {

		tableOperator.dropTable("test_table");
	}

	// 修改表的注释 TestOK
	// @Test
	public void updateTableComment() throws SQLException {
		tableOperator.updateTableComment("test_table", "测试修改表注释");
	}

	// 添加列 TestOK
	// @Test
	public void addColumn() throws SQLException {
		DefaultColumn column = new DefaultColumn();
		column.setName("add_column");
		column.setComment("添加列");
		column.setColumnType(Column.COLUMN_TYPE_VARCHAR);
		column.setCharLen(128);
		tableOperator.addColumn("test_table", column);
	}

	// 修改列 TestOK
	// @Test
	public void updateColumn() throws SQLException {
		DefaultColumn column = new DefaultColumn();
		column.setName("update_column");
		column.setComment("修改列");
		column.setColumnType(Column.COLUMN_TYPE_VARCHAR);
		column.setCharLen(256);
		tableOperator.updateColumn("test_table", "test2", column);
	}

	// 添加外键。 TestOK
	// @Test
	public void addForeignKey() throws SQLException {
		// //创建简单表 TestOK
		// DefaultColumn cm = new DefaultColumn();
		// cm.setName("test_id");
		// cm.setIsPk(true);
		// cm.setComment("测试ID");
		// cm.setColumnType(Column.COLUMN_TYPE_INT);
		// cm.setIntLen(18);
		// List<Column> columnList = new ArrayList<Column>();
		// columnList.add(cm);
		//
		//
		// //外键
		// DefaultColumn cm1 = new DefaultColumn();
		// cm1.setName("fid");
		// cm1.setComment("测试添加外键ID");
		// cm1.setColumnType(Column.COLUMN_TYPE_INT);
		// cm1.setIntLen(18);
		// columnList.add(cm1);
		//
		// DefaultTable tm = new DefaultTable();
		// tm.setName("test_f_table");
		// tm.setComment("测试表");
		// tm.setColumnList(columnList);
		// tableOperator.createTable(tm);

		tableOperator.addForeignKey("test_table", "test_f_table", "id", "fid");
	}

	// 刪除外鍵 not pass
	// @Test
	public void dropForeignKey() throws SQLException {
		tableOperator.dropForeignKey("test_f_table", "fid");
	}

	// @Test
	public void getPKColumns() throws SQLException {
		// //测试1
		// List<String> list =tableOperator.getPKColumns("test_table");
		//
		// for (String s : list) {
		// Assert.assertEquals(s, "id");
		// }

		// 测试2
		List<String> list1 = new ArrayList<String>();
		list1.add("test_table");
		// list1.add("test_f_table");
		Map<String, List<String>> map = tableOperator.getPKColumns(list1);

		for (Iterator<Entry<String, List<String>>> it = map.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, List<String>> e = (Map.Entry<String, List<String>>) it
					.next();
			String key = e.getKey();
			Assert.assertEquals(key, "test_table");
			List<String> val = e.getValue();
			for (String s : val) {
				Assert.assertEquals(s, "id");
			}
		}
	}

	// @Test
	public void isTableExist() throws SQLException {
		boolean f = tableOperator.isTableExist("test_table");
		Assert.assertEquals(f, true);
		boolean f1 = tableOperator.isTableExist("test_table1");
		Assert.assertEquals(f1, false);
	}

}