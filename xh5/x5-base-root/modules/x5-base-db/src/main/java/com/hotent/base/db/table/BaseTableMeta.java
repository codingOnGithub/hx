package com.hotent.base.db.table;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.model.DataSource;
import com.hotent.base.api.db.model.Table;
import com.hotent.base.db.jdbc.JdbcHelper;
import com.hotent.base.db.mybatis.Dialect;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.db.mybatis.domain.PageResult;
import com.hotent.base.db.table.factory.DatabaseFactory;

/**
 * 数据表元数据抽象类。 用于读取数据库表的元数据信息。 1.查询数据库中的表列表。 2.取得表的详细数据。
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
public abstract class BaseTableMeta {

	@SuppressWarnings("rawtypes")
	protected JdbcHelper jdbcHelper;

	/**
	 * 根据表名，取得表模型。此处对表名进行非模糊匹配。
	 * 
	 * @param tableName
	 *            表名
	 * @return 表模型
	 */
	public abstract Table getTableByName(String tableName);

	/**
	 * 根据表名，使用模糊匹配，查询系统中的表。返回的用Map表示的数据对象。key=表名，value=表注解。
	 * 
	 * @param tableName
	 * @return
	 */
	public abstract Map<String, String> getTablesByName(String tableName);

	/**
	 * 根据表名,使用模糊匹配，查询系统中的表。返回的用Map表示的数据对象。key=表名，value=表注解。
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public abstract List<Table> getTablesByName(String tableName, Page page)
			throws Exception;

	/**
	 * 根据表名查询系统中的表。 返回的Map中：key=表名，value=表comment
	 * 
	 * @param tableName
	 * @return
	 */
	public abstract Map<String, String> getTablesByName(List<String> names);

	/**
	 * 设置数据源。
	 * 
	 * @throws Exception
	 */
	public void init() throws Exception {
		setDataSource(DatabaseFactory.getDataSource(null));
	}

	/**
	 * 设置数据源。
	 * 
	 * @param dsName
	 *            数据源名称
	 * @throws Exception
	 */
	public void setDataSource(DataSource dataSource) throws Exception {
		jdbcHelper = JdbcHelper.getInstance();
		jdbcHelper.init(dataSource);
	}

	/**
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
			RowMapper<T> rowMapper, String dbType) throws Exception {
		if (page != null) {
			int pageSize = page.getPageSize();
			int offset = page.getStartIndex();
			Dialect dialect = DatabaseFactory.getDialect(dbType);
			String pageSql = dialect.getLimitString(sql, offset, pageSize);
			String totalSql = dialect.getCountString(sql);
			List<T> list = this.jdbcHelper.getJdbcTemplate().query(pageSql,
					rowMapper);
			int total = this.jdbcHelper.getJdbcTemplate().queryForObject(
					totalSql, Integer.class);
			return new PageList<T>(list,
					new PageResult(offset, pageSize, total));
		} else {
			return this.jdbcHelper.getJdbcTemplate().query(sql, rowMapper);
		}
	}

}
