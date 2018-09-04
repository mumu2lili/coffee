package com.piggy.coffee.gojs.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public static String getBasePath(HttpServletRequest req) {

		String path = req.getContextPath();
		String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";

		return basePath;
	}
}
