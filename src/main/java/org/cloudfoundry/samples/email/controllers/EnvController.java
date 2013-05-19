package org.cloudfoundry.samples.email.controllers;

import org.cloudfoundry.samples.email.services.EmailCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class EnvController {

    private EmailCredentials credentials;

    @Autowired
    public EnvController(EmailCredentials credentials) {
        this.credentials = credentials;
    }

    @RequestMapping(value = "/env")
    @ResponseBody
    public Map<String, String> showEnvironment() {
        return System.getenv();
    }

    @ResponseBody
    @RequestMapping(value = "/creds")
    public EmailCredentials showCredentials() {
        return credentials;
    }
}
