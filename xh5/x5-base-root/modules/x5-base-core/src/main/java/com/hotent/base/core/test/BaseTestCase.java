package com.hotent.base.core.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 单元测试的基础类
 * @author csx
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseTestCase{
	protected Log logger=LogFactory.getLog(BaseTestCase.class);

    @Test
    public void test(){
    	
    }
    
}