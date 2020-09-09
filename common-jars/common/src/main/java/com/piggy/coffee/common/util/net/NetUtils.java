package com.piggy.coffee.common.util.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public final class NetUtils {

	public static List<BridgeNetDevice> getNetDevices() {
		List<BridgeNetDevice> devList = new ArrayList<>();
		try {
			Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface
					.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = (NetworkInterface) netInterfaces.nextElement();

				Enumeration<InetAddress> ias = networkInterface.getInetAddresses();
				while (ias.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress) ias.nextElement();
					if (inetAddress != null && !inetAddress.isLoopbackAddress() // 非127.0.0.1
							&& inetAddress.getHostAddress().matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
						BridgeNetDevice dev = new BridgeNetDevice();
						dev.ip = inetAddress.getHostAddress();
						dev.mac = getMacFromBytes(networkInterface.getHardwareAddress());
						devList.add(dev);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return devList;
	}

	private static String getMacFromBytes(byte[] bytes) {
		StringBuffer mac = new StringBuffer();
		byte currentByte;
		boolean first = false;
		for (byte b : bytes) {
			if (first) {
				mac.append("-");
			}
			currentByte = (byte) ((b & 240) >> 4);
			mac.append(Integer.toHexString(currentByte));
			currentByte = (byte) (b & 15);
			mac.append(Integer.toHexString(currentByte));
			first = true;
		}
		return mac.toString().toUpperCase();
	}

	public static class BridgeNetDevice {
		public String ip;
		public String mac;

		@Override
		public String toString() {
			return "BridgeNetDevice [ip=" + ip + ", mac=" + mac + "]";
		}

	}
}
