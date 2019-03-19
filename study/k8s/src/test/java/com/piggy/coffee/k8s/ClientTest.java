package com.piggy.coffee.k8s;

import org.junit.After;
import org.junit.Before;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class ClientTest {
	KubernetesClient client = null;

	@Before
	public void setUp() throws Exception {
		String certDir = "F:/mumu2lili/mumu/note/cloud/k8s/cur_auth/";
		Config config = new ConfigBuilder().withMasterUrl("https://192.168.76.5:6443").withNamespace("default")
				.withTrustCerts(true).withCaCertFile(certDir + "k8s-root-ca.pem")
				.withClientCertFile(certDir + "admin.pem").withClientKeyFile(certDir + "admin-key.pem")
				.withOauthToken("0e06e5cfc7257d078040c79babf6609d").build();
		client = new DefaultKubernetesClient(config);
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

}
