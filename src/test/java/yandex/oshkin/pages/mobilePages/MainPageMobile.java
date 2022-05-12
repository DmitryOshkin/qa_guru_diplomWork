package yandex.oshkin.pages.mobilePages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class MainPageMobile {

    @Step("Открываем главную страницу приложения")
    public MainPageMobile openMainPage() {
        $(AppiumBy.id("ru.citilink:id/dashboard_graph")).click();
        return this;
    }

    @Step("Проверяем открытие главной страницы приложения")
    public MainPageMobile checkOpenMainPage() {
        $(AppiumBy.id("ru.citilink:id/tabLayoutBanners"))
                .shouldBe();
        return this;
    }

    @Step("Подтверждаем регион местонахождения")
    public MainPageMobile skipBanner() {
        $(AppiumBy.id("android:id/button1")).click();
        return this;
    }

    @Step("Нажимаем на кнопку поиска товара")
    public MainPageMobile clickSearchButton() {
        $(AppiumBy.id("ru.citilink:id/buttonSearch")).click();
        return this;
    }

    @Step("Открываем корзину")
    public MainPageMobile openOrder() {
        $(AppiumBy.id("ru.citilink:id/ordering_graph")).click();
        return this;
    }

    @Step("Открываем избранное")
    public MainPageMobile openFavorite() {
        $(AppiumBy.id("ru.citilink:id/favorite_products_graph")).click();
        return this;
    }
}
