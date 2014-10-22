package com.hotent.base.db.table.impl.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.IViewOperator;
import com.hotent.base.api.db.model.Column;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.jdbc.JdbcHelper;
import com.hotent.base.db.table.BaseViewOperator;
import com.hotent.base.db.table.colmap.MySQLColumnMap;
import com.hotent.base.db.table.model.DefaultTable;
import com.hotent.base.db.table.util.SQLConst;

/**
 * Mysql 视图操作的实现类。
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
public class MySQLViewOperator extends BaseViewOperator implements
		IViewOperator {

	// private static final String
	// sqlAllView="select view_name from user_views ";
	private static final String sqlAllView = "SELECT TABLE_NAME FROM information_schema.`TABLES` WHERE TABLE_TYPE LIKE 'VIEW'";
	// private static final String SQL_GET_COLUMNS =
	// "SELECT TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,"
	// +
	// "NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT "
	// +
	// " FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";
	private static final String SQL_GET_COLUMNS_BATCH = "SELECT"
			+ " TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,"
			+ " NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT "
			+ " FROM" + " INFORMATION_SCHEMA.COLUMNS "
			+ " WHERE TABLE_SCHEMA=DATABASE() ";

	private static final String DB_TYPE = SQLConst.DB_MYSQL;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.api.db.IViewOperator#createOrRep(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void createOrRep(String viewName, String sql) throws Exception {
		String getSql = "CREATE OR REPLACE VIEW " + viewName + " as (" + sql
				+ ")";
		jdbcTemplate.execute(getSql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.api.db.IViewOperator#getViews(java.lang.String)
	 */
	@Override
	public List<String> getViews(String viewName) throws SQLException {
		String sql = sqlAllView;
		if (StringUtils.isNotEmpty(viewName))
			sql += " AND TABLE_NAME LIKE '" + viewName + "%'";
		return this.jdbcTemplate.queryForList(sql, String.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.api.db.IViewOperator#getViews(java.lang.String,
	 * com.hotent.base.api.Page)
	 */
	@Override
	public List<String> getViews(String viewName, Page page)
			throws SQLException, Exception {
		String sql = sqlAllView;
		if (StringUtils.isNotEmpty(viewName))
			sql += " AND TABLE_NAME LIKE '" + viewName + "%'";
		return super.getForList(sql, page, String.class, DB_TYPE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.api.db.IViewOperator#getViewsByName(java.lang.String,
	 * com.hotent.base.api.Page)
	 */
	@Override
	public List<Table> getViewsByName(String viewName, Page page)
			throws Exception {
		String sql = sqlAllView;
		if (StringUtils.isNotEmpty(viewName)) {
			sql += " AND TABLE_NAME LIKE '" + viewName + "%'";
		}

		RowMapper<Table> rowMapper = new RowMapper<Table>() {
			@Override
			public Table mapRow(ResultSet rs, int row) throws SQLException {
				Table table = new DefaultTable();
				table.setName(rs.getString("table_name"));
				table.setComment(table.getName());
				return table;
			}
		};
		List<Table> tableModels = getForList(sql, page, rowMapper, DB_TYPE);

		List<String> tableNames = new ArrayList<String>();
		// get all table names
		for (Table table : tableModels) {
			tableNames.add(table.getName());
		}
		// batch get table columns
		Map<String, List<Column>> tableColumnsMap = getColumnsByTableName(tableNames);
		// extract table columns from paraTypeMap by table name;
		for (Entry<String, List<Column>> entry : tableColumnsMap.entrySet()) {
			// set TableModel's columns
			for (Table table : tableModels) {
				if (table.getName().equalsIgnoreCase(entry.getKey())) {
					table.setColumnList(entry.getValue());
				}
			}
		}
		return tableModels;
	}

	/**
	 * 根据表名获取列。此方法使用批量查询方式。
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, List<Column>> getColumnsByTableName(
			List<String> tableNames) {
		String sql = SQL_GET_COLUMNS_BATCH;
		Map<String, List<Column>> map = new HashMap<String, List<Column>>();
		if (tableNames != null && tableNames.size() == 0) {
			return map;
		} else {
			StringBuffer buf = new StringBuffer();
			for (String str : tableNames) {
				buf.append("'" + str + "',");
			}
			buf.deleteCharAt(buf.length() - 1);
			sql += " AND TABLE_NAME IN (" + buf.toString() + ") ";
		}
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		List<Column> columns = jdbcHelper.queryForList(sql,
				new HashMap<String, Object>(), new MySQLColumnMap());
		for (Column column : columns) {
			String tableName = column.getTableName();
			if (map.containsKey(tableName)) {
				map.get(tableName).add(column);
			} else {
				List<Column> cols = new ArrayList<Column>();
				cols.add(column);
				map.put(tableName, cols);
			}
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.db.table.BaseViewOperator#getType(java.lang.String)
	 */
	@Override
	public String getType(String type) {
		type = type.toLowerCase();
		if (type.indexOf("number") > -1)
			return Column.COLUMN_TYPE_NUMBER;
		else if (type.indexOf("date") > -1) {
			return Column.COLUMN_TYPE_DATE;
		} else if (type.indexOf("char") > -1) {
			return Column.COLUMN_TYPE_VARCHAR;
		}
		return Column.COLUMN_TYPE_VARCHAR;
	}

}
