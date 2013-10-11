package org.cloudfoundry.samples.email.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:/application.properties")
@Profile("!cloud")
public class AppLocalConfig {

    @Autowired Environment environment;

    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(environment.getProperty("smtp.host"));
        mailSenderImpl.setPort(587);
        mailSenderImpl.setUsername(environment.getProperty("smtp.user"));
        mailSenderImpl.setPassword(environment.getProperty("smtp.password"));

        return mailSenderImpl;
    }
}