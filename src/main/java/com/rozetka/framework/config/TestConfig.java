package com.rozetka.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:config.properties")
public interface TestConfig extends Config {
    TestConfig CONFIG = ConfigFactory.create(TestConfig.class, System.getenv(), System.getProperties());

    @Key("browser")
    String browser();

    @Key("base.url")
    String baseUrl();

    @Key("user.email")
    String userEmail();

    @Key("user.password")
    String userPassword();
}
