package com.jonas.list.authorization.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/29
 */
@Data
public class AuthDTO implements Serializable {
	private static final long serialVersionUID = -4231997782242272396L;

	private long id;
	private String account;
	private String password;
}
