package com.jonas.list.web.core.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Getter
@Setter
public class Request implements Serializable {
	private static final long serialVersionUID = -8031596255564834455L;

	/** operation type */
	public static final int OPERATION_GET = 1;
	public static final int OPERATION_QUERY = 2;
	public static final int OPERATION_LIST = 3;
	public static final int OPERATION_INSERT = 4;
	public static final int OPERATION_UPDATE = 5;
	public static final int OPERATION_DELETE = 6;

	private long timestamp;

	@Max(512)
	private String body;
}
