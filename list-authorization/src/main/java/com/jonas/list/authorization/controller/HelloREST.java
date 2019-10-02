package com.jonas.list.authorization.controller;

import com.jonas.list.authorization.dao.AuthDAO;
import com.jonas.list.authorization.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/29
 */
@RequestMapping("hello")
@RestController
public class HelloREST {
	@Autowired
	AuthService authService;

	@RequestMapping("signIn")
	public Object signIn(@RequestParam("a") String a, @RequestParam("b") String b) {
		return null;
	}
}

class Hello {

	public static void main(String[] args) {
		System.out.println(1%4);
		System.out.println(2%4);
		System.out.println(3%4);
		System.out.println(4%4);
		System.out.println(5%4);
		System.out.println(6%4);
	}

}
