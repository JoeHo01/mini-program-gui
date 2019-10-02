package com.jonas.list.web.core.model;

import com.jonas.list.web.core.pagination.Page;
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
	private Page page;
	private int errorCode;
	private String errorMessage;

	public Response(boolean success, int serialNumber, Object data, Page page, int errorCode, String errorMessage) {
		this.success = success;
		this.serialNumber = serialNumber;
		this.data = data;
		this.page = page;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
}
