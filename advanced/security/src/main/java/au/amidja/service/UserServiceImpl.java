package au.amidja.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jboss.aerogear.security.otp.api.Base32;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.model.SystemUser;

@Service("userService")
public class UserServiceImpl implements IUserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	public static String APP_NAME = "MySpringTwoFactorRegistration";
	public static String QR_PREFIX =  "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";

	@Autowired(required=true)
	private ISystemUserReadDao systemUserReadDao;

	@Override
	@Transactional(readOnly=false)
	public String generateQRUrl(String userName) throws UnsupportedEncodingException {
		
		SystemUser systemUser = systemUserReadDao.findByUserName(userName);
						
		if (systemUser.getSecret() == null || systemUser.getSecret().length() <= 0){
			LOG.debug("userService about the set secret for [{}]", systemUser);
			systemUser.setSecret(Base32.random());
			systemUserReadDao.persist(systemUser);
			systemUserReadDao.flush();
		}
				
		return QR_PREFIX + URLEncoder.encode(String.format(
			      "otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, systemUser.getId(), systemUser.getSecret(), APP_NAME), "UTF-8");
	}



	@Override
	@Transactional(readOnly=false)
	public boolean registerFor2FA(String userName) {
		
		SystemUser systemUser = systemUserReadDao.findByUserName(userName);
		
		if (systemUser.getSecret() == null || systemUser.getSecret().length() <= 0){
			throw new IllegalArgumentException("User Secret cannot be empty or null");
		}
				
		systemUser.setUsing2FA(true);
		systemUserReadDao.persist(systemUser);
		systemUserReadDao.flush();		
		return true;
	}
}
