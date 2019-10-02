package com.jonas.list.web.rest.component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Slf4j
@Setter
@Component
@PropertySource("classpath:service.properties")
@ConfigurationProperties(prefix = "service")
public class ServiceMapper {

	private Map<String, String> DTO = new HashMap<>();
	private Map<String, Class> classDTO = new HashMap<>();

	@PostConstruct
	void init() {
		for (Map.Entry<String, String> entry : DTO.entrySet()) try {
			classDTO.put(entry.getKey(), Class.forName(entry.getValue()));
		} catch (ClassNotFoundException e) {
			log.error("ServiceMapper : " + e.getMessage(), e);
		}
	}

	public Class get(String service) {
		return classDTO.get(service);
	}
}
