package com.hotent.org.persistence.manager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AttributeManagerTest.class, AttributeValueManagerTest.class, DimensionManagerTest.class, GroupManagerTest.class, RelationTypeManagerTest.class,
		UserManagerTest.class })
public class AllManagerTests {

}
