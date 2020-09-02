package com.piggy.coffee.license;

import org.junit.Test;

public class LicenseCreatorTest {

	@Test
	public void testCreate() {

		LicenseCreator lc = new LicenseCreator();
		lc.setParam("license.properties");
		lc.create();
	}

}
