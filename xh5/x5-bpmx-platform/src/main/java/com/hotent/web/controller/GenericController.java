package com.hotent.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.base.api.model.ResultMessage;
import com.hotent.base.api.query.Direction;
import com.hotent.base.api.query.FieldLogic;
import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.api.query.FieldSort;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.core.util.string.StringUtil;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryField;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultFieldSort;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.bpmx.core.context.ContextUtil;
import com.hotent.web.context.RequestContext;
import com.hotent.web.util.RequestUtil;


/**
 * <pre> 
 * 描述：通用控制器
 * 构建组：x5-bpmx-platform
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-1-4-下午2:33:01
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class GenericController {
	
	protected Logger logger = LoggerFactory.getLogger(BaseController.class);
	/**
	 * 返回成功的JSON字符串
	 */
	public final String SUCCESS = "{success:true}";
	/**
	 * 返回失败字符串
	 */
	public final String FAILURE = "{success:false}";

	/**
	 * 保存操作的信息
	 */
	private MessageSourceAccessor messages;

	public static final String MESSAGES_KEY = "successMessages";

	public static final String ERRORS = "errors";

	/**
	 * 通过Request的URL对应转成对应的JSP文件展示
	 * 
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getAutoView() throws Exception {
		HttpServletRequest request = RequestContext.getHttpServletRequest();
		
		String requestURI = request.getRequestURI();
		// 处理RequestURI
		logger.debug("requestURI:" + requestURI);
		String contextPath = request.getContextPath();

		requestURI = requestURI.replace(".ht", "");
		int cxtIndex = requestURI.indexOf(contextPath);
		if (cxtIndex != -1) {
			requestURI = requestURI.substring(cxtIndex + contextPath.length());
		}
		String jspPath=null;
		String[] paths = requestURI.split("[/]");
		
		if (paths != null && paths.length == 5) {
			jspPath = "/" + paths[1] + "/" + paths[2] + "/" + paths[3] + StringUtil.upperFirst(paths[4]) + ".jsp";
		} else if (paths != null && paths.length == 4) {
			jspPath = "/" + paths[1] + "/" + paths[2] + StringUtil.upperFirst(paths[3]) + ".jsp";
		} else {
			jspPath=requestURI + ".jsp";
		}
		
		return new ModelAndView(jspPath);

	}

	@Autowired
	public void setMessages(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}
	
	/**
	 * 返回出错或成功的信息。
	 * 
	 * @param writer
	 * @param resultMsg
	 * @param successFail
	 */
	protected void writeResultMessage(PrintWriter writer, String resultMsg,
			int successFail) {
		ResultMessage resultMessage = new ResultMessage(successFail, resultMsg);
		writeResultMessage(writer, resultMessage);
	}

	/**
	 * 返回出错或成功的信息。
	 * 
	 * @param writer
	 * @param resultMessage
	 */
	protected void writeResultMessage(PrintWriter writer,
			ResultMessage resultMessage) {
		writer.print(resultMessage);
	}

	/**
	 * 保存错误消息（errors）
	 * 
	 * @param request
	 * @param error
	 */
	public void saveError(HttpServletRequest request, String msg) {
		saveMessage(request, ERRORS, msg);
	}

	/**
	 * 保存消息（successMessages）
	 * 
	 * @param request
	 * @param msg
	 */
	public void saveMessage(HttpServletRequest request, String msg) {
		saveMessage(request, MESSAGES_KEY, msg);
	}

	/**
	 * 
	 * @param request
	 * @param key
	 * @param msg
	 */
	@SuppressWarnings("unchecked")
	public void saveMessage(HttpServletRequest request, String key, String msg) {
		List<Object> messages = (List<Object>) request.getSession()
				.getAttribute(key);
		if (messages == null) {
			messages = new ArrayList<Object>();
		}
		messages.add(msg);
		request.getSession().setAttribute(key, messages);
	}

	/**
	 * 根据key和local取得键值。
	 * 
	 * @param msgKey
	 * @param locale
	 * @return
	 */
	public String getText(String msgKey, Locale locale) {
		return messages.getMessage(msgKey, locale);
	}

	/**
	 * 根据键值，参数，local取得键值。
	 * 
	 * @param msgKey
	 * @param arg
	 * @param locale
	 * @return
	 */
	public String getText(String msgKey, String arg, Locale locale) {
		return getText(msgKey, new Object[] { arg }, locale);
	}

	/**
	 * 根据键值，参数数组，local取得键值。
	 * 
	 * @param msgKey
	 * @param args
	 * @param locale
	 * @return
	 */
	public String getText(String msgKey, Object[] args, Locale locale) {
		return messages.getMessage(msgKey, args, locale);
	}

	/**
	 * 根据键值参数取得键值。
	 * 
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public String getText(String msgKey, Object... args) {
		return messages.getMessage(msgKey, args,ContextUtil.getLocale());
	}

	/**
	 * 根据键值取得键值。
	 * 
	 * @param msgKey
	 * @return
	 */
	public String getText(String msgKey) {
		return messages.getMessage(msgKey, ContextUtil.getLocale());
	}

	/**
	 * 取得资源键值
	 * 
	 * @param msgKey
	 * @param arg
	 * @param request
	 * @return
	 */
	protected String getText(String msgKey, String arg,
			HttpServletRequest request) {
		Locale locale = ContextUtil.getLocale();
		return getText(msgKey, arg, locale);
	}

	/**
	 * 取得资源键值
	 * 
	 * @param msgKey
	 * @param args
	 * @param request
	 * @return
	 */
	protected String getText(String msgKey, Object[] args,
			HttpServletRequest request) {
		Locale locale = ContextUtil.getLocale();
		return getText(msgKey, args, locale);
	}

	/**
	 * 返回构建的QueryFilter
	 * @param request
	 * @return 
	 * QueryFilter
	 * @exception 
	 * @since  1.0.0
	 */
	protected QueryFilter getQuerFilter(HttpServletRequest request){
		DefaultQueryFilter queryFilter=new DefaultQueryFilter();
		try {
			String pageSize=request.getParameter("page");
			String rows=(String)request.getParameter("rows");
			//设置查询字段
			FieldLogic andFieldLogic=RequestUtil.getFieldLogic(request);
			queryFilter.setFieldLogic(andFieldLogic);
			//设置分页
			if(StringUtil.isNotEmpty(pageSize) && StringUtil.isNotEmpty(rows)){
				DefaultPage page=new DefaultPage();
				page.setPage(Integer.parseInt(pageSize));
				page.setLimit(Integer.parseInt(rows));
				queryFilter.setPage(page);
			}
			//设置排序字段
			String sort=request.getParameter("sort");
			String order=request.getParameter("order");
			if(StringUtil.isNotEmpty(sort)&&StringUtil.isNotEmpty(rows)){
				List<FieldSort> fieldSorts=new ArrayList<FieldSort>();
				fieldSorts.add(new DefaultFieldSort(sort, Direction.fromString(order)));
				queryFilter.setFieldSortList(fieldSorts);
			}
		} catch (Exception e) {
		}
		
		return queryFilter;
	}
	
	
}
