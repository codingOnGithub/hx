package demo;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyTest {
	
	public static void main(String[] args) {
		Person p=new Person();
		Binding bind=new Binding();
		p.setName("zyg");
		bind.setVariable("person", p);
		GroovyShell shell=new GroovyShell(bind);
		
		String str="Object p=person;System.out.println(p.getName());";
		
		shell.evaluate(str);
		
	}
}
