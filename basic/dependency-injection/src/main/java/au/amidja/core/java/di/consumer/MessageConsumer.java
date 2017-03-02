package au.amidja.core.java.di.consumer;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.MessageService;

public class MessageConsumer implements Consumer{
	
	private MessageService service;
	
	public MessageConsumer(MessageService svc){
		this.service=svc;
	}
	
	@Override
	public void processMessage(String msg, String rec){
		//do some msg validation, manipulation logic etc
		this.service.sendMessage(msg, rec);
	}
}
