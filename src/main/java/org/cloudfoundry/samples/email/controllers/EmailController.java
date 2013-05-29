package org.cloudfoundry.samples.email.controllers;

import org.apache.log4j.Logger;
import org.cloudfoundry.samples.email.domain.EmailMessage;
import org.cloudfoundry.samples.email.domain.Status;
import org.cloudfoundry.samples.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/email")
public class EmailController {
    private static Logger logger = Logger.getLogger("services");

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Status send(@RequestBody EmailMessage emailMessage) {
        logger.info("Sending e-mail message [" + emailMessage + "]");

        return emailService.send(emailMessage);
    }
}