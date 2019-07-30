package com.piggy.coffee.redis.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zzx on 17/10/30.
 */
public class PortUtil {
	public static int getPort() {
		int port;
		while (true) {
			port = (int) (20000 * Math.random() + 40000);
			try {
				new ServerSocket(port).close();// TODO 端口可能冲突
				break;
			} catch (IOException e) {
				continue;
			}
		}
		return port;
	}

	public static int getPort2() {
		Long p = JedisUtil.incrBy("port", 1);
		if (p >= 60000) {
			// lock
			p = JedisUtil.incrBy("port", 1);
			if (p >= 60000) {
				JedisUtil.set("port", 40000 + "");
				p = JedisUtil.incrBy("port", 1);
			}

		}

		return p.intValue();
	}

	public static int getPort3() {
		Long p = JedisUtil.incrBy("port", 1);
		return p.intValue();
	}

	public static boolean isPortUsed(String ip, int port) {
		boolean used = true;
		Socket socket = null;
		try {
			socket = new Socket(ip, port);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			used = false;
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// ignore quietly
				}
			}
		}

		return used;

	}
}
