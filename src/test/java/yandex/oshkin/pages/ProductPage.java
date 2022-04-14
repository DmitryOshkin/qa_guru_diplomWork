package yandex.oshkin.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    SelenideElement
            addOrderButton = $(".js--AddToCart .Button__text"),
            addCompareButton = $(".js--ProductHeader__add-to-compare"),
            closePopUpButton = $(".UpsaleBasket__main-popup__close path");

    @Step("Проверяем результат поиска")
    public ProductPage checkResultSearch(String value) {
        $(".ProductHeader__product-id")
                .shouldHave(text("Код товара: " + value));
        return this;
    }

    @Step("Добавляем товар в корзину")
    public ProductPage addProductOrder() {
        addOrderButton.click();
        return this;
    }

    @Step("Добавляем товар в список сравнения")
    public ProductPage addProductCompare() {
        addCompareButton.click();
        return this;
    }

    @Step("Проверяем добавление товара в корзину")
    public ProductPage checkResultAddOrder(String value) {
        $(".UpsaleBasket__header-title")
                .shouldHave(text("Товар добавлен в корзину"));
        $(".ProductCardForUpsale__vendor-code")
                .shouldHave(text("Код товара: " + value));
        return this;
    }

    @Step("Закрываем всплывающее окно с товаром")
    public ProductPage closePopUpOrder() {
        closePopUpButton.click();
        return this;
    }
}
