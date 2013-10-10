package org.cloudfoundry.samples.email.config.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "org.cloudfoundry.samples.email.services" })
public class AppConfig {

}
