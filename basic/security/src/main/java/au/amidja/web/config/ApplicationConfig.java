package au.amidja.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile("default")
@Configuration
@Import({MvcConfig.class})
public class ApplicationConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
			
    public ApplicationConfig() {
        if (logger.isInfoEnabled()) {
            logger.info("AppConfig started..");
        }        
    }  
}
