package com.piggy.coffee.k8s.client;

import org.junit.After;
import org.junit.Before;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class ClientTimeoutTest {
	KubernetesClient client = null;

	@Before
	public void setUp() throws Exception {
		String certDir = "D:\\mumu\\note\\cloud\\k8s\\k8s_auth\\";
		Config config = new ConfigBuilder().withMasterUrl("https://192.168.56.101:6443").withNamespace("default")
				.withTrustCerts(true).withCaCertFile(certDir + "k8s-root-ca.pem")
				.withClientCertFile(certDir + "admin.pem").withClientKeyFile(certDir + "admin-key.pem")
				.withOauthToken("aa1d522ad080f2f893ea87252d5d1588").withConnectionTimeout(5000).withWebsocketTimeout(1000).withRequestTimeout(1000).build();
		client = new DefaultKubernetesClient(config);

	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

}
