package com.piggy.coffee.k8s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piggy.coffee.k8s.constant.K8sCsts;
import com.piggy.coffee.k8s.domain.K8sClientConfig;
import com.piggy.coffee.k8s.domain.XfuzzPod;
import com.piggy.coffee.k8s.util.K8sClientUtils;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent.SpecNested;
import io.fabric8.kubernetes.client.KubernetesClient;

@Service
public class K8sService {
	@Autowired
	private K8sClientConfig k8sClientConfig;

	public void createPod(XfuzzPod xPod) {

		KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);

		// metadata
		PodBuilder pb = new PodBuilder().withNewMetadata().withName(xPod.getName()).endMetadata();

		// container
		SpecNested<PodBuilder> specNested = pb.withNewSpec().addNewContainer().withName(xPod.getContainerName())
				.withImage(xPod.getImage()).addNewVolumeMount().withName(K8sCsts.CONFIG_DIR)
				.withMountPath(K8sCsts.CONFIG_DIR_CONTAINER_PATH).endVolumeMount().addNewVolumeMount().withName(K8sCsts.SEED_DIR)
				.withMountPath(xPod.getSeedDirContainerPath()).endVolumeMount().addNewCommand("start.sh")
				.endContainer();

		// volume
		specNested.addNewVolume().withName(K8sCsts.CONFIG_DIR).withNewHostPath(xPod.getConfigDirHostPath(), null)
				.endVolume();
		specNested.addNewVolume().withName(K8sCsts.SEED_DIR).withNewHostPath(xPod.getSeedDirHostPath(), null)
				.endVolume();

		Pod pod = specNested.endSpec().build();
		System.out.print("***************************");
		System.out.print(pod);

		client.pods().inNamespace(xPod.getNamespace()).create(pod);

	}

}
