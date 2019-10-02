package com.jonas.list.web.rest.controller;

import com.jonas.list.web.core.exception.ExceptionInfo;
import com.jonas.list.web.core.exception.WebException;
import com.jonas.list.web.core.util.CodeUtil;
import com.jonas.list.web.core.pagination.Page;
import com.jonas.list.web.rest.component.ServiceMapper;
import com.jonas.list.web.rest.controller.base.BaseREST;
import com.jonas.list.web.core.model.Request;
import com.jonas.list.web.core.model.Response;
import com.jonas.list.web.rest.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Auth: Jo.Ho
 * Date: 2019/9/26
 */
@Slf4j
@RestController
@RequestMapping("data")
public class CommonREST extends BaseREST {

	private final CommonService commonService;

	private final ServiceMapper serviceMapper;

	public CommonREST(CommonService commonService, ServiceMapper serviceMapper) {
		this.commonService = commonService;
		this.serviceMapper = serviceMapper;
	}

	@GetMapping("{service}/{dateType}")
	public Response get(@PathVariable String service, @PathVariable int dateType,
	                    @RequestParam(value = "p", required = false) String param,
	                    @RequestParam(value = "pageSize", required = false) Integer pageSize,
	                    @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
	                    HttpServletRequest httpServletRequest) {
		Class eClass = serviceMapper.get(service);
		if (eClass == null) {
			log.error(ExceptionInfo.PATH_ERROR_NONE_SERVICE.getMessage() + service);
			return fail(ExceptionInfo.PATH_ERROR_NONE_SERVICE);
		}
		try {
			switch (dateType) {
				case Request.OPERATION_GET:
					return success(commonService.get(CodeUtil.base64Decode(param), Long.parseLong(httpServletRequest.getHeader("range")), eClass));
				case Request.OPERATION_QUERY:
					Page page = new Page(pageSize, pageNumber);
					return success(commonService.query(CodeUtil.base64Decode(param), page, Long.parseLong(httpServletRequest.getHeader("range")), eClass), page);
				case Request.OPERATION_LIST:
					return success(commonService.list(CodeUtil.base64Decode(param), Long.parseLong(httpServletRequest.getHeader("range")), eClass));
				default:
					log.error(ExceptionInfo.PATH_ERROR_NONE_TYPE.getMessage() + dateType);
					return fail(ExceptionInfo.PATH_ERROR_NONE_TYPE);
			}
		}catch (IllegalArgumentException e) {
			log.error(ExceptionInfo.PARAM_ERROR_ILLEGAL.getMessage() + e.getMessage(), e);
			return fail(ExceptionInfo.PARAM_ERROR_ILLEGAL);
		}
	}

	@PostMapping("{service}/{operation}")
	public Response post(@PathVariable String service, @PathVariable int operation, @Valid @RequestBody Request request,
	                     HttpServletRequest httpServletRequest) {
		Class eClass = serviceMapper.get(service);
		if (eClass == null) {
			log.error(ExceptionInfo.PATH_ERROR_NONE_SERVICE.getMessage() + service);
			return fail(ExceptionInfo.PATH_ERROR_NONE_SERVICE);
		}
		try {
			switch (operation) {
				case Request.OPERATION_INSERT:
					try {
						return success(commonService.insert(
								CodeUtil.base64Decode(request.getBody())
								, Long.parseLong(httpServletRequest.getHeader("range"))
								, eClass));
					}catch (WebException e) {
						return fail(e.getMessage());
					}
				case Request.OPERATION_UPDATE:
					return success(commonService.update(CodeUtil.base64Decode(request.getBody()), eClass));
				case Request.OPERATION_DELETE:
					return success(commonService.delete(CodeUtil.base64Decode(request.getBody()), eClass));
				default:
					log.error(ExceptionInfo.PATH_ERROR_NONE_OPERATION.getMessage() + service);
					return fail(ExceptionInfo.PATH_ERROR_NONE_OPERATION);
			}
		}catch (IllegalArgumentException e) {
			log.error(ExceptionInfo.PARAM_ERROR_ILLEGAL.getMessage() + e.getMessage(), e);
			return fail(ExceptionInfo.PARAM_ERROR_ILLEGAL);
		}
	}
}
