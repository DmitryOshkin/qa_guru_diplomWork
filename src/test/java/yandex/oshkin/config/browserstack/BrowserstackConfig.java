package yandex.oshkin.config.browserstack;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstaсk/browserstack.properties")
public interface BrowserstackConfig extends Config {

    String browserstackUser();
    String browserstackKey();
    String browserstackSessionId();
    String browserstackURL();
    String browserstackDeviceVersion();
    String browserstackDeviceName();
    String browserstackVideoAttachURL();
}
