package yandex.oshkin.pages.uiPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderPageUi {
    SelenideElement
            cleanOrderButton = $(".OrderFinalPrice__empty-cart"),
            cleanOrderProductButton =
                    $(".ProductListForBasket__item .ProductCardForBasket__button-icon_remove");

    @Step("Открываем страницу корзины")
    public OrderPageUi openOrderPage() {
        open("/order/");
        return this;
    }

    @Step("Проверяем что открыта страница корзины")
    public OrderPageUi checkOpenOrderPage() {
        $(".Basket__title__text")
                .shouldHave(text("Корзина"));
        return this;
    }

    @Step("Удаляем товар из корзины")
    public OrderPageUi delProductOrder() {
        cleanOrderProductButton.click();
        return this;
    }

    @Step("Очищаем корзину целиком")
    public OrderPageUi cleanOrder() {
        cleanOrderButton.click();
        return this;
    }

    @Step("Проверяем что корзина пуста")
    public OrderPageUi checkCleanOrder() {
        $(".Basket__basket-empty-title")
                .shouldHave(text("В корзине нет товаров"));
        return this;
    }
}
