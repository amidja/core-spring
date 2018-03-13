package au.amidja.core.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("default")
@Configuration
public class SpringAppConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringAppConfig.class);

	public SpringAppConfig() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("\t SpringAppConfig: started ....");
		}
	}

}
