package au.amidja.dao.persistence.impl;

import org.hamcrest.core.IsEqual;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.model.SystemRole;
import au.amidja.persistence.model.SystemUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-appContext.xml"})
public class SystemUserReadJpaDaoTest {

	private static final Logger LOG = LoggerFactory.getLogger(SystemUserReadJpaDaoTest.class);
	
	@Autowired
	private ISystemUserReadDao systemUserReadDao;
	
	@Test
	public void testfindByUserId() {
		int userId = 1;
		
		LOG.debug("... in testfindByUserId [{}] ", Integer.valueOf(userId));		
		SystemUser systemUser = systemUserReadDao.findByUserId(Integer.valueOf(userId));
		assertNotNull(systemUser);
		assertThat(systemUser.getId(), IsEqual.equalTo(userId));
		assertNotNull(systemUser.getSystemRoles());
		assertTrue(systemUser.isUsing2FA());
		LOG.debug("System User [{}] has [{}] roles ", systemUser.getUserName(), systemUser.getSystemRoles().size());
		for (SystemRole role: systemUser.getSystemRoles()){
			LOG.debug("System User [{}] has role [{}]", systemUser.getId(), role.getRole());
		}
	}
	
}