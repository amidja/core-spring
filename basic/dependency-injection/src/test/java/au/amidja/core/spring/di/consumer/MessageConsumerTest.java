package au.amidja.core.spring.di.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testAppContext.xml")
public class MessageConsumerTest {

	@Autowired
	MessageConsumer messageConsumer;
	
	@Test
	public void test(){
		messageConsumer.processMessage("Hi Pankaj", "pankaj@abc.com");
	}	
}
