package yandex.oshkin.tests.api;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import yandex.oshkin.tests.TestBase;

import static yandex.oshkin.tests.TestData.*;

@Tag("api")
@Owner("oshkinda")
public class APITests extends TestBase {

    @Test
    @DisplayName("Добавление товара в корзину и изменение его количества")
    public void addToOrderProductTest() {
        int basketCount = 0;
        basketCount = basketCount + 4;
        commonStepsAPI
                .addToOrder(product_code_2, 4)
                .changeProductCountToOrder(product_code_2, 7);
    }

    @Test
    @DisplayName("Удаление товара из корзины")
    public void delOrderProductTest() {
        int basketCount = 0;
        basketCount = basketCount + 4;
        commonStepsAPI
                .addToOrder(product_code_2, 4)
                .delProductOrder(product_code_2);
    }

    @Test
    @DisplayName("Очистка корзины")
    public void clearOrderProductTest() {
        int basketCount = 0;
        basketCount = basketCount + 4;
        commonStepsAPI.addToOrder(product_code_2, 4);
        basketCount = basketCount + 3;
        commonStepsAPI.addToOrder(product_code_1, 3);
        basketCount = basketCount + 8;
        commonStepsAPI.addToOrder(product_code_3, 8);
        commonStepsAPI.clearOrder();
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
