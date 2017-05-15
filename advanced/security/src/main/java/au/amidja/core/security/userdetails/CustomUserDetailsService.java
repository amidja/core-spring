package au.amidja.core.security.userdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import au.amidja.persistence.dao.ISystemUserReadDao;
import au.amidja.persistence.dao.adapter.ISystemUserAdapter;
import au.amidja.persistence.model.SystemUser;

public class CustomUserDetailsService implements UserDetailsService{
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired(required=true)
	ISystemUserAdapter adapter;
	
	@Autowired(required=true)
	private ISystemUserReadDao systemUserReadDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.debug(" .... in loadUserByUsername for [{}] ", username);		
		
		SystemUser systemUser = systemUserReadDao.findByUserName(username);
		if (systemUser != null){
			LOG.debug(" .... in loadUserByUsername.findByUserName did not find [{}] ", username);
			UserDetails userDetails = adapter.adapt(systemUser);
			return userDetails;	
		}else{
			throw new UsernameNotFoundException("userName " + username + " not found"); 		
		}		
	}
}