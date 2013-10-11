package org.cloudfoundry.samples.email.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.cloudfoundry.samples.email.domain.Status;
import org.cloudfoundry.samples.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/email", headers = "Accept=application/json")
public class EmailController {
    private static Logger logger = Logger.getLogger("controller");

    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Status send(@RequestBody SimpleMailMessageDto emailMessageDto) {
        SimpleMailMessage emailMessage = new SimpleMailMessage();
        emailMessage.setFrom(emailMessageDto.from);
        emailMessage.setTo(listToArray(emailMessageDto.to));
        emailMessage.setCc(listToArray(emailMessageDto.cc));
        emailMessage.setBcc(listToArray(emailMessageDto.bcc));

        emailMessage.setSubject(emailMessageDto.subject);
        emailMessage.setText(emailMessageDto.text);

        logger.info("Sending e-mail message [" + emailMessage + "]");

        return emailService.send(emailMessage);
    }

    private String[] listToArray(List<String> list) {
        String[] array = new String[list.size()];

        int index = 0;
        for (String elem : list) {
            if (elem != null && !elem.isEmpty()) {
                array[index++] = elem;
            }
        }

        if (index == 0) {
            return null;
        } else {
            return array;
        }
    }

    public static class SimpleMailMessageDto {
        private String from;
        private List<String> to;
        private List<String> cc;
        private List<String> bcc;

        private String subject;
        private String text;

        public SimpleMailMessageDto() {

        }

        public SimpleMailMessageDto(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String text) {
            this.from = from;
            this.to = to;
            this.cc = cc;
            this.bcc = bcc;
            this.subject = subject;
            this.text = text;
        }

        public String getFrom() {
            return from;
        }

        public List<String> getTo() {
            return to;
        }

        public List<String> getCc() {
            return cc;
        }

        public List<String> getBcc() {
            return bcc;
        }

        public String getSubject() {
            return subject;
        }

        public String getText() {
            return text;
        }
    }
}