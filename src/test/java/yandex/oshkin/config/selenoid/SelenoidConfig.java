package yandex.oshkin.config.selenoid;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/selenoid/credentials.properties")
public interface SelenoidConfig extends Config {

    String user();
    String password();
    String remoteUrl();
    String videoAttachUrl();
    String browser();
    String browserVersion();
    String browserSize();
}
