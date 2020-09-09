package com.piggy.coffee.license.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.piggy.coffee.license.mgr.LicenseMgr;

public class LicenseInterceptor implements HandlerInterceptor {

	@Autowired
	private LicenseMgr licenseMgr;

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {

		if (!licenseMgr.isValidLicense()) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			// res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			throw new RuntimeException("无效许可"); // 改成直接返回，不要抛异常
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {

	}
}