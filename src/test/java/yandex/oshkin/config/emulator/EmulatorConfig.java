package yandex.oshkin.config.emulator;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/emulator/emulator.properties")
public interface EmulatorConfig extends Config {

    String deviceName();
    String platformName();
    String platformVersion();
    String locale();
    String language();
    String appPackage();
    String appActivity();
    String hubUrl();
}
