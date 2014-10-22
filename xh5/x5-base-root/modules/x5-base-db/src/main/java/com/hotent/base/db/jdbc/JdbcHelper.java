package com.hotent.base.db.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.hotent.base.api.Page;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.db.jdbc.helper.JdbcCmd;
import com.hotent.base.db.jdbc.helper.JdbcCommand;
import com.hotent.base.db.jdbc.helper.ObjectHelper;
import com.hotent.base.db.jdbc.helper.OperatorType;
import com.hotent.base.db.jdbc.model.ColumnModel;
import com.hotent.base.db.mybatis.Dialect;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.base.db.mybatis.domain.PageResult;
import com.hotent.base.db.table.factory.DatabaseFactory;

/**
 * 功能：直接调用JDBC进行查询, 支持多数据源，多种数据, 支持分页查询。<br>
 * 
 * <pre>
 * 1.实现数据库的事务操作
 * 2.查询列表数据
 * 3.查询整形数据
 * 
 * 使用方法：
 * 1.初始化数据库链接。
 * JdbcHelper jdbcHelper= JdbcHelper.getInstance();
 * jdbcHelper.init("estbpm","oracle.jdbc.OracleDriver","jdbc:oracle:thin:@localhost:1521:orcl", "estbpm", "estbpm");
 *  
 * 	jdbcHelper.init("cms","oracle.jdbc.OracleDriver","jdbc:oracle:thin:@localhost:1521:orcl", "testcms", "testcms");
 * 2. 设置当前需要访问的数据库。
 *  jdbcHelper.setCurrentDb("estbpm");
 * 4.如果需要分页查询 需要设置方言。
 *  jdbcHelper.setDialect(new OracleDialect());
 * 3.数据库操作
 *  3.1.查询返回INT
 *  String sql="select count(*) from sys_role where name=:name";
 *  Map map=new HashMap(); 		
 *  map.put("name", "li");
 * 	jdbcHelper.queryForInt( sql,map);
 *  3.2.查询返回列表。
 *  String sql="select * from sys_role where name=:name";
 *  Map map=new HashMap(); 		
 *  map.put("name", "li");
 *  RoleRowMapper mapper=new RoleRowMapper(); 需要实现RowMapper接口
 *  jdbcHelper.queryForList( sql, map,mapper);
 *  
 *  3.3.执行事务操作。
 *  //多个操作在JdbcCommand中实现。
 *  JdbcCommand cmd = new RoleCommand();
 *  jdbcHelper.execute(cmd)
 * 
 * </pre>
 */
public class JdbcHelper<T, PK> {

	private static Log log = LogFactory.getLog(JdbcHelper.class);

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	private static final ThreadLocal<Dialect> dialectHolder = new ThreadLocal<Dialect>();

	@SuppressWarnings("rawtypes")
	private static JdbcHelper jdbcHelper = null;

	private JdbcHelper() {
	}

	/**
	 * 设置方言。
	 * 
	 * @param dialect
	 */
	public void setDialect(Dialect dialect) {
		dialectHolder.set(dialect);
	}

	/**
	 * 获取当前的方言。
	 * 
	 * @return
	 */
	private Dialect getDialect() {
		return dialectHolder.get();
	}

	@SuppressWarnings("rawtypes")
	public static synchronized JdbcHelper getInstance() {
		if (jdbcHelper == null)
			jdbcHelper = new JdbcHelper();
		return jdbcHelper;
	}

	/**
	 * 设置当前可操作的数据库
	 * 
	 * @param alias
	 */
	public void setCurrentDb(String alias) {
		contextHolder.set(alias);
	}

	/**
	 * 取得当前的数据库别名
	 * 
	 * @return
	 */
	public String getCurrentDb() {
		return (String) contextHolder.get();
	}

	/**
	 * 数据源
	 */
	private Map<String, Map<String, Object>> dataSouceMap = new HashMap<String, Map<String, Object>>();

