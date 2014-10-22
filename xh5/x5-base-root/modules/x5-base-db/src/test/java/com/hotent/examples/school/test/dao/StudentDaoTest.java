package com.hotent.examples.school.test.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.hotent.base.api.Page;
import com.hotent.base.api.query.Direction;
import com.hotent.base.api.query.FieldLogic;
import com.hotent.base.api.query.FieldRelation;
import com.hotent.base.api.query.QueryFilter;
import com.hotent.base.api.query.QueryOP;
import com.hotent.base.db.model.DefaultFieldLogic;
import com.hotent.base.db.model.DefaultQueryField;
import com.hotent.base.db.model.DefaultQueryFilter;
import com.hotent.base.db.mybatis.domain.DefaultFieldSort;
import com.hotent.base.db.mybatis.domain.DefaultPage;
import com.hotent.base.db.mybatis.domain.PageList;
import com.hotent.examples.school.dao.StudentDao;
import com.hotent.examples.school.entity.Student;
import com.hotent.examples.school.test.SchoolBaseTest;



public class StudentDaoTest extends SchoolBaseTest{
	@Resource
	private StudentDao studentDao;
	
	@Test
	@Rollback(true)
	public void testCrud(){
		//DOMConfigurator.configure("E:/work/x5/x5-base-root/modules/x5-base-db/src/test/resources/log4j.properties");//加载.xml文件
		Student student=new Student();
		student.setBirthday(new Date());
		Integer randId=new Double(100000*Math.random()).intValue();
		student.setDesc("student" + randId);
		student.setSex(new Short("1"));
		student.setName("Gen"+randId);
		student.setStuId(idGenerator.getSuid());
		//创建一实体
		studentDao.create(student);
//        Assert.assertNotNull(student.getStuId());
//        logger.debug("student1:"+ student.getStuId());
//		//获取一实体
//		Student student2=studentDao.get(student.getStuId());
//		Assert.assertNotNull(student2);
//		logger.debug("student2:" + student2.toString());
//		Integer randId2=new Double(100000*Math.random()).intValue();
//		student2.setName("Gen" + randId2);
//		student2.setDesc("Gen Stuent"+randId2);
//		student2.setSex(new Short("0"));
//		//更新一实体
//		studentDao.update(student2);
//		
//		Student student3=studentDao.get(student.getStuId());
//		System.out.println("student3:"+student3.toString());
		//删除一实体
		//studentDao.remove(student.getStuId());
		
	}
	
	//@Test
	public void getQuery(){
		FieldLogic orFieldLogic=new DefaultFieldLogic(FieldRelation.OR);
		FieldLogic andFieldLogic=new DefaultFieldLogic(FieldRelation.AND);
		
		orFieldLogic.getWhereClauses().add(andFieldLogic);
		orFieldLogic.getWhereClauses().add(new DefaultQueryField("SEX_",new Short("0")));
		
		andFieldLogic.getWhereClauses().add(new DefaultQueryField("NAME_", QueryOP.LIKE, "Gen"));
		andFieldLogic.getWhereClauses().add(new DefaultQueryField("BIRTHDAY_", QueryOP.LESS_EQUAL, new Date()));		
		QueryFilter queryFilter=new DefaultQueryFilter(orFieldLogic);
		//加上两个字段的排序
		queryFilter.getFieldSortList().add(new DefaultFieldSort("NAME_",Direction.ASC));
		queryFilter.getFieldSortList().add(new DefaultFieldSort("SEX_",Direction.DESC));
		Page page=new DefaultPage(1,5);
		List<Student> queryList1 = studentDao.query(queryFilter);
		
		List<Student> queryList=studentDao.getAllByPage(page);
		
		for(Student stu:queryList){
			logger.debug("name=" + stu.getName());
		}
		
	}
	
	//@Test
	public void getPage(){
		
		studentDao.getPage();
	}
	
	//@Test
	public void getAllPage(){
		
		PageList<Student> list= studentDao.getAllPage();
		System.out.println(list.getPageResult().getTotalCount());
		for(Iterator<Student> it = list.iterator();it.hasNext();){
			Student student = it.next();
			System.out.println(student);
		}
	}
	@Test
	public void queryByName(){
		PageList<Student> list=studentDao.queryByName("Gen","Gen S");
		System.out.println("getTotalCount:" +list.getPageResult().getTotalCount());
		
		Student student=new Student();
		student.setBirthday(new Date());
		Integer randId=new Double(100000*Math.random()).intValue();
		student.setDesc("Gen S" + randId);
		student.setSex(new Short("1"));
		student.setName("Gen"+randId);
		student.setStuId(idGenerator.getSuid());
		//创建一实体
		studentDao.create(student);
		PageList<Student> list2=studentDao.queryByName("Gen","Gen S");
		System.out.println("getTotalCount:" +list2.getPageResult().getTotalCount());
	}
}
