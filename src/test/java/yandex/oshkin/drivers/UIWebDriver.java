package yandex.oshkin.drivers;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;
import yandex.oshkin.config.selenoid.Selenoid;
import yandex.oshkin.config.webapp.App;
import yandex.oshkin.helpers.Attach;

import static java.lang.String.format;
import static yandex.oshkin.helpers.CustomAllureListener.withCustomTemplates;

public class UIWebDriver {

    public static void createDriver() {
        String remoteUrl = System.getProperty("remoteUrl", Selenoid.config.remoteUrl());
        String user = System.getProperty("user", Selenoid.config.user());
        String password = System.getProperty("password", Selenoid.config.password());
        String browser = System.getProperty("browser", Selenoid.config.browser());
        String browserVersion = System.getProperty("browserVersion", Selenoid.config.browserVersion());
        String browserSize = System.getProperty("browserSize", Selenoid.config.browserSize());
        String url = format("https://%s:%s@%s", user, password, remoteUrl);

        //Configuration.remote = url;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        Configuration.browserSize = browserSize;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
        RestAssured.filters(withCustomTemplates());

        Attach.attachAsText("Browser: ", browser);
        Attach.attachAsText("Version: ", browserVersion);
        Attach.attachAsText("Remote Url: ", remoteUrl);
    }
}
