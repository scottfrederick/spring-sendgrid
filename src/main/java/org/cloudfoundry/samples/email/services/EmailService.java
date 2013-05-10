package org.cloudfoundry.samples.email.services;

import org.cloudfoundry.samples.email.domain.EmailMessage;
import org.cloudfoundry.samples.email.domain.Status;

public interface EmailService {
    Status send(EmailMessage emailMessage);
}
