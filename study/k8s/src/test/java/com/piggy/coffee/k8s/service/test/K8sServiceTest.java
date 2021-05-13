package com.piggy.coffee.k8s.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.piggy.coffee.k8s.domain.XfuzzPod;
import com.piggy.coffee.k8s.service.K8sService;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class K8sServiceTest {

	@Autowired
	private K8sService k8sService;

	@Test
	public void testCreatePod() {
		XfuzzPod xPod = new XfuzzPod().setName("hi").setImage("hi:v1.0");

		k8sService.createPod(xPod);
	}
}
