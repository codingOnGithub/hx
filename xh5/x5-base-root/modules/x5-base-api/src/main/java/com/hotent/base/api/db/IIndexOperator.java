package com.hotent.base.api.db;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.base.api.Page;
import com.hotent.base.api.db.model.Index;

/**
 * 索引操作接口
 * 
 * <pre>
 * 构建组：x5-base-api
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-22-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 * 
 */
public interface IIndexOperator {

	/**
	 * 添加索引。
	 * 
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            表字段
	 */
	public void createIndex(String tableName, String columnName)
			throws SQLException;

	/**
	 * 创建索引
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 * @param indexType
	 *            索引类型
	 * @param columnList
	 *            字段列表
	 * @throws Exception
	 */
	public void createIndex(String tableName, String indexName,
			String indexType, List<String> columnList) throws SQLException;

	/**
	 * 通过Index实例，在数据库中创建索引
	 * 
	 * @param index
	 *            索引接口
	 * @throws SQLException
	 */
	public void createIndex(Index index) throws SQLException;

	/**
	 * 根据表名和索引名，删除表名和索引名对应的索引.所有实现使用精确匹配方式。
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 */
	public void dropIndex(String tableName, String indexName);

	/**
	 * 根据表名和索引名，取得表名和索引名对应的索引.所有实现使用精确匹配方式。
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 * @return
	 * @throws SQLException
	 */
	public Index getIndex(String tableName, String indexName)
			throws SQLException;

	/**
	 * 根据表名，取得表名对应的索引列表.所有实现使用精确匹配方式。
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 * @throws SQLException
	 */
	public List<Index> getIndexesByTable(String tableName) throws SQLException;

	/**
	 * 根据表名，索引名使用模糊匹配，取得索引列表。
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 * @return 取得索引列表
	 * @throws SQLException
	 */
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName) throws SQLException;

	/**
	 * 根据表名，索引名使用模糊匹配，取得索引列表。
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 * @param getDDL
	 *            是否获取索引的DDL
	 * @return
	 */
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL) throws SQLException;

	/**
	 * 根据表名，索引名使用模糊匹配，取得索引分页列表。
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 * @param getDDL
	 *            是否获取索引的DDL
	 * @param page
	 *            分页
	 * @return 取得索引列表
	 * @throws SQLException
	 */
	public List<Index> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL, Page page) throws SQLException;

	/**
	 * 重建索引
	 * 
	 * @param tableName
	 *            表名
	 * @param indexName
	 *            索引名
	 */
	public void rebuildIndex(String tableName, String indexName);

	/**
	 * 设置数据源
	 * 
	 * @param dialect
	 *            数据源
	 */
	public void setDialect(IDialect dialect);

	/**
	 * 设置数据库类型
	 * 
	 * @param dbType
	 */
	public void setDbType(String dbType);

	/**
	 * 设置jdbcTemplate
	 * 
	 * @param jdbcTemplate
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);

}
