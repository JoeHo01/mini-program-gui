package com.jonas.list.web.rest.controller;

import com.jonas.list.web.rest.component.ServiceMapper;
import com.jonas.list.web.rest.dao.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@RestController
@RequestMapping("hello")
public class HelloREST {
	@Autowired
	ServiceMapper serviceMapper;

	@Autowired
	MongoRepository mongoRepository;

	@RequestMapping
	public Object hello() {
		return null;
	}
}

class Hello {
	public static void main(String[] args) {
		System.out.println(Base64Utils.encodeToString("5d8dd9692ed0a4031cbeb450".getBytes()));
	}
}
