package com.piggy.coffee.k8s.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piggy.coffee.common.util.shell.ShellExeResult;
import com.piggy.coffee.common.util.shell.ShellUtils;
import com.piggy.coffee.k8s.constant.K8sCsts;
import com.piggy.coffee.k8s.domain.CoffeeCmd;
import com.piggy.coffee.k8s.domain.CoffeePod;
import com.piggy.coffee.k8s.domain.K8sClientConfig;
import com.piggy.coffee.k8s.util.K8sClientUtils;
import com.piggy.coffee.k8s.util.K8sCmdUtils;
import com.piggy.coffee.k8s.util.K8sUtils;

import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodFluent.SpecNested;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetricsList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.MetricAPIGroupClient;

@Service
public class K8sService {
	@Autowired
	private K8sClientConfig k8sClientConfig;

	 public void createPod(CoffeePod cPod) {

	        KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);

	        // metadata
	        PodBuilder pb = new PodBuilder().withNewMetadata().withName(cPod.getName()).addToAnnotations("seccomp.security.alpha.kubernetes.io/pod", "unconfined").endMetadata();

	        // container
	        SpecNested<PodBuilder> specNested = pb.withNewSpec().addNewContainer().withName(K8sUtils.buildContainerName(cPod.getContainerName()))
	                .withImage(cPod.getImage()).withResources(null).addNewVolumeMount().withName(K8sCsts.CONFIG_DIR)
	                .withMountPath(K8sCsts.CONFIG_DIR_CONTAINER_PATH).endVolumeMount().addNewVolumeMount().withName(K8sCsts.SEED_DIR)
	                .withMountPath(cPod.getSeedDirContainerPath()).endVolumeMount().addNewCommand("/xfuzz_work/scripts/start.sh")
	                .withNewSecurityContext().withPrivileged(true).editOrNewCapabilities().addToAdd("SYS_PTRACE").endCapabilities().endSecurityContext()
	                .endContainer();

	        // volume
	        specNested.addNewVolume().withName(K8sCsts.CONFIG_DIR).withNewHostPath(cPod.getConfigDirHostPath(), null)
	                .endVolume();
	        specNested.addNewVolume().withName(K8sCsts.SEED_DIR).withNewHostPath(cPod.getSeedDirHostPath(), null)
	                .endVolume();
	        
	        Pod pod = specNested.withNewRestartPolicy("Always").endSpec().build();

	        client.pods().inNamespace(cPod.getNamespace()).create(pod);

	    }

	    
	    public Pod getPod(CoffeePod cPod) {
	        KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);
	        return client.pods().inNamespace(cPod.getNamespace()).withName(cPod.getName()).get();
	    }

	    
	    public void detelePod(CoffeePod cPod) {
	        KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);
	        client.pods().inNamespace(cPod.getNamespace()).withName(cPod.getName()).delete();
	    }

	    
	    public void detelePodSync(CoffeePod cPod) {
	        String cmd = "kubectl --kubeconfig=" + k8sClientConfig.getKubeConfig() + " delete pods " + cPod.getName()
	                + " --now=true --wait=true";
	        ShellExeResult seResult = ShellUtils.executeAndGetExitStatus(cmd);
//	        if (seResult.getExitCode() != 0) {
//	            throw new RuntimeException(seResult.getOut());
//	        }

	    }

	    
	    public ShellExeResult exec(CoffeePod cPod, CoffeeCmd cCmd) {
	        String cmd = K8sCmdUtils.buildCmd(cCmd, cPod, k8sClientConfig);
	        return ShellUtils.executeAndGetExitStatus(cmd);

	    }

	    
	    public NodeList getNode() {
	        KubernetesClient client = K8sClientUtils.getClient(k8sClientConfig);
	        return client.nodes().list();
	    }

	    // metrics start ...
	    
	    public NodeMetricsList getNodeMetrics() {
	        MetricAPIGroupClient mClient = K8sClientUtils.getMetricsClient(k8sClientConfig);
	        NodeMetricsList nmList = mClient.nodes().metrics();
	        return nmList;
	    }

}
