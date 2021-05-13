package com.piggy.coffee.k8s.domain;

public class XfuzzPod {

	private String namespace = "default";

	private String name;

	private String image;

	/**
	 * 默认取 image 名称部分
	 */
	private String containerName;

	public String getNamespace() {
		return namespace;
	}

	public XfuzzPod setNamespace(String namespace) {
		this.namespace = namespace;
		return this;
	}

	public String getName() {
		return name;
	}

	public XfuzzPod setName(String name) {
		this.name = name;
		return this;
	}

	public String getImage() {
		return image;
	}

	public XfuzzPod setImage(String image) {
		this.image = image;

		if (this.containerName == null) {
			this.containerName = image.substring(0, image.indexOf(":"));
		}
		return this;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
}
