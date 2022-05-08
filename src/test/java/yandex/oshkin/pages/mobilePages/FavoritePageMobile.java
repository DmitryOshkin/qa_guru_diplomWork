package yandex.oshkin.pages.mobilePages;

import com.codeborne.selenide.ElementsCollection;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FavoritePageMobile {

    @Step("Проверяем открытие избранного")
    public FavoritePageMobile checkOpenFavorite() {
        $(AppiumBy.id("ru.citilink:id/toolbar"))
                .$(AppiumBy.className("android.widget.TextView"))
                .shouldHave(attribute("text", "Избранное"));
        return this;
    }

    @Step("Проверяем наличие добавленного товара в избранное")
    public FavoritePageMobile checkProductInFavorite(String productName) {
        int counter = 0;
        ElementsCollection listProducts =
                $$(AppiumBy.id("ru.citilink:id/textViewTitle"));
        for (WebElement element : listProducts) {
            if (element.getAttribute("text").equals(productName)) {
                counter++;
            }
        }
        Assertions.assertEquals(1, counter, "Товар не найден");
        return this;
    }

    @Step("Убираем товар из избранного")
    public FavoritePageMobile delProductFromFavorite(String productName) {
        ElementsCollection listProducts =
                $$(AppiumBy.id("ru.citilink:id/textViewTitle"));
        for (WebElement element : listProducts) {
            if (element.getAttribute("text").equals(productName)) {
                $(AppiumBy.id("ru.citilink:id/imageViewFavorite")).click();
            }
        }
        return this;
    }

    @Step("Проверяем что в избранном нет товара")
    public FavoritePageMobile checkDelProductFromFavorite(String productName) {
        int counter = 0;
        ElementsCollection listProducts =
                $$(AppiumBy.id("ru.citilink:id/textViewTitle"));
        for (WebElement element : listProducts) {
            if (element.getAttribute("text").equals(productName)) {
                counter++;
            }
        }
        Assertions.assertEquals(0, counter, "Товар не убран из избранного");
        return this;
    }
}
