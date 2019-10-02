package com.jonas.list.web.core.exception;

import lombok.Getter;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Getter
public enum ExceptionInfo {
	PATH_ERROR_NONE_TYPE(120100, "None data type found. "),
	PATH_ERROR_NONE_SERVICE(120102, "None service found. "),
	PATH_ERROR_NONE_OPERATION(120103, "None operation found. "),

	PARAM_ERROR_ILLEGAL(120200, "Param illegal. "),
	;

	private int code;
	private String message;

	ExceptionInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
