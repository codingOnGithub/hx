package com.hotent.base.db.table.impl.mysql;

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
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.jdbc.JdbcHelper;
import com.hotent.base.db.table.BaseTableMeta;
import com.hotent.base.db.table.colmap.MySQLColumnMap;
import com.hotent.base.db.table.model.DefaultTable;
import com.hotent.base.db.table.util.SQLConst;

/**
 * MySQL 表元数据的实现类
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
public class MySQLTableMeta extends BaseTableMeta {

	private final String SQL_GET_COLUMNS = "SELECT"
			+ " TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,"
			+ " NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT "
			+ " FROM " + " INFORMATION_SCHEMA.COLUMNS "
			+ " WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";

	private final String SQL_GET_COLUMNS_BATCH = "SELECT"
			+ " TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH,"
			+ " NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT "
			+ " FROM " + " INFORMATION_SCHEMA.COLUMNS "
			+ " WHERE TABLE_SCHEMA=DATABASE() ";

	private final String sqlComment = "select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ";

	private final String sqlAllTable = "select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";

	@SuppressWarnings("unused")
	private final String sqlPk = "SELECT k.column_name name "
			+ "FROM information_schema.table_constraints t "
			+ "JOIN information_schema.key_column_usage k "
			+ "USING(constraint_name,table_schema,table_name) "
			+ "WHERE t.constraint_type='PRIMARY KEY' "
			+ "AND t.table_schema=DATABASE() " + "AND t.table_name='%s'";

	@Override
	public Table getTableByName(String tableName) {
		Table model = getTableModel(tableName);
		// 获取列对象
		List<Column> columnList = getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql = sqlAllTable;
		if (StringUtils.isNotEmpty(tableName))
			sql += " AND TABLE_NAME LIKE '%" + tableName + "%'";
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();

		List list = jdbcHelper.queryForList(sql, new HashMap(),
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int row)
							throws SQLException {
						String tableName = rs.getString("table_name");
						String comments = rs.getString("table_comment");
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", tableName);
						map.put("comments", comments);
						return map;
					}
				});
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("name");
			String comments = tmp.get("comments");
			comments = getComments(comments, name);
			map.put(name, comments);
		}

		return map;
	}

	@Override
	public List<Table> getTablesByName(String tableName, Page page)
			throws Exception {
		jdbcHelper = JdbcHelper.getInstance();
		String sql = sqlAllTable;
		if (StringUtils.isNotEmpty(tableName))
			sql += " AND TABLE_NAME LIKE '%" + tableName + "%'";
		RowMapper<Table> rowMapper = new RowMapper<Table>() {
			@Override
			public Table mapRow(ResultSet rs, int row) throws SQLException {
				Table table = new DefaultTable();
				table.setName(rs.getString("TABLE_NAME"));
				String comments = rs.getString("TABLE_COMMENT");
				comments = getComments(comments, table.getName());
				table.setComment(comments);
				return table;
			}
		};
		List<Table> tables = getForList(sql, page, rowMapper, SQLConst.DB_MYSQL);

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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(List<String> names) {
		StringBuffer sb = new StringBuffer();
		for (String name : names) {
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length() - 1);
		String sql = sqlAllTable + " and  lower(table_name) in ("
				+ sb.toString().toLowerCase() + ")";

		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		Map parameter = new HashMap();
		List list = jdbcHelper.queryForList(sql, parameter,
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int row)
							throws SQLException {
						String tableName = rs.getString("table_name");
						String comments = rs.getString("table_comment");
						Map<String, String> map = new HashMap<String, String>();
						map.put("tableName", tableName);
						map.put("tableComment", comments);
						return map;
					}
				});
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("tableName");
			String comments = tmp.get("tableComment");
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
	private Table getTableModel(final String tableName) {
		jdbcHelper = JdbcHelper.getInstance();
		String sql = String.format(sqlComment, tableName);
		@SuppressWarnings("unchecked")
		Table table = (Table) jdbcHelper.queryForObject(sql, null,
				new RowMapper<Table>() {
					@Override
					public Table mapRow(ResultSet rs, int row)
							throws SQLException {
						Table table = new DefaultTable();
						String comments = rs.getString("table_comment");
						comments = getComments(comments, tableName);
						table.setName(tableName);
						table.setComment(comments);
						return table;
					}
				});
		if (BeanUtils.isEmpty(table))
			table = new DefaultTable();

		return table;
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
		jdbcHelper = JdbcHelper.getInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		// sqlColumns语句的column_key包含了column是否为primary
		// key，并在MySqlColumnMap中进行了映射。
		List<Column> list = jdbcHelper.queryForList(sql, map,
				new MySQLColumnMap());
		for (Column model : list) {
			model.setTableName(tableName);
		}
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
			sql += " AND TABLE_NAME IN (" + buf.toString() + ") ";
		}
		// jdbcHelper.setCurrentDb(currentDb);
		jdbcHelper = JdbcHelper.getInstance();
		List<Column> Columns = jdbcHelper.queryForList(sql,
				new HashMap<String, Object>(), new MySQLColumnMap());
		for (Column column : Columns) {
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

	/**
	 * 获取注释
	 * 
	 * @param comments
	 * @param defaultValue
	 * @return
	 */
	public static String getComments(String comments, String defaultValue) {
		if (StringUtils.isEmpty(comments))
			return defaultValue;
		int idx = comments.indexOf("InnoDB free");
		if (idx > -1) 
			comments = StringUtils.remove(comments.substring(0, idx).trim(),
					";");
		if (StringUtils.isEmpty(comments)) 
			comments = defaultValue;
		return comments;
	}
}
