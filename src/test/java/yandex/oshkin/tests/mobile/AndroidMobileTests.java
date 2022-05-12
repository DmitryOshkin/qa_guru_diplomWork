package yandex.oshkin.tests.mobile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import yandex.oshkin.tests.TestBase;

import java.util.Map;

import static yandex.oshkin.tests.TestData.*;

@Tag("mobile")
@Owner("oshkinda")
public class AndroidMobileTests extends TestBase {

    @Test
    @DisplayName("Тестирование поиска")
    void searchAppTest() {
        mainPageMobile
                .skipBanner();
        commonStepsMobile
                .searchProduct(product_code_4);
        searchPageMobile
                .checkSearchResult(product_name_4);
    }

    @Test
    @DisplayName("Тестирование добавления товара в корзину")
    void addProductToOrderTest() {
        int countOrder = 0;
        mainPageMobile
                .skipBanner();
        commonStepsMobile
                .searchProduct(product_code_1);
        searchPageMobile
                .checkSearchResult(product_name_1);
        commonStepsMobile
                .addProductToOrder(product_code_1, product_name_1);
        countOrder++;
        productPageMobile
                .checkChangeCountProductToOrder(countOrder);
        commonStepsMobile
                .checkAddedProductToOrder(product_name_1);
    }

    @Test
    @DisplayName("Тестирование удаления всех товаров из корзины")
    void DeleteProductFromOrderTest() {
        int countOrder = 0;
        mainPageMobile
                .skipBanner();
        Map<String, String> products =
                Map.of(product_code_1, product_name_1, product_code_2, product_name_2, product_code_4, product_name_4);
        for (Map.Entry<String, String> entry : products.entrySet()) {
            commonStepsMobile
                    .searchProduct(entry.getKey());
            searchPageMobile
                    .checkSearchResult(entry.getValue());
            commonStepsMobile
                    .addProductToOrder(entry.getKey(), entry.getValue());
            countOrder++;
            productPageMobile
                    .checkChangeCountProductToOrder(countOrder);
            commonStepsMobile
                    .checkAddedProductToOrder(entry.getValue());
        }
        commonStepsMobile
                .deleteAllProductFromOrder();
        orderPageMobile
                .checkDeleteAllProductsFromOrder();
    }

    @Test
    @DisplayName("Тестирование добавления товара в избранное")
    void addProductToFavoriteTest() {
        int countFavorite = 0;
        mainPageMobile
                .skipBanner();
        commonStepsMobile
                .searchProduct(product_code_1);
        searchPageMobile
                .checkSearchResult(product_name_1);
        commonStepsMobile
                .addProductToFavoriteList(product_code_1, product_name_1);
        countFavorite++;
        productPageMobile
                .checkChangeCountProductToFavorite(countFavorite);
        commonStepsMobile
                .checkAddedProductToFavoriteList(product_name_1);
    }

    @Test
    @DisplayName("Тестирование удаления товара из избранного")
    void DeleteProductFromFavoriteTest() {
        int countFavorite = 0;
        mainPageMobile
                .skipBanner();
        Map<String, String> products =
                Map.of(product_code_1, product_name_1, product_code_2, product_name_2, product_code_4, product_name_4);
        for (Map.Entry<String, String> entry : products.entrySet()) {
            commonStepsMobile
                    .searchProduct(entry.getKey());
            searchPageMobile
                    .checkSearchResult(entry.getValue());
            commonStepsMobile
                    .addProductToFavoriteList(entry.getKey(), entry.getValue());
            countFavorite++;
            productPageMobile
                    .checkChangeCountProductToFavorite(countFavorite);
            commonStepsMobile
                    .checkAddedProductToFavoriteList(entry.getValue());
        }
        commonStepsMobile
                .deleteProductFromFavorite(product_name_2);
        favoritePageMobile
                .checkDelProductFromFavorite(product_name_2);
    }
}
