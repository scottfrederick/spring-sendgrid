package org.cloudfoundry.samples.email.controllers;

import org.cloudfoundry.samples.email.domain.EmailMessage;
import org.cloudfoundry.samples.email.domain.Status;
import org.cloudfoundry.samples.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @RequestMapping
    public String form(@ModelAttribute EmailMessage emailMessage) {
        return "email";
    }

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(EmailMessage emailMessage, Model model) {
        Status status = emailService.send(emailMessage);

        model.addAttribute(status);

        return "email";
    }
}