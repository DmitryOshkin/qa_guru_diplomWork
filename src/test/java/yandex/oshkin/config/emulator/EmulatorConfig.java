package yandex.oshkin.config.emulator;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/emulator/emulator.properties")
public interface EmulatorConfig extends Config {

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("locale")
    String locale();

    @Key("language")
    String language();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("hubUrl")
    String hubUrl();
}
