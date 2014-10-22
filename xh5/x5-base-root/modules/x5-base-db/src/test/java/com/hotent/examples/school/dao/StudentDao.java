package com.hotent.examples.school.dao;

import com.hotent.base.db.api.Dao;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.examples.school.entity.Student;


public interface StudentDao extends Dao<String,  Student> {
	
	public void getPage();
	
	
	public PageList<Student> getAllPage();
	
	
	public PageList<Student> queryByName(String name,String desc) ;
}
