package org.cloudfoundry.samples.email.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("default")
public class PropertiesEmailCredentials implements EmailCredentials {
    private static Logger logger = Logger.getLogger(PropertiesEmailCredentials.class);

    @Value("${smtp.host:}")
    protected String host;

    @Value("${smtp.user:}")
    protected String user;

    @Value("${smtp.password:}")
    protected String password;

    public PropertiesEmailCredentials() {
        logger.info("host=" + host + ", user=" + user + ", password=" + password);
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getUserName() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
