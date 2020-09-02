package com.piggy.coffee.license;

import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * LicenseManager单例模式下的证书管理器
 */
public class LicenseManagerHolder {

    private static LicenseManager licenseManager;

    public static synchronized LicenseManager getLicenseManager(LicenseParam licenseParam) {
        if (licenseManager == null) {
            licenseManager = new LicenseManager(licenseParam);
        }
        return licenseManager;
    }

}