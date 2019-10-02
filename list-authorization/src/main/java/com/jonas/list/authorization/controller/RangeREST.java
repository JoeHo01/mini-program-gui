package com.jonas.list.authorization.controller;

import com.jonas.list.authorization.component.exception.WebException;
import com.jonas.list.authorization.component.model.Response;
import com.jonas.list.authorization.controller.base.BaseREST;
import com.jonas.list.authorization.service.RangeService;
import org.springframework.web.bind.annotation.*;

/**
 * Auth: Jo.Ho
 * Date: 2019/10/1
 */
@RestController
@RequestMapping("range")
public class RangeREST extends BaseREST {

	private final RangeService rangeService;

	public RangeREST(RangeService rangeService) {
		this.rangeService = rangeService;
	}

	@PostMapping("insert")
	public Response insertRange(@RequestParam long authId) {
		try {
			return success(rangeService.insertRange(authId));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}

	@PostMapping("member/insert")
	public Response insertMember(@RequestParam long rangeId, @RequestParam long memberId) {
		try {
			return success(rangeService.insertMember(rangeId, memberId));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}

	@PostMapping("member/delete")
	public Response deleteMember(@RequestParam long rangeId, @RequestParam long memberId) {
		try {
			return success(rangeService.deleteMember(rangeId, memberId));
		} catch (WebException e) {
			return fail(e.getExceptionInfo());
		}
	}
}
