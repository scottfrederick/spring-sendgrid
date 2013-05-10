package org.cloudfoundry.samples.email.services;

public interface EmailCredentials {
    public String getHost();

    public String getUserName();

    public String getPassword();
}
