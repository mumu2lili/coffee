package com.piggy.coffee.k8s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piggy.coffee.k8s.domain.K8sClientConfig;
import com.piggy.coffee.k8s.domain.XfuzzPod;
import com.piggy.coffee.k8s.util.K8sClientUtils;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;

@Service
public class K8sService {
	@Autowired
	private K8sClientConfig k8sClientConfig;

	public void createPod(XfuzzPod xPod) {

		KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);

		Pod pod = new PodBuilder().withNewMetadata().withName(xPod.getName()).endMetadata().withNewSpec()
				.addNewContainer().withName(xPod.getContainerName()).withImage(xPod.getImage())
				.addNewCommand("start.sh").endContainer().endSpec().build();
		client.pods().inNamespace(xPod.getNamespace()).create(pod);

		System.out.print("aa");

	}

}
