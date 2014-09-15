package demo;

import java.util.Date;

import org.junit.Test;

import com.hotent.core.util.BeanUtils;
import com.rits.cloning.Cloner;

public class BeanUtilsTest {
	
	@Test
	public void testCopyProperties(){
		Boy boy=new Boy();
		boy.setAge(1);
		boy.setName("ray");
		boy.setBirthDay(new Date(2003, 11, 13));
		
		Boy boy2=BeanUtils.copyProperties(Boy.class, boy);
		System.out.println(boy2.getAge());
		
		Cloner clone=new Cloner();
		Boy boy3=clone.deepClone(boy);
		System.out.println(boy3.getName());
	}

}
