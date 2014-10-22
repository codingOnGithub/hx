package com.hotent.examples.school.test;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.db.id.UniqueIdUtil;
import com.hotent.base.test.BaseTestCase;


@ContextConfiguration({"classpath:conf/x5-examples.xml"})
public class UniqueIdUtilTest extends BaseTestCase {

	
	@Test
	public void getId(){
		String id=UniqueIdUtil.getSuid();
		System.out.println(id);
	}

}
