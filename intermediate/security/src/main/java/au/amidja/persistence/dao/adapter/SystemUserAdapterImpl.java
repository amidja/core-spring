package au.amidja.persistence.dao.adapter;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import au.amidja.persistence.model.SystemRole;
import au.amidja.persistence.model.SystemUser;

@Component("systemUserAdapter")
public class SystemUserAdapterImpl implements SystemUserAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SystemUserAdapterImpl.class);

	@Override
	public UserDetails adapt(SystemUser source) {			
		if (source == null){
			LOG.debug("... adapting systemUser returning [null]");
			return null;
		}
		
		LOG.debug("... adapting systemUser with userName= [{}]", source.getUserName());		
		UserDetails userDetails = new User(source.getUserName(), source.getPassword(), true, true, true, true, getRoles(source));
		return userDetails;		
	}
	
	private List<GrantedAuthority> getRoles(SystemUser source){
		
		GrantedAuthority[] roles = new GrantedAuthority[source.getSystemRoles().size()]; 
		
		int index = 0; 
		for (SystemRole systemRole : source.getSystemRoles()){
			roles[index++] = new SimpleGrantedAuthority(systemRole.getRole());			
		}
		return Arrays.asList(roles);		
	}
}