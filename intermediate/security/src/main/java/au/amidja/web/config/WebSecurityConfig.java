package au.amidja.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import au.amidja.core.security.CustomAccessDeniedHandler;
import au.amidja.core.security.CustomAuthenticationProvider;
import au.amidja.core.security.CustomWebAuthenticationDetailsSource;
import au.amidja.core.security.VerificationCodeAuthenticationProvider;
import au.amidja.core.security.filter.VerificationCodeAuthenticationFilter;
import au.amidja.core.security.userdetails.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	private static final String DEFAULT_SUCCESS_URL = "/admin"; 
	
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
				.defaultSuccessUrl(DEFAULT_SUCCESS_URL)
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
				.failureHandler(authFailureHandler())
				.authenticationDetailsSource(getAuthenticationDetailsSource())	  		
	  	.and()
	  		.logout()
	  			.logoutSuccessUrl("/login?logout")
	  	.and()
	  		.addFilterBefore(verificationCodeAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)	  	
	  		.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
		;
	}		
				
	@Autowired	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("amidja").password("amidja").roles("USER", "ADMIN");		
		auth.authenticationProvider(userNamePasswordAuthProvider())
			.authenticationProvider(verificationCodeAuthProvider())
		
		;		
	}	
		
	@Bean
	CustomUserDetailsService getCustomUserDetailsService(){
		return new CustomUserDetailsService();		
	}		
	
	@Bean
	CustomWebAuthenticationDetailsSource getAuthenticationDetailsSource(){
		return new CustomWebAuthenticationDetailsSource();
	}
	
	
	@Bean
	AuthenticationProvider userNamePasswordAuthProvider() {
	    CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
	    authProvider.setUserDetailsService(getCustomUserDetailsService());
	    return authProvider;
	}
	
	@Bean
	AuthenticationProvider verificationCodeAuthProvider() {
		VerificationCodeAuthenticationProvider authProvider = new VerificationCodeAuthenticationProvider();	    
	    return authProvider;
	}
		
	@Bean 
	AuthenticationFailureHandler authFailureHandler(){
		SimpleUrlAuthenticationFailureHandler authFailureHandler = new SimpleUrlAuthenticationFailureHandler();
		authFailureHandler.setDefaultFailureUrl("/login?error=true");
		authFailureHandler.setUseForward(true);
		return authFailureHandler;
	}	
	
	@Bean 
	AccessDeniedHandler accessDeniedHandler(){
		CustomAccessDeniedHandler custAccessDeniedHandler = new CustomAccessDeniedHandler();
		custAccessDeniedHandler.setStepUpPage("/login?error=true");
		return custAccessDeniedHandler;
	}
	
	@Bean
	UsernamePasswordAuthenticationFilter verificationCodeAuthenticationFilter() throws Exception{
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(DEFAULT_SUCCESS_URL);
		successHandler.setAlwaysUseDefaultTargetUrl(true);
						
		UsernamePasswordAuthenticationFilter customAuthFilter = new VerificationCodeAuthenticationFilter();
		customAuthFilter.setAuthenticationManager(this.authenticationManager());
		customAuthFilter.setAuthenticationDetailsSource(getAuthenticationDetailsSource());
		customAuthFilter.setAuthenticationSuccessHandler(successHandler);
		return customAuthFilter;
	}
}