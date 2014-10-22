/**
 * 描述：TODO
 * 包名：com.hotent.bpmx.persistence.model.query
 * 文件名：BpmDefSortFields.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-1-3-上午11:28:51
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package com.hotent.bpmx.persistence.model.query;

import java.util.ArrayList;
import java.util.List;

import com.hotent.base.api.query.Direction;
import com.hotent.base.api.query.FieldSort;
import com.hotent.base.db.mybatis.domain.DefaultFieldSort;

/**
 * <pre> 
 * 描述：简化流程定义排序字段的构造（对使用者屏蔽字段名称）
 * 构建组：x5-bpmx-core
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-1-3-上午11:28:51
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class BpmDefFieldSorts {
	private List<FieldSort> fieldSorts = new ArrayList<FieldSort>();
	
	/**
	 * fieldSorts
	 * @return  the fieldSorts
	 * @since   1.0.0
	 */
	
	public List<FieldSort> getFieldSorts() {
		return fieldSorts;
	}

	public BpmDefFieldSorts addDefId(){
		DefaultFieldSort defaultFieldSort = new DefaultFieldSort("DEF_ID_",Direction.DESC);
		fieldSorts.add(defaultFieldSort);
		return this;
	}
	public BpmDefFieldSorts addCreateTime(){
		DefaultFieldSort defaultFieldSort = new DefaultFieldSort("CREATE_TIME_",Direction.DESC);
		fieldSorts.add(defaultFieldSort);
		return this;
	}
	public BpmDefFieldSorts addIsMain(){
		DefaultFieldSort defaultFieldSort = new DefaultFieldSort("IS_MAIN_",Direction.DESC);
		fieldSorts.add(defaultFieldSort);
		return this;		
	}
}
