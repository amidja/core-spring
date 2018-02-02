package au.amidja.core.java.di.consumer.factory;

import au.amidja.core.di.consumer.Consumer;

public class DemoApp {
	
	public static void main(String[] args) {
		String msg = "Hi Bobo";
		String email = "boboj@abc.com";
		String phone = "4088888888";
		
		MessageServiceFactory injector = null;
		Consumer app = null;
		
		//Send email
		injector = new EmailServiceFactory();
		app = injector.getConsumer();
		app.processMessage(msg, email);
		
		//Send SMS
		injector = new SMSServiceFactory();
		app = injector.getConsumer();
		app.processMessage(msg, phone);
	}

}
