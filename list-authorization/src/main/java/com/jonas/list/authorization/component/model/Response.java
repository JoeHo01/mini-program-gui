package com.jonas.list.authorization.component.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Getter
@Setter
public class Response {
	private boolean success;
	private int serialNumber;
	private Object data;
	private int errorCode;
	private String errorMessage;

	public Response(boolean success, int serialNumber, Object data, int errorCode, String errorMessage) {
		this.success = success;
		this.serialNumber = serialNumber;
		this.data = data;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
