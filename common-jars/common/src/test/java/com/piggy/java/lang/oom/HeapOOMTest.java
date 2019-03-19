package com.piggy.java.lang.oom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class HeapOOMTest {
	private static class OOMObject {

	}

	private static class MemeoryLeakObject {

		private String id;

		public MemeoryLeakObject(String id) {
			super();
			this.id = id;
		}

		/**
		 * 只重写equals，可能造成内存泄漏。
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MemeoryLeakObject other = (MemeoryLeakObject) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

	}

	/**
	 * 不可完全避免，但应近可能减少。<br/>
	 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 */
	@Test
	public void testHeapOOM() {
		List<OOMObject> list = new ArrayList<>();
		while (true) {
			list.add(new OOMObject());
		}
	}

	/**
	 * 可避免。<br/>
	 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 */
	@Test
	public void testMemeoryLeak() {
		Set<MemeoryLeakObject> set = new HashSet<>(100000);
		String id = "1";
		while (true) {
			MemeoryLeakObject obj = new MemeoryLeakObject(id);
			while (!set.contains(obj)) {
				set.add(obj);
			}
		}
	}

}
