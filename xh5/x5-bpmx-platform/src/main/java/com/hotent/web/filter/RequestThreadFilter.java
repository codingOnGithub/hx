package com.hotent.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.hotent.web.context.RequestContext;
/**
 * 
 * <pre> 
 * 描述：用于拦截请求以获取HttpSevletRequest对象，以供后续其他类使用,如可获取当前用户请求的IP等信息
 *       用于相同线程间共享Request对象
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱: chensx@jee-soft.cn
 * 日期:2014-1-4-下午4:01:41
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class RequestThreadFilter implements Filter{
	
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		try{
			RequestContext.setHttpServletRequest((HttpServletRequest) request);
			//RequestContext.setHttpServletResponse((HttpServletResponse)response);
			chain.doFilter(request, response);
		}finally{
			
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void destroy() {
		
	}
}
