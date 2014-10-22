package com.hotent.bpmx.persistence.constants;

public enum ProcDefStatus {
	DRAFT("draft","草稿"),PUBLISHED("published","发布");
	private String key;
	private String name;
	private ProcDefStatus(String _key,String _name){
		key = _key;
		name = _name;
	}
	public String getKey(){
		return key;
	}
	public String getName(){
		return name;
	}
}
