package com.hotent.bpmx.activiti.def.impl;

import com.hotent.bpmx.natapi.def.DefTransform;

public class WebDefTransform implements DefTransform {

	@Override
	public String convert(String id, String name, String designerXml) {
		
		return designerXml;
	}

	
	

}
