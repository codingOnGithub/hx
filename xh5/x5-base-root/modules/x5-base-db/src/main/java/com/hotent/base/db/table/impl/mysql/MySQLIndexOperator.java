package com.hotent.base.db.table.impl.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.model.Index;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.db.mybatis.domain.PageResult;
import com.hotent.base.db.table.BaseIndexOperator;
import com.hotent.base.db.table.model.DefaultIndex;

/**
 * MySQL 索引操作的实现
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-25-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 */
public class MySQLIndexOperator extends BaseIndexOperator {

	// 批量操作的
	protected int BATCH_SIZE = 100;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#createIndex(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void createIndex(String tableName, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		super.createIndex(tableName, columnName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#createIndex(java.lang.String,
	 * java.lang.String, java.lang.String, java.util.List)
	 */
	@Override
	public void createIndex(String tableName, String indexName,
			String indexType, List<String> columnList) throws SQLException {
		Index index = new DefaultIndex();
		index.setTableName(tableName);
		index.setIndexName(indexName);
		index.setIndexType(indexType);
		index.setColumnList(columnList);
		this.createIndex(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#createIndex(com.hotent.base
	 * .api.table.model.Index)
	 */
	@Override
	public void createIndex(Index index) throws SQLException {
		String sql = genIndexDDL(index);
		jdbcTemplate.execute(sql);
	}

	/**
	 * 生成Index对象对应的DDL语句
	 * 
	 * @param index
	 * @return
	 */
	private String genIndexDDL(Index index) {
		StringBuffer ddl = new StringBuffer();
		ddl.append("CREATE");
		if (index.isUnique()) {
			ddl.append(" UNIQUE");
		}
		ddl.append(" INDEX");
		ddl.append(" " + index.getIndexName());
		if (StringUtils.isNotEmpty(index.getIndexType()))// HASH, BTREE
			ddl.append(" USING").append(" " + index.getIndexType());
		ddl.append(" ON " + index.getTableName());
		ddl.append("(");
		for (String column : index.getColumnList()) {
			ddl.append(column + ",");
		}
		if (!StringUtils.isEmpty(index.getIndexComment())) {
			ddl.append("COMMENT '" + index.getIndexComment() + "'");
		}
		ddl.replace(ddl.length() - 1, ddl.length(), ")");
		return ddl.toString();
	}

	/*
	 * (non-Javadoc) 刪除索引
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#dropIndex(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void dropIndex(String tableName, String indexName) {
		String sql = "drop index " + indexName + " on " + tableName;
		jdbcTemplate.execute(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#getIndex(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Index getIndex(String tableName, String indexName)
			throws SQLException {
		List<Index> indexList = getIndexesByFuzzyMatching(tableName, indexName,
				true);
		if (indexList.size() > 0) {
			Index index = indexList.get(0);
			return dedicatePKIndex(index);
		}
		return null;
	}

	/**
	 * 通过SQL获得索引
	 * 
	 * @param sql
	 * @return
	 */
	private List<Index> getIndexesBySql(String sql) {
		List<Index> indexes = jdbcTemplate.query(sql, new RowMapper<Index>() {
			@Override
			public Index mapRow(ResultSet rs, int rowNum) throws SQLException {
				Index index = new DefaultIndex();
				List<String> columns = new ArrayList<String>();
				index.setTableName(rs.getString("TABLE_NAME"));
				index.setIndexName(rs.getString("INDEX_NAME"));
				index.setIndexType(rs.getString("INDEX_TYPE"));
				index.setUnique(rs.getInt("NON_UNIQUE") == 0 ? true : false);
				// index.setIndexComment(rs.getString("INDEX_COMMENT"));
				columns.add(rs.getString("COLUMN_NAME"));
				index.setColumnList(columns);
				return index;
			}

		});
		return indexes;

	}

	/**
	 * indexes中，每一索引列，对就一元素。需要进行合并。
	 * 
	 * @param indexes
	 * @return
	 */
	private List<Index> mergeIndex(List<Index> indexes) {
		List<Index> indexList = new ArrayList<Index>();
		for (Index index : indexes) {
			boolean found = false;
			for (Index index1 : indexList) {
				if (index.getIndexName().equals(index1.getIndexName())
						&& index.getTableName().equals(index1.getTableName())) {
					index1.getColumnList().add(index.getColumnList().get(0));
					found = true;
					break;
				}
			}
			if (!found)
				indexList.add(index);
		}
		return indexList;
	}

	/**
	 * 判断索引是否是主键索引。如果是，则将索引index的pkIndex属性设置为true。此方法是批量操作，主要是减少对数据库访问次数。
	 * 
	 * @param indexList
	 * @return
	 * @throws SQLException
	 */
	private List<Index> dedicatePKIndex(List<Index> indexList)
			throws SQLException {
		// 用于存放所有索引所包含的所有数据表名(去掉重复)
		List<String> tableNames = new ArrayList<String>();
		for (Index index : indexList) {
			// 将索引对应的表名放到tableNames中。
			if (!tableNames.contains(index.getTableName())) {
				tableNames.add(index.getTableName());
			}
		}
		Map<String, List<String>> tablePKColsMaps = getTablesPKColsByNames(tableNames);
		for (Index index : indexList) {
			if (isListEqual(index.getColumnList(),
					tablePKColsMaps.get(index.getTableName()))) {
				index.setPkIndex(true);
			} else {
				index.setPkIndex(false);
			}
		}

		return indexList;
	}

	/**
	 * 根据表名，取得对应表的主键列。此方法是指操作，主要是减少对数据库的访问次数。返回的Map中：key=表名；value=表名对应的主键列列表。
	 * 
	 * @param tableNames
	 * @return
	 * @throws SQLException
	 */
	private Map<String, List<String>> getTablesPKColsByNames(
			List<String> tableNames) throws SQLException {
		Map<String, List<String>> tableMaps = new HashMap<String, List<String>>();
		List<String> names = new ArrayList<String>();
		for (int i = 1; i <= tableNames.size(); i++) {
			names.add(tableNames.get(i - 1));
			if (i % BATCH_SIZE == 0 || i == tableNames.size()) {
				Map<String, List<String>> map;
				map = getPKColumns(names);
				tableMaps.putAll(map);
				names.clear();
			}
		}
		return tableMaps;
	}

	/**
	 * 获取主键字段
	 * 
	 * @param tableNames
	 * @return
	 * @throws SQLException
	 */
	private Map<String, List<String>> getPKColumns(List<String> tableNames)
			throws SQLException {
		getTableOperator();
		return tableOperator.getPKColumns(tableNames);
	}

	/**
	 * 获取主键字段
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	private List<String> getPKColumns(String tableName) throws SQLException {
		getTableOperator();
		return tableOperator.getPKColumns(tableName);
	}

	private void getTableOperator() {
		tableOperator = new MySQLTableOperator();
		tableOperator.setJdbcTemplate(jdbcTemplate);
		tableOperator.setDialect(dialect);
	}

	/**
	 * 判断索引是否是主键索引。如果是，则将索引index的pkIndex属性设置为true。
	 * 
	 * @param index
	 * @return
	 */
	private Index dedicatePKIndex(Index index) {
		try {
			List<String> pkCols = getPKColumns(index.getIndexName());
			if (isListEqual(index.getColumnList(), pkCols))
				index.setPkIndex(true);
			else
				index.setPkIndex(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return index;
	}

	/**
	 * 比较两个列表是否相等。在比较两个列表的元素时，比较的方式为(o==null ? e==null : o.equals(e)).
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean isListEqual(List<String> list1, List<String> list2) {
		if (list1 == null && list2 == null)// 2个都为null
			return true;
		if (list1 == null || list2 == null)// 2个有一个为null
			return false;
		if (list1.size() != list2.size())// 2个长度不相等
			return false;
		if (list1.containsAll(list2))
			return true;

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#getIndexesByTable(java.lang
	 * .String)
	 */
	@Override
	public List<Index> getIndexesByTable(String tableName) throws SQLException {
		List<Index> indexList = getIndexesByFuzzyMatching(tableName, null, true);
		return dedicatePKIndex(indexList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#getIndexesByFuzzyMatching(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName) throws SQLException {
		return getIndexesByFuzzyMatching(tableName, indexName, true, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#getIndexesByFuzzyMatching(
	 * java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL) throws SQLException {
		return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#getIndexesByFuzzyMatching(
	 * java.lang.String, java.lang.String, java.lang.Boolean,
	 * com.hotent.base.api.Page)
	 */
	@Override
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL, Page page) throws SQLException {
		String schema = getSchema();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT TABLE_NAME,INDEX_NAME,COLUMN_NAME,NULLABLE,INDEX_TYPE,NON_UNIQUE FROM "
				+ "  INFORMATION_SCHEMA.STATISTICS WHERE 1=1");
		if (StringUtils.isNotEmpty(schema))
			sql.append(" AND TABLE_SCHEMA='").append(schema).append("'");
		if (StringUtils.isNotEmpty(tableName))
			sql.append(" AND UPPER(TABLE_NAME) LIKE UPPER('%")
					.append(tableName).append("%')");
		if (StringUtils.isNotEmpty(indexName))
			sql.append(" AND UPPER(INDEX_NAME) LIKE UPPER('%")
					.append(indexName).append("%')");

		String getSql = sql.toString();
		List<Index> list = new ArrayList<Index>();

		if (page != null) {
			int currentPage = page.getStartIndex();
			int pageSize = page.getPageSize();
			int offset = (currentPage - 1) * pageSize;
			String totalSql = dialect.getCountString(getSql);
			int total = jdbcTemplate.queryForObject(totalSql, Integer.class);
			getSql = dialect.getLimitString(getSql, offset, pageSize);
			list = getIndexesBySql(getSql);
			list = new PageList<Index>(list, new PageResult(offset, pageSize,
					total));
		} else {
			list = getIndexesBySql(getSql);
		}

		// indexes中，每一索引列，对就一元素。需要进行合并。
		List<Index> indexList = mergeIndex(list);
		// 是否获取DDL语句
		if (getDDL) {
			for (Index index : indexList) {
				index.setIndexDdl(genIndexDDL(index));
			}
		}

		// 设置主键索引
		dedicatePKIndex(indexList);
		return indexList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseIndexOperator#rebuildIndex(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void rebuildIndex(String tableName, String indexName) {
		String sql = "SHOW CREATE TABLE " + tableName;
		List<String> ddls = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("Create Table");
			}
		});
		String ddl = ddls.get(0);

		Pattern pattern = Pattern.compile("ENGINE\\s*=\\s*\\S+",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(ddl);
		if (matcher.find()) {
			String str = matcher.group();
			String sql_ = "ALTER TABLE " + tableName + " " + str;
			jdbcTemplate.execute(sql_);
		}
	}

	/**
	 * Mysql的schema
	 * 
	 * @return
	 */
	private String getSchema() {
		String schema = null;
		try {
			schema = jdbcTemplate.getDataSource().getConnection().getCatalog();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return schema;
	}
}
