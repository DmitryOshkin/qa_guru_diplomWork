package yandex.oshkin.pages.uiPages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPageUi {
    SelenideElement
            searchInput = $(".MainHeader__search .InputBox__input");

    @Step("Ищем товар")
    public MainPageUi searchProduct(String productCode) {
        openMainPage();
        closeCookies();
        search(productCode);
        return this;
    }

    @Step("Открываем главную страницу")
    public MainPageUi openMainPage() {
        open("/");
        $(".Main__category-wrapper .PopularCategoriesBox")
                .shouldHave(text("Популярные категории"));
        return this;
    }

    @Step("Вводим запрос на поиск по коду товара")
    public MainPageUi search(String value) {
        searchInput.setValue(value)
                .pressEnter();
        return this;
    }

    @Step("Проверяем количество товаров в корзине")
    public MainPageUi checkOrderCount(String value) {
        $(".HeaderMenu__buttons_basket .HeaderMenu__count")
                .shouldHave(text(value));
        return this;
    }

    @Step("Проверяем количество товаров в списке сравнения")
    public MainPageUi checkCompareCount(String value) {
        $(".HeaderMenu__button_compare .HeaderMenu__count")
                .shouldHave(text(value));
        return this;
    }

    @Step("Закрывеем всплывашку с куками")
    public MainPageUi closeCookies() {
        if ($(".PersonalDataConfirm").isDisplayed()) {
            $(".PersonalDataConfirm .PersonalDataConfirm__button")
                    .click();
        }
        return this;
    }
}
