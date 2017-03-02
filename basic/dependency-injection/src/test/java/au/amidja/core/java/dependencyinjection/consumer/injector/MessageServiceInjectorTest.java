package au.amidja.core.java.dependencyinjection.consumer.injector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.amidja.core.di.consumer.Consumer;
import au.amidja.core.di.service.MessageService;
import au.amidja.core.java.di.consumer.MessageConsumer;
import au.amidja.core.java.di.consumer.injector.MessageServiceInjector;

public class MessageServiceInjectorTest {

	private MessageServiceInjector injector;

	@Before
	public void setUp() {
		// mock the injector with an anonymous class
		injector = new MessageServiceInjector() {

			@Override
			public Consumer getConsumer() {
				// mock the message service
				return new MessageConsumer(new MessageService() {
					@Override
					public void sendMessage(String msg, String rec) {
						System.out.println("Mock Message Service implementation");					
					}
				});
			}
		};
	}

	@Test
	public void test() {
		Consumer consumer = injector.getConsumer();
		consumer.processMessage("Hi Pankaj", "pankaj@abc.com");
	}

	@After
	public void tear() {
		injector = null;
	}

}
