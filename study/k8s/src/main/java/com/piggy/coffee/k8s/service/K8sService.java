package com.piggy.coffee.k8s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piggy.coffee.k8s.domain.K8sClientConfig;
import com.piggy.coffee.k8s.domain.XfuzzPod;
import com.piggy.coffee.k8s.util.K8sClientUtils;

import io.fabric8.kubernetes.client.KubernetesClient;

@Service
public class K8sService {
	@Autowired
	private K8sClientConfig k8sClientConfig;

	public void createPod(XfuzzPod xPod) {

		KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);

		client.pods().inNamespace(xPod.getNamespace()).createOrReplaceWithNew().withNewMetadata()
				.withName(xPod.getName()).endMetadata().withNewSpec().addNewContainer().withImage(xPod.getImage())
				.endContainer().endSpec().done();
		
		System.out.print("aa");

	}

}
