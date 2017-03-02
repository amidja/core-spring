package au.amidja.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Profile("local")
@Configuration
@Import({MvcConfig.class})
public class LocalApplicationConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalApplicationConfig.class);
			
    public LocalApplicationConfig() {
        if (logger.isInfoEnabled()) {
            logger.info("LocalAppConfig started..");
        }        
    }  
}
