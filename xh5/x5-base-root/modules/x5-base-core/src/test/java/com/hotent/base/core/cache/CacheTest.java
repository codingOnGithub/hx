package com.hotent.base.core.cache;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.core.test.BaseTestCase;


@ContextConfiguration({"classpath:conf/x5-base-core-test.xml"})
public class CacheTest extends BaseTestCase {
	
	@Resource
	ICache<String> iCache;

	@Test
	public void add(){
		iCache.add("aaa","hello");
		
	}
	
	@Test
	public void get(){
		String str=(String)iCache.getByKey("aaa");
		System.out.println(str);
		
	}
}
