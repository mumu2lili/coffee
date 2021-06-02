package com.piggy.coffee.k8s.domain;

public class CoffeePod {

	private String namespace = "default";

	private String name;

	private String image;

	/**
	 * 默认取 image 名称部分
	 */
	private String containerName;

	/**
	 * 配置目录在主机上的路径
	 */
	private String configDirHostPath;

	/**
	 * 配置目录在容器中的路径
	 */
	private String configDirContainerPath;

	/**
	 * 种子目录在主机上的路径
	 */
	private String seedDirHostPath;

	/**
	 * 种子目录在主机上的路径
	 */
	private String seedDirContainerPath;

	public String getNamespace() {
		return namespace;
	}

	public CoffeePod setNamespace(String namespace) {
		this.namespace = namespace;
		return this;
	}

	public String getName() {
		return name;
	}

	public CoffeePod setName(String name) {
		this.name = name;
		return this;
	}

	public String getImage() {
		return image;
	}

	public CoffeePod setImage(String image) {
		this.image = image;

		if (this.containerName == null) {
			this.containerName = image.substring(0, image.indexOf(":"));
		}
		return this;
	}

	public String getContainerName() {
		return containerName;
	}

	public CoffeePod setContainerName(String containerName) {
		this.containerName = containerName;
		return this;
	}

	public String getConfigDirHostPath() {
		return configDirHostPath;
	}

	public CoffeePod setConfigDirHostPath(String configDirHostPath) {
		this.configDirHostPath = configDirHostPath;
		return this;
	}

	public String getConfigDirContainerPath() {
		return configDirContainerPath;
	}

	public CoffeePod setConfigDirContainerPath(String configDirContainerPath) {
		this.configDirContainerPath = configDirContainerPath;
		return this;
	}

	public String getSeedDirHostPath() {
		return seedDirHostPath;
	}

	public CoffeePod setSeedDirHostPath(String seedDirHostPath) {
		this.seedDirHostPath = seedDirHostPath;
		return this;
	}

	public String getSeedDirContainerPath() {
		return seedDirContainerPath;
	}

	public CoffeePod setSeedDirContainerPath(String seedDirContainerPath) {
		this.seedDirContainerPath = seedDirContainerPath;
		return this;
	}

}
