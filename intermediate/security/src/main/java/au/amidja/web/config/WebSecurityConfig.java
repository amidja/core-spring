package au.amidja.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
    public WebSecurityConfig() {
        if (logger.isInfoEnabled()) {
            logger.info("WebSecurityConfig started..");
        }        
    }  
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")		
		.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/welcome")
			.failureUrl("/login?error")
			.usernameParameter("username")
	  		.passwordParameter("password")
	  	.and()
	  	.logout()
	  		.logoutSuccessUrl("/login?logout")	  	
		;
	}	
				
	@Autowired	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication().withUser("amidja").password("amidja").roles("USER", "ADMIN");
	}	
}