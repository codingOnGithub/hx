package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadLocalTest {
	
	
	private ThreadLocal<Boy> boyLocal=new ThreadLocal<Boy>();
	private ThreadLocal<Map<String,Object>> mapLocal=new ThreadLocal<Map<String,Object>>();
	
	@org.junit.Test
	public void testThreadLocal(){
	
		Boy boy=new Boy();
		boy.setName("ray");
		boy.setAge(1);
		boyLocal.set(boy);
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("boy", boyLocal.get());
		
		Boy boy1=new Boy();
		boy1.setName("boy");
		boy1.setAge(1);
		boyLocal.set(boy1);
		
		Boy boy3=(Boy)map.get("boy");
		
		
		System.out.println(boy3.getName() +"," + boyLocal.get().getName());
		
	}

}
