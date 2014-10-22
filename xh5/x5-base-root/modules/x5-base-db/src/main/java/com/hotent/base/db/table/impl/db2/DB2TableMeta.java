package com.hotent.base.db.table.impl.db2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.model.Column;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.jdbc.JdbcHelper;
import com.hotent.base.db.table.BaseTableMeta;
import com.hotent.base.db.table.colmap.DB2ColumnMap;
import com.hotent.base.db.table.model.DefaultTable;
import com.hotent.base.db.table.util.SQLConst;

/**
 * DB2 表元数据的实现类
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
public class DB2TableMeta extends BaseTableMeta {

	private final String SQL_GET_COLUMNS = "" + "SELECT "
			+ "TABNAME TAB_NAME, " + "COLNAME COL_NAME, "
			+ "TYPENAME COL_TYPE, " + "REMARKS COL_COMMENT, "
			+ "NULLS IS_NULLABLE, " + "LENGTH LENGTH, " + "SCALE SCALE, "
			+ "KEYSEQ  " + "FROM  " + "SYSCAT.COLUMNS " + "WHERE  "
			+ "TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "
			+ "AND UPPER(TABNAME) = UPPER('%s') ";

	private final String SQL_GET_COLUMNS_BATCH = "SELECT "
			+ "TABNAME TAB_NAME, " + "COLNAME COL_NAME, "
			+ "TYPENAME COL_TYPE, " + "REMARKS COL_COMMENT, "
			+ "NULLS IS_NULLABLE, " + "LENGTH LENGTH, " + "SCALE SCALE, "
			+ "KEYSEQ  " + "FROM  " + "SYSCAT.COLUMNS " + "WHERE  "
			+ "TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";

	private final String SQL_GET_TABLE_COMMENT = "" + "SELECT "
			+ "TABNAME TAB_NAME, " + "REMARKS TAB_COMMENT " + "FROM "
			+ "SYSCAT.TABLES " + "WHERE "
			+ "TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "
			+ "AND UPPER(TABNAME) =UPPER('%s')";

	private final String SQL_GET_ALL_TABLE_COMMENT = ""
			+ "SELECT "
			+ "TABNAME TAB_NAME, "
			+ "REMARKS TAB_COMMENT "
			+ "FROM "
			+ "SYSCAT.TABLES "
			+ "WHERE "
			+ "TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "
			+ "AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";

	@Override
	public Table getTableByName(String tableName) {
		Table model = getTableModel(tableName);
		if (model == null)
			return null;
		// 获取列对象
		List<Column> columnList = getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql = SQL_GET_ALL_TABLE_COMMENT;
		if (StringUtils.isNotEmpty(tableName))
			sql += " AND UPPER(TABNAME) LIKE UPPER('%" + tableName + "%')";
		jdbcHelper = JdbcHelper.getInstance();
		Map<String, Object> parameter = new HashMap<String, Object>();
		List<Map<String, String>> list = jdbcHelper.queryForList(sql,
				parameter, tableMapRowMapper);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("name");
			String comments = tmp.get("comments");
			map.put(name, comments);
		}
		return map;
	}

	@Override
	public List<Table> getTablesByName(String tableName, Page page)
			throws Exception {
		jdbcHelper = JdbcHelper.getInstance();
		String sql = SQL_GET_ALL_TABLE_COMMENT;
		if (StringUtils.isNotEmpty(tableName))
			sql += " AND UPPER(TABNAME) LIKE '%" + tableName.toUpperCase()
					+ "%'";
		List<Table> tables = getForList(sql, page, tableModelRowMapper,
				SQLConst.DB_DB2);

		List<String> tableNames = new ArrayList<String>();
		// get all table names
		for (Table model : tables) {
			tableNames.add(model.getName());
		}
		// batch get table columns
		Map<String, List<Column>> tableColumnsMap = getColumnsByTableName(tableNames);
		// extract table columns from paraTypeMap by table name;
		for (Entry<String, List<Column>> entry : tableColumnsMap.entrySet()) {
			// set TableModel's columns
			for (Table model : tables) {
				if (model.getName().equalsIgnoreCase(entry.getKey())) {
					model.setColumnList(entry.getValue());
				}
			}
		}
		return tables;

	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, String> getTablesByName(List<String> tableNames) {
		Map<String, String> map = new HashMap<String, String>();
		String sql = SQL_GET_ALL_TABLE_COMMENT;
		if (tableNames == null || tableNames.size() == 0) {
			return map;
		} else {
			StringBuffer buf = new StringBuffer();
			for (String str : tableNames) {
				buf.append("'" + str + "',");
			}
			buf.deleteCharAt(buf.length() - 1);
			sql += " AND UPPER(TABNAME) IN (" + buf.toString().toUpperCase()
					+ ") ";
		}
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		Map<String, Object> parameter = new HashMap<String, Object>();
		List<Map<String, String>> list = jdbcHelper.queryForList(sql,
				parameter, tableMapRowMapper);
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("name");
			String comments = tmp.get("comments");
			map.put(name, comments);
		}
		return map;

	}

	/**
	 * 根据表名获取tableModel。
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Table getTableModel(final String tableName) {
		jdbcHelper = JdbcHelper.getInstance();
		String sql = String.format(SQL_GET_TABLE_COMMENT, tableName);
		Table tableModel = (Table) jdbcHelper.queryForObject(sql, null,
				tableModelRowMapper);
		return tableModel;
	}

	/**
	 * 根据表名获取列
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Column> getColumnsByTableName(String tableName) {
		String sql = String.format(SQL_GET_COLUMNS, tableName);
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Column> list = jdbcHelper.queryForList(sql, map,
				new DB2ColumnMap());
		return list;
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
			sql += " AND UPPER(TABNAME) IN (" + buf.toString().toUpperCase()
					+ ") ";
		}
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		List<Column> columnModels = jdbcHelper.queryForList(sql,
				new HashMap<String, Object>(), new DB2ColumnMap());
		for (Column columnModel : columnModels) {
			String tableName = columnModel.getTableName();
			if (map.containsKey(tableName)) {
				map.get(tableName).add(columnModel);
			} else {
				List<Column> cols = new ArrayList<Column>();
				cols.add(columnModel);
				map.put(tableName, cols);
			}
		}
		return map;
	}

	RowMapper<Table> tableModelRowMapper = new RowMapper<Table>() {
		@Override
		public Table mapRow(ResultSet rs, int row) throws SQLException {
			Table tableModel = new DefaultTable();
			String tabName = rs.getString("TAB_NAME");
			String tabComment = rs.getString("TAB_COMMENT");
			tableModel.setName(tabName);
			tableModel.setComment(tabComment);
			return tableModel;
		}
	};

	RowMapper<Map<String, String>> tableMapRowMapper = new RowMapper<Map<String, String>>() {
		@Override
		public Map<String, String> mapRow(ResultSet rs, int row)
				throws SQLException {
			String tableName = rs.getString("TAB_NAME");
			String comments = rs.getString("TAB_COMMENT");
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", tableName);
			map.put("comments", comments);
			return map;
		}
	};

}
