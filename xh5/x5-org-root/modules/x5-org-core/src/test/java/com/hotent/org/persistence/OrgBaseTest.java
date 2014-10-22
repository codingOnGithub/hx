package com.hotent.org.persistence;

import javax.annotation.Resource;
import org.springframework.test.context.ContextConfiguration;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.test.BaseTestCase;

@ContextConfiguration({"classpath:conf/x5-org-core-test.xml"})
public class OrgBaseTest extends BaseTestCase{
    @Resource
    protected IdGenerator idGenerator;
}
  