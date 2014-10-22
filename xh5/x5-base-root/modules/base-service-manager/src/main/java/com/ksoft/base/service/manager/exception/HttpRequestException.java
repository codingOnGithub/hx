package com.ksoft.base.service.manager.exception;

public class HttpRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HttpRequestException()
	{
		super();
	}
	
	public HttpRequestException(String message,Throwable cause)
	{
		super(message, cause);
	}
	
	public HttpRequestException(String message)
	{
		super(message);
	}

	public HttpRequestException(Throwable cause)
	{
		super(cause);
	}
}
