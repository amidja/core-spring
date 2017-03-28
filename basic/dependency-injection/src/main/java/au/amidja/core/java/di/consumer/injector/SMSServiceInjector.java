package au.amidja.core.java.di.consumer.injector;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.SMSServiceImpl;
import au.amidja.core.java.di.consumer.MessageConsumer;

public class SMSServiceInjector implements MessageServiceInjector{
	
	@Override
	public Consumer getConsumer() {
		return new MessageConsumer(new SMSServiceImpl());
	}
}