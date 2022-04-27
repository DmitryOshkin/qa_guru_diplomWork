package yandex.oshkin.config.selenoid;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/selenoid/credentials.properties")
public interface SelenoidConfig extends Config {

    String user();
    String password();
    String remoteUrl();
    String videoAttachUrl();
    @DefaultValue("chrome")
    String browser();
    @DefaultValue("100.0")
    String browserVersion();
    @DefaultValue("1024x980")
    String browserSize();
}
