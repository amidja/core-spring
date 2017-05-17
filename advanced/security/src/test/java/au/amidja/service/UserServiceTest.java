package au.amidja.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.model.SystemUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-appContext.xml"})
public class UserServiceTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Autowired
	private ISystemUserReadDao systemUserReadDao;
	
	@Autowired
	IUserService userService;
	
	@Test
	public void testUserServiceGetQR() throws Exception {
		assertNotNull(userService);
		String userId = "amidja";

		LOG.debug("QRUTL [{}]", userService.generateQRUrl(userId));
		
		userService.registerFor2FA(userId);
				
		SystemUser systemUser = systemUserReadDao.findByUserName(userId);
		LOG.debug("Secret [{}]", systemUser.getSecret());						
	}

}
