package au.amidja.core.spring.di.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.MessageService;

@Component
public class MessageConsumer implements Consumer {

	// @Autowired
	private MessageService messageService;

	// constructor-based dependency injection
	/*@Autowired
	public MessageConsumer(MessageService svc) {
		this.messageService = svc;
	}*/

	@Autowired
	public void setService(MessageService svc) {
		this.messageService = svc;
	}

	@Override
	public void processMessage(String msg, String rec) {
		// some magic like validation, logging etc
		this.messageService.sendMessage(msg, rec);
	}

}
