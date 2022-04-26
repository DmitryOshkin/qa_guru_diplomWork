package yandex.oshkin.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import yandex.oshkin.config.browserstack.Browserstack;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

//    private static final String browserstackUploadSessionId = uploadAPK();

    public static URL getBrowserstackUrl() {
        try {
            return new URL(Browserstack.config.browserstackURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Nonnull
//    private static String uploadAPK() {
//        return given()
//                .auth().basic(Browserstack.config.browserstackUser(), Browserstack.config.browserstackKey())
//                .multiPart("file", new File("src/test/resources/apk/citilink.apk"))
//                .contentType("multipart/form-data;")
//                .log().all()
//                .when()
//                .post(Browserstack.config.browserstackUploadApkUrl())
//                .then()
//                .log().all()
//                .statusCode(200)
//                .body("app_url", is(notNullValue()))
//                .extract().path("app_url").toString();
//    }

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        mutableCapabilities.setCapability("browserstack.appium_version", "1.22.0");
        mutableCapabilities.setCapability("browserstack.user", Browserstack.config.browserstackUser());
        mutableCapabilities.setCapability("browserstack.key", Browserstack.config.browserstackKey());
        mutableCapabilities.setCapability("app", Browserstack.config.browserstackSessionId());
        mutableCapabilities.setCapability("device", Browserstack.config.browserstackDeviceName());
        mutableCapabilities.setCapability("os_version", Browserstack.config.browserstackDeviceVersion());
        mutableCapabilities.setCapability("project", "First Java Project");
        mutableCapabilities.setCapability("build", "browserstack-build-1");
        mutableCapabilities.setCapability("name", "first_test");

        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }
}