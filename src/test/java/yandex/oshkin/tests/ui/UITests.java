package yandex.oshkin.tests.ui;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import yandex.oshkin.tests.TestBase;

import static yandex.oshkin.tests.TestData.product_code_1;
import static yandex.oshkin.tests.TestData.product_code_2;

@Tag("ui")
@Owner("oshkinda")
public class UITests extends TestBase {

    @Test
    @DisplayName("Поиск товара")
    void searchTest() {
        mainPageUi
                .searchProduct(product_code_1);
        productPageUi
                .checkResultSearch(product_code_1);
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductOrderTest() {
        int countOrder = 0;
        mainPageUi
                .searchProduct(product_code_2);
        productPageUi
                .checkResultSearch(product_code_2);
        productPageUi
                .addToOrder(product_code_2);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
    }

    @ParameterizedTest(name = "Добавление товара {0} в список сравнения")
    @ValueSource(strings = {"1442049", "1442048", "1114499"})
    void addProductCompareTest(String productCode) {
        int countCompare = 0;
        mainPageUi
                .searchProduct(productCode);
        productPageUi
                .checkResultSearch(productCode);
        productPageUi
                .addProductCompare();
        countCompare++;
        mainPageUi
                .checkCompareCount(String.valueOf(countCompare));
    }

    @ParameterizedTest(name = "Удаление товара из корзины")
    @CsvSource(value = {"1442049, 1442048, 1114499"})
    void delProductOrderTest(String productCode_1, String productCode_2, String productCode_3) {
        int countOrder = 0;
        mainPageUi
                .searchProduct(productCode_1);
        productPageUi
                .checkResultSearch(productCode_1)
                .addToOrder(productCode_1);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
        mainPageUi
                .searchProduct(productCode_2);
        productPageUi
                .checkResultSearch(productCode_2)
                .addToOrder(productCode_2);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
        mainPageUi
                .searchProduct(productCode_3);
        productPageUi
                .checkResultSearch(productCode_3)
                .addToOrder(productCode_3);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
        orderPageUi
                .openOrderPage()
                .checkOpenOrderPage()
                .delProductOrder();
        countOrder--;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
    }

    @ParameterizedTest(name = "Полная очистка корзины")
    @CsvFileSource(resources = "/data/uidata.csv")
    void cleanOrderTest(String productCode_1, String productCode_2, String productCode_3) {
        int countOrder = 0;
        mainPageUi
                .searchProduct(productCode_1);
        productPageUi
                .checkResultSearch(productCode_1)
                .addToOrder(productCode_1);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
        mainPageUi
                .searchProduct(productCode_2);
        productPageUi
                .checkResultSearch(productCode_2)
                .addToOrder(productCode_2);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
        mainPageUi
                .searchProduct(productCode_3);
        productPageUi
                .checkResultSearch(productCode_3)
                .addToOrder(productCode_3);
        countOrder++;
        mainPageUi
                .checkOrderCount(String.valueOf(countOrder));
        orderPageUi
                .openOrderPage()
                .checkOpenOrderPage()
                .cleanOrder()
                .checkCleanOrder();
    }

    @ParameterizedTest(name = "Полная очистка списка сравнения")
    @CsvFileSource(resources = "/data/uidata.csv")
    void cleanCompareTest(String productCode_1, String productCode_2, String productCode_3) {
        int countCompare = 0;
        mainPageUi
                .searchProduct(productCode_1);
        productPageUi
                .checkResultSearch(productCode_1)
                .addProductCompare();
        countCompare++;
        mainPageUi
                .checkCompareCount(String.valueOf(countCompare));
        mainPageUi
                .searchProduct(productCode_2);
        productPageUi
                .checkResultSearch(productCode_2)
                .addProductCompare();
        countCompare++;
        mainPageUi
                .checkCompareCount(String.valueOf(countCompare));
        mainPageUi
                .searchProduct(productCode_3);
        productPageUi
                .checkResultSearch(productCode_3)
                .addProductCompare();
        countCompare++;
        mainPageUi
                .checkCompareCount(String.valueOf(countCompare));
        comparePageUi
                .openComparePage()
                .checkOpenComparePage()
                .cleanCompare()
                .checkCleanCompare();
    }
}
