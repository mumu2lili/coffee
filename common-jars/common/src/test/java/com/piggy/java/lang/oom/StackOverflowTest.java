package com.piggy.java.lang.oom;

import org.junit.Test;

public class StackOverflowTest {
	private static class OverflowObject {

		private int stackLength;

		public void overflowLeak() {
			++stackLength;
			overflowLeak();
		}
	}

	/**
	 *
	 * VM Args: -Xss128k
	 */
	@Test
	public void testStackOverflow() {
		OverflowObject obj = new OverflowObject();
		try {
			obj.overflowLeak();
		} catch (Throwable e) {
			System.out.println(obj.stackLength);
		}
	}

}
