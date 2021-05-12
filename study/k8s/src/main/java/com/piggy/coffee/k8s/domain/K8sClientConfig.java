package com.piggy.coffee.k8s.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties()
public class K8sClientConfig implements K8sClientConfigIf {

	@Value("${k8s.masterUrl}")
	private String masterUrl;
	@Value("${k8s.caCertData}")
	private String caCertData;
	@Value("${k8s.clientCertData}")
	private String clientCertData;
	@Value("${k8s.clientKeyData}")
	private String clientKeyData;

	@Override
	public String getMasterUr() {
		return masterUrl;
	}

	@Override
	public String getCaCertData() {
		return caCertData;
	}

	@Override
	public String getClientCertData() {
		return clientCertData;
	}

	@Override
	public String getClientKeyData() {
		return clientKeyData;
	}

}
