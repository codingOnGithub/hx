package demo;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ObjectUtil {

	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";

	private static ObjectUtil instance = null;

	public ObjectUtil() {
	}

	/**
	 * <blockquote>
	 * <table summary="Element types and encodings">
	 * <tr>
	 * <th>Element Type
	 * <th>&nbsp;&nbsp;&nbsp;
	 * <th>Encoding
	 * <tr>
	 * <td>boolean
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>Z
	 * <tr>
	 * <td>byte
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>B
	 * <tr>
	 * <td>char
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>C
	 * <tr>
	 * <td>class or interface
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>L<i>classname</i>;
	 * <tr>
	 * <td>double
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>D
	 * <tr>
	 * <td>float
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>F
	 * <tr>
	 * <td>int
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>I
	 * <tr>
	 * <td>long
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>J
	 * <tr>
	 * <td>short
	 * <td>&nbsp;&nbsp;&nbsp;
	 * <td align=center>S
	 * </table>
	 * </blockquote>
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> toObjectMap(Object obj) {
		return toObjectMap(obj, false);
	}

	public static Map<String, Object> toObjectMap(Object obj, String dateFormat) {
		return toObjectMap(obj, false, dateFormat);
	}

	public static Map<String, Object> toObjectMap(Object obj,
			boolean useClassConvert) {
		return toObjectMap(obj, useClassConvert, DATE_FORMAT_FULL);
	}

	public static Map<String, Object> toObjectMap(Object obj,
			boolean useClassConvert, String dateFormat) {
		if (instance == null)
			instance = new ObjectUtil();
		return instance.toObject(obj, useClassConvert, dateFormat);
	}

	private Map<String, Object> toObject(Object o, boolean useClassConvert,
			String dateFormat) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 处理原始类型
		if (o == null)
			return toNull(map);
		// 代理处理
		o = proxyCheck(o);

		// String
		if (o instanceof String)
			return toString(o, map);

		// Number or boolean
		if (o instanceof Number || o instanceof Boolean)
			return toObject(o, map);

		// Map
		if (o instanceof Map)
			return toMap(o, map, useClassConvert, dateFormat);

		// Class
		if (o instanceof Class)
			return toClass(o, map);

		// 集合或数组
		if (o instanceof Collection || o.getClass().isArray())
			return toArray(o, map, useClassConvert, dateFormat);

		// 日期、时间
		if (o instanceof Date)
			return toDate(o, map, dateFormat);

		// 接口
		if (o.getClass().isInterface())
			return toObject(o, map);

		// 枚举
		if (o.getClass().isEnum())
			return toEnum(o, map);

		if (o instanceof Void)
			return toObject(o, map);
		// 对象
		return refObject(o, map, useClassConvert, dateFormat);
	}

	/**
	 * 代理类时做的检查.返回应该检查的对象.
	 * 
	 * @param bean
	 * @return
	 */
	protected Object proxyCheck(Object bean) {
		return bean;
	}

	/**
	 * null值处理
	 * 
	 * @param map
	 * @return
	 */
	private Map<String, Object> toNull(Map<String, Object> map) {
		map.put("null", null);
		return map;
	}

	/**
	 * 处理String
	 * 
	 * @param o
	 * @param map
	 * @return
	 */
	private Map<String, Object> toString(Object o, Map<String, Object> map) {
		return putObjectValue(o, map, o.toString());
	}

	/**
	 * 处理日期
	 * 
	 * @param o
	 * @param map
	 * @param dateFormat
	 * @return
	 */
	private Map<String, Object> toDate(Object o, Map<String, Object> map,
			String dateFormat) {
		DateFormat format = new SimpleDateFormat(dateFormat);
		return putObjectValue(o, map, format.format((Date) o));
	}

	/**
	 * 处理枚举
	 * 
	 * @param o
	 * @param map
	 * @return
	 */
	private Map<String, Object> toEnum(Object o, Map<String, Object> map) {
		return putObjectValue(o, map, (Enum<?>) o);
	}

	/**
	 * 处理Class
	 * 
	 * @param o
	 * @param map
	 * @return
	 */
	private Map<String, Object> toClass(Object o, Map<String, Object> map) {
		return putObjectValue(o, map, ((Class<?>) o).getSimpleName());
	}

	private Map<String, Object> toObject(Object o, Map<String, Object> map) {
		return putObjectValue(o, map, o);
	}

	/**
	 * 存放map
	 * 
	 * @param o
	 * @param map
	 * @param value
	 *            对象的值
	 * @return
	 */
	private Map<String, Object> putObjectValue(Object o,
			Map<String, Object> map, Object value) {
		map.put(o.getClass().getName(), value);
		return map;
	}

	/**
	 * 处理对象
	 * 
	 * @param o
	 * @param map
	 * @param useClassConvert
	 * @param dateFormat
	 * @return
	 */
	private Map<String, Object> refObject(Object o, Map<String, Object> map,
			boolean useClassConvert, String dateFormat) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		Method[] aryMethod = o.getClass().getDeclaredMethods();
		for (Method method : aryMethod) {
			try {
				String key = "";
				String name = method.getName();
				if (name.startsWith("get")) {
					key = name.substring(3);
				} else if (name.startsWith("is")) {// Boolean 是is
					key = name.substring(2);
				}

				if (key.length() > 0 && Character.isUpperCase(key.charAt(0))
						&& method.getParameterTypes().length == 0) {
					// if (key.length() == 1) {
					// key = key.toLowerCase();
					// } else if (!Character.isUpperCase(key.charAt(1))) {
					// key = key.substring(0, 1).toLowerCase()
					// + key.substring(1);
					// }
					Object elementObj = method.invoke(o);
					// 是否进行Class 转换
					if (!useClassConvert && elementObj instanceof Class)
						continue;
					key = toFirstLowerCase(key);
					map2.put(key,
							toObject(elementObj, useClassConvert, dateFormat));
				}
			} catch (Exception e) {
				/**//* forget about it */
				System.out.println(e);
			}
		}
		return putObjectValue(o, map, map2);
	}

	/**
	 * 把一个字符串的第一个字母大写、效率是最高的
	 * 
	 * @param key
	 * @return
	 */
	private static String toFirstLowerCase(String key) {
		byte[] items = key.getBytes();
		if (items[0] >= 65 && items[0] <= 90)
			items[0] = (byte) ((char) items[0] - 65 + 97);
		return new String(items);
	}

	/**
	 * 集合与数组处理
	 * 
	 * @param o
	 * @param map
	 * @param useClassConvert
	 * @param dateFormat
	 * @return
	 */
	private Map<String, Object> toArray(Object o, Map<String, Object> map,
			boolean useClassConvert, String dateFormat) {

		if (o == null)
			return toNull(map);

		o = proxyCheck(o);

		List<Object> list = new ArrayList<Object>();
		// 集合
		if (o instanceof Collection) {
			Iterator<?> iterator = ((Collection<?>) o).iterator();
			while (iterator.hasNext()) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				Object rowObj = iterator.next();
				list.add(addList(rowObj, map2, useClassConvert, dateFormat));
			}
		}
		// 数组
		if (o.getClass().isArray()) {
			int arrayLength = Array.getLength(o);
			for (int i = 0; i < arrayLength; i++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				Object rowObj = Array.get(o, i);
				list.add(addList(rowObj, map2, useClassConvert, dateFormat));
			}
		}
		return putObjectValue(o, map, list);

	}

	/**
	 * 增加List
	 * 
	 * @param o
	 * @param map
	 * @param useClassConvert
	 * @param dateFormat
	 * @return
	 */
	private Map<String, Object> addList(Object o, Map<String, Object> map,
			boolean useClassConvert, String dateFormat) {
		if (o == null)
			return toNull(map);
		else if (o.getClass().isArray() || o instanceof Collection)
			return toArray(o, map, useClassConvert, dateFormat);
		else
			return toObject(o, useClassConvert, dateFormat);
	}

	/**
	 * 处理Map
	 * 
	 * @param o
	 * @param map
	 * @param useClassConvert
	 * @param dateFormat
	 * @return
	 */
	private Map<String, Object> toMap(Object o, Map<String, Object> map,
			boolean useClassConvert, String dateFormat) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		Iterator<?> iterator = ((Map<?, ?>) o).keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next().toString();
			Object valueObj = ((Map<?, ?>) o).get(key);
			map2.put(key, toObject(valueObj, useClassConvert, dateFormat));
		}
		return putObjectValue(o, map, map2);
	}

}
