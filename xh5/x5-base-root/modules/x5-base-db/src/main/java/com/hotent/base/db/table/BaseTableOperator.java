package com.hotent.base.db.table;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.base.api.db.IDialect;
import com.hotent.base.api.db.ITableOperator;
import com.hotent.base.api.db.model.Column;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.mybatis.Dialect;

/**
 * 操作数据表基类。
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
public class BaseTableOperator implements ITableOperator {
	// logger
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 数据库类型
	protected String dbType;

	// JdbcTemplate
	protected JdbcTemplate jdbcTemplate;

	// 方言
	protected Dialect dialect;

	@Override
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Override
	public String getDbType() {
		return this.dbType;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void setDialect(IDialect dialect) {
		this.dialect = (Dialect) dialect;
	}

	@Override
	public String getColumnType(Column column) {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public String getColumnType(String columnType, int charLen, int intLen,
			int decimalLen) {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void createTable(Table table) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void dropTable(String tableName) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void updateTableComment(String tableName, String comment)
			throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void addColumn(String tableName, Column model) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void updateColumn(String tableName, String columnName, Column model)
			throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void addForeignKey(String pkTableName, String fkTableName,
			String pkField, String fkField) {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void dropForeignKey(String tableName, String keyName) {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public List<String> getPKColumns(String tableName) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public Map<String, List<String>> getPKColumns(List<String> tableNames)
			throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public boolean isTableExist(String tableName) {
		return false;
	}

}
