package com.jonas.list.authorization.dao;

import com.jonas.list.authorization.dao.base.BaseDAO;
import com.jonas.list.authorization.entity.dbo.Range;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@Repository
public class RangeDAO extends BaseDAO<Range> {

	public RangeDAO(JdbcTemplate jdbcTemplate) {
		super(Range.class, jdbcTemplate);
	}

	public long insertRange(long authId) {
		return update(
				"INSERT INTO hub_range (authId) VALUES (?)"
				, new Long[]{authId});
	}

	public int insertMember(long rangeId, long authId) {
		return jdbcTemplate.update(
				"INSERT INTO rel_range_member (rangeId, authId) VALUES (?, ?)"
				, rangeId, authId);
	}

	public int deleteMember(long rangeId, long authId) {
		return jdbcTemplate.update(
				"delete from rel_range_member where rangeId = ? and authId = ?"
				, rangeId, authId);
	}

	public boolean validate(long rangeId, long authId) {
		return jdbcTemplate.queryForObject(
				"SELECT (SELECT count(0) FROM rel_range_member WHERE rangeId = ? AND authId = ?) = 1"
				, Boolean.class, rangeId, authId);
	}
}
