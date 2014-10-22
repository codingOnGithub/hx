package com.hotent.base.db.table;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.IDialect;
import com.hotent.base.api.db.IIndexOperator;
import com.hotent.base.api.db.ITableOperator;
import com.hotent.base.api.db.model.Index;
import com.hotent.base.db.mybatis.Dialect;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.db.mybatis.domain.PageResult;

/**
 * 操作索引基类
 * 
 * <pre>
 * 构建组：x5-base-db
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-25-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BaseIndexOperator implements IIndexOperator {

	protected JdbcTemplate jdbcTemplate;
	// 数据库类型
	protected String dbType;
	// 方言
	protected Dialect dialect;

	protected ITableOperator tableOperator;

	@Override
	public void setDialect(IDialect dialect) {
		this.dialect = (Dialect) dialect;
	}

	@Override
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	@Override
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createIndex(String tableName, String columnName)
			throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void createIndex(String tableName, String indexName,
			String indexType, List<String> fields) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void createIndex(Index index) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void dropIndex(String tableName, String indexName) {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public Index getIndex(String tableName, String indexName)
			throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public List<Index> getIndexesByTable(String tableName) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL, Page page) throws SQLException {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	@Override
	public void rebuildIndex(String tableName, String indexName) {
		throw new UnsupportedOperationException(
				"Current Implement not supported");
	}

	/**
	 * 
	 * 获取分页数据
	 * 
	 * @param sql
	 *            SQL
	 * @param page
	 *            分页
	 * @param rowMapper
	 *            列数据
	 * @param dbType
	 *            数据库类型
	 * @return
	 * @throws Exception
	 */
	protected <T> List<T> getForList(String sql, Page page,
			Class<T> elementType, String dbType) throws Exception {
		if (page != null) {
			int pageSize = page.getPageSize();
			int offset = page.getStartIndex();
			String pageSql = dialect.getLimitString(sql, offset, pageSize);
			String totalSql = dialect.getCountString(sql);
			List<T> list = this.jdbcTemplate.queryForList(pageSql, elementType);
			int total = this.jdbcTemplate.queryForObject(totalSql,
					Integer.class);
			return new PageList<T>(list,
					new PageResult(offset, pageSize, total));
		} else {
			return this.jdbcTemplate.queryForList(sql, elementType);
		}
	}

}