	/**
	 * 通过数据源初始化数据库链接
	 * 
	 * @param dataSource
	 *            数据源
	 * @throws Exception
	 */
	public synchronized void init(
			com.hotent.base.api.db.model.DataSource dataSource)
			throws Exception {
		init(dataSource.getAlias(), dataSource.getDriver(),
				dataSource.getUrl(), dataSource.getUserName(),
				dataSource.getPassword());
	}

	/**
	 * 初始化数据库链接
	 * 
	 * @param alias
	 *            数据库别名
	 * @param className
	 *            数据库驱动名称
	 * @param url
	 *            数据库链接字符串
	 * @param user
	 *            数据库访问的用户
	 * @param pwd
	 *            数据库密码
	 * @throws Exception
	 */
	public synchronized void init(String alias, String className, String url,
			String user, String pwd) throws Exception {
		if (!dataSouceMap.containsKey(alias)) {
			Properties prop = new Properties();
			prop.put("driverClassName", className);
			prop.put("url", url);
			prop.put("username", user);
			prop.put("password", pwd);

			DataSource source = BasicDataSourceFactory.createDataSource(prop);
			JdbcTemplate jdbcTemplate = new JdbcTemplate(source);
			DataSourceTransactionManager tansactionManager = new DataSourceTransactionManager(
					source);
			TransactionTemplate tansactionTemplate = new TransactionTemplate(
					tansactionManager);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("source", source);
			map.put("jdbcTemplate", jdbcTemplate);
			map.put("tansactionTemplate", tansactionTemplate);

			dataSouceMap.put(alias, map);
		}
		this.setCurrentDb(alias);
	}

	/**
	 * 根据别名添加数据源。
	 * 
	 * @param alias
	 *            数据源别名。
	 */
	public void removeAlias(String alias) {
		if (dataSouceMap.containsKey(alias))
			dataSouceMap.remove(alias);
	}

	/**
	 * 返回jdbctemplate对象。
	 * 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		String alias = getCurrentDb();
		Map<String, Object> map = dataSouceMap.get(alias);
		final JdbcTemplate jdbcTemplate = (JdbcTemplate) map
				.get("jdbcTemplate");
		return jdbcTemplate;
	}

	/**
	 * 返回jdbctemplate对象。
	 * 
	 * @return
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		String alias = getCurrentDb();
		Map<String, Object> map = dataSouceMap.get(alias);
		DataSource source = (DataSource) map.get("source");
		final NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				source);
		return template;
	}

	/**
	 * 根据数据源的信息获取JdbcHelper。
	 * 
	 * @param alias
	 *            数据库别名
	 * @param className
	 *            数据库驱动名称
	 * @param url
	 *            数据库链接字符串
	 * @param user
	 *            数据库访问的用户
	 * @param pwd
	 *            数据库密码
	 * @param dbType
	 *            数据库类型
	 * @return
	 * @throws Exception
	 */
	public static JdbcHelper<?, ?> getJdbcHelper(String alias,
			String className, String url, String user, String pwd, String dbType)
			throws Exception {
		JdbcHelper<?, ?> jdbcHelper = getInstance();
		jdbcHelper.init(alias, className, url, user, pwd);
		jdbcHelper.setCurrentDb(alias);
		jdbcHelper.setDialect(DatabaseFactory.getDialect(dbType));
		return jdbcHelper;
	}

