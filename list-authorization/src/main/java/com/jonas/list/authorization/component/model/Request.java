package com.jonas.list.authorization.component.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Getter
@Setter
public class Request {
	/** operation type */
	public static final int OPERATION_GET = 1;
	public static final int OPERATION_QUERY = 2;
	public static final int OPERATION_LIST = 3;
	public static final int OPERATION_INSERT = 4;
	public static final int OPERATION_UPDATE = 5;
	public static final int OPERATION_DELETE = 6;

	private long timestamp;
	private String body;
}
