package demo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Person implements Serializable{
    private String name;
    private int age;
    private Map<String,String> map=new HashMap<String, String>();
    public Person(){
        
    }
    public Person(String str, int n){
       // System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
    }
    
    
    public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}