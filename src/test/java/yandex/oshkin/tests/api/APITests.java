package yandex.oshkin.tests.api;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import yandex.oshkin.tests.TestBase;
import yandex.oshkin.tests.commonsteps.CommonStepsAPI;

import static yandex.oshkin.tests.TestData.*;

@Tag("API")
@Epic("API")
@Owner("oshkinda")
public class APITests extends TestBase {

    public CommonStepsAPI commonStepsAPI = new CommonStepsAPI();

    @Test
    @DisplayName("Добавление товара в корзину и изменение его количества")
    public void addToOrderProductTest() {

        commonStepsAPI
                .addToOrder(product_code_2, 4)
                .changeProductCountToOrder(product_code_2, 7);
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    public void delOrderProductTest() {

        commonStepsAPI
                .addToOrder(product_code_2, 4)
                .delProductOrder(product_code_2);
    }

    @Test
    @DisplayName("Очистка корзины")
    public void clearOrderProductTest() {

        commonStepsAPI
                .addToOrder(product_code_2, 4)
                .addToOrder(product_code_1, 3)
                .addToOrder(product_code_3, 8)
                .clearOrder();
    }

    @Test
    @DisplayName("Добавление товара в список сравнения")
    public void addToCompareTest() {

        commonStepsAPI
                .addToCompare(product_code_1, 211);
    }

    @Test
    @DisplayName("Удаление товара из списка сравнения")
    public void delProductCompareTest() {

        commonStepsAPI
                .addToCompare(product_code_1, 211)
                .addToCompare(product_code_2, 211)
                .delProductCompare(product_code_1, 211);
    }

    @Test
    @DisplayName("Очистка списка сравнения")
    public void cleanProductCompareTest() {

        commonStepsAPI
                .addToCompare(product_code_1, 211)
                .addToCompare(product_code_2, 211)
                .addToCompare(product_code_3, 211)
                .cleanProductCompare(211);
    }
}
