package com.hotent.org;

import com.hotent.org.api.context.IdentityContext;
import com.hotent.org.core.OrgConfiguration;

import junit.framework.TestCase;

public class OrgConfigurationTest extends TestCase{
	IdentityContext identityContext=null;
	OrgConfiguration orgConfiguration=null;
	
	public void testGetIdentityContext(){
		orgConfiguration.getIdentityContext();
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		orgConfiguration=OrgConfiguration.getInstance();
	}
}
