package com.piggy.coffee.common.util.net;

import java.util.List;

import org.junit.Test;

import com.piggy.coffee.common.util.net.NetUtils.BridgeNetDevice;

public class NetUtilsTest {

	@Test
	public void test() {

		List<BridgeNetDevice> list = NetUtils.getNetDevices();
		System.out.println(list);
	}

}
