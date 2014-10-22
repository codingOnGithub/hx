package com.hotent.examples.school.manager.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.base.db.api.Dao;
import com.hotent.base.manager.impl.AbstractManagerImpl;
import com.hotent.examples.school.dao.StudentDao;
import com.hotent.examples.school.entity.Student;
import com.hotent.examples.school.manager.StudentManager;

@Service("studentManager")
public class StudentManagerImpl extends AbstractManagerImpl<String, Student> implements StudentManager{
	@Resource
	StudentDao studentDao;
	@Override
	protected Dao<String, Student> getDao() {
		return studentDao;
	}
	
}
