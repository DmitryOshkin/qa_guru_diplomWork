package yandex.oshkin.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import yandex.oshkin.config.emulator.Emulator;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

@ParametersAreNonnullByDefault
public class EmulatorMobileDriver implements WebDriverProvider {

    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        File app = downloadApk();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(Emulator.config.platformName());
        options.setDeviceName(Emulator.config.deviceName());
        options.setPlatformVersion(Emulator.config.platformVersion());
        options.setApp(app.getAbsolutePath());
        options.setLocale(Emulator.config.locale());
        options.setLanguage(Emulator.config.language());
        options.setAppPackage(Emulator.config.appPackage());
        options.setAppActivity(Emulator.config.appActivity());

        try {
            return new AndroidDriver(new URL(Emulator.config.hubUrl()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File downloadApk() {
        File apk = new File("src/test/resources/apk/app-alpha-universal-release.apk");
        if (!apk.exists()) {
            String url = "https://github.com/wikimedia/apps-android-wikipedia/" +
                    "releases/download/latest/app-alpha-universal-release.apk?raw=true";
            try (InputStream in = new URL(url).openStream()) {
                copyInputStreamToFile(in, apk);
            }
            catch (IOException e) {
                throw new AssertionError("Failed to download apk", e);
            }
        }
        return apk;
    }
}
