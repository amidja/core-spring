package au.amidja.core.java.di.consumer.injector;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.EmailServiceImpl;
import au.amidja.core.java.di.consumer.MessageConsumer;

public class EmailServiceInjector implements MessageServiceInjector{
	
	@Override
	public Consumer getConsumer() {
		return new MessageConsumer(new EmailServiceImpl());
	}
}
