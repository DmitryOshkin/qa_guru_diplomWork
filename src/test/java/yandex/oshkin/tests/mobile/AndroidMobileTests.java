package yandex.oshkin.tests.mobile;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import yandex.oshkin.tests.TestBase;

import static yandex.oshkin.tests.TestData.*;

@Tag("Mobile")
@Epic("Android")
@Owner("oshkinda")
public class AndroidMobileTests extends TestBase {

    @Test
    @DisplayName("Тестирование поиска")
    void searchAppTest() {
        stepMobile
                .skipBanner()
                .searchProduct(product_code_4, product_name_4);
    }

    @Test
    @DisplayName("Тестирование добавления товара в корзину")
    void addProductToOrderTest() {
        stepMobile
                .skipBanner()
                .searchProduct(product_code_1, product_name_1)
                .addProductToOrder(product_code_1, product_name_1);
    }

    @Test
    @DisplayName("Тестирование удаления всех товаров из корзины")
    void DeleteProductFromOrderTest() {
        stepMobile
                .skipBanner()
                .searchProduct(product_code_1, product_name_1)
                .addProductToOrder(product_code_1, product_name_1)
                .searchProduct(product_code_2, product_name_2)
                .addProductToOrder(product_code_2, product_name_2)
                .searchProduct(product_code_4, product_name_4)
                .addProductToOrder(product_code_4, product_name_4)
                .deleteAllProductFromOrder();
    }

    @Test
    @DisplayName("Тестирование добавления товара в избранное")
    void addProductToFavoriteTest() {
        stepMobile
                .skipBanner()
                .searchProduct(product_code_1, product_name_1)
                .addProductToFavoriteList(product_code_1, product_name_1);
    }

    @Test
    @DisplayName("Тестирование удаления товара из избранного")
    void DeleteProductFromFavoriteTest() {
        stepMobile
                .skipBanner()
                .searchProduct(product_code_1, product_name_1)
                .addProductToFavoriteList(product_code_1, product_name_1)
                .searchProduct(product_code_2, product_name_2)
                .addProductToFavoriteList(product_code_2, product_name_2)
                .searchProduct(product_code_4, product_name_4)
                .addProductToFavoriteList(product_code_4, product_name_4)
                .deleteProductFromFavorite(product_name_2);
    }
}
