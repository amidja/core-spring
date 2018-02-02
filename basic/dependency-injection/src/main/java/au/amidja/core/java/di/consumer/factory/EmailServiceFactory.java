package au.amidja.core.java.di.consumer.factory;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.EmailServiceImpl;
import au.amidja.core.java.di.consumer.MessageConsumer;

public class EmailServiceFactory implements MessageServiceFactory{
	
	@Override
	public Consumer getConsumer() {
		return new MessageConsumer(new EmailServiceImpl());
	}
}
