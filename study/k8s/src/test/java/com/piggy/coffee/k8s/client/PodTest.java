package com.piggy.coffee.k8s.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.dsl.Deletable;
import io.fabric8.kubernetes.client.dsl.FilterWatchListDeletable;

public class PodTest extends ClientTest {

	@Test
	public void testLabel() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "a");
		Pod pod = this.getPod(map, map);
		System.out.println(pod);
	}

	private Pod getPod(Map<String, String> inLabels, Map<String, String> outLabels) {
		// 加上所有key in values的筛选条件
		FilterWatchListDeletable<Pod, PodList> filteredPodList = null;
		for (Map.Entry<String, String> label : inLabels.entrySet()) {
			if (filteredPodList == null) {
				filteredPodList = client.pods().inNamespace("default").withLabelIn(label.getKey(), label.getValue());
			} else {
				filteredPodList = filteredPodList.withLabelIn(label.getKey(), label.getValue());
			}
		}
		for (Map.Entry<String, String> label : outLabels.entrySet()) {
			if (filteredPodList == null) {
				filteredPodList = client.pods().inNamespace("default").withLabelNotIn(label.getKey(), label.getValue());
			} else {
				filteredPodList = filteredPodList.withLabelNotIn(label.getKey(), label.getValue());
			}
		}

		List<Pod> pods = filteredPodList.list().getItems();

		// 在目前的需求里边都只有一个对应的pod，所以取第0个元素
		if (pods != null && pods.size() > 0) {
			Pod pod = pods.get(0);
			return pod;
		} else {
			return null;
		}
	}

	@Test
	public void testName() {
		Pod pod = this.getPod("mypoda");
		System.out.println(pod);
	}

	private Pod getPod(String podName) {

		return client.pods().inNamespace("default").withName(podName).get();

	}

	@Test
	public void testDelete() {
		Deletable d = client.pods().inNamespace("default").withName("mypodb").withGracePeriod(0);
		d.delete();
	}

	@Test
	public void testDelete2() {
		Deletable d = client.pods().inNamespace("default").withName("mypodb");
		d.delete();
	}

}