	/**
	 * 执行事务操作。<br>
	 * 事务操作在JdbcCommand 实现类中。<br>
	 * 使用方法如下：<br>
	 * 
	 * <pre>
	 * jdbcHelper.execute(new JdbcCommand() {
	 * 	&#064;Override
	 * 	public void execute(DataSource source) throws Exception {
	 * 
	 * 	}
	 * });
	 * </pre>
	 * 
	 * @param cmd
	 */
	public void execute(final JdbcCommand cmd) {
		String alias = getCurrentDb();
		Map<String, Object> map = dataSouceMap.get(alias);
		TransactionTemplate tansactionTemplate = (TransactionTemplate) map
				.get("tansactionTemplate");
		final DataSource source = (DataSource) map.get("source");
		tansactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					cmd.execute(source);
				} catch (Exception e) {
					status.setRollbackOnly();
					log.error(e.getMessage());
				}
			}
		});
	}

	/**
	 * 执行SQL语句。
	 * 
	 * @param sql
	 * @return
	 */
	public void execute(String sql) {
		final JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		jdbcTemplate.execute(sql);
	}

	/**
	 * 执行SQL语句。
	 * 
	 * @param sql
	 * @return
	 */
	public int update(String sql) {
		final JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		return jdbcTemplate.update(sql);
	}

	/**
	 * 执行SQL语句。
	 * 
	 * @param sql
	 * @return
	 */
	public int update(String sql, Object... args) {
		final JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 执行sql语句。
	 * 
	 * @param sql
	 *            sql语句带参数
	 * @param params
	 *            参数Map
	 * @return
	 * @return
	 */
	public int update(String sql, Map<String, ?> params) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		return template.update(sql, params);
	}

	/**
	 * 查询T返回值。
	 * 
	 * @param sql
	 *            SQL语句
	 * @param paramMap
	 *            参数
	 * @param requiredType
	 *            返回的类型
	 * @return
	 */
	public T queryForObject(String sql, Map<String, ?> paramMap,
			Class<T> requiredType) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		return template.queryForObject(sql, paramMap, requiredType);
	}

	/**
	 * 查询int返回值。
	 * 
	 * @param sql
	 *            select count(*) from sys_role where name=:name
	 * @param parameter
	 *            Map map=new HashMap(); map.put("name", "li");
	 * @return
	 */
	public int queryForInt(String sql, Map<String, ?> paramMap) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		return template.queryForObject(sql, paramMap, Integer.class);
	}

	/**
	 * 执行SQL查询语句,。
	 * 
	 * @param sql
	 * @return
	 */
	public long queryForLong(String sql) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	/**
	 * 输入查询语句和查询条件查询列表。
	 * 
	 * @param sql
	 *            查询语句 示例：select * from sys_role where name=:name
	 * @param parameter
	 *            Map map=new HashMap(); map.put("name", "li");
	 * @param rowMap
	 *            需要实现RowMapper接口,有两种实现方式，一种是匿名类，另外一种是实现RowMapper接口。 使用方法如下：<br/>
	 * 
	 *            <pre>
	 * List&lt;Role&gt; list = jdbcHelper.queryForList(sql, null, new RowMapper&lt;Role&gt;() {
	 * 	&#064;Override
	 * 	public Role mapRow(ResultSet rs, int arg1) throws SQLException {
	 * 		Role role = new Role();
	 * 		role.setName(rs.getString(&quot;name&quot;));
	 * 		return role;
	 * 	}
	 * });
	 * </pre>
	 * @return
	 */
	public List<T> queryForList(String sql, Map<String, ?> paramMap,
			RowMapper<T> rowMap) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		return template.query(sql, paramMap, rowMap);
	}

	/**
	 * 根据sql和参数列映射查询列表。
	 * 
	 * @param sql
	 * @param parameter
	 * @param rowMap
	 * @return
	 */
	public List<T> queryForList(String sql, Object[] parameter,
			RowMapper<T> rowMap) {
		JdbcTemplate jdbcTemplate = this.getJdbcTemplate();
		return jdbcTemplate.query(sql, parameter, rowMap);
	}

	/**
	 * 查询列表 ，列表中为map对象。
	 * 
	 * @param sql
	 *            示例：select * from sys_role where name=:name
	 * @param parameter
	 *            Map map=new HashMap(); map.put("name", "li");
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryForList(String sql, Map<String, ?> parameter) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		return (List<T>) template.queryForList(sql, parameter);
	}

	/**
	 * 查询分页数据，分页数据放到List中，一行数据放到map对象中，使用键值对的方式存储。
	 * 
	 * @param sql
	 *            sql语句可以使用?参数化。
	 * @param paramMaps
	 *            参数
	 * @param pageBean
	 *            是否需要分页。
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql,
			Map<String, Object> paramMaps, Page page) {
		List<Map<String, Object>> result = null;
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		if (page != null) {
			int pageSize = page.getPageSize();
			int offset = page.getStartIndex();
			String pageSql = getDialect().getLimitString(sql, offset, pageSize);
			String totalSql = getDialect().getCountString(pageSql);
			result = template.queryForList(pageSql, paramMaps);
			int total = template.queryForObject(totalSql, paramMaps,
					Integer.class);
			result = new PageList<Map<String, Object>>(result, new PageResult(
					offset, pageSize, total));
		} else {
			result = template.queryForList(sql, paramMaps);
		}
		return result;
	}

	/**
	 * 查询对象。
	 * 
	 * <pre>
	 * role = (Role) jdbcHelper.queryForObject(role, new RowMapper&lt;Role&gt;() {
	 * 	&#064;Override
	 * 	public Role mapRow(ResultSet rs, int row) throws SQLException {
	 * 		Role role = new Role();
	 * 		role.setName(rs.getString(&quot;name&quot;));
	 * 		return role;
	 * 	}
	 * });
	 * </pre>
	 * 
	 * @param sql
	 *            sql语句可以使用? 。
	 * @param parameter
	 *            参数使用map传入。
	 * @param rowMap
	 * @return
	 */
	public Object queryForObject(String sql, Map<String, ?> parameter,
			RowMapper<?> rowMap) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		List<?> list = template.query(sql, parameter, rowMap);
		if (BeanUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	/**
	 * 根据主键查询对象。<br>
	 * 使用方法：<br>
	 * 
	 * <pre>
	 * role = (Role) jdbcHelper.queryForObject(role, new RowMapper&lt;Role&gt;() {
	 * 	&#064;Override
	 * 	public Role mapRow(ResultSet rs, int row) throws SQLException {
	 * 		Role role = new Role();
	 * 		role.setName(rs.getString(&quot;name&quot;));
	 * 		return role;
	 * 	}
	 * });
	 * </pre>
	 * 
	 * @param obj
	 * @param rowMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryForObject(T obj, RowMapper<?> rowMap) {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();

		ObjectHelper<T> objHelper = new ObjectHelper<T>();
		objHelper.setModel(obj);
		List<ColumnModel> list = objHelper.getColumns();
		ColumnModel column = objHelper.getPk(list);
		String sql = "select a.* from " + objHelper.getTableName()
				+ " a where " + column.getColumnName() + "=:"
				+ column.getPropery();

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				obj);
		return (T) template.queryForObject(sql, namedParameters, rowMap);

	}

	/**
	 * 分页，list中对象为model对象。<br>
	 * 如果使用分页查询需要设置方言。<br>
	 * 使用方法：<br>
	 * 
	 * <pre>
	 * PageModel pageModel = jdbcHelper.getPage(1, 2, &quot;select * from sys_role&quot;, null,
	 * 		new RowMapper&lt;Role&gt;() {
	 * 			&#064;Override
	 * 			public Role mapRow(ResultSet rs, int arg1) throws SQLException {
	 * 				Role role = new Role();
	 * 				role.setName(rs.getString(&quot;name&quot;));
	 * 				return role;
	 * 			}
	 * 		});
	 * </pre>
	 * 
	 * @param currentPage
	 *            当前页， 从1开始
	 * @param pageSize
	 *            每页显示记录数
	 * @param sql
	 *            sql语句，如果需要参数，请输入如下sql语句，sql=
	 *            "select * from user where name=:name";
	 * @param paraMap
	 *            参数，为map对象
	 * @param rowMap
	 *            映射对象的方法。
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> getPage(Page page, String sql, RowMapper<T> rowMap) {
		int pageSize = page.getPageSize();
		int offset = page.getStartIndex();

		Map<String, ?> map = new HashMap();
		String pageSql = getDialect().getLimitString(sql, offset, pageSize);
		String totalSql = getDialect().getCountString(sql);
		List<T> list = (List<T>) queryForList(pageSql, map, rowMap);
		int total = queryForInt(totalSql, map);
		return new PageList<T>(list, new PageResult(offset, pageSize, total));
	}

	/**
	 * 查询分页数据。 如果传入的pageben为null,那么就不实现分页。
	 * 
	 * <pre>
	 * getPage(sql, new ResultSetExtractor&lt;SysRole&gt;() {
	 * 
	 * 	&#064;Override
	 * 	public SysRole extractData(ResultSet rs) throws SQLException,
	 * 			DataAccessException {
	 * 		SysRole sysRole = new SysRole();
	 * 		rs.getString(&quot;roleName&quot;);
	 * 		return sysRole;
	 * 	}
	 * }, pageBean, params);
	 * </pre>
	 * 
	 * @param sql
	 *            sql语句。
	 * @param rse
	 *            ResultSetExtractor 实现类。
	 * @param page
	 *            分页对象。
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public T getPage(String sql, ResultSetExtractor<T> rse, Page page,
			Map<String, Object> params) throws Exception {
		NamedParameterJdbcTemplate template = this
				.getNamedParameterJdbcTemplate();
		T result = null;
		if (page != null) {
			int pageSize = page.getPageSize();
			int offset = page.getStartIndex();
			String pageSql = getDialect().getLimitString(sql, offset, pageSize);
			String totalSql = getDialect().getCountString(sql);
			result = template.query(pageSql, params, rse);
			int total = template
					.queryForObject(totalSql, params, Integer.class);

			result = (T) new PageList<T>((Collection<? extends T>) result,
					new PageResult(offset, pageSize, total));
		} else {
			result = template.query(sql, params, rse);
		}
		return result;
	}

	/**
	 * 分页返回数据 list 中的数据为Map对象。
	 * 
	 * @param currentPage
	 *            当前页， 从1开始
	 * @param pageSize
	 *            每页显示记录数
	 * @param sql
	 *            sql语句，如果需要参数，请输入如下sql语句，sql=
	 *            "select * from user where name=:name";
	 * @param paraMap
	 *            参数，为map对象
	 * @return
	 */
	public List<T> getPage(int currentPage, int pageSize, String sql,
			Map<String, ?> paraMap, Page page) {
		int offset = (currentPage - 1) * pageSize;
		String pageSql = getDialect().getLimitString(sql, offset, pageSize);
		String totalSql = getDialect().getCountString(sql);
		List<T> list = queryForList(pageSql, paraMap);
		int total = queryForInt(totalSql, paraMap);
		return new PageList<T>(list, new PageResult(offset, pageSize, total));
	}

	/**
	 * 
	 * @param sql
	 *            sql语句，如果需要参数，请输入如下sql语句，sql=
	 *            "select * from user where name=:name";
	 * @param paraMap
	 *            参数，为map对象
	 * @param pageBean
	 *            分页数据
	 * @return
	 */
	public List<T> getPage(String sql, Map<String, ?> paraMap, Page page) {
		int currentPage = page.getStartIndex();
		int pageSize = page.getPageSize();
		return getPage(currentPage, pageSize, sql, paraMap, page);
	}

	/**
	 * 添加对象，需要从外部传入主键。 使用方法：<br>
	 * 
	 * <pre>
	 * Role role;
	 * jdbcHelper.add(role);
	 * </pre>
	 * 
	 * @param obj
	 */
	public void add(T obj) {
		JdbcCmd<T> cmd = new JdbcCmd<T>(obj, OperatorType.Add);
		jdbcHelper.execute(cmd);
	}

	/**
	 * 更新对象。<br>
	 * 使用方法：<br>
	 * 
	 * <pre>
	 * Role role;
	 * jdbcHelper.upd(role);
	 * </pre>
	 * 
	 * @param obj
	 */
	public void upd(T obj) {
		JdbcCmd<T> cmd = new JdbcCmd<T>(obj, OperatorType.Upd);
		jdbcHelper.execute(cmd);
	}

	/**
	 * 删除对象，只要求提供主键。<br>
	 * 使用方法：<br>
	 * 
	 * <pre>
	 * Role role = new Role();
	 * role.setId(3333L);
	 * jdbcHelper.delById(role);
	 * </pre>
	 * 
	 * @param obj
	 */
	public void delById(T obj) {
		JdbcCmd<T> cmd = new JdbcCmd<T>(obj, OperatorType.Del);
		jdbcHelper.execute(cmd);
	}

}
