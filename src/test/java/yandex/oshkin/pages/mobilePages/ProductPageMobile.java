package yandex.oshkin.pages.mobilePages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;

public class ProductPageMobile {

    @Step("Проверяем открытие страницы с товаром")
    public ProductPageMobile checkOpenProductPage(String productName, String productCode) {
        $(AppiumBy.id("ru.citilink:id/textViewProductTitle"))
                .shouldHave(attribute("text", productName));
        $(AppiumBy.id("ru.citilink:id/textViewProductId"))
                .shouldHave(attribute("text", "ID " + productCode));
        return this;
    }

    @Step("Нажимаем на кнопку добавления товара в корзину")
    public ProductPageMobile clickButtonAddProductToOrder() {
        $(AppiumBy.id("ru.citilink:id/textViewProductAddToCartLabel")).click();
        return this;
    }

    @Step("Проверяем изменение счетчика на иконке товаров в корзине")
    public ProductPageMobile checkChangeCountProductToOrder(Integer count) {
        $(AppiumBy.id("ru.citilink:id/ordering_graph"))
                .shouldHave(attributeMatching("content-desc", "Корзина, " + count + ".*"));
        return this;
    }

    @Step("Добавляем товар в избраное")
    public ProductPageMobile addProductsToFavorite() {
        $(AppiumBy.id("ru.citilink:id/favorite")).click();
        return this;
    }

    @Step("Проверяем изменение счетчика на иконке товаров в избранном")
    public ProductPageMobile checkChangeCountProductToFavorite(Integer count) {
        $(AppiumBy.id("ru.citilink:id/favorite_products_graph"))
                .shouldHave(attributeMatching("content-desc", "Избранное, " + count + ".*"));
        return this;
    }
}
