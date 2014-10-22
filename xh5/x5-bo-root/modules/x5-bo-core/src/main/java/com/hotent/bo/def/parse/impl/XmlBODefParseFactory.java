package com.hotent.bo.def.parse.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.xmlbeans.XmlException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import cn.jeeSoft.x5BoDef.Attribute;
import cn.jeeSoft.x5BoDef.Bodef;
import cn.jeeSoft.x5BoDef.BodefcfgDocument;
import cn.jeeSoft.x5BoDef.HasMany;
import cn.jeeSoft.x5BoDef.HasOne;
import cn.jeeSoft.x5BoDef.Mainmodel;
import cn.jeeSoft.x5BoDef.Rule;
import cn.jeeSoft.x5BoDef.Ruleconfig;
import cn.jeeSoft.x5BoDef.Rules;
import cn.jeeSoft.x5BoDef.Submodel;

import com.hotent.base.core.util.BeanUtils;
import com.hotent.base.core.xml.Dom4jUtil;
import com.hotent.base.db.api.IdGenerator;
import com.hotent.bo.def.parse.BODefParseFactory;
import com.hotent.bo.exception.BOBaseException;
import com.hotent.bo.persistence.model.BOAttribute;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_IS_LIST;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_IS_REQUIRED;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_REF_TYPE;
import com.hotent.bo.persistence.model.BOAttribute.BOATTRIBUTE_TYPE;
import com.hotent.bo.persistence.model.BODef;
import com.hotent.bo.persistence.model.BODef.BODEF_IS_MAIN;
import com.hotent.bo.persistence.model.BODef.BODEF_IS_MASTER;
import com.hotent.bo.persistence.model.BODef.BODEF_STATUS;
import com.hotent.bo.persistence.model.BORule;
import com.hotent.bo.persistence.model.BORule.BORULE_BELONG_TYPE;
import com.hotent.bo.persistence.model.BORule.BORULE_TYPE;



