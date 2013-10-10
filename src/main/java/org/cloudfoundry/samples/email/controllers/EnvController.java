package org.cloudfoundry.samples.email.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EnvController {

    @RequestMapping(value = "/env")
    @ResponseBody
    public Map<String, String> showEnvironment() {
        return System.getenv();
    }
}
