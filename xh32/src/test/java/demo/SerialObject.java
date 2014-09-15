package demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.serial.SerialJavaObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JSONString;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.commons.beanutils.LazyDynaMap;

public class SerialObject {
	public static String path = "D:\\workspace\\bpmx31\\src\\test\\java\\demo\\object.txt";
	public static String path1 = "	E:\\work\\work\\bpmx31\\src\\test\\java\\demo\\myPerson.txt";

	public static void savePerson() throws IOException {
		Person myPerson = new Person();
		myPerson.setAge(10);
		myPerson.setName("zhuang");

		Time time = new Time(22222);
		String s = null;
		boolean b = true;

		List list = new ArrayList();
		list.add("111");
		list.add(22222222222222d);
		list.add(s);
		list.add(b);

		String[] array = new String[2];
		array[0] = "1";
		//array[1] = "2";

		java.sql.Date date = new java.sql.Date(new Date().getTime());

		Map map = new HashMap();
		map.put("cs", myPerson);
		map.put("cs1", myPerson);
		map.put("cs2", time);
		map.put("cs3", list);
		map.put("cs4", array);
		map.put("cs5", date);
		s = null; 
		 
			List<Person> list1 = new ArrayList<Person>();
			list1.add(myPerson);
			list1.add(myPerson);
			
		 
		saveObj(map);
	}

	public static void saveObj(Object obj) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}

	public static void restorePerson() {
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Object object = (Object) ois.readObject();
			System.out.println(ObjectUtil.toObjectMap(object));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		savePerson();
		restorePerson();
	}

}
