package demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Demo {
	
	
	private static Object cloneObject(Object obj) throws Exception{
		 ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		 ObjectOutputStream out = new ObjectOutputStream(byteOut);
		 out.writeObject(obj); 

		 ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
		 ObjectInputStream in =new ObjectInputStream(byteIn);

		 return in.readObject();
	}


	
	
	public static void main(String[] args) throws Exception{
		long l=System.currentTimeMillis();
		for(int i=0;i<1000000;i++){
			Admin admin=new Admin();
			admin.setName("abc");
			admin.setSex("男");
			Boy boy=new Boy("zyg",20);
			boy.addAdmin(admin);
			List<Admin> list=boy.getAdmin();
			
			
			Boy boy2=(Boy)cloneObject(boy);
			
//			for(Admin ad :list){
//				ad.setName(ad.getName() +"k");
//			}
//		
//			boy2.setAge(40);
			
			
//			for(Admin ad:boy2.getAdmin()){
//				System.out.println(ad.getName());
//			}
		}
		System.out.println(System.currentTimeMillis()-l);
		
	}
	
	

}
