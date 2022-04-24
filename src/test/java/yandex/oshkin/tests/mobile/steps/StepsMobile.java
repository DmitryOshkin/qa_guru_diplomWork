package yandex.oshkin.tests.mobile.steps;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class StepsMobile {

    Integer
            countOrder = 0,
            countFavorite = 0;

    @Step("Ищем товар")
    public StepsMobile searchProduct(String productCode, String productName) {
        openMainPage();
        checkOpenMainPage();
        clickSearchButton();
        checkOpenSearchPage();
        insertSearchProduct(productCode);
        checkSearchResult(productName);
        return this;
    }

    @Step("Добавляем товар в корзину")
    public StepsMobile addProductToOrder(String productCode, String productName) {
        openProduct();
        checkOpenProductPage(productName, productCode);
        clickButtonAddProductToOrder();
        countOrder = countOrder + 1;
        checkAddedProductToOrder(countOrder);
        openOrder();
        checkOpenOrder();
        checkProductInOrder(productName);
        return this;
    }

    @Step("Удаляем все товары из корзины")
    public StepsMobile deleteProductFromOrder() {
        tapToChangeButton();
        selectAllProductsOnOrder();
        delProductsFromOrder();
        checkDeleteAllProductsFromOrder();
        return this;
    }

    @Step("Добавляем товар в избранное")
    public StepsMobile addProductToFavoriteList(String productCode, String productName) {
        openProduct();
        checkOpenProductPage(productName, productCode);
        addProductsToFavorite();
        countFavorite = countFavorite + 1;
        checkAddedProductToFavorite(countFavorite);
        openFavorite();
        checkOpenFavorite();
        checkProductInFavorite(productName);
        return this;
    }

    @Step("Удаляем товар из избранного")
    public StepsMobile deleteProductFromFavorite() {
        delProductFromFavorite();
        openFavorite();
        checkDelAllProductFromFavorite();
        return this;
    }

    @Step("Открываем главную страницу приложения")
    public StepsMobile openMainPage() {
        $(AppiumBy.id("ru.citilink:id/dashboard_graph")).click();
        return this;
    }

    @Step("Проверяем открытие главной страницы приложения")
    public StepsMobile checkOpenMainPage() {
        $(AppiumBy.id("ru.citilink:id/tabLayoutBanners"))
                .shouldBe();
        return this;
    }

    @Step("Подтверждаем регион местонахождения")
    public StepsMobile skipBanner() {
        $(AppiumBy.id("android:id/button1")).click();
        return this;
    }

    @Step("Нажимаем на кнопку поиска товара")
    public StepsMobile clickSearchButton() {
        $(AppiumBy.id("ru.citilink:id/buttonSearch")).click();
        return this;
    }

    @Step("Проверяем открытие страницы поиска товара")
    public StepsMobile checkOpenSearchPage() {
        $(AppiumBy.id("ru.citilink:id/textEmptyTitle"))
                .shouldHave(attribute("text", "Введите название товара"));
        return this;
    }

    @Step("Вводим код товара в поле поиска")
    public StepsMobile insertSearchProduct(String productCode) {
        $(AppiumBy.id("ru.citilink:id/editTextSearchToolbar")).setValue(productCode);
        return this;
    }

    @Step("Проверяем результаты поиска")
    public StepsMobile checkSearchResult(String productName) {
        $(AppiumBy.id("ru.citilink:id/textViewSearchProductName"))
                .shouldHave(attribute("text", productName));
        return this;
    }

    @Step("Открываем найденный товар")
    public StepsMobile openProduct() {
        $(AppiumBy.id("ru.citilink:id/textViewSearchProductName")).click();
        return this;
    }

    @Step("Проверяем открытие страницы с товаром")
    public StepsMobile checkOpenProductPage(String productName, String productCode) {
        $(AppiumBy.id("ru.citilink:id/textViewProductTitle"))
                .shouldHave(attribute("text", productName));
        $(AppiumBy.id("ru.citilink:id/textViewProductId"))
                .shouldHave(attribute("text", "ID " + productCode));
        return this;
    }

    @Step("Нажимаем на кнопку добавления товара в корзину")
    public StepsMobile clickButtonAddProductToOrder() {
        $(AppiumBy.id("ru.citilink:id/textViewProductAddToCartLabel")).click();
        return this;
    }

    @Step("Проверяем добавление товара в корзину")
    public StepsMobile checkAddedProductToOrder(Integer count) {
        $(AppiumBy.id("ru.citilink:id/ordering_graph"))
                .shouldHave(attributeMatching("content-desc", "Корзина, " + count + " new notification.*"));
        return this;
    }

    @Step("Открываем корзину")
    public StepsMobile openOrder() {
        $(AppiumBy.id("ru.citilink:id/ordering_graph")).click();
        return this;
    }

    @Step("Проверяем открытие корзины")
    public StepsMobile checkOpenOrder() {
        sleep(3000);
        $(AppiumBy.id("ru.citilink:id/toolbar"))
                .$(AppiumBy.className("android.widget.TextView"))
                .shouldHave(attribute("text", "Корзина"));
        return this;
    }

    @Step("Проверяем наличие добавленного товара в корзине")
    public StepsMobile checkProductInOrder(String productName) {
        $(AppiumBy.id("ru.citilink:id/textViewCartProductName"))
                .shouldHave(attribute("text", productName));
        return this;
    }

    @Step("Нажимаем на кнопку Изменить")
    public StepsMobile tapToChangeButton() {
        $(AppiumBy.id("ru.citilink:id/cartChange")).click();
        return this;
    }

    @Step("Выбираем все товары в корзине")
    public StepsMobile selectAllProductsOnOrder() {
        $(AppiumBy.id("ru.citilink:id/checkBoxCartProductSelectorAll")).click();
        return this;
    }

    @Step("Удаляем выбраные товары из корзины")
    public StepsMobile delProductsFromOrder() {
        $(AppiumBy.id("ru.citilink:id/buttonCartDeleteProducts")).click();
        return this;
    }

    @Step("Проверяем что все товары удалены")
    public StepsMobile checkDeleteAllProductsFromOrder() {
        $(AppiumBy.id("ru.citilink:id/textEmptyTitle"))
                .shouldHave(attribute("text", "Ваша корзина пуста"));
        return this;
    }

    @Step("Добавляем товар в избраное")
    public StepsMobile addProductsToFavorite() {
        $(AppiumBy.id("ru.citilink:id/favorite")).click();
        return this;
    }

    @Step("Проверяем добавление товара в избранное")
    public StepsMobile checkAddedProductToFavorite(Integer count) {
        $(AppiumBy.id("ru.citilink:id/favorite_products_graph"))
                .shouldHave(attributeMatching("content-desc", "Избранное, " + count + " new notification.*"));
        return this;
    }

    @Step("Открываем избранное")
    public StepsMobile openFavorite() {
        $(AppiumBy.id("ru.citilink:id/favorite_products_graph")).click();
        return this;
    }

    @Step("Проверяем открытие избранного")
    public StepsMobile checkOpenFavorite() {
        $(AppiumBy.id("ru.citilink:id/toolbar"))
                .$(AppiumBy.className("android.widget.TextView"))
                .shouldHave(attribute("text", "Избранное"));
        return this;
    }

    @Step("Проверяем наличие добавленного товара в избранное")
    public StepsMobile checkProductInFavorite(String productName) {
        $(AppiumBy.id("ru.citilink:id/constraintLayoutItemProduct"))
                .$(AppiumBy.id("ru.citilink:id/textViewTitle"))
                .shouldHave(attribute("text", productName));
        return this;
    }

    @Step("Убираем товар из избранного")
    public StepsMobile delProductFromFavorite() {
        $(AppiumBy.id("ru.citilink:id/imageViewFavorite")).click();
        return this;
    }

    @Step("Проверяем что в избранном нет товаров")
    public StepsMobile checkDelAllProductFromFavorite() {
        $(AppiumBy.id("ru.citilink:id/textEmptyTitle"))
                .shouldHave(attribute("text", "В избранном пока пусто"));
        return this;
    }
}
