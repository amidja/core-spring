
package au.amidja.core.spring.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import au.amidja.core.di.service.EmailServiceImpl;
import au.amidja.core.di.service.MessageService;
//import au.amidja.core.spring.di.consumer.MessageConsumer;

@Configuration
@ComponentScan(value={"au.amidja.core.spring.di.consumer"})
public class BaseDIConfiguration {
	

	@Bean
	//Used by MessageConsumer
	public MessageService getMessageService(){
		return new EmailServiceImpl();
	}
	
	/*@Bean
	public MessageConsumer getMessageConsumer(){
		return new MessageConsumer();
		
	}*/
				
}