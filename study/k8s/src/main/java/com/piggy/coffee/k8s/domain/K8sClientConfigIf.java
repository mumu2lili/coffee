package com.piggy.coffee.k8s.domain;

public interface K8sClientConfigIf {
	String getMasterUr();

	String getCaCertData();

	String getClientCertData();

	String getClientKeyData();
}
