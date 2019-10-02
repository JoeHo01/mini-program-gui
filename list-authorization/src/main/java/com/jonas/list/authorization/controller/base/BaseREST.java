package com.jonas.list.authorization.controller.base;

import com.jonas.list.authorization.component.exception.ExceptionInfo;
import com.jonas.list.authorization.component.model.Response;
import org.springframework.beans.factory.annotation.Value;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
public class BaseREST {
	@Value("${server.serial-number}")
	private int serialNumber;

	protected Response success(Object data) {
		return new Response(true, serialNumber, data, 0, null);
	}

	protected Response fail(ExceptionInfo e) {
		return new Response(false, serialNumber, null, e.getCode(), e.getMessage());
	}
}
