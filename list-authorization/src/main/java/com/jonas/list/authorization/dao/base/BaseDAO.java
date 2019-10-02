package com.jonas.list.authorization.dao.base;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
public class BaseDAO <T> {
	protected JdbcTemplate jdbcTemplate;

	protected BeanPropertyRowMapper<T> mapper;

	public BaseDAO(Class<T> eClass, JdbcTemplate jdbcTemplate) {
		this.mapper = new BeanPropertyRowMapper<>(eClass);
		this.jdbcTemplate = jdbcTemplate;
	}

	protected <C> C get(String sql, Object[] values, Class<C> type) {
		try {
			return jdbcTemplate.queryForObject(sql, values, type);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected T get(String sql, Object[] values) {
		try {
			return jdbcTemplate.queryForObject(sql, values, mapper);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected <C> List<C> select(String sql, Object[] values, Class<C> type) {
		try {
			return jdbcTemplate.queryForList(sql, values, type);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected List<T> select(String sql, Object[] values) {
		try {
			return jdbcTemplate.queryForList(sql, values, mapper.getMappedClass());
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	protected long update(final String sql, final Object[] values) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator(){
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
						for (int i = 0; i < values.length; i++) {
							statement.setObject(i + 1, values[i]);
						}
						return statement;
					}
				},keyHolder);
		return keyHolder.getKey() == null ? 0 : keyHolder.getKey().longValue();
	}
}
