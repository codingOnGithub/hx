package com.hotent.base.db.table.factory;

import org.springframework.beans.factory.FactoryBean;

import com.hotent.base.api.db.model.DataSource;
import com.hotent.base.db.table.BaseTableMeta;
import com.hotent.base.db.table.util.SQLConst;

/**
 * TableOperator factorybean，用户创建ITableOperator对象。
 * 
 * <pre>
 * 配置文件：app-beans.xml
 * &lt;bean id="tableOperator" class="com.hotent.base.db.table.factory.TableOperatorFactoryBean">
 * 		&lt;property name="dbType" value="${jdbc.dbType}"/>
 * 		&lt;property name="jdbcTemplate" ref="jdbcTemplate"/>
 * &lt;/bean>
 * </pre>
 * 
 * @author ray
 * 
 */
public class TableMetaFactoryBean implements FactoryBean<BaseTableMeta> {

	private BaseTableMeta tableMeta;

	// 数据库类型
	private String dbType = SQLConst.DB_MYSQL;
	// 数据源
	private DataSource dataSource;

	@Override
	public BaseTableMeta getObject() throws Exception {
		dbType = dataSource.getDbType();
		tableMeta = DatabaseFactory.getTableMetaByDbType(dbType);
		tableMeta.setDataSource(dataSource);
		return tableMeta;
	}

	/**
	 * 设置数据库类型
	 * 
	 * @param dbType
	 */
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * @return 获取数据源
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param 设置数据源
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Class<?> getObjectType() {
		return BaseTableMeta.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
