package com.hotent.examples.school.test.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.hotent.examples.school.entity.Student;
import com.hotent.examples.school.manager.StudentManager;
import com.hotent.examples.school.test.SchoolBaseTest;

public class StudentManagerTest extends SchoolBaseTest{
	@Resource
	StudentManager studentManager;
	@Test
	public void testCreate(){
		Student student=new Student();
		student.setBirthday(new Date());
		Integer randId=new Double(100000*Math.random()).intValue();
		student.setDesc("student" + randId);
		student.setSex(new Short("1"));
		student.setName("Gen"+randId);
		student.setStuId(idGenerator.getSuid());
		//创建一实体
		studentManager.create(student);
	}
}
