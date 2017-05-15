package au.amidja.persistence.dao.adapter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.amidja.dao.persistence.impl.SystemUserReadJpaDaoTest;
import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.model.SystemUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-appContext.xml"})
public class SystemUserAdapterTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(SystemUserReadJpaDaoTest.class);

	@Autowired
	ISystemUserAdapter adapter;
	
	@Autowired
	private ISystemUserReadDao systemUserReadDao;
	
	@Test
	public void testAdapt(){		
		String userName = "amidja";
		LOG.debug("... in testfindByUserId [{}] ", userName);		
		SystemUser systemUser = systemUserReadDao.findByUserName(userName);
		
		UserDetails userDetails = adapter.adapt(systemUser);
		Assert.assertNotNull(userDetails);
		Assert.assertNotNull(userDetails.getAuthorities());
	}
}	