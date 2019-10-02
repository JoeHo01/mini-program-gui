package com.jonas.list.authorization.service;

import com.jonas.list.authorization.component.exception.ExceptionInfo;
import com.jonas.list.authorization.component.exception.WebException;
import com.jonas.list.authorization.dao.RangeDAO;
import org.springframework.stereotype.Service;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@Service
public class RangeService {
	private final RangeDAO rangeDAO;

	public RangeService(RangeDAO rangeDAO) {
		this.rangeDAO = rangeDAO;
	}

	public long insertRange(long authId) throws WebException {
		long id = rangeDAO.insertRange(authId);
		if (id > 0)
			return id;
		else
			throw new WebException(ExceptionInfo.RANGE_INSERT_ERROR);
	}

	public boolean insertMember(long rangeId, long memberId) throws WebException {
		if (rangeDAO.insertMember(rangeId, memberId) == 1)
			return true;
		else
			throw new WebException(ExceptionInfo.RANGE_MEMBER_INSERT_ERROR);
	}


	public boolean deleteMember(long rangeId, long memberId) throws WebException {
		if (rangeDAO.deleteMember(rangeId, memberId) == 1)
			return true;
		else
			throw new WebException(ExceptionInfo.RANGE_MEMBER_DELETE_ERROR);
	}
}
