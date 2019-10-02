package com.jonas.list.web.rest.controller.base;

import com.jonas.list.web.core.exception.ExceptionInfo;
import com.jonas.list.web.core.model.Response;
import com.jonas.list.web.core.pagination.Page;
import org.springframework.beans.factory.annotation.Value;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
public class BaseREST {

	@Value("${server.serial-number}")
	private int serialNumber;

	protected Response success(Object data) {
		return new Response(true, serialNumber, data, null, 0, null);
	}

	protected Response success(Object data, Page page) {
		return new Response(true, serialNumber, data, page, 0, null);
	}

	protected Response fail(ExceptionInfo e) {
		return new Response(true, serialNumber, null, null, e.getCode(), e.getMessage());
	}

	protected Response fail(String message) {
		return new Response(true, serialNumber, null, null, 0, message);
	}

}
