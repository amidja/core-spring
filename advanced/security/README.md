Spring Security
================================

Based From:
	http://www.mkyong.com/spring-security/spring-security-hello-world-example/
	http://www.mkyong.com/spring-security/spring-security-form-login-example/

	Run: mvn clean install tomcat7:run

	URLs:		
		http://localhost:8080/advanced-security/
		http://localhost:8080/advanced-security/echo
		http://localhost:8080/advanced-security/welcome

Spring Security References:
 
	http://docs.spring.io/spring-security/site/docs/3.2.9.RELEASE/reference/htmlsingle/
	http://docs.spring.io/spring-security/site/docs/3.2.9.RELEASE/guides

	Migration from Spring Security 3 to Spring Security 4:
		http://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-xml.html#m3to4-xmlnamespace-defaults
		
	
	Notes:
	======	 	
		The application is using InsufficientAuthenticationException on successful user_name password authentication to trigger step-up authentication.
		
	In order to support this functionality following customization were made to the standard spring security authentication:
	1) Add new custom authentication parameter, code , to the login form.
		1.1)  Add new custom authenticationDetailsSource that is extracting this parameter from the form.  
	2)	Add new customAuthenticationProvider which on successful password authentication  gives authenticated user the GUEST role. 
	3) Created new customAccessDeniedHandler that redirects authenticate users with GUEST role to the login form, 
		3.1) Update login form to ask for step-up credential if required
	4) Update login form only to ask for verification on step-up authentication.
	5) Created new verificationCodeAuthenticationProvider that takes VerificationCodeAuthenticationToken and authenticates user.   
	

Requirements: 	
	<> Guest account can register for two factor authentication.
	<> User accounts registered for two factor authentication can access admin page. 
	