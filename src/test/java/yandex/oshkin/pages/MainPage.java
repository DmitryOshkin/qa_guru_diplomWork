package yandex.oshkin.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    SelenideElement
            searchInput = $(".MainHeader__search .InputBox__input");

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        open("");
        $(".Main__category-wrapper .PopularCategoriesBox")
                .shouldHave(text("Популярные категории"));
        return this;
    }

    @Step("Вводим запрос на поиск по коду товара")
    public MainPage search(String value) {
        searchInput.setValue(value)
                .pressEnter();
        return this;
    }

    @Step("Проверяем количество товаров в корзине")
    public MainPage checkOrderCount(String value) {
        $(".HeaderMenu__buttons_basket .HeaderMenu__count")
                .shouldHave(text(value));
        return this;
    }

    @Step("Проверяем количество товаров в списке сравнения")
    public MainPage checkCompareCount(String value) {
        $(".HeaderMenu__button_compare .HeaderMenu__count")
                .shouldHave(text(value));
        return this;
    }
}
