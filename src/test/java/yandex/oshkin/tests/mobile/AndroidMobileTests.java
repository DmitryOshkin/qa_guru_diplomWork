package yandex.oshkin.tests.mobile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import yandex.oshkin.tests.TestBase;

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
        commonStepsMobile
                .searchProduct(product_code_2);
        searchPageMobile
                .checkSearchResult(product_name_2);
        commonStepsMobile
                .addProductToOrder(product_code_2, product_name_2);
        countOrder++;
        productPageMobile
                .checkChangeCountProductToOrder(countOrder);
        commonStepsMobile
                .checkAddedProductToOrder(product_name_2);
        commonStepsMobile
                .searchProduct(product_code_4);
        searchPageMobile
                .checkSearchResult(product_name_4);
        commonStepsMobile
                .addProductToOrder(product_code_4, product_name_4);
        countOrder++;
        productPageMobile
                .checkChangeCountProductToOrder(countOrder);
        commonStepsMobile
                .checkAddedProductToOrder(product_name_4);
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
        commonStepsMobile
                .searchProduct(product_code_2);
        searchPageMobile
                .checkSearchResult(product_name_2);
        commonStepsMobile
                .addProductToFavoriteList(product_code_2, product_name_2);
        countFavorite++;
        productPageMobile
                .checkChangeCountProductToFavorite(countFavorite);
        commonStepsMobile
                .checkAddedProductToFavoriteList(product_name_2);
        commonStepsMobile
                .searchProduct(product_code_4);
        searchPageMobile
                .checkSearchResult(product_name_4);
        commonStepsMobile
                .addProductToFavoriteList(product_code_4, product_name_4);
        countFavorite++;
        productPageMobile
                .checkChangeCountProductToFavorite(countFavorite);
        commonStepsMobile
                .checkAddedProductToFavoriteList(product_name_4);
        commonStepsMobile
                .deleteProductFromFavorite(product_name_2);
        favoritePageMobile
                .checkDelProductFromFavorite(product_name_2);
    }
}
