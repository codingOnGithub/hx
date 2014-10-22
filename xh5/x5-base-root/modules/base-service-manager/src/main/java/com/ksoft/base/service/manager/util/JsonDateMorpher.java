package com.ksoft.base.service.manager.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

public class JsonDateMorpher extends AbstractObjectMorpher {

	private static final Logger logger = Logger
			.getLogger(JsonDateMorpher.class);

	private Date defaultValue;
	private String[] formats;
	private boolean lenient;
	private Locale locale;

	/**
	 * @param formats
	 *            a list of formats this morpher supports.
	 */
	public JsonDateMorpher(String[] formats) {
		this(formats, Locale.getDefault(), false);
	}

	/**
	 * @param formats
	 *            a list of formats this morpher supports.
	 * @param lenient
	 *            if the parsing should be lenient or not.
	 */
	public JsonDateMorpher(String[] formats, boolean lenient) {
		this(formats, Locale.getDefault(), lenient);
	}

	/**
	 * @param formats
	 *            a list of formats this morpher supports.
	 * @param defaultValue
	 *            return value if the value to be morphed is null.
	 */
	public JsonDateMorpher(String[] formats, Date defaultValue) {
		this(formats, defaultValue, Locale.getDefault(), false);
	}

	/**
	 * @param formats
	 *            a list of formats this morpher supports.
	 * @param defaultValue
	 *            return value if the value to be morphed is null.
	 * @param locale
	 *            the Locale used to parse each format.
	 * @param lenient
	 *            if the parsing should be lenient or not.
	 */
	public JsonDateMorpher(String[] formats, Date defaultValue, Locale locale,
			boolean lenient) {
		super(true);
		if (formats == null || formats.length == 0) {
			throw new MorphException("invalid array of formats");
		}
		// should use defensive copying ?
		this.formats = formats;

		if (locale == null) {
			this.locale = Locale.getDefault();
		} else {
			this.locale = locale;
		}

		this.lenient = lenient;
		setDefaultValue(defaultValue);
	}

	/**
	 * @param formats
	 *            a list of formats this morpher supports.
	 * @param locale
	 *            the Locale used to parse each format.
	 */
	public JsonDateMorpher(String[] formats, Locale locale) {
		this(formats, locale, false);
	}

	/**
	 * @param formats
	 *            a list of formats this morpher supports.
	 * @param locale
	 *            the Locale used to parse each format.
	 * @param lenient
	 *            if the parsing should be lenient or not.
	 */
	public JsonDateMorpher(String[] formats, Locale locale, boolean lenient) {
		if (formats == null || formats.length == 0) {
			throw new MorphException("invalid array of formats");
		}
		// should use defensive copying ?
		this.formats = formats;

		if (locale == null) {
			this.locale = Locale.getDefault();
		} else {
			this.locale = locale;
		}

		this.lenient = lenient;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (!(obj instanceof JsonDateMorpher)) {
			return false;
		}

		JsonDateMorpher other = (JsonDateMorpher) obj;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(formats, other.formats);
		builder.append(locale, other.locale);
		builder.append(lenient, other.lenient);
		if (isUseDefault() && other.isUseDefault()) {
			builder.append(getDefaultValue(), other.getDefaultValue());
			return builder.isEquals();
		} else if (!isUseDefault() && !other.isUseDefault()) {
			return builder.isEquals();
		} else {
			return false;
		}
	}

	/**
	 * Returns the default value for this Morpher.
	 */
	public Date getDefaultValue() {
		return (Date) defaultValue.clone();
	}

	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(formats);
		builder.append(locale);
		builder.append(lenient);
		if (isUseDefault()) {
			builder.append(getDefaultValue());
		}
		return builder.toHashCode();
	}

	public Object morph(Object value) {
		if (value == null) {
			return null;
		}

		if (Date.class.isAssignableFrom(value.getClass())) {
			return (Date) value;
		}

		if (!supports(value.getClass())) {
			throw new MorphException(value.getClass() + " is not supported");
		}

		String strValue = value.toString();
		SimpleDateFormat dateParser = null;

		for (int i = 0; i < formats.length; i++) {
			if (dateParser == null) {
				dateParser = new SimpleDateFormat(formats[i], locale);
			} else {
				dateParser.applyPattern(formats[i]);
			}
			dateParser.setLenient(lenient);
			try {
				return dateParser.parse(strValue.toLowerCase());
			} catch (ParseException pe) {
				// ignore exception, try the next format
			}
		}

		// 如果通过日期无法转换尝试时间戳转换
		logger.debug("Json转换，当前值Class:" + value.getClass().getName()
				+ ",Value:" + value.toString() + ",无法通过日期格式转换，开始尝试时间戳转换。");
		try {
			
			return new Date(Long.parseLong(value.toString()));
		} catch (ClassCastException e) {
			// 如果当前值无法转换为日期则直接返回原始值
			logger.debug("Json转换，当前值Class:" + value.getClass().getName()
					+ ",Value:" + value.toString() + ",无法转换为日期型。");
		}

		// unable to parse the date
		if (isUseDefault()) {
			return defaultValue;
		} else {
			throw new MorphException("Unable to parse the date " + value);
		}
	}

	public Class morphsTo() {
		return Date.class;
	}

	/**
	 * Sets the defaultValue to use if the value to be morphed is null.
	 * 
	 * @param defaultValue
	 *            return value if the value to be morphed is null
	 */
	public void setDefaultValue(Date defaultValue) {
		this.defaultValue = (Date) defaultValue.clone();
	}

	public boolean supports(Class clazz) {
		boolean support = false;

		if (String.class.isAssignableFrom(clazz)
				|| Long.class.isAssignableFrom(clazz)) {
			support = true;
		}

		return support;
	}
}
