package com.piggy.coffee.license.task;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.piggy.coffee.license.mgr.LicenseMgr;

/**
 * 清理遗漏pod
 */
@Component
public class LicenseTask {

	@Autowired
	private LicenseMgr LicenseMgr;

	@PostConstruct
	public void checkLicenseAtInit() {
		String path = System.getProperty("user.dir") + File.separator + "bridge.lic";
		LicenseMgr.verifyLicense(path);
	}

	@Scheduled(cron = "0 */1 * * * ?")
	public void work() {
		String path = System.getProperty("user.dir") + File.separator + "bridge.lic";
		LicenseMgr.verifyLicense(path);
	}
}
