package au.amidja.core.spring.di.consumer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import au.amidja.core.spring.di.config.BaseDIConfiguration;

public class DemoApp {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BaseDIConfiguration.class);
		
		MessageConsumer consumer = context.getBean(MessageConsumer.class);
						
		consumer.processMessage("Hi Bobo", "Bobo@abc.com");
		
		//close the context
		context.close();
	}
}