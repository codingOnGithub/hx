package demo;

import java.io.IOException;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class StartWebService {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		System.out.println("Starting Server");
//		HelloWorldImpl implementor = new HelloWorldImpl();
//		String address = "http://192.168.1.66:9000/helloWorld";
//		Endpoint.publish(address, implementor);
		
		HelloWorldImpl implementor = new HelloWorldImpl();
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean();
		svrFactory.setServiceClass(HelloWorld.class);
		svrFactory.setAddress("http://192.168.1.66:9000/helloWorld");
		svrFactory.setServiceBean(implementor);
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
		svrFactory.create();

		System.in.read();
		System.out.println("ok");

	}

}
