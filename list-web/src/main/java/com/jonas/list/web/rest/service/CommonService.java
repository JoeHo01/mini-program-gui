package com.jonas.list.web.rest.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jonas.list.web.core.exception.WebException;
import com.jonas.list.web.core.pagination.Page;
import com.jonas.list.web.rest.dao.MongoRepository;
import com.jonas.list.web.rest.entity.base.DTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Slf4j
@Service
public class CommonService {

	private final MongoRepository mongoRepository;

	public CommonService(MongoRepository mongoRepository) {
		this.mongoRepository = mongoRepository;
	}

	public <T> T get(String id, long rangeId, Class<T> eClass) {
		return mongoRepository.get(id, rangeId, eClass);
	}

	public <T> List<T> query(String shell, Page page, long rangeId, Class<T> eClass) {
		return mongoRepository.query(shell, page, rangeId, eClass);
	}

	public <T> List<T> list(String shell, long rangeId, Class<T> eClass) {
		return mongoRepository.list(shell, rangeId, eClass);
	}

	public <T extends DTO> T insert(String json, long range, Class<T> eClass) throws WebException {
		try {
			T document = JSONObject.parseObject(json, eClass);
			document.setCreatedTime(new Date().getTime());
			document.setRange(range);
			document.setId(null);
			return mongoRepository.insert(document);
		}catch (JSONException e) {
			log.error(e.getMessage(), e);
			throw new WebException(e.getMessage(), e);
		}
	}

	public <T> String update(String script, Class<T> eClass) {
		return null;
	}

	public <T> String delete(String script, Class<T> eClass) {
		return null;
	}
}
