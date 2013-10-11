package org.cloudfoundry.samples.email.services;

import org.apache.log4j.Logger;
import org.cloudfoundry.samples.email.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class SMTPEmailService implements EmailService {

    private static Logger logger = Logger.getLogger("services");

    @Autowired
    private MailSender mailSender;

    @Override
    public Status send(SimpleMailMessage msg) {
        logger.info("Sending... " + msg + " " + mailSender);
        try {
            mailSender.send(msg);
        } catch (Exception e) {
            logger.error(e);
            return new Status(true, e.getMessage());
        }
        return new Status();
    }
}

