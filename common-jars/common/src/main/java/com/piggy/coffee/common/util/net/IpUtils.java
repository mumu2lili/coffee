package com.piggy.coffee.common.util.net;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public final class IpUtils {

	public static List<String> getLocalIpList() {
		List<String> ipList = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			NetworkInterface networkInterface;
			Enumeration<InetAddress> inetAddresses;
			InetAddress inetAddress;
			String ip;
			while (networkInterfaces.hasMoreElements()) {
				networkInterface = networkInterfaces.nextElement();
				inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
						ip = inetAddress.getHostAddress();
						ipList.add(ip);
					}
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return ipList;
	}

	public static String getLocalIpByName(String netNames) {
		String[] arr = netNames.split(",");
		for (String name : arr) {
			try {
				return IpUtils.getLocalIp(name);
			} catch (Exception e) {
				// ignore quietly
			}
		}
		throw new RuntimeException("没有找到网卡：" + netNames);
	}

	public static String getLocalIp(String ifaceName) {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			NetworkInterface networkInterface;
			Enumeration<InetAddress> inetAddresses;
			InetAddress inetAddress;
			String ip;
			while (networkInterfaces.hasMoreElements()) {
				networkInterface = networkInterfaces.nextElement();
				inetAddresses = networkInterface.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					inetAddress = inetAddresses.nextElement();
					if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
						ip = inetAddress.getHostAddress();
						if (ifaceName.equals(networkInterface.getName())) {
							return ip;
						}
					}
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("未找到网卡" + ifaceName);
	}

}
