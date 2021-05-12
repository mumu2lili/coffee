package com.piggy.coffee.k8s.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeCondition;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.Quantity;

public class NodeTest extends ClientTest {

	@Test
	public void test() {

		Map<String, Double> map = getSumUsage();
		System.out.println(map);
	}

	private Map<String, Double> getSumUsage() {
		Map<String, Double> sumUsage = new HashMap<>();
		double cpuUsage = 0.0;
		double memoryUsage = 0.0;

		NodeList list = client.nodes().list();
		for (Node node : list.getItems()) {
			List<NodeCondition> conditions = node.getStatus().getConditions();
			//
			for (NodeCondition condition : conditions) {
				if ("Ready".equalsIgnoreCase(condition.getType())) {
					if (!"True".equalsIgnoreCase(condition.getStatus())) {
						continue;
					}
				}
			}
			Map<String, Quantity> allocatable = node.getStatus().getAllocatable();
			Quantity quantity = allocatable.get("cpu");
			String cpu = quantity.getAmount();
			if (cpu.contains("m")) {
				// 单位为m
				cpu = cpu.substring(0, cpu.length() - 1);
				cpuUsage = cpuUsage + Double.parseDouble(cpu);
			} else {
				// 单位为核数
				cpuUsage = cpuUsage + Double.parseDouble(cpu) * 1000;
			}

			quantity = allocatable.get("memory");
			String mem = quantity.getAmount();
			/**
			 * 去除各字符串中的单位如Mi,Ki 以解析出数值。
			 */
			if (mem.contains("Mi")) {
				mem = mem.substring(0, mem.length() - 2);
				memoryUsage = memoryUsage + Double.parseDouble(mem);
			} else if (mem.contains("Ki")) {
				mem = mem.substring(0, mem.length() - 2);
				memoryUsage = memoryUsage + Double.parseDouble(mem) / 1024;
			} else {
				// TODO ....
			}

		}
		sumUsage.put("cpuUsage", cpuUsage);
		sumUsage.put("memoryUsage", memoryUsage);
		return sumUsage;
	}

}
