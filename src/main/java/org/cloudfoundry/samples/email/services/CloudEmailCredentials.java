package org.cloudfoundry.samples.email.services;

import org.apache.log4j.Logger;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Profile("cloud")
public class CloudEmailCredentials implements EmailCredentials {

    private static Logger logger = Logger.getLogger(CloudEmailCredentials.class);

    @Value("${smtp.host}")
    private String host;

    private Map<String, String> credentials;

    public CloudEmailCredentials() {
        CloudEnvironment cloudEnvironment = new CloudEnvironment();
        getSendGridCredentials(cloudEnvironment.getServices());

        logger.info("host=" + host + ", credentials=" + credentials);
    }

    @SuppressWarnings("unchecked")
    private void getSendGridCredentials(List<Map<String, Object>> services) {
        for (Map<String, Object> service : services) {
            if (service.get("label").toString().contains("sendgrid")) {
                credentials = (Map<String, String>) service.get("credentials");
            }
        }
    }

    @Override
    public String getHost() {
        if (credentials.get("host") != null)
            return credentials.get("host");
        else 
            return host;
    }

    @Override
    public String getUserName() {
        return credentials.get("username");
    }

    @Override
    public String getPassword() {
        return credentials.get("password");
    }
}
