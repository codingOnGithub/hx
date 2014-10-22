package com.hotent.base.core.depend;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository 
public class Person {
	
	
	private Head head;
	
//	@Resource(name="head")
	public void setHead(Head head){
		this.head=head;
	}
	
	
	public void sayHello(){
		if(head!=null)
			head.sayHello();
		else{
			head = new Head();
			head.sayHello();
		}
		
	}

}
