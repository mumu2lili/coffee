package com.piggy.coffee.https.controller;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggy.coffee.common.model.ApiResult;

@RequestMapping("evaluations")
@RestController
public class EvaluationController {
	private Logger log = LoggerFactory.getLogger(getClass());

	private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

	@GetMapping("/{tpiID}/evaluate")
	public ApiResult<?> evaluate(@PathVariable("tpiID") String tpiID) {
		ApiResult<?> r = new ApiResult<>();
		r.setMsg(tpiID);
		return r;
	}

	@GetMapping("/{tpiID}/result")
	public ApiResult<Integer> test(@PathVariable("tpiID") String tpiID) {
		ApiResult<Integer> r = new ApiResult<>();

		Integer i = map.get(tpiID);
		if (i == null) {
			i = 1;
		} else {
			i += 1;
		}
		map.put(tpiID, i);
		r.setData(i);

		if (i < 10) {
			r.setMsg("NO");
		} else {
			map.remove(tpiID);
			r.setMsg("YES");
		}

		return r;
	}

}