/**
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bo-core
 * 作者：xcux
 * 邮箱:xucx@jee-soft.cn
 * 日期:2014-1-24-下午3:40:00
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
/**
 * <pre> 
 * 描述：TODO
 * 构建组：x5-bo-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-2-13-下午3:46:28
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
@Repository
public class XmlBODefParseFactory implements BODefParseFactory {
	
	protected Logger logger = LoggerFactory.getLogger(XmlBODefParseFactory.class);
	
	private final static String TNS_XML = "http://www.jee-soft.cn/x5-bo-def";
	
	private final static String XSI_XML = "http://www.w3.org/2001/XMLSchema-instance";
	
	private final static String SCHEMALOCATION_XML = "http://www.jee-soft.cn/x5-bo-def ../xsd/x5-bo-def.xsd";
	
	@Resource
	IdGenerator idGenerator;
	
	/**
	 * 解析xml
	 * <pre>
	 * 示例：
	 * String xml="XML的字串内容";
	 * BODef bodef = bODefParseFactory.parse(xml);   //BODefParseFactory类
	 * 返回:
	 * BODef对象
	 * </pre>
	 * @param xml
	 * @return BODef
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	public BODef parse(String xml) {
		BodefcfgDocument document;
		Bodef bodefcfg;;
		try{
			document = BodefcfgDocument.Factory.parse(xml);
			bodefcfg = document.getBodefcfg();
		} catch (XmlException e){
			throw new BOBaseException("解析业务对象的xml出错！！！");
		}
		
		if(BeanUtils.isEmpty(bodefcfg)){
			logger.info("解析业务对象的xml出错，没有找到<tns:bodefcfg>标签！！！");
			return null;
		}
		BODef bODef = analyzeBODef(bodefcfg);
		return bODef;
	}
	
	
	
	/**
	 * 解析BODef
	 * <pre>
	 * 示例：
	 * String xml="XML的字串内容";
	 * BodefcfgDocument document = BodefcfgDocument.Factory.parse(xml);
	 * Bodef bodefcfg = document.getBodefcfg();
	 * BODef bODef = analyzeBODef(bodefcfg);
	 * 返回:
	 * BODef对象
	 * </pre>
	 * @param bodefcfg
	 * @return BODef
	 * @exception 
	 * @since  1.0.0
	 */
	private BODef analyzeBODef(Bodef bodefcfg) {
		String cagetory = bodefcfg.getCagetory();
		String dataType = bodefcfg.getDatatype();
		BODef mainBODef = null;
		//包为空
		if(BeanUtils.isEmpty(cagetory)){
			logger.info("包名为空，不能创建！！！");
			return null;
		}
		
		//创建业务实体
		Mainmodel mainModef= bodefcfg.getMainmodel();
		if(BeanUtils.isEmpty(mainModef)){
			logger.info("主业务对象为空，不能创建！！！");
			return null;
		}
		Submodel[] submodels = bodefcfg.getSubmodelArray();
		//把主业务对象插入到第一个bmodels数据元素
		Submodel[] bmodels = new Submodel[submodels.length+1];
		bmodels[0] = mainmodelToSubmodel(mainModef);
		//把从属对象相应插入到bmodels当中
		for (int i = 0; i < submodels.length; i++){
			bmodels[i+1] = submodels[i];
		}
		Map<String,BODef> bODefMap = new HashMap<String, BODef>();
		List<BODef> boList = new ArrayList<BODef>();
		Map<String,Submodel> bomodelMap = new HashMap<String, Submodel>();
		for (int i = 0; i < bmodels.length; i++){
			Submodel bomodel = bmodels[i];
			//初始化业务对象数据
			BODef bd = new BODef();
			bd.setDataType(dataType);
			bd.setName(bomodel.getName());
			bd.setDesc(bomodel.getDescription());
			bd.setCode("0");
			bd.setVersion(0l);       
			//*状态。inactive=未激活；actived=激活；forbidden=禁用
			bd.setStatus(BODEF_STATUS.ACTIVED);             
			 //是否主版本：Y：主版本，N：非主版本   且只有一个主版本
			bd.setIsMain(BODEF_IS_MAIN.MAIN_YES);      
			//数组的第一个：主对象的个性数据处理（只有一个）
			if(i==0){ 
				bd.setPackage(cagetory);                        				
				//是否主对象：Y：主对象，N：从对象   且只有一个主对象
				bd.setIsMaster(BODEF_IS_MASTER.MASTER_YES);   
				mainBODef = bd;
			}else{
				bd.setIsMaster(BODEF_IS_MASTER.MASTER_NO);   
			}
			bODefMap.put(bomodel.getName(),bd);
			boList.add(bd);
			bomodelMap.put(bomodel.getName(),bomodel);
		}
		
		//没有设置主业务对象
		if(BeanUtils.isEmpty(mainBODef)){
			logger.info("解析出错，没有设置主业务对象！！！");
			return null;
		}
		
		//创建属性规则  brList 在上面解析对象属性时会保存有属性对应的规则 (注意：先处理公共规则再到下面的属性及其规则)
		Rules rules = bodefcfg.getRules();
		analyzeRules(mainBODef, rules);	
		
		//解析对象属性和属性对应的规则
		analyzeBoAttribute(mainBODef,bomodelMap,bODefMap,mainBODef);     //解析对象属性和属性对应的规则

		return mainBODef;

	}
	
	
	
	/**
	 * 把主业务对象转成变通对象
	 * @param mainModef  XML中主业务对象
	 * @return XML中Submodel对象
	 * Submodel
	 * @exception 
	 * @since  1.0.0
	 */
	private Submodel mainmodelToSubmodel(Mainmodel mainModef){
		//通过工工厂类创建新的对象
		Submodel sub = Submodel.Factory.newInstance();
		sub.setName(mainModef.getName());
		sub.setDescription(mainModef.getDescription());
		sub.setAttributeArray(mainModef.getAttributeArray());
		sub.setListArray(mainModef.getListArray());
		sub.setHasOneArray(mainModef.getHasOneArray());
		sub.setHasManyArray(mainModef.getHasManyArray());
		return sub;
	}
	
	
	/**
	 * 解析属性及其规则和引用对象
	 * @param bODef 要分析业务对象(BODef)
	 * @param bomodelMap  所有对象数据的原始集合（Map<String,Bomodel>）
	 * @param bODefMap 已解析过的对象的容器集合（Map<String,BODef>）
	 * @param mainBODef  主业务对象的副本容器(BODef)
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	private BODef analyzeBoAttribute(BODef bODef,Map<String,Submodel> bomodelMap,Map<String,BODef> bODefMap,BODef mainBODef) {
		//解析对象为空时直接返回
		if(BeanUtils.isEmpty(bODef)){  
			return bODef;
		}
		//解析对象为主对象时直接赋值给主对象容器：mainBODef
		if(bODef.getIsMaster()==BODEF_IS_MASTER.MASTER_YES){   
			mainBODef = bODef;
		}
		
		//通过在bODefMap容器中是否存在过解析了的对象，存在则跳过，不存在就解析
		Set<String> setKey = bomodelMap.keySet();
		if(setKey.contains(bODef.getName())){
			//获取对象的bomodel
			Submodel  mainBomodel = bomodelMap.get(bODef.getName());
			
			//解析对象下的简单属性
			Attribute[] atts= mainBomodel.getAttributeArray(); 
			if(atts.length>0){
				initAttrHasRule(bODef, atts, BOATTRIBUTE_IS_LIST.LIST_NO, mainBODef);
			}
			
			//解析对象下的LIST标签的属性
			cn.jeeSoft.x5BoDef.List[]  x5lists = mainBomodel.getListArray();
			for (int i = 0; i < x5lists.length; i++){
				cn.jeeSoft.x5BoDef.List x5Lt = x5lists[i];
				Attribute[] attLts = x5Lt.getAttributeArray();
				initAttrHasRule(bODef, attLts, BOATTRIBUTE_IS_LIST.LIST_YES, mainBODef);
			}
			
			//解析对象下的HASONE属性
			HasOne[] hasOnes =  mainBomodel.getHasOneArray();		
			for (HasOne hasOne : hasOnes){
				String hasOneName = hasOne.getName();
				String hasOneRel = hasOne.getRel();
				BODef oneRefBODef = bODefMap.get(hasOneRel);
				BOAttribute baHo = new BOAttribute();
				baHo.setName(hasOneName);
				baHo.setType(BOATTRIBUTE_TYPE.REF);
				baHo.setRefType(BOATTRIBUTE_REF_TYPE.HAS_ONE);
				baHo.setIsList(BOATTRIBUTE_IS_LIST.LIST_NO);
				baHo.setIsRequired(BOATTRIBUTE_IS_REQUIRED.REQUIRED_NO);
				 //获取依赖的对象信息（递归方法）
				oneRefBODef = analyzeBoAttribute(oneRefBODef, bomodelMap, bODefMap, mainBODef);    
				baHo.setRefBODef(oneRefBODef);
				//把对象属性存放入业务对象属性的LIST
				bODef.getBOAttributeList().add(baHo);   
			}
			
			//解析对象下的HASMANY属性
			HasMany[] hasManys =  mainBomodel.getHasManyArray();		
			for (HasMany hasMany : hasManys){
				String hasManyName = hasMany.getName();
				String hasManyRel = hasMany.getRel();
				BODef manysRefBODef = bODefMap.get(hasManyRel);
				BOAttribute baHm = new BOAttribute();
				baHm.setName(hasManyName);
				baHm.setType(BOATTRIBUTE_TYPE.REF);
				baHm.setRefType(BOATTRIBUTE_REF_TYPE.HAS_MANY);
				baHm.setIsList(BOATTRIBUTE_IS_LIST.LIST_NO);
				baHm.setIsRequired(BOATTRIBUTE_IS_REQUIRED.REQUIRED_NO);
				//获取依赖的对象信息（递归方法）
				manysRefBODef = analyzeBoAttribute(manysRefBODef, bomodelMap, bODefMap, mainBODef);     
				baHm.setRefBODef(manysRefBODef);
				//把对象属性存放入业务对象属性的LIST
				bODef.getBOAttributeList().add(baHm);     
			}	
			//把解析过后的对象重新返回给bODefMap
			bODefMap.put(bODef.getName(), bODef);    
			//删除解析过的，防止重复解析对象
			bomodelMap.remove(bODef.getName());            
			return bODef;              
		}else{
			//如果没有获得主对象数据，证明之前有解析过了，就直接从解析结果bODefMap中抽取解析后的对象
			BODef bODefNo= bODefMap.get(bODef.getName());          
			return bODefNo;
		}
	}

	
	/**
	 * 解析 非依赖业务对象的属性
	 * @param bODef 要分析业务对象(BODef)
	 * @param atts  解析对象的属性原始数组（Attribute[]）
	 * @param isList 是否是LIST标签下面的属性（String）
	 * @param mainBODef  主业务对象的副本容器(BODef)
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	private BODef initAttrHasRule(BODef bODef, Attribute[] atts, char isList, BODef mainBODef){
		for(Attribute att : atts){
			//初始化属性数据
			BOAttribute ba = new BOAttribute();
			ba.setName(att.getName());
			ba.setDesc(att.getDescription());
            ba.setDataType(att.getType());
            ba.setDefaultValue(att.getDefault());
            ba.setFormat(att.getFormat());
            ba.setIsRequired(att.getIsRequire()==true?BOATTRIBUTE_IS_REQUIRED.REQUIRED_YES:BOATTRIBUTE_IS_REQUIRED.REQUIRED_NO);
            //是否是LIST标签的属性数据
            ba.setIsList(isList);
            ba.setType(BOATTRIBUTE_TYPE.BASE);
            
           //解析对象下的简单属性的规则
            Ruleconfig rc =  att.getRuleconfig();
            List<BORule> attRuleList = new ArrayList<BORule>();
            if(BeanUtils.isNotEmpty(rc)){
            	Rule[] ras= rc.getRuleArray();
            	for (Rule ra : ras){
            		BORule br = new BORule();
            		//依赖规则时就对象的属性个性规则    	
            		if(BORULE_TYPE.REFERENCE.equals(ra.getType().toString().toLowerCase())){   	
	    				//把依赖的规则ID设置到中间表对象中
	    				List<BORule> mainBoRuleList = mainBODef.getBORuleList();
	    				for (BORule mainBoRule : mainBoRuleList){
	    					//找到主业务对象中公共规则的对应规则内容
	    					if(ra.getRel().equals(mainBoRule.getName())){     
	    						br = mainBoRule;
			    				br.setBelongType(BORULE_BELONG_TYPE.DEF);
			    				break;
	    					}  
						}	      
	    			//所依赖的规则名称
            		}else{
	        			br.setName(ra.getRel());                  
	    				br.setContent(ra.getContent());
	    				br.setTipInfo(ra.getTipinfo());
	    				br.setRuleType(ra.getType().toString().toLowerCase());
	    				br.setBelongType(BORULE_BELONG_TYPE.ATTR);
	        		}
            		//规则存放入规则的LIST
            		attRuleList.add(br);               
    			}            	
            }
            //把对象属性的规则存放入属性个性的规则的LIST
            ba.setAttRuleList(attRuleList);
            //把对象属性存放入业务对象属性的LIST
            bODef.getBOAttributeList().add(ba);  
		}
		return bODef;
	}
	    
	
	
	/**
	 * 解析对象全局规则
	 * @param mainBODef 主业务对象(BODef)
	 * @param rules 解析对象的公共规则的原始数据（Rules）
	 * @return 
	 * BODef
	 * @exception 
	 * @since  1.0.0
	 */
	private BODef analyzeRules(BODef mainBODef,Rules rules) {
		List<BORule> bORuleList = new ArrayList<BORule>();
		if(BeanUtils.isNotEmpty(rules)){
			Rule[] ras= rules.getRuleArray();
			for (Rule ra : ras){
				BORule br = new BORule();
				br.setName(ra.getKey());            
				br.setContent(ra.getContent());
				br.setTipInfo(ra.getTipinfo());
				br.setRuleType(ra.getType().toString().toLowerCase());
				br.setBelongType(BORULE_BELONG_TYPE.DEF);
				bORuleList.add(br);
			}			
		}
		mainBODef.setBORuleList(bORuleList);
		return mainBODef;
	}
	
	
	/**
	 * 将BODef反解析为BODefXML
	 * <pre>
	 * 示例：
	 *  BODef bODef = boDefManager.loadBODef("业务对象ID");
	 *	String xml = bODefParseFactory.getBODefXML(bODef);
	 * 返回:
	 * BODef对象
	 * </pre>
	 * @param boDef    BODef对象
	 * @return String    xml的字符串
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public String getBODefXML(BODef boDef)
	{
	    String xml = "";
		if(BeanUtils.isNotEmpty(boDef)&&BeanUtils.isNotEmpty(boDef.getName())){
			
	        //创建文档 及其文档的相关设置
	        Document document = DocumentHelper.createDocument();  
			document.setXMLEncoding("UTF-8");
			Namespace tns_namespace = new Namespace("tns", TNS_XML);
	        Namespace xsi_namespace = new Namespace("xsi", XSI_XML);
			Element bodefcfg = document.addElement(new QName("bodefcfg", tns_namespace));
	        bodefcfg.add(tns_namespace);
	        bodefcfg.add(xsi_namespace);
	        bodefcfg.addAttribute(new QName("schemaLocation", xsi_namespace), SCHEMALOCATION_XML);
			
	        //对象名的拼装
			Element bdName = bodefcfg.addElement(new QName("name", tns_namespace));
			bdName.setText(boDef.getName());  
			
			//对象包名的拼装
		    Element bdCagetory = bodefcfg.addElement(new QName("cagetory", tns_namespace));
		    bdCagetory.setText(boDef.getPackage());  
		        
		    //业务对象数据类型的拼装
			Element datatype = bodefcfg.addElement(new QName("datatype", tns_namespace));
			datatype.setText(boDef.getDataType());  
		    
		    //对象公共规则的拼装
			List<BORule> bORuleList = boDef.getBORuleList();
			if(bORuleList.size()>0){
				setBORuleToXML(bORuleList,bodefcfg,tns_namespace);
			}
			
			//对象属性的拼装
			getBODefAttrToXML(boDef,bodefcfg,tns_namespace);
			//把对象变成XML字符串
		    xml = Dom4jUtil.docToString(document);
	    }else{
	    	logger.info("组装XML出错，业务对象的数据不完整！！！");
	    	return null;
	    }
		return xml;
	}
	

	/**
	 * 拼装业务对象的公共规则的XML
	 * @param bORuleList  公共规则的LIST数组（List<BORule>）
	 * @param bodefcfg  XML的根节点（Element）
	 * @param tns_namespace  标签名的空间（Namespace）
	 * @return 
	 * Element
	 * @exception 
	 * @since  1.0.0
	 */
	private Element setBORuleToXML(List<BORule> bORuleList,Element bodefcfg,Namespace tns_namespace){
		Element bdRules = bodefcfg.addElement(new QName("rules", tns_namespace));
		for (BORule boRule : bORuleList){
			Element bdRule = bdRules.addElement(new QName("rule", tns_namespace));
			bdRule.addAttribute("key", boRule.getName());
			bdRule.addAttribute("type", boRule.getRuleType());
			bdRule.addAttribute("tipinfo", boRule.getTipInfo());
			bdRule.addAttribute("content", boRule.getContent());
		}
		return bodefcfg;
	}
	

	/**
	 * 拼装业务对象属性的XML
	 * @param boDef 整个业务对象（BODef）
	 * @param bodefcfg  XML的根节点（Element）
	 * @param tns_namespace 标签名的空间（Namespace）
	 * @return 
	 * Element
	 * @exception 
	 * @since  1.0.0
	 */
	private Element getBODefAttrToXML(BODef boDef,Element bodefcfg,Namespace tns_namespace){
		if(BeanUtils.isEmpty(boDef)){
			return bodefcfg;
		}	
		//是否是主对象拼装
		Element bdBomodel = null;
		if(boDef.getIsMaster()==BODEF_IS_MASTER.MASTER_YES){
			bdBomodel= bodefcfg.addElement(new QName("mainmodel", tns_namespace));
		}else{
			bdBomodel= bodefcfg.addElement(new QName("submodel", tns_namespace));
		}
		
		//对象名称拼装
		bdBomodel.addAttribute("name", boDef.getName());

		//对象说明的拼装
		bdBomodel.addAttribute("description", boDef.getDesc());  
		
		//属性拼装
		List<BOAttribute> baList =  boDef.getBOAttributeList();
		Element bdList = null;
		for (BOAttribute ba : baList){
			//属性为list组的属性对象
			if(ba.getIsList()==BOATTRIBUTE_IS_LIST.LIST_YES){  
				//一个业务对象中用一个LIST组对象就可以了
				if(BeanUtils.isEmpty(bdList)){   
					bdList = bdBomodel.addElement(new QName("list", tns_namespace));
				}	
				getAttrHasRuleToXML(ba, bdList, tns_namespace);
			}else{
				 //依赖对象属性 （hasOne和hasMany）
				if(BOATTRIBUTE_TYPE.REF.equals(ba.getType())){          
					Element bdRef = null;
					//hasOne依赖类型
					if(BOATTRIBUTE_REF_TYPE.HAS_ONE.equals(ba.getRefType())){ 
						bdRef = bdBomodel.addElement(new QName("hasOne", tns_namespace));
					//hasMany依赖类型
					}else{  
						bdRef = bdBomodel.addElement(new QName("hasMany", tns_namespace));
					}
					bdRef.addAttribute("name", ba.getName());	
					BODef refBODef = ba.getRefBODef();
					//有依赖对象的通过递归方法，把依赖对象解析成XML
					if(BeanUtils.isNotEmpty(refBODef)&&BeanUtils.isNotEmpty(refBODef.getName())){
						bdRef.addAttribute("rel", refBODef.getName());
						getBODefAttrToXML(refBODef, bodefcfg, tns_namespace);     
					}
				//基本属性（不是依赖对象类型）
				}else{       
					getAttrHasRuleToXML(ba, bdBomodel, tns_namespace);
				}
			}
		}
		return bodefcfg;
	}
	
	

	/**
	 * 有自己个性规则的属性的XML拼装
	 * @param ba 属性（BOAttribute）
	 * @param attrEl 上一级父节点（Element）
	 * @param tns_namespace  标签名的空间（Namespace）
	 * @return 
	 * Element
	 * @exception 
	 * @since  1.0.0
	 */
	private Element getAttrHasRuleToXML(BOAttribute ba,Element attrEl,Namespace tns_namespace){
		Element bdAttribute = attrEl.addElement(new QName("attribute", tns_namespace));
		bdAttribute.addAttribute("name", ba.getName());
		bdAttribute.addAttribute("type", ba.getDataType());
		bdAttribute.addAttribute("description", ba.getDesc());
		//属性的个性规则
		List<BORule> brList= ba.getAttRuleList(); 
		if(brList.size()>0){
			Element bdRuleconfig = bdAttribute.addElement(new QName("ruleconfig", tns_namespace));
			for (BORule boRule : brList){
				Element bdRule = bdRuleconfig.addElement(new QName("rule", tns_namespace));
				//依赖规则对象的类型
				if(BORULE_BELONG_TYPE.DEF.equals(boRule.getBelongType())){     
					bdRule.addAttribute("type", BORULE_TYPE.REFERENCE);
					bdRule.addAttribute("rel", boRule.getName());
				//非依赖规则对象的类型
				}else{
					bdRule.addAttribute("type", boRule.getRuleType());
					bdRule.addAttribute("tipinfo", boRule.getTipInfo());
					bdRule.addAttribute("content", boRule.getContent());
				}
			}
		}
		return attrEl;
	}
	
}
