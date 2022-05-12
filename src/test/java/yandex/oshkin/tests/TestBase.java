package yandex.oshkin.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import yandex.oshkin.pages.mobilePages.*;
import yandex.oshkin.pages.uiPages.ComparePageUi;
import yandex.oshkin.pages.uiPages.MainPageUi;
import yandex.oshkin.pages.uiPages.OrderPageUi;
import yandex.oshkin.pages.uiPages.ProductPageUi;
import yandex.oshkin.tests.commonsteps.CommonStepsAPI;
import yandex.oshkin.tests.commonsteps.CommonStepsMobile;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static yandex.oshkin.helpers.Attach.*;
import static yandex.oshkin.helpers.DeviceSelection.getDeviceDriver;

public class TestBase {

    public MainPageUi mainPageUi = new MainPageUi();
    public ProductPageUi productPageUi = new ProductPageUi();
    public OrderPageUi orderPageUi = new OrderPageUi();
    public ComparePageUi comparePageUi = new ComparePageUi();
    public MainPageMobile mainPageMobile = new MainPageMobile();
    public ProductPageMobile productPageMobile = new ProductPageMobile();
    public OrderPageMobile orderPageMobile = new OrderPageMobile();
    public FavoritePageMobile favoritePageMobile = new FavoritePageMobile();
    public SearchPageMobile searchPageMobile = new SearchPageMobile();
    public CommonStepsMobile commonStepsMobile = new CommonStepsMobile();
    public CommonStepsAPI commonStepsAPI = new CommonStepsAPI();

    private static final String deviceHost = System.getProperty("deviceHost", "ui");  // ui / emulation / browserstack / realmobile

    @BeforeAll
    public static void setUp() {
        addListener("allure", new AllureSelenide());
        getDeviceDriver(deviceHost);
        if (deviceHost.equals("ui")) {
        } else {
            Configuration.browserSize = null;
        }
    }

    @BeforeEach
    public void startDriver() {
        if (deviceHost.equals("ui")) {
            open("/");
        } else {
            open();
        }
    }

    @AfterEach
    public void addAttachments() {
        String sessionId = getSessionId();
        screenshotAs("Last screenshot");
        pageSourceText();
        pageSourceHtml();
        if (deviceHost.equals("ui")) browserConsoleLogs();
        closeWebDriver();

        switch (deviceHost) {
            case "browserstack":
                browserstackVideo(sessionId);
                break;
            case "ui":
                selenoidVideo(sessionId);
                break;
        }
    }
}
