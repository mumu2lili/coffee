package com.piggy.coffee.license.mgr;

import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.piggy.coffee.common.util.net.NetUtils;
import com.piggy.coffee.common.util.net.NetUtils.BridgeNetDevice;
import com.piggy.coffee.license.model.BridgeGrantInfo;
import com.piggy.coffee.license.model.BridgeHost;
import com.piggy.coffee.license.model.BridgeLicense;
import com.piggy.coffee.license.util.CertUtils;
import com.piggy.coffee.license.util.LicenseUtils;

@Component
public class LicenseMgr {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private boolean validLicense = false;

	private static final String CA_CERT_DATA = new StringBuilder().append("-----BEGIN CERTIFICATE-----")
			.append(CertUtils.SIGN).append("MIIDnTCCAoWgAwIBAgIEaOv7rDANBgkqhkiG9w0BAQsFADB/MQ8wDQYDVQQGDAbk")
			.append(CertUtils.SIGN).append("uK3lm70xDzANBgNVBAgMBua5luWNlzEPMA0GA1UEBwwG6ZW/5rKZMRIwEAYDVQQK")
			.append(CertUtils.SIGN).append("DAnlrp7orq3kupExJzAlBgNVBAsMHua5luWNl+aZuuaTjuenkeaKgOaciemZkOWF")
			.append(CertUtils.SIGN).append("rOWPuDENMAsGA1UEAxMEbXVtdTAeFw0yMDA5MDMwOTAxMzFaFw0zMDA5MDEwOTAx")
			.append(CertUtils.SIGN).append("MzFaMH8xDzANBgNVBAYMBuS4reWbvTEPMA0GA1UECAwG5rmW5Y2XMQ8wDQYDVQQH")
			.append(CertUtils.SIGN).append("DAbplb/mspkxEjAQBgNVBAoMCeWunuiureS6kTEnMCUGA1UECwwe5rmW5Y2X5pm6")
			.append(CertUtils.SIGN).append("5pOO56eR5oqA5pyJ6ZmQ5YWs5Y+4MQ0wCwYDVQQDEwRtdW11MIIBIjANBgkqhkiG")
			.append(CertUtils.SIGN).append("9w0BAQEFAAOCAQ8AMIIBCgKCAQEAueK02KjhwPyj1Q6kinFgcWsFdP0m4GJXW4go")
			.append(CertUtils.SIGN).append("47ICaOcI9WRkuJWNBKWCH5ozgkxj9ZseZGGahSVOpkEN7ao09HvGbAQHAHxRH+FN")
			.append(CertUtils.SIGN).append("/Q+eGlL4/uJZjU1xA5MmWcwXIYYL6MfmF9/+MR5slI0hQ8eAJOyW0XOwHS4iG9ME")
			.append(CertUtils.SIGN).append("mFi+jjGLGrtMtv+OsZMLWtyHL1GvWW+yZ+R9a1YGj0Y2Js+4P/AFke2rMMepaTZx")
			.append(CertUtils.SIGN).append("FMqOokde/72KSl3yHFCfwR0TdtaAOPEbSVdGXGjp0V//k4RP7/Tb+K6SCAcHWltp")
			.append(CertUtils.SIGN).append("qHvUitl+EwKjth5sxWK+WedFwoxcZkJrM8qG/yL6fgVnpB9s9wIDAQABoyEwHzAd")
			.append(CertUtils.SIGN).append("BgNVHQ4EFgQUsoElFohddOc5cdLMszF8gRasUTowDQYJKoZIhvcNAQELBQADggEB")
			.append(CertUtils.SIGN).append("AKUePaJxjwY1QPcG6dXk4k+AbNY/XyUqScj3Wn/d6mG87fwA+UztGSkgxPLBfB/E")
			.append(CertUtils.SIGN).append("5PyKGoAmTWtOFg3ksXTeeV16ZdlrKJ+RJu/07E1PQh9B5B//V+CveVCDnJGRPr7U")
			.append(CertUtils.SIGN).append("cyVNoZpyFMYLS5zPIivlV/KEaKwxnmZVevSSN4oYSfkjVIIiYWH+5psNoXfaVQ9Q")
			.append(CertUtils.SIGN).append("a1dkTR7qGd9Qt6M23v/piFMYra30PWdBgYYT+sT8rZ3/5L3V6fdbFOM4xLjlVbJY")
			.append(CertUtils.SIGN).append("GwzGy8jW+Sfes4t8E0m8yb3/7SQik+MwAMePODW7kx8Deq3fXplSopnjyYtwvVQW")
			.append(CertUtils.SIGN).append("uH+Nd2XKn0YKVT42sIqWcow=").append(CertUtils.SIGN)
			.append("-----END CERTIFICATE-----").toString();

	public boolean isValidLicense() {
		return validLicense;
	}

	/**
	 * 
	 * 
	 * @param licensePath
	 *            许可路径
	 */
	public void verifyLicense(String licensePath) {
		try {
			validLicense = verifyLicenseInnter(licensePath);
		} catch (Exception e) {
			validLicense = false;
			logger.error(e.getMessage());
		}
	}

	public boolean verifyLicenseInnter(String licensePath) {
		BridgeLicense license = LicenseUtils.readLicense(licensePath);
		X509Certificate cert = license.getCert();

		String caCertData = getCaCertData();
		X509Certificate caCert = CertUtils.parseCert(caCertData);

		// 验证证书签名
		CertUtils.verifyCert(cert, caCert);

		// 验证证书本身
		// 是否过期
		CertUtils.checkValidity(cert);

		// 验证自定义信息
		BridgeGrantInfo info = license.getInfo();
		List<BridgeHost> hostList = info.getBridgeHosts();// 许可的主机
		if (!hostList.isEmpty()) {// 需要验证 mac 或者 ip
			List<BridgeNetDevice> devList = NetUtils.getNetDevices();
			// 本机网络设备匹配到任何一个许可的设备，即通过
			for (BridgeNetDevice dev : devList) {
				for (BridgeHost host : hostList) {
					boolean ipMatch = StringUtils.isBlank(host.getIp()) || host.getIp().equals(dev.ip);
					boolean macMatch = StringUtils.isBlank(host.getMac()) || host.getMac().equals(dev.mac);
					if (ipMatch && macMatch) {
						return true;
					}
				}
			}
		} else {// 不验证mac 和 ip
			return true;
		}

		return false;
	}

	public static String getCaCertData() {
		return CA_CERT_DATA;
	}

}