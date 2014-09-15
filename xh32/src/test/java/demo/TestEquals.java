package demo;

public class TestEquals {
	
	public static void main(String[] args) {
		Short i=1;
		int ii=1;
		long lo=1L;
		Integer io=new Integer(1);
		System.out.println(ii==io);
		System.out.println(i==lo);
		System.out.println(i==ii);
		System.out.println(i.equals(ii));
		System.out.println(i.equals((short)ii));
	}

}
