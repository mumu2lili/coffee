package com.piggy.coffee.license.model;

import java.security.cert.X509Certificate;

public class BridgeLicense {

	private BridgeGrantInfo info;

	private X509Certificate cert;

	public BridgeGrantInfo getInfo() {
		return info;
	}

	public void setInfo(BridgeGrantInfo info) {
		this.info = info;
	}

	public X509Certificate getCert() {
		return cert;
	}

	public void setCert(X509Certificate cert) {
		this.cert = cert;
	}

	
}
