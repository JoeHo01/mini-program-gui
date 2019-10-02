package com.jonas.list.authorization.entity.dbo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 745865379376057242L;

	private int id;
	private String code;
	private String name;
	private List<Permission> permissions;
}
