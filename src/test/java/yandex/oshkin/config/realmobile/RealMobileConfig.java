package yandex.oshkin.config.realmobile;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/realmobile/realmobile.properties")
public interface RealMobileConfig extends Config {

    String deviceName();
    String platformName();
    String platformVersion();
    String locale();
    String language();
    String appPackage();
    String appActivity();
    String hubUrl();
}
