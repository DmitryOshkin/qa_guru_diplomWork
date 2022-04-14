package yandex.oshkin.tests.UI;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import yandex.oshkin.tests.TestBase;

@Tag("UI")
@Epic("UI")
@Owner("oshkinda")
public class UITests extends TestBase {

    int countOrder = 0;
    int countCompare = 0;

    @Test
    @DisplayName("Поиск товара")
    void searchTest(String productCode) {
        mainPage
                .openMainPage()
                .search(productCode);
        productPage
                .checkResultSearch(productCode);
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductOrderTest(String productCode) {
        searchTest(productCode);
        productPage
                .addProductOrder()
                .checkResultAddOrder(productCode)
                .closePopUpOrder();
        countOrder = countOrder + 1;
        mainPage
                .checkOrderCount(String.valueOf(countOrder));
    }

    @ParameterizedTest(name = "Добавление товара {0} в список сравнения")
    @ValueSource(strings = {"1442049", "1442048", "1114499"})
    @DisplayName("Добавление товара {0} в список сравнения")
    void addProductCompareTest(String productCode) {
        searchTest(productCode);
        productPage
                .addProductCompare();
        countCompare = countCompare + 1;
        mainPage
                .checkCompareCount(String.valueOf(countCompare));
    }

    @ParameterizedTest(name = "Удаление товара из корзины")
    @CsvFileSource(resources = "/data/dataCsv.csv")
    @DisplayName("Удаление товара из корзины")
    void delProductOrderTest(String productCode) {
        addProductOrderTest(productCode);
        orderPage
                .openOrderPage()
                .delProductOrder();
        countOrder = countOrder - 1;
        mainPage
                .checkOrderCount(String.valueOf(countOrder));
    }

    @ParameterizedTest(name = "Полная очистка корзины")
    @CsvFileSource(resources = "/data/dataCsv.csv")
    @DisplayName("Полная очистка корзины")
    void cleanOrderTest(String productCode) {
        addProductOrderTest(productCode);
        orderPage
                .openOrderPage()
                .cleanOrder();
    }

    @ParameterizedTest(name = "Полная очистка списка сравнения")
    @ValueSource(strings = {"1442049", "1442048", "1114499"})
    @DisplayName("Полная очистка списка сравнения")
    void cleanCompareTest(String productCode) {
        addProductCompareTest(productCode);
        comparePage
                .openComparePage()
                .cleanCompare();
    }
}
