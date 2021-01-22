package com.piggy.coffee.common.util.shell;

import org.junit.Test;

public class ShellUtilsTest {

	@Test
	public void test3() {
		String cmd = "ssh mumu@192.168.56.100 D:/work/python/python D:/tmp/py/eva.py 25 1 \"YWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmM=\",\"YQpiCmEKYgphCmI=\",\"YQpiCmMKZAplCmY=\",\"YQoKYQoKYQoK\",\"CmEKCmEKCmE=\"";
		ShellExeResult r = ShellUtils.executeAndGetExitStatus(cmd);
		System.out.println(r.getOut());
	}

	@Test
	public void test2() {
		String cmd = "ssh mm@192.168.56.105 python Y:/tmp/py/eva.py 25 1 \"YWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmM=\",\"YQpiCmEKYgphCmI=\",\"YQpiCmMKZAplCmY=\",\"YQoKYQoKYQoK\",\"CmEKCmEKCmE=\"";
		ShellExeResult r = ShellUtils.executeAndGetExitStatus(cmd);
		System.out.println(r.getOut());
	}

	@Test
	public void test() {
		String cmd = "ssh mm@192.168.56.105 ipconfig";
		ShellExeResult r = ShellUtils.executeAndGetExitStatus(cmd);
		System.out.println(r.getOut());
	}

}
