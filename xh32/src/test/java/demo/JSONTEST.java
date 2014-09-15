package demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import org.junit.Test;

public class JSONTEST {

	/**
	 * setIgnoreDefaultExcludes
	 *设置为true输出如下：
	 *{"name":"json","class":"ddd"}
	 *设置为false输出{"name":"json"}
	 */
	@org.junit.Test
	public  void testMap() {  
        Map map = new HashMap();  
        map.put("name", "json");  
        map.put("class", "ddd");  
        JsonConfig config = new JsonConfig();  
        config.setIgnoreDefaultExcludes(true);  //默认为false，即过滤默认的key  
        JSONObject jsonObject = JSONObject.fromObject(map,config);  
        System.out.println(jsonObject);  
		          
	}  
	
	/**
	 * 设置需要排除的属性
	 * setExcludes
	 */
	@org.junit.Test
	public  void testExcludeProperites() {  
	       String str = "{'string':'JSON', 'integer': 1, 'double': 0, 'boolean': true}";  
	       JsonConfig jsonConfig = new JsonConfig();  
	       jsonConfig.setExcludes(new String[] { "double", "boolean" });  
	       JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(str, jsonConfig);  
	       System.out.println(jsonObject.toString());  
	       
	}  
	
	/**
	 * setCycleDetectionStrategy 防止自包含
	 */
	@org.junit.Test
	public  void testCycleObject() {  
		JSONTEST obj=new JSONTEST();
       CycleObject object = obj.new CycleObject(); 
       object.setMemberId("yajuntest");  
       object.setSex("male");  
       JsonConfig jsonConfig = new JsonConfig();  
       jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
  
       JSONObject json = JSONObject.fromObject(object, jsonConfig);  
       System.out.println("testCycleObject:" +json);  
	}  
		  
	
	public class CycleObject {  
	    private String      memberId;  
	    private String      sex;  
	    private CycleObject me = this;
	    
		public String getMemberId() {
			return memberId;
		}
		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public CycleObject getMe() {
			return me;
		}
		public void setMe(CycleObject me) {
			this.me = me;
		}  
	    
	    
		  
	}  
	
	@Test
	public void testJSONFILTER(){
		 JsonConfig  jsonConfig =new JsonConfig() ;  
          jsonConfig.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {  
             @Override  
             public Object processObjectValue(String key, Object value, JsonConfig arg2) {  
                 return new SimpleDateFormat("yyyy-MM-dd").format(value);  
             }  
             @Override  
             public Object processArrayValue(Object value, JsonConfig arg1) {  
                 return value;  
             }  
         });  
          /** filter  filedName  workDate*/  
         jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
        	 @Override  
             public boolean apply(Object source, String name, Object value) {  
            	 if("workDate".equals(name)){  
                     return true ;  
                 }  
                 return false;  
             }  
         } );  
         Teacher obj=new Teacher();
         obj.setBirthday(new Date(1986, 10, 10));
         obj.setAge(21);
         obj.setName("ray");
         obj.setSex(true);
         obj.setWorkDate(new Date());
          
        System.out.println(JSONObject.fromObject(obj, jsonConfig).toString());  

	}
	
	@Test
	public void testProperJson(){
		String str="{name:\"zyg\",property:{id:1,type:2}}";
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor("property",new JsonValueProcessor() {
			@Override
			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				return "\"" + value +"\"";
			}

			@Override
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}
		} );
		JSONObject jsonObj=JSONObject.fromObject(str,config);
		PlantObject obj=(PlantObject)JSONObject.toBean(jsonObj,  PlantObject.class);
		System.out.println("object:"+obj.getProperty());
	
	}
	
	@Test
	public void testJsonToObj(){
		String str="{name:\"ray\",property:{type:1}}";
		
		JsonConfig config=new JsonConfig();
		config.registerJsonValueProcessor("property",new JsonValueProcessor() {
			@Override
			public Object processObjectValue(String key, Object value, JsonConfig arg2) {
				return "\"" + value +"\"";
			}
			@Override
			public Object processArrayValue(Object arg0, JsonConfig arg1) {
				return null;
			}
		} );
		
		JSONObject jsonObj=JSONObject.fromObject(str,config);
		PlantObject obj=(PlantObject)JSONObject.toBean(jsonObj,  PlantObject.class);
		System.out.println("object:"+obj.getProperty());
	
	}
		 
	
	@Test
	public void testObjectJson(){
		PlantObject obj=new PlantObject();
		obj.setName("ray");
		obj.setProperty("属性");
		JSONObject jsonObj=JSONObject.fromObject(obj);
		System.out.println(jsonObj.toString());
	}
	
	@Test
	public void testObjectJsonList(){
		List<PlantObject> list=new ArrayList<PlantObject>();
		
		PlantObject obj=new PlantObject();
		obj.setName("ray");
		obj.setProperty("属性");
		
		PlantObject obj1=new PlantObject();
		obj1.setName("ray1");
		obj1.setProperty("属性1");
		
		list.add(obj1);
		list.add(obj);
		
		JSONArray jsonObj=JSONArray.fromObject(list);
		System.out.println(jsonObj.toString());
	}
	
	
	
	@Test
	public void testJsonToObjDate(){
		String str="{name:\"zyg\",createDate:\"2013-09-01 11:23:34\"}";
		JSONObject json = JSONObject.fromObject(str);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss"}));
		
		PlainObj obj=(PlainObj)JSONObject.toBean(json,PlainObj.class);
		System.out.println(obj.getCreateDate());
		
		 
	}



}
