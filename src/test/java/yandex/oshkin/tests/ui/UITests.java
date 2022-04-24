package yandex.oshkin.tests.ui;

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
import yandex.oshkin.tests.commonsteps.CommonStepsUI;

import static yandex.oshkin.tests.TestData.product_code_1;
import static yandex.oshkin.tests.TestData.product_code_2;

@Tag("UI")
@Epic("UI")
@Owner("oshkinda")
public class UITests extends TestBase {

    public CommonStepsUI commonStepsUI = new CommonStepsUI();

    @Test
    @DisplayName("Поиск товара")
    void searchTest() {
        commonStepsUI
                .searchProduct(product_code_1);
    }

    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductOrderTest() {
        commonStepsUI
                .searchProduct(product_code_2)
                .addToOrder(product_code_2);
    }

    @ParameterizedTest(name = "Добавление товара {0} в список сравнения")
    @ValueSource(strings = {"1442049", "1442048", "1114499"})
    void addProductCompareTest(String productCode) {
        commonStepsUI
                .searchProduct(productCode)
                .addToCompare();
    }

    @ParameterizedTest(name = "Удаление товара из корзины")
    @CsvSource(value = {"1442049, 1442048, 1114499"})
    void delProductOrderTest(String productCode_1, String productCode_2, String productCode_3) {
        commonStepsUI
                .searchProduct(productCode_1)
                .addToOrder(productCode_1)
                .searchProduct(productCode_2)
                .addToOrder(productCode_2)
                .searchProduct(productCode_3)
                .addToOrder(productCode_3);
        orderPage
                .openOrderPage()
                .delProductOrder();
        commonStepsUI.countOrder = commonStepsUI.countOrder - 1;
        mainPage
                .checkOrderCount(String.valueOf(commonStepsUI.countOrder));
    }

    @ParameterizedTest(name = "Полная очистка корзины")
    @CsvFileSource(resources = "/data/uidata.csv")
    void cleanOrderTest(String productCode_1, String productCode_2, String productCode_3) {
        commonStepsUI
                .searchProduct(productCode_1)
                .addToOrder(productCode_1)
                .searchProduct(productCode_2)
                .addToOrder(productCode_2)
                .searchProduct(productCode_3)
                .addToOrder(productCode_3);
        orderPage
                .openOrderPage()
                .cleanOrder();
    }

    @ParameterizedTest(name = "Полная очистка списка сравнения")
    @CsvFileSource(resources = "/data/uidata.csv")
    void cleanCompareTest(String productCode_1, String productCode_2, String productCode_3) {
        commonStepsUI
                .searchProduct(productCode_1)
                .addToCompare()
                .searchProduct(productCode_2)
                .addToCompare()
                .searchProduct(productCode_3)
                .addToCompare();
        comparePage
                .openComparePage()
                .cleanCompare();
    }
}
