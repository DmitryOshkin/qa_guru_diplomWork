package yandex.oshkin.pages.mobilePages;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;

public class SearchPageMobile {

    @Step("Проверяем открытие страницы поиска товара")
    public SearchPageMobile checkOpenSearchPage() {
        $(AppiumBy.id("ru.citilink:id/textEmptyTitle"))
                .shouldHave(attribute("text", "Введите название товара"));
        return this;
    }

    @Step("Вводим код товара в поле поиска")
    public SearchPageMobile insertSearchProduct(String productCode) {
        $(AppiumBy.id("ru.citilink:id/editTextSearchToolbar")).setValue(productCode);
        return this;
    }

    @Step("Проверяем результаты поиска")
    public SearchPageMobile checkSearchResult(String productName) {
        $(AppiumBy.id("ru.citilink:id/textViewSearchProductName"))
                .shouldHave(attribute("text", productName));
        return this;
    }

    @Step("Открываем найденный товар")
    public SearchPageMobile openProduct() {
        $(AppiumBy.id("ru.citilink:id/textViewSearchProductName")).click();
        return this;
    }
}
