package com.hotent.bpmx.extension.org.model;

import com.hotent.bpmx.api.model.identity.BpmIdentity;
import com.hotent.org.api.model.User;

public class DefaultBpmIdentity implements BpmIdentity{
	
	public final static String TYPE_USER="user";
	public final static String TYPE_ORG="org";
	
	private String id;
	private String name;
	private String type;
	
	public DefaultBpmIdentity(){
		
	}
	
	public DefaultBpmIdentity(User user){
		this.id=user.getUserId();
		this.name=user.getFullname();
		this.type=TYPE_USER;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getType() {
		return this.type;
	}


}
