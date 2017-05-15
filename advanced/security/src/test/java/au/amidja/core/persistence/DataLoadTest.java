package au.amidja.core.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-appContext.xml"})
public class DataLoadTest {
	
	Logger logger = LoggerFactory.getLogger(DataLoadTest.class);
		
	@Test
	public void testLoadData() {		
		logger.debug("... loading db data");		
	}	
}