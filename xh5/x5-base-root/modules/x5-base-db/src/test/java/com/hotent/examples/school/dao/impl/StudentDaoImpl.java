package com.hotent.examples.school.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.base.api.query.FieldSort;
import com.hotent.base.db.impl.MyBatisDaoImpl;
import com.hotent.base.db.mybatis.domain.DefaultFieldSort;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.examples.school.dao.StudentDao;
import com.hotent.examples.school.entity.Student;

@Repository
public class StudentDaoImpl extends MyBatisDaoImpl<String, Student> implements StudentDao{

    @Override
    public String getNamespace() {
        return Student.class.getName();
    }

	@Override
	public void getPage() {
		DefaultPage p=new DefaultPage(2, 2);
		List list= this.sqlSessionTemplate.selectList(this.getNamespace() + ".query", null, p);
		 PageList pageList = (PageList)list;
	    
		
	}
	
	
	@Override
	public PageList<Student> getAllPage() {
		FieldSort sort=new DefaultFieldSort("STU_ID_");
		DefaultPage p=new DefaultPage(1, 2,sort);
		List list= this.getAllByPage(p);
		 PageList<Student> pageList = (PageList<Student>)list;
		 return pageList;
	    
		
	}
	
	@Override
	public PageList<Student> queryByName(String name,String desc) {
		Map<String,String> map=new HashMap<String,String>();
		name=name +"%";
		desc=desc +"%";
		map.put("name", name);
		map.put("desc", desc);
		DefaultPage p=new DefaultPage(1, 2);
		List list= this.sqlSessionTemplate.selectList(this.getNamespace() + ".queryByName", map, p);
		 PageList pageList = (PageList)list;
	    
		return pageList;
	}

	
	
}
