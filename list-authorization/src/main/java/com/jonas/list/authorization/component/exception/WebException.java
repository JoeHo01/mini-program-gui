package com.jonas.list.authorization.component.exception;

import lombok.Getter;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/29
 */
@Getter
public class WebException extends Exception{

	private ExceptionInfo exceptionInfo;

	public WebException(ExceptionInfo exceptionInfo) {
		super(exceptionInfo.getMessage());
		this.exceptionInfo = exceptionInfo;
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
	}
}
