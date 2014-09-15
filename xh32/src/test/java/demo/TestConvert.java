package demo;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TestConvert extends XmlAdapter<Admin, User> {

	@Override
	public Admin marshal(User user) throws Exception {
		Admin admin=new Admin();
		admin.setName("管理员:" + user.getName());
		admin.setSex("性别:" +user.getSex());
		return admin;
	}

	@Override
	public User unmarshal(Admin admin) throws Exception {
		User user=new User();
		user.setName(admin.getName());
		user.setSex(admin.getSex());
		return user;
	}
	
	public static void main(String[] args) throws Exception{
		User user=new User();
		user.setName("老张");
		user.setSex("男");
		TestConvert conver=new TestConvert();
		Admin admin= conver.marshal(user);
		System.out.println(admin.getName() +"," + admin.getSex());
	}

	
}
