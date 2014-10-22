package com.hotent.base.db;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.hotent.base.core.test.BaseTestCase;

@ContextConfiguration(locations={"classpath:conf/x5-base-db-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
public class DbBaseTest  extends BaseTestCase{

}
