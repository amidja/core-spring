package au.amidja.core.spring.di.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientXmlApp {
		
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("au/amidja/core/spring/di/config/applicationContext.xml");
		
		MessageConsumer consumer = context.getBean(MessageConsumer.class);
						
		consumer.processMessage("Hi Bobo", "Bobo@abc.com");
		
		//close the context
		context.close();
	}
}