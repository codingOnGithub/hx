package com.hotent.base.db.table;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hotent.base.api.db.IIndexOperator;
import com.hotent.base.api.db.model.Index;
import com.hotent.base.db.DbBaseTest;
import com.hotent.base.db.table.model.DefaultIndex;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IndexOperatorTest extends DbBaseTest  {
	@Resource
	private IIndexOperator indexOperator;
	
	/**
	 * 创建索引
	 * @throws SQLException
	 */
	//@Test
	public   void createIndex() throws SQLException{
		DefaultIndex index =   new DefaultIndex();
		index.setIndexName("ind_test_table_test2");
		index.setTableName("test_table");
		List<String> columnList = new ArrayList<String>();
		columnList.add("test2");
		index.setColumnList(columnList);
		//index.setIndexType("BTREE");
		indexOperator.createIndex(index);
	}
	
	//@Test
	public   void dropIndex() throws SQLException{
		indexOperator.dropIndex("test_table","ind_test_table_test2");
	}
	
	//@Test
	public void  getIndex() throws SQLException{
		DefaultIndex index = (DefaultIndex) indexOperator.getIndex("test_table", "ind_test_table_test2");
		Assert.assertEquals(index.getIndexName(), "test_table");
	}
	
	//@Test
	public void getIndexesByTable()  throws SQLException{
		List<Index> indexList = indexOperator.getIndexesByTable("test_table");

		System.out.println(indexList);
	}
	
	
	@Test
	public void  getIndexesByFuzzyMatching()  throws SQLException{
		List<Index> indexList = indexOperator.getIndexesByFuzzyMatching("test_table","ind_test_table_test2");
		System.out.println(indexList);
		List<Index> indexList1 = indexOperator.getIndexesByFuzzyMatching("test_table",null);
		System.out.println(indexList1);
		List<Index> indexList2 = indexOperator.getIndexesByFuzzyMatching("test_table","ind_test_table_test2",true);
		System.out.println(indexList2);
		
		//分页暂时不进行测试
	}
	
	@Test
	public void rebuildIndex()  throws SQLException{
	//	indexOperator.rebuildIndex("test_table","ind_test_table_test2");
	}
}
