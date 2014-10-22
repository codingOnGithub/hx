package com.hotent.base.db.mybatis.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 *  包含“分页”信息的List，这个对象包含分页数据和分页结果。
 * <pre> 
 * 构建组：x5-base-db
 * 作者：ray
 * 邮箱:zhangyg@jee-soft.cn
 * 日期:2014-1-3-下午3:59:14
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class PageList<E> extends ArrayList<E> implements Serializable {

    private static final long serialVersionUID = 1412759446332294208L;
    
    private PageResult pageResult;

    public PageList() {}
    
	public PageList(Collection<? extends E> c) {
		super(c);
	}

	
	public PageList(Collection<? extends E> c,PageResult p) {
        super(c);
        this.pageResult = p;
    }

    public PageList(PageResult p) {
        this.pageResult = p;
    }


	/**
	 * 得到分页器，通过Paginator可以得到总页数等值
	 * @return
	 */
	public PageResult getPageResult() {
		return pageResult;
	}

	
}
