package yandex.oshkin.helpers;

import com.codeborne.selenide.Configuration;
import yandex.oshkin.drivers.BrowserstackMobileDriver;
import yandex.oshkin.drivers.EmulatorMobileDriver;
import yandex.oshkin.drivers.RealMobileDriver;
import yandex.oshkin.drivers.UIWebDriver;

public class DeviceSelection {
    public static String getDeviceDriver(String deviceHost) {

        switch (deviceHost) {
            case "ui":
                UIWebDriver.createDriver();
                break;
            case "emulation":
                Configuration.browser = EmulatorMobileDriver.class.getName();
                break;
            case "browserstack":
                Configuration.browser = BrowserstackMobileDriver.class.getName();
                break;
            case "realmobile":
                Configuration.browser = RealMobileDriver.class.getName();
                break;
            default:
                throw new RuntimeException("Please select " +
                        "-DdeviceHost parameter / ui / emulation / browserstack / realmobile");
        }
        return deviceHost;
    }
}
