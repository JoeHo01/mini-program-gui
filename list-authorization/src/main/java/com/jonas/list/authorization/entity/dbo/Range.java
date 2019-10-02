package com.jonas.list.authorization.entity.dbo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@Data
public class Range implements Serializable {
	private static final long serialVersionUID = 3523238208639020508L;

	private long id;
	private long authId;
	private List<Long> members;
}
