package com.hotent.base.api.db.model;

/**
 * 
 * 数据源 连接数据库的数据源 接口
 * 
 * <pre>
 * 构建组：x5-base-api
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-22-上午11:35:40
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public interface DataSource {

	/**
	 * 别名
	 * 
	 * @return the alias
	 */
	public String getAlias();

	/**
	 * 驱动名称
	 * 
	 * @return the driver
	 */
	public String getDriver();

	/**
	 * 数据库URL
	 * 
	 * @return the url
	 */
	public String getUrl();

	/**
	 * 用户名
	 * 
	 * @return the userName
	 */
	public String getUserName();

	/**
	 * 密码
	 * 
	 * @return the password
	 */
	public String getPassword();

	/**
	 * 数据库类型
	 * 
	 * @return the dbType
	 */
	public String getDbType();
}
