package com.piggy.coffee.k8s.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.piggy.coffee.k8s.constant.K8sCsts;
import com.piggy.coffee.k8s.domain.CoffeePod;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class K8sServiceTest {

	@Autowired
	private K8sService k8sService;

	@Test
	public void testCreatePod() {
		String configDirHostPath = String.format(K8sCsts.CONFIG_DIR_HOST_PATH, "1");

		CoffeePod cPod = new CoffeePod().setName("hi").setImage("192.168.56.104/library/hi:v1.0")
				.setConfigDirContainerPath(K8sCsts.CONFIG_DIR_CONTAINER_PATH).setConfigDirHostPath(configDirHostPath)
				.setSeedDirContainerPath(K8sCsts.SEED_DIR_CONTAINER_PATH)
				.setSeedDirHostPath(K8sCsts.SEED_DIR_HOST_PATH);

		k8sService.createPod(cPod);
	}
}
