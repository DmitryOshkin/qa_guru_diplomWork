package yandex.oshkin.tests.commonsteps;

import io.qameta.allure.Step;
import yandex.oshkin.pages.mobilePages.*;

public class CommonStepsMobile {
    public MainPageMobile mainPageMobile = new MainPageMobile();
    public ProductPageMobile productPageMobile = new ProductPageMobile();
    public OrderPageMobile orderPageMobile = new OrderPageMobile();
    public FavoritePageMobile favoritePageMobile = new FavoritePageMobile();
    public SearchPageMobile searchPageMobile = new SearchPageMobile();

    @Step("Ищем товар")
    public CommonStepsMobile searchProduct(String productCode) {
        mainPageMobile
                .openMainPage()
                .checkOpenMainPage()
                .clickSearchButton();
        searchPageMobile
                .checkOpenSearchPage()
                .insertSearchProduct(productCode);
        return this;
    }

    @Step("Добавляем товар в корзину")
    public CommonStepsMobile addProductToOrder(String productCode, String productName) {
        searchPageMobile.openProduct();
        productPageMobile
                .checkOpenProductPage(productName, productCode)
                .clickButtonAddProductToOrder();
        return this;
    }

    @Step("Проверяем добавление товара в корзину")
    public CommonStepsMobile checkAddedProductToOrder(String productName) {
        mainPageMobile.openOrder();
        orderPageMobile
                .checkOpenOrder()
                .checkProductInOrder(productName);
        return this;
    }

    @Step("Удаляем все товары из корзины")
    public CommonStepsMobile deleteAllProductFromOrder() {
        orderPageMobile
                .tapToChangeButton()
                .selectAllProductsOnOrder()
                .delProductsFromOrder();
        return this;
    }

    @Step("Добавляем товар в избранное")
    public CommonStepsMobile addProductToFavoriteList(String productCode, String productName) {
        searchPageMobile.openProduct();
        productPageMobile
                .checkOpenProductPage(productName, productCode)
                .addProductsToFavorite();
        return this;
    }

    @Step("Проверяем добавление товара в избранное")
    public CommonStepsMobile checkAddedProductToFavoriteList(String productName) {
        mainPageMobile.openFavorite();
        favoritePageMobile
                .checkOpenFavorite()
                .checkProductInFavorite(productName);
        return this;
    }

    @Step("Удаляем товар из избранного")
    public CommonStepsMobile deleteProductFromFavorite(String productName) {
        favoritePageMobile.delProductFromFavorite(productName);
        mainPageMobile.openFavorite();
        return this;
    }
}
