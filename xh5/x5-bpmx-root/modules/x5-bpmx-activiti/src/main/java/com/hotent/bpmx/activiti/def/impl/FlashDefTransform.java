package com.hotent.bpmx.activiti.def.impl;

import com.hotent.bpmx.natapi.def.DefTransform;



public class FlashDefTransform implements DefTransform {

	@Override
	public String convert(String id, String name, String designXml) {
		
		return designXml;
	}

	

	

}
