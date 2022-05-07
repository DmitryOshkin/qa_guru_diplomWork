package yandex.oshkin.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderPage {
    SelenideElement
            cleanOrderButton = $(".OrderFinalPrice__empty-cart"),
            cleanOrderProductButton =
                    $(".ProductListForBasket__item .ProductCardForBasket__button-icon_remove");

    @Step("Открываем страницу корзины")
    public OrderPage openOrderPage() {
        open("/order/");
//        $(".Basket__title__text")
//                .shouldHave(text("Корзина"));
        return this;
    }
    @Step("Проверяем что открыта страница корзины")
    public OrderPage checkOpenOrderPage() {
        $(".Basket__title__text")
                .shouldHave(text("Корзина"));
        return this;
    }

    @Step("Удаляем товар из корзины")
    public OrderPage delProductOrder() {
        cleanOrderProductButton.click();
        return this;
    }

    @Step("Очищаем корзину целиком")
    public OrderPage cleanOrder() {
        cleanOrderButton.click();
//        $(".Basket__basket-empty-title")
//                .shouldHave(text("В корзине нет товаров"));
        return this;
    }

    @Step("Проверяем что корзина пуста")
    public OrderPage checkCleanOrder() {
        $(".Basket__basket-empty-title")
                .shouldHave(text("В корзине нет товаров"));
        return this;
    }
}
