package yandex.oshkin.tests.api;

import io.qameta.allure.Owner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import yandex.oshkin.models.Basket.clearBasket.ClearBasket;
import yandex.oshkin.tests.TestBase;

import java.util.Map;

import static yandex.oshkin.tests.TestData.*;

@Tag("api")
@Owner("oshkinda")
public class APITests extends TestBase {

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
        int basketCount = 0;
        Map<String, Integer> products = Map.of(product_code_2, 4,product_code_1, 3, product_code_3, 8);
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            basketCount += entry.getValue();
            commonStepsAPI.addToOrder(entry.getKey(), entry.getValue());
        }
        ClearBasket basketClean = commonStepsAPI.clearOrder();
        Assertions.assertThat(basketClean.getStorage().getCart().getList().isEmpty());
        Assertions.assertThat(basketClean.getStorage().getCart().getBasketCount()).isEqualTo(0);

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
