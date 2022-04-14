package yandex.oshkin.drivers;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import yandex.oshkin.config.webapp.App;
import yandex.oshkin.config.selenoid.Selenoid;

import static java.lang.String.format;
import static yandex.oshkin.helpers.CustomAllureListener.withCustomTemplates;

public class UIWebDriver {

    private static final ChromeOptions OPTIONS = new ChromeOptions();

    UIWebDriver() {
//        Assertions.assertNotNull(config.browser());
//        Assertions.assertNotNull(config.remoteDriver());

        OPTIONS.addArguments("--no-sandbox");
        OPTIONS.addArguments("--disable-infobars");
        OPTIONS.addArguments("--disable-popup-blocking");
        OPTIONS.addArguments("--disable-notifications");
        OPTIONS.addArguments("--lang=ru-ru");
    }

    public static void createDriver() {
        RestAssured.baseURI = App.config.apiUrl();
        Configuration.baseUrl = App.config.webUrl();
        RestAssured.filters(withCustomTemplates());
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100");
        Configuration.browser = System.getProperty("browser", "chrome");

        String remoteUrl = System.getProperty("remoteUrl");
        String user = Selenoid.config.user();
        String password = Selenoid.config.password();
        // Configuration.remote = format("https://%s:%s@%s", user, password, remoteUrl);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability(ChromeOptions.CAPABILITY, OPTIONS);
        Configuration.browserCapabilities = capabilities;
    }
}
