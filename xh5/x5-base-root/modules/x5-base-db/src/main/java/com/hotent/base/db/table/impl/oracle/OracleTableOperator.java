package com.hotent.base.db.table.impl.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hotent.base.api.db.IDialect;
import com.hotent.base.api.db.model.Column;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.mybatis.Dialect;
import com.hotent.base.db.table.BaseTableOperator;

/**
 * oracle 数据库表操作的接口实现。
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
public class OracleTableOperator extends BaseTableOperator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#setDbType(java.lang.String)
	 */
	@Override
	public void setDbType(String dbType) {
		super.setDbType(dbType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.db.table.BaseTableOperator#getDbType()
	 */
	@Override
	public String getDbType() {
		return super.getDbType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hotent.base.db.table.BaseTableOperator#setJdbcTemplate(org.
	 * springframework.jdbc.core.JdbcTemplate)
	 */
	@Override
	public void setJdbcTemplate(JdbcTemplate template) {
		super.setJdbcTemplate(template);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#setDialect(com.hotent.base
	 * .db.mybatis.Dialect)
	 */
	@Override
	public void setDialect(IDialect dialect) {
		this.dialect = (Dialect) dialect;
	}

	/*
	 * 创建表 (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#createTable(com.hotent.base
	 * .api.table.model.Table)
	 */
	@Override
	public void createTable(Table table) throws SQLException {
		List<Column> columnList = table.getColumnList();
		// 建表语句
		StringBuffer sb = new StringBuffer();
		// 主键字段
		String pkColumn = null;

		// 例注释
		List<String> columnCommentList = new ArrayList<String>();
		// 建表开始
		sb.append("CREATE TABLE " + table.getName() + " (\n");
		for (int i = 0; i < columnList.size(); i++) {
			// 建字段
			Column column = columnList.get(i);
			sb.append("    ").append(column.getName()).append("    ");
			sb.append(getColumnType(column.getColumnType(),
					column.getCharLen(), column.getIntLen(),
					column.getDecimalLen()));
			sb.append(" ");

			// 主键
			if (column.getIsPk()) {
				if (pkColumn == null) {
					pkColumn = column.getName();
				} else {
					pkColumn += "," + column.getName();
				}
			}
			// 添加默认值
			if (StringUtils.isNotEmpty(column.getDefaultValue())) {
				sb.append(" DEFAULT " + column.getDefaultValue());
			}

			// 非空
			// if (!cm.getIsNull()) {
			// sb.append(" NOT NULL ");
			// }

			// 字段注释
			if (column.getComment() != null && column.getComment().length() > 0) {
				// createTableSql.append(" COMMENT '" + cm.getComment() + "'");
				String comment = "COMMENT ON COLUMN " + table.getName() + "."
						+ column.getName() + " IS '" + column.getComment()
						+ "'\n";
				columnCommentList.add(comment);
			}
			sb.append(",\n");
		}
		// 创建主键
		if (pkColumn != null) {
			sb.append("    CONSTRAINT PK_").append(table.getName())
					.append(" PRIMARY KEY (").append(pkColumn).append(")");
		}

		// 建表结束
		sb.append("\n)");
		// 表注释
		jdbcTemplate.execute(sb.toString());
		if (table.getComment() != null && table.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON TABLE " + table.getName()
					+ " IS '" + table.getComment() + "'\n");
		}
		for (String columnComment : columnCommentList) {
			jdbcTemplate.execute(columnComment);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#getColumnType(com.hotent.base
	 * .api.table.model.Column)
	 */
	@Override
	public String getColumnType(Column column) {
		return getColumnType(column.getColumnType(), column.getCharLen(),
				column.getIntLen(), column.getDecimalLen());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#getColumnType(java.lang.String
	 * , int, int, int)
	 */
	@Override
	public String getColumnType(String columnType, int charLen, int intLen,
			int decimalLen) {
		if (Column.COLUMN_TYPE_VARCHAR.equals(columnType)) {
			return "VARCHAR2(" + charLen + ')';
		} else if (Column.COLUMN_TYPE_NUMBER.equals(columnType)) {
			return "NUMBER(" + (intLen + decimalLen) + "," + decimalLen + ")";
		} else if (Column.COLUMN_TYPE_DATE.equals(columnType)) {
			return "DATE";
		} else if (Column.COLUMN_TYPE_INT.equals(columnType)) {
			return "NUMBER(" + intLen + ")";
		} else if (Column.COLUMN_TYPE_CLOB.equals(columnType)) {
			return "CLOB";
		} else {
			return "VARCHAR2(200)";
		}
	}

	/*
	 * 
	 * 删除表
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#dropTable(java.lang.String)
	 */
	@Override
	public void dropTable(String tableName) throws SQLException {
		String selSql = "select count(*) amount from user_objects where object_name = upper('"
				+ tableName + "')";
		int rtn = jdbcTemplate.queryForObject(selSql, Integer.class);
		if (rtn <= 0)
			return;
		String sql = "drop table " + tableName;
		jdbcTemplate.execute(sql);

	}

	/*
	 * 修改表的注释
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#updateTableComment(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void updateTableComment(String tableName, String comment)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("COMMENT ON TABLE ").append(tableName).append(" IS '")
				.append(comment).append("'\n");
		jdbcTemplate.execute(sb.toString());
	}

	/*
	 * 添加列
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#addColumn(java.lang.String,
	 * com.hotent.base.api.table.model.Column)
	 */
	@Override
	public void addColumn(String tableName, Column model) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" ADD ");
		sb.append(model.getName()).append(" ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(),
				model.getIntLen(), model.getDecimalLen()));

		// 添加默认值
		if (StringUtils.isNotEmpty(model.getDefaultValue())) {
			sb.append(" DEFAULT " + model.getDefaultValue());
		}
		// 添加是否为空
		// if (!model.getIsNull()) {
		// sb.append(" NOT NULL ");
		// }
		sb.append("\n");
		jdbcTemplate.execute(sb.toString());
		if (model.getComment() != null && model.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "."
					+ model.getName() + " IS '" + model.getComment() + "'");
		}

	}

	/*
	 * 修改列
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#updateColumn(java.lang.String,
	 * java.lang.String, com.hotent.base.api.table.model.Column)
	 */
	@Override
	public void updateColumn(String tableName, String columnName, Column column)
			throws SQLException {
		// 修改列名
		if (!columnName.equals(column.getName())) {
			StringBuffer modifyName = new StringBuffer("ALTER TABLE ")
					.append(tableName);
			modifyName.append(" RENAME COLUMN ").append(columnName)
					.append(" TO ").append(column.getName());
			jdbcTemplate.execute(modifyName.toString());
		}

		// 修改列的大小,此处不修改列的类型,若修改列的类型则在前面部分已抛出异常
		StringBuffer sb = new StringBuffer();
		// alter table test01 modify(test_01 NUMBER(1));
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" MODIFY(" + column.getName()).append(" ");
		sb.append(getColumnType(column.getColumnType(), column.getCharLen(),
				column.getIntLen(), column.getDecimalLen()));
		if (!column.getIsNull()) {
			sb.append(" NOT NULL ");
		}
		sb.append(")");

		jdbcTemplate.execute(sb.toString());

		// 修改注释
		if (column.getComment() != null && column.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "."
					+ column.getName() + " IS'" + column.getComment() + "'");
		}
	}

	/*
	 * 
	 * 添加外键。
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#addForeignKey(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void addForeignKey(String pkTableName, String fkTableName,
			String pkField, String fkField) {
		String shortTableName = fkTableName.replaceFirst("(?im)W_", "");
		String sql = " ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_"
				+ shortTableName + " FOREIGN KEY (" + fkField + ") REFERENCES "
				+ pkTableName + " (" + pkField + ") ON DELETE CASCADE";
		jdbcTemplate.execute(sql);
	}

	/*
	 * 刪除外鍵
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#dropForeignKey(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public void dropForeignKey(String tableName, String keyName) {
		String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  "
				+ keyName;
		jdbcTemplate.execute(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#getPKColumns(java.lang.String)
	 */
	@Override
	public List<String> getPKColumns(String tableName) throws SQLException {
		String sql = "SELECT cols.column_name"
				+ " FROM USER_CONSTRAINTS CONS, USER_CONS_COLUMNS COLS"
				+ " WHERE UPPER(cols.table_name) = UPPER('" + tableName + "')"
				+ " AND cons.constraint_type = 'P'"
				+ " AND cons.constraint_name = cols.constraint_name"
				+ " AND CONS.OWNER = COLS.OWNER";
		List<String> columns = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return columns;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#getPKColumns(java.util.List)
	 */
	@Override
	public Map<String, List<String>> getPKColumns(List<String> tableNames)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		for (String name : tableNames) {
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length() - 1);

		String sql = "SELECT cols.table_name,cols.column_name"
				+ " FROM USER_CONSTRAINTS CONS, USER_CONS_COLUMNS COLS"
				+ " WHERE UPPER(cols.table_name) in ("
				+ sb.toString().toUpperCase() + ")"
				+ " AND cons.constraint_type = 'P'"
				+ " AND cons.constraint_name = cols.constraint_name"
				+ " AND CONS.OWNER = COLS.OWNER";

		Map<String, List<String>> columnsMap = new HashMap<String, List<String>>();

		List<Map<String, String>> maps = jdbcTemplate.query(sql,
				new RowMapper<Map<String, String>>() {
					@Override
					public Map<String, String> mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String table = rs.getString(1);
						String column = rs.getString(2);
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", table);
						map.put("column", column);
						return map;
					}
				});

		for (Map<String, String> map : maps) {
			if (columnsMap.containsKey(map.get("name"))) {
				columnsMap.get(map.get("name")).add(map.get("column"));
			} else {
				List<String> cols = new ArrayList<String>();
				cols.add(map.get("column"));
				columnsMap.put(map.get("name"), cols);
			}
		}

		return columnsMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hotent.base.db.table.BaseTableOperator#isTableExist(java.lang.String)
	 */
	@Override
	public boolean isTableExist(String tableName) {
		StringBuffer sql = new StringBuffer();
		sql.append("select COUNT(1) from user_tables t where t.TABLE_NAME='")
				.append(tableName.toUpperCase()).append("'");
		return jdbcTemplate.queryForObject(sql.toString(), Integer.class) > 0 ? true
				: false;
	}

}
