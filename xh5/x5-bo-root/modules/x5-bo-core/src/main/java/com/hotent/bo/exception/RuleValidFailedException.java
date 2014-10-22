package com.hotent.bo.exception;

/**
 * 规则验证失败异常
 * <pre> 
 * 描述：属性规则验证属性值失败的异常
 * 构建组：x5-bo-core
 * 作者：heyifan
 * 邮箱:heyf@jee-soft.cn
 * 日期:2014-2-8-上午11:40:46
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@SuppressWarnings("serial")
public class RuleValidFailedException extends BOBaseException{
	public RuleValidFailedException(){
	}
	
	public RuleValidFailedException(String message){
		super(message);
	}
	
	public RuleValidFailedException(String message, Throwable cause){
		super(message,cause);
	}
	
	public RuleValidFailedException(Throwable cause){
		super(cause);
	}
}
