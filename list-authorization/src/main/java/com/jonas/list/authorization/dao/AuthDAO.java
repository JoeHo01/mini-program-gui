package com.jonas.list.authorization.dao;

import com.jonas.list.authorization.dao.base.BaseDAO;
import com.jonas.list.authorization.entity.dbo.Auth;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/28
 */
@Repository
public class AuthDAO extends BaseDAO<Auth> {

	public AuthDAO(JdbcTemplate jdbcTemplate) {
		super(Auth.class, jdbcTemplate);
	}

	public boolean validateActive(String account) {
		return jdbcTemplate.queryForObject(
				"SELECT (SELECT active FROM hub_auth WHERE account = ?) IS true"
				, new String[]{account}, Boolean.class);
	}

	public Long validatePassword(String account, String password) {
		return get(
				"SELECT id FROM hub_auth WHERE account = ? AND password = ?"
				, new String[]{account, password}, Long.class);
	}

	public long addAuth(final String account, final String password) {
		return update(
				"INSERT INTO hub_auth (account, password, active) VALUES (?, ?, false)"
				, new String[]{account, password});
	}

	public long activeAuth(String account) {
		return update(
				"UPDATE hub_auth SET active = true WHERE account = ?"
				, new String[]{account});
	}

	public int deleteAuth(String account) {
		return jdbcTemplate.update(
				"DELETE FROM hub_auth WHERE account = ?"
				, account);
	}
}
