package com.hotent.base.core.soap;

import javax.xml.soap.SOAPFault;

/**
 * 调用出错异常
 * 
 */
public class InvokeException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 调用webservice出错时返回的{@link SOAPFault#getFaultCode()}值.
	 */
	private String code;

	/**
	 * 调用webservice出错时返回的{@link SOAPFault#getFaultString()}值.
	 */
	private String msg;

	public InvokeException(String code, String msg) {
		this(code, msg, null);
	}

	public InvokeException(String code, String msg, Throwable e) {
		super("[" + code + "]" + msg, e);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
