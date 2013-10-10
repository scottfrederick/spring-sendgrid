package org.cloudfoundry.samples.email.services;

import org.cloudfoundry.samples.email.domain.Status;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    Status send(SimpleMailMessage emailMessage);
}
