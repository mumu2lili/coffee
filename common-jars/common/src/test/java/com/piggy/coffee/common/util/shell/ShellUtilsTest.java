package com.piggy.coffee.common.util.shell;

import org.junit.Test;

public class ShellUtilsTest {
	
	@Test
	public void test5() {
		String cmd = "ssh mumu@192.168.56.100 python D:/work/eclipse-workspace/eva/e/h.py 1 \"YWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmM=\",\"YQpiCmEKYgphCmI=\",\"YQpiCmMKZAplCmY=\",\"YQoKYQoKYQoK\",\"CmEKCmEKCmE=\"";
		ShellExeResult r = ShellUtils.executeAndGetExitStatus(cmd);
		System.out.println(r.getOut());
		

	}
	
	@Test
	public void test4() {
		String cmd = "scp  /media/sf_note/tmp/py/eva.py mumu@192.168.56.100:D:/tmp/py/eva.py";
		ShellExeResult r = ShellUtils.executeAndGetExitStatus(cmd);
		System.out.println(r.getOut());
		
		cmd = "ssh mumu@192.168.56.100 python D:/tmp/py/eva.py 25 1 \"YWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmMKYWFiYmM=\",\"YQpiCmEKYgphCmI=\",\"YQpiCmMKZAplCmY=\",\"YQoKYQoKYQoK\",\"CmEKCmEKCmE=\"";
		r = ShellUtils.executeAndGetExitStatus(cmd);
		System.out.println(r.getOut());
	}

	
	
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
