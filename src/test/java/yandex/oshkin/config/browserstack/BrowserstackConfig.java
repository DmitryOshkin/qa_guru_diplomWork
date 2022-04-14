package yandex.oshkin.config.browserstack;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstaсk/browserstack.properties")
public interface BrowserstackConfig extends Config {

    @Key("browserstackUser")
    String browserstackUser();

    @Key("browserstackKey")
    String browserstackKey();

    @Key("browserstackSessionId")
    String browserstackSessionId();

    @Key("browserstackURL")
    String browserstackURL();

    @Key("browserstackDeviceVersion")
    String browserstackDeviceVersion();

    @Key("browserstackDeviceName")
    String browserstackDeviceName();

}
