package yandex.oshkin.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import yandex.oshkin.pages.ComparePage;
import yandex.oshkin.pages.MainPage;
import yandex.oshkin.pages.OrderPage;
import yandex.oshkin.pages.ProductPage;
import yandex.oshkin.tests.mobile.steps.StepsMobile;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static yandex.oshkin.helpers.Attach.*;
import static yandex.oshkin.helpers.DeviceSelection.getDeviceDriver;

public class TestBase {

    public MainPage mainPage = new MainPage();
    public ProductPage productPage = new ProductPage();
    public OrderPage orderPage = new OrderPage();
    public ComparePage comparePage = new ComparePage();
    public StepsMobile stepMobile = new StepsMobile();

    private static final String deviceHost = System.getProperty("deviceHost", "ui");//ui / emulation / browserstack / realmobile

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = getDeviceDriver(deviceHost);
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = getSessionId();
        screenshotAs("Last screenshot");
        pageSourceText();
        PageSourceHtml();

        switch (deviceHost) {
            case "browserstack":
                browserstackVideo(sessionId);
            case "ui":
                browserConsoleLogs();
                SelenoidVideo(sessionId);
        }
        closeWebDriver();
    }
}
