package yandex.oshkin.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import yandex.oshkin.config.realmobile.RealMobile;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@ParametersAreNonnullByDefault
public class RealMobileDriver implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        File app = downloadApk();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(RealMobile.config.platformName());
        options.setDeviceName(RealMobile.config.deviceName());
        options.setPlatformVersion(RealMobile.config.platformVersion());
        options.setApp(app.getAbsolutePath());
        options.setLocale(RealMobile.config.locale());
        options.setLanguage(RealMobile.config.language());
        options.setAppPackage(RealMobile.config.appPackage());
        options.setAppActivity(RealMobile.config.appActivity());

        try {
            return new AndroidDriver(new URL(RealMobile.config.hubUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File downloadApk() {
        File apk = new File("src/test/resources/apk/citilink.apk");
//        if (!apk.exists()) {
//            String url = "https://github.com/wikimedia/apps-android-wikipedia/" +
//                    "releases/download/latest/app-alpha-universal-release.apk?raw=true";
//            try (InputStream in = new URL(url).openStream()) {
//                copyInputStreamToFile(in, apk);
//            }
//            catch (IOException e) {
//                throw new AssertionError("Failed to download apk", e);
//            }
//        }
        return apk;
    }
}
