package com.hotent.base.db.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.DbBaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TableMetaTest extends DbBaseTest {
	@Resource
	private BaseTableMeta tableMeta;

	@Test
	// @Rollback
	public void getTableByName() {
		Table table = tableMeta.getTableByName("test_table");
		Assert.assertEquals(table.getName(), "test_table");
	}

	//@Test
	// @Rollback
	public void getTableByNameMap() {
		Map<String, String> map = tableMeta.getTablesByName("test_table");
		Assert.assertEquals(map.get("test_table"), "测试表");
	}

	//@Test
	public void getTablesByNameList() throws Exception {
		List<Table> tableList = tableMeta.getTablesByName("test_table", null);
		Assert.assertEquals(tableList.size() > 0, true);
	}

	//@Test
	public void getTablesByNameMapList() throws Exception {
		List<String> names = new ArrayList<String>();
		names.add("test_table");
		Map<String, String> map = tableMeta.getTablesByName(names);
		Assert.assertEquals(map.get("test_table"), "测试表");
	}

}
