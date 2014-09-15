package com.hotent.platform.tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.hotent.core.util.ContextUtil;


/**
 * 获取流程状态
 * @author zxh
 *
 */
public class ProcessStatusTag  extends BodyTagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	public int doEndTag() throws JspTagException {

		try {
			String str = getProcessStatus(this.status);
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}



	private String getProcessStatus(Short status) {
		switch (status) {	
			case 0:
				return "<font color='red'>"+ContextUtil.getMessages("processRun.status.suspend")+"</font>";
			case 1:
				return "<font color='green'>"+ContextUtil.getMessages("processRun.status.running")+"</font>";
			case 2:
				return "<font color='green'>"+ContextUtil.getMessages("processRun.status.end")+"</font>";
			case 3:
				return "<font color='red'>"+ContextUtil.getMessages("processRun.endByUser")+"</font>";
			case 4:
				return "<font color='brown'>"+ContextUtil.getMessages("processRun.draft")+"</font>";
			case 5:
				return "<font color='red'>"+ContextUtil.getMessages("processRun.status.recover")+"</font>";
			case 6:
				return "<font color='red'>"+ContextUtil.getMessages("processRun.status.reject")+"</font>";
			case 7:
				return "<font color='red'>"+ContextUtil.getMessages("processRun.status.retrieved")+"</font>";
			case 10:
				return "<font color='red'>"+ContextUtil.getMessages("processRun.status.logicDelete")+"</font>";
			default:
				break;
		}	
		return "";
	}
	
	private Short status;
	

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}



}
