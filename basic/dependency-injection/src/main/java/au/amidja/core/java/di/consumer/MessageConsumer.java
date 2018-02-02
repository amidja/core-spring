package au.amidja.core.java.di.consumer;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.MessageService;

/* This class is used to inject the dependencies in the app class   */
public class MessageConsumer implements Consumer{
	
	private MessageService service;
	
	// Constructor is used to inject the dependency
	public MessageConsumer(MessageService svc){
		this.service=svc;
	}
	
	@Override
	public void processMessage(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.service.sendMessage(msg, rec);
	}
}
