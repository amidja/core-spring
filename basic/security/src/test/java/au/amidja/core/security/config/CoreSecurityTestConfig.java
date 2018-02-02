package au.amidja.core.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import au.amidja.core.security.CustomAuthenticationProvider;

@Configuration
@ComponentScan("au.amidja.core.security")
public class CoreSecurityTestConfig {

	@Autowired
	private CustomAuthenticationProvider authProvider;
	
}
