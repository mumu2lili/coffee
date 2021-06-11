package com.piggy.coffee.harbor;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarborTest {
	private Logger logger = LoggerFactory.getLogger(HarborTest.class);

	@Test
	public void test() throws IOException  {

		HarborClient harborClient = new HarborClient("http://192.168.56.104", false);


		harborClient.login("admin", "Harbor12345");
     
	}



}
