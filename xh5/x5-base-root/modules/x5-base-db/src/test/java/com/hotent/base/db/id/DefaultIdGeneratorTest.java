package com.hotent.base.db.id;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.test.BaseTestCase;

/**
 * ID缺省实现
 * @author csx
 */
@ContextConfiguration({"classpath:conf/x5-base-db.xml"})
public class DefaultIdGeneratorTest extends BaseTestCase{
    @Resource
    private IdGenerator idGenerator;
    
    @Test
    public void getUid(){
        Long id=idGenerator.getUId();
        Assert.assertNotNull(id);
        logger.debug("getUid=" + id);
    }
    
    @Test
    public void getSuid(){
    	String suid=idGenerator.getSuid();
    	Assert.assertNotNull(suid);
    	logger.debug("getSuid=" + suid);
    }
}