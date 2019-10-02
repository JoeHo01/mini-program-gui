package com.jonas.list.authorization.entity.dbo;

import lombok.Data;

import java.io.Serializable;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@Data
public class Permission implements Serializable {
	private static final long serialVersionUID = 1861360228252595808L;

	private int id;
	private String code;
	private String name;
	private int serviceId;
}
