package com.hotent.base.db.jdbc.helper;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * 根据对象实现添加，更新，删除、查询。<br>
 * 
 * <pre>
 * Role obj;
 * DbCmd&lt;T&gt; cmd = new DbCmd&lt;T&gt;();
 * cmd.setModel(obj);
 * cmd.setOperatorType(OperatorType.Add);
 * jdbcHelper.execute(cmd);
 * </pre>
 * 
 * @author hotent
 * 
 * @param <T>
 */
public class JdbcCmd<T> implements JdbcCommand {

	private ObjectHelper<T> objectHelper;
	/**
	 * 对象
	 */
	private T obj;
	/**
	 * 操作类型
	 */
	private OperatorType type;

	
	public JdbcCmd() {
		super();
	}

	public JdbcCmd(T obj, OperatorType operatorType) {
		this.setModel(obj);
		this.setOperatorType(operatorType);
	}

	public void setModel(T obj) {
		objectHelper = new ObjectHelper<T>();
		objectHelper.setModel(obj);
		this.obj = obj;
	}

	public void setOperatorType(OperatorType type) {
		this.type = type;
	}

	@Override
	public void execute(DataSource source) throws Exception {
		String sql = "";
		if (type == OperatorType.Add) {
			sql = objectHelper.getAddSql();
		} else if (type == OperatorType.Upd) {
			sql = objectHelper.getUpdSql();
		} else if (type == OperatorType.Del) {
			sql = objectHelper.getDelSql();
		}
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				this.obj);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				source);
		template.update(sql, namedParameters);
	}

}
