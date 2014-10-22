package com.hotent.bpmx.core.engine.def;

import com.hotent.bpmx.activiti.def.impl.EclipseDefTransform;
import com.hotent.bpmx.activiti.def.impl.FlashDefTransform;
import com.hotent.bpmx.activiti.def.impl.WebDefTransform;
import com.hotent.bpmx.api.constant.DesignerType;
import com.hotent.bpmx.natapi.def.DefTransform;

public class DefTransFormUtil {

	/**
	 * 
	 * 根据类型做不同的转换。
	 * @param designerType
	 * @param designFile
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String transForm(DesignerType designerType,String id,String name, String designFile){
		DefTransform trans=null;
		switch (designerType) {
			case WEB:
				trans=new WebDefTransform();
				break;
			case ECLIPSE:
				trans=new EclipseDefTransform();
				break;
			case FLASH:
				trans=new FlashDefTransform();
				break;
		}
		return trans.convert(id,name,designFile);
		
	}

}
