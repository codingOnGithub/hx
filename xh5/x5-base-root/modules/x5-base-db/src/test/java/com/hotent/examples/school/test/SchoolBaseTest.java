package com.hotent.examples.school.test;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;

import com.hotent.base.db.api.IdGenerator;
import com.hotent.base.test.BaseTestCase;

/**
 * 学校例子测试基类，建议每个模块有一个总的测试基类。
 * 其下的测试类均继承该子类
 * @author csx
 *
 */
@ContextConfiguration({"classpath:conf/x5-examples.xml"})
public class SchoolBaseTest extends BaseTestCase{
    @Resource
    protected IdGenerator idGenerator;
}
