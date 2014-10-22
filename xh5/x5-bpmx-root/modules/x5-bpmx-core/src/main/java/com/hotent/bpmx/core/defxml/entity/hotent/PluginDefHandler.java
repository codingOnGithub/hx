package com.hotent.bpmx.core.defxml.entity.hotent;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.DomHandler;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import com.hotent.base.core.xml.Dom4jUtil;

public class PluginDefHandler implements DomHandler<String, StreamResult> {

	private static final String PLUGIN_DEF_START_TAG = "<[^:]+:)?plugin-def[^>]*>";
	private static final String PLUGIN_DEF_END_TAG = "</[^:]+:)?plugin-def[^>]*>\\s*$";

	private StringWriter xmlWriter = new StringWriter();

	public StreamResult createUnmarshaller(ValidationEventHandler errorHandler) {
		return new StreamResult(xmlWriter);
	}

	@Override
	public String getElement(StreamResult rt) {
		Writer writer = rt.getWriter();
		String xml = rt.getWriter().toString();
		
		String validXml = xml;
		try {
			String splitB = "<split>";
			String splitE = "</split>";

			String regex = "<split>-?\\d+</split>";
			
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(xml);
			List<Integer[]> list = new ArrayList<Integer[]>();
			while(matcher.find()){
				Integer[] ps = new Integer[]{matcher.start(),matcher.end()};
				list.add(ps);
			}
			for(int i = list.size();i>0;i--){
				int start = list.get(i-1)[0]+splitB.length();
				int end = list.get(i-1)[1] - splitE.length();

				int nhc = xml.substring(0, start).hashCode();
				
				String hcstr = xml.substring(start,end);
				int hc = Integer.valueOf(hcstr);
				
				if(nhc == hc){
					validXml = xml.substring(list.get(i-1)[1]);
					break;
				}
			}


			writer.write(UUID.randomUUID()+splitB);
			String str = writer.toString();
			int hashCode = str.hashCode();
			writer.write(hashCode + splitE);

		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
//		System.out.println(validXml);
//		System.out.println("--------------------------------");

		return validXml;
	}

	@Override
	public Source marshal(String n, ValidationEventHandler errorHandler) {
		// try {
		String xml = PLUGIN_DEF_START_TAG + n.trim() + PLUGIN_DEF_END_TAG;
		StringReader xmlReader = new StringReader(xml);
		return new StreamSource(xmlReader);
		// } catch(Exception e) {
		// throw new RuntimeException(e);
		// }
	}

}