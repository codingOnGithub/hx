package com.hotent.base.api.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.base.api.db.model.Column;
import com.hotent.base.api.db.model.Table;

/**
 * 数据表操作接口。 对每一个数据库写一个实现，实现对不同数据库的统一操作。
 * 
 * <pre>
 * 构建组：x5-base-api
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-22-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface ITableOperator {

	/**
	 * 取得数据库的类型
	 * 
	 * @return
	 */
	public String getDbType();

	/**
	 * 设置spring 的JDBCTemplate
	 * 
	 * @param template
	 */
	public void setJdbcTemplate(JdbcTemplate template);

	/**
	 * 获取字段类型
	 * 
	 * @param columnType
	 *            字段类型
	 * @param charLen
	 *            字符串长度
	 * @param intLen
	 *            整型长度
	 * @param decimalLen
	 *            小数长度
	 * @return
	 */
	public String getColumnType(String columnType, int charLen, int intLen,
			int decimalLen);

	/**
	 * 获取字段类型
	 * 
	 * @param column
	 *            字段
	 * @return
	 */
	public String getColumnType(Column column);

	/**
	 * 根据Tble创建表。
	 * 
	 * @param table
	 *            表
	 * @throws SQLException
	 */
	public void createTable(Table table) throws SQLException;

	/**
	 * 根据表名删除表。
	 * 
	 * @param tableName
	 */
	public void dropTable(String tableName) throws SQLException;;

	/**
	 * 修改表的注释
	 * 
	 * @param tableName
	 *            表名
	 * @param comment
	 *            表注释
	 * @throws SQLException
	 */
	public void updateTableComment(String tableName, String comment)
			throws SQLException;

	/**
	 * 添加列
	 * 
	 * @param tableName
	 *            表名
	 * @param column
	 *            字段
	 * @throws SQLException
	 */
	public void addColumn(String tableName, Column column) throws SQLException;

	/**
	 * 更新列。<br/>
	 * 可以修改列名，字段类型，字段是否非空，字段的注释。
	 * 
	 * @param tableName
	 *            表名
	 * @param columnName
	 *            列名
	 * @param column
	 *            字段
	 * @throws SQLException
	 */
	public void updateColumn(String tableName, String columnName, Column column)
			throws SQLException;

	/**
	 * 添加外键
	 * 
	 * @param pkTableName
	 *            主键表
	 * @param fkTableName
	 *            外键表
	 * @param pkField
	 *            主键
	 * @param fkField
	 *            外键
	 */
	public void addForeignKey(String pkTableName, String fkTableName,
			String pkField, String fkField);

	/**
	 * 删除外键
	 * 
	 * @param tableName
	 * @param keyName
	 */
	public void dropForeignKey(String tableName, String keyName);

	/**
	 * 根据表名，取得相应的主键的列表
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 * @throws SQLException
	 */
	public List<String> getPKColumns(String tableName) throws SQLException;

	/**
	 * 根据表名列表，取得相应的主键的列列表的Map。Map中:key="name",value=表名；key=columns，value=
	 * 相应的主键的列列表。
	 * 
	 * @param tableNames
	 *            表名列表
	 * @return
	 * @throws SQLException
	 */
	public Map<String, List<String>> getPKColumns(List<String> tableNames)
			throws SQLException;

	/**
	 * 设置相应的实现使用的方言
	 * 
	 * @param dialect
	 */
	public void setDialect(IDialect dialect);

	/**
	 * 设置相应的实现使用的数据库
	 * 
	 * @param dbType
	 */
	public void setDbType(String dbType);

	/**
	 * 判断表是否存在。
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean isTableExist(String tableName);

}
