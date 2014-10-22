package com.ksoft.base.service.manager.util;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;

import com.ksoft.base.service.manager.util.AssertValue;

public class JsonPluginsUtilDateValueProcessor implements JsonValueProcessor {

	private static final Logger logger = Logger
			.getLogger(JsonPluginsUtilDateValueProcessor.class);

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		logger.debug("JSON日期处理 Value:" + value);
		return this.parse(value);
	}

	@Override
	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		logger.debug("JSON日期处理 Key:" + key + ",Value:" + value);
		return this.parse(value);
	}

	private Object parse(Object value) {
		if (AssertValue.isNotNull(value)) {
			Date tempDate = (Date) value;
			return tempDate.getTime();
		} else {
			return value;
		}
	}

}
