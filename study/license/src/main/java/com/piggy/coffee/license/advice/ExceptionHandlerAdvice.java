package com.piggy.coffee.license.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.piggy.coffee.common.constant.ApiResultCsts;
import com.piggy.coffee.common.model.ApiResult;

@ControllerAdvice
public class ExceptionHandlerAdvice {

	private Logger log = LoggerFactory.getLogger(getClass());



	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResult<?>> exception(Exception ex) {
		ApiResult<?> r = new ApiResult<>();
		r.setCode(ApiResultCsts.FAIL);
		r.setMsg("未知错误");
		ResponseEntity<ApiResult<?>> res = new ResponseEntity<>(r, HttpStatus.OK);

		log.error("未知错误", ex);

		return res;
	}

}
