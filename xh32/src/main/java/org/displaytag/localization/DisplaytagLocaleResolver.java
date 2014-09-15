package org.displaytag.localization;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.hotent.core.util.ContextUtil;

/**
 * Displaytag的设置的语言环境
 * 
 * @author zxh
 * 
 */
public class DisplaytagLocaleResolver implements LocaleResolver {

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		return ContextUtil.getLocale();
	}

}
