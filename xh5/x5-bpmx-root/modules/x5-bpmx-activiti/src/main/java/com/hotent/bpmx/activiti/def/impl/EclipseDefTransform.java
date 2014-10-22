package com.hotent.bpmx.activiti.def.impl;

import com.hotent.bpmx.activiti.def.BpmDefUtil;
import com.hotent.bpmx.natapi.def.DefTransform;

public class EclipseDefTransform implements DefTransform {

	@Override
	public String convert(String id, String name, String designXml) {
		
		return BpmDefUtil.transBpmDef(id, name, designXml);
	}

	

	

}
