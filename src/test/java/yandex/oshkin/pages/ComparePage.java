package yandex.oshkin.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ComparePage {
    SelenideElement
            cleanCompareButton = $(".Compare__actions_delete-list");

    @Step("Открываем страницу сравнения")
    public ComparePage openComparePage() {
        open("compare/");
        $(".ComparePage__heading")
                .shouldHave(text("Сравнение товаров"));
        return this;
    }

    @Step("Очищаем список сравнения целиком")
    public ComparePage cleanCompare() {
        cleanCompareButton.click();
        $(".Compare__empty-products")
                .shouldHave(text("Нет товаров для сравнения"));
        return this;
    }
}
