package yandex.oshkin.tests.UI;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import yandex.oshkin.tests.TestBase;

import static yandex.oshkin.tests.TestData.product_1;
import static yandex.oshkin.tests.TestData.product_2;

@Tag("UI")
@Epic("UI")
@Owner("oshkinda")
public class UITests extends TestBase {

    public CommonSteps commonSteps = new CommonSteps();

    @Test
    @DisplayName("Поиск товара")
    void searchTest() {
        commonSteps
                .search(product_1);
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductOrderTest() {
        commonSteps
                .search(product_2)
                .addProductOrder(product_2);
    }

    @ParameterizedTest(name = "Добавление товара {0} в список сравнения")
    @ValueSource(strings = {"1442049", "1442048", "1114499"})
    void addProductCompareTest(String productCode) {
        commonSteps
                .search(productCode)
                .addProductCompare();
    }

    @ParameterizedTest(name = "Удаление товара из корзины")
    @CsvSource(value = {"1442049, 1442048, 1114499"})
    void delProductOrderTest(String productCode_1, String productCode_2, String productCode_3) {
        commonSteps
                .search(productCode_1)
                .addProductOrder(productCode_1)
                .search(productCode_2)
                .addProductOrder(productCode_2)
                .search(productCode_3)
                .addProductOrder(productCode_3);
        orderPage
                .openOrderPage()
                .delProductOrder();
        commonSteps.countOrder = commonSteps.countOrder - 1;
        mainPage
                .checkOrderCount(String.valueOf(commonSteps.countOrder));
    }

    @ParameterizedTest(name = "Полная очистка корзины")
    @CsvFileSource(resources = "/data/dataCsv.csv")
    void cleanOrderTest(String productCode_1, String productCode_2, String productCode_3) {
        commonSteps
                .search(productCode_1)
                .addProductOrder(productCode_1)
                .search(productCode_2)
                .addProductOrder(productCode_2)
                .search(productCode_3)
                .addProductOrder(productCode_3);
        orderPage
                .openOrderPage()
                .cleanOrder();
    }

    @ParameterizedTest(name = "Полная очистка списка сравнения")
    @CsvFileSource(resources = "/data/dataCsv.csv")
    void cleanCompareTest(String productCode_1, String productCode_2, String productCode_3) {
        commonSteps
                .search(productCode_1)
                .addProductCompare()
                .search(productCode_2)
                .addProductCompare()
                .search(productCode_3)
                .addProductCompare();
        comparePage
                .openComparePage()
                .cleanCompare();
    }
}
