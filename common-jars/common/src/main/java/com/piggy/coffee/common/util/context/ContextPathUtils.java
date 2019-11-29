package com.piggy.coffee.common.util.context;

/**
 * 用于 web环境
 * 
 * @author mumu
 *
 */
public final class ContextPathUtils {

	private static String contextPath;

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		ContextPathUtils.contextPath = contextPath;
	}

}
