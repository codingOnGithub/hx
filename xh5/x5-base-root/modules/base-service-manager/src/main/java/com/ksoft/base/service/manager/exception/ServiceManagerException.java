package com.ksoft.base.service.manager.exception;

public class ServiceManagerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceManagerException()
	{
		super();
	}
	
	public ServiceManagerException(String message,Throwable cause)
	{
		super(message, cause);
	}
	
	public ServiceManagerException(String message)
	{
		super(message);
	}

	public ServiceManagerException(Throwable cause)
	{
		super(cause);
	}
}
