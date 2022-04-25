package yandex.oshkin.tests.commonsteps;

import io.qameta.allure.Step;
import yandex.oshkin.tests.TestBase;

public class CommonStepsUI extends TestBase {

    public int countOrder = 0;
    int countCompare = 0;

    @Step("Ищем товар")
    public CommonStepsUI searchProduct(String productCode) {
        mainPage
                .openMainPage()
                .closeCookies()
                .search(productCode);
        productPage
                .checkResultSearch(productCode);
        return this;
    }

    @Step("Добавляем товар в корзину")
    public CommonStepsUI addToOrder(String productCode) {
        productPage
                .addProductOrder()
                .checkResultAddOrder(productCode)
                .closePopUpOrder();
        countOrder = countOrder + 1;
        mainPage
                .checkOrderCount(String.valueOf(countOrder));
        return this;
    }

    @Step("Добавляем товар в список сравнения")
    public CommonStepsUI addToCompare() {
        productPage
                .addProductCompare();
        countCompare = countCompare + 1;
        mainPage
                .checkCompareCount(String.valueOf(countCompare));
        return this;
    }
}
