package com.piggy.coffee.k8s.util;

import com.piggy.coffee.k8s.domain.K8sClientConfigIf;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.MetricAPIGroupClient;

public final class K8sClientUtils {
    private static volatile KubernetesClient client;
    private static volatile MetricAPIGroupClient metricClient;

    public static KubernetesClient getClient(K8sClientConfigIf configIf) {

        if (client == null) {
            synchronized (K8sClientUtils.class) {
                if (client == null) {
                    Config config = new ConfigBuilder().withMasterUrl(configIf.getMasterUr()).withNamespace("default")
                            .withTrustCerts(true).withCaCertData(configIf.getCaCertData())
                            .withClientCertData(configIf.getClientCertData())
                            .withClientKeyData(configIf.getClientKeyData()).build();
                    client = new DefaultKubernetesClient(config);
                }
            }
        }

        return client;
    }

    public static MetricAPIGroupClient getMetricsClient(K8sClientConfigIf configIf) {
        if (metricClient == null) {
            synchronized (K8sClientUtils.class) {
                if (metricClient == null) {
                    KubernetesClient client = getClient(configIf);
                    metricClient = client.adapt(MetricAPIGroupClient.class);
                }
            }
        }
        return metricClient;
    }
}
