package demo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="boy")
public class Boy implements Serializable {   
	
	@XmlAttribute
    private String name = "";
	@XmlAttribute
	private  int age = 0;
	
	private Date birthDay;
	
	

	private List<Admin> list=new ArrayList<Admin>();
	
	public Boy(){}
	
	public Boy(String _name,int _age){
		this.name=_name;
		this.age=_age;
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
	
	public void addAdmin(Admin admin){
		list.add(admin);
	}
	
	public List<Admin> getAdmin(){
		return list;
	}
	
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
    
}