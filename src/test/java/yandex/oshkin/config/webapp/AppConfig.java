package yandex.oshkin.config.webapp;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/webapp/app.properties")
public interface AppConfig extends Config {

    String webUrl();

    String apiUrl();
    String userLogin();
    String userPassword();



}
