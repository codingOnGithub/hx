package com.ksoft.base.service.manager.ws.model;


public abstract class AbstractSoapModel {
	private String name;

	private String namespace;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

}
