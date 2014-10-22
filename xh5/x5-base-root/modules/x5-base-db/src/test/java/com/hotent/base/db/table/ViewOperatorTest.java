package com.hotent.base.db.table;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.base.api.db.IViewOperator;
import com.hotent.base.api.db.model.DataSource;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.DbBaseTest;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.db.table.factory.DatabaseFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ViewOperatorTest extends DbBaseTest {
	DataSource dataSource;
	IViewOperator view;

	String viewName = "test_view";

	@Before
	public void getDataSourceModel() throws Exception {
		// dataSource=new DefaultDataSource();
		// dataSource.setAlias("MySQL");
		// dataSource.setDbType("mysql");
		// dataSource.setDriverName("com.mysql.jdbc.Driver");
		// dataSource.setUrl("jdbc:mysql://localhost:3306/x5");
		// dataSource.setUserName("root");
		// dataSource.setPassword("000");

		view = DatabaseFactory.getViewOperator(dataSource);

	}

	// @Test
	public void createOrRep() throws Exception {

		view.createOrRep(viewName, "select * from test_table");
	}

	// @Test
	public void getViews() throws Exception {

		List<String> list = view.getViews("test");

		for (String s : list) {
			Assert.assertEquals(s, viewName);
		}
	}

	@Test
	public void getPageViews() throws Exception {
		DefaultPage page = new DefaultPage();
		page.setLimit(2);
		page.setAsyncTotalCount(true);

		viewName = "test";
		PageList<String> list = (PageList<String>) view
				.getViews(viewName, page);

		System.out.println(list);

		System.out.println();
	}

	// @Test
	public void getViewsByName() throws Exception {
		List<Table> list = view.getViewsByName(viewName, null);

		for (Table table : list) {
			System.out.println(table);
		}
	}

	// @Test
	public void getColumnsByTableName() throws Exception {
		Table table = view.getModelByViewName(viewName);
		System.out.println(table);
	}

}
