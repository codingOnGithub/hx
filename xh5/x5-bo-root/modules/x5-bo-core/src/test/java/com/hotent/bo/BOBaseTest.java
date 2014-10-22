package com.hotent.bo;

import javax.annotation.Resource;
import org.springframework.test.context.ContextConfiguration;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.test.BaseTestCase;

@ContextConfiguration({"classpath:conf/x5-bo-core-test.xml"})
public class BOBaseTest extends BaseTestCase{
    @Resource
    protected IdGenerator idGenerator;
}
  