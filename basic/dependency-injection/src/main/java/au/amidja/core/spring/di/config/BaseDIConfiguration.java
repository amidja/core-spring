package au.amidja.core.spring.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import au.amidja.core.di.service.EmailServiceImpl;
import au.amidja.core.di.service.MessageService;
import au.amidja.core.spring.di.consumer.MessageConsumer;

@Configuration
public class BaseDIConfiguration {
		
	@Bean
	public MessageService getMessageService(){
		return new EmailServiceImpl();
	}
	
	@Bean
	public MessageConsumer getMessageConsumer(){
		return new MessageConsumer();
	}
			
	
}