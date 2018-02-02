Spring Security Basics 
================================

Based From:
	http://www.mkyong.com/spring-security/spring-security-hello-world-example/
	http://www.mkyong.com/spring-security/spring-security-form-login-example/


Run: mvn clean install tomcat7:run
	> http://localhost:8080/basic-security/

URLs: 

	http://localhost:8080/basic-security/echo -> call to EchoController 
	http://localhost:8080/basic-security/	--> goes to the welcome page 
	http://localhost:8080/basic-security/welcome --> triggers authentication which uses custom login form


Spring Core Security
================================
	
	http://www.logicbig.com/tutorials/spring-framework/spring-security/custom-authentication-provider/
	


Spring Security References:
---------------------------------------------------------- 
	http://docs.spring.io/spring-security/site/docs/3.2.9.RELEASE/reference/htmlsingle/
	http://docs.spring.io/spring-security/site/docs/3.2.9.RELEASE/guides

	https://docs.spring.io/spring-security/site/docs/4.2.5.BUILD-SNAPSHOT/reference/htmlsingle/
	https://docs.spring.io/spring-security/site/docs/4.2.5.BUILD-SNAPSHOT/guides

Migration from Spring Security 3 to Spring Security 4:
	http://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-xml.html#m3to4-xmlnamespace-defaults

	