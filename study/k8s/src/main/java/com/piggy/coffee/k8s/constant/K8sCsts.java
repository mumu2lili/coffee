package com.piggy.coffee.k8s.constant;

public interface K8sCsts {

	// 配置目录
	String CONFIG_DIR = "config-dir";
	String CONFIG_DIR_CONTAINER_PATH = "/xfuzz/data/config";
	// %s 为 taskId
	String CONFIG_DIR_HOST_PATH = "/remote-data/task/%s/config";

	// 种子目录
	String SEED_DIR = "seed-dir";
	String SEED_DIR_CONTAINER_PATH = "/remote-data/seeds";
	String SEED_DIR_HOST_PATH = "/remote-data/seeds";
}
