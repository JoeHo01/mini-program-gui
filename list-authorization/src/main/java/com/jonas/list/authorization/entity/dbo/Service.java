package com.jonas.list.authorization.entity.dbo;

import lombok.Data;

import java.io.Serializable;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@Data
public class Service implements Serializable {
	private static final long serialVersionUID = 1099338332753653290L;

	int id;
	String code;
	String name;
}
