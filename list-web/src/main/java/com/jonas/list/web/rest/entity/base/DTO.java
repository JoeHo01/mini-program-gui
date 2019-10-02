package com.jonas.list.web.rest.entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/28
 */
@Getter
@Setter
@Document(collection = "project")
public class DTO {
	protected String id;
	protected long range;
	protected long createdTime;
	protected long updatedTime;
	protected String name;
}
