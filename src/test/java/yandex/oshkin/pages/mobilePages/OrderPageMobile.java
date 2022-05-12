package yandex.oshkin.pages.mobilePages;

import com.codeborne.selenide.ElementsCollection;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class OrderPageMobile {

    @Step("Проверяем открытие корзины")
    public OrderPageMobile checkOpenOrder() {
        sleep(3000);
        $(AppiumBy.id("ru.citilink:id/toolbar"))
                .$(AppiumBy.className("android.widget.TextView"))
                .shouldHave(attribute("text", "Корзина"));
        return this;
    }

    @Step("Проверяем наличие добавленного товара в корзине")
    public OrderPageMobile checkProductInOrder(String productName) {
        int counter = 0;
        ElementsCollection listProducts =
                $$(AppiumBy.id("ru.citilink:id/textViewCartProductName"));
        for (WebElement element : listProducts) {
            if (element.getAttribute("text").equals(productName)) {
                counter++;
            }
        }
        Assertions.assertEquals(1, counter, "Товар не найден");
        return this;
    }

    @Step("Нажимаем на кнопку Изменить")
    public OrderPageMobile tapToChangeButton() {
        $(AppiumBy.id("ru.citilink:id/cartChange")).click();
        return this;
    }

    @Step("Выбираем все товары в корзине")
    public OrderPageMobile selectAllProductsOnOrder() {
        $(AppiumBy.id("ru.citilink:id/checkBoxCartProductSelectorAll")).click();
        return this;
    }

    @Step("Удаляем выбраные товары из корзины")
    public OrderPageMobile delProductsFromOrder() {
        $(AppiumBy.id("ru.citilink:id/buttonCartDeleteProducts")).click();
        return this;
    }

    @Step("Проверяем что все товары удалены")
    public OrderPageMobile checkDeleteAllProductsFromOrder() {
        $(AppiumBy.id("ru.citilink:id/textEmptyTitle"))
                .shouldHave(attribute("text", "Ваша корзина пуста"));
        return this;
    }
}
