package com.jonas.list.web.rest.dao;

import com.jonas.list.web.core.pagination.Page;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/27
 */
@Repository
public class MongoRepository {

	private final String KEY_RANGE = "range";
	private final String KEY_ID = "_id";

	private final MongoTemplate mongoTemplate;

	public MongoRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public <T> T get(String id, long rangeId, Class<T> eClass) {
		return mongoTemplate.findOne(new Query().addCriteria(new Criteria(KEY_ID).is(id).and(KEY_RANGE).is(rangeId)), eClass);
	}

	public <T> List<T> query(String queryShell, Page page, long rangeId, Class<T> eClass) {
		Query query = new BasicQuery(queryShell).addCriteria(new Criteria(KEY_RANGE).is(rangeId));
		page.setCount(mongoTemplate.count(query, eClass));
		return mongoTemplate.find(query.skip(page.getSkip()).limit(page.getPageSize()), eClass);
	}

	public <T> List<T> list(String queryShell, long rangeId, Class<T> eClass) {
		return mongoTemplate.find(new BasicQuery(queryShell).addCriteria(new Criteria(KEY_RANGE).is(rangeId)), eClass);
	}

	public <T> T insert(T document) {
		return mongoTemplate.insert(document);
	}

	public <T> UpdateResult update(String queryShell, String updateShell, long rangeId, Class<T> eClass) {
		return mongoTemplate.updateMulti(new BasicQuery(queryShell).addCriteria(new Criteria(KEY_RANGE).is(rangeId)), new BasicUpdate(updateShell), eClass);
	}

	public <T> DeleteResult delete(String queryShell, long rangeId, Class<T> eClass) {
		return mongoTemplate.remove(new BasicQuery(queryShell).addCriteria(new Criteria(KEY_RANGE).is(rangeId)), eClass);
	}
}
