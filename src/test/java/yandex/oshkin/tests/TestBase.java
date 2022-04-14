package yandex.oshkin.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import yandex.oshkin.helpers.Attach;
import yandex.oshkin.pages.ComparePage;
import yandex.oshkin.pages.MainPage;
import yandex.oshkin.pages.OrderPage;
import yandex.oshkin.pages.ProductPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static yandex.oshkin.helpers.DeviceSelection.getDeviceDriver;

public class TestBase {

    public MainPage mainPage = new MainPage();
    public ProductPage productPage = new ProductPage();
    public OrderPage orderPage = new OrderPage();
    public ComparePage comparePage = new ComparePage();

    private static final String deviceHost = System.getProperty("deviceHost", "ui");

    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = getDeviceDriver(deviceHost);
        Configuration.browserSize = null;
    }

    @BeforeEach
    void beforeEach() {
        open("");
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSourceText();
        Attach.PageSourceHtml();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

//        @AfterEach
//    public void afterEach() {
//        String sessionId = getSessionId();
//
//        Attach.screenshotAs("Last screenshot");
//        Attach.pageSource();
//
//        closeWebDriver();
//        if (deviceHost.contains("browserstack")) {
//            Attach.video(sessionId);
//        }


}
