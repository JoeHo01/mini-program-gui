package com.jonas.list.authorization.controller;

import com.jonas.list.authorization.component.exception.WebException;
import com.jonas.list.authorization.component.model.Response;
import com.jonas.list.authorization.controller.base.BaseREST;
import com.jonas.list.authorization.entity.dto.AuthDTO;
import com.jonas.list.authorization.service.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/28
 */
@RestController
@RequestMapping("auth")
public class AuthREST extends BaseREST {

	private final AuthService authService;

	public AuthREST(AuthService authService) {
		this.authService = authService;
	}

	@GetMapping("authorization")
	public Response authorization(HttpServletRequest httpServletRequest) {
		try {
			return success(authService.authorization(
					Long.parseLong(httpServletRequest.getHeader("Range")), httpServletRequest.getHeader("Token")
			));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}

	@PostMapping("signIn")
	public Response signIn(@RequestBody AuthDTO auth) {
		try {
			return success(authService.signIn(auth.getAccount(), auth.getPassword()));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}

	@PostMapping("signUp")
	public Response signUp(@RequestBody AuthDTO auth) {
		try {
			return success(authService.signUp(auth.getAccount(), auth.getPassword()));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}

	@PostMapping("reValidEmail")
	public Response reValidEmail(@RequestBody AuthDTO auth) {
		return success(authService.validateEmail(auth.getAccount()));
	}

	@PostMapping("active")
	public Response active(@RequestParam String p) {
		try {
			return success(authService.active(p));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}
}
