package com.hotent.bo.persistence.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.core.xml.Dom4jUtil;

public class XMLDataObject extends AbstractDataObject {
	private String xml;
	
	/**
	 * 处理路径中的数字类型索引
	 * <pre>
	 * path中传进来的索引是从0开始，xpath的语法中是从1开始
	 * </pre>
	 * @param str
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	private String regReplace(String str){
        StringBuffer resultString = new StringBuffer();
        try {
            Pattern regex = Pattern.compile("\\[(\\d)\\]");
            Matcher regexMatcher = regex.matcher(str);
            while (regexMatcher.find()) {
                String s = "[" + (Integer.parseInt(regexMatcher.group(1)) + 1) + "]";
                regexMatcher.appendReplacement(resultString, s);
            }
            regexMatcher.appendTail(resultString);
        } catch (PatternSyntaxException ex) {
            ex.printStackTrace();
        }
        return resultString.toString();
    }
	
	private String handlerPath(String path){
		Pattern regex = Pattern.compile("^((\\w+)\\.).*");
		Matcher regexMatcher = regex.matcher(path);
		if (regexMatcher.find()) {
			String fullFirtNode = regexMatcher.group(1);
			String pathFirstNode = regexMatcher.group(2);
			if(BeanUtils.isEmpty(this.boDef) || pathFirstNode.equals(this.boDef.getName())){
				//传递进来的path处理成xpath能识别的格式
				path = path.replaceAll("\\[\\*\\]", "").replaceFirst(fullFirtNode, "").replaceAll("\\.", "/");
				path = regReplace(path);
			}
		}
		else{
			//传入的path只有一个字节，比如person，就直接返回空字符串
			path = "";
		}
		return path;
	}
	
	//通过xpath获取Elements
	private List<Element> getByXpath(Element element,String path){
		String key = handlerPath(path);
		//如果处理path后返回空字符串，则将根节点作为结果返回
		if(BeanUtils.isEmpty(key)){
			List<Element> ary = new ArrayList<Element>();
			ary.add(element);
			return ary;
		} 
		//通过xpath获取Elements
		List nodes = element.selectNodes(key);
		return nodes;
	}
	
	@Override
	public Object get(String key) {
		initInstData();
		Document document = Dom4jUtil.loadXml(this.xml);
		Element el = document.getRootElement();
		List<Element> nodes = getByXpath(el,key);
		int size = nodes.size();
		//获取到的列表为空，则返回空字符串
		if(size <= 0){
			return "";
		}
		Element node = nodes.get(0);
		//判断获取到的元素有没有子节点
		boolean isTextOnly = node.isTextOnly();
		//只获取到一个元素
		if(size == 1){
			if(isTextOnly){
				return node.getText();
			}
			else{
				return node;
			}
		}
		//获取到多个元素
		else{
			if(isTextOnly){
				List<String> list = new ArrayList<String>();
				for(Element e : nodes){
					list.add(e.getText());
				}
				return list.toArray();
			}
			else{
				return nodes;
			}
		}
	}
	
	//构建初始化的xml数据
	private void initInstData(){
		if(BeanUtils.isEmpty(this.xml)){
			String rootName = this.boDef==null?"root" : this.boDef.getName();
			if(BeanUtils.isNotEmpty(this.attrName)){
				rootName = this.attrName;
			}
			this.xml = "<" +rootName +"></" +rootName +">";
		}
	}
	
	//递归构建Element
	private Element createElementByKeyValue(Element rootElement,String key){
		List<Element> nodes = getByXpath(rootElement,key);
		if(nodes.size()<=0){
			String code = "";
			Pattern regex = Pattern.compile("^(.*)\\.(.*)$");
			Matcher regexMatcher = regex.matcher(key);
			while (regexMatcher.find()) {
				key = regexMatcher.group(1);
				code =  regexMatcher.group(2);
			}
			Element parentElement = createElementByKeyValue(rootElement,key);
			return parentElement.addElement(code);
		}
		else{
			return nodes.get(0);
		}
	}
	
	//将纯文本值设置到Element的text中
	private void setText2Element(List nodes,List<String> ary){
		int nodeCount = nodes.size();
		int aryCount = ary.size();
		Element node = (Element)nodes.get(0);
		for(int i=0;i<aryCount;i++){
			if(i >= nodeCount){
				//node节点不足，则补齐
				nodes.add(i, node.clone());
			}
			((Element)nodes.get(i)).setText(ary.get(i));
		}
		int gap = nodeCount - aryCount;
		while(gap > 0){
			//移除多余的node节点
			((Element)nodes.get(nodeCount-gap)).detach();
			gap--;
		}
	}
	
	//用xml格式的值替换掉现有的Element
	private void setXml2Element(List nodes,List<String> ary){
		Element node = (Element)nodes.get(0);
		Element parentElement = node.getParent();
		//移除原有的元素
		for(Object obj : nodes){
			Element element = (Element)obj;
			element.detach();
		}
		//通过传入的xml片段重新构建元素
		for(String str : ary){
			String xml = "<root>" + str + "</root>";
			Document d = Dom4jUtil.loadXml(xml);
			List newObjs = d.getRootElement().elements();
			for(Object obj : newObjs){
				Element e = (Element)obj;
				e.detach();
				parentElement.add(e);
			}
		}
	}
	
	//字符串是否是xml结构
	private boolean isXmlStruct(String str){
		try{
			DocumentHelper.parseText(str);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private void setValue2Element(List nodes,Object value){
		if(BeanUtils.isEmpty(value))return;
		List<String> ary;
		if(value instanceof List){
			ary = (List<String>)value;
		}
		else if(value instanceof Object[]){
			Object[] objs = (Object[])value;
			ary = new ArrayList<String>();
			for(Object obj : objs){
				ary.add(obj.toString());
			}
		}
		else{
			ary = new ArrayList<String>();
			ary.add(value.toString());
		}
		Object firstObj = ary.get(0);
		//如果出入的value是[null]格式，则直接返回
		if(BeanUtils.isEmpty(firstObj))return;
		if(isXmlStruct(firstObj.toString())){
			setXml2Element(nodes, ary);
		}
		else{
			setText2Element(nodes, ary);
		}
	}
	
	@Override
	public void set(String key, Object value) {
		initInstData();
		Document document = Dom4jUtil.loadXml(this.xml);
		Element el = document.getRootElement();
		List<Element> nodes = getByXpath(el, key);
		int size = nodes.size();
		//未获取到要设置的元素
		if(size <= 0){
			//通过xpath递归构建Element
			Element initElement = createElementByKeyValue(el, key);
			nodes = new ArrayList<Element>();
			nodes.add(initElement);
		}
		setValue2Element(nodes, value);
		this.xml = Dom4jUtil.docToPrettyString(document);
		this.xml = this.xml.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}

	@Override
	public String getInstData() {
		return this.xml;
	}

	@Override
	public void setInstData(String instData) {
		if(BeanUtils.isEmpty(instData))return;
		this.xml = instData;
	}

	@Override
	public String getDataType() {
		return "xml";
	}
}
