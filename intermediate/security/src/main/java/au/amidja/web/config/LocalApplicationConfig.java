package au.amidja.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
@Import({WebSecurityConfig.class})
@ImportResource("classpath:/inmemory-dbConfig.xml")
public class LocalApplicationConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalApplicationConfig.class);
			
    public LocalApplicationConfig() {
        if (logger.isInfoEnabled()) {
            logger.info("LocalAppConfig started..");
        }        
    }  
}