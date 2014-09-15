package demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.hotent.platform.model.system.SysRole;

public class User {

	private String name="";
	
	private String sex="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public boolean equals(Object object)
	{
		if (!(object instanceof User))
		{
			return false;
		}
		User rhs = (User) object;
		return new EqualsBuilder()
				.append(this.name, rhs.name)
				.isEquals();
	}
	
	public static void main(String[] args) {
		User a=new User();
		a.setName("zyg");
		a.setSex("男");
		
		User b=new User();
		b.setName("zyg");
		b.setSex("女");
		
		List<User> list=new ArrayList<User>();
		list.add(a);
		list.add(b);
		
		if(list.contains(b)){
			System.out.println("ok");
		}
	}
}
