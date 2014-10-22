package com.hotent.bpmx.persistence;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.test.BaseTestCase;

/**
 * 测试基类，。
 * 其下的测试类均继承该子类
 * @author zyp
 *
 */
@ContextConfiguration({"classpath:conf/x5-bpmx-core-test.xml"})
public class BpmxBaseTest extends BaseTestCase{
	
	@Resource
    protected IdGenerator idGenerator;
}
