package com.jonas.list.authorization.entity.dbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/27
 */
@Data
@JsonIgnoreProperties({"password"})
public final class Auth implements Serializable {
	private static final long serialVersionUID = 6411600555886515244L;

	private long id;
	private String account;
	private String password;
	private boolean active;
	private List<Role> roles;
}
