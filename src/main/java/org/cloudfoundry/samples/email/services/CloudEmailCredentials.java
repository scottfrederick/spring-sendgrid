package org.cloudfoundry.samples.email.services;

import org.apache.log4j.Logger;
import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Component
@Profile("cloud")
public class CloudEmailCredentials extends PropertiesEmailCredentials {

    private static Logger logger = Logger.getLogger(CloudEmailCredentials.class);

    private Map<String, String> credentials;

    public CloudEmailCredentials() {
        CloudEnvironment cloudEnvironment = new CloudEnvironment();
        getSendGridCredentials(cloudEnvironment.getServices());

        logger.info("credentials=" + credentials);
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
        if (!StringUtils.isEmpty(host)) {
            return host;
        } else {
            return credentialsOrEmptyString("host");
        }
    }

    @Override
    public String getUserName() {
        if (!StringUtils.isEmpty(user)) {
            return user;
        } else {
            return credentialsOrEmptyString("username");
        }
    }

    @Override
    public String getPassword() {
        if (!StringUtils.isEmpty(password)) {
            return password;
        } else {
            return credentialsOrEmptyString("password");
        }
    }

    private String credentialsOrEmptyString(String key) {
        String value = null;

        if (credentials != null) {
            value = credentials.get(key);
        }

        return value == null ? "" : value;
    }
}
