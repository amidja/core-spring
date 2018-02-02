package au.amidja.core.java.di.consumer.factory;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.SMSServiceImpl;
import au.amidja.core.java.di.consumer.MessageConsumer;

public class SMSServiceFactory implements MessageServiceFactory{
	
	@Override
	public Consumer getConsumer() {
		return new MessageConsumer(new SMSServiceImpl());
	}
}