package com.jonas.list.authorization.component.exception;

import lombok.Getter;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Getter
public enum ExceptionInfo {
	AUTH_VALIDATE_ERROR_TOKEN(110100, "Token illegal. "),
	AUTH_VALIDATE_ERROR_RANGE(110101, "Range illegal. "),
	AUTH_VALIDATE_ERROR_INACTIVE(110102, "Account inactive. "),
	AUTH_VALIDATE_ERROR_WRONG_PWD(110103, "Password wrong. "),
	AUTH_VALIDATE_ERROR_TO_ACTIVE(110104, "Active account error . "),
	AUTH_INSERT_ERROR_DUPLICATE(110200, "Account duplicate. "),

	RANGE_INSERT_ERROR(120100, "Error insert range. "),
	RANGE_MEMBER_INSERT_ERROR(120200, "Error insert range member. "),
	RANGE_MEMBER_DELETE_ERROR(120201, "Error delete range member. "),
	;
	private int code;
	private String message;

	ExceptionInfo(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
