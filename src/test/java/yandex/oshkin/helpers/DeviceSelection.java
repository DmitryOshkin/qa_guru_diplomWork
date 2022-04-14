package yandex.oshkin.helpers;

import yandex.oshkin.drivers.BrowserstackMobileDriver;
import yandex.oshkin.drivers.EmulatorMobileDriver;
import yandex.oshkin.drivers.RealMobileDriver;
import yandex.oshkin.drivers.UIWebDriver;

public class DeviceSelection {
    public static String getDeviceDriver(String deviceHost) {

        switch (deviceHost) {
            case "ui":
                UIWebDriver.createDriver();
            case "emulation":
                return EmulatorMobileDriver.class.getName();
            case "browserstack":
                return BrowserstackMobileDriver.class.getName();
            case "realmobile":
                return RealMobileDriver.class.getName();
            default:
                throw new RuntimeException("Please select only " +
                        "ui / emulation / browserstack / realmobile /-DdeviceHost parameter");
        }
    }
}
