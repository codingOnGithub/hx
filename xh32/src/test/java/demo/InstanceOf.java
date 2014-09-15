package demo;

public class InstanceOf {
	public static void main(String[] args) throws ClassNotFoundException {
		Boy boy=new Boy();
		Class cls=Class.forName("demo.Boy");
		if(boy.getClass()==cls){
			System.out.println("ok");
		}
		System.out.println(Boy.class.getSimpleName());
	}
}
