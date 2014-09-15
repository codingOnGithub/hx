package com.hotent.platform.dao.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hotent.BaseTestCase;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;

public class ResourceInit extends BaseTestCase {
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void execute() throws Exception {
		StringBuffer sb = new StringBuffer();

		String path = new File("").getAbsolutePath() + File.separator
				+ "src/main/resources/conf/resourceConfig.xml";
		InputStream stream = new BufferedInputStream(new FileInputStream(path));
		String xml = FileUtil.inputStream2String(stream, "utf-8");
		stream.close();
		Document document = Dom4jUtil.loadXml(xml);
		Element root = document.getRootElement();
		List<Element> list = root.elements();
		// <<表,<原来的ID，现在的ID>>
		Map<String, Map<Long, Long>> tableMap = new HashMap<String, Map<Long, Long>>();
		for (Element e : list) {
			String pk = e.attributeValue("pk");
			String template = e.elementText("template");
			String sql = e.elementText("sql");
			// 父类ID
			String parentId = e.attributeValue("parentId");
			// 父类ID的值
			Long parentIdVal = StringUtil.isNotEmpty(parentId) ? Long
					.parseLong(e.attributeValue("parentIdVal")) : 0L;
			// 表
			String table = e.attributeValue("table");
			// 主表
			String mainTable = e.attributeValue("mainTable");
			// 外键
			String fk = e.attributeValue("fk");

			Map<String, Object> variables = new HashMap<String, Object>();
			if (StringUtil.isNotEmpty(parentId))
				variables.put(parentId, parentIdVal);

			List<Map<String, Object>> dataList = getNewData(sql, pk, parentId,
					parentIdVal, 0L, table, mainTable, fk, variables, tableMap,
					new HashMap<Long, Long>(),
					new ArrayList<Map<String, Object>>());

			for (Map<String, Object> dataMap : dataList) {
				sb.append(freemarkEngine.parseByStringTemplate(dataMap,
						template));
			}
		}
		sb.append("\ncommit;");
		FileUtil.writeFile(root.attributeValue("filePath"), sb.toString());
	}

	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> getNewData(String sql, String pk,
			String parentId, Long parentIdVal, Long newPkValue, String table,
			String mainTable, String fk, Map<String, Object> variables,
			Map<String, Map<Long, Long>> tableMap, Map<Long, Long> pkMap,
			List<Map<String, Object>> dataList) throws Exception {

		String sqlStr = freemarkEngine.parseByStringTemplate(variables, sql)
				.trim();
		List<Map<String, Object>> dataMaps = jdbcTemplate.queryForList(sqlStr);
		if (BeanUtils.isEmpty(dataMaps))
			return dataList;
		Map<Long, Long> mainPkMap = tableMap.get(mainTable);
		// 行遍历数据
		for (Map<String, Object> map : dataMaps) {
			Iterator<?> it = map.entrySet().iterator();
			Map<String, Object> dataMap = new HashMap<String, Object>();
			boolean flag = true;
			// 遍历一行数据
			while (it.hasNext()) {
				Map.Entry<String, Object> entry = ((Entry<String, Object>) it
						.next());
				String key = entry.getKey();
				Object val = entry.getValue();
				// 主键
				if (key.equals(pk.toUpperCase())) {
					Long pkVal = Long.parseLong(val.toString());
					Long newPkVal = new Long(dataList.size() + 1);
					pkMap.put(pkVal, newPkVal);
					entry.setValue(newPkVal);
					variables.put(parentId, val.toString());
				}
				// 父类ID
				if (StringUtil.isNotEmpty(parentId)
						&& key.equals(parentId.toUpperCase()))
					entry.setValue(newPkValue);
				// 外键
				if (StringUtil.isNotEmpty(fk) && key.equals(fk.toUpperCase())
						&& BeanUtils.isNotEmpty(mainPkMap)) {
					Long fkVal = Long.parseLong(val.toString());
					if (BeanUtils.isEmpty(mainPkMap.get(fkVal))) {
						flag = false;
						break;
					}
					entry.setValue(mainPkMap.get(fkVal).toString());
				}

				//特殊字符的处理
				if(val instanceof String && BeanUtils.isNotEmpty(val))
					entry.setValue(val.toString().replaceAll("&", "'||'&'||'"));
				
				dataMap.put(key, entry.getValue());
			}
			if (flag)
				dataList.add(dataMap);

			// 有父类ID的递归
			if (StringUtil.isNotEmpty(parentId))
				getNewData(sql, pk, parentId, parentIdVal,
						Long.parseLong(dataMap.get(pk).toString()), table,
						mainTable, fk, variables, tableMap, pkMap, dataList);

		}
		tableMap.put(table, pkMap);
		return dataList;
	}

}